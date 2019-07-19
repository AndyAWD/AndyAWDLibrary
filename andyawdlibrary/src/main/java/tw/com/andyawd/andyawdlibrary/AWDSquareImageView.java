package tw.com.andyawd.andyawdlibrary;

import android.content.Context;

import androidx.annotation.Nullable;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by andydai on 2018/8/22.
 */

public class AWDSquareImageView extends androidx.appcompat.widget.AppCompatImageView {

    private int lockLayoutSide;

    public AWDSquareImageView(Context context) {
        super(context);
        initLockSide(context, null, 0);
    }

    public AWDSquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLockSide(context, attrs, 0);
    }

    public AWDSquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLockSide(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        int childSize;
        if (0 == lockLayoutSide) {
            childSize = getMeasuredWidth();

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.EXACTLY);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.EXACTLY);

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        } else {
            childSize = getMeasuredHeight();

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.EXACTLY);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(childSize, MeasureSpec.EXACTLY);

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
    }

    private void initLockSide(Context context, AttributeSet attrs, int defStyleAttr) {
        if (null == attrs) {
            return;
        }

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AWDSquareImageView, defStyleAttr, 0);
        lockLayoutSide = typedArray.getInt(R.styleable.AWDSquareImageView_lockLayoutSide, 0);
        typedArray.recycle();
    }
}
