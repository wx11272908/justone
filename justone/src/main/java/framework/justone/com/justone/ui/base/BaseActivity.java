package framework.justone.com.justone.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import framework.justone.com.justone.R;
import framework.justone.com.justone.app.TrapApp;

/**
 * Created by trap on 17/8/29.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        TrapApp.addActivity(this);
        initView();
        initListener();
        initData();
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    abstract protected int getLayoutId();


    /**
     * 初始化组件
     *
     * @author trap
     * @time 2017年08月31日15:42:54
     */
    abstract protected void initView();

    /**
     * 初始化参数数据
     *
     * @author trap
     * @time 2017年08月31日15:44:05
     */
    abstract protected void initData();

    /**
     * 初始化绑定事件
     *
     * @author trap
     * @time 2017年08月31日15:44:30
     */
    abstract protected void initListener();

    @Override
    public void onBackPressed() {
        // 按返回键时，如果有fragment，弹出上一个fragment，否则关闭activity
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() == 1) {
            finish();
        } else {
            fm.popBackStack();
        }
    }

    protected void setActionbar(ActionBar actionBar, String title,
                                boolean isBack) {
        if (null == actionBar)
            return;
        // 自定义标题
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_custom);

        if (!StringUtils.isEmpty(title)) {
            // 设置title
            View view = actionBar.getCustomView();
            TextView titleTv = (TextView) view
                    .findViewById(R.id.tv_actionbar_title);
            titleTv.setText(title);
        }

        // 是否有返回键
        if (isBack) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TrapApp.removeActivity(this);
    }

}
