package framework.justone.com.justone.cache.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

import framework.justone.com.justone.interfaces.IManger;

/**
 * Created by trap on 17/8/31.
 * Explain：
 */

public class Sp implements IManger {

    private SharedPreferences sharedPreferences;

    private static Sp intance;

    synchronized public static Sp getIntance() {
        if (null == intance)
            intance = new Sp();
        return intance;
    }

    @Override
    public void init(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 保存SP值
     *
     * @author trap
     * @time 2017年09月01日11:13:12
     */
    public static boolean setValue(String key, Object object) {
        if (object instanceof String)
            return intance.sharedPreferences.edit().putString(key, (String) object).commit();
        else if (object instanceof Boolean)
            return intance.sharedPreferences.edit().putBoolean(key, (Boolean) object).commit();
        else if (object instanceof Integer)
            return intance.sharedPreferences.edit().putInt(key, (Integer) object).commit();
        else if (object instanceof Float)
            return intance.sharedPreferences.edit().putFloat(key, (Float) object).commit();
        else if (object instanceof Long)
            return intance.sharedPreferences.edit().putLong(key, (Long) object).commit();
        else
            return intance.sharedPreferences.edit().putString(key, object.toString()).commit();

    }

    /**
     * 获取SP值
     *
     * @author trap
     * @time 2017年09月01日11:13:30
     */
    public static Object getValue(String key, Object defValue) {
        if (defValue instanceof String)
            return intance.sharedPreferences.getString(key, (String) defValue);
        else if (defValue instanceof Boolean)
            return intance.sharedPreferences.getBoolean(key, (Boolean) defValue);
        else if (defValue instanceof Integer)
            return intance.sharedPreferences.getInt(key, (Integer) defValue);
        else if (defValue instanceof Float)
            return intance.sharedPreferences.getFloat(key, (Float) defValue);
        else if (defValue instanceof Long)
            return intance.sharedPreferences.getLong(key, (Long) defValue);
        else
            return intance.sharedPreferences.getString(key, null);
    }


    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        intance.sharedPreferences.edit().remove(key).commit();
    }

    /**
     * 清除所有的数据
     */
    public static void clear() {
        intance.sharedPreferences.edit().clear().commit();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return intance.sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        return intance.sharedPreferences.getAll();
    }


    @Override
    public void destory() {

    }


}
