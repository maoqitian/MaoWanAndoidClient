package mao.com.mao_wanandroid_client.base.fragment;

import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description 每個Fragment 的基类
 * @Time 2018/12/14 0014 22:48
 */
public class RootBaseFragment <T extends AbstractBasePresenter>extends BaseFragment <T> {

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
