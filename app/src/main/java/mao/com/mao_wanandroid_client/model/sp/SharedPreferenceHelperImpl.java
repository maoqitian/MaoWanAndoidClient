package mao.com.mao_wanandroid_client.model.sp;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.application.MyApplication;

/**
 * @author maoqitian
 * @Description
 * @Time 2018/10/30 0030 23:32
 */
public class SharedPreferenceHelperImpl implements SharedPreferenceHelper {

    private final SharedPreferences mSharedPreferences;

    @Inject
    public SharedPreferenceHelperImpl(){
        mSharedPreferences=MyApplication.getInstance().getSharedPreferences(Constants.SHAREDPREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    @Override
    public void setLoginUserName(String userName) {
        mSharedPreferences.edit().putString(Constants.SP_USER_NAME,userName).apply();
    }

    @Override
    public void setLoginPassword(String password) {
        mSharedPreferences.edit().putString(Constants.SP_PASSWORD,password).apply();
    }

    @Override
    public String getLoginUserName() {
        return mSharedPreferences.getString(Constants.SP_USER_NAME,"");
    }
    @Override
    public String getLoginPassword() {
        return mSharedPreferences.getString(Constants.SP_PASSWORD,"");
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {
        mSharedPreferences.edit().putBoolean(Constants.SP_LOGIN_STATUS,loginStatus).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mSharedPreferences.getBoolean(Constants.SP_LOGIN_STATUS,false);
    }


    @Override
    public void setCurrentPage(int position) {
        mSharedPreferences.edit().putInt(Constants.SP_CURRENT_PAGE,position).apply();
    }

    @Override
    public int getCurrentPage() {
        return mSharedPreferences.getInt(Constants.SP_CURRENT_PAGE,0);
    }

    @Override
    public void setProjectCurrentPage(int position) {
        mSharedPreferences.edit().putInt(Constants.SP_PROJECT_CURRENT_PAGE,position).apply();
    }

    @Override
    public int getProjectCurrentPage() {
        return mSharedPreferences.getInt(Constants.SP_PROJECT_CURRENT_PAGE,0);
    }

    @Override
    public void setAutoCacheState(boolean autoCache) {
        mSharedPreferences.edit().putBoolean(Constants.SP_AUTO_CACHE,autoCache).apply();
    }

    @Override
    public boolean getAutoCacheState() {
        return mSharedPreferences.getBoolean(Constants.SP_AUTO_CACHE,false);
    }

    @Override
    public void setNoImageState(boolean noImageState) {
        mSharedPreferences.edit().putBoolean(Constants.SP_NO_IMAGE,noImageState).apply();
    }

    @Override
    public boolean getNoImageState() {
        return mSharedPreferences.getBoolean(Constants.SP_NO_IMAGE,false);
    }

    /**
     * 跟随系统，默认值 通常为 MODE_NIGHT_NO = 1，所以 defValue 为 1
     * @return
     */
    @Override
    public int getNightMode() {
        return  mSharedPreferences.getInt(Constants.SP_NIGHT_MODE,1);
    }

    @Override
    public void setNightMode(int mode) {
        mSharedPreferences.edit().putInt(Constants.SP_NIGHT_MODE,mode).apply();
    }
}
