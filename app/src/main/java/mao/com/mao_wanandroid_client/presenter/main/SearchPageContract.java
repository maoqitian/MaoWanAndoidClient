package mao.com.mao_wanandroid_client.presenter.main;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description: SearchFragment Contract
 * @date 2019/7/17 0017 11:24
 */
public interface SearchPageContract {

    interface SearchPageView extends BaseView {

    }

    interface SearchFragmentPresenter extends AbstractBasePresenter<SearchPageView> {

    }
}
