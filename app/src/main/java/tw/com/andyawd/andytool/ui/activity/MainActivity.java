package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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
    private AppCompatButton btAm_ShowPermissions;
    private AppCompatButton btAm_ShowLogCat;
    private AppCompatButton btAm_ShowSquareImage;
    private AppCompatButton btAm_ShowThousandBit;
    private AppCompatButton btAm_ShowPopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    @SuppressLint("CheckResult")
    private void initComponent() {
        btAm_ShowToast = findViewById(R.id.btAm_ShowToast);
        btAm_ShowDateFormat = findViewById(R.id.btAm_ShowDateFormat);
        aetAm_EditText = findViewById(R.id.aetAm_EditText);
        btAm_ShowRadioGroup = findViewById(R.id.btAm_ShowRadioGroup);
        btAm_ShowPermissions = findViewById(R.id.btAm_ShowPermissions);
        btAm_ShowLogCat = findViewById(R.id.btAm_ShowLogCat);
        btAm_ShowSquareImage = findViewById(R.id.btAm_ShowSquareImage);
        btAm_ShowThousandBit = findViewById(R.id.btAm_ShowThousandBit);
        btAm_ShowPopupWindow = findViewById(R.id.btAm_ShowPopupWindow);

        RxView.clicks(btAm_ShowToast)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowToastPage_Click);
        RxView.clicks(btAm_ShowDateFormat)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowDateFormatPage_Click);
        RxView.clicks(btAm_ShowRadioGroup)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowRadioGroup_Click);
        RxView.clicks(btAm_ShowPermissions)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowPermissions_Click);
        RxView.clicks(btAm_ShowLogCat)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowLogCat_Click);
        RxView.clicks(btAm_ShowSquareImage)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowSquareImage_Click);
        RxView.clicks(btAm_ShowThousandBit)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowThousandBit_Click);
        RxView.clicks(btAm_ShowPopupWindow)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAm_ShowPopupWindow_Click);

        aetAm_EditText.setOnKeycodeBackListener(aetAm_EditText_KeycodeBack);
    }

    private Consumer<? super Unit> btAm_ShowToastPage_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            Intent intent = new Intent(MainActivity.this, ToastShowActivity.class);
            startActivity(intent);
        }
    };


    private Consumer<? super Unit> btAm_ShowDateFormatPage_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            Intent intent = new Intent(MainActivity.this, DateFormatActivity.class);
            startActivity(intent);
        }
    };

    private OnKeycodeBackListener aetAm_EditText_KeycodeBack = new OnKeycodeBackListener() {
        @Override
        public void KeycodeBack() {
            aetAm_EditText.setText(getResources().getString(R.string.ClickKeycode_text));
        }
    };

    private Consumer<? super Unit> btAm_ShowRadioGroup_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            Intent intent = new Intent(MainActivity.this, RadioGroupActivity.class);
            startActivity(intent);
        }
    };

    private Consumer<? super Unit> btAm_ShowPermissions_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            Intent intent = new Intent(MainActivity.this, PermissionsTransformerActivity.class);
            startActivity(intent);
        }
    };

    private Consumer<? super Unit> btAm_ShowLogCat_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            Intent intent = new Intent(MainActivity.this, LogCatActivity.class);
            startActivity(intent);
        }
    };

    private Consumer<? super Unit> btAm_ShowSquareImage_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            Intent intent = new Intent(MainActivity.this, SquareImageActivity.class);
            startActivity(intent);
        }
    };

    private Consumer<? super Unit> btAm_ShowThousandBit_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            Intent intent = new Intent(MainActivity.this, ThousandBitActivity.class);
            startActivity(intent);
        }
    };

    private Consumer<? super Unit> btAm_ShowPopupWindow_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            Intent intent = new Intent(MainActivity.this, PopupWindowActivity.class);
            startActivity(intent);
        }
    };
}
