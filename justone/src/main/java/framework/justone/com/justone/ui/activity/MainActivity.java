package framework.justone.com.justone.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.LinearLayout;

import framework.justone.com.justone.R;
import framework.justone.com.justone.ui.base.BaseFragmentActivity;
import framework.justone.com.justone.ui.fragment.FourthFragment;
import framework.justone.com.justone.ui.fragment.FristFragment;
import framework.justone.com.justone.ui.fragment.SecondFragment;
import framework.justone.com.justone.ui.fragment.ThirdFragment;


public class MainActivity extends BaseFragmentActivity {

    public static ActionBar actionBar;
    private LinearLayout linFrist, linSecond, linThird, linFourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
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

    @Override
    protected void initView() {
        linFrist = (LinearLayout) findViewById(R.id.lin_first);
        linSecond = (LinearLayout) findViewById(R.id.lin_second);
        linThird = (LinearLayout) findViewById(R.id.lin_third);
        linFourth = (LinearLayout) findViewById(R.id.lin_fourth);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        linFrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FristFragment());
            }
        });

        linSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SecondFragment());
            }
        });

        linThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ThirdFragment());
            }
        });

        linFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FourthFragment());
            }
        });
    }


    public void setActionbar(String title, boolean isBack) {
        super.setActionbar(actionBar, title, isBack);
    }
}
