package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.dao.SearchHistoryData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.search.HotKeyData;

/**
 * @author maoqitian
 * @Description: SearchFragment Contract
 * @date 2019/7/17 0017 11:24
 */
public interface SearchPageContract {

    interface SearchPageView extends BaseView {

        void showSearchArticleList(List<HomeArticleData> homeArticleDataList,boolean isLoadMore);
        //搜索加载更多为空调用
        void showLoadDataMessage(String msg);
        //搜索热词
        void showHotKeyListData(List<HotKeyData> hotKeyDataList);
        //搜索历史
        void showSearchHistoryListData(List<SearchHistoryData> searchHistoryData);
        //删除搜索历史触发
        void showClearAllSearchHistoryEvent();
    }

    interface SearchFragmentPresenter extends AbstractBasePresenter<SearchPageView> {
        //普通搜索
        void getSearchKeyWordData(String keyWord);

        //公众号搜索
        void getWxArticleHistoryByKey(int id,String keyWord);

        void getLoadMoreSearchData(int id);

        void getHotKeyData();

        void getSearchHistoryData();

        void getClearAllSearchHistoryData();
    }
}
