package example.com.googleplay.ui.fragment;

import android.view.View;
import android.widget.TextView;

import example.com.googleplay.ui.view.LoadingPager.ResultState;
import example.com.googleplay.utils.UIUtils;

/**
 * HomeFragment
 * Created by root on 16-12-13.
 */

public class GameFragment extends BaseFragment {

    @Override
    public View onCreateSuccessView(){
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText("GameFragment");
        return textView;
    }

    @Override
    public ResultState onLoad() {
        return ResultState.STATE_SUCCESS;
    }

}
