package example.com.googleplay.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.googleplay.R;
import example.com.googleplay.domin.AppInfo;
import example.com.googleplay.http.protocol.HomeProtocol;
import example.com.googleplay.ui.activity.HomeDetailActivity;
import example.com.googleplay.ui.activity.MainActivity;
import example.com.googleplay.ui.adapter.MyBaseAdapter;
import example.com.googleplay.ui.holder.BaseHolder;
import example.com.googleplay.ui.holder.HomeHeaderHolder;
import example.com.googleplay.ui.holder.HomeHolder;
import example.com.googleplay.ui.view.LoadingPager.ResultState;
import example.com.googleplay.utils.UIUtils;

/**
 * HomeFragment
 * Created by root on 16-12-13.
 */

public class HomeFragment extends BaseFragment {

    private ArrayList<AppInfo> data;
    private HomeProtocol protocol;
    private ArrayList<String> mPictureList;

    @Override
    public View onCreateSuccessView(){
        ListView view = new ListView(UIUtils.getContext());
        view.setSelector(new ColorDrawable());
        view.setDivider(null);

        HomeHeaderHolder header = new HomeHeaderHolder();

        view.addHeaderView(header.getRootView());
        view.setAdapter(new HomeAdapter(data));
        if (mPictureList != null){
            header.setData(mPictureList);

        }

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AppInfo appInfo = data.get(position - 1);// 去掉头布局


                if (appInfo != null) {
                    Intent intent = new Intent(UIUtils.getContext(),
                            HomeDetailActivity.class);
                    intent.putExtra("packageName", appInfo.packageName);
                    startActivity(intent);
                }

//                AppInfo appInfo = data.get(position - 1);
//                if (appInfo != null){
//                    Intent intent = new Intent(UIUtils.getContext(), HomeDetailActivity.class);
//                    intent.putExtra("packageName", appInfo.packageName);
//                    startActivity(intent);
//                }
            }
        });

        return view;
    }

    @Override
    public ResultState onLoad() {

//        data = new ArrayList<String>();
//        for (int i = 0; i < 20; i++){
//
//            data.add("test data:" + i);
//        }

        protocol = new HomeProtocol();
        data= protocol.getData(0);
        mPictureList = protocol.getPictureList();
        return check(data);
    }


    private class HomeAdapter extends MyBaseAdapter<AppInfo> {

        public HomeAdapter(ArrayList<AppInfo> data){
            super(data);
        }


        @Override
        protected ArrayList<AppInfo> onLoadMore() {

//            ArrayList<AppInfo> moreData = new ArrayList<AppInfo>();
//            for (int i = 0; i < 10; i++){
//
//                moreData.add("test data:" + i);
//            }
//
//            SystemClock.sleep(2000);
//

//            HomeProtocol protocol = new HomeProtocol();
            ArrayList<AppInfo> moreData= protocol.getData(getListSize());

            return moreData;
        }

        @Override
        public BaseHolder<AppInfo> getHolder(int position) {
            return new HomeHolder();
        }

        @Override
        public boolean hasMore() {
            return true;
        }


//        @Override
//        public int getCount(){
//            return data.size();
//        }
//
//        @Override
//        public String getItem(int position){
//            return data.get(position);
//        }
//
//        @Override
//        public long getItemId(int position){
//            return position;
//        }

//        @Override
//        public View getView(int position, View convertView,ViewGroup parent){

//            HomeHolder homeHolder;
//            if (convertView == null){
//                convertView = UIUtils.inflate(R.layout.listview_item);
//                homeHolder = new HomeHolder();
//                homeHolder.textView  = (TextView) convertView.findViewById(R.id.tv_item);
//                convertView.setTag(homeHolder);
//            }else{
//                homeHolder = (HomeHolder) convertView.getTag();
//            }
////            homeHolder.textView.setText(data.get(position));
////            return convertView;
//        }
//
////        private class HomeHolder {
////            TextView textView;
////        }
    }
}
