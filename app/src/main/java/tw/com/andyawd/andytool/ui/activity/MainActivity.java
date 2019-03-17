package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import tw.com.andyawd.andyawdlibrary.AWDLog;
import tw.com.andyawd.andyawdlibrary.AWDToastMgr;
import tw.com.andyawd.andytool.R;
import tw.com.andyawd.andytool.logic.AWDPAndroidAsyncHttpMgr;


public class MainActivity extends AppCompatActivity {

    private AppCompatTextView tbAm_ShowMessage;
    private AppCompatButton btAm_Start;
    private AppCompatButton btAm_Button2;

    private AWDToastMgr errorToast;
    private TextView tvVae_Message;

    private AWDToastMgr selectToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitiComponent();
    }

    private void InitiComponent() {
        tbAm_ShowMessage = (AppCompatTextView) findViewById(R.id.tbAm_ShowMessage);
        btAm_Start = (AppCompatButton) findViewById(R.id.btAm_Start);
        btAm_Start.setOnClickListener(btAm_Start_Click);

        btAm_Button2 = (AppCompatButton) findViewById(R.id.btAm_Button2);
        btAm_Button2.setOnClickListener(btAm_Button2_Click);

        errorToast = new AWDToastMgr.init(MainActivity.this).setLayout(R.layout.view_api_error).build();
//        tvVae_Message = (TextView) errorToast.findViewById(R.id.tvVae_Message);
//        tvVae_Message.setText("");

        selectToast = new AWDToastMgr.init(MainActivity.this).setLayout(R.layout.view_show_select).build();

//        errorToast = new AWDToastMgr.init(MainActivity.this).setTextSize(10).build();
//        selectToast = new AWDToastMgr.init(MainActivity.this).setTextSize(500).setTextColor(R.color.red).build();

    }

    private View.OnClickListener btAm_Start_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            new AWDToastMgr.init(MainActivity.this)
//                    .setBackgroundColor(R.color.yellow)
//                    .setTextBackgroundColor(R.color.blue)
//                    .setTextColor(R.color.white)
//                    .setTextSize(100)
//                    .setTextGravity(Gravity.BOTTOM)
//                    .setGravity(Gravity.TOP, 300, 300)
//                    .setDuration(AWDToastMgr.LENGTH_LONG)
//                    .setBackgroundPicture(R.drawable.background_bigtest)
//                    .build().show("333");
            errorToast.show("5");
        }
    };

    private View.OnClickListener btAm_Button2_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectToast.show("1");
        }
    };

}
