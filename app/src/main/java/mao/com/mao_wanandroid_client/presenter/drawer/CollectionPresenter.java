package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.ProgressObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.collect.CollectListData;

/**
 * @author maoqitian
 * @Description: 收藏 Presenter
 * @date 2019/7/26 0026 16:07
 */
public class CollectionPresenter extends RxBasePresenter<CollectionContract.CollectionView> implements CollectionContract.CollectionFragmentPresenter {

    private DataClient mDataClient;
    //当前页码 实际下拉加载更多获取数据 填入该页面即可
    private int curPage = 0;
    @Inject
    public CollectionPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(CollectionContract.CollectionView view) {
        super.attachView(view);
    }

    @Override
    public void getCollectListData() {
        getCollectList(0,false);
    }

    @Override
    public void getLoadCollectListData() {
        getCollectList(curPage,true);
    }

    //收藏页面取消收藏
    @Override
    public void getCancelCollectArticleData(Context context,int articleId, int originId,int position) {
        Observable<ResponseBody<String>> responseBodyObservable = mDataClient.cancelCollectArticlePageData(articleId, originId);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                              .subscribe(new ProgressObserver<String>(context, context.getString(R.string.delete_collection_text)) {
                                  @Override
                                  public void onSuccess(String result) {
                                      mView.showCancelCollectArticleSuccess(position,context.getString(R.string.cancle_collection_success));
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {
                                      mView.showCancelCollectArticleFail(context.getString(R.string.cancle_collection_fail));
                                  }
                              });
    }

    private void getCollectList(int pageNum,boolean isRefresh) {
        Observable<ResponseBody<CollectListData>> collectListData = mDataClient.getCollectListData(pageNum);
        collectListData.compose(RxSchedulers.observableIO2Main())
                       .subscribe(new BaseObserver<CollectListData>() {
                           @Override
                           public void onSuccess(CollectListData result) {
                               if(result.getDatas().size() != 0){
                                   curPage = result.getCurPage();
                                   mView.showCollectListData(result.getDatas(),isRefresh);
                               }else {
                                   //哥这回真没了
                                   mView.showLoadDataMessage(MyApplication.getInstance().getString(R.string.not_load_more_msg));

                               }
                           }

                           @Override
                           public void onFailure(Throwable e, String errorMsg) {

                           }
                       });
    }




}
