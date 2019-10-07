package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.CollectionWebArticleEvent;
import mao.com.mao_wanandroid_client.compoent.event.ShareArticleEvent;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.ProgressObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.collect.CollectData;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/8/24 0024 15:09
 */
public class ArticleShareDialogPresenter extends RxBasePresenter<ArticleShareDialogContract.ArticleShareDialogView> implements ArticleShareDialogContract.ArticleShareDialogFragmentPresenter{

    private DataClient mDataClient;

    @Inject
    public ArticleShareDialogPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }


    @Override
    public void attachView(ArticleShareDialogContract.ArticleShareDialogView view) {
        super.attachView(view);
    }

    @Override
    public void getAddCollectWebData(Context context, String title, String link) {

        Observable<ResponseBody<String>> userArticleShare = mDataClient.getUserArticleShare(title, link);
        userArticleShare.compose(RxSchedulers.observableIO2Main(context))
                        .subscribe(new ProgressObserver<String>(context, "正在分享") {
                            @Override
                            public void onSuccess(String result) {
                                RxBus.getDefault().post(new ShareArticleEvent(true,"分享成功"));
                                mView.showConfirmShareStatus();
                            }

                            @Override
                            public void onFailure(Throwable e, String errorMsg) {
                                RxBus.getDefault().post(new ShareArticleEvent(false,errorMsg));
                                mView.showConfirmShareStatus();
                            }
                        });
    }
}
