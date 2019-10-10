package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.BaseListData;
import mao.com.mao_wanandroid_client.model.modelbean.BaseMultipleData;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.frienduser.CommonWebData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;

/**
 * @author maoqitian
 * @Description: 自己分享文章 Presenter
 * @date 2019/7/26 0026 16:07
 */
public class PrivateArticlePresenter extends RxBasePresenter<PrivateArticleContract.PrivateArticleView> implements PrivateArticleContract.PrivateArticleFragmentPresenter {

    private DataClient mDataClient;

    @Inject
    public PrivateArticlePresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }


    @Override
    public void attachView(PrivateArticleContract.PrivateArticleView view) {
        super.attachView(view);
    }

    @Override
    public void getPrivateArticleData() {

        getPrivateArticleDataList(0);
    }

    //广场用户信息获取
    @Override
    public void getUserShareArticlesData(int userId) {

        getUserShareArticlesDataList(userId,1);


    }

    private void getUserShareArticlesDataList(int userId, int pageNum) {
        Observable<ResponseBody<BaseMultipleData<RankData, BaseListData<HomeArticleData>>>> userShareArticlesData = mDataClient.getUserShareArticlesData(userId, pageNum);
        userShareArticlesData.compose(RxSchedulers.observableIO2Main())
                             .subscribe(new BaseObserver<BaseMultipleData<RankData, BaseListData<HomeArticleData>>>() {
                                 @Override
                                 public void onSuccess(BaseMultipleData<RankData, BaseListData<HomeArticleData>> result) {
                                     mView.showPrivateArticleData(result.getData2().getDatas());
                                 }

                                 @Override
                                 public void onFailure(Throwable e, String errorMsg) {

                                 }
                             });

    }

    //个人分享文章列表
    private void getPrivateArticleDataList(int pageNum) {
        Observable<ResponseBody<BaseMultipleData<RankData, BaseListData<HomeArticleData>>>> privateShareArticlesData = mDataClient.getPrivateShareArticlesData(pageNum);
        privateShareArticlesData.compose(RxSchedulers.observableIO2Main())
                                .subscribe(new BaseObserver<BaseMultipleData<RankData, BaseListData<HomeArticleData>>>() {
                                    @Override
                                    public void onSuccess(BaseMultipleData<RankData, BaseListData<HomeArticleData>> result) {
                                        mView.showPrivateArticleData(result.getData2().getDatas());
                                    }

                                    @Override
                                    public void onFailure(Throwable e, String errorMsg) {
                                        mView.showErrorMsg(errorMsg);
                                    }
                                });
    }


}
