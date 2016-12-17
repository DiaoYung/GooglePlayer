package example.com.googleplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.googleplay.ui.view.LoadingPager.ResultState ;
import example.com.googleplay.ui.view.LoadingPager;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-13.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        TextView textView = new TextView(UIUtils.getContext());
//        textView.setText(getClass().getSimpleName());

        loadingPager = new LoadingPager(UIUtils.getContext()){
            @Override
            protected ResultState onLoad() {
                return BaseFragment.this.onLoad();
            }

            @Override
            public View onCreateSuccessView(){
                return BaseFragment.this.onCreateSuccessView();
            }
        };
        return loadingPager;
    }

    public abstract View onCreateSuccessView();

    public abstract ResultState onLoad() ;

    public void loadData(){
        if (loadingPager != null){
            loadingPager.loadData();
        }
    }

    public ResultState check(Object object){
        if (object != null){
            if (object instanceof ArrayList){
                ArrayList list = (ArrayList) object;
                if (list.isEmpty()){
                    return ResultState.STATE_EMPTY;
                }else {
                    return ResultState.STATE_SUCCESS;
                }
            }
        }

        return ResultState.STATE_ERROR;
    }
}
