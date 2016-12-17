package example.com.googleplay.domin;

import java.util.ArrayList;

/**
 * Created by root on 16-12-14.
 */

public class AppInfo {

    public String id;
    public String name;
    public String packageName;
    public String iconUrl;
    public float star;
    public long size;
    public String downloadUrl;
    public String des;


    //Details More

    public String author;
    public String date;
    public String downloadNum;
    public String version;
    public ArrayList<SafeInfo> safe;
    public ArrayList<String> screen;

    public static class SafeInfo{
        public String safeDes;
        public String safeDesUrl;
        public String safeUrl;

    }

}
