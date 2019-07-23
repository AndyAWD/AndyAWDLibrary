package tw.com.andyawd.andytool.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import kotlin.Unit;
import tw.com.andyawd.andyawdlibrary.AWDConstants;
import tw.com.andyawd.andyawdlibrary.AWDLog;
import tw.com.andyawd.andyawdlibrary.AWDPopupWindowMgr;
import tw.com.andyawd.andyawdlibrary.AWDToastMgr;
import tw.com.andyawd.andyawdlibrary.interfaceListener.OnPopupWindowListener;
import tw.com.andyawd.andytool.R;

public class PopupWindowActivity extends AppCompatActivity {

    private AppCompatTextView tvApw_ShowPopupWindow;

    private AWDPopupWindowMgr awdPopupWindowMgr;
    private TextView tvVae_Message;

    private AWDToastMgr awdToastMgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    private void initComponent() {
        tvApw_ShowPopupWindow = findViewById(R.id.tvApw_ShowPopupWindow);

        RxView.clicks(tvApw_ShowPopupWindow)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(tvApw_ShowPopupWindow_Click);


        awdPopupWindowMgr = new AWDPopupWindowMgr.init(PopupWindowActivity.this)
                .setOutsideTouchable(false)
                .setLayout(R.layout.view_api_error)
                .build();

        awdPopupWindowMgr.setOnPopupWindowListener(awdPopupWindowMgr_PopupWindow);
        tvVae_Message = (TextView) awdPopupWindowMgr.findViewById(R.id.tvVae_Message);

        RxView.clicks(tvVae_Message)
                .throttleFirst(AWDConstants.ONE_INT, TimeUnit.SECONDS).subscribe(tvVae_Message_Click);

        awdToastMgr = new AWDToastMgr.init(PopupWindowActivity.this).build();
    }

    private Consumer<? super Unit> tvApw_ShowPopupWindow_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            awdPopupWindowMgr.showAtLocation(R.layout.activity_popup_window);
        }
    };

    private Consumer<? super Unit> tvVae_Message_Click = new Consumer<Unit>() {
        @Override
        public void accept(Unit unit) throws Exception {
            AWDLog.d("tvVae_Message_Click");
            awdToastMgr.show("你點了PopupWindow");
        }
    };

    private OnPopupWindowListener awdPopupWindowMgr_PopupWindow = new OnPopupWindowListener() {
        @Override
        public void OnDismiss() {
            AWDLog.d("OnDismiss");
            awdToastMgr.show("PopupWindow消失");
        }

        @Override
        public void OnBackgroundTouch(View view, MotionEvent motionEvent) {
            AWDLog.d("OnBackgroundTouch");
            awdToastMgr.show("PopupWindow點擊外部");
        }
    };
}
