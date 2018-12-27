package mao.com.mao_wanandroid_client.base.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.presenter.RxBasePresenter;
import mao.com.mao_wanandroid_client.widget.LoadingView;

/**
 * @author maoqitian
 * @Description
 * @Time 2018/10/25 0025 16:52
 */
public class RootActivity<T extends RxBasePresenter> extends BaseActivity<T> {


    //默认为NORMAL状态
    private int currentState = STATE_NORMAL;

    private LoadingView mLoadingView;

    private ViewGroup normalView;
    private View loadingView;
    private View errorView;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        normalView = findViewById(R.id.view_base_normal);
        if (normalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'normalView'.");
        }

    }


    @Override
    public void showNormal() {
        super.showNormal();

    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void showError() {
        super.showError();
    }

}
