package example.com.googleplay.http.protocol;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import example.com.googleplay.domin.AppInfo;

/**
 * Created by root on 16-12-14.
 */

public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {
    private ArrayList<String> picture;

    @Override
    protected String getParams() {
        return "";
    }

    @Override
    protected String getKey() {
        return "home";
    }

    public ArrayList<String> getPictureList(){
        return picture;
    }

    @Override
    protected ArrayList<AppInfo> parseData(String result) {
        try{
            JSONObject jo = new JSONObject(result);

            JSONArray ja1 = jo.getJSONArray("picture");
            picture = new ArrayList<>();

            for (int i =0; i < ja1.length(); i++){
                String pic = ja1.getString(i);
                picture.add(pic);
            }

            JSONArray ja = jo.getJSONArray("list");
            ArrayList<AppInfo> appInfoList = new ArrayList<AppInfo>();
            for (int i =0; i < ja.length(); i++){
                JSONObject jo1 = ja.getJSONObject(i);
                AppInfo info = new AppInfo();

                info.id = jo1.getString("id") ;
                info.name = jo1.getString("name") ;
                info.packageName = jo1.getString("packageName") ;
                info.iconUrl = jo1.getString("iconUrl") ;
                info.star = (float) jo1.getDouble("stars");
                info.size = jo1.getLong("size");
                info.downloadUrl = jo1.getString("downloadUrl") ;
                info.des = jo1.getString("des") ;
                appInfoList.add(info);
            }

            return appInfoList;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
