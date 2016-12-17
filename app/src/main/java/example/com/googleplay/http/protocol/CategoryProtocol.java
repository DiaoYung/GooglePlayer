package example.com.googleplay.http.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import example.com.googleplay.domin.CategoryInfo;

/**
 * Created by root on 16-12-15.
 */
public class CategoryProtocol extends BaseProtocol<ArrayList<CategoryInfo>>{
    @Override
    protected ArrayList<CategoryInfo> parseData(String result) {
        try {
            JSONArray ja = new JSONArray(result);
            ArrayList<CategoryInfo> infos = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                CategoryInfo titleInfo = new CategoryInfo();
                titleInfo.title = jo.getString("title");
                titleInfo.isTitle = true;
                infos.add(titleInfo);

                JSONArray ja1 = jo.getJSONArray("infos");
                for (int j = 0; j < ja1.length(); j++){
                    JSONObject jo1 = ja1.getJSONObject(j);
                    CategoryInfo info = new CategoryInfo();
                    info.name1 = jo1.getString("name1");
                    info.name2 = jo1.getString("name2");
                    info.name3 = jo1.getString("name3");
                    info.url1 = jo1.getString("url1");
                    info.url2 = jo1.getString("url2");
                    info.url3 = jo1.getString("url3");
                    info.isTitle = false;

                    infos.add(info);
                }
            }

            return infos;
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
        return "category";
    }
}
