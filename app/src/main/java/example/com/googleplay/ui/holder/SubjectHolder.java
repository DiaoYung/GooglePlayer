package example.com.googleplay.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import example.com.googleplay.R;
import example.com.googleplay.domin.SubjectInfo;
import example.com.googleplay.http.HttpHelper;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-15.
 */
public class SubjectHolder extends BaseHolder<SubjectInfo> {

    private ImageView subjectImage;
    private TextView subjectTitle;
    private BitmapUtils mBitmapUtils;

    @Override
    public View initView() {

        View view = UIUtils.inflate(R.layout.list_item_subject);
        subjectImage = (ImageView)view.findViewById(R.id.iv_subject);
        subjectTitle = (TextView) view.findViewById(R.id.tv_title);

        mBitmapUtils = new BitmapUtils(UIUtils.getContext());

        return view;
    }

    @Override
    public void refreshView(SubjectInfo data) {

        subjectTitle.setText(data.des);
        mBitmapUtils.display(subjectImage, HttpHelper.URL + "image?name=" + data.url);

    }
}
