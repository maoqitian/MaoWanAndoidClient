package mao.com.mao_wanandroid_client.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Random;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.MyApplication;

/**
 * @author maoqitian
 * @Description 工具类
 * @Time 2018/12/3 0003 22:04
 */
public class ToolsUtils {
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 手动反射 设置 tabLayout 下划线宽度 （适配API 28 ）
     * @param tabLayout
     * @param margin
     */
    public static void setIndicatorWidth(@NonNull final TabLayout tabLayout, final int margin) {
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    // 拿到tabLayout的slidingTabIndicator属性
                    Field slidingTabIndicatorField = tabLayout.getClass().getDeclaredField("slidingTabIndicator");
                    slidingTabIndicatorField.setAccessible(true);
                    LinearLayout mTabStrip = (LinearLayout) slidingTabIndicatorField.get(tabLayout);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性
                        Field textViewField = tabView.getClass().getDeclaredField("textView");
                        textViewField.setAccessible(true);
                        TextView mTextView = (TextView) textViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        // 因为想要的效果是字多宽线就多宽，所以测量mTextView的宽度
                        int width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        // 设置tab左右间距,注意这里不能使用Padding,因为源码中线的宽度是根据tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = margin;
                        params.rightMargin = margin;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 隐藏键盘
     *
     * @param etInput
     */
    public static void hideSoftInput(EditText etInput) {
        InputMethodManager imm = (InputMethodManager) etInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0); // 强制隐藏键盘
    }

    /**
     * 隐藏 activity 当前的软键盘
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示键盘
     *
     * @param etInput
     */
    public static void showSoftInput(EditText etInput) {
        InputMethodManager imm = (InputMethodManager) etInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        etInput.requestFocus();
        imm.showSoftInput(etInput, 0);
    }

    public static void showSoftInput2(EditText etInput) {
        InputMethodManager imm = (InputMethodManager) etInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        //获取焦点
        etInput.requestFocus();
        imm.showSoftInput(etInput, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 隐藏或显示软键盘
     * 如果现在是显示调用后则隐藏 反之则显示
     * @param activity
     */
    public static void showORhideSoftKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 判断软键盘是否显示方法
     * @param activity
     * @return
     */

    public static boolean isSoftShowing(Activity activity) {
        //获取当屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight - rect.bottom - getSoftButtonsBarHeight(activity)!= 0;
    }

    /**
     * 底部虚拟按键栏的高度
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static int getSoftButtonsBarHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }

    /**
     * 随机获取颜色
     * @return
     */
    public static int getRandColor() {
        Random random=new Random();
        int r=0;
        int g=0;
        int b=0;
        for(int i=0;i<2;i++){
            int temp=random.nextInt(16);
            r=r*16+temp;
            temp=random.nextInt(16);
            g=g*16+temp;
            temp=random.nextInt(16);
            b=b*16+temp;
        }
        return Color.rgb(r,g,b);
    }

    /**
     * 随机从固定的颜色组中获取颜色
     * @return
     */
    public static int getRandSomeColor(Context context) {
        Random random=new Random();
        int[] color={R.color.colorPrimary,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light};
        int temp=random.nextInt(5);
        return  ContextCompat.getColor(context, color[temp]);
    }

    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
}
