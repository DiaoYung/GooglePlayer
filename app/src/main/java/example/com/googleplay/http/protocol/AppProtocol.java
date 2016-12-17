package example.com.googleplay.http.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import example.com.googleplay.domin.AppInfo;

/**
 * Created by root on 16-12-15.
 */

public class AppProtocol extends BaseProtocol<ArrayList<AppInfo>> {

    @Override
    protected ArrayList<AppInfo> parseData(String result) {

        try {
            JSONArray ja = new JSONArray(result);
            AppInfo info = new AppInfo();

            ArrayList<AppInfo> list = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++){
                JSONObject jo1 = ja.getJSONObject(i);
                info.id = jo1.getString("id") ;
                info.name = jo1.getString("name") ;
                info.packageName = jo1.getString("packageName") ;
                info.iconUrl = jo1.getString("iconUrl") ;
                info.star = (float) jo1.getDouble("stars");
                info.size = jo1.getLong("size");
                info.downloadUrl = jo1.getString("downloadUrl") ;
                info.des = jo1.getString("des") ;
                list.add(info);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    protected String getParams() {
        return "";
    }

    @Override
    protected String getKey() {
        return "app";
    }
}
