package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;

import org.json.JSONException;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import tw.com.andyawd.andyawdlibrary.AWDConstants;
import tw.com.andyawd.andyawdlibrary.AWDEditText;
import tw.com.andyawd.andyawdlibrary.AWDLog;
import tw.com.andyawd.andyawdlibrary.AWDToastMgr;
import tw.com.andyawd.andyawdlibrary.interfaceListener.AWDOnKeycodeBackListener;
import tw.com.andyawd.andytool.R;
import tw.com.andyawd.andytool.base.AWDPConstants;
import tw.com.andyawd.andytool.logic.AWDPAndroidAsyncHttpMgr;


public class MainActivity extends AppCompatActivity {

    private AppCompatButton btAm_ShowToast;
    private AppCompatButton btAm_ShowDateFormat;
    private AWDEditText aetAm_EditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    private void initComponent() {
        btAm_ShowToast = findViewById(R.id.btAm_ShowToast);
        btAm_ShowDateFormat = findViewById(R.id.btAm_ShowDateFormat);
        aetAm_EditText = findViewById(R.id.aetAm_EditText);

        RxView.clicks(btAm_ShowToast)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowToastPage_Click);
        RxView.clicks(btAm_ShowDateFormat)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowDateFormatPage_Click);

        aetAm_EditText.setOnKeycodeBackListener(aetAm_EditText_KeycodeBack);
    }

    private Observer<? super Unit> btAm_ShowToastPage_Click = new Observer<Unit>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Unit unit) {
            Intent intent = new Intent(MainActivity.this, ToastShowActivity.class);
            startActivity(intent);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    private Observer<? super Unit> btAm_ShowDateFormatPage_Click = new Observer<Unit>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Unit unit) {
            Intent intent = new Intent(MainActivity.this, DateFormatActivity.class);
            startActivity(intent);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    private AWDOnKeycodeBackListener aetAm_EditText_KeycodeBack = new AWDOnKeycodeBackListener() {
        @Override
        public void KeycodeBack() {
            aetAm_EditText.setText(getResources().getString(R.string.ClickKeycode_text));
        }
    };
}
