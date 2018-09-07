package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by andydai on 2018/8/22.
 */

public class AWDSquareImageView extends android.support.v7.widget.AppCompatImageView {


    public AWDSquareImageView(Context context) {
        super(context);
    }

    public AWDSquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AWDSquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        // 高度和寬度一樣
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
