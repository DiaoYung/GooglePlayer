package example.com.googleplay.ui.fragment;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import example.com.googleplay.http.protocol.RecommendProtocol;
import example.com.googleplay.ui.view.LoadingPager;
import example.com.googleplay.ui.view.fly.ShakeListener;
import example.com.googleplay.ui.view.fly.StellarMap;
import example.com.googleplay.utils.UIUtils;

/**
 * HomeFragment
 * Created by root on 16-12-13.
 */

public class RecommendFragment extends BaseFragment {

    private ArrayList<String> data;

    @Override
    public View onCreateSuccessView(){

        final StellarMap stellarMap = new StellarMap(UIUtils.getContext());
        stellarMap.setAdapter(new RecommendAdapter());
        stellarMap.setRegularity(6, 9);
        int padding = UIUtils.dip2px(10);
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        stellarMap.setGroup(0, true);

        ShakeListener shake = new ShakeListener(UIUtils.getContext());
        shake.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                stellarMap.zoomIn();
            }
        });

        return stellarMap;
    }

    public LoadingPager.ResultState onLoad() {

        RecommendProtocol protocol = new RecommendProtocol();
        data = protocol.getData(0);
        return check(data);
    }


    private class RecommendAdapter implements StellarMap.Adapter {
        @Override

        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getCount(int group) {
            int count = data.size()/getGroupCount();
            if (group == (getGroupCount() -1)){
                count = count + data.size()%getGroupCount();
            }
            return count;
        }

        @Override
        public View getView(int group, int position, View convertView) {

            position = position + group*getCount(group-1);

            final String keyword = data.get(position);

            TextView textView = new TextView(UIUtils.getContext());
            textView.setText(data.get(position));

            Random random = new Random();
            int textSize = 16 + random.nextInt(10);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

            int r = 30 + random.nextInt(200);
            int g = 30 + random.nextInt(200);
            int b = 30 + random.nextInt(200);

            textView.setTextColor(Color.rgb(r, g, b));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), keyword, Toast.LENGTH_SHORT).show();
                }
            });
            return textView;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (isZoomIn){
                if (group >0){
                    group--;
                }else {
                    group = getGroupCount() -1;
                }
            }else {
                if (group < getGroupCount()-1){
                    group++;
                }else {
                    group = 0;
                }
            }

            return group;
        }
    }
}
