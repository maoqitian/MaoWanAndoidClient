package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
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

                       }
                   });
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
                                        mView.showAddCollectWebSuccess(result,context.getString(R.string.collection_web));
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {
                                      mView.showAddCollectWebFail(errorMsg);
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
                                       mView.showUpdateCollectWebSuccess(position,result,context.getString(R.string.update_collection_web));
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {
                                       mView.showUpdateCollectWebFail(errorMsg);
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
                                      mView.showDeleteCollectWebFail(errorMsg);
                                  }
                              });
    }
}
