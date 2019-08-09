package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.navigation.NavigationListData;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/5/8 0008 23:29
 */
public interface NavigationContract {
    interface  NavigationView extends BaseView{
        void showNavigationListData(List<NavigationListData> navigationListData);
    }

    interface NavigationFragmentPresenter extends AbstractBasePresenter<NavigationView> {
        void getNavigationData();
    }
}
