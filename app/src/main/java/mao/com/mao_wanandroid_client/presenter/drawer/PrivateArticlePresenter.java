package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.model.http.DataClient;
import mao.com.mao_wanandroid_client.model.http.control.BaseObserver;
import mao.com.mao_wanandroid_client.model.http.control.RxSchedulers;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.model.modelbean.frienduser.CommonWebData;

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
}
