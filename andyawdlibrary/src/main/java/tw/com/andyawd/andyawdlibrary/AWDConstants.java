package tw.com.andyawd.andyawdlibrary;

import java.util.UUID;

/**
 * Created by andydai on 2018/9/12.
 */

public class AWDConstants {
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final String IPIFY = "https://api.ipify.org/";

    public static final String MahoroMode = "maho";
    public static final String BluetoothName = "AWDBlueTooth";

    public static final int BluetoothOpen = 401;
    public static final int BluetoothFind = 408;
    public static final int BluetoothStartListening = 402;
    public static final int BluetoothFinishListening = 403;
    public static final int BluetoothConnectClinet = 404;
    public static final int BluetoothConnectServer = 405;
    public static final int BluetoothGetMessage = 406;
    public static final int BluetoothError = 407;

    public static final int PERMISSIONS_CAMERA = 100;
    public static final int PERMISSIONS_IMAGE = 101;
    public static final int PERMISSIONS_LOCATION = 102;
    public static final int PERMISSIONS_PHONE = 103;
    public static final int PERMISSIONS_RECORD = 104;
    public static final int PERMISSIONS_SYSTEM = 105;
    public static final int PERMISSIONS_VIDEO = 106;
    public static final int PERMISSIONS_CALENDAR = 107;
    public static final int PERMISSIONS_CONTACTS = 108;
    public static final int PERMISSIONS_AUDIO = 109;
    public static final int PERMISSIONS_SENSORS = 110;
    public static final int PERMISSIONS_SMS = 111;
    public static final int PERMISSIONS_STORAGE = 112;
    
    public static final int VERBOSE = 1;    //Log模式等級
    public static final int DEBUG = 2;  //Log模式等級
    public static final int WARN = 3;   //Log模式等級
    public static final int INFO = 4;   //Log模式等級
    public static final int ERROR = 5;  //Log模式等級
    public static final int LogOFF = 6; //Log模式等級

    public static final String SYMBOL_AT = "@";
    public static final String SYMBOL_OCTOTHORPE = "#";
    public static final String SYMBOL_POUNDS = "£";
    public static final String SYMBOL_EURO = "€";
    public static final String SYMBOL_DOLLOR_SIGN = "$";
    public static final String SYMBOL_CENT_SIGN = "¢";
    public static final String SYMBOL_JAPANESE_YEN = "¥";
    public static final String SYMBOL_GENERIC_CURRENCY = "¤";
    public static final String SYMBOL_MICRO = "µ";
    public static final String SYMBOL_PERCENT = "%";
    public static final String SYMBOL_DEGREE = "°";
    public static final String SYMBOL_CARET = "^";
    public static final String SYMBOL_AND = "&";
    public static final String SYMBOL_ASTERISK = "*";
    public static final String SYMBOL_OPEN_PARENTHESIS = "(";
    public static final String SYMBOL_CLOSE_PARENTHESIS = ")";
    public static final String SYMBOL_HYPHEN = "-";
    public static final String SYMBOL_UNDERSCORE = "_";
    public static final String SYMBOL_PLUS = "+";
    public static final String SYMBOL_EQUALS = "=";
    public static final String SYMBOL_OPEN_BRACE = "{";
    public static final String SYMBOL_CLOSE_BRACE = "}";
    public static final String SYMBOL_OPEN_BRACKET = "[";
    public static final String SYMBOL_CLOSE_BRACKET = "]";
    public static final String SYMBOL_PIPE = "|";
    public static final String SYMBOL_FORWARD_SLASH = "/";
    public static final String SYMBOL_BACK_SLASH = "\\";
    public static final String SYMBOL_SECTION = "§";
    public static final String SYMBOL_COLON = ":";
    public static final String SYMBOL_SEMICOLON = ";";
    public static final String SYMBOL_QUOTE = "\"";
    public static final String SYMBOL_APOSTROPHE = "'";
    public static final String SYMBOL_COMMA = ",";
    public static final String SYMBOL_PERIOD = ".";
    public static final String SYMBOL_QUESTION = "?";

    public static final String IMAGE_INTENT_TYPE = "image/*";
    public static final String VIDEO_INTENT_TYPE = "video/*";
    public static final String AUDIO_INTENT_TYPE = "audio/*";
    public static final String TEXT_INTENT_TYPE = "text/*";
}
