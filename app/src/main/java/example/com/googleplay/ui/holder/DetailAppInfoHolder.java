package example.com.googleplay.ui.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import example.com.googleplay.R;
import example.com.googleplay.domin.AppInfo;
import example.com.googleplay.http.HttpHelper;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-16.
 */
public class DetailAppInfoHolder extends BaseHolder<AppInfo>{

    private BitmapUtils mBitmapUtils;
    private ImageView ivIcon;
    private TextView tvName;
    private TextView tvDownloadNum;
    private TextView tvVersion;
    private TextView tvDate;
    private TextView tvSize;
    private RatingBar rbStar;

    @Override
    public View initView() {

        View view = UIUtils.inflate(R.layout.layout_detail_appinfo);
        ivIcon = (ImageView)view.findViewById(R.id.iv_icon);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvDownloadNum = (TextView) view.findViewById(R.id.tv_download_num);
        tvVersion = (TextView) view.findViewById(R.id.tv_version);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvSize = (TextView) view.findViewById(R.id.tv_size);

        rbStar = (RatingBar) view.findViewById(R.id.rb_star);

        mBitmapUtils = new BitmapUtils(UIUtils.getContext());

        return view;
    }

    @Override
    public void refreshView(AppInfo data) {

//        mBitmapUtils.display(ivIcon, HttpHelper.URL + "image?name=" + data.iconUrl);
        mBitmapUtils.display(ivIcon, HttpHelper.URL + "image?name="
        				+ data.iconUrl);
        tvName.setText(data.name);
        tvDownloadNum.setText(data.downloadNum);
        tvVersion.setText(data.version);
        tvDate.setText(data.date);
        tvSize.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));
        rbStar.setRating(data.star);

    }
}
