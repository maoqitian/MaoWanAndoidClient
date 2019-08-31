package mao.com.mao_wanandroid_client.view.main.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.project.ProjectClassifyData;
import mao.com.mao_wanandroid_client.presenter.main.ProjectContract;
import mao.com.mao_wanandroid_client.presenter.main.ProjectPresenter;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeTabPageAdapter;

/**
 * @author maoqitian
 * @Description 项目 (加载失败页面特殊处理)
 * @Time 2019/5/8 0008 23:39
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.ProjectView {

    @BindView(R.id.project_tab)
    TabLayout mProjectTb;
    @BindView(R.id.view_pager_project)
    ViewPager mProjectVP;

    @BindView(R.id.view_error)
    ConstraintLayout mErrorView;
    @BindView(R.id.tv_reload)
    TextView mTvReload;

    List<ProjectClassifyData> mProjectClassifyDataList;
    List<Fragment> mProjectFragmentsList;
    List<String> mTitle;

    public static ProjectFragment newInstance() {

        Bundle args = new Bundle();

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        mProjectClassifyDataList = new ArrayList<>();
        mProjectFragmentsList = new ArrayList<>();
        mTitle = new ArrayList<>();
        mErrorView.setVisibility(View.GONE);
        mTvReload.setOnClickListener(v -> mPresenter.getProjectClassifyData());
    }
    @Override
    protected int getLayoutId() {
        return R.layout.project_fragment_layout;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getProjectClassifyData();
    }

    @Override
    public void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList) {
        mProjectTb.setVisibility(View.VISIBLE);
        mProjectVP.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        mProjectClassifyDataList.clear();
        mProjectClassifyDataList.addAll(projectClassifyDataList);
        for (ProjectClassifyData projectClassifyData:mProjectClassifyDataList) {
             mTitle.add(projectClassifyData.getName());
             mProjectFragmentsList.add(HomeSecondTabFragment.newInstance(projectClassifyData.getName(),projectClassifyData.getId()));
        }
        mProjectVP.setAdapter(new HomeTabPageAdapter(getChildFragmentManager(),mTitle,mProjectFragmentsList));
        mProjectTb.setupWithViewPager(mProjectVP);
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        super.showErrorMsg(errorMsg);
        mProjectTb.setVisibility(View.GONE);
        mProjectVP.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }
}
