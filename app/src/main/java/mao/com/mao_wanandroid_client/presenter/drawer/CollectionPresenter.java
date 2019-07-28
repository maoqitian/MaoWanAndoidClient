package mao.com.mao_wanandroid_client.presenter.drawer;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.core.http.DataClient;
import mao.com.mao_wanandroid_client.core.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.core.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.ResponseBody;
import mao.com.mao_wanandroid_client.model.collect.CollectListData;

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
