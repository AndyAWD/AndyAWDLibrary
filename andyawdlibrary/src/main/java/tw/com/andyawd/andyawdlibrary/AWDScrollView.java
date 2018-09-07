package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 *
 * Created by andydai on 2018/9/6.
 */

public class AWDScrollView extends ScrollView {

    private OnScrollListener listener;    //命名

    public interface OnScrollListener {    //建立interface
        void onScrollChanged(AWDScrollView scrollView, int x, int y, int oldX, int oldY);
    }

    public void setOnScrollChangeListener(OnScrollListener listener) {    //初始化interface
        this.listener = listener;
    }

    public AWDScrollView(Context context) {
        super(context);
    }

    public AWDScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AWDScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (null != listener) {
            listener.onScrollChanged(this, x, y, oldX, oldY);
        }
    }
}
