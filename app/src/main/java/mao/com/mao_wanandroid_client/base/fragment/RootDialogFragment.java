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
 * @Description: DialogFragment 的基类,让其具有 loading error 功能(在 DialogFragment 中不生效 有待研究)
 * @date 2019/8/6 0006 15:41
 */
public abstract class RootDialogFragment <T extends AbstractBasePresenter>extends BaseDialogFragment <T>{

    //默认为NORMAL状态
    protected int currentState = STATE_NORMAL;

    private LoadingView mLoadingView;

    private ViewGroup mBaseView;
    private ViewGroup inflateView;
    private View loadingView;
    private View errorView;
    //重复加载
    private TextView tvReload;

    @Override
    protected void initLoadingView() {
        if(getView() == null){
            //如果root view 不存在
            return;
        }
        inflateView = getView().findViewById(R.id.inflate_view);
        if (inflateView == null) {
            throw new IllegalStateException(
                    "The subclass of RootDialogFragment must contain a View named 'inflateView'.");
        }
        if (!(inflateView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "inflateView's ParentView should be a ViewGroup.");
        }
        mBaseView = (ViewGroup) inflateView.getParent();
        inflateView.setVisibility(View.VISIBLE);
    }


    public void addLoadingView(){
        //加入errorView 到 mBaseView 并返回 root 布局为 mBaseView
        if(loadingView!=null){
            mBaseView.removeView(loadingView);
        }
        LayoutInflater.from(getActivity()).inflate(R.layout.view_loading, mBaseView,true);
        loadingView = mBaseView.findViewById(R.id.loading_view_container);
        mLoadingView = loadingView.findViewById(R.id.view_loading);
    }

    public void addErrorView(){
        //加入loadingView 到 mBaseView 并返回 root 布局为 mBaseView
        if(errorView!=null){
            mBaseView.removeView(errorView);
        }
        LayoutInflater.from(getActivity()).inflate(R.layout.view_error,mBaseView,true);
        errorView = mBaseView.findViewById(R.id.view_error);
        tvReload = errorView.findViewById(R.id.tv_reload);
        tvReload.setOnClickListener(v -> reload());
    }

    @Override
    public void showNormal() {
        if(currentState == STATE_NORMAL) return;
        hideCurrentView();
        currentState = STATE_NORMAL;
        //inflateView.setVisibility(View.VISIBLE);
        Log.e("毛麒添","调用showNormal");
    }

    @Override
    public void showLoading() {
        if(currentState == STATE_LOADING) return;
        addLoadingView();
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
        addErrorView();
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
                //inflateView.setVisibility(View.GONE);
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
                if (errorView!=null){
                    errorView.setVisibility(View.GONE);
                    mBaseView.removeView(errorView);
                }
                break;
        }
    }

}
