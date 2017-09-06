package framework.justone.com.justone.ui.activity;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import framework.justone.com.justone.R;
import framework.justone.com.justone.cache.sp.SPKey;
import framework.justone.com.justone.cache.sp.Sp;
import framework.justone.com.justone.ui.base.BaseActivity;
import framework.justone.com.justone.ui.view.BookView;
import framework.justone.com.justone.ui.view.SunMoonView;
import framework.justone.com.justone.ui.view.ThirdScreenView;

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

    private boolean mSecondPageSelected;
    private HashMap<ImageView, Float> mOriginalXValuesMap = new HashMap<>();
    private int mSelectedPosition = -1;

    //First screen
    private ImageView mCenterBox;
    private ImageView mCamcordImage;
    private ImageView mClockImage;
    private ImageView mGraphImage;
    private ImageView mAudioImage;
    private ImageView mQuoteImage;
    private ImageView mMapImage;
    private ImageView mWordPressImage;
    private AnimatorSet mAnimatorSet;


    //Second screen
    private SunMoonView mAnimationView;
    private float mPreviousPositionOffset;
    private boolean mViewPagerScrollingLeft;
    private int mPreviousPosition;
    private BookView mBookView;

    // Third screen
    private boolean mShouldSpheresRotate = true;
    private ThirdScreenView mRoundView;
    private boolean mThirdPageSelected;
    private Button mLetsGoButton;


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
        viewPager.bringToFront();
    }


    private void setPageChangeListener(ViewPager viewpager) {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                // Scrollling left or right
                if ((positionOffset > mPreviousPositionOffset && position == mPreviousPosition) || (positionOffset < mPreviousPositionOffset && position > mPreviousPosition)) {
                    mViewPagerScrollingLeft = true;
                } else if (positionOffset < mPreviousPositionOffset) {

                    mViewPagerScrollingLeft = false;
                }
                mPreviousPositionOffset = positionOffset;
                mPreviousPosition = position;

                // FADE the indicator layout
                if (position == 1 && mViewPagerScrollingLeft) {

                    linIntroduce.setAlpha(1 - positionOffset);
                } else if (position == 1 && !mViewPagerScrollingLeft) {

                    linIntroduce.setAlpha(1 - positionOffset);
                }

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 1) {
                    mSelectedPosition = 1;
                    mSecondPageSelected = true;
                    setViewsInOriginalPosition();
                    //initializeAlpha();
                    if (mAnimatorSet != null) {
                        mAnimatorSet.cancel();
                    }

                    animateBookView();
                }
                if (position == 0) {
                    mSelectedPosition = 0;
                    doFadeAnimation();

                }


                for (int i = 0; i < dotTv.length; i++) {
                    dotTv[i].setBackgroundResource(R.drawable.dot_gray_bg);
                }
                dotTv[position].setBackgroundResource(R.drawable.dot_blue_bg);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    mShouldSpheresRotate = false;
                } else if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mShouldSpheresRotate = true;
                }
                if (mRoundView != null) {
                    mRoundView.setRotatingPermission(mShouldSpheresRotate);
                }

                if (mSelectedPosition == 0 && state == ViewPager.SCROLL_STATE_IDLE) {
                    mSecondPageSelected = false;
                }
            }
        });
    }



    private void animateBookView() {

        mBookView.fadeInTheLines();
    }


    private void setViewsInOriginalPosition() {

        mCenterBox.setX(mOriginalXValuesMap.get(mCenterBox));
        mCamcordImage.setX(mOriginalXValuesMap.get(mCamcordImage));
        mClockImage.setX(mOriginalXValuesMap.get(mClockImage));
        mGraphImage.setX(mOriginalXValuesMap.get(mGraphImage));
        mAudioImage.setX(mOriginalXValuesMap.get(mAudioImage));
        mQuoteImage.setX(mOriginalXValuesMap.get(mQuoteImage));
        mMapImage.setX(mOriginalXValuesMap.get(mMapImage));
        mWordPressImage.setX(mOriginalXValuesMap.get(mWordPressImage));

        initializeAlpha();

    }

    private void initializeAlpha() {

        mCamcordImage.setAlpha(0f);
        mClockImage.setAlpha(0f);
        mGraphImage.setAlpha(0f);
        mAudioImage.setAlpha(0f);
        mQuoteImage.setAlpha(0f);
        mMapImage.setAlpha(0f);
        mWordPressImage.setAlpha(0f);
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



    private class CustomTransformer implements ViewPager.PageTransformer {


        @Override
        public void transformPage(View page, float position) {

            int pageWidth = page.getWidth();
            if ((mViewPagerScrollingLeft && page.findViewById(R.id.center_box) != null)) {
                animateSecondScreen(position, pageWidth, 0);
            }

            if (!mViewPagerScrollingLeft && page.findViewById(R.id.center_box_second) != null) {
                animateSecondScreen(position, pageWidth, 1);
            }

            if (position < -1) {

            } else if (position <= 1) {

                if (!mSecondPageSelected && page.findViewById(R.id.center_box_second) != null) {
                    moveTheSpheres(position, pageWidth);
                }

                if (!mShouldSpheresRotate && page.findViewById(R.id.center_box_third) != null) {
                    mRoundView.translateTheSpheres(position, pageWidth);
                }


            } else {

            }

        }
    }

    private void moveTheSpheres(float position, int pageWidth) {


        float camcordPos = (float) ((1 - position) * 0.15 * pageWidth);
        if (camcordPos > (-1 * mOriginalXValuesMap.get(mCamcordImage))) {
            mCamcordImage.setTranslationX(camcordPos);
        }


        float clockPos = (float) ((1 - position) * 0.50 * pageWidth);
        if (clockPos > (-1 * mOriginalXValuesMap.get(mClockImage))) {
            mClockImage.setTranslationX(clockPos);
        }

        float graphPos = (float) ((1 - position) * 0.50 * pageWidth);
        if (graphPos > (-1 * mOriginalXValuesMap.get(mGraphImage))) {
            mGraphImage.setTranslationX(graphPos);
        }

        float audioPos = (float) ((1 - position) * 0.30 * pageWidth);
        if (audioPos > (-1 * mOriginalXValuesMap.get(mAudioImage))) {
            mAudioImage.setTranslationX(audioPos);
        }


        float quotePos = (float) (-(1 - position) * 0.37 * pageWidth);
        if (quotePos > (-1 * mOriginalXValuesMap.get(mQuoteImage))) {
            mQuoteImage.setTranslationX(quotePos);
        }

        float mapPos = (float) (-(1 - position) * 1.1 * pageWidth);
        if (mapPos > (-1 * mOriginalXValuesMap.get(mMapImage))) {
            mMapImage.setTranslationX(mapPos);
        }

        float wordpressPos = (float) (-(1 - position) * 0.37 * pageWidth);
        if (wordpressPos > (-1 * mOriginalXValuesMap.get(mWordPressImage))) {
            mWordPressImage.setTranslationX(wordpressPos);
        }


    }

    private void animateSecondScreen(float position, int pageWidth, int direction) {

        if (direction == 0) {
            mAnimationView.animateSecondScreenClock(position);
        } else {
            mAnimationView.animateSecondScreenAntiClock(position);
        }
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

    @SuppressLint("ValidFragment")
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

    private void doFadeAnimation() {


        ObjectAnimator fadeCamcord = ObjectAnimator.ofFloat(mCamcordImage, "alpha", 0f, 1f);
        fadeCamcord.setDuration(700);

        ObjectAnimator fadeClock = ObjectAnimator.ofFloat(mClockImage, "alpha", 0f, 1f);
        fadeClock.setDuration(700);

        ObjectAnimator fadeGraph = ObjectAnimator.ofFloat(mGraphImage, "alpha", 0f, 1f);
        fadeGraph.setDuration(700);

        ObjectAnimator fadeAudio = ObjectAnimator.ofFloat(mAudioImage, "alpha", 0f, 1f);
        fadeAudio.setDuration(700);

        ObjectAnimator fadeQuote = ObjectAnimator.ofFloat(mQuoteImage, "alpha", 0f, 1f);
        fadeQuote.setDuration(700);

        ObjectAnimator fadeMap = ObjectAnimator.ofFloat(mMapImage, "alpha", 0f, 1f);
        fadeMap.setDuration(700);

        ObjectAnimator fadeWordpress = ObjectAnimator.ofFloat(mWordPressImage, "alpha", 0f, 1f);
        fadeWordpress.setDuration(700);

        //1 5    3 2  7 6  4

        mAnimatorSet = new AnimatorSet();
        fadeAudio.setStartDelay(50);
        fadeGraph.setStartDelay(200);
        fadeWordpress.setStartDelay(500);
        fadeClock.setStartDelay(700);
        fadeMap.setStartDelay(900);
        fadeQuote.setStartDelay(1100);

        mAnimatorSet.play(fadeCamcord).with(fadeAudio).with(fadeGraph).with(fadeWordpress).with(fadeClock).with(fadeMap).with(fadeQuote);
        mAnimatorSet.start();

    }


    /**
     * 初始化viewpager第一个页面view
     *
     * @author trap
     * @time 2017年09月05日15:06:47
     */
    private void initFirstScreenViews(View rootView, final Bundle savedInstanceState) {

        mCenterBox = (ImageView) rootView.findViewById(R.id.center_box);
        mCamcordImage = (ImageView) rootView.findViewById(R.id.imageView);
        mClockImage = (ImageView) rootView.findViewById(R.id.imageView6);
        mGraphImage = (ImageView) rootView.findViewById(R.id.imageView3);
        mAudioImage = (ImageView) rootView.findViewById(R.id.imageView4);
        mQuoteImage = (ImageView) rootView.findViewById(R.id.imageView5);
        mMapImage = (ImageView) rootView.findViewById(R.id.imageView2);
        mWordPressImage = (ImageView) rootView.findViewById(R.id.imageView7);

        initializeAlpha();

        rootView.post(new Runnable() {
            @Override
            public void run() {

                getOriginalXValues(savedInstanceState);

            }
        });

        if (savedInstanceState == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    doFadeAnimation();
                }
            }, 700);

        }

    }


    private void getOriginalXValues(Bundle savedInstanceState) {

        mOriginalXValuesMap.put(mCenterBox, mCenterBox.getX());
        mOriginalXValuesMap.put(mCamcordImage, mCamcordImage.getX());
        mOriginalXValuesMap.put(mClockImage, mClockImage.getX());
        mOriginalXValuesMap.put(mGraphImage, mGraphImage.getX());
        mOriginalXValuesMap.put(mAudioImage, mAudioImage.getX());
        mOriginalXValuesMap.put(mQuoteImage, mQuoteImage.getX());
        mOriginalXValuesMap.put(mMapImage, mMapImage.getX());
        mOriginalXValuesMap.put(mWordPressImage, mWordPressImage.getX());

        if (savedInstanceState == null) {
            viewPager.setPageTransformer(true, new CustomTransformer());
        }


    }

    /**
     * 初始化viewpager第而个页面view
     *
     * @author trap
     * @time 2017年09月05日15:07:11
     */
    private void initSecondScreenViews(View rootView, Bundle savedInstanceState) {
        final RelativeLayout secondScreenRoot = (RelativeLayout) rootView.findViewById(R.id.root);
        //final ImageView centerBox=(ImageView)rootView.findViewById(R.id.center_box_second);
        mBookView = (BookView) rootView.findViewById(R.id.center_box_second);
        mAnimationView = (SunMoonView) rootView.findViewById(R.id.animation_view);
    }

    /**
     * 初始化viewpager第三个页面view
     *
     * @author trap
     * @time 2017年09月05日15:07:25
     */
    private void initThirdScreenViews(View rootView, Bundle savedInstanceState) {

        mRoundView = (ThirdScreenView) rootView.findViewById(R.id.round_view);
        mLetsGoButton = (Button) rootView.findViewById(R.id.letsgo);

        mLetsGoButton.setOnClickListener(clickListener);
        mRoundView.setContext(this);

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.letsgo:

                    mRoundView.startNextScreen();
                    goHome();
                    break;
            }
        }
    };

    /**
     * 跳转主页
     *
     * @author trap
     * @time 2017年09月01日11:38:50
     */
    private void goHome() {
        Sp.setValue(SPKey.SPKEY_IS_FIRST_ENTER, false);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
