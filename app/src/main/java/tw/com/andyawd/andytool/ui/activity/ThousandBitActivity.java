package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


import io.reactivex.functions.Consumer;
import kotlin.Unit;
import tw.com.andyawd.andyawdlibrary.AWDConstants;
import tw.com.andyawd.andyawdlibrary.AWDThousandBitStyleMgr;
import tw.com.andyawd.andytool.R;

public class ThousandBitActivity extends AppCompatActivity {

    private AppCompatTextView tvAtb_Message;
    private AppCompatButton btAtb_Pattern01;
    private AppCompatButton btAtb_Pattern02;
    private AppCompatButton btAtb_Pattern03;
    private AppCompatButton btAtb_Pattern04;
    private AppCompatButton btAtb_Pattern05;

    private String thousandBitNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thousand_bit);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    private void initComponent() {
        tvAtb_Message = findViewById(R.id.tvAtb_Message);
        btAtb_Pattern01 = findViewById(R.id.btAtb_Pattern01);
        btAtb_Pattern02 = findViewById(R.id.btAtb_Pattern02);
        btAtb_Pattern03 = findViewById(R.id.btAtb_Pattern03);
        btAtb_Pattern04 = findViewById(R.id.btAtb_Pattern04);
        btAtb_Pattern05 = findViewById(R.id.btAtb_Pattern05);

        RxView.clicks(btAtb_Pattern01)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAtb_Pattern01_Click);
        RxView.clicks(btAtb_Pattern02)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAtb_Pattern02_Click);
        RxView.clicks(btAtb_Pattern03)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAtb_Pattern03_Click);
        RxView.clicks(btAtb_Pattern04)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAtb_Pattern04_Click);
        RxView.clicks(btAtb_Pattern05)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAtb_Pattern05_Click);

        thousandBitNumber = AWDThousandBitStyleMgr.getInstance().getThousandBitStyle("987654321.12345");
    }

    private Consumer<? super Unit> btAtb_Pattern01_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDThousandBitStyleMgr.getInstance().setThousandBitStyle(AWDConstants.THOUSAND_FORMAT_01);
            tvAtb_Message.setText(AWDThousandBitStyleMgr.getInstance().getThousandBitStyle("987654321.12345"));
        }
    };

    private Consumer<? super Unit> btAtb_Pattern02_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDThousandBitStyleMgr.getInstance().setThousandBitStyle(AWDConstants.THOUSAND_FORMAT_02);
            tvAtb_Message.setText(AWDThousandBitStyleMgr.getInstance().getThousandBitStyle("987654321.12345"));
        }
    };

    private Consumer<? super Unit> btAtb_Pattern03_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDThousandBitStyleMgr.getInstance().setThousandBitStyle(AWDConstants.THOUSAND_FORMAT_03);
            tvAtb_Message.setText(AWDThousandBitStyleMgr.getInstance().getThousandBitStyle("987654321.12345"));
        }
    };

    private Consumer<? super Unit> btAtb_Pattern04_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDThousandBitStyleMgr.getInstance().setThousandBitStyle(AWDConstants.THOUSAND_FORMAT_04);
            tvAtb_Message.setText(String.valueOf(AWDThousandBitStyleMgr.getInstance().getThousandBitStyle(654.321)));
        }
    };

    private Consumer<? super Unit> btAtb_Pattern05_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDThousandBitStyleMgr.getInstance().setThousandBitStyle(AWDConstants.THOUSAND_FORMAT_05);
            tvAtb_Message.setText(String.valueOf(AWDThousandBitStyleMgr.getInstance().getThousandBitStyle(987)));
        }
    };
}
