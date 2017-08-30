package com.chinaums.yunerp.util;

import android.hardware.Camera;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by lizhenbin on 16/10/19 09:57.
 * Explain:
 */
public class FlashlightManager {
    private static final String TAG = FlashlightManager.class.getSimpleName();
    private static final Object iHardwareService;
    private static final Method setFlashEnabledMethod;
    static {
        iHardwareService = getHardwareService();
        setFlashEnabledMethod = getSetFlashEnabledMethod(iHardwareService);
        if (iHardwareService == null) {
            Log.v(TAG, "This device does supports control of a flashlight");
        } else {
            Log.v(TAG, "This device does not support control of a flashlight");
        }
    }
    private FlashlightManager() {
    }
    private static Object getHardwareService() {
        Class<?> serviceManagerClass = maybeForName("android.os.ServiceManager");
        if (serviceManagerClass == null) {
            return null;
        }
        Method getServiceMethod = maybeGetMethod(serviceManagerClass,
                "getService", String.class);
        if (getServiceMethod == null) {
            return null;
        }
        Object hardwareService = invoke(getServiceMethod, null, "hardware");
        if (hardwareService == null) {
            return null;
        }
        Class<?> iHardwareServiceStubClass = maybeForName("android.os.IHardwareService$Stub");
        if (iHardwareServiceStubClass == null) {
            return null;
        }
        Method asInterfaceMethod = maybeGetMethod(iHardwareServiceStubClass,
                "asInterface", IBinder.class);
        if (asInterfaceMethod == null) {
            return null;
        }
        return invoke(asInterfaceMethod, null, hardwareService);
    }
    private static Method getSetFlashEnabledMethod(Object iHardwareService) {
        if (iHardwareService == null) {
            return null;
        }
        Class<?> proxyClass = iHardwareService.getClass();
        return maybeGetMethod(proxyClass, "setFlashlightEnabled", boolean.class);
    }
    private static Class<?> maybeForName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException cnfe) {
            // OK
            return null;
        } catch (Exception re) {
            re.printStackTrace();
            Log.w(TAG, "Unexpected error while finding class " + name, re);
            return null;
        }
    }
    /**
     * 通过设置Camera打开闪光灯
     * @param mCamera
     */
    public static void turnLightOn(Camera mCamera) {
        if (mCamera == null) {
            return;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        if (parameters == null) {
            return;
        }
        List<String> flashModes = parameters.getSupportedFlashModes();
        // Check if camera flash exists
        if (flashModes == null) {
            // Use the screen as a flashlight (next best thing)
            return;
        }
        String flashMode = parameters.getFlashMode();
        Log.i(TAG, "Flash mode: " + flashMode);
        Log.i(TAG, "Flash modes: " + flashModes);
        if (!Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode)) {
            // Turn on the flash
            if (flashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                mCamera.setParameters(parameters);
            } else {
            }
        }
    }
    /**
     * 通过设置Camera关闭闪光灯
     * @param mCamera
     */
    public static void turnLightOff(Camera mCamera) {
        if (mCamera == null) {
            return;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        if (parameters == null) {
            return;
        }
        List<String> flashModes = parameters.getSupportedFlashModes();
        String flashMode = parameters.getFlashMode();
        // Check if camera flash exists
        if (flashModes == null) {
            return;
        }
        Log.i(TAG, "Flash mode: " + flashMode);
        Log.i(TAG, "Flash modes: " + flashModes);
        if (!Camera.Parameters.FLASH_MODE_OFF.equals(flashMode)) {
            // Turn off the flash
            if (flashModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(parameters);
            } else {
                Log.e(TAG, "FLASH_MODE_OFF not supported");
            }
        }
    }
    private static Method maybeGetMethod(Class<?> clazz, String name,
                                         Class<?>... argClasses) {
        try {
            return clazz.getMethod(name, argClasses);
        } catch (Exception nsme) {
            nsme.printStackTrace();
            // OK
            return null;
        }
    }
    private static Object invoke(Method method, Object instance, Object... args) {
        try {
            return method.invoke(instance, args);
        } catch (Exception e) {
            Log.w(TAG, "Unexpected error while invoking " + method, e);
            return null;
        }
    }
    /**
     * 通过反射来操作闪光灯
     * @param active
     */
    public static void switchFlashlight(boolean active) {
        setFlashlight(active);
    }
    static void disableFlashlight() {
        setFlashlight(false);
    }
    private static void setFlashlight(boolean active) {
        if (iHardwareService != null) {
            invoke(setFlashEnabledMethod, iHardwareService, active);
        }
    }
}
