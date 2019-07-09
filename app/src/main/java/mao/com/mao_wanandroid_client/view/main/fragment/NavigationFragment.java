package mao.com.mao_wanandroid_client.view.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.presenter.main.NavigationContract;
import mao.com.mao_wanandroid_client.presenter.main.NavigationPresenter;
import mao.com.mao_wanandroid_client.view.main.adapter.NavigationViewAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * @author maoqitian
 * @Description 导航
 * @Time 2019/5/8 0008 23:33
 */
public class NavigationFragment  extends BaseFragment<NavigationPresenter> implements NavigationContract.NavigationView {


    @BindView(R.id.vertical_tab)
    VerticalTabLayout verticalTabLayout;
    @BindView(R.id.nav_recyclerview)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    List<NavigationListData> mNavigationListData;

    NavigationViewAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.navigation_fragment_layout;
    }

    @Override
    protected void initView() {
        initRecycleView();
    }

    private void initRecycleView() {
        mNavigationListData = new ArrayList<>();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new NavigationViewAdapter(R.layout.nav_item_cardview_layout);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        //Log.e("毛麒添","当前页面状态"+currentState);
        mPresenter.getNavigationData();
    }


    @Override
    public void showNavigationListData(List<NavigationListData> navigationListData) {

    }
}
