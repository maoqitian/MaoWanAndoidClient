package mao.com.mao_wanandroid_client.view.main.fragment;

import android.content.ContextWrapper;
import android.support.v4.content.ContextCompat;
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
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

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
        mNavigationListData.clear();
        mNavigationListData.addAll(navigationListData);
        verticalTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationListData.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(mNavigationListData.get(position).getName())
                        .setTextColor(ContextCompat.getColor(_mActivity,R.color.colorPrimaryDark), ContextCompat.getColor(_mActivity,R.color.color_white))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        mAdapter.replaceData(mNavigationListData);
    }


}
