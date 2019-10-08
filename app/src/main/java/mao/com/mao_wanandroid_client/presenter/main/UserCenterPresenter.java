package mao.com.mao_wanandroid_client.presenter.main;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.BaseListData;
import mao.com.mao_wanandroid_client.model.modelbean.BaseMultipleData;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;

/**
 * @author maoqitian
 * @Description 用户中心
 * @Time 2019/5/8 0008 23:30
 */
public class UserCenterPresenter extends RxBasePresenter<UserCenterContract.UserCenterView>
        implements UserCenterContract.UserCenterActivityPresenter {

    private DataClient mDataClient;
    @Inject
    public UserCenterPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(UserCenterContract.UserCenterView view) {
        super.attachView(view);
    }


    @Override
    public void getUserShareArticlesData(int id, int pageNum) {
        Observable<ResponseBody<BaseMultipleData<RankData, BaseListData<HomeArticleData>>>> userShareArticlesData = mDataClient.getUserShareArticlesData(id, pageNum);
        userShareArticlesData.compose(RxSchedulers.observableIO2Main())
                             .subscribe(new BaseObserver<BaseMultipleData<RankData, BaseListData<HomeArticleData>>>() {
                                 @Override
                                 public void onSuccess(BaseMultipleData<RankData, BaseListData<HomeArticleData>> result) {
                                     Log.e("毛麒天 个人中心数据",result.toString());
                                     mView.showCoinAndRank(result.getData1());

                                 }

                                 @Override
                                 public void onFailure(Throwable e, String errorMsg) {

                                 }
                             });
    }
}
