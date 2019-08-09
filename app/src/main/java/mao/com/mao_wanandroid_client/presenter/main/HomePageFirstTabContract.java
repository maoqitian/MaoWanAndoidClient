package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;

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
        void showHomePageBanner(boolean isRefreshData,List<HomePageBannerModel> bannerModelList);

        void showTopArticleList(List<HomeArticleData> homeArticleDataList);

        void showHomeArticleList(boolean isRefreshData,HomeArticleListData homeArticleListData);

        //void showHomeLatestProjectList(HomeArticleListData homeArticleListData);

        //自动登录成功
        void showAutoLoginSuccess();

        //自动登录失败
        void showAutoLoginFail(String errorMsg);


    }


    interface HomeFirstTabFragmentPresenter extends AbstractBasePresenter<HomePageFirstTabView> {



        /**
         * 获取首页第一个 item 数据 (最新博文)
         * @param isRefreshData 是否刷新数据 true 刷新  false 正常加载
         */
        void getHomeFirstPageData(boolean isRefreshData);


        //void getHomeLatestProjectListDate();

        //下拉刷新页面
        void getRefreshPage();
        //上拉加载更多
        void getLoadMorePage();
    }
}
