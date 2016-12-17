package example.com.googleplay.http.protocol;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by root on 16-12-15.
 */
public class HotProtocol extends BaseProtocol<ArrayList<String>>{
    @Override
    protected ArrayList<String> parseData(String result) {

        try {
            JSONArray ja = new JSONArray(result);
            ArrayList<String> hotData = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++){
                String hotItem = ja.getString(i);
                hotData.add(hotItem);
            }
            return hotData;
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
        return "hot";
    }
}
