package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import kotlin.Unit;
import tw.com.andyawd.andyawdlibrary.AWDConstants;
import tw.com.andyawd.andyawdlibrary.AWDLog;
import tw.com.andyawd.andytool.R;

public class LogCatActivity extends AppCompatActivity {


    private AppCompatButton btAlc_LogV;
    private AppCompatButton btAlc_LogD;
    private AppCompatButton btAlc_LogW;
    private AppCompatButton btAlc_LogI;
    private AppCompatButton btAlc_LogE;
    private AppCompatButton btAlc_LogApi;
    private AppCompatTextView tvAlc_LogMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_cat);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    private void initComponent() {
        btAlc_LogV = findViewById(R.id.btAlc_LogV);
        btAlc_LogD = findViewById(R.id.btAlc_LogD);
        btAlc_LogW = findViewById(R.id.btAlc_LogW);
        btAlc_LogI = findViewById(R.id.btAlc_LogI);
        btAlc_LogE = findViewById(R.id.btAlc_LogE);
        btAlc_LogApi = findViewById(R.id.btAlc_LogApi);
        tvAlc_LogMessage = findViewById(R.id.tvAlc_LogMessage);

        RxView.clicks( btAlc_LogV).throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe( btAlc_LogV_Click);
        RxView.clicks( btAlc_LogD).throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe( btAlc_LogD_Click);
        RxView.clicks( btAlc_LogW).throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe( btAlc_LogW_Click);
        RxView.clicks( btAlc_LogI).throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe( btAlc_LogI_Click);
        RxView.clicks( btAlc_LogE).throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe( btAlc_LogE_Click);
        RxView.clicks( btAlc_LogApi).throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe( btAlc_LogApi_Click);

        //AWDLog.setLogLevel(AWDConstants.LOG_VERBOSE);
    }

    private void setLogMessage(String text){
        tvAlc_LogMessage.setText(text);
    }

    private Consumer<? super Unit> btAlc_LogV_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDLog.v(AWDConstants.MAHORO_MODE);
            setLogMessage(AWDConstants.MAHORO_MODE);
        }
    };

    private Consumer<? super Unit> btAlc_LogD_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDLog.d(AWDConstants.MAHORO_MODE);
            setLogMessage(AWDConstants.MAHORO_MODE);
        }
    };

    private Consumer<? super Unit> btAlc_LogW_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDLog.w(AWDConstants.MAHORO_MODE);
            setLogMessage(AWDConstants.MAHORO_MODE);
        }
    };

    private Consumer<? super Unit> btAlc_LogI_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDLog.i(AWDConstants.MAHORO_MODE);
            setLogMessage(AWDConstants.MAHORO_MODE);
        }
    };

    private Consumer<? super Unit> btAlc_LogE_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDLog.e(AWDConstants.MAHORO_MODE);
            setLogMessage(AWDConstants.MAHORO_MODE);
        }
    };

    private Consumer<? super Unit> btAlc_LogApi_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDLog.api("maho","{\"returnValue\":0,\"returnMsg\":\"執行成功\"}");
            setLogMessage("{\"returnValue\":0,\"returnMsg\":\"執行成功\"}");
        }
    };
}
