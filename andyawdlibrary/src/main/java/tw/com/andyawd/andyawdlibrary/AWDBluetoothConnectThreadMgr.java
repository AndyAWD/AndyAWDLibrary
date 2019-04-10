package tw.com.andyawd.andyawdlibrary;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;

/**
 * Created by andydai on 2018/5/16.
 */

public class AWDBluetoothConnectThreadMgr extends Thread {

    private BluetoothSocket mBluetoothSocket = null;
    private BluetoothDevice mBluetoothDevice = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private Handler mHandler;
    private AWDBluetoothMessageThreadMgr awdBluetoothMessageThreadMgr;

    public AWDBluetoothConnectThreadMgr(BluetoothDevice bluetoothDevice, BluetoothAdapter bluetoothAdapter, Handler handler) {
        BluetoothSocket bluetoothSocket = null;
        this.mBluetoothDevice = bluetoothDevice;
        this.mBluetoothAdapter = bluetoothAdapter;
        this.mHandler = handler;
        try {
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(AWDConstants.MY_UUID);
        } catch (IOException e) {
        }
        this.mBluetoothSocket = bluetoothSocket;
    }

    public void run() {
        mBluetoothAdapter.cancelDiscovery();

        try {
            mBluetoothSocket.connect();
        } catch (Exception e) {
            mHandler.sendMessage(mHandler.obtainMessage(AWDConstants.BLUETOOTH_ERROR, e));

            try {
                mBluetoothSocket.close();
            } catch (IOException ie) {
                return;
            }
        }
        BluetoothSocket(mBluetoothSocket);
    }

    private void BluetoothSocket(BluetoothSocket bluetoothSocket) {
        mHandler.sendEmptyMessage(AWDConstants.BLUETOOTH_CONNECT_SERVER);
        awdBluetoothMessageThreadMgr = new AWDBluetoothMessageThreadMgr(bluetoothSocket, mHandler);
        awdBluetoothMessageThreadMgr.start();
    }

    public void CancelConnect() {
        try {
            mBluetoothSocket.close();
        } catch (IOException e) {
        }
    }

    public void SendBluetoothData(byte[] bytes) {
        if (awdBluetoothMessageThreadMgr != null) {
            awdBluetoothMessageThreadMgr.SendMessage(bytes);
        }
    }
}
