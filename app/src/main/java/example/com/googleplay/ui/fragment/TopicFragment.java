package example.com.googleplay.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import example.com.googleplay.http.protocol.HotProtocol;
import example.com.googleplay.ui.view.FlowLayout;
import example.com.googleplay.ui.view.LoadingPager;
import example.com.googleplay.utils.DrawableUtils;
import example.com.googleplay.utils.UIUtils;

/**
 * HomeFragment
 * Created by root on 16-12-13.
 */

public class TopicFragment extends BaseFragment {

    private ArrayList<String> data;

    @Override
    public View onCreateSuccessView(){

        ScrollView scrollView = new ScrollView(UIUtils.getContext());
        FlowLayout flow = new FlowLayout(UIUtils.getContext());

        int padding = UIUtils.dip2px(10);
        flow.setPadding(padding, padding, padding, padding);

        flow.setHorizontalSpacing(UIUtils.dip2px(6));
        flow.setVerticalSpacing(UIUtils.dip2px(8));

        for (int i = 0; i < data.size(); i++){
            final String keyword = data.get(i);
            TextView view = new TextView(UIUtils.getContext());

            Random random = new Random();
            int r = 30 + random.nextInt(200);
            int g = 30 + random.nextInt(200);
            int b = 30 + random.nextInt(200);
            int color = 0xffcecece;// 按下后偏白的背景色

//            GradientDrawable bgNormal = DrawableUtils.getGradientDrawable(UIUtils.dip2px(5),Color.rgb(r, g, b));
//            GradientDrawable bgPress = DrawableUtils.getGradientDrawable(UIUtils.dip2px(5), color);
//            StateListDrawable selector = DrawableUtils.getSelector(bgNormal, bgPress);

            StateListDrawable selector = DrawableUtils.getSelector(UIUtils.dip2px(6), Color.rgb(r, g, b), color);

            view.setBackgroundDrawable(selector);
            view.setTextColor(Color.WHITE);
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            view.setPadding(padding, padding, padding, padding);

            view.setText(keyword);
            view.setGravity(Gravity.CENTER);
            flow.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), keyword, Toast.LENGTH_SHORT).show();
                }
            });
        }


        scrollView.addView(flow);
        return scrollView;
    }

    @Override
    public LoadingPager.ResultState onLoad() {

        HotProtocol protocol = new HotProtocol();
        data = protocol.getData(0);

        return check(data);
    }
}
