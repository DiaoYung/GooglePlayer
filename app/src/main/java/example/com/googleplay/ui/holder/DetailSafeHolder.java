package example.com.googleplay.ui.holder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

import example.com.googleplay.R;
import example.com.googleplay.domin.AppInfo;
import example.com.googleplay.http.HttpHelper;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-16.
 */
public class DetailSafeHolder extends BaseHolder<AppInfo>{

    private ImageView[] mSafeIcons;// 安全标识图片
    private ImageView[] mDesIcons;// 安全描述图片
    private TextView[] mSafeDes;// 安全描述文字
    private LinearLayout[] mSafeDesBar;// 安全描述条目(图片+文字)
    private LinearLayout llDesRoot;
    private RelativeLayout rlDesRoot;
    private BitmapUtils mBitmapUtils;
    private ImageView ivArrow;
    private int mHeight;
    private LinearLayout.LayoutParams params;
    private boolean isOpen = false;
    private ValueAnimator animator;

    @Override
    public View initView() {

        View view = UIUtils.inflate(R.layout.layout_detail_safeinfo);

        mSafeIcons = new ImageView[4];

        mSafeIcons[0] = (ImageView) view.findViewById(R.id.iv_safe1);
        mSafeIcons[1] = (ImageView) view.findViewById(R.id.iv_safe2);
        mSafeIcons[2] = (ImageView) view.findViewById(R.id.iv_safe3);
        mSafeIcons[3] = (ImageView) view.findViewById(R.id.iv_safe4);

        mDesIcons = new ImageView[4];
        mDesIcons[0] = (ImageView) view.findViewById(R.id.iv_des1);
        mDesIcons[1] = (ImageView) view.findViewById(R.id.iv_des2);
        mDesIcons[2] = (ImageView) view.findViewById(R.id.iv_des3);
        mDesIcons[3] = (ImageView) view.findViewById(R.id.iv_des4);

        mSafeDes = new TextView[4];
        mSafeDes[0] = (TextView) view.findViewById(R.id.tv_des1);
        mSafeDes[1] = (TextView) view.findViewById(R.id.tv_des2);
        mSafeDes[2] = (TextView) view.findViewById(R.id.tv_des3);
        mSafeDes[3] = (TextView) view.findViewById(R.id.tv_des4);

        rlDesRoot = (RelativeLayout) view.findViewById(R.id.rl_des_root);
        llDesRoot = (LinearLayout) view.findViewById(R.id.ll_des_root);

        mSafeDesBar = new LinearLayout[4];
        mSafeDesBar[0] = (LinearLayout) view.findViewById(R.id.ll_des1);
        mSafeDesBar[1] = (LinearLayout) view.findViewById(R.id.ll_des2);
        mSafeDesBar[2] = (LinearLayout) view.findViewById(R.id.ll_des3);
        mSafeDesBar[3] = (LinearLayout) view.findViewById(R.id.ll_des4);

        rlDesRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        mBitmapUtils = new BitmapUtils(UIUtils.getContext());

        ivArrow = (ImageView) view.findViewById(R.id.iv_arrow);
        return view;
    }

    private void toggle() {
        if (isOpen){
            animator = ValueAnimator.ofInt(mHeight, 0);
            isOpen =false;
        }else {
            isOpen = true;
            animator = ValueAnimator.ofInt(0, mHeight);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer height = (Integer) animator.getAnimatedValue();
                params.height = height;
                llDesRoot.setLayoutParams(params);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if (isOpen){
                    ivArrow.setImageResource(R.drawable.arrow_up);
                }else {
                    ivArrow.setImageResource(R.drawable.arrow_down);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.setDuration(200);
        animator.start();

    }

    @Override
    public void refreshView(AppInfo data) {

        ArrayList<AppInfo.SafeInfo> safe = data.safe;
        for (int i = 0; i < 4; i++){
            if (i < safe.size()){
                AppInfo.SafeInfo safeInfo = safe.get(i);
                mBitmapUtils.display(mSafeIcons[i], HttpHelper.URL + "image?name=" + safeInfo.safeUrl);
                mBitmapUtils.display(mDesIcons[i], HttpHelper.URL + "image?name=" + safeInfo.safeDesUrl);
                mSafeDes[i].setText(safeInfo.safeDes);
            }else {
                mSafeIcons[i].setVisibility(View.GONE);
                mSafeDesBar[i].setVisibility(View.GONE);
            }
        }

        llDesRoot.measure(0,0);
        mHeight = llDesRoot.getMeasuredHeight();

        params = (LinearLayout.LayoutParams)llDesRoot.getLayoutParams();
        params.height = 0;
        llDesRoot.setLayoutParams(params);

    }
}
