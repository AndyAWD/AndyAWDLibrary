package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import kotlin.Unit;
import tw.com.andyawd.andyawdlibrary.AWDConstants;
import tw.com.andyawd.andyawdlibrary.AWDSnackbarMgr;
import tw.com.andyawd.andytool.R;

public class SnackbarShowActivity extends AppCompatActivity {

    private AppCompatButton btAss_Base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_show);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    private void initComponent() {
        btAss_Base = findViewById(R.id.btAss_Base);

        RxView.clicks(btAss_Base)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(btAss_Base_Click);
    }

    private Consumer<? super Unit> btAss_Base_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            new AWDSnackbarMgr.init(SnackbarShowActivity.this).setMessage("456123").build().show();
        }
    };
}
