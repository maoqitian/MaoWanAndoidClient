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

/**
 * @author maoqitian
 * @Description: 我的收藏页面 Presenter
 * @date 2019/7/26 0026 16:07
 */
public class CollectionPagePresenter extends RxBasePresenter<CollectionPageContract.CollectionPageView> implements CollectionPageContract.CollectionPageFragmentPresenter {

    private DataClient mDataClient;

    @Inject
    public CollectionPagePresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }


    @Override
    public void attachView(CollectionPageContract.CollectionPageView view) {
        super.attachView(view);
    }
}
