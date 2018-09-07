package tw.com.andyawd.andyawdlibrary;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Created by andydai on 2018/8/13.
 */

public class AWDViewPagerAdapter extends PagerAdapter {

    private ArrayList<AWDViewPager> mViewPager = new ArrayList<>();

    @Override
    public int getCount() {
        return mViewPager != null ? mViewPager.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewPager.get(position));
        return mViewPager.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 新增一筆頁面
     */
    public void add(AWDViewPager pageView) {
        mViewPager.add(pageView);
        notifyDataSetChanged();
    }

    /**
     * 刪除全部頁面
     */
    public void removeAll() {
        mViewPager = new ArrayList<>();
        notifyDataSetChanged();
    }

    /**
     * 抓目前頁面數量
     * @return
     */
    public int getViewPagerSize() {
        if (null != mViewPager) {
            return mViewPager.size();
        } else {
            return 0;
        }
    }
}
