package example.com.googleplay.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;


/**
 * Created by root on 16-12-13.
 */

public class GooglePlayApplication extends Application{

    public static Context context;

    public static Handler handler;

    public static int mainThreadId;
    @Override
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
}
