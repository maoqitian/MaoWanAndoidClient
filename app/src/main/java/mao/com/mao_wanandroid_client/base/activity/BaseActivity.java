package mao.com.mao_wanandroid_client.base.activity;

import android.support.v4.app.Fragment;

import dagger.android.AndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description MVP BaseActivity
 * @Time 2018/10/9 0009 22:46
 */
public class BaseActivity <T extends AbstractBasePresenter> extends AbstractSimpleActivity implements BaseView,HasSupportFragmentInjector {


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
    public void showReload() {

    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void useNightMode(boolean isNightMode) {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    public void showCollectSuccess() {

    }

    @Override
    public void showCancelCollectSuccess() {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showSnackBar(String message) {

    }

    @Override
    protected void onViewCreated() {

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
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }
}
