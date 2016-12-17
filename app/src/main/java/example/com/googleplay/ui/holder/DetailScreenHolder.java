package example.com.googleplay.ui.holder;

import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

import example.com.googleplay.R;
import example.com.googleplay.domin.AppInfo;
import example.com.googleplay.http.HttpHelper;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-16.
 */
public class DetailScreenHolder extends BaseHolder<AppInfo>{

    private ImageView[] mScreen;
    private BitmapUtils mBitmapUtils;

    @Override
    public View initView() {

        View view = UIUtils.inflate(R.layout.layout_detail_picinfo);

        mScreen = new ImageView[5];

        mScreen[0] =(ImageView) view.findViewById(R.id.iv_pic1);
        mScreen[1] =(ImageView) view.findViewById(R.id.iv_pic2);
        mScreen[2] =(ImageView) view.findViewById(R.id.iv_pic3);
        mScreen[3] =(ImageView) view.findViewById(R.id.iv_pic4);
        mScreen[4] =(ImageView) view.findViewById(R.id.iv_pic5);

        mBitmapUtils = new BitmapUtils(UIUtils.getContext());

        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        ArrayList<String> screen = data.screen;

        for (int i = 0; i < 5; i++){
            if (i < screen.size()){
                mBitmapUtils.display(mScreen[i], HttpHelper.URL + "image?name=" + screen.get(i));
            }else {
                mScreen[i].setVisibility(View.GONE);
            }
        }
    }
}
