package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by andyawd on 2018/7/7.
 * 鎖左右滑動的ViewPager
 */

public class AWDNoScrollViewPager extends ViewPager {

    public boolean isScroll = true;

    public AWDNoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public AWDNoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScroll(boolean isCanScroll) {
        this.isScroll = isCanScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isScroll) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!isScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }
}
