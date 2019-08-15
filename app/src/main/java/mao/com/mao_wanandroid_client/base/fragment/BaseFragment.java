package mao.com.mao_wanandroid_client.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;

/**
 * @author maoqitian
 * @Description MVP Fragment 基类
 * @Time 2018/12/14 0014 22:47
 */
public abstract class BaseFragment <T extends AbstractBasePresenter> extends SimpleFragment implements BaseView {

    //Presenter 对象注入
    @Inject
    protected T mPresenter;

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mPresenter != null){
            Log.e("毛麒添","BaseFragment mPresenter 不为空" + mPresenter.getClass());
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mPresenter != null){
            mPresenter = null;
        }
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showErrorMsg(String errorMsg) {
        Toast.makeText(getContext(),errorMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void useThemeMode(int mode) {

    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void reload() {
        Toast.makeText(getContext(),"点击重新加载",Toast.LENGTH_SHORT).show();
    }
    //收藏或者取消收藏
    protected void addOrCancelCollect(int position,HomeArticleData homeArticleData) {
        if(!mPresenter.getLoginStatus()){
            StartDetailPage.start(_mActivity,null, Constants.PAGE_LOGIN,Constants.ACTION_LOGIN_ACTIVITY);
            return;
        }
        if(!homeArticleData.isCollect()){
            //收藏
            mPresenter.addArticleCollect(position,homeArticleData);
        }else {
            //取消收藏
            mPresenter.cancelArticleCollect(position,homeArticleData);
        }
    }

    @Override
    public void showAddArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg) {

    }

    @Override
    public void showCancelArticleCollectStatus(int position, HomeArticleData homeArticleData, String msg) {

    }
}
