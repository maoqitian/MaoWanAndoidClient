package mao.com.mao_wanandroid_client.presenter.login;

import android.content.Context;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/2/21 0021 23:30
 */
public interface SignUpContract {

    interface SignUpView extends BaseView{

        void showSignUpSuccess();
        void showSignUpFail(String errorMsg);
    }


    interface SignUpActivityPresenter extends AbstractBasePresenter<SignUpView>{
         void getSignUpLogin(Context context, String username, String password, String repassword);
    }

}
