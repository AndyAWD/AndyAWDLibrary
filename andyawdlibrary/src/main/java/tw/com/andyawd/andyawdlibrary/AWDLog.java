package tw.com.andyawd.andyawdlibrary;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by andydai on 2018/2/2.
 * 使用時需要設定setLogLevel()，怕麻煩的話可以做在Application
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

    private static String mahoroTegText = "maho";
    private static int level = AWDConstants.LOG_CLOSE;

    private static boolean mahoroTagMode = MAHORO_TAG_MODE_CLOSE;
    private static boolean messageBeautifulMode = MESSAGE_BEAUTIFUL_MODE_OPEN;

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

    /**
     * 判斷能不能顯示Log
     */
    private static boolean isLogCanShow(int logLevel) {
        if (level > logLevel) {
            return true;
        } else {
            return false;
        }
    }

    private static void logBuild(int level, String tag, String message) {
        if (isLogCanShow(level)) {
            return;
        }

        if (MAHORO_TAG_MODE_OPEN || TextUtils.isEmpty(tag)) {
            tag = mahoroTegText + getToday();
        }

        if (messageBeautifulMode) {
            logShow(level, tag, AWDConstants.LOG_HEADER);
            logShow(level, tag, AWDConstants.LOG_THREAD + Thread.currentThread().getName());
            logShow(level, tag, AWDConstants.LOG_FUNCTION + getStackTraceElement().getMethodName() + AWDConstants.LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + AWDConstants.LOG_COLON + getStackTraceElement().getLineNumber() + AWDConstants.LOG_RIGHT_PARENTHESES);
            logShow(level, tag, AWDConstants.LOG_HORIZONTAL);
            logShow(level, tag, AWDConstants.LOG_MESSAGE_WALL + message);
            logShow(level, tag, AWDConstants.LOG_FOOTER);
        } else {
            logShow(level, tag, message);
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
            case AWDConstants.LOG_VERBOSE:
                Log.v(tag, message);
                break;
            case AWDConstants.LOG_DEBUG:
                Log.d(tag, message);
                break;
            case AWDConstants.LOG_WARN:
                Log.w(tag, message);
                break;
            case AWDConstants.LOG_INFO:
                Log.i(tag, message);
                break;
            case AWDConstants.LOG_ERROR:
                Log.e(tag, message);
                break;
            default:
                Log.v(tag, message);
                break;
        }
    }

    /**
     * Log級別 LOG_VERBOSE
     */
    public static void v(String tag, String message) {
        logBuild(AWDConstants.LOG_VERBOSE, tag, message);
    }

    public static void v(String message) {
        logBuild(AWDConstants.LOG_VERBOSE, "", message);
    }

    /**
     * Log級別 LOG_DEBUG
     */
    public static void d(String tag, String message) {
        logBuild(AWDConstants.LOG_DEBUG, tag, message);
    }

    public static void d(String message) {
        logBuild(AWDConstants.LOG_DEBUG, "", message);
    }

    /**
     * Log級別 LOG_WARN
     */
    public static void w(String tag, String message) {
        logBuild(AWDConstants.LOG_WARN, tag, message);
    }

    public static void w(String message) {
        logBuild(AWDConstants.LOG_WARN, "", message);
    }

    /**
     * Log級別 LOG_INFO
     */
    public static void i(String tag, String message) {
        logBuild(AWDConstants.LOG_INFO, tag, message);
    }

    public static void i(String message) {
        logBuild(AWDConstants.LOG_INFO, "", message);
    }

    /**
     * Log級別 LOG_ERROR
     */
    public static void e(String tag, String message) {
        logBuild(AWDConstants.LOG_ERROR, tag, message);
    }

    public static void e(String message) {
        logBuild(AWDConstants.LOG_ERROR, "", message);
    }

    /**
     * Log級別 LOG_VERBOSE，印出JSON字串用
     */
    public static void api(String apiInfo, String apiJson) {
        if (isLogCanShow(AWDConstants.LOG_VERBOSE)) {
            return;
        }

        if (messageBeautifulMode && TextUtils.isEmpty(apiJson)) {
            logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_HEADER);
            logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_MESSAGE_WALL + apiInfo);
            logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_FOOTER);
            return;
        }

        if (messageBeautifulMode && !TextUtils.isEmpty(apiJson)) {
            try {
                if (apiJson.startsWith(AWDConstants.LOG_LEFT_CURLY_BRACKET)) {
                    JSONObject joMsg = new JSONObject(apiJson);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_HEADER);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_MESSAGE_WALL + apiInfo);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_HORIZONTAL);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_POST + apiJson);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_POST_INDENTATION + joMsg.toString(4));
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_FOOTER);
                }
                if (apiJson.startsWith(AWDConstants.LOG_LEFT_SQUARE_BRACKET)) {
                    JSONArray jaMsg = new JSONArray(apiJson);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_HEADER);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_MESSAGE_WALL + apiInfo);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_HORIZONTAL);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_POST + apiJson);
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_POST_INDENTATION + jaMsg.toString(4));
                    logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_FOOTER);
                }
            } catch (Exception e) {
                logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_HEADER);
                logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_MESSAGE_WALL + apiInfo);
                logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_HORIZONTAL);
                logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_POST + apiJson);
                logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), AWDConstants.LOG_FOOTER);
            }
            return;
        }

        if (messageBeautifulMode == MESSAGE_BEAUTIFUL_MODE_CLOSE) {
            logShow(AWDConstants.LOG_VERBOSE, mahoroTegText + getToday(), apiInfo + AWDConstants.LOG_POST_NORMAL + apiJson);
            return;
        }
    }
}
