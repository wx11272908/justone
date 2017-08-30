package framework.justone.com.justone.util.Picture;

import android.content.Context;

/**
 * Created by lizhenbin on 16/7/25 16:41.
 * Explain:
 */
public class GlideHelper {

    private static Context context;

    private static GlideHelper instance;

    private void  GlideHelper(){
    }


    synchronized public static GlideHelper getInstance(){
        if(instance == null){
            instance = new GlideHelper();
        }
        return instance;
    }

}
