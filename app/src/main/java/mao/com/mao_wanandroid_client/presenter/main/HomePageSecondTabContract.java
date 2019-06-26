package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;

/**
 * @author maoqitian
 * @Description:
 * @date 2019/5/7 0007 11:47
 */
public interface HomePageSecondTabContract {

    interface HomePageSecondTabView extends BaseView {

        void showHomeLatestProjectList(boolean isRefreshData,HomeArticleListData homeArticleListData);

        //添加文章收藏成功
        void showAddArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg);
        //取消文章收藏成功
        void showCancelArticleCollectStatus(int position,HomeArticleData homeArticleData,String msg);
    }


    interface HomeSecondTabFragmentPresenter extends AbstractBasePresenter<HomePageSecondTabView> {
        /**
         * 获取最新项目数据
         * @param isRefreshData
         */
        void getHomeLatestProjectListDate(boolean isRefreshData);

        /**
         * 文章收藏
         * @param position 文章目前在 recycleview 位置
         * @param homeArticleData 文章信息
         */
        void addArticleCollect(int position,HomeArticleData homeArticleData);
        /**
         * 取消文章收藏
         * @param position 文章目前在 recycleview 位置
         * @param homeArticleData 文章信息
         */
        void cancelArticleCollect(int position,HomeArticleData homeArticleData);

        //下拉刷新页面
        void getRefreshPage();
        //上拉加载更多
        void getLoadMorePage();
    }
}
