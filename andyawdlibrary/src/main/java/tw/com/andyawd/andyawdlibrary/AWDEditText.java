package tw.com.andyawd.andyawdlibrary;

import android.content.Context;

import androidx.appcompat.widget.AppCompatEditText;

import android.util.AttributeSet;
import android.view.KeyEvent;

import tw.com.andyawd.andyawdlibrary.interfaceListener.AWDOnKeycodeBackListener;

/**
 * Created by andydai on 2018/6/13.
 * Refactor by andydai on 2019/7/16.
 * 監聽返回鍵的EditText
 */

public class AWDEditText extends AppCompatEditText {

    private AWDOnKeycodeBackListener listener;

    public AWDEditText(Context context) {
        super(context);
    }

    public AWDEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AWDEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnKeycodeBackListener(AWDOnKeycodeBackListener listener) {
        this.listener = listener;
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
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (null != listener) {
                listener.KeycodeBack();
            }
        }

        return super.onKeyPreIme(keyCode, event);
    }
}
