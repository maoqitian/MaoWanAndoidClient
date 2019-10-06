package mao.com.mao_wanandroid_client.presenter.drawer;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.CollectionWebArticleEvent;
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

    }
}
