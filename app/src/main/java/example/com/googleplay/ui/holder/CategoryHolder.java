package example.com.googleplay.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;

import example.com.googleplay.R;
import example.com.googleplay.domin.CategoryInfo;
import example.com.googleplay.http.HttpHelper;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-15.
 */
public class CategoryHolder extends BaseHolder<CategoryInfo> implements View.OnClickListener{

    private ImageView ivIcon1, ivIcon2, ivIcon3;
    private TextView name1, name2, name3;
    private LinearLayout grid1;
    private LinearLayout grid2;
    private LinearLayout grid3;
    private BitmapUtils bitmapUtils;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_category);
        ivIcon1 = (ImageView) view.findViewById(R.id.iv_icon1);
        ivIcon2 = (ImageView) view.findViewById(R.id.iv_icon2);
        ivIcon3 = (ImageView) view.findViewById(R.id.iv_icon3);

        name1 = (TextView) view.findViewById(R.id.tv_name1);
        name2 = (TextView) view.findViewById(R.id.tv_name2);
        name3 = (TextView) view.findViewById(R.id.tv_name3);

        grid1 = (LinearLayout) view.findViewById(R.id.ll_grid1);
        grid2 = (LinearLayout) view.findViewById(R.id.ll_grid2);
        grid3 = (LinearLayout) view.findViewById(R.id.ll_grid3);

        grid1.setOnClickListener(this);
        grid2.setOnClickListener(this);
        grid3.setOnClickListener(this);

        bitmapUtils = new BitmapUtils(UIUtils.getContext());

        return view;
    }

    @Override
    public void refreshView(CategoryInfo data) {

        name1.setText(data.name1);
        bitmapUtils.display(ivIcon1, HttpHelper.URL + "image?name=" + data.url1);

        name2.setText(data.name2);
        bitmapUtils.display(ivIcon2, HttpHelper.URL + "image?name=" + data.url2);


        name3.setText(data.name3);
        bitmapUtils.display(ivIcon3, HttpHelper.URL + "image?name=" + data.url3);


    }

    @Override
    public void onClick(View v) {

        CategoryInfo info = getData();

        switch (v.getId()){
            case R.id.ll_grid1:
                Toast.makeText(UIUtils.getContext(), info.name1, Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_grid2:
                Toast.makeText(UIUtils.getContext(), info.name2, Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_grid3:
                Toast.makeText(UIUtils.getContext(), info.name3, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }


    }
}
