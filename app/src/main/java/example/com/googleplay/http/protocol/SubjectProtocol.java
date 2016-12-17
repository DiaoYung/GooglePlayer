package example.com.googleplay.http.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import example.com.googleplay.domin.SubjectInfo;

/**
 * Created by root on 16-12-15.
 */
public class SubjectProtocol extends BaseProtocol<ArrayList<SubjectInfo>>{
    @Override
    protected ArrayList<SubjectInfo> parseData(String result) {

        try {
            JSONArray ja = new JSONArray(result);
            ArrayList<SubjectInfo> subjectInfos = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);

                SubjectInfo subjectInfo = new SubjectInfo();
                subjectInfo.des = jo.getString("des");
                subjectInfo.url = jo.getString("url");
                subjectInfos.add(subjectInfo);
            }
            return subjectInfos;
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
        return "subject";
    }
}
