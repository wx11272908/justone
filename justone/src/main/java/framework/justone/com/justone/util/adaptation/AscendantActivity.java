package framework.justone.com.justone.util.adaptation;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 适配不同手机屏幕
 */
public class AscendantActivity extends Activity {
	public Context thisInstance;
	public static MobUtil mobUtil;
	boolean b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA)
				.format(new Date());
		String v = "[ "
				+ ScreenSchema.w
				+ " "
				+ ScreenSchema.h
				+ " "
				+ ScreenSchema.densityDpi
				+ " "
				+ ScreenSchema.density
				+ " "
				+ ScreenSchema.deviceSize
				+ " - "
				+ (Float.parseFloat(ScreenSchema.w + "") / (Float
						.parseFloat(ScreenSchema.densityDpi + ""))) + " ] "
				+ " : [ " + Build.DEVICE + "-" + Build.PRODUCT + "-"
				+ Build.DISPLAY + " " + date + " ]";
		//Log.d("LOG_D", v);
		
		if (!b) {

			b = true;

			Map<String, String> map = new HashMap<String, String>();

			map.put("screen", v);

			Map<String, String> value = new HashMap<String, String>();
			value.put("screenschema", map.toString());

			ScreenSchema.w = this.getWindowManager().getDefaultDisplay()
					.getWidth();
			ScreenSchema.h = this.getWindowManager().getDefaultDisplay()
					.getHeight();
			ScreenSchema.densityDpi = getResources().getDisplayMetrics().densityDpi;
			ScreenSchema.density = getResources().getDisplayMetrics().density;

			Float densityDpi = new Float(
					320f * new Float(ScreenSchema.w) / 720f);

			Float density = new Float(densityDpi / 160);
			ScreenSchema.densityDpi = densityDpi.intValue();

			ScreenSchema.density = density;

		}
		getResources().getDisplayMetrics().densityDpi = ScreenSchema.densityDpi;
		getResources().getDisplayMetrics().density = ScreenSchema.density;
		initActivity(this);

	}

	public static void initActivity(Activity activity) {
		if (mobUtil == null) {
			MobUtil.instance = mobUtil = new MobUtil(activity);
		}
		if (activity instanceof AscendantActivity) {
			((AscendantActivity) activity).thisInstance = activity;
		}
	}

}
