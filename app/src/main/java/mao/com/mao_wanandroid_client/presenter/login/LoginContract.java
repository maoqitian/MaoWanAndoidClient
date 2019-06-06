package mao.com.mao_wanandroid_client.presenter.login;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/2/21 0021 23:30
 */
public interface LoginContract {

    interface LoginView extends BaseView{

    }


    interface LoginActivityPresenter extends AbstractBasePresenter<LoginView>{

    }

}
