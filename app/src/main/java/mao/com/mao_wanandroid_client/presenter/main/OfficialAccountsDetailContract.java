package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;

/**
 * @author maoqitian
 * @Description: 公众号详情页
 * @date 2019/7/15 0015 17:48
 */
public interface OfficialAccountsDetailContract {

     interface OfficialAccountsDetailView extends BaseView {
           void showWxArticleHistoryData(List<HomeArticleData> homeArticleDataList,boolean isRefresh);
    }

    interface OfficialAccountsDetailActivityPresenter extends AbstractBasePresenter<OfficialAccountsDetailView> {
        void getWxArticleHistory(int id);
        //下拉刷新页面
        void getRefreshPage(int id);
        //上拉加载更多
        void getLoadMorePage(int id);
    }


}
