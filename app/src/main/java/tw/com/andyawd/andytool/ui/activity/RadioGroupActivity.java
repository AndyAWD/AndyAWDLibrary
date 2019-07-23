package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.Objects;

import tw.com.andyawd.andyawdlibrary.AWDConstraintRadioGroup;
import tw.com.andyawd.andyawdlibrary.AWDToastMgr;
import tw.com.andyawd.andytool.R;

public class RadioGroupActivity extends AppCompatActivity {

    private AWDConstraintRadioGroup argArg_RadioGroup;
    private AWDToastMgr awdToastMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    private void initComponent() {
        argArg_RadioGroup = findViewById(R.id.argArg_RadioGroup);
        argArg_RadioGroup.setOnCheckedChangeListener(argArg_RadioGroup_CheckChange);

        awdToastMgr = new AWDToastMgr.init(RadioGroupActivity.this).setTextColor(R.color.mediumOrchid).build();
    }

    private AWDConstraintRadioGroup.OnCheckedChangeListener argArg_RadioGroup_CheckChange = new AWDConstraintRadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(AWDConstraintRadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rbArg_Select00:
                    awdToastMgr.show("你選了00");
                    break;
                case R.id.rbArg_Select01:
                    awdToastMgr.show("你選了01");
                    break;
                case R.id.rbArg_Select02:
                    awdToastMgr.show("你選了02");
                    break;
                case R.id.rbArg_Select03:
                    awdToastMgr.show("你選了03");
                    break;
                case R.id.rbArg_Select04:
                    awdToastMgr.show("你選了04");
                    break;
                case R.id.rbArg_Select05:
                    awdToastMgr.show("你選了05");
                    break;
                case R.id.rbArg_Select06:
                    awdToastMgr.show("你選了06");
                    break;
                case R.id.rbArg_Select07:
                    awdToastMgr.show("你選了07");
                    break;
                case R.id.rbArg_Select08:
                    awdToastMgr.show("你選了08");
                    break;
                case R.id.rbArg_Select09:
                    awdToastMgr.show("你選了09");
                    break;
            }
        }
    };
}
