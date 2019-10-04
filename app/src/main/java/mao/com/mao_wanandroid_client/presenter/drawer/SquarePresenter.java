package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.BaseListData;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;


/**
 * @author maoqitian
 * @Description: 广场 Presenter
 * @date 2019/7/26 0026 16:07
 */
public class SquarePresenter extends RxBasePresenter<SquareContract.SquareView> implements SquareContract.SquareFragmentPresenter {

    private DataClient mDataClient;


    @Inject
    public SquarePresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(SquareContract.SquareView view) {
        super.attachView(view);
    }

    @Override
    public void getSquareArticleList() {

        getSquareArticleListData(false);
    }

    private void getSquareArticleListData(boolean isLoadData) {

        Observable<ResponseBody<BaseListData<HomeArticleData>>> userArticleList = mDataClient.getUserArticleList(0);
        userArticleList.compose(RxSchedulers.observableIO2Main())
                       .subscribe(new BaseObserver<BaseListData<HomeArticleData>>() {
                           @Override
                           public void onSuccess(BaseListData<HomeArticleData> result) {
                               mView.showSquareArticleData(isLoadData,result.getDatas());
                           }

                           @Override
                           public void onFailure(Throwable e, String errorMsg) {

                           }
                       });

    }
}
