package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.ShareArticleEvent;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.ProgressObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.BaseListData;
import mao.com.mao_wanandroid_client.model.modelbean.BaseMultipleData;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
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
        addEventSubscribe(RxBus.getDefault().toFlowable(ShareArticleEvent.class).subscribe(new Consumer<ShareArticleEvent>() {
            @Override
            public void accept(ShareArticleEvent shareArticleEvent) throws Exception {
                if(shareArticleEvent.ismIsShareSuccess()){
                    //分享成功 刷新页面数据
                    getPrivateArticleDataList(0);
                    mView.showErrorMsg(shareArticleEvent.getmMsg());
                }else{
                    mView.showErrorMsg(shareArticleEvent.getmMsg());
                }
            }
        }));
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

    //删除自己分享的文章
    @Override
    public void getUserArticleDelete(Context context,int id) {
        Observable<ResponseBody<String>> userArticleDelete = mDataClient.getUserArticleDelete(id);
        userArticleDelete.compose(RxSchedulers.observableIO2Main(context))
                         .subscribe(new ProgressObserver<String>(context, context.getString(R.string.delete_article_text)) {
                             @Override
                             public void onSuccess(String result) {
                                 //分享成功 刷新页面数据
                                 getPrivateArticleDataList(0);
                                 mView.showErrorMsg(context.getString(R.string.delete_article_success_text));
                             }

                             @Override
                             public void onFailure(Throwable e, String errorMsg) {
                                 mView.showErrorMsg(errorMsg);

                             }
                         });
    }

    //用户分享文章列表
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
                                     mView.showErrorMsg(errorMsg);
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
