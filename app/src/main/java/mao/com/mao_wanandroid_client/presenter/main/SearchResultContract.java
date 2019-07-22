package mao.com.mao_wanandroid_client.presenter.main;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;

/**
 * @author maoqitian
 * @Description: SearchResultActivity Contract
 * @date 2019/7/17 0017 11:24
 */
public interface SearchResultContract {

    interface SearchResultView extends BaseView {

    }

    interface SearchResultActivityPresenter extends AbstractBasePresenter<SearchResultView> {

    }
}
