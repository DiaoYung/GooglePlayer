package example.com.googleplay.ui.fragment;


import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 16-12-13.
 */

public class FragmentFactory {

    private static Map<Integer,BaseFragment> map = new HashMap<Integer, BaseFragment>();


    public static BaseFragment createFragment(int pos){

        BaseFragment fragment = map.get(pos);
        if (fragment == null){
            switch (pos){
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new RecommendFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new TopicFragment();
                    break;
                default:
                    break;
            }

            map.put(pos, fragment);
        }

        return fragment;

    }
}
