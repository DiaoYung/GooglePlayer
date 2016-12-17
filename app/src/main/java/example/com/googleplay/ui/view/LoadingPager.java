package example.com.googleplay.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import example.com.googleplay.R;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-13.
 */

public abstract class LoadingPager extends FrameLayout {

    public static final int STATE_LOAD_UNDO = 1;
    public static final int STATE_LOAD_LAODING = 2;
    public static final int STATE_LOAD_EMPTY = 3;
    public static final int STATE_LOAD_ERROR = 4;
    public static final int STATE_LOAD_SUCCESS = 5;

    private int mCurrentState = STATE_LOAD_UNDO;
    private View mLoadingPager;
    private View mLoadingError;
    private View mLoadingEmpty;
    private View mSuccessPager;

    public LoadingPager(Context context) {
        super(context);
        initView();
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    private void initView() {
        if (mLoadingPager == null){
            mLoadingPager = UIUtils.inflate(R.layout.loading_pager);
            addView(mLoadingPager);
        }

        if (mLoadingError == null){
            mLoadingError = UIUtils.inflate(R.layout.loading_error);

            Button retry = (Button) mLoadingError.findViewById(R.id.btn_retry);
            retry.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadData();
                }
            });
            addView(mLoadingError);
        }

        if (mLoadingEmpty == null){
            mLoadingEmpty = UIUtils.inflate(R.layout.loading_empty);
            addView(mLoadingEmpty);
        }

        showRightPager();

    }

    private void showRightPager() {

//        if (mCurrentState == STATE_LOAD_UNDO || mCurrentState == STATE_LOAD_LAODING){
//            mLoadingPager.setVisibility(View.VISIBLE);
//        }else {
//            mLoadingPager.setVisibility(View.GONE);
//        }
//
//        if (mCurrentState == STATE_LOAD_EMPTY){
//            mLoadingEmpty.setVisibility(View.VISIBLE);
//        }else {
//            mLoadingEmpty.setVisibility(View.GONE);
//        }
//
//        if (mCurrentState == STATE_LOAD_ERROR){
//            mLoadingError.setVisibility(View.VISIBLE);
//        }else {
//            mLoadingError.setVisibility(View.GONE);
//        }

        mLoadingPager.setVisibility((mCurrentState == STATE_LOAD_UNDO || mCurrentState == STATE_LOAD_LAODING) ? View.VISIBLE : View.GONE);
        mLoadingEmpty.setVisibility((mCurrentState == STATE_LOAD_EMPTY) ? View.VISIBLE : View.GONE);
        mLoadingError.setVisibility((mCurrentState == STATE_LOAD_ERROR || mCurrentState == STATE_LOAD_LAODING) ? View.VISIBLE : View.GONE);

        if (mSuccessPager == null && mCurrentState == STATE_LOAD_SUCCESS){
            mSuccessPager = onCreateSuccessView();
            if (mSuccessPager != null){
                addView(mSuccessPager);
            }
        }

        if (mSuccessPager != null){
            mSuccessPager.setVisibility(mCurrentState == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

    public void loadData(){
        if (mCurrentState != STATE_LOAD_LAODING){
            mCurrentState = STATE_LOAD_LAODING;
            new Thread(){
                @Override
                public void run(){
                    final ResultState resultState = onLoad();

                    UIUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            mCurrentState =resultState.getState();
                            showRightPager();

                        }
                    });
                }
            }.start();
        }
    }

    protected abstract ResultState onLoad();

    public abstract View onCreateSuccessView();

    public enum ResultState{
        STATE_SUCCESS(STATE_LOAD_SUCCESS),
        STATE_EMPTY(STATE_LOAD_EMPTY),
        STATE_ERROR(STATE_LOAD_ERROR);

        private int state;
        private ResultState(int state){
            this.state = state;
        }

        public int getState(){
            return state;
        }


    }

}
