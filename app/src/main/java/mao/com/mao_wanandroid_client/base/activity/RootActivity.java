package mao.com.mao_wanandroid_client.base.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.widget.LoadingView;

/**
 * @author maoqitian
 * @Description
 * @Time 2018/10/25 0025 16:52
 */
public abstract class RootActivity<T extends RxBasePresenter> extends BaseActivity<T> implements View.OnClickListener {


    //默认为NORMAL状态
    private int currentState = STATE_NORMAL;

    private LoadingView mLoadingView;

    private ViewGroup mBaseView;
    private ViewGroup normalView;
    private View loadingView;
    private View errorView;
    //重复加载
    private TextView tvReload;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        normalView = findViewById(R.id.view_base_normal);
        if (normalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'normalView'.");
        }
        if (!(normalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "normalView's ParentView should be a ViewGroup.");
        }
        mBaseView = (ViewGroup) normalView.getParent();
        View.inflate(this,R.layout.view_loading,mBaseView);
        View.inflate(this,R.layout.view_error,mBaseView);
        loadingView = mBaseView.findViewById(R.id.loading_view_container);
        mLoadingView = loadingView.findViewById(R.id.view_loading);
        errorView = mBaseView.findViewById(R.id.view_error);
        tvReload = errorView.findViewById(R.id.tv_reload);
        tvReload.setOnClickListener(this);
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
    }

    @Override
    public void showLoading() {
        if(currentState == STATE_LOADING) return;
        hideCurrentView();
        currentState = STATE_LOADING;
        loadingView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showError() {
        if(currentState == STATE_ERROR) return;
        hideCurrentView();
        currentState = STATE_ERROR;
        errorView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏当前View
     */
    private void hideCurrentView() {
       switch (currentState){
           case STATE_NORMAL:
               if(loadingView != null){
                   normalView.setVisibility(View.GONE);
               }
               break;
           case STATE_LOADING:
               if(loadingView != null){
                   loadingView.setVisibility(View.GONE);
                   mLoadingView.setVisibility(View.GONE);
               }
               break;
           case STATE_ERROR:
               if(errorView != null){
                   errorView.setVisibility(View.GONE);
               }
               break;
       }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_reload:
                reload();
                break;
             default:
                 break;
        }
    }
}
