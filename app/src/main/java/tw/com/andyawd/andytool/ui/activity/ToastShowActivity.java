package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

import tw.com.andyawd.andyawdlibrary.AWDToastMgr;
import tw.com.andyawd.andytool.R;

public class ToastShowActivity extends AppCompatActivity {

    private AppCompatButton btAts_ToastBase;
    private AppCompatButton btAts_ToastBaseTextColor;
    private AppCompatButton btAts_ToastBaseTextColorBackground;
    private AppCompatButton btAts_ToastBaseTextColorDrawableBackground;
    private AppCompatButton btAts_ToastBaseTextColorDrawableBackgroundTextSize;
    private AppCompatButton btAts_ToastBaseGravity;
    private AppCompatButton btAts_ToastBaseTextGravity;
    private AppCompatButton btAts_ToastBaseBackgroundTextBackground;
    private AppCompatButton btAts_ToastPicture;
    private AppCompatButton btAts_ToastLayout;

    private AWDToastMgr toastBase;
    private AWDToastMgr toastBaseTextColor;
    private AWDToastMgr toastBaseTextColorBackground;
    private AWDToastMgr toastBaseTextColorDrawableBackground;
    private AWDToastMgr toastBaseTextColorDrawableBackgroundTextSize;
    private AWDToastMgr toastBaseGravity;
    private AWDToastMgr toastBaseTextGravity;
    private AWDToastMgr toastBaseBackgroundTextBackgroundText;
    private AWDToastMgr toastPicture;
    private AWDToastMgr toastLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_show);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    private void initComponent() {
        btAts_ToastBase = (AppCompatButton) findViewById(R.id.btAts_ToastBase);
        btAts_ToastBase.setOnClickListener(btAts_ToastBase_Click);

        btAts_ToastBaseTextColor = (AppCompatButton) findViewById(R.id.btAts_ToastBaseTextColor);
        btAts_ToastBaseTextColor.setOnClickListener(btAts_ToastBaseTextColor_Click);

        btAts_ToastBaseTextColorBackground = (AppCompatButton) findViewById(R.id.btAts_ToastBaseTextColorBackground);
        btAts_ToastBaseTextColorBackground.setOnClickListener(btAts_ToastBaseTextColorBackground_Click);

        btAts_ToastBaseTextColorDrawableBackground = (AppCompatButton) findViewById(R.id.btAts_ToastBaseTextColorDrawableBackground);
        btAts_ToastBaseTextColorDrawableBackground.setOnClickListener(btAts_ToastBaseTextColorDrawableBackground_Click);

        btAts_ToastBaseTextColorDrawableBackgroundTextSize = (AppCompatButton) findViewById(R.id.btAts_ToastBaseTextColorDrawableBackgroundTextSize);
        btAts_ToastBaseTextColorDrawableBackgroundTextSize.setOnClickListener(btAts_ToastBaseTextColorDrawableBackgroundTextSize_Click);

        btAts_ToastBaseGravity = (AppCompatButton) findViewById(R.id.btAts_ToastBaseGravity);
        btAts_ToastBaseGravity.setOnClickListener(btAts_ToastBaseGravity_Click);

        btAts_ToastBaseTextGravity = (AppCompatButton) findViewById(R.id.btAts_ToastBaseTextGravity);
        btAts_ToastBaseTextGravity.setOnClickListener(btAts_ToastBaseTextGravity_Click);

        btAts_ToastBaseBackgroundTextBackground = (AppCompatButton) findViewById(R.id.btAts_ToastBaseBackgroundTextBackground);
        btAts_ToastBaseBackgroundTextBackground.setOnClickListener(btAts_ToastBaseBackgroundTextBackground_Click);

        btAts_ToastPicture = (AppCompatButton) findViewById(R.id.btAts_ToastPicture);
        btAts_ToastPicture.setOnClickListener(btAts_ToastPicture_Click);

        btAts_ToastLayout = (AppCompatButton) findViewById(R.id.btAts_ToastLayout);
        btAts_ToastLayout.setOnClickListener(btAts_ToastLayout_Click);

        toastBase = new AWDToastMgr
                .init(ToastShowActivity.this)
                .build();

        toastBaseTextColor = new AWDToastMgr
                .init(ToastShowActivity.this)
                .setTextColor(R.color.dodgerblue)
                .build();

        toastBaseTextColorBackground = new AWDToastMgr
                .init(ToastShowActivity.this)
                .setBackgroundColor(R.color.metro_91d100)
                .setTextColor(R.color.dodgerblue)
                .build();

        toastBaseTextColorDrawableBackground = new AWDToastMgr
                .init(ToastShowActivity.this)
                .setBackgroundDrawable(R.drawable.rounded_hollow_rounded_ff83bf_ffb5d2_radius20_angle270)
                .setTextColor(R.color.dodgerblue)
                .build();

        toastBaseTextColorDrawableBackgroundTextSize = new AWDToastMgr
                .init(ToastShowActivity.this)
                .setBackgroundDrawable(R.drawable.rounded_fc6392_f9baac_radius18_angle270)
                .setTextColor(R.color.dodgerblue)
                .setTextSize(50)
                .build();

        toastBaseGravity = new AWDToastMgr
                .init(ToastShowActivity.this)
                .setGravity(Gravity.CENTER, 100, 300)
                .build();

        toastBaseTextGravity = new AWDToastMgr
                .init(ToastShowActivity.this)
                .setTextGravity(Gravity.LEFT)
                .build();

        toastBaseBackgroundTextBackgroundText = new AWDToastMgr
                .init(ToastShowActivity.this)
                .setBackgroundColor(R.color.metro_91d100)
                .setTextBackgroundColor(R.color.dodgerblue)
                .build();

        toastPicture = new AWDToastMgr
                .init(ToastShowActivity.this)
                .setBackgroundPicture(R.drawable.alertbox_1x)
                .build();

        toastLayout = new AWDToastMgr
                .init(ToastShowActivity.this)
                .setLayout(R.layout.view_api_error)
                .build();
    }

    private View.OnClickListener btAts_ToastBase_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastBase.show(getResources().getString(R.string.ToastBase_text));
        }
    };

    private View.OnClickListener btAts_ToastBaseTextColor_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastBaseTextColor.show(getResources().getString(R.string.ToastBaseTextColor_text));
        }
    };

    private View.OnClickListener btAts_ToastBaseTextColorBackground_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastBaseTextColorBackground.show(getResources().getString(R.string.ToastBaseTextColorBackground_text));
        }
    };

    private View.OnClickListener btAts_ToastBaseTextColorDrawableBackground_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastBaseTextColorDrawableBackground.show(getResources().getString(R.string.ToastBaseTextColorDrawableBackground_text));
        }
    };

    private View.OnClickListener btAts_ToastBaseTextColorDrawableBackgroundTextSize_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastBaseTextColorDrawableBackgroundTextSize.show(getResources().getString(R.string.ToastBaseTextColorDrawableBackgroundTextSize_text));
        }
    };

    private View.OnClickListener btAts_ToastBaseGravity_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastBaseGravity.show(getResources().getString(R.string.ToastBaseGravity_text));
        }
    };

    private View.OnClickListener btAts_ToastBaseTextGravity_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastBaseTextGravity.show(getResources().getString(R.string.ToastBaseTextGravity_text));
        }
    };

    private View.OnClickListener btAts_ToastBaseBackgroundTextBackground_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastBaseBackgroundTextBackgroundText.show(getResources().getString(R.string.ToastBaseBackgroundTextBackground_text));
        }
    };

    private View.OnClickListener btAts_ToastPicture_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastPicture.show();
        }
    };

    private View.OnClickListener btAts_ToastLayout_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toastLayout.show();
        }
    };
}
