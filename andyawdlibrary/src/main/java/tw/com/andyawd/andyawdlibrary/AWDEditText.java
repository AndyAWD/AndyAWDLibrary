package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 *
 * Created by andydai on 2018/6/13.
 * 監聽返回鍵的EditText
 */

public class AWDEditText extends AppCompatEditText {

    private AWDEditTextListener listener;    //命名

    public interface AWDEditTextListener {    //建立interface
        void KeycodeBack();
    }

    public void setAWDEditTextListener(AWDEditTextListener listener) {    //初始化interface
        this.listener = listener;
    }

    public AWDEditText(Context context) {
        super(context);
    }

    public AWDEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AWDEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performLongClick() {
        try {
            return super.performLongClick();
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (null != listener) {
                listener.KeycodeBack();
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
