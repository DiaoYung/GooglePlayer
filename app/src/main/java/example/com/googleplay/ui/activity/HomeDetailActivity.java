package example.com.googleplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import example.com.googleplay.R;
import example.com.googleplay.domin.AppInfo;
import example.com.googleplay.http.protocol.HomeDetailProtocol;
import example.com.googleplay.ui.holder.DetailAppInfoHolder;
import example.com.googleplay.ui.holder.DetailSafeHolder;
import example.com.googleplay.ui.holder.DetailScreenHolder;
import example.com.googleplay.ui.view.LoadingPager;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-16.
 */

public class HomeDetailActivity extends BaseActivity {

    private LoadingPager mLoadingPager;
    private String packageName;
    private AppInfo data;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        packageName = intent.getStringExtra("packageName");


        mLoadingPager = new LoadingPager(UIUtils.getContext()){
            @Override
            protected ResultState onLoad() {
                return HomeDetailActivity.this.onLoad();
            }

            @Override
            public View onCreateSuccessView(){
                return HomeDetailActivity.this.onCreateSuccessView();
            }
        };

        setContentView(mLoadingPager);
        mLoadingPager.loadData();
        initActionBar();
    }

    private void initActionBar() {

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
////        actionBar.setDisplayShowHomeEnabled(true);
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
//
//        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.drawable.ic_drawer_am,R.string.drawer_open,R.string.drawer_close);
//        toggle.syncState();

        ActionBar actionbar = getSupportActionBar();
//        actionbar.setIcon(R.drawable.ic_launcher);// 设置logo
//        actionbar.setDisplayShowHomeEnabled(true);

        actionbar.setHomeButtonEnabled(true);// home处可以点击
        actionbar.setDisplayHomeAsUpEnabled(true);// 显示左上角返回键,当和侧边栏结合时展示三个杠图片

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
//
//        // 初始化抽屉开关
//        toggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open,
//                R.string.drawer_close);
//        toggle.syncState();// 同步状态, 将DrawerLayout和开关关联在一起
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public  View onCreateSuccessView(){

        View view = UIUtils.inflate(R.layout.layout_detail);
        FrameLayout flDetailAppInfo = (FrameLayout) view.findViewById(R.id.fl_detail_appinfo);

        DetailAppInfoHolder detailAppInfoHolder = new DetailAppInfoHolder();
        flDetailAppInfo.addView(detailAppInfoHolder.getRootView());
        detailAppInfoHolder.setData(data);

        FrameLayout flDetailSafe = (FrameLayout) view.findViewById(R.id.fl_detail_safe);
        DetailSafeHolder detailSafeHolder = new DetailSafeHolder();
        flDetailSafe.addView(detailSafeHolder.getRootView());
        detailSafeHolder.setData(data);

        HorizontalScrollView hsvDetailScreen = (HorizontalScrollView) view.findViewById(R.id.hsv_screen);
        DetailScreenHolder detailScreenHolder = new DetailScreenHolder();
        hsvDetailScreen.addView(detailScreenHolder.getRootView());
        detailScreenHolder.setData(data);

        return view;
    }

    public  LoadingPager.ResultState onLoad(){

        HomeDetailProtocol protocol = new HomeDetailProtocol(packageName);
        data = protocol.getData(0);
        Log.d("Data","Data: " + data);
        if (data != null){
            return LoadingPager.ResultState.STATE_SUCCESS;
        }else {
            return LoadingPager.ResultState.STATE_ERROR;
        }
    }
}
