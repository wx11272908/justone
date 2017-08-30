package framework.justone.com.justone.app;

import android.app.Activity;
import android.app.Application;
import android.os.Process;

import com.nrs.utils.tools.CrashHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trap on 17/8/1.
 */

public class TrapApp extends Application {

    private static List<Activity> activitys = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
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
