package mao.com.mao_wanandroid_client.base.fragment;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description MVP Fragment 基类
 * @Time 2018/12/14 0014 22:47
 */
public class BaseFragment <T extends AbstractBasePresenter> extends SimpleFragment implements BaseView {

    //Presenter 对象注入
    @Inject
    private T mPresenter;



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
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }
}
