package tw.com.andyawd.andytool.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;

import java.util.Objects;

import tw.com.andyawd.andyawdlibrary.AWDConstants;
import tw.com.andyawd.andyawdlibrary.AWDDateFormat;
import tw.com.andyawd.andytool.R;

public class DateFormatActivity extends AppCompatActivity {

    private AppCompatTextView tvAdf_Message;
    private AppCompatButton btAdf_Format01;

    private String inputDate = "2018-06-12 16:30:54";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_format);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initComponent();

        tvAdf_Message.setText(getDateMessageString(
                inputDate,
                ""
                ));
    }

    private void initComponent() {
        tvAdf_Message = findViewById(R.id.tvAdf_Message);

        btAdf_Format01 = findViewById(R.id.btAdf_Format01);
        btAdf_Format01.setOnClickListener(btAdf_Format01_Click);
        btAdf_Format01.setText(AWDConstants.DATETIME_FORMAT_01);
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

    private View.OnClickListener btAdf_Format01_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvAdf_Message.setText(getDateMessageString(
                    inputDate,
                    AWDDateFormat.getInstance().getDateFormat(inputDate, AWDConstants.DATETIME_FORMAT_01, AWDConstants.DATETIME_FORMAT_01
                    )));
        }
    };
}
