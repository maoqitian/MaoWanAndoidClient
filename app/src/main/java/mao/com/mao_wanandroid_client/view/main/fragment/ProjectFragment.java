package mao.com.mao_wanandroid_client.view.main.fragment;

import android.util.Log;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.presenter.main.ProjectContract;
import mao.com.mao_wanandroid_client.presenter.main.ProjectPresenter;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/5/8 0008 23:39
 */
public class ProjectFragment extends RootBaseFragment <ProjectPresenter> implements ProjectContract.ProjectView {

    @Override
    protected int getLayoutId() {
        return R.layout.project_fragment_layout;
    }
    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Log.e("毛麒添","当前页面状态"+currentState);
    }
}
