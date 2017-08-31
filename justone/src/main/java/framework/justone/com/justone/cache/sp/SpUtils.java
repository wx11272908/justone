package framework.justone.com.justone.cache.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import framework.justone.com.justone.interfaces.IManger;

/**
 * Created by trap on 17/8/31.
 * Explain：
 */

public class SpUtils implements IManger {

    private SharedPreferences sharedPreferences;




    private static SpUtils intance;

    synchronized private static SpUtils getIntance() {
        if (null == intance)
            intance = new SpUtils();
        return intance;
    }

    @Override
    public void init(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static boolean setStringValue(String key,String value) {
        return intance.sharedPreferences.edit().putString(key,value).commit();
    }

    public static String getString(String key){
        return intance.sharedPreferences.getString(key,"");
    }


    public static boolean setIntValue(String key,int value){
        return intance.sharedPreferences.edit().putInt(key,value).commit();
    }

    public static boolean setValue(String key,Class<?> value){
        value.getClass()
    }



    @Override
    public void destory() {

    }


}
