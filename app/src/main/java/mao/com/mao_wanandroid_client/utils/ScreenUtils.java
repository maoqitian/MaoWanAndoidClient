package mao.com.mao_wanandroid_client.utils;

/**
 * @author maoqitian
 * @Description: 状态栏 导航栏工具类
 * @date 2019/6/28 0028 16:31
 */
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Method;

public class ScreenUtils {

    //隐藏底部状态栏
    public static void hideBottomUIMenu(Context context) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = ((Activity) context).getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = ((Activity) context).getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 是否有底部状态栏
     *
     * @param context
     * @return
     */

    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;

    }

    public static int getWight(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screenWidth = 0;
        /*DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);*/
        //DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics dm = new DisplayMetrics();
            display.getRealMetrics(dm);
            screenWidth = dm.widthPixels;
            //或者也可以使用getRealSize方法
//            Point size = new Point();
//            display.getRealSize(size);
//            screenHeight = size.y;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            try {
                screenWidth = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
            } catch (Exception e) {
                DisplayMetrics dm = new DisplayMetrics();
                display.getMetrics(dm);
                screenWidth = dm.heightPixels;
            }
        }
        return screenWidth;
    }

    /**
     * 某些手机有底部导航栏 所以获取的高度不是真实的高度 而是去掉导航栏的高度，所有这里获取真实高度需要重新就算 by maoqitian
     *
     * @param mContext
     * @return
     */
    public static int getHeight(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screenHeight = 0;
        /*DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);*/
        //DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics dm = new DisplayMetrics();
            display.getRealMetrics(dm);
            screenHeight = dm.heightPixels;
            //或者也可以使用getRealSize方法
//            Point size = new Point();
//            display.getRealSize(size);
//            screenHeight = size.y;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            try {
                screenHeight = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) {
                DisplayMetrics dm = new DisplayMetrics();
                display.getMetrics(dm);
                screenHeight = dm.heightPixels;
            }
        }
        return screenHeight;
    }

    /**
     * 获取底部导航栏高度 by maoqitian
     *
     * @param mContext
     * @return
     */

    public static int getNavigationBarHeight(Context mContext) {
        int navigationBarHeight = -1;
        Resources resources = mContext.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }

    public static boolean isInRight(Context mContext, int xWeight) {
        return (xWeight > getHeight(mContext) * 3 / 4);
    }

    public static boolean isInLeft(Context mContext, int xWeight) {
        return (xWeight < getWight(mContext) * 1 / 4);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 是否横屏
     *
     * @param mContext
     * @return
     */
    public static boolean screenIsLanscape(Context mContext) {
        boolean ret = false;
        switch (mContext.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                ret = false;
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                ret = true;
                break;
            default:
                break;
        }
        return ret;
    }

    /**
     * 获取当前屏幕状态
     *
     * @param mContext
     * @return
     */
    public static int getOrientation(Context mContext) {
        return mContext.getResources().getConfiguration().orientation;
    }

    /**
     * 获取16：10图片的宽度。这个宽度实际就是屏幕的宽度-2dp /2.
     *
     * @param mContext 只用于两列的全屏listView 中
     * @return
     */
    public static int getImageWidht16_10(Context mContext) {
        return (getWight(mContext) - dip2px(mContext, 2)) / 2;
    }

    /**
     * 获取16：10图片的高度。这个方法和getImageWidht16_10() 有关。比例
     *
     * @param mContext
     * @return
     */
    public static int getImageHight16_10(Context mContext) {
        return (int) (getImageWidht16_10(mContext) / 1.6);
    }

    /**
     * 根据屏幕宽度获取16-10的屏幕高度
     */
    public static int getImageWidth16_10(int heightPx) {
        return (int) (heightPx * 1.6);
    }

    public static int getImageHeight16_10(int widthPx) {
        return (int) (widthPx / 1.6);
    }

    public static int getImageHeight16_9(int widthPx) {
        return (widthPx * 9) / 16;
    }

    public static int getImageHeight7_2(int widthPx) {
        return (widthPx * 2) / 7;
    }
}