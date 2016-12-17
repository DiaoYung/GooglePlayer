package example.com.googleplay.http.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by root on 16-12-15.
 */
public class RecommendProtocol extends BaseProtocol<ArrayList<String>>{
    @Override
    protected String getKey() {
        return "recommend";
    }

    @Override
    protected ArrayList<String> parseData(String result) {
        try {
            JSONArray ja = new JSONArray(result);
            ArrayList<String> recommendList = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++){
                String content = ja.getString(i);
                recommendList.add(content);
            }
            return recommendList;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected String getParams() {
        return "";
    }
}
