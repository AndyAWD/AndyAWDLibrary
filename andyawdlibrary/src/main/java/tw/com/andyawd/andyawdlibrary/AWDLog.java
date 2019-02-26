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

    /**
     * Tag統一變成maho
     */
    public static final boolean MAHORO_TAG_MODE_OPEN = true;

    /**
     * Tag自己輸入
     */
    public static final boolean MAHORO_TAG_MODE_CLOSE = false;

    /**
     * 讓Log變得很漂亮，小心不要烤了他
     */
    public static final boolean MESSAGE_BEAUTIFUL_MODE_OPEN = true;

    /**
     * 讓Log正常顯示
     */
    public static final boolean MESSAGE_BEAUTIFUL_MODE_CLOSE = false;

    /**
     * 關閉全部的Log
     */
    public static final int LOG_OFF = 6;

    /**
     * 開啟.v等級以上的Log，打開這個的話能看到全部的API發送和接收訊息
     */
    public static final int VERBOSE = 1;

    /**
     * 開啟.d等級以上的Log
     */
    public static final int DEBUG = 2;

    /**
     * 開啟.w等級以上的Log
     */
    public static final int WARN = 3;

    /**
     * 開啟.i等級以上的Log
     */
    public static final int INFO = 4;

    /**
     * 開啟.e等級以上的Log
     */
    public static final int ERROR = 5;

    private static final String LOG_HEADER = "◢◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◤◥◣";
    private static final String LOG_THREAD = "◥◣執行緒 : ";
    private static final String LOG_FUNCTION = "◢◤執行方法(類名:行數) : ";
    private static final String LOG_HORIZONTAL = "◥◣==============================================================";
    private static final String LOG_MESSAGE_WALL = "◢◤";
    private static final String LOG_FOOTER = "◥◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◣◢◤";
    private static final String LOG_LEFT_PARENTHESES = "(";
    private static final String LOG_RIGHT_PARENTHESES = ")";
    private static final String LOG_LEFT_SQUARE_BRACKET = "[";
    private static final String LOG_RIGHT_SQUARE_BRACKET = "]";
    private static final String LOG_LEFT_CURLY_BRACKET = "{";
    private static final String LOG_RIGHT_CURLY_BRACKET = "}";
    private static final String LOG_COLON = ":";
    private static final String LOG_POST = "◥◣Post字串無縮排 : \n";
    private static final String LOG_POST_NORMAL = "\n Post字串 : ";
    private static final String LOG_POST_INDENTATION = "◢◤Post字串有縮排 : \n";
    private static int level = LOG_OFF;
    private static boolean mahoroTagMode = MAHORO_TAG_MODE_CLOSE;
    private static boolean messageBeautifulMode = MESSAGE_BEAUTIFUL_MODE_OPEN;
    private static String mahoroTegText = "maho";

    /**
     * 設定Tag的自定義文字，預設"maho日期"
     */
    public static void setMahoroTegText(String mahoroTegText) {
        AWDLog.mahoroTegText = mahoroTegText;
    }

    /**
     * 設定Tag是否加上自定義文字，預設開啟
     */
    public static void isMahoroTagModeOpen(boolean open) {
        AWDLog.mahoroTagMode = open;
    }

    /**
     * 設定訊息漂亮模式，預設開啟
     */
    public static void isMessageBeautifulModeOpen(boolean open) {
        AWDLog.messageBeautifulMode = open;
    }

    /**
     * 傳入Log顯示等級，預設全關
     */
    public static void setLogLevel(int level) {
        AWDLog.level = level;
    }

    private static void logBuild(int level, String tag, String message) {
        if (isLogCanShow(level)) {
            return;
        }

        if (MAHORO_TAG_MODE_OPEN || TextUtils.isEmpty(tag)) {
            tag = mahoroTegText + getToday();
        }

        if (messageBeautifulMode) {
            logShow(level, tag, LOG_HEADER);
            logShow(level, tag, LOG_THREAD + Thread.currentThread().getName());
            logShow(level, tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            logShow(level, tag, LOG_HORIZONTAL);
            logShow(level, tag, LOG_MESSAGE_WALL + message);
            logShow(level, tag, LOG_FOOTER);
        } else {
            logShow(level, tag, message);
        }
    }

    private static boolean isLogCanShow(int logLevel) {
        if (level >= logLevel) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 讓Log的Tag自動加上月、日
     */
    private static String getToday() {

        Date date = new Date();
        String month = String.format("%tm", date);
        String day = String.format("%td", date);

        return month + day;
    }

    /**
     * 抓Log的相關資料
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

    private static void logShow(int level, String tag, String message) {
        switch (level) {
            case VERBOSE:
                Log.v(tag, message);
                break;
            case DEBUG:
                Log.d(tag, message);
                break;
            case WARN:
                Log.w(tag, message);
                break;
            case INFO:
                Log.i(tag, message);
                break;
            case ERROR:
                Log.e(tag, message);
                break;
            default:
                Log.v(tag, message);
                break;
        }
    }

    /**
     * Log級別 VERBOSE
     */
    public static void v(String tag, String message) {
        logBuild(VERBOSE, tag, message);
    }

    public static void v(String message) {
        logBuild(VERBOSE, "", message);
    }

    /**
     * Log級別 DEBUG
     */
    public static void d(String tag, String message) {
        logBuild(DEBUG, tag, message);
    }

    public static void d(String message) {
        logBuild(DEBUG, "", message);
    }

    /**
     * Log級別 WARN
     */
    public static void w(String tag, String message) {
        logBuild(WARN, tag, message);
    }

    public static void w(String message) {
        logBuild(WARN, "", message);
    }

    /**
     * Log級別 INFO
     */
    public static void i(String tag, String message) {
        logBuild(INFO, tag, message);
    }

    public static void i(String message) {
        logBuild(INFO, "", message);
    }

    /**
     * Log級別 ERROR
     */
    public static void e(String tag, String message) {
        logBuild(ERROR, tag, message);
    }

    public static void e(String message) {
        logBuild(ERROR, "", message);
    }

    public static void api(String apiInfo, String apiJson) {
        isLogCanShow(VERBOSE);

        if (messageBeautifulMode == MESSAGE_BEAUTIFUL_MODE_OPEN && TextUtils.isEmpty(apiJson)) {
            logShow(VERBOSE, mahoroTegText + getToday(), LOG_HEADER);
            logShow(VERBOSE, mahoroTegText + getToday(), LOG_MESSAGE_WALL + apiInfo);
            logShow(VERBOSE, mahoroTegText + getToday(), LOG_FOOTER);
            return;
        }

        if (messageBeautifulMode == MESSAGE_BEAUTIFUL_MODE_OPEN && !TextUtils.isEmpty(apiJson)) {
            try {
                if (apiJson.startsWith(LOG_LEFT_CURLY_BRACKET)) {
                    JSONObject joMsg = new JSONObject(apiJson);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_HEADER);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_MESSAGE_WALL + apiInfo);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_HORIZONTAL);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_POST + apiJson);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_POST_INDENTATION + joMsg.toString(4));
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_FOOTER);
                }
                if (apiJson.startsWith(LOG_LEFT_SQUARE_BRACKET)) {
                    JSONArray jaMsg = new JSONArray(apiJson);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_HEADER);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_MESSAGE_WALL + apiInfo);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_HORIZONTAL);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_POST + apiJson);
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_POST_INDENTATION + jaMsg.toString(4));
                    logShow(VERBOSE, mahoroTegText + getToday(), LOG_FOOTER);
                }
            } catch (Exception e) {
                logShow(VERBOSE, mahoroTegText + getToday(), LOG_HEADER);
                logShow(VERBOSE, mahoroTegText + getToday(), LOG_MESSAGE_WALL + apiInfo);
                logShow(VERBOSE, mahoroTegText + getToday(), LOG_HORIZONTAL);
                logShow(VERBOSE, mahoroTegText + getToday(), LOG_POST + apiJson);
                logShow(VERBOSE, mahoroTegText + getToday(), LOG_FOOTER);
            }
            return;
        }

        if (messageBeautifulMode == MESSAGE_BEAUTIFUL_MODE_CLOSE) {
            logShow(VERBOSE, mahoroTegText + getToday(), apiInfo + LOG_POST_NORMAL + apiJson);
            return;
        }
    }
}
