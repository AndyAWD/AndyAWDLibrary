package tw.com.andyawd.andyawdlibrary;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by andydai on 2018/5/16.
 */

public class AWDBluetoothMessageThreadMgr extends Thread {

    private BluetoothSocket mBluetoothSocket  = null;
    private InputStream mInputStream  = null;
    private OutputStream mOutputStream  = null;
    private Handler mHandler;

    public AWDBluetoothMessageThreadMgr(BluetoothSocket bluetoothSocket, Handler handler) {
        this.mBluetoothSocket = bluetoothSocket;
        this.mHandler = handler;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try{
            inputStream = mBluetoothSocket.getInputStream();
            outputStream = mBluetoothSocket.getOutputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.mInputStream = inputStream;
        this.mOutputStream = outputStream;
    }

    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;

        while (true) {
            try {
                bytes = mInputStream.read(buffer);
                if (bytes > 0) {
                    Message message = mHandler.obtainMessage(AWDConstants.BluetoothGetMessage, new String(buffer, 0, bytes, "utf-8"));
                    mHandler.sendMessage(message);
                }

            } catch (IOException e) {
                //mHandler.sendMessage(mHandler.obtainMessage(ADConstants.BluetoothError, e));
                //ADLog.d("ADBluetoothMessageThreadMgr - run : " + e);
            }
        }
    }

    public void SendMessage(byte[] bytes) {
        try {
            mOutputStream.write(bytes);
        } catch (IOException e) {
        }
    }

    public void CancelMessageThread() {
        try {
            mBluetoothSocket.close();
        } catch (IOException e) {
        }
    }
}
