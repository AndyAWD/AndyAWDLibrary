package tw.com.andyawd.andytool;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import tw.com.andyawd.andyawdlibrary.AWDSnackbarMgr;


public class MainActivity extends AppCompatActivity {

    private AppCompatTextView tbAm_ShowMessage;
    private AppCompatButton btAm_Toast;
    private AppCompatButton btAm_SnackBarMode0;
    private AppCompatButton btAm_SnackBarMode1;
    private AppCompatButton btAm_SnackBarMode2;
    private AppCompatButton btAm_SnackBarMode3;

    private AWDSnackbarMgr awdSnackbarMgr;



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
        btAm_SnackBarMode3 = (AppCompatButton)findViewById(R.id.btAm_SnackBarMode3);
        btAm_SnackBarMode3.setOnClickListener(btAm_SnackBarMode3_Click);

        awdSnackbarMgr = new AWDSnackbarMgr.initi()
                .setView(getWindow().getDecorView())
                .setDuration(Snackbar.LENGTH_SHORT)
                .setContext(MainActivity.this)
                .setBacakgroundColor(R.color.metro_f4b300)
                .setTextColor(R.color.metro_ae113d)
                .setLayout(R.layout.view_show_select)
                .setMessage("000000")
                .build();


    }

    private View.OnClickListener btAm_Toast_CLick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("maho0912", "btAm_StartClick_CLick");
            //AWDToolMgr.getInstance().Toast(MainActivity.this, "btAm_Toast_CLick", Toast.LENGTH_SHORT);
        }
    };

    private View.OnClickListener btAm_SnackBarMode0_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            awdSnackbarMgr.show(AWDSnackbarMgr.Basic);
            //awdSnackbarMgr.dismiss();
        }
    };

    private View.OnClickListener btAm_SnackBarMode1_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            awdSnackbarMgr.show(AWDSnackbarMgr.TextColor);

        }
    };

    private View.OnClickListener btAm_SnackBarMode2_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            awdSnackbarMgr.show(AWDSnackbarMgr.BackgroundColor);
        }
    };

    private View.OnClickListener btAm_SnackBarMode3_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            awdSnackbarMgr.show(AWDSnackbarMgr.TextBackgroundColor);
        }
    };
}
