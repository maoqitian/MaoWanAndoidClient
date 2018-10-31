package mao.com.mao_wanandroid_client.core.sp;

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
    public void setLoginAccount(String account) {
        mSharedPreferences.edit().putString(Constants.SP_ACCOUNT,account).apply();
    }

    @Override
    public void setLoginPassword(String password) {
        mSharedPreferences.edit().putString(Constants.SP_PASSWORD,password).apply();
    }

    @Override
    public String getLoginAccount() {
      return mSharedPreferences.getString(Constants.SP_ACCOUNT,"");
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
    public void setCookie(String domain, String cookie) {
        mSharedPreferences.edit().putString(Constants.COOKIE,cookie).apply();
    }

    @Override
    public String getCookie(String domain) {
        return mSharedPreferences.getString(Constants.COOKIE,"");
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

    @Override
    public void setNightModeState(boolean nightState) {
        mSharedPreferences.edit().putBoolean(Constants.SP_NIGHT_MODE,nightState).apply();
    }

    @Override
    public boolean getNightModeState() {
        return mSharedPreferences.getBoolean(Constants.SP_NIGHT_MODE,false);
    }
}
