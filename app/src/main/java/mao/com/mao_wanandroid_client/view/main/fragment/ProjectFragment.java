package mao.com.mao_wanandroid_client.view.main.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.project.ProjectClassifyData;
import mao.com.mao_wanandroid_client.presenter.main.ProjectContract;
import mao.com.mao_wanandroid_client.presenter.main.ProjectPresenter;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeTabPageAdapter;

/**
 * @author maoqitian
 * @Description 项目
 * @Time 2019/5/8 0008 23:39
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.ProjectView {

    @BindView(R.id.project_tab)
    TabLayout mProjectTb;
    @BindView(R.id.view_pager_project)
    ViewPager mProjectVP;

    List<ProjectClassifyData> mProjectClassifyDataList;
    List<Fragment> mProjectFragmentsList;
    List<String> mTitle;
    @Override
    protected void initView() {
        mProjectClassifyDataList = new ArrayList<>();
        mProjectFragmentsList = new ArrayList<>();
        mTitle = new ArrayList<>();
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
        mProjectClassifyDataList.clear();
        mProjectClassifyDataList.addAll(projectClassifyDataList);
        for (ProjectClassifyData projectClassifyData:mProjectClassifyDataList) {
             mTitle.add(projectClassifyData.getName());
             mProjectFragmentsList.add(HomeSecondTabFragment.newInstance(projectClassifyData.getName(),projectClassifyData.getId()));
        }
        mProjectVP.setAdapter(new HomeTabPageAdapter(getChildFragmentManager(),mTitle,mProjectFragmentsList));
        mProjectTb.setupWithViewPager(mProjectVP);
    }
}
