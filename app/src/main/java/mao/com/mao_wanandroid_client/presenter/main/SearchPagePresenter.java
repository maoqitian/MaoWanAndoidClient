package mao.com.mao_wanandroid_client.presenter.main;

import java.security.Key;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.core.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.home.HomeArticleListData;

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

                             }
                         });
    }

    //公众号号搜索
    @Override
    public void getWxArticleHistoryByKey(int id, String keyWord) {
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

                                 }
                             });
    }
}
