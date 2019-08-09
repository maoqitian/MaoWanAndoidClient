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
public class SignUpPresenter extends RxBasePresenter<SignUpContract.SignUpView> implements SignUpContract.SignUpActivityPresenter {

    private DataClient mDataClient;

    @Inject
    public SignUpPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }


    @Override
    public void getSignUpLogin(Context context, String username, String password, String repassword) {
        Observable<ResponseBody<LoginData>> responseBodyObservable = mDataClient.postSignUpData(username, password, repassword);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main(context))
                              .subscribe(new ProgressObserver<LoginData>(context, context.getString(R.string.registered)) {
                                  @Override
                                  public void onSuccess(LoginData result) {
                                      //注册成功
                                       mDataClient.setLoginUserName(result.getUsername());
                                       mDataClient.setLoginPassword(password);
                                       mDataClient.setLoginStatus(true);
                                       RxBus.getDefault().post(new LoginStatusEvent(true,false));
                                       mView.showSignUpSuccess();
                                  }
                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {
                                      mDataClient.setLoginStatus(false);
                                      mDataClient.setLoginUserName("");
                                      mDataClient.setLoginPassword("");
                                      mView.showSignUpFail(errorMsg);
                                  }
                              });
    }
}
