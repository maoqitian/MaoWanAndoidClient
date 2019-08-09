package mao.com.mao_wanandroid_client.presenter.main;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;

/**
 * @author maoqitian
 * @Description Level2Page Fragment
 * @Time 2019/6/30 0030 14:06
 */
public interface Level2PageContract {

    interface Level2PageView extends BaseView {

        void ShowSuperChapterArticle(boolean isRefresh, HomeArticleListData homeArticleListData);

        //添加文章收藏成功
        void showAddArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg);
        //取消文章收藏成功
        void showCancelArticleCollectStatus(int position,HomeArticleData homeArticleData,String msg);
        void showLoadDataMessage(String msg);
    }

    interface KnowledgeLevel2PageFragmentPresenter extends AbstractBasePresenter<Level2PageContract.Level2PageView> {

        void getSuperChapterArticleData(int cid);
        //下拉刷新页面
        void getRefreshPage(int cid);
        //上拉加载更多
        void getLoadMorePage(int cid);
    }

}
