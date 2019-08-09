package mao.com.mao_wanandroid_client.presenter.main;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.dao.SearchHistoryData;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;
import mao.com.mao_wanandroid_client.model.modelbean.search.HotKeyData;

/**
 * @author maoqitian
 * @Description 搜索
 * @Time 2019/5/8 0008 23:30
 */
public class SearchPagePresenter extends RxBasePresenter<SearchPageContract.SearchPageView>
        implements SearchPageContract.SearchFragmentPresenter {

    private DataClient mDataClient;

    private int curPage = 0;//当前页码 实际下拉加载更多获取数据 填入该页面即可

    private String mKeyWord;

    @Inject
    public SearchPagePresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(SearchPageContract.SearchPageView view) {
        super.attachView(view);
    }

    //普通搜索
    @Override
    public void getSearchKeyWordData(String keyWord) {
           //存储搜索历史数据
           mDataClient.addSearchHistoryData(keyWord);
           getSearchData(keyWord,0,false);
    }

    /**
     * 获取普通搜索数据
     * @param keyWord 关键词
     * @param pageNum 页码
     */
    private void getSearchData(String keyWord, int pageNum,boolean isLoadMore) {
        mKeyWord = keyWord;
        Observable<ResponseBody<HomeArticleListData>> searchKeyWordData = mDataClient.getSearchKeyWordData(pageNum, keyWord);
        searchKeyWordData.compose(RxSchedulers.observableIO2Main())
                         .subscribe(new BaseObserver<HomeArticleListData>() {
                             @Override
                             public void onSuccess(HomeArticleListData result) {
                                 if(result.getDatas().size()!= 0){
                                     curPage = result.getCurPage();
                                     mView.showSearchArticleList(result.getDatas(),isLoadMore);
                                 }else {
                                     mView.showLoadDataMessage(MyApplication.getInstance().getString(R.string.not_load_more_msg));
                                 }
                             }

                             @Override
                             public void onFailure(Throwable e, String errorMsg) {
                                     mView.showError();
                             }
                         });
    }

    //公众号号搜索
    @Override
    public void getWxArticleHistoryByKey(int id, String keyWord) {
        //存储搜索历史数据
        mDataClient.addSearchHistoryData(keyWord);
        getWxArticleSearchData(id,keyWord,1,false);
    }

    @Override
    public void getLoadMoreSearchData(int id) {
         if(id == 0){
             getSearchData(mKeyWord,curPage,true);
         }else {
             getWxArticleSearchData(id,mKeyWord,curPage+1,true);
         }
    }

    /**
     * 搜索热词 数据
     */
    @Override
    public void getHotKeyData() {
        Observable<ResponseBody<List<HotKeyData>>> responseBodyObservable = mDataClient.GetHotKeyData();
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                              .subscribe(new BaseObserver<List<HotKeyData>>() {
                                  @Override
                                  public void onSuccess(List<HotKeyData> result) {
                                       mView.showHotKeyListData(result);
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {

                                  }
                              });
    }
    //获取搜索历史记录
    @Override
    public void getSearchHistoryData() {
        List<SearchHistoryData> searchHistoryData = mDataClient.loadAllSearchHistoryData();
        if(searchHistoryData.size() > 0){
            //倒序排列返回历史数据
            Collections.reverse(searchHistoryData);
            mView.showSearchHistoryListData(searchHistoryData);
        }
    }
    //清理历史记录
    @Override
    public void getClearAllSearchHistoryData() {
        mDataClient.clearAllSearchHistoryData();
        mView.showClearAllSearchHistoryEvent();
    }

    /**
     * 微信公众号 搜索数据
     * @param id 公众号id
     * @param keyWord 关键词
     * @param pageNum 页码
     */
    private void getWxArticleSearchData(int id, String keyWord, int pageNum,boolean isLoadMore) {
        mKeyWord = keyWord;
        Observable<ResponseBody<HomeArticleListData>> wxArticleHistoryByKey = mDataClient.getWxArticleHistoryByKey(id, pageNum, keyWord);
        wxArticleHistoryByKey.compose(RxSchedulers.observableIO2Main())
                             .subscribe(new BaseObserver<HomeArticleListData>() {
                                 @Override
                                 public void onSuccess(HomeArticleListData result) {
                                     if(result.getDatas().size()!= 0){
                                         curPage = result.getCurPage();
                                         mView.showSearchArticleList(result.getDatas(),isLoadMore);
                                     }else {
                                         mView.showLoadDataMessage(MyApplication.getInstance().getString(R.string.not_load_more_msg));
                                     }
                                 }

                                 @Override
                                 public void onFailure(Throwable e, String errorMsg) {
                                         mView.showError();
                                 }
                             });
    }
}
