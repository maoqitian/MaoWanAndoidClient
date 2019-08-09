package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.navigation.NavigationListData;

/**
 * @author maoqitian
 * @Description 导航
 * @Time 2019/5/8 0008 23:30
 */
public class NavigationPresenter
        extends RxBasePresenter<NavigationContract.NavigationView>
        implements NavigationContract.NavigationFragmentPresenter {

    private DataClient mDataClient;
    @Inject
    public NavigationPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(NavigationContract.NavigationView view) {
        super.attachView(view);
    }


    @Override
    public void getNavigationData() {
        Observable<ResponseBody<List<NavigationListData>>> navigationListData = mDataClient.getNavigationListData();
        navigationListData.compose(RxSchedulers.observableIO2Main())
                          .subscribe(new BaseObserver<List<NavigationListData>>() {
                              @Override
                              public void onSuccess(List<NavigationListData> result) {
                                  mView.showNavigationListData(result);
                              }

                              @Override
                              public void onFailure(Throwable e, String errorMsg) {
                                  mView.showError();
                              }
                          });
    }
}
