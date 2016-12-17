package example.com.googleplay.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.View;


import example.com.googleplay.global.GooglePlayApplication;

/**
 * Created by root on 16-12-13.
 */

public class UIUtils {

    public static Context getContext(){
        return GooglePlayApplication.getContext();
    }

    public static Handler getHandler(){
        return GooglePlayApplication.getHandler();
    }

    public static int getMainThreadId(){
        return GooglePlayApplication.getMainThreadId();
    }

    public static String getString(int id){
        return getContext().getResources().getString(id);
    }

    public static String[] getStringArray(int id){
        return getContext().getResources().getStringArray(id);
    }

    public static int getColor(int id){
        return getContext().getResources().getColor(id);
    }

    public static Drawable getDrawable(int id){
        return getContext().getResources().getDrawable(id);
    }

    public static int getDimen(int id){
        return getContext().getResources().getDimensionPixelSize(id);
    }

    public static int dip2px(float dip){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip*density + 0.5f);
    }

    public static float px2dip(int px){
        float denisity = getContext().getResources().getDisplayMetrics().density;
        return px/denisity;
    }

    public static View inflate(int id){
        return View.inflate(getContext(), id, null);
    }

    public static boolean isRunOnUIThread(){
        int myId = Process.myTid();
        if (myId == getMainThreadId()){
            return true;
        }

        return false;
    }

    public static void runOnUIThread(Runnable runnable){
        if (isRunOnUIThread()){
            runnable.run();
        }else {
            getHandler().post(runnable);
        }

    }

    public static ColorStateList getColorStateList(int mTabTextColorResId) {
        return getContext().getResources().getColorStateList(mTabTextColorResId);
    }
}
