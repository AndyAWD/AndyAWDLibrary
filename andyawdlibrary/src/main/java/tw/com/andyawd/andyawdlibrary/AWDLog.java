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
    private static final int LOG_OFF = 6;
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int WARN = 3;
    private static final int INFO = 4;
    private static final int ERROR = 5;
    private static final int MAHORO_MODE_OPEN = 1046;
    private static final int MAHORO_MODE_CLOSE = 1047;
    private static final int BEAUTIFUL_MODE_OPEN = 1048;
    private static final int BEAUTIFUL_MODE_CLOSE = 1049;

    private static final int level = LOG_OFF;   //關閉全部的Log
//    private static int level = VERBOSE;    //開啟.v等級以上的Log，打開這個的話能看到全部的API發送和接收訊息
//    private static final int level = DEBUG;    //開啟.d等級以上的Log
//    private static final int level = WARN;    //開啟.w等級以上的Log
//    private static final int level = INFO;    //開啟.i等級以上的Log
//    private static final int level = ERROR;    //開啟.e等級以上的Log

    private static int mahoroMode = MAHORO_MODE_CLOSE;   //Tag額外加上maho
//    private static final int mahoroMode = AWDLog.MAHORO_MODE_OPEN;  //Tag不加maho

    private static int beautifulMode = BEAUTIFUL_MODE_OPEN;  //讓Log變得很漂亮，小心不要烤了他
