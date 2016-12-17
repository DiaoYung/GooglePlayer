package example.com.googleplay.ui.holder;


import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

import example.com.googleplay.R;
import example.com.googleplay.http.HttpHelper;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-16.
 */
public class HomeHeaderHolder extends BaseHolder<ArrayList<String>> {

    private ViewPager mViewPager;
    private ArrayList<String> data;
    private BitmapUtils mBitmapUtils;


    public static final int WHAT = 1;
    private LinearLayout llContainer;
    private int mPositonLast;


    @Override
    public View initView() {

        RelativeLayout rlRoot = new RelativeLayout(UIUtils.getContext());
        AbsListView.LayoutParams absParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,UIUtils.dip2px(150));
        rlRoot.setLayoutParams(absParams);

        mViewPager = new ViewPager(UIUtils.getContext());
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        rlRoot.addView(mViewPager,rlParams);

        llContainer = new LinearLayout(UIUtils.getContext());
        llContainer.setOrientation(LinearLayout.HORIZONTAL);

        RelativeLayout.LayoutParams llParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT );
        int padding = UIUtils.dip2px(10);
        llContainer.setPadding(padding, padding, padding, padding);
        llParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        llParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rlRoot.addView(llContainer, llParams);

        return rlRoot;
    }

    @Override
    public void refreshView(final ArrayList<String> data) {

        this.data = data;

        mViewPager.setAdapter(new HeaderAdapter());

        mViewPager.setCurrentItem(data.size() * 10000);

        for (int i =0; i < data.size(); i++){
            ImageView point = new ImageView(UIUtils.getContext());
            point.setImageResource(R.drawable.indicator_normal);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i == 0){
                point.setImageResource(R.drawable.indicator_selected);
            }else {
                point.setImageResource(R.drawable.indicator_normal);
                params.leftMargin = UIUtils.dip2px(3);
            }
            point.setLayoutParams(params);

            llContainer.addView(point);
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position%data.size();
                ImageView point = (ImageView) llContainer.getChildAt(position);
                point.setImageResource(R.drawable.indicator_selected);
                ImageView lastPoint = (ImageView) llContainer.getChildAt(mPositonLast);
                lastPoint.setImageResource(R.drawable.indicator_normal);
                mPositonLast = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        handler.sendEmptyMessageDelayed(WHAT,1000);

        HeaderTask task = new HeaderTask();
        task.start();
    }

    class HeaderTask implements Runnable{

        public void start(){
            UIUtils.getHandler().removeCallbacksAndMessages(null);
            UIUtils.getHandler().postDelayed(this, 1000);

        }
        @Override
        public void run() {

            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            UIUtils.getHandler().postDelayed(this, 1000);
        }
    }

//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//
//            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
//            handler.sendEmptyMessageDelayed(WHAT, 1000);
//        }
//    };

    private class HeaderAdapter extends PagerAdapter {


        public HeaderAdapter(){
            mBitmapUtils = new BitmapUtils(UIUtils.getContext());
        }


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            position = position%(data.size());

            String url = data.get(position);

            ImageView imageView = new ImageView(UIUtils.getContext());

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);


            mBitmapUtils.display(imageView, HttpHelper.URL + "image?name=" + url);

            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
