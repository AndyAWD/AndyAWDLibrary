package tw.com.andyawd.andytool.logic;

import android.app.Dialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyStore;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import tw.com.andyawd.andyawdlibrary.AWDLog;
import tw.com.andyawd.andyawdlibrary.AWDToolMgr;
import tw.com.andyawd.andytool.base.AWDPApplication;
import tw.com.andyawd.andytool.base.AWDPConstants;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by andydai on 2017/11/30.
 * <p>
 * 目前先統一用這個執行API功能 2018/09/26
 * <p>
 * 用這個方法不用runOnUiThread，直接寫就好
 * 使用方法︰
 * 複製範例V1到這個Class的最下面，修改方法名稱和要Post的變數
 * Api回傳為0表示成功，AndroidAsyncHttpReturnZero傳送的message是return值為0的整串Json
 * Api回傳非0表示失敗，AndroidAsyncHttpReturnOther傳送的otherCode是錯誤碼，有可能回傳Api錯誤(1~9)、網頁通訊協定錯誤(404、500)、自定義錯誤碼(052934~052999)等，要寫case去接錯誤碼的數質
 *
 * 如何判斷要用V1還是V2版 ?
 * 如果return的值只要用0，那就用V2版
 * 如果return的值除了0以外還有別的值要用，那就用V1版，再從AndroidAsyncHttpReturnOther抓需要的值去判斷
 */

public class AWDPAndroidAsyncHttpMgr {

    private static AsyncHttpClient asyncHttpClient;
    private StringEntity stringEntity;
    private JSONObject jsonObject;
    private RequestParams requestParams;
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;

    /**
     * 建立回傳值
     */
    public interface AndroidAsyncHttpListener {
        void AndroidAsyncHttpReturnZero(String message) throws JSONException;

        void AndroidAsyncHttpReturnOther(String otherCode, String otherMessage);
    }

    private AWDPAndroidAsyncHttpMgr() {
        asyncHttpClient = new AsyncHttpClient();
        connectivityManager = (ConnectivityManager) AWDPApplication.getContext().getSystemService(CONNECTIVITY_SERVICE);
    }

    /**
     * 靜態內部單例
     * 第一次載入HLOkhttpMgr並不會初始化HLOkhttpMgr，只有在第一次調用getInstance的時候才會載入HLOkhttpHolder
     * ，不僅能保證線程安全，也能保證單例的唯一性，也延遲了單例的實例化，比較推薦；此外單例對象如果持有Context，
     * 那麼很容易引發內存洩露。此時需要注意傳遞給單例對象的Context最好是Application Context。
     */
    public static AWDPAndroidAsyncHttpMgr getInstance() {
        return AWDAndroidAsyncHttpHolder.hlAndroidAsyncHttpMgr;
    }

    private static class AWDAndroidAsyncHttpHolder {
        private static final AWDPAndroidAsyncHttpMgr hlAndroidAsyncHttpMgr = new AWDPAndroidAsyncHttpMgr();
    }

    /**
     * 製作範例V1版，不傳入Context手動處理錯誤碼顯示方式，下面有可以直接複製貼上
     * 命名規則：【Call_"API名稱或知道在呼什麼的名稱"_Api】
     */
    public void Call_AsyncHttpClientMgrDemo_Api(String identifier, final AndroidAsyncHttpListener androidAsyncHttpListener) {
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {  //判斷有沒有網路，沒網路就什麼都不用做
            jsonObject = new JSONObject();  //post字串
            try {
                jsonObject.put(AWDPConstants.AndroidAsyncHttpInterface, "要記得改");    //API名稱，可以修改
                jsonObject.put(AWDPConstants.AndroidAsyncHttpIdentifier, identifier);   //API需要的值，可以修改增加減少
                stringEntity = new StringEntity(jsonObject.toString(), AWDPConstants.AndroidAsyncHttpPostUTF8);
            } catch (JSONException e) {
                AWDLog.v("Json製作失敗 : " + e.getMessage());   //訊息，從HLMLog開關
                androidAsyncHttpListener.AndroidAsyncHttpReturnOther(AWDPConstants.PostJsonFailure, String.valueOf(e));
            }
            AsyncHttpClientPost(androidAsyncHttpListener);

        } else {
            AWDLog.v("沒有網路");   //訊息，從HLMLog開關
            androidAsyncHttpListener.AndroidAsyncHttpReturnOther(AWDPConstants.NoConnection, "");
        }
    }

