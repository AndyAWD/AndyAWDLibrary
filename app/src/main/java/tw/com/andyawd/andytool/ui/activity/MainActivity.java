package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import tw.com.andyawd.andyawdlibrary.AWDConstants;
import tw.com.andyawd.andyawdlibrary.AWDConstraintRadioGroup;
import tw.com.andyawd.andyawdlibrary.AWDEditText;
import tw.com.andyawd.andyawdlibrary.interfaceListener.OnKeycodeBackListener;
import tw.com.andyawd.andytool.R;


public class MainActivity extends AppCompatActivity {

    private AppCompatButton btAm_ShowToast;
    private AppCompatButton btAm_ShowDateFormat;
    private AWDEditText aetAm_EditText;
    private AWDConstraintRadioGroup awdConstraintRadioGroup;
    private AppCompatButton btAm_ShowRadioGroup;

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
        btAm_ShowRadioGroup = findViewById(R.id.btAm_ShowRadioGroup);

        RxView.clicks(btAm_ShowToast)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowToastPage_Click);
        RxView.clicks(btAm_ShowDateFormat)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowDateFormatPage_Click);
        RxView.clicks(btAm_ShowRadioGroup)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowRadioGroup_Click);

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

    private OnKeycodeBackListener aetAm_EditText_KeycodeBack = new OnKeycodeBackListener() {
        @Override
        public void KeycodeBack() {
            aetAm_EditText.setText(getResources().getString(R.string.ClickKeycode_text));
        }
    };

    private Observer<? super Unit> btAm_ShowRadioGroup_Click = new Observer<Unit>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Unit unit) {
            Intent intent = new Intent(MainActivity.this, RadioGroupActivity.class);
            startActivity(intent);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };
}
