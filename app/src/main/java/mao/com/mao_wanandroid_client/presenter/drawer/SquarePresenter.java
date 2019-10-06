package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.application.MyApplication;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.ShareArticleEvent;
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
    //当前页码 实际下拉加载更多获取数据 填入该页面即可
    private int curPage = 0;

    @Inject
    public SquarePresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(SquareContract.SquareView view) {
        super.attachView(view);

        addEventSubscribe(RxBus.getDefault().toFlowable(ShareArticleEvent.class).subscribe(new Consumer<ShareArticleEvent>() {
            @Override
            public void accept(ShareArticleEvent shareArticleEvent) throws Exception {
                if(shareArticleEvent.ismIsShareSuccess()){
                    //分享成功 刷新广场页面数据
                    getSquareArticleListData(false,0);
                    mView.showErrorMsg(shareArticleEvent.getmMsg());
                }else{
                    mView.showErrorMsg(shareArticleEvent.getmMsg());
                }
            }
        }));
    }

    @Override
    public void getSquareArticleList() {

        getSquareArticleListData(false,0);
    }

    @Override
    public void getLoadSquareArticleListData() {
        getSquareArticleListData(true,curPage);
    }

    private void getSquareArticleListData(boolean isLoadData,int pageNum) {

        Observable<ResponseBody<BaseListData<HomeArticleData>>> userArticleList = mDataClient.getUserArticleList(pageNum);
        userArticleList.compose(RxSchedulers.observableIO2Main())
                       .subscribe(new BaseObserver<BaseListData<HomeArticleData>>() {
                           @Override
                           public void onSuccess(BaseListData<HomeArticleData> result) {
                               if(result.getDatas().size()> 0){
                                   curPage = result.getCurPage();
                                   mView.showSquareArticleData(isLoadData,result.getDatas());
                               }else {
                                   //哥这回真没了
                                   mView.showErrorMsg(MyApplication.getInstance().getString(R.string.not_load_more_msg));
                               }
                           }

                           @Override
                           public void onFailure(Throwable e, String errorMsg) {
                               mView.showError();
                               mView.showErrorMsg(errorMsg);
                           }
                       });

    }
}
