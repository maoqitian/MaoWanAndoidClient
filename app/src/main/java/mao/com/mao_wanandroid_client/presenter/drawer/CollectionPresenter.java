package mao.com.mao_wanandroid_client.presenter.drawer;

import javax.inject.Inject;

import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;

/**
 * @author maoqitian
 * @Description: 收藏 Presenter
 * @date 2019/7/26 0026 16:07
 */
public class CollectionPresenter extends RxBasePresenter<CollectionContract.CollectionView> implements CollectionContract.CollectionFragmentPresenter {

    private DataClient mDataClient;

    @Inject
    public CollectionPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(CollectionContract.CollectionView view) {
        super.attachView(view);
    }
}
