package mao.com.mao_wanandroid_client.utils;

import android.content.Context;
import android.net.ConnectivityManager;

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
}
