package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionPageContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionPagePresenter;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeTabPageAdapter;

/**
 * @author maoqitian
 * @Description: 我的收藏页面
 * @date 2019/8/20 0020 16:57
 */
public class CollectionPageFragment extends BaseFragment<CollectionPagePresenter> implements CollectionPageContract.CollectionPageView {


    @BindView(R.id.collection_tab)
    TabLayout mCollectionTab;
    @BindView(R.id.collection_view_pager)
    ViewPager mViewPager;

    List<String> mTitle;
    List<Fragment> mFragments;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        initPage();
    }

    private void initPage() {
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTitle.add(getString(R.string.collection_article));
        mTitle.add(getString(R.string.collection_web));
        mFragments.add(CollectionFragment.newInstance());
        mFragments.add(CollectionWebFragment.newInstance());

        //下划线间距
        ToolsUtils.setIndicatorWidth(mCollectionTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
        mViewPager.setAdapter(new HomeTabPageAdapter(getChildFragmentManager(),mTitle,mFragments));
        mCollectionTab.setupWithViewPager(mViewPager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.collection_page_fragment_layout;
    }
}
