package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.Objects;

import tw.com.andyawd.andyawdlibrary.AWDLog;
import tw.com.andyawd.andyawdlibrary.AWDToastMgr;
import tw.com.andyawd.andytool.R;
import tw.com.andyawd.andytool.logic.AWDPAndroidAsyncHttpMgr;


public class MainActivity extends AppCompatActivity {

    private AppCompatButton btAm_ShowToastPage;
    private AppCompatButton btAm_ShowDateFormatPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        InitiComponent();
    }

    private void InitiComponent() {
        btAm_ShowToastPage = (AppCompatButton) findViewById(R.id.btAm_ShowToastPage);
        btAm_ShowToastPage.setOnClickListener(btAm_ShowToastPage_Click);

        btAm_ShowDateFormatPage = (AppCompatButton)findViewById(R.id.btAm_ShowDateFormatPage);
        btAm_ShowDateFormatPage.setOnClickListener(btAm_ShowDateFormatPage_Click);
    }

    private View.OnClickListener btAm_ShowToastPage_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ToastShowActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btAm_ShowDateFormatPage_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DateFormatActivity.class);
            startActivity(intent);
        }
    };
}
