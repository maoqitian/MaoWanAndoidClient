package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;

/**
 * @author maoqitian
 * @Description:
 * @date 2019/5/7 0007 11:47
 */
public interface HomePageSecondTabContract {

    interface HomePageSecondTabView extends BaseView {

        void showHomeLatestProjectList(HomeArticleListData homeArticleListData);
    }


    interface HomeSecondTabFragmentPresenter extends AbstractBasePresenter<HomePageSecondTabView> {
        void getHomeLatestProjectListDate();

    }
}
