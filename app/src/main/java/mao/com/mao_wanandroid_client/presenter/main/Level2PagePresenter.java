package mao.com.mao_wanandroid_client.presenter.main;


import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleListData;

/**
 * @author maoqitian
 * @Description: 知识体系 Level2Page Fragment Presenter
 * @date 2019/5/7 0007 11:47
 */
public class Level2PagePresenter extends RxBasePresenter<Level2PageContract.Level2PageView> implements Level2PageContract.KnowledgeLevel2PageFragmentPresenter {

    private DataClient mDataClient;
    private int curPage = 0;

    @Inject
    public Level2PagePresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(Level2PageContract.Level2PageView view) {
        super.attachView(view);
    }

    @Override
    public void getSuperChapterArticleData(int cid) {
        getSuperChapterArticle(true,curPage,cid);
    }

    private void getSuperChapterArticle(boolean isRefresh, int pageNum, int cid) {
        Observable<ResponseBody<HomeArticleListData>> knowledgeTreeDetailData = mDataClient.getKnowledgeTreeDetailData(pageNum, cid);
        knowledgeTreeDetailData.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<HomeArticleListData>() {
                    @Override
                    public void onSuccess(HomeArticleListData result) {
                        if(result.getDatas().size()!= 0){
                            curPage = result.getCurPage();
                            mView.ShowSuperChapterArticle(isRefresh,result);
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

    /*//收藏项目
    @Override
    public void addArticleCollect(int position, HomeArticleData homeArticleData) {
        Observable<ResponseBody<String>> responseBodyObservable = mDataClient.addCollectInsideListData(homeArticleData.getId());
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String result) {
                        homeArticleData.setCollect(true);
                        mView.showAddArticleCollectStatus(position,homeArticleData, MyApplication.getInstance().getApplicationContext().getString(R.string.collection_success));
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showAddArticleCollectStatus(position,null, MyApplication.getInstance().getApplicationContext().getString(R.string.collection_fail));
                    }
                });
    }
    //取消 收藏项目
    @Override
    public void cancelArticleCollect(int position, HomeArticleData homeArticleData) {
        Observable<ResponseBody<String>> responseBodyObservable = mDataClient.cancelCollectArticleListData(homeArticleData.getId());
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String result) {
                        homeArticleData.setCollect(false);
                        mView.showCancelArticleCollectStatus(position,homeArticleData, MyApplication.getInstance().getApplicationContext().getString(R.string.cancle_collection_success));
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showCancelArticleCollectStatus(position,null, MyApplication.getInstance().getApplicationContext().getString(R.string.cancle_collection_fail));
                    }
                });
    }*/
    //下拉刷新
    @Override
    public void getRefreshPage(int cid) {
        getSuperChapterArticle(true,0,cid);
    }
    //加载更多
    @Override
    public void getLoadMorePage(int cid) {
        getSuperChapterArticle(false,curPage,cid);
    }
}
