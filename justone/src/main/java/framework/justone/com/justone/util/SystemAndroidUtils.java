package framework.justone.com.justone.util;

import android.app.Activity;
import android.util.DisplayMetrics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhenbin on 16/11/2 14:14.
 * Explain:
 */
public class SystemAndroidUtils {


    /**
      * 获取屏幕分辨率
      * @author lizhenbin
      * @time 16/11/2 下午2:14
      */
    public static Map<String,String> getScreenPower(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        String string = "手机屏幕分辨率为：" + displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
        Map<String ,String> map = new HashMap<>();
        map.put("width",displayMetrics.widthPixels+"");
        map.put("height",displayMetrics.heightPixels+"");
        return map;
    }


}
