package mao.com.mao_wanandroid_client.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import mao.com.mao_wanandroid_client.application.MyApplication;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/9/8 0008 10:51
 */
public class ToastUtils {

    private static Toast mToast;

    /**
     * 封装了Toast的方法 :需要等待
     *
     * @param context Context
     * @param str     要显示的字符串
     * @param isLong  Toast.LENGTH_LONG / Toast.LENGTH_SHORT
     */
    public static void showToast(Context context, String str, boolean isLong) {
        if (isLong) {
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showToastShort(Context context,String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showToastShort(Context context,int resId) {
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showToastLong(Context context,String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showToastLong(int resId) {
        Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param msg 显示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param resId String资源ID
     */
    public static void showToast(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getString(resId), Toast.LENGTH_LONG);
        } else {
            mToast.setText(MyApplication.getInstance().getApplicationContext().getString(resId));
        }
        mToast.show();
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param context  实体
     * @param resId    String资源ID
     * @param duration 显示时长
     */
    public static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getString(resId), duration);
    }
    //===========================================Toast 替代方法======================================

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param context  实体
     * @param msg      要显示的字符串
     * @param duration 显示时长
     */
    @SuppressLint("ShowToast")
    public static void showToast(Context context, String msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, duration);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
