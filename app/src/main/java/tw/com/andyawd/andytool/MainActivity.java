package tw.com.andyawd.andytool;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import tw.com.andyawd.andyawdlibrary.AWDSnackbarMgr;
import tw.com.andyawd.andyawdlibrary.AWDToastMgr;
import tw.com.andyawd.andyawdlibrary.AWDToolMgr;


public class MainActivity extends AppCompatActivity {

    private AppCompatTextView tbAm_ShowMessage;
    private AppCompatButton btAm_Toast;
    private AppCompatButton btAm_SnackBarMode0;
    private AppCompatButton btAm_SnackBarMode1;
    private AppCompatButton btAm_SnackBarMode2;
    private AppCompatButton btAm_SnackBarMode3;
    private AppCompatButton btAm_SnackBarMode4;
    private AppCompatButton btAm_SnackBarModeDismass;
    private AWDToastMgr awdToastMgr;

    private AWDSnackbarMgr awdSnackbarMgr;
    private AWDSnackbarMgr awdSnackbarMgr2;

    private TextView tvVae_Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitiComponent();
    }

    private void InitiComponent() {
        tbAm_ShowMessage = (AppCompatTextView) findViewById(R.id.tbAm_ShowMessage);
        btAm_Toast = (AppCompatButton) findViewById(R.id.btAm_Toast);
        btAm_Toast.setOnClickListener(btAm_Toast_CLick);
        btAm_SnackBarMode0 = (AppCompatButton) findViewById(R.id.btAm_SnackBarMode0);
        btAm_SnackBarMode0.setOnClickListener(btAm_SnackBarMode0_Click);
        btAm_SnackBarMode1 = (AppCompatButton) findViewById(R.id.btAm_SnackBarMode1);
        btAm_SnackBarMode1.setOnClickListener(btAm_SnackBarMode1_Click);
        btAm_SnackBarMode2 = (AppCompatButton) findViewById(R.id.btAm_SnackBarMode2);
        btAm_SnackBarMode2.setOnClickListener(btAm_SnackBarMode2_Click);
        btAm_SnackBarMode3 = (AppCompatButton) findViewById(R.id.btAm_SnackBarMode3);
        btAm_SnackBarMode3.setOnClickListener(btAm_SnackBarMode3_Click);
        btAm_SnackBarMode4 = (AppCompatButton) findViewById(R.id.btAm_SnackBarMode4);
        btAm_SnackBarMode4.setOnClickListener(btAm_SnackBarMode4_Click);
        btAm_SnackBarModeDismass = (AppCompatButton) findViewById(R.id.btAm_SnackBarModeDismass);
        btAm_SnackBarModeDismass.setOnClickListener(btAm_SnackBarModeDismass_Click);

        awdSnackbarMgr = new AWDSnackbarMgr.initi()
                .setView(getWindow().getDecorView())
                .setDuration(Snackbar.LENGTH_SHORT)
                .setMessage("這邊顯示文字")
                .build();
        awdSnackbarMgr.setLog(AWDSnackbarMgr.Log_On);

        awdToastMgr = new AWDToastMgr.initi()
                .setContext(MainActivity.this)
                .setDuration(Toast.LENGTH_SHORT)
                .setGravity(Gravity.CENTER, 200, 500)
                .setTextColor(R.color.white)
                .setTextSize(50)
                .setLayout(R.layout.view_api_error)
                .setTextBacakgroundColor(R.color.deepskyblue)
                .build();

        awdToastMgr.setLog(AWDToastMgr.Log_On);
        tvVae_Message = (TextView) awdToastMgr.findViewById(R.id.tvVae_Message);
//

        /*
        .setTextColor(R.color.metro_ae113d)
                .setActionText("6666666")
                .setOnActionClickListener(awdSnackbarMgr2_Action)
         */
    }

    private View.OnClickListener btAm_Toast_CLick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //awdToastMgr.show("吐司測試");
            tvVae_Message.setText("9999999999999");
            awdToastMgr.show("3333");
        }
    };

    private View.OnClickListener btAm_SnackBarMode0_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            awdSnackbarMgr.show();

        }
    };

    private View.OnClickListener btAm_SnackBarMode1_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    private View.OnClickListener btAm_SnackBarMode2_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    private View.OnClickListener btAm_SnackBarMode3_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.gc();
        }
    };

    private View.OnClickListener btAm_SnackBarMode4_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    private View.OnClickListener btAm_SnackBarModeDismass_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            awdSnackbarMgr.dismiss(AWDSnackbarMgr.GC_On);
        }
    };

    private AWDSnackbarMgr.setOnActionClickListener awdSnackbarMgr2_Action = new AWDSnackbarMgr.setOnActionClickListener() {
        @Override
        public void Action() {
            Log.d("AWDSnackbarMgr", "snackbar_Action_002");
        }
    };
}
