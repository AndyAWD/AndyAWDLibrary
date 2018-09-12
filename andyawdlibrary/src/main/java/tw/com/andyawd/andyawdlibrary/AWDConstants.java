package tw.com.andyawd.andyawdlibrary;

import java.util.UUID;

/**
 * Created by andydai on 2018/9/12.
 */

public class AWDConstants {
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final String BluetoothName = "AWDBlueTooth";

    public static final int BluetoothOpen = 401;
    public static final int BluetoothFind = 408;
    public static final int BluetoothStartListening = 402;
    public static final int BluetoothFinishListening = 403;
    public static final int BluetoothConnectClinet = 404;
    public static final int BluetoothConnectServer = 405;
    public static final int BluetoothGetMessage = 406;
    public static final int BluetoothError = 407;

    public static final int VERBOSE = 1;    //Log模式等級
    public static final int DEBUG = 2;  //Log模式等級
    public static final int WARN = 3;   //Log模式等級
    public static final int INFO = 4;   //Log模式等級
    public static final int ERROR = 5;  //Log模式等級
    public static final int LogOFF = 6; //Log模式等級
}
