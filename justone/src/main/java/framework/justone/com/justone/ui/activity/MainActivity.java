package framework.justone.com.justone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import java.util.Map;

import framework.justone.com.justone.R;
import framework.justone.com.justone.ui.base.BaseActivity;
import framework.justone.com.justone.ui.fragment.FristFragment;
import framework.justone.com.justone.util.SystemAndroidUtils;


public class MainActivity extends BaseActivity  {

    public static ActionBar actionBar;

    TextView textScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();

        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getContainerId() {
        return R.id.activity_container_main;
    }

    @Override
    protected Fragment createFragment() {
        return new FristFragment();
    }

    private void initView(){
//        textScreen = (TextView) findViewById(R.id.text_screen);
//        Map<String,String> map = SystemAndroidUtils.getScreenPower(this);
//        String width = map.get("width");
//        String height = map.get("height");
//        textScreen.setText("宽"+width+"高"+height);
    }


    public void setActionbar(String title, boolean isBack) {
        super.setActionbar(actionBar, title, isBack);
    }
}
