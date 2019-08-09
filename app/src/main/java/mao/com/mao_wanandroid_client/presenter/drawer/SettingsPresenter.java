package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.setting.SettingData;

/**
 * @author maoqitian
 * @Description: 收藏 Presenter
 * @date 2019/7/26 0026 16:07
 */
public class SettingsPresenter extends RxBasePresenter<SettingsContract.SettingsView> implements SettingsContract.SettingsFragmentPresenter {

    private DataClient mDataClient;


    @Inject
    public SettingsPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(SettingsContract.SettingsView view) {
        super.attachView(view);
    }

    @Override
    public void getSettingsItemData() {
        List<SettingData> settingDataList = new ArrayList<>();
        settingDataList.add(new SettingData("夜间模式",true, Constants.SETTINGS_NIGHT_MODE_TYPE));
        settingDataList.add(new SettingData("清除缓存",false,Constants.SETTINGS_CLEAR_CACHE_TYPE));
        settingDataList.add(new SettingData("版本",false,Constants.SETTINGS_VERSION_TYPE));
        mView.showSettingsItemData(settingDataList);
    }


    //退出登录
    @Override
    public void getSingOut() {
        Observable<ResponseBody<String>> loginOut = mDataClient.getSignOut();
        loginOut.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String result) {
                        mDataClient.setLoginStatus(false);
                        mView.showSingOutSuccess();
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showSingOutFail(errorMsg);
                    }
                });
    }
}
