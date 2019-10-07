package mao.com.mao_wanandroid_client.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.ToastUtils;

/**
 * @author maoqitian
 * @Description MVP BaseActivity 基类
 * 结合 Dagger2 本应该继承 DaggerAppCompatActivity，但是我们基类为AbstractSimpleActivity，则手动实现DaggerAppCompatActivity功能
 * @Time 2018/10/9 0009 22:46
 */
public abstract class  BaseActivity <T extends AbstractBasePresenter> extends AbstractSimpleActivity implements BaseView, HasFragmentInjector,HasSupportFragmentInjector {

    //Presenter 对象注入 (注意不能使用 private )
    @Inject
    protected T mPresenter;
    //手动实现DaggerAppCompatActivity功能
    @Inject DispatchingAndroidInjector<Fragment> supportFragmentInjector;
    @Inject DispatchingAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //必须在super.onCreate之前调用AndroidInjection.inject
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
            Log.e("毛麒添","BaseActivity mPresenter 不为空" + mPresenter.getClass());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }


    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtils.showToastShort(this,errorMsg);
    }

    @Override
    public void useThemeMode(int mode) {
        //设置当前主题模式
        AppCompatDelegate.setDefaultNightMode(mode);
        mPresenter.setThemeMode(mode);
        recreate();
    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }


    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    public void reload() {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }

    //收藏或者取消收藏
    protected void addOrCancelCollect(int position,HomeArticleData homeArticleData) {
        if(!mPresenter.getLoginStatus()){
            StartDetailPage.start(this,null, Constants.PAGE_LOGIN,Constants.ACTION_LOGIN_ACTIVITY);
            return;
        }
        if(!homeArticleData.isCollect()){
            //收藏
            mPresenter.addArticleCollect(position,homeArticleData);
        }else {
            //取消收藏
            mPresenter.cancelArticleCollect(position,homeArticleData);
        }
    }

    @Override
    public void showCoinAndRank(int coin) {

    }

    @Override
    public void showAddArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg) {

    }

    @Override
    public void showCancelArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg) {

    }
}
