package example.com.googleplay.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;



/**
 * Created by root on 16-12-15.
 */

public class DrawableUtils {

    public static GradientDrawable getGradientDrawable(int radius, int rgb){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(radius);
        drawable.setColor(rgb);

        return drawable;
    }

    public static StateListDrawable getSelector(Drawable normal, Drawable press){
        StateListDrawable selector = new StateListDrawable();
        selector.addState(new int []{android.R.attr.state_pressed}, press);
        selector.addState(new int[]{}, normal);

        return selector;
    }

    public static StateListDrawable getSelector(int radius, int normal, int press){
        GradientDrawable bgNormal = getGradientDrawable(radius, normal);
        GradientDrawable bgPress = getGradientDrawable(radius, press);
        StateListDrawable selector = getSelector(bgNormal, bgPress);
        return selector;
    }

}
