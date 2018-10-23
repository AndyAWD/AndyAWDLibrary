package tw.com.andyawd.andytool.base;

/**
 * Created by andydai on 2018/10/22.
 */

public class AWDPConstants {

    public static final int VERBOSE = 1;    //Log模式等級
    public static final int DEBUG = 2;  //Log模式等級
    public static final int WARN = 3;   //Log模式等級
    public static final int INFO = 4;   //Log模式等級
    public static final int ERROR = 5;  //Log模式等級
    public static final int LogOFF = 6; //Log模式等級

    public static final String MahoroMode = "maho";

    public static final String PostUrl = "http://test_service.goe.live/mylive/interface_v2.php";

    public static final String AndroidAsyncHttpInterface = "interface";
    public static final String AndroidAsyncHttpReturnValue = "returnValue";
    public static final String AndroidAsyncHttpIdentifier = "identifier";

    public static final String AndroidAsyncHttpPostUTF8 = "UTF-8";
    public static final String AndroidAsyncHttpPostType = "application/json";

    public static final String NoConnection = "052934";             //手機未開啟網路
    public static final String AndroidAsyncHttpFailure = "052936";  //AndroidAsyncHttp本身發生錯誤
    public static final String OkhttpFailure = "052935";            //跟Okhttp分手惹，以後用不到惹
    public static final String ApacheFailure = "052937";            //跟Apache分手惹，以後用不到惹
    public static final String PostJsonFailure = "052942";          //發送的Json格式錯誤
    public static final String ReturnJsonFailure = "052938";        //回傳的Json格式錯誤
    public static final String UnknowFailure = "052939";            //被catch到Exception錯誤
    public static final String SocketFailure = "052940";            //Socket無連線
    public static final String PostUrlFailure = "052941";           //Post的網址為空值
    public static final String ProxyFailure = "052943";             //手機開啟Proxy


}
