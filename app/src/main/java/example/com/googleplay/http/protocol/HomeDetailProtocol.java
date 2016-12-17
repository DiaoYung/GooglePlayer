package example.com.googleplay.http.protocol;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import example.com.googleplay.domin.AppInfo;

/**
 * Created by root on 16-12-16.
 */
public class HomeDetailProtocol extends BaseProtocol<AppInfo>{

    private final String packageName;

    public HomeDetailProtocol(String packageName){
        this.packageName = packageName;
    }
    @Override
    protected String getParams() {
        return "&packageName=" + packageName;
    }

    @Override
    protected String getKey() {
        return "detail";
    }

    @Override
    protected AppInfo parseData(String result) {

        try {
            JSONObject jo1 = new JSONObject(result);
            AppInfo info = new AppInfo();

            info.id = jo1.getString("id") ;
            info.name = jo1.getString("name") ;
            info.packageName = jo1.getString("packageName") ;
            info.iconUrl = jo1.getString("iconUrl") ;
            info.star = (float) jo1.getDouble("stars");
            info.size = jo1.getLong("size");
            info.downloadUrl = jo1.getString("downloadUrl") ;
            info.des = jo1.getString("des") ;

            info.author = jo1.getString("author") ;
            info.date= jo1.getString("date") ;
            info.downloadNum = jo1.getString("downloadNum") ;
            info.version = jo1.getString("version") ;

            JSONArray ja = jo1.getJSONArray("safe");

            ArrayList<AppInfo.SafeInfo> safe = new ArrayList<>();

            for (int i = 0; i < ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);

                AppInfo.SafeInfo safeInfo = new AppInfo.SafeInfo();
                safeInfo.safeDes = jo.getString("safeDes");
                safeInfo.safeDesUrl = jo.getString("safeDesUrl");
                safeInfo.safeUrl = jo.getString("safeUrl");
                safe.add(safeInfo);
            }
            info.safe = safe;

            JSONArray ja1 = jo1.getJSONArray("screen");
            ArrayList<String> screen = new ArrayList<>();
            for (int i = 0; i < ja1.length(); i++){
                String pic = ja1.getString(i);
                screen.add(pic);
            }
            info.screen = screen;

            return info;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
