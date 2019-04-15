package mao.com.mao_wanandroid_client.utils;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

import mao.com.mao_wanandroid_client.application.Constants;

import static mao.com.mao_wanandroid_client.application.Constants.PATH_NET_CACHE;

/**
 * @author maoqitian
 * @Description: 文件管理
 * @date 2019/1/23 0023 16:31
 */

public class FileUtils {
    private static String mSDCardFolderPath;
    private static String mImgFolderPath;
    private static String mApkFolderPath;
    private static String mCacheFolderPath;
    private static String mLogFolderPath;

    public static FileUtils getInstance() {

        return FileUtilsHolder.instance;
    }

    public void init() {
        mImgFolderPath = getImgFolderPath();
        mApkFolderPath = getApkFolderPath();
        mCacheFolderPath = getCacheFolderPath();
        mLogFolderPath = getLogFolderPath();

        mkdirs(mImgFolderPath);
        mkdirs(mApkFolderPath);
        mkdirs(mCacheFolderPath);
        mkdirs(mLogFolderPath);
    }

    private String getSDCardFolderPath() {
        if (TextUtils.isEmpty(mSDCardFolderPath)) {
            mSDCardFolderPath = Environment.getExternalStorageDirectory().getPath() + "/CacheDemo";
            //mSDCardFolderPath = Constants.PATH_NET_CACHE;
        }

        return mSDCardFolderPath;
    }

    public File mkdirs(@NonNull String path) {
        File file = new File(path);
        if (!file.exists()) {
            boolean issu = file.mkdirs();
            //Log.e("毛麒添 缓存路径",file.getAbsolutePath() + ""+ issu);
        }
        //Log.e("毛麒添 缓存路径",file.getAbsolutePath());
        return file;
    }

    public String getCacheFolderPath() {
        if (TextUtils.isEmpty(mCacheFolderPath)) {
            mCacheFolderPath = getSDCardFolderPath() + "/Cache/";
        }
        return mCacheFolderPath;
    }

    public String getImgFolderPath() {
        if (TextUtils.isEmpty(mImgFolderPath)) {
            mImgFolderPath = getSDCardFolderPath() + "/Img/";
        }
        return mImgFolderPath;
    }

    public String getApkFolderPath() {
        if (TextUtils.isEmpty(mApkFolderPath)) {
            mApkFolderPath = getSDCardFolderPath() + "/Apk/";
        }
        return mApkFolderPath;
    }

    public String getLogFolderPath() {
        if (TextUtils.isEmpty(mLogFolderPath)) {
            mLogFolderPath = getSDCardFolderPath() + "/Log/";
        }
        return mLogFolderPath;
    }

    public File getCacheFolder(){
        return mkdirs(getCacheFolderPath());
    }

    private static class FileUtilsHolder {
        private static FileUtils instance = new FileUtils();
    }
}
