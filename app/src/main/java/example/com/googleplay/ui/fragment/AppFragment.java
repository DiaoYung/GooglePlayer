package example.com.googleplay.ui.fragment;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import example.com.googleplay.domin.AppInfo;
import example.com.googleplay.http.protocol.HomeProtocol;
import example.com.googleplay.ui.adapter.MyBaseAdapter;
import example.com.googleplay.ui.holder.AppHolder;
import example.com.googleplay.ui.holder.BaseHolder;
import example.com.googleplay.ui.view.LoadingPager.ResultState;
import example.com.googleplay.utils.UIUtils;

/**
 * HomeFragment
 * Created by root on 16-12-13.
 */

public class AppFragment extends BaseFragment {

    private ArrayList<AppInfo> data;

    @Override
    public View onCreateSuccessView(){

        ListView view = new ListView(UIUtils.getContext());
        view.setSelector(new ColorDrawable());
        view.setDivider(null);
        view.setAdapter(new AppAdapter(data));
        return view;
    }

    @Override
    public ResultState onLoad() {

        HomeProtocol protocol = new HomeProtocol();
        data = protocol.getData(0);
        return check(data);
    }

    private class AppAdapter extends MyBaseAdapter {

        private ArrayList<AppInfo> data;

        public AppAdapter(ArrayList data) {
            super(data);
        }

        @Override
        protected ArrayList onLoadMore() {

            HomeProtocol protocol = new HomeProtocol();
            ArrayList<AppInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }

        @Override
        public BaseHolder getHolder(int position) {
            return new AppHolder();
        }
    }
}
