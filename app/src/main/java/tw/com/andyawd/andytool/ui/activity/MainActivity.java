package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;

import tw.com.andyawd.andyawdlibrary.AWDLog;
import tw.com.andyawd.andyawdlibrary.AWDToastMgr;
import tw.com.andyawd.andytool.R;
import tw.com.andyawd.andytool.logic.AWDPAndroidAsyncHttpMgr;


public class MainActivity extends AppCompatActivity {

    private AppCompatTextView tbAm_ShowMessage;
    private AppCompatButton btAm_Start;

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
    }

    private View.OnClickListener btAm_Start_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new AWDToastMgr.init(MainActivity.this)
                    .setBackgroundColor(R.color.yellow)
                    .setTextBackgroundColor(R.color.blue)
                    .setTextColor(R.color.white)
                    .setTextSize(100)
                    .setTextGravity(Gravity.BOTTOM)
                    .setGravity(Gravity.TOP, 300, 300)
                    .setDuration(AWDToastMgr.LENGTH_LONG)
                    .setBackgroundPicture(R.drawable.background_bigtest)
                    .build().show("333");
        }
    };
}
