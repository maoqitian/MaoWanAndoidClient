package mao.com.mao_wanandroid_client.presenter.login;

import android.content.Context;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/2/21 0021 23:30
 */
public interface LoginContract {

    interface LoginView extends BaseView{

        void showLoginSuccess();
        void showLoginFail(String errorMsg);
    }


    interface LoginActivityPresenter extends AbstractBasePresenter<LoginView>{
         void getPostLogin(Context context,String username, String password);
    }

}
