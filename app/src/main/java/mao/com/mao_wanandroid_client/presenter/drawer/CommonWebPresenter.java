package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.frienduser.CommonWebData;

/**
 * @author maoqitian
 * @Description: 收藏 Presenter
 * @date 2019/7/26 0026 16:07
 */
public class CommonWebPresenter extends RxBasePresenter<CommonWebContract.CommonWebView> implements CommonWebContract.CommonFragmentPresenter {

    private DataClient mDataClient;

    @Inject
    public CommonWebPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(CommonWebContract.CommonWebView view) {
        super.attachView(view);
        mDataClient.GetFriendUseWebData();
    }


    @Override
    public void getCommonWebData() {
        Observable<ResponseBody<List<CommonWebData>>> responseBodyObservable = mDataClient.GetFriendUseWebData();
        responseBodyObservable.compose(RxSchedulers.observableIO2Main())
                              .subscribe(new BaseObserver<List<CommonWebData>>() {
                                  @Override
                                  public void onSuccess(List<CommonWebData> result) {
                                       mView.showCommonWebData(result);
                                  }

                                  @Override
                                  public void onFailure(Throwable e, String errorMsg) {

                                  }
                              });
    }
}
