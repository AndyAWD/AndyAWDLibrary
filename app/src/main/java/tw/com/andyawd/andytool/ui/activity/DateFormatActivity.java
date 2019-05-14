package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import java.util.Locale;
import java.util.Objects;

import tw.com.andyawd.andyawdlibrary.AWDConstants;
import tw.com.andyawd.andyawdlibrary.AWDDateFormat;
import tw.com.andyawd.andytool.R;

public class DateFormatActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatTextView tvAdf_Message;
    private AppCompatButton btAdf_Format00;
    private AppCompatButton btAdf_Format01;
    private AppCompatButton btAdf_Format02;
    private AppCompatButton btAdf_Format03;
    private AppCompatButton btAdf_Format04;
    private AppCompatButton btAdf_Format05;
    private AppCompatButton btAdf_Format06;
    private AppCompatButton btAdf_Format07;
    private AppCompatButton btAdf_Format08;
    private AppCompatButton btAdf_Format09;
    private AppCompatButton btAdf_Format10;
    private AppCompatButton btAdf_Format11;
    private AppCompatButton btAdf_Format12;

    private String inputDatePattern = AWDConstants.DATETIME_FORMAT_01;

    private String inputDate = "2018-05-29 16:30:54";
//    private String inputDate = "2018-06-12 16:30:54";
//    private String inputDate = "16:30:54";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_format);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();
    }

    private void initComponent() {
        tvAdf_Message = findViewById(R.id.tvAdf_Message);
        tvAdf_Message.setText(getDateMessageString(inputDate, AWDConstants.EMPTY_STRING));

        btAdf_Format01 = findViewById(R.id.btAdf_Format01);
        btAdf_Format01.setText(AWDConstants.DATETIME_FORMAT_01);

        btAdf_Format02 = findViewById(R.id.btAdf_Format02);
        btAdf_Format02.setText(AWDConstants.DATETIME_FORMAT_02);

        btAdf_Format03 = findViewById(R.id.btAdf_Format03);
        btAdf_Format03.setText(AWDConstants.DATETIME_FORMAT_03);

        btAdf_Format04 = findViewById(R.id.btAdf_Format04);
        btAdf_Format04.setText(AWDConstants.DATETIME_FORMAT_04);

        btAdf_Format05 = findViewById(R.id.btAdf_Format05);
        btAdf_Format05.setText(AWDConstants.DATETIME_FORMAT_05);

        btAdf_Format06 = findViewById(R.id.btAdf_Format06);
        btAdf_Format06.setText(AWDConstants.DATETIME_FORMAT_06);

        btAdf_Format07 = findViewById(R.id.btAdf_Format07);
        btAdf_Format07.setText(AWDConstants.DATETIME_FORMAT_07);

        btAdf_Format08 = findViewById(R.id.btAdf_Format08);
        btAdf_Format08.setText(AWDConstants.DATETIME_FORMAT_08);

        btAdf_Format09 = findViewById(R.id.btAdf_Format09);
        btAdf_Format09.setText(AWDConstants.DATETIME_FORMAT_09);

        btAdf_Format10 = findViewById(R.id.btAdf_Format10);
        btAdf_Format10.setText(AWDConstants.DATETIME_FORMAT_10);

        btAdf_Format11 = findViewById(R.id.btAdf_Format11);
        btAdf_Format11.setText(AWDConstants.DATETIME_FORMAT_11);

        btAdf_Format12 = findViewById(R.id.btAdf_Format12);
        btAdf_Format12.setText(AWDConstants.DATETIME_FORMAT_12);
    }

    private StringBuilder getDateMessageString(String input, String format) {
        StringBuilder dateMessage = new StringBuilder();
        dateMessage.append("輸入日期 : ");
        dateMessage.append("\n");
        dateMessage.append(input);
        dateMessage.append("\n");
        dateMessage.append("\n");
        dateMessage.append("轉換日期 : ");
        dateMessage.append("\n");
        dateMessage.append(format);
        return dateMessage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAdf_Format01:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_01
                        )));
                break;
            case R.id.btAdf_Format02:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_02
                        )));
                break;
            case R.id.btAdf_Format03:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_03
                        )));
                break;
            case R.id.btAdf_Format04:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_04
                        )));
                break;
            case R.id.btAdf_Format05:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_05
                        )));
                break;
            case R.id.btAdf_Format06:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_06
                        )));
                break;
            case R.id.btAdf_Format07:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_07, Locale.ENGLISH
                        )));
                break;
            case R.id.btAdf_Format08:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_08, Locale.ENGLISH
                        )));
                break;
            case R.id.btAdf_Format09:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_09, Locale.ENGLISH
                        )));
                break;
            case R.id.btAdf_Format10:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_10
                        )));
                break;
            case R.id.btAdf_Format11:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_11
                        )));
                break;
            case R.id.btAdf_Format12:
                tvAdf_Message.setText(getDateMessageString(
                        inputDate,
                        AWDDateFormat.getInstance().getDateFormat(inputDate, inputDatePattern, AWDConstants.DATETIME_FORMAT_12
                        )));
                break;
            default:
                break;
        }
    }
}
