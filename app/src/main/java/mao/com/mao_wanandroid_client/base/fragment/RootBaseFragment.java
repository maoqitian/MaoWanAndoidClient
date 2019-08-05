package mao.com.mao_wanandroid_client.base.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.widget.LoadingView;

/**
 * @author maoqitian
 * @Description 每個Fragment 的基类
 * @Time 2018/12/14 0014 22:48
 */
public abstract class RootBaseFragment <T extends AbstractBasePresenter>extends BaseFragment <T> {

    //默认为NORMAL状态
    protected int currentState = STATE_NORMAL;

    private LoadingView mLoadingView;

    private ViewGroup mBaseView;
    private ViewGroup normalView;
    private View loadingView;
    private View errorView;
    //重复加载
    private TextView tvReload;
    
    @Override
    protected void initEventAndData() {
        if(getView() == null){
            //如果root view 不存在
            return;
        }
        normalView = getView().findViewById(R.id.view_base_normal);
        if (normalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'normalView'.");
        }
        if (!(normalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "normalView's ParentView should be a ViewGroup.");
        }
        mBaseView = (ViewGroup) normalView.getParent();
        LayoutInflater.from(_mActivity).inflate(R.layout.view_loading, mBaseView,false);
        LayoutInflater.from(_mActivity).inflate(R.layout.view_error,mBaseView,false);
        /*View.inflate(_mActivity, R.layout.view_loading, mBaseView);
        View.inflate(_mActivity,R.layout.view_error,mBaseView);*/
        loadingView = mBaseView.findViewById(R.id.loading_view_container);
        mLoadingView = loadingView.findViewById(R.id.view_loading);
        errorView = mBaseView.findViewById(R.id.view_error);
        tvReload = errorView.findViewById(R.id.tv_reload);
        tvReload.setOnClickListener(v -> reload());
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        normalView.setVisibility(View.VISIBLE);
    }


    @Override
    public void showNormal() {
        if(currentState == STATE_NORMAL) return;
        hideCurrentView();
        currentState = STATE_NORMAL;
        normalView.setVisibility(View.VISIBLE);
        Log.e("毛麒添","调用showNormal");
    }

    @Override
    public void showLoading() {
        if(currentState == STATE_LOADING) return;
        hideCurrentView();
        loadingView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.startFallAnimator();
        currentState = STATE_LOADING;
        Log.e("毛麒添","调用showLoading");
    }

    @Override
    public void showError() {
        if(currentState == STATE_ERROR) return;
        hideCurrentView();
        errorView.setVisibility(View.VISIBLE);
        currentState = STATE_ERROR;
        Log.e("毛麒添","调用showError");
    }

    /**
     * 隐藏当前View
     */
    private void hideCurrentView() {
        switch (currentState){
            case STATE_NORMAL:
                    normalView.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                    if(loadingView!=null){
                        //停止动画
                       mLoadingView.setVisibility(View.GONE);
                       loadingView.setVisibility(View.GONE);
                       //移除加载loading view 防止重复出现在页面上
                       mBaseView.removeView(loadingView);
                    }
                break;
            case STATE_ERROR:
                    errorView.setVisibility(View.GONE);
                    //mBaseView.removeView(errorView);
                break;
        }
    }
}
