package tw.com.andyawd.andyawdlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by andydai on 2018/5/15.
 */

public class AWDItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable divider;
    private Paint paint;
    private int dividerHeight = 1;

    public AWDItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        divider = a.getDrawable(0);
        a.recycle();
    }

    /**
     * 自定義分割線
     *
     * @param context
     * @param drawableId 分割線圖片
     */
    public AWDItemDecoration(Context context, int drawableId) {
        divider = ContextCompat.getDrawable(context, drawableId);
        dividerHeight = divider.getIntrinsicHeight();
    }

    /**
     * 自定義分割線
     *
     * @param context
     * @param dividerHeight 分割線高度
     * @param dividerColor  分割線顏色
     */
    public AWDItemDecoration(Context context, int dividerHeight, int dividerColor) {
        this.dividerHeight = dividerHeight;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //paint.setColor(context.getResources().getColor(dividerColor));
        paint.setColor(ContextCompat.getColor(context, dividerColor));
        paint.setStyle(Paint.Style.FILL);

        /*
        //holder.tbxPublishType.setTextColor(getResources().getColor(R.color.PeggyRED));    //過時寫法
                holder.tbxPublishTypeChange.setTextColor(ContextCompat.getColor(TCLivePublisherActivity.this, R.color.PeggyRED));
                holder.tbxPublishTypeChange.setBackgroundResource(R.drawable.hl_frame_red_1);
         */
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
        drawTopLine(c, parent);
    }

    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1; //列數
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    //繪製水平線
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin;
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + dividerHeight;
            if (divider != null) {
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
            if (paint != null) {
                c.drawRect(left, top, right, bottom, paint);
            }
        }
    }

    //繪製垂直線
    public void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin - dividerHeight;
            final int right = left + dividerHeight;
            if (divider != null) {
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
            if (paint != null) {
                c.drawRect(left, top, right, bottom, paint);
            }
        }
    }

    public void drawTopLine(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop();
            //Log.e("畫線", String.valueOf(top));
            final int bottom = top + dividerHeight;
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin + dividerHeight;
            if (divider != null) {
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
            if (paint != null) {
                c.drawRect(left, top, right, bottom, paint);
            }
        }
    }

    //判斷是否是最後一列
    private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0) //如果是最後一列，則不需要繪製右邊
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0) {   //如果是最後一列，則不需要繪製右邊
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount) { //如果是最後一列，則不需要繪製右邊
                    return true;
                }
            }
        }
        return false;
    }

    //判斷是否是最後一行
    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount) { // 如果是最後一行，則不需要繪製底部
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {   //StaggeredGridLayoutManager 且縱向滾動
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount) { //如果是最後一行，則不需要繪製底部
                    return true;
                }
            } else  //StaggeredGridLayoutManager 且橫向滾動
            {
                if ((pos + 1) % spanCount == 0) {   //如果是最後一行，則不需要繪製底部
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        //int spanCount = getSpanCount(parent);
        //int childCount = parent.getAdapter().getItemCount();

        //if (isLastRaw(parent, itemPosition, spanCount, childCount)) {   //如果是最後一行，則不需要繪製底部
        //    outRect.set(0, 0, dividerHeight, 0);
        //} else if (isLastColum(parent, itemPosition, spanCount, childCount)) {  //如果是最後一列，則不需要繪製右邊
        //    outRect.set(0, 0, 0, dividerHeight);
        //}
        //else {
        //    outRect.set(0, 0, dividerHeight, dividerHeight);
        //}

        outRect.set(0, 0, 0, 0);
    }

}
