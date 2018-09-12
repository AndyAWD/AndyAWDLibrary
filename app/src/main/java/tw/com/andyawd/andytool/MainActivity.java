package tw.com.andyawd.andytool;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import tw.com.andyawd.andyawdlibrary.AWDPopupWindowMgr;
import tw.com.andyawd.andyawdlibrary.AWDToolMgr;


public class MainActivity extends AppCompatActivity {

    private AppCompatTextView tbAm_ShowMessage;
    private AppCompatButton btAm_Toast;
    private AppCompatButton btAm_SnackBarModeBase;
    private AppCompatButton btAm_SnackBarModeTextColor;
    private AppCompatButton btAm_SnackBarModeTextColorBackground;

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
        btAm_SnackBarModeBase = (AppCompatButton) findViewById(R.id.btAm_SnackBarModeBase);
        btAm_SnackBarModeBase.setOnClickListener(btAm_SnackBarModeBase_Click);
        btAm_SnackBarModeTextColor = (AppCompatButton) findViewById(R.id.btAm_SnackBarModeTextColor);
        btAm_SnackBarModeTextColor.setOnClickListener(btAm_SnackBarModeTextColor_Click);
        btAm_SnackBarModeTextColorBackground = (AppCompatButton) findViewById(R.id.btAm_SnackBarModeTextColorBackground);
        btAm_SnackBarModeTextColorBackground.setOnClickListener(btAm_SnackBarModeTextColorBackground_Click);
    }

    private View.OnClickListener btAm_Toast_CLick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("maho0912", "btAm_StartClick_CLick");
            AWDToolMgr.getInstance().Toast(MainActivity.this, "btAm_Toast_CLick", Toast.LENGTH_SHORT);
        }
    };

    private View.OnClickListener btAm_SnackBarModeBase_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AWDToolMgr.getInstance().Snackbar(v, "btAm_SnackBarModeBase_Click", Snackbar.LENGTH_SHORT);
        }
    };

    private View.OnClickListener btAm_SnackBarModeTextColor_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AWDToolMgr.getInstance().snackbarTextColor(v, "btAm_SnackBarModeTextColor_Click", R.color.white, Snackbar.LENGTH_SHORT);
        }
    };

    private View.OnClickListener btAm_SnackBarModeTextColorBackground_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AWDToolMgr.getInstance().snackbarTextColorBackground(v, "btAm_SnackBarModeTextColorBackground_Click", ColorStateList.valueOf(0xFFFF0000), R.color.white, Snackbar.LENGTH_SHORT);
        }
    };
}