//    private static final int beautifulMode = AWDLog.BEAUTIFUL_MODE_CLOSE;   //讓Log正常顯示

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

    /**
     * 讓Log的Tag自動加上月、日
     */
    private static String mahoroDate() {

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

    private static void log(int level, String tag, String message) {
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
        }
    }

    /**
     * Log級別 VERBOSE
     */
    public static void v(String tag, String msg) {
        if (level >= AWDLog.VERBOSE) {
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(VERBOSE, MahoroName + mahoroDate() + tag, LOG_HEADER);
            log(VERBOSE, MahoroName + mahoroDate() + tag, LOG_THREAD + Thread.currentThread().getName());
            log(VERBOSE, MahoroName + mahoroDate() + tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(VERBOSE, MahoroName + mahoroDate() + tag, LOG_HORIZONTAL);
            log(VERBOSE, MahoroName + mahoroDate() + tag, LOG_MESSAGE_WALL + msg);
            log(VERBOSE, MahoroName + mahoroDate() + tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(VERBOSE, MahoroName + mahoroDate() + tag, msg);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(VERBOSE, tag, LOG_HEADER);
            log(VERBOSE, tag, LOG_THREAD + Thread.currentThread().getName());
            log(VERBOSE, tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(VERBOSE, tag, LOG_HORIZONTAL);
            log(VERBOSE, tag, LOG_MESSAGE_WALL + msg);
            log(VERBOSE, tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(VERBOSE, tag, msg);
            return;
        }
    }

    public static void v(String msg) {
        if (level >= AWDLog.VERBOSE) {
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(VERBOSE, MahoroName + mahoroDate(), LOG_HEADER);
            log(VERBOSE, MahoroName + mahoroDate(), LOG_THREAD + Thread.currentThread().getName());
            log(VERBOSE, MahoroName + mahoroDate(), LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(VERBOSE, MahoroName + mahoroDate(), LOG_HORIZONTAL);
            log(VERBOSE, MahoroName + mahoroDate(), LOG_MESSAGE_WALL + msg);
            log(VERBOSE, MahoroName + mahoroDate(), LOG_FOOTER);
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(VERBOSE, MahoroName + mahoroDate(), msg);
        }
    }


    /**
     * Log級別 DEBUG
     */
    public static void d(String tag, String msg) {
        if (level >= AWDLog.DEBUG) {
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(DEBUG, MahoroName + mahoroDate() + tag, LOG_HEADER);
            log(DEBUG, MahoroName + mahoroDate() + tag, LOG_THREAD + Thread.currentThread().getName());
            log(DEBUG, MahoroName + mahoroDate() + tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(DEBUG, MahoroName + mahoroDate() + tag, LOG_HORIZONTAL);
            log(DEBUG, MahoroName + mahoroDate() + tag, LOG_MESSAGE_WALL + msg);
            log(DEBUG, MahoroName + mahoroDate() + tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            Log.v(MahoroName + mahoroDate() + tag, msg);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(DEBUG, tag, LOG_HEADER);
            log(DEBUG, tag, LOG_THREAD + Thread.currentThread().getName());
            log(DEBUG, tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(DEBUG, tag, LOG_HORIZONTAL);
            log(DEBUG, tag, LOG_MESSAGE_WALL + msg);
            log(DEBUG, tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(DEBUG, tag, msg);
            return;
        }
    }

    public static void d(String msg) {
        if (level >= AWDLog.DEBUG) {
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(DEBUG, MahoroName + mahoroDate(), LOG_HEADER);
            log(DEBUG, MahoroName + mahoroDate(), LOG_THREAD + Thread.currentThread().getName());
            log(DEBUG, MahoroName + mahoroDate(), LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(DEBUG, MahoroName + mahoroDate(), LOG_HORIZONTAL);
            log(DEBUG, MahoroName + mahoroDate(), LOG_MESSAGE_WALL + msg);
            log(DEBUG, MahoroName + mahoroDate(), LOG_FOOTER);
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            Log.d(MahoroName + mahoroDate(), msg);
            return;
        }
    }


    /**
     * Log級別 WARN
     */
    public static void w(String tag, String msg) {
        if (level >= AWDLog.DEBUG) {
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(WARN, MahoroName + mahoroDate() + tag, LOG_HEADER);
            log(WARN, MahoroName + mahoroDate() + tag, LOG_THREAD + Thread.currentThread().getName());
            log(WARN, MahoroName + mahoroDate() + tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(WARN, MahoroName + mahoroDate() + tag, LOG_HORIZONTAL);
            log(WARN, MahoroName + mahoroDate() + tag, LOG_MESSAGE_WALL + msg);
            log(WARN, MahoroName + mahoroDate() + tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(WARN, MahoroName + mahoroDate() + tag, msg);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(WARN, tag, LOG_HEADER);
            log(WARN, tag, LOG_THREAD + Thread.currentThread().getName());
            log(WARN, tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(WARN, tag, LOG_HORIZONTAL);
            log(WARN, tag, LOG_MESSAGE_WALL + msg);
            log(WARN, tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(WARN, tag, msg);
            return;
        }
    }

    public static void w(String msg) {
        if (level >= AWDLog.WARN) {
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(WARN, MahoroName + mahoroDate(), LOG_HEADER);
            log(WARN, MahoroName + mahoroDate(), LOG_THREAD + Thread.currentThread().getName());
            log(WARN, MahoroName + mahoroDate(), LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(WARN, MahoroName + mahoroDate(), LOG_HORIZONTAL);
            log(WARN, MahoroName + mahoroDate(), LOG_MESSAGE_WALL + msg);
            log(WARN, MahoroName + mahoroDate(), LOG_FOOTER);
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(WARN, MahoroName + mahoroDate(), msg);
            return;
        }
    }


    /**
     * Log級別 INFO
     */
    public static void i(String tag, String msg) {
        if (level >= AWDLog.DEBUG) {
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(INFO, MahoroName + mahoroDate() + tag, LOG_HEADER);
            log(INFO, MahoroName + mahoroDate() + tag, LOG_THREAD + Thread.currentThread().getName());
            log(INFO, MahoroName + mahoroDate() + tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(INFO, MahoroName + mahoroDate() + tag, LOG_HORIZONTAL);
            log(INFO, MahoroName + mahoroDate() + tag, LOG_MESSAGE_WALL + msg);
            log(INFO, MahoroName + mahoroDate() + tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(INFO, MahoroName + mahoroDate() + tag, msg);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(INFO, tag, LOG_HEADER);
            log(INFO, tag, LOG_THREAD + Thread.currentThread().getName());
            log(INFO, tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(INFO, tag, LOG_HORIZONTAL);
            log(INFO, tag, LOG_MESSAGE_WALL + msg);
            log(INFO, tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(INFO, tag, msg);
            return;
        }
    }

    public static void i(String msg) {
        if (level >= AWDLog.INFO) {
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(INFO, MahoroName + mahoroDate(), LOG_HEADER);
            log(INFO, MahoroName + mahoroDate(), LOG_THREAD + Thread.currentThread().getName());
            log(INFO, MahoroName + mahoroDate(), LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(INFO, MahoroName + mahoroDate(), LOG_HORIZONTAL);
            log(INFO, MahoroName + mahoroDate(), LOG_MESSAGE_WALL + msg);
            log(INFO, MahoroName + mahoroDate(), LOG_FOOTER);
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(INFO, MahoroName + mahoroDate(), msg);
            return;
        }
    }

    /**
     * Log級別 ERROR
     */
    public static void e(String tag, String msg) {
        if (level >= AWDLog.ERROR) {
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(ERROR, MahoroName + mahoroDate() + tag, LOG_HEADER);
            log(ERROR, MahoroName + mahoroDate() + tag, LOG_THREAD + Thread.currentThread().getName());
            log(ERROR, MahoroName + mahoroDate() + tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(ERROR, MahoroName + mahoroDate() + tag, LOG_HORIZONTAL);
            log(ERROR, MahoroName + mahoroDate() + tag, LOG_MESSAGE_WALL + msg);
            log(ERROR, MahoroName + mahoroDate() + tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_OPEN && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(ERROR, MahoroName + mahoroDate() + tag, msg);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(ERROR, tag, LOG_HEADER);
            log(ERROR, tag, LOG_THREAD + Thread.currentThread().getName());
            log(ERROR, tag, LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(ERROR, tag, LOG_HORIZONTAL);
            log(ERROR, tag, LOG_MESSAGE_WALL + msg);
            log(ERROR, tag, LOG_FOOTER);
            return;
        }

        if (mahoroMode == MAHORO_MODE_CLOSE && beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(ERROR, tag, msg);
            return;
        }
    }

    public static void e(String msg) {
        if (level >= AWDLog.ERROR) {
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_OPEN) {
            log(ERROR, MahoroName + mahoroDate(), LOG_HEADER);
            log(ERROR, MahoroName + mahoroDate(), LOG_THREAD + Thread.currentThread().getName());
            log(ERROR, MahoroName + mahoroDate(), LOG_FUNCTION + getStackTraceElement().getMethodName() + LOG_LEFT_PARENTHESES + getStackTraceElement().getFileName() + LOG_COLON + getStackTraceElement().getLineNumber() + LOG_RIGHT_PARENTHESES);
            log(ERROR, MahoroName + mahoroDate(), LOG_HORIZONTAL);
            log(ERROR, MahoroName + mahoroDate(), LOG_MESSAGE_WALL + msg);
            log(ERROR, MahoroName + mahoroDate(), LOG_FOOTER);
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(ERROR, MahoroName + mahoroDate(), msg);
            return;
        }
    }

    public static void api(String apiInfo, String apiJson) {
        if (level >= AWDLog.VERBOSE) {
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_OPEN && TextUtils.isEmpty(apiJson)) {
            log(VERBOSE, MahoroName + mahoroDate(), LOG_HEADER);
            log(VERBOSE, MahoroName + mahoroDate(), LOG_MESSAGE_WALL + apiInfo);
            log(VERBOSE, MahoroName + mahoroDate(), LOG_FOOTER);
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_OPEN && !TextUtils.isEmpty(apiJson)) {
            try {
                if (apiJson.startsWith(LOG_LEFT_CURLY_BRACKET)) {
                    JSONObject joMsg = new JSONObject(apiJson);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_HEADER);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_MESSAGE_WALL + apiInfo);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_HORIZONTAL);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_POST + apiJson);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_POST_INDENTATION + joMsg.toString(4));
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_FOOTER);
                }
                if (apiJson.startsWith(LOG_LEFT_SQUARE_BRACKET)) {
                    JSONArray jaMsg = new JSONArray(apiJson);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_HEADER);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_MESSAGE_WALL + apiInfo);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_HORIZONTAL);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_POST + apiJson);
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_POST_INDENTATION + jaMsg.toString(4));
                    log(VERBOSE, MahoroName + mahoroDate(), LOG_FOOTER);
                }
            } catch (Exception e) {
                log(VERBOSE, MahoroName + mahoroDate(), LOG_HEADER);
                log(VERBOSE, MahoroName + mahoroDate(), LOG_MESSAGE_WALL + apiInfo);
                log(VERBOSE, MahoroName + mahoroDate(), LOG_HORIZONTAL);
                log(VERBOSE, MahoroName + mahoroDate(), LOG_POST + apiJson);
                log(VERBOSE, MahoroName + mahoroDate(), LOG_FOOTER);
            }
            return;
        }

        if (beautifulMode == BEAUTIFUL_MODE_CLOSE) {
            log(VERBOSE, MahoroName + mahoroDate(), apiInfo + LOG_POST_NORMAL + apiJson);
            return;
        }
    }

    /**
     * 設定Log的Tag名稱，預設maho + 月日
     */
    public static void setMahoroName(String mahoroName) {
        MahoroName = mahoroName;
    }

    /**
     * Tag額外加上maho
     */
    public static void setMahoroMode(int mahoroMode) {
        AWDLog.mahoroMode = mahoroMode;
    }

    /**
     * 讓Log變得很漂亮，小心不要烤了他
     */
    public static void setBeautifulMode(int beautifulMode) {
        AWDLog.beautifulMode = beautifulMode;
    }
}
