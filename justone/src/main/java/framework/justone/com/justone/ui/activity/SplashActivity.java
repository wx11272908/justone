package framework.justone.com.justone.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import framework.justone.com.justone.R;
import framework.justone.com.justone.cache.sp.SPKey;
import framework.justone.com.justone.cache.sp.Sp;
import framework.justone.com.justone.ui.view.CTextView;
import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {

    private FingerprintManagerCompat managerCompat;
    private ImageView imgPrictrre;

    private Context context = SplashActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initFingerPrint();
    }

    private void initFingerPrint() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;
        else
            Toast.makeText(context, "请验证指纹进入", Toast.LENGTH_SHORT).show();
        managerCompat = FingerprintManagerCompat.from(context);
        managerCompat.authenticate(null, 0, null, new FingerprintManagerCompat.AuthenticationCallback() {
            private static final String TAG = "MyCallBack";

            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                Log.d(TAG, "onAuthenticationError: " + errString);
                Toast.makeText(context, "onAuthenticationError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                Log.d(TAG, "onAuthenticationHelp: " + helpString);
                Toast.makeText(context, "onAuthenticationError", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                Log.d(TAG, "onAuthenticationSucceeded: " + "验证成功");
                Toast.makeText(context, "onAuthenticationError:验证成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAuthenticationFailed() {
                Log.d(TAG, "onAuthenticationFailed: " + "验证失败");
                Toast.makeText(context, "onAuthenticationError:验证失败,请重新验证指纹", Toast.LENGTH_SHORT).show();


            }
        }, null);
    }

    private void initView() {

        CTextView cTextView = (CTextView) findViewById(R.id.tv_splash_name);
        cTextView.setText("智享生活", AnimationUtils.loadAnimation(context, R.anim.text_alpha), 600);
        GifImageView view = (GifImageView) findViewById(R.id.img_gif);
        view.setImageResource(R.mipmap.icongiff);
        imgPrictrre = (ImageView) findViewById(R.id.img_splash_pricture);
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(3000);// 设置动画显示时间
        imgPrictrre.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
    }


    private class AnimationImpl implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
//            welcomeImg.setBackgroundResource(R.drawable.welcome);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if ((Boolean) Sp.getValue(SPKey.SPKEY_IS_FIRST_ENTER, true))//判断是否首次进入
                toIntent(IntroduceActivity.class); // 动画结束后跳转到别的页面
            else
                toIntent(MainActivity.class); // 动画结束后跳转到别的页面
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }


    private void toIntent(Class clazz) {
        startActivity(new Intent(this, clazz));
        finish();
    }


}
