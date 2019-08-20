package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;

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

    }
}
