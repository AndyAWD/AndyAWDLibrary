package tw.com.andyawd.andyawdlibrary;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by andydai on 2018/2/2.
 */

public class AWDLog {

    private static String MahoroName = "maho";
    private static final int LogOFF = 6;
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int WARN = 3;
    private static final int INFO = 4;
    private static final int ERROR = 5;
    private static final int MahoroModeOpen = 1046;
    private static final int MahoroModeClose = 1047;
    private static final int BeautifulModeOpen = 1048;
    private static final int BeautifulModeClose = 1049;

    //    private static final int level = LogOFF;   //關閉全部的Log
    private static int level = VERBOSE;    //開啟.v等級以上的Log，打開這個的話能看到全部的API發送和接收訊息
//    private static final int level = DEBUG;    //開啟.d等級以上的Log
//    private static final int level = WARN;    //開啟.w等級以上的Log
//    private static final int level = INFO;    //開啟.i等級以上的Log
//    private static final int level = ERROR;    //開啟.e等級以上的Log

    private static int MahoroMode = MahoroModeClose;   //Tag額外加上maho
//    private static final int MahoroMode = AWDLog.MahoroModeOpen;  //Tag不加maho

    private static int BeautifulMode = BeautifulModeOpen;  //讓Log變得很漂亮，小心不要烤了他
//    private static final int BeautifulMode = AWDLog.BeautifulModeClose;   //讓Log正常顯示

    /**
     * 讓Log的Tag自動加上月、日
     *
     * @return 回傳月 + 日
     */
    private static String MahoroDate() {

        Date date = new Date();
        String strMonth = String.format("%tm", date);
        String strDay = String.format("%td", date);

        return strMonth + strDay;
    }

    /**
     * 抓Log的相關資料
     *
     * @return
     */
    private static StackTraceElement getStackTraceElement() {
        StackTraceElement stackTraceElement = null;
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        boolean target = false;

        for (StackTraceElement traceElement : stackTraceElements) {
            boolean isfindAWDLogClass = traceElement.getClassName().equals(AWDLog.class.getName());
            if (target && !isfindAWDLogClass) {
                stackTraceElement = traceElement;
                break;
            }
            target = isfindAWDLogClass;
        }
        return stackTraceElement;
    }

