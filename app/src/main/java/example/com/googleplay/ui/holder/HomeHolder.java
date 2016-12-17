package example.com.googleplay.ui.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import example.com.googleplay.R;
import example.com.googleplay.domin.AppInfo;
import example.com.googleplay.http.HttpHelper;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-13.
 */

public class HomeHolder extends BaseHolder<AppInfo> {

    private TextView tvName,tvDes,tvSize;

    private ImageView appImage;

    private RatingBar rbStar;

    private BitmapUtils mBitmapUtils;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_home);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvDes =  (TextView) view.findViewById(R.id.tv_des);
        tvSize =  (TextView) view.findViewById(R.id.tv_size);
        appImage = (ImageView) view.findViewById(R.id.app_image);
        rbStar = (RatingBar) view.findViewById(R.id.rb_star);

        mBitmapUtils = new BitmapUtils(UIUtils.getContext());

        return view;
    }

    @Override
    public void refreshView(AppInfo data) {

        tvName.setText(data.name);
        tvDes.setText(data.des);
        tvSize.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));
        rbStar.setRating(data.star);

        mBitmapUtils.display(appImage, HttpHelper.URL + "image?name=" + data.iconUrl);
    }
}
