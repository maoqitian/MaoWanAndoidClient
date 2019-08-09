package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.setting.SettingData;

/**
 * @author maoqitian
 * @Description:
 * @date 2019/7/26 0026 16:04
 */
public interface SettingsContract {

    interface SettingsView extends BaseView{
        void showSettingsItemData(List<SettingData> settingDataList);
        void showSingOutSuccess();

        void showSingOutFail(String errorMsg);
    }
    

    interface  SettingsFragmentPresenter extends AbstractBasePresenter<SettingsView>{

        void getSettingsItemData();

        void getSingOut();
    }

}

