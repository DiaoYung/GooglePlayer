package example.com.googleplay.ui.holder;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import example.com.googleplay.R;
import example.com.googleplay.ui.holder.BaseHolder;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-14.
 */
public class MoreHolder extends BaseHolder<Integer> {

    public static final int LOAD_MORE_MORE = 1;
    public static final int LOAD_MORE_ERROR = 2;
    public static final int LOAD_MORE_NONE = 3;

    private LinearLayout llLoadMore;
    private TextView loadError;

    public MoreHolder(boolean hasMore) {
        setData(hasMore ? LOAD_MORE_MORE : LOAD_MORE_NONE);
    }

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_more);

        llLoadMore = (LinearLayout) view.findViewById(R.id.ll_load_more);
        loadError = (TextView) view.findViewById(R.id.tv_load_error);
        return view;
    }


    @Override
    public void refreshView(Integer data) {

        switch(data){
            case LOAD_MORE_MORE:
                llLoadMore.setVisibility(View.VISIBLE);
                loadError.setVisibility(View.GONE);
                break;
            case LOAD_MORE_ERROR:
                llLoadMore.setVisibility(View.GONE);
                loadError.setVisibility(View.VISIBLE);
                break;
            case LOAD_MORE_NONE:
                llLoadMore.setVisibility(View.GONE);
                loadError.setVisibility(View.GONE);
                break;
            default:
                break;
        }

    }

}