    /**
     * Post送出V1版，不傳入Context手動處理錯誤碼顯示方式
     *
     * @param androidAsyncHttpListener
     */
    private void AsyncHttpClientPost(final AndroidAsyncHttpListener androidAsyncHttpListener) {
        if (!AWDToolMgr.getInstance().isPhoneSetProxy()) {
            if (TextUtils.isEmpty(AWDPConstants.PostUrl)) {
                androidAsyncHttpListener.AndroidAsyncHttpReturnOther(AWDPConstants.PostUrlFailure, "");
            } else {
                asyncHttpClient.post(AWDPApplication.getContext(), AWDPConstants.PostUrl, stringEntity, AWDPConstants.AndroidAsyncHttpPostType, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        //Log.v(TAG, "伺服器訊息 : " + String.valueOf(statusCode) + "\n" + "回傳失敗字串 : " + responseString + "\n" + "throwable : " + throwable);   //訊息，手動開關
                        AWDLog.v("伺服器訊息 : " + String.valueOf(statusCode) + "\n" + "回傳失敗字串 : " + responseString + "\n" + "throwable : " + throwable);   //訊息，從HLMLog開關
                        AsyncHttpClientPostFailure(statusCode, responseString, throwable, androidAsyncHttpListener);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        //Log.v(TAG, "伺服器訊息 : " + String.valueOf(statusCode) + "\n" + "回傳成功字串 : " + responseString);   //訊息，手動開關
                        AWDLog.v("伺服器訊息 : " + String.valueOf(statusCode) + "\n" + "回傳成功字串 : " + responseString);   //訊息，從HLMLog開關
                        AsyncHttpClientPostSuccess(statusCode, responseString, androidAsyncHttpListener);
                    }
                });
            }
        }
    }

    /**
     * 製作範例V1版，API回傳成功後統一處理
     *
     * @param statusCode
     * @param responseString
     * @param androidAsyncHttpListener
     */
    private void AsyncHttpClientPostSuccess(int statusCode, String responseString, final AndroidAsyncHttpListener androidAsyncHttpListener) {
        switch (statusCode) {    //伺服器訊息，回傳200過第一關
            case 200:
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    String strReturnValue = jsonObject.getString(AWDPConstants.AndroidAsyncHttpReturnValue);
                    switch (strReturnValue) {    //API回傳訊息，回傳0過第二關
                        case "0":
                            androidAsyncHttpListener.AndroidAsyncHttpReturnZero(responseString);
                            break;
                        default:
                            androidAsyncHttpListener.AndroidAsyncHttpReturnOther(strReturnValue, responseString);
                            break;
                    }
                } catch (JSONException e) {
                    AWDLog.v("Json解析失敗 : " + e.getMessage());   //訊息，從HLMLog開關
                    androidAsyncHttpListener.AndroidAsyncHttpReturnOther(AWDPConstants.ReturnJsonFailure, String.valueOf(e));
                } catch (Exception e) {
                    AWDLog.v("Exception失敗 : " + e.getMessage());   //訊息，從HLMLog開關
                    androidAsyncHttpListener.AndroidAsyncHttpReturnOther(AWDPConstants.UnknowFailure, String.valueOf(e));
                }
                break;
            default:
                AWDLog.v("伺服器訊息非200 : " + statusCode);   //訊息，從HLMLog開關
                androidAsyncHttpListener.AndroidAsyncHttpReturnOther(String.valueOf(statusCode), responseString);
                break;
        }
    }

    /**
     * 製作範例V1版，API回傳失敗後統一處理
     *
     * @param statusCode
     * @param responseString
     * @param androidAsyncHttpListener
     */
    private void AsyncHttpClientPostFailure(int statusCode, String responseString, Throwable throwable, final AndroidAsyncHttpListener androidAsyncHttpListener) {
        androidAsyncHttpListener.AndroidAsyncHttpReturnOther(String.valueOf(statusCode), "responseString : " + String.valueOf(responseString) + " / throwable : " + String.valueOf(throwable));
    }


    /**
     * 另一種製作範例，Hen難用而且不維護，單純展示有這個功能，千萬別用這個來Post
     * 命名規則：【Call_"API名稱或知道在呼什麼的名稱"_Api】
     */
    public void Call_AsyncHttpClientMgrDemo2_Api(String identifier, AsyncHttpResponseHandler responseHandler) {

        //post字串
        requestParams = new RequestParams();
        requestParams.setUseJsonStreamer(true);
        requestParams.put("interface", "about_me");
        requestParams.put("identifier", identifier);
//        Log.d(TAG, url);   //如果想知道Post出去的網址長什麼樣就打開她
//        Log.d(TAG, String.valueOf(requestParams));   //如果想知道Post出去的字串長什麼樣就打開她

        //發送
        asyncHttpClient.post(AWDPConstants.PostUrl, requestParams, responseHandler);
    }

    /**
     * 同場加映，GET寫法範例
     */
    public void Call_DownloadApk(AsyncHttpResponseHandler responseHandler) {
        asyncHttpClient.get(AWDPConstants.PostUrl, responseHandler);
    }

    /*----------------開始新增吧----------------*/
    public void Call_AppVersionV2_GService_Api(final AndroidAsyncHttpListener androidAsyncHttpListener) {
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {  //判斷有沒有網路，沒網路就什麼都不用做
            jsonObject = new JSONObject();  //post字串
            try {
                jsonObject.put(AWDPConstants.AndroidAsyncHttpInterface, "app_version_v2");    //API名稱，可以修改
                jsonObject.put("system", "2");   //API需要的值，可以修改增加減少
                stringEntity = new StringEntity(jsonObject.toString(), AWDPConstants.AndroidAsyncHttpPostUTF8);
            } catch (JSONException e) {
                AWDLog.v("Json製作失敗 : " + e.getMessage());   //訊息，從HLMLog開關
                androidAsyncHttpListener.AndroidAsyncHttpReturnOther(AWDPConstants.PostJsonFailure, String.valueOf(e));
            }
            AWDLog.v("Post網址 : " + AWDPConstants.PostUrl + "\n" + "Post字串 : " + String.valueOf(jsonObject)); //訊息，從HLMLog開關
            AsyncHttpClientPost(androidAsyncHttpListener);

        } else {
            AWDLog.v("沒有網路");   //訊息，從HLMLog開關
            androidAsyncHttpListener.AndroidAsyncHttpReturnOther(AWDPConstants.NoConnection, "");
        }
    }
}
