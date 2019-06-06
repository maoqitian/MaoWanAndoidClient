package mao.com.mao_wanandroid_client.presenter.login;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description:
 * @date 2019/6/6 0006 11:17
 */
public class LoginPresenter extends RxBasePresenter<LoginContract.LoginView> implements LoginContract.LoginActivityPresenter {

    private DataClient mDataClient;

    @Inject
    public LoginPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(LoginContract.LoginView view) {
        super.attachView(view);
    }
}
