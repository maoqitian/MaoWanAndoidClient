package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.CollectionWebArticleEvent;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.ProgressObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.collect.CollectData;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/8/24 0024 15:09
 */
public class CollectionDialogPresenter extends RxBasePresenter<CollectionDialogContract.CollectionDialogView> implements CollectionDialogContract.CollectionDialogFragmentPresenter{

    private DataClient mDataClient;

    @Inject
    public CollectionDialogPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }


    @Override
    public void attachView(CollectionDialogContract.CollectionDialogView view) {
        super.attachView(view);
    }

    /**
     * 添加收藏 web
     */
    @Override
    public void getAddCollectWebData(Context context, String name, String link) {
        Observable<ResponseBody<WebBookMark>> responseBodyObservable = mDataClient.addWebBookMark(name, link);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main(context))
                .subscribe(new ProgressObserver<WebBookMark>(context,context.getString(R.string.collection_web)){
                    @Override
                    public void onSuccess(WebBookMark result) {
                        CollectionWebArticleEvent collectionWebArticleEvent = new CollectionWebArticleEvent(0,"");
                        collectionWebArticleEvent.setWebBookMark(result);
                        collectionWebArticleEvent.setDialogType(Constants.COLLECTION_WEB_TYPE);
                        collectionWebArticleEvent.setAdd(true);
                        RxBus.getDefault().post(collectionWebArticleEvent);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        RxBus.getDefault().post(new CollectionWebArticleEvent(-1,errorMsg));
                    }
                });
    }

    /**
     * 更新收藏 web
     */
    @Override
    public void getUpdateCollectWebData(Context context, WebBookMark webBookMark, int position) {
        Observable<ResponseBody<WebBookMark>> responseBodyObservable = mDataClient.updateWebBookMark(webBookMark.getId(), webBookMark.getName(), webBookMark.getLink());
        responseBodyObservable.compose(RxSchedulers.observableIO2Main(context))
                .subscribe(new ProgressObserver<WebBookMark>(context, context.getString(R.string.update_collection_web)) {
                    @Override
                    public void onSuccess(WebBookMark result) {
                        CollectionWebArticleEvent collectionWebArticleEvent = new CollectionWebArticleEvent(0,"");
                        collectionWebArticleEvent.setWebBookMark(result);
                        collectionWebArticleEvent.setPosition(position);
                        collectionWebArticleEvent.setDialogType(Constants.COLLECTION_WEB_TYPE);
                        collectionWebArticleEvent.setAdd(false);
                        RxBus.getDefault().post(collectionWebArticleEvent);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        RxBus.getDefault().post(new CollectionWebArticleEvent(-1,errorMsg));
                    }
                });
    }

    @Override
    public void getAddCollectArticleData(Context context, String name, String author, String link) {
        Observable<ResponseBody<CollectData>> responseBodyObservable = mDataClient.addCollectOutsideListData(name, author, link);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main(context))
                              .subscribe(new ProgressObserver<CollectData>(context, context.getString(R.string.collection_article)) {
                                  @Override
                                  public void onSuccess(CollectData result) {
                                      CollectionWebArticleEvent collectionWebArticleEvent = new CollectionWebArticleEvent(0,"");
                                      collectionWebArticleEvent.setCollectData(result);
                                      collectionWebArticleEvent.setDialogType(Constants.COLLECTION_ARTICLE_TYPE);
                                      collectionWebArticleEvent.setAdd(false);
                                      RxBus.getDefault().post(collectionWebArticleEvent);
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {
                                      RxBus.getDefault().post(new CollectionWebArticleEvent(-1,errorMsg));
                                  }
                              });
    }
}
