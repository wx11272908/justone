package framework.justone.com.justone.util.adaptation;


import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenSchema {
	public static int w;
	public static int h;
	public static int densityDpi;
	public static float density;
	public static double deviceSize;

	public static int getW() {
		return w;
	}

	public static void setW(int w) {
		if(ScreenSchema.w==0){
			ScreenSchema.w = w;			
		}
	}

	public static int getH() {
		return h;
	}

	public static void setH(int h) {
		if(ScreenSchema.h==0){
			ScreenSchema.h = h;			
		}
	}

	public static int getDensityDpi() {
		return densityDpi;
	}

	public static void setDensityDpi(int densityDpi) {		
		ScreenSchema.densityDpi = densityDpi;
	}

	public static float getDensity() {
		return density;
	}

	public static void setDensity(float density) {
		ScreenSchema.density = density;
	}

	public static double getDeviceSize() {
		return deviceSize;
	}

	public static void setDeviceSize(double deviceSize) {
		ScreenSchema.deviceSize = deviceSize;
	}

	public static void initSize(Activity aty) {
		if (w == 0) {

			DisplayMetrics displayMetrics = aty.getResources()
					.getDisplayMetrics();
			aty.getWindowManager().getDefaultDisplay()
					.getMetrics(displayMetrics);
			double size = Math.sqrt(Math.pow(displayMetrics.widthPixels, 2)
					+ Math.pow(displayMetrics.heightPixels, 2));
			size = size / (160 * displayMetrics.density);
			deviceSize = size;
			ScreenSchema.w = displayMetrics.widthPixels;
			ScreenSchema.w = displayMetrics.heightPixels;
		}
	}
}
