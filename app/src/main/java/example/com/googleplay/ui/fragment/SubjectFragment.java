package example.com.googleplay.ui.fragment;

import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import example.com.googleplay.domin.SubjectInfo;
import example.com.googleplay.http.protocol.SubjectProtocol;
import example.com.googleplay.ui.adapter.MyBaseAdapter;
import example.com.googleplay.ui.holder.BaseHolder;
import example.com.googleplay.ui.holder.SubjectHolder;
import example.com.googleplay.ui.view.LoadingPager;
import example.com.googleplay.utils.UIUtils;

/**
 * HomeFragment
 * Created by root on 16-12-13.
 */

public class SubjectFragment extends BaseFragment {

    private ArrayList<SubjectInfo> data;
    private SubjectProtocol protocol;

    @Override
    public View onCreateSuccessView(){

        ListView view = new ListView(UIUtils.getContext());
        view.setSelector(new ColorDrawable());
        view.setDivider(null);
        view.setAdapter(new SubjectAdapter(data));
        return view;
    }

    @Override
    public LoadingPager.ResultState onLoad() {

        protocol = new SubjectProtocol();
        
        data = protocol.getData(0);
        Log.d("State", "State: " + check(data));
        return check(data);
    }


    private class SubjectAdapter extends MyBaseAdapter {
        public SubjectAdapter(ArrayList data) {
            super(data);
        }

        @Override
        protected ArrayList onLoadMore() {
            ArrayList<SubjectInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }

        @Override
        public BaseHolder getHolder(int position) {
            return new SubjectHolder();
        }
    }
}
