package mao.com.mao_wanandroid_client.presenter.login;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.LoginStatusEvent;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.ProgressObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.login.LoginData;

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

    @Override
    public void getPostLogin(Context context,String username, String password) {
        Observable<ResponseBody<LoginData>> responseBodyObservable = mDataClient.postLoginData(username, password);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main(context))
                .subscribe(new ProgressObserver<LoginData>(context, context.getString(R.string.landing)) {
                    @Override
                    public void onSuccess(LoginData result) {
                        mDataClient.setLoginUserName(result.getUsername());
                        mDataClient.setLoginPassword(password);
                        mDataClient.setLoginStatus(true);
                        mView.showLoginSuccess();
                        //发送登录状态到事件总线
                        RxBus.getDefault().post(new LoginStatusEvent(true,false));
                    }
                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mDataClient.setLoginUserName("");
                        mDataClient.setLoginPassword("");
                        mDataClient.setLoginStatus(false);
                        mView.showLoginFail(errorMsg);
                    }
                });

    }
}