    /**
     * Log級別 VERBOSE
     *
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (level <= AWDLog.VERBOSE) {
            switch (MahoroMode){
                case MahoroModeOpen:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.v(MahoroName + MahoroDate() + tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.v(MahoroName + MahoroDate() + tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.v(MahoroName + MahoroDate() + tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.v(MahoroName + MahoroDate() + tag, "◥◣==============================================================");
                            Log.v(MahoroName + MahoroDate() + tag, "◢◤" + msg);
                            Log.v(MahoroName + MahoroDate() + tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.v(MahoroName + MahoroDate() + tag, msg);
                            break;
                    }
                    break;
                case MahoroModeClose:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.v(tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.v(tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.v(tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.v(tag, "◥◣==============================================================");
                            Log.v(tag, "◢◤" + msg);
                            Log.v(tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.v(tag, msg);
                            break;
                    }
                    break;
            }
        }
    }

    public static void v(String msg) {
        if (level <= AWDLog.VERBOSE) {
            switch (BeautifulMode){
                case BeautifulModeOpen:
                    Log.v(MahoroName + MahoroDate(), "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                    Log.v(MahoroName + MahoroDate(), "◥◣執行緒 : " + Thread.currentThread().getName());
                    Log.v(MahoroName + MahoroDate(), "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                    Log.v(MahoroName + MahoroDate(), "◥◣==============================================================");
                    Log.v(MahoroName + MahoroDate(), "◢◤" + msg);
                    Log.v(MahoroName + MahoroDate(), "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                    break;
                case BeautifulModeClose:
                    Log.v(MahoroName + MahoroDate(), msg);
                    break;
            }
        }
    }


    /**
     * Log級別 DEBUG
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (level <= AWDLog.DEBUG) {
            switch (MahoroMode){
                case MahoroModeOpen:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.d(MahoroName + MahoroDate() + tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.d(MahoroName + MahoroDate() + tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.d(MahoroName + MahoroDate() + tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.d(MahoroName + MahoroDate() + tag, "◥◣==============================================================");
                            Log.d(MahoroName + MahoroDate() + tag, "◢◤" + msg);
                            Log.d(MahoroName + MahoroDate() + tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.d(MahoroName + MahoroDate() + tag, msg);
                            break;
                    }
                    break;
                case MahoroModeClose:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.d(tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.d(tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.d(tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.d(tag, "◥◣==============================================================");
                            Log.d(tag, "◢◤" + msg);
                            Log.d(tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.d(tag, msg);
                            break;
                    }
                    break;
            }
        }
    }

    public static void d(String msg) {
        if (level <= AWDLog.DEBUG) {
            switch (BeautifulMode){
                case BeautifulModeOpen:
                    Log.d(MahoroName + MahoroDate(), "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                    Log.d(MahoroName + MahoroDate(), "◥◣執行緒 : " + Thread.currentThread().getName());
                    Log.d(MahoroName + MahoroDate(), "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                    Log.d(MahoroName + MahoroDate(), "◥◣==============================================================");
                    Log.d(MahoroName + MahoroDate(), "◢◤" + msg);
                    Log.d(MahoroName + MahoroDate(), "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                    break;
                case BeautifulModeClose:
                    Log.d(MahoroName + MahoroDate(), msg);
                    break;
            }
        }
    }


    /**
     * Log級別 WARN
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (level <= AWDLog.DEBUG) {
            switch (MahoroMode){
                case MahoroModeOpen:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.w(MahoroName + MahoroDate() + tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.w(MahoroName + MahoroDate() + tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.w(MahoroName + MahoroDate() + tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.w(MahoroName + MahoroDate() + tag, "◥◣==============================================================");
                            Log.w(MahoroName + MahoroDate() + tag, "◢◤" + msg);
                            Log.w(MahoroName + MahoroDate() + tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.w(MahoroName + MahoroDate() + tag, msg);
                            break;
                    }
                    break;
                case MahoroModeClose:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.w(tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.w(tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.w(tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.w(tag, "◥◣==============================================================");
                            Log.w(tag, "◢◤" + msg);
                            Log.w(tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.w(tag, msg);
                            break;
                    }
                    break;
            }
        }
    }

    public static void w(String msg) {
        if (level <= AWDLog.WARN) {
            switch (BeautifulMode){
                case BeautifulModeOpen:
                    Log.w(MahoroName + MahoroDate(), "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                    Log.w(MahoroName + MahoroDate(), "◥◣執行緒 : " + Thread.currentThread().getName());
                    Log.w(MahoroName + MahoroDate(), "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                    Log.w(MahoroName + MahoroDate(), "◥◣==============================================================");
                    Log.w(MahoroName + MahoroDate(), "◢◤" + msg);
                    Log.w(MahoroName + MahoroDate(), "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                    break;
                case BeautifulModeClose:
                    Log.w(MahoroName + MahoroDate(), msg);
                    break;
            }
        }
    }


    /**
     * Log級別 INFO
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (level <= AWDLog.DEBUG) {
            switch (MahoroMode){
                case MahoroModeOpen:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.i(MahoroName + MahoroDate() + tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.i(MahoroName + MahoroDate() + tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.i(MahoroName + MahoroDate() + tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.i(MahoroName + MahoroDate() + tag, "◥◣==============================================================");
                            Log.i(MahoroName + MahoroDate() + tag, "◢◤" + msg);
                            Log.i(MahoroName + MahoroDate() + tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.i(MahoroName + MahoroDate() + tag, msg);
                            break;
                    }
                    break;
                case MahoroModeClose:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.i(tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.i(tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.i(tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.i(tag, "◥◣==============================================================");
                            Log.i(tag, "◢◤" + msg);
                            Log.i(tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.i(tag, msg);
                            break;
                    }
                    break;
            }
        }
    }

    public static void i(String msg) {
        if (level <= AWDLog.INFO) {
            switch (BeautifulMode){
                case BeautifulModeOpen:
                    Log.i(MahoroName + MahoroDate(), "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                    Log.i(MahoroName + MahoroDate(), "◥◣執行緒 : " + Thread.currentThread().getName());
                    Log.i(MahoroName + MahoroDate(), "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                    Log.i(MahoroName + MahoroDate(), "◥◣==============================================================");
                    Log.i(MahoroName + MahoroDate(), "◢◤" + msg);
                    Log.i(MahoroName + MahoroDate(), "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                    break;
                case BeautifulModeClose:
                    Log.i(MahoroName + MahoroDate(), msg);
                    break;
            }
        }
    }

    /**
     * Log級別 ERROR
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (level <= AWDLog.ERROR) {
            switch (MahoroMode){
                case MahoroModeOpen:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.e(MahoroName + MahoroDate() + tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.e(MahoroName + MahoroDate() + tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.e(MahoroName + MahoroDate() + tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.e(MahoroName + MahoroDate() + tag, "◥◣==============================================================");
                            Log.e(MahoroName + MahoroDate() + tag, "◢◤" + msg);
                            Log.e(MahoroName + MahoroDate() + tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.e(MahoroName + MahoroDate() + tag, msg);
                            break;
                    }
                    break;
                case MahoroModeClose:
                    switch (BeautifulMode){
                        case BeautifulModeOpen:
                            Log.e(tag, "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.e(tag, "◥◣執行緒 : " + Thread.currentThread().getName());
                            Log.e(tag, "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                            Log.e(tag, "◥◣==============================================================");
                            Log.e(tag, "◢◤" + msg);
                            Log.e(tag, "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                            break;
                        case BeautifulModeClose:
                            Log.e(tag, msg);
                            break;
                    }
                    break;
            }
        }
    }

    public static void e(String msg) {
        if (level <= AWDLog.ERROR) {
            switch (BeautifulMode){
                case BeautifulModeOpen:
                    Log.e(MahoroName + MahoroDate(), "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                    Log.e(MahoroName + MahoroDate(), "◥◣執行緒 : " + Thread.currentThread().getName());
                    Log.e(MahoroName + MahoroDate(), "◢◤執行方法(類名:行數) : " + getStackTraceElement().getMethodName() + "(" + getStackTraceElement().getFileName() + ":" + getStackTraceElement().getLineNumber() + ")");
                    Log.e(MahoroName + MahoroDate(), "◥◣==============================================================");
                    Log.e(MahoroName + MahoroDate(), "◢◤" + msg);
                    Log.e(MahoroName + MahoroDate(), "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                    break;
                case BeautifulModeClose:
                    Log.e(MahoroName + MahoroDate(), msg);
                    break;
            }
        }
    }

    public static void api(String apiInfo, String apiJson) {
        switch (BeautifulMode){
            case BeautifulModeOpen:
                if (TextUtils.isEmpty(apiJson)) {
                    Log.v(MahoroName + MahoroDate(), "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                    Log.v(MahoroName + MahoroDate(), "◥◣" + apiInfo);
                    Log.v(MahoroName + MahoroDate(), "◢◤◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                } else {
                    try {
                        if (apiJson.startsWith("{")) {
                            JSONObject joMsg = new JSONObject(apiJson);
                            Log.v(MahoroName + MahoroDate(), "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.v(MahoroName + MahoroDate(), "◥◣" + apiInfo);
                            Log.v(MahoroName + MahoroDate(), "◢◤==============================================================");
                            Log.v(MahoroName + MahoroDate(), "◥◣Post字串無縮排 : \n" + apiJson);
                            Log.v(MahoroName + MahoroDate(), "◢◤Post字串有縮排 : \n" + joMsg.toString(4));
                            Log.v(MahoroName + MahoroDate(), "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                        }
                        if (apiJson.startsWith("[")) {
                            JSONArray jaMsg = new JSONArray(apiJson);
                            Log.v(MahoroName + MahoroDate(), "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                            Log.v(MahoroName + MahoroDate(), "◥◣" + apiInfo);
                            Log.v(MahoroName + MahoroDate(), "◢◤==============================================================");
                            Log.v(MahoroName + MahoroDate(), "◥◣Post字串無縮排 : \n" + apiJson);
                            Log.v(MahoroName + MahoroDate(), "◢◤Post字串有縮排 : \n" + jaMsg.toString(4));
                            Log.v(MahoroName + MahoroDate(), "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                        }
                    } catch (Exception e) {
                        Log.v(MahoroName + MahoroDate(), "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣");
                        Log.v(MahoroName + MahoroDate(), "◥◣" + apiInfo);
                        Log.v(MahoroName + MahoroDate(), "◢◤==============================================================");
                        Log.v(MahoroName + MahoroDate(), "◥◣Post字串無縮排 : \n" + apiJson);
                        Log.v(MahoroName + MahoroDate(), "◢◤◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤");
                    }
                }
                break;
            case BeautifulModeClose:
                Log.v(MahoroName + MahoroDate(), apiInfo + "\n" + "Post字串 : " + apiJson);
                break;
        }
    }

    /**
     * 設定Log的Tag名稱，預設maho + 月日
     * @param mahoroName
     */
    public static void setMahoroName(String mahoroName) {
        MahoroName = mahoroName;
    }

    /**
     * Tag額外加上maho
     * @param mahoroMode
     */
    public static void setMahoroMode(int mahoroMode) {
        MahoroMode = mahoroMode;
    }

    /**
     * 讓Log變得很漂亮，小心不要烤了他
     * @param beautifulMode
     */
    public static void setBeautifulMode(int beautifulMode) {
        BeautifulMode = beautifulMode;
    }
}
