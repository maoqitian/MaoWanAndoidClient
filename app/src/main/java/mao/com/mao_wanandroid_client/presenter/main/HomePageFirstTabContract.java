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
public interface HomePageFirstTabContract {

    interface HomePageFirstTabView extends BaseView {

        /**
         * 显示首页banner 数据
         * @param bannerModelList
         */
        void showHomePageBanner(List<HomePageBannerModel> bannerModelList);

        void showHomeArticleList(HomeArticleListData homeArticleListData);

        void showHomeLatestProjectList(HomeArticleListData homeArticleListData);

        //自动登录成功
        void showAutoLoginSuccess();

        //自动登录失败
        void showAutoLoginFail();
    }


    interface HomeFirstTabFragmentPresenter extends AbstractBasePresenter<HomePageFirstTabView> {

        //获取首页第一个 item 数据
        void getHomeFirstPageData();


        void getHomeLatestProjectListDate();


    }
}
