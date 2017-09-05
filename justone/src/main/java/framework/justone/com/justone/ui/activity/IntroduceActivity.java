package framework.justone.com.justone.ui.activity;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import framework.justone.com.justone.R;
import framework.justone.com.justone.cache.sp.SPKey;
import framework.justone.com.justone.cache.sp.Sp;
import framework.justone.com.justone.ui.base.BaseActivity;

/**
 * 新功能介绍页面
 *
 * @author trap
 * @time 2017年09月01日11:38:12
 */
public class IntroduceActivity extends BaseActivity {

    private ViewPager viewPager;
    private LinearLayout linIntroduce;
    private Drawable mPagerBackground;

    private final int DOT_NUM = 3;
    private TextView[] dotTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_introduce;
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager_introduce);
        linIntroduce = (LinearLayout) findViewById(R.id.lin_introduce);
        mPagerBackground = viewPager.getBackground();
        initDotView();
        viewPager.setAdapter(new ScreenSlidePagerAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initData() {
//        mPagerBackground.setAlpha(0);
    }

    @Override
    protected void initListener() {
        setPageChangeListener(viewPager);
    }


    private void setPageChangeListener(ViewPager viewpager){
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    /**
     * 初始化小圆点
     *
     * @author trap
     * @time 2017年09月01日16:49:34
     */
    private void initDotView() {
        int curDotNum = DOT_NUM;
        dotTv = new TextView[curDotNum];
        for (int i = 0; i < curDotNum; i++) {
            dotTv[i] = new TextView(this);
            dotTv[i].setWidth((int) getResources().getDimension(R.dimen.dimen_8));
            dotTv[i].setHeight((int) getResources().getDimension(R.dimen.dimen_8));
            dotTv[i].setGravity(Gravity.CENTER);
            dotTv[i].setTextColor(getResources().getColor(R.color.black));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int) getResources().getDimension(R.dimen.dimen_15), 0);
            dotTv[i].setLayoutParams(params);
            dotTv[i].setBackgroundResource(R.drawable.dot_gray_bg);
            linIntroduce.addView(dotTv[i]);
        }

        dotTv[0].setBackgroundResource(R.drawable.dot_blue_bg);
        dotTv[0].setGravity(Gravity.CENTER);
    }


    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            ScreenSlideFragment fragment = new ScreenSlideFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return DOT_NUM;
        }
    }

    public class ScreenSlideFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            Bundle args = getArguments();
            int position = args.getInt("position");
            int layoutId = getLayoutId(position);
            ViewGroup rootView = (ViewGroup) inflater.inflate(layoutId, container, false);

            if (0 == position)
                initFirstScreenViews(rootView, savedInstanceState);
            if (1 == position)
                initSecondScreenViews(rootView, savedInstanceState);
            if (2 == position)
                initThirdScreenViews(rootView, savedInstanceState);

            return rootView;
        }


        private int getLayoutId(int position) {
            int id = 0;
            if (0 == position) {
                id = R.layout.introduce_first_view;
            } else if (1 == position) {
                id = R.layout.introduce_second_view;
            } else if (2 == position) {
                id = R.layout.introduce_third_view;
            }
            return id;
        }

    }

    /**
     * 初始化viewpager第一个页面view
     *
     * @author trap
     * @time 2017年09月05日15:06:47
     */
    private void initFirstScreenViews(View rootView, Bundle savedInstanceState) {

    }

    /**
     * 初始化viewpager第而个页面view
     *
     * @author trap
     * @time 2017年09月05日15:07:11
     */
    private void initSecondScreenViews(View rootView, Bundle savedInstanceState) {

    }

    /**
     * 初始化viewpager第三个页面view
     *
     * @author trap
     * @time 2017年09月05日15:07:25
     */
    private void initThirdScreenViews(View rootView, Bundle savedInstanceState) {

    }


    /**
     * 跳转主页
     *
     * @author trap
     * @time 2017年09月01日11:38:50
     */
    private void goHome() {
        Sp.setValue(SPKey.SPKEY_IS_FIRST_ENTER, true);
        startActivity(new Intent(this, MainActivity.class));
    }
}
