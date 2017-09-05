package framework.justone.com.justone.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.nrs.utils.tools.CrashHandler;

import java.util.ArrayList;
import java.util.List;

import framework.justone.com.justone.cache.sp.Sp;
import framework.justone.com.justone.interfaces.IManger;

/**
 * Created by trap on 17/8/1.
 */

public class TrapApp extends Application {

    private static List<Activity> activitys = new ArrayList<>();

    private IManger[] iMangers;

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initContext();
        initIMangers();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    /**
     * 初始化Context
     * @author trap
     * @time  2017年09月01日11:33:11
     */
    private void initContext(){
        context = getApplicationContext();
    }


    /**
     * 初始化应用全局组件
     * @author trap
     * @time
     */
    public void initIMangers(){
        iMangers = new IManger[]{Sp.getIntance()};
        for (IManger iManger:iMangers){
            iManger.init(context);
        }
    }

    /**
     * 添加Activity
     */
    public static void addActivity(Activity activity){
        activitys.add(activity);
    }


    /**
     * 移除Activity
     */
    public static void removeActivity(Activity activity){
        activitys.remove(activity);
    }

    /**
     * 退出App
     */
    public static void exitApp(int staue){
        for (Activity activity:activitys){
            activity.finish();
        }
        Process.killProcess(Process.myPid());
        System.exit(staue);
    }
}
