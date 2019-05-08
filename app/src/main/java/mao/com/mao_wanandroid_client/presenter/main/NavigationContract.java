package mao.com.mao_wanandroid_client.presenter.main;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/5/8 0008 23:29
 */
public interface NavigationContract {
    interface  NavigationView extends BaseView{

    }

    interface NavigationFragmentPresenter extends AbstractBasePresenter<NavigationView> {
    }
}
