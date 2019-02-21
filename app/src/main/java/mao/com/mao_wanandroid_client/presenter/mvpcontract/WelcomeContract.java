package mao.com.mao_wanandroid_client.presenter.mvpcontract;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description view 和 presenter 实现连接的抽象接口 定义抽象的方法
 * @Time 2019/2/21 0021 23:30
 */
public interface WelcomeContract {

    interface WelcomeView extends BaseView{
        /**
         * 闪屏页进入MainActivity
         */
        void jumpToMainActivity();
    }

    interface WelcomePresenter extends AbstractBasePresenter<WelcomeView>{

    }

}
