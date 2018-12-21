package mao.com.mao_wanandroid_client.base.activity;

import android.text.TextUtils;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;

/**
 * @author maoqitian
 * @Description
 * @Time 2018/10/25 0025 16:52
 */
public class RootActivity<T extends RxBasePresenter> extends BaseActivity<T> {


    //状态
    private static final int STATE_NORMAL = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;

    //默认为NORMAL状态
    private int currentState = STATE_NORMAL;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
    }


    @Override
    public void showNormal() {
        super.showNormal();

    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void showError() {
        super.showError();
    }

}
