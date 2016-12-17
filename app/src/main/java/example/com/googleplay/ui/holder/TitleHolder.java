package example.com.googleplay.ui.holder;

import android.view.View;
import android.widget.TextView;

import example.com.googleplay.R;
import example.com.googleplay.domin.CategoryInfo;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-15.
 */
public class TitleHolder extends BaseHolder<CategoryInfo> {

    private TextView ivCategoryTitle;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_title);
        ivCategoryTitle = (TextView) view.findViewById(R.id.tv_category_title);
        return view;
    }

    @Override
    public void refreshView(CategoryInfo data) {
        ivCategoryTitle.setText(data.title);
    }
}
