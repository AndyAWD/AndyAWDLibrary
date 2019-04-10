package tw.com.andyawd.andyawdlibrary;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;

/**
 * Created by andydai on 2018/5/16.
 */

public class AWDBluetoothAcceptThreadMgr extends Thread {

    private BluetoothServerSocket mBluetoothServerSocket = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private Handler mHandler;
    private AWDBluetoothMessageThreadMgr awdBluetoothMessageThreadMgr;


    public AWDBluetoothAcceptThreadMgr(BluetoothAdapter bluetoothAdapter, Handler handler) {
        this.mBluetoothAdapter = bluetoothAdapter;
        this.mHandler = handler;
        BluetoothServerSocket bluetoothServerSocket = null;

        try {
            bluetoothServerSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(AWDConstants.BLUETOOTH_NAME, AWDConstants.BLUETOOTH_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mBluetoothServerSocket = bluetoothServerSocket;
    }

    public void run() {
        BluetoothSocket bluetoothSocket = null;

        while (true) {
            try {
                mHandler.sendEmptyMessage(AWDConstants.BLUETOOTH_START_LISTENING);
                bluetoothSocket = mBluetoothServerSocket.accept();
            } catch (IOException e) {
                mHandler.sendMessage(mHandler.obtainMessage(AWDConstants.BLUETOOTH_ERROR, e));
                break;
            }
            if (bluetoothSocket != null) {
                BlockBluetoothSocket(bluetoothSocket);
                try {
                    mBluetoothServerSocket.close();
                    mHandler.sendEmptyMessage(AWDConstants.BLUETOOTH_FINISH_LISTENING);
                } catch (IOException e) {
                }
                break;
            }
        }
    }

    public void BlockBluetoothSocket(BluetoothSocket bluetoothSocket){
        if (awdBluetoothMessageThreadMgr != null){
            awdBluetoothMessageThreadMgr.CancelMessageThread();
        }

        mHandler.sendEmptyMessage(AWDConstants.BLUETOOTH_CONNECT_CLINET);
        awdBluetoothMessageThreadMgr = new AWDBluetoothMessageThreadMgr(bluetoothSocket, mHandler);
        awdBluetoothMessageThreadMgr.start();
    }

    public void SendBluetoothData(byte[] bytes){
        if (awdBluetoothMessageThreadMgr != null){
            awdBluetoothMessageThreadMgr.SendMessage(bytes);
        }
    }

    public void CancelAcceptThread() {
        try {
            mBluetoothServerSocket.close();
            mHandler.sendEmptyMessage(AWDConstants.BLUETOOTH_FINISH_LISTENING);
        } catch (IOException e) {
        }
    }
}
