package tw.com.andyawd.andyawdlibrary;

import android.view.View;

import java.io.Serializable;

/**
 * Created by andydai on 2018/9/7.
 */

public class AWDObjectData {

    public static class ViewPagerActivity implements Serializable {
        private View view;
        private AWDAppCompatActivity appCompatActivity;

        public ViewPagerActivity(View view, AWDAppCompatActivity appCompatActivity) {
            this.view = view;
            this.appCompatActivity = appCompatActivity;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }

        public AWDAppCompatActivity getAWDAppCompatActivity() {
            return appCompatActivity;
        }

        public void setAWDAppCompatActivity(AWDAppCompatActivity appCompatActivity) {
            this.appCompatActivity = appCompatActivity;
        }
    }

    public static class BluetoothInfo{
        public String paired;
        public String name;
        public String address;

        public BluetoothInfo(String paired, String name, String address) {
            this.paired = paired;
            this.name = name;
            this.address = address;
        }
    }
}
