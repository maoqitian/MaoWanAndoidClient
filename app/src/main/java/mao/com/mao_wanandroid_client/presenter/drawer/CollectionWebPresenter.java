package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.CollectionWebArticleEvent;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.ProgressObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;

/**
 * @author maoqitian
 * @Description: 收藏 网站 Presenter
 * @date 2019/7/26 0026 16:07
 */
public class CollectionWebPresenter extends RxBasePresenter<CollectionWebContract.CollectionWeb> implements CollectionWebContract.CollectWebFragmentPresenter {

    private DataClient mDataClient;

    @Inject
    public CollectionWebPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(CollectionWebContract.CollectionWeb view) {
        super.attachView(view);
        //网站收藏结果订阅
        addEventSubscribe(RxBus.getDefault().toFlowable(CollectionWebArticleEvent.class).subscribe(new Consumer<CollectionWebArticleEvent>() {
            @Override
            public void accept(CollectionWebArticleEvent collectionWebArticleEvent) throws Exception {
                 if(Constants.COLLECTION_WEB_TYPE.equals(collectionWebArticleEvent.getDialogType())){
                     if(collectionWebArticleEvent.getErrorCode() == 0){
                         if(collectionWebArticleEvent.isAdd()){
                             mView.showAddCollectWebSuccess(collectionWebArticleEvent.getWebBookMark(),
                                     MyApplication.getInstance().getString(R.string.collection_web_success));
                         }else {
                             mView.showUpdateCollectWebSuccess(collectionWebArticleEvent.getPosition(),
                                     collectionWebArticleEvent.getWebBookMark(),
                                     MyApplication.getInstance().getString(R.string.update_collection_web_success));
                         }
                     }else {
                         mView.showCollectionWebFailStatus(collectionWebArticleEvent.getmMsg());
                     }
                 }
            }
        }));
    }

    @Override
    public void getCollectWebData() {
        Observable<ResponseBody<List<WebBookMark>>> webBookMark = mDataClient.getWebBookMark();
        webBookMark.compose(RxSchedulers.observableIO2Main())
                   .subscribe(new BaseObserver<List<WebBookMark>>() {
                       @Override
                       public void onSuccess(List<WebBookMark> result) {
                           mView.showCollectionWebData(result);
                       }

                       @Override
                       public void onFailure(Throwable e, String errorMsg) {
                           mView.showError();
                       }
                   });
    }

    /**
     * 删除收藏 web
     */
    @Override
    public void getDeleteCollectWebData(Context context, int id, int position) {
        Observable<ResponseBody<String>> responseBodyObservable = mDataClient.deleteWebBookMark(id);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main(context))
                              .subscribe(new ProgressObserver<String>(context, context.getString(R.string.delete_collection_web)) {
                                  @Override
                                  public void onSuccess(String result) {
                                      mView.showDeleteCollectWebSuccess(position,context.getString(R.string.delete_collection_web));
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {
                                      mView.showCollectionWebFailStatus(errorMsg);
                                  }
                              });
    }
}
