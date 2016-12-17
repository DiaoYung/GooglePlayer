package example.com.googleplay.ui.fragment;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import example.com.googleplay.domin.CategoryInfo;
import example.com.googleplay.http.protocol.CategoryProtocol;
import example.com.googleplay.ui.adapter.MyBaseAdapter;
import example.com.googleplay.ui.holder.BaseHolder;
import example.com.googleplay.ui.holder.CategoryHolder;
import example.com.googleplay.ui.holder.TitleHolder;
import example.com.googleplay.ui.view.LoadingPager;
import example.com.googleplay.utils.UIUtils;


/**
 * HomeFragment
 * Created by root on 16-12-13.
 */

public class CategoryFragment extends BaseFragment {

    private ArrayList<CategoryInfo> data;

    @Override
    public View onCreateSuccessView(){

        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new CategoryAdapter(data));
        return listView;
    }

    @Override
    public LoadingPager.ResultState onLoad() {

        CategoryProtocol protocol = new CategoryProtocol();
        data = protocol.getData(0);

        return check(data);
    }


    private class CategoryAdapter extends MyBaseAdapter {
        public CategoryAdapter(ArrayList data) {
            super(data);
        }

        @Override
        protected ArrayList onLoadMore() {
            return null;
        }

        @Override
        public boolean hasMore() {
            return false;
        }

        @Override
        public int getInnerType(int position) {
            if (data.get(position).isTitle){
                return super.getInnerType(position) + 1;
            }else {
                return super.getInnerType(position);
            }

        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;
        }



        @Override
        public BaseHolder getHolder(int position) {
            if (data.get(position).isTitle){
                return new TitleHolder();
            }else{
                return new CategoryHolder();
            }
        }
    }
}
