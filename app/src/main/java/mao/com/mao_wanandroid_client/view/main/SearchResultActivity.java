package mao.com.mao_wanandroid_client.view.main;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.main.SearchResultContract;
import mao.com.mao_wanandroid_client.presenter.main.SearchResultPresenter;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionWebFragment;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeTabPageAdapter;

/**
 * @author maoqitian
 * @Description 个人中心
 * @Time 2019/7/22 0022 23:22
 */
public class SearchResultActivity extends BaseActivity<SearchResultPresenter> implements SearchResultContract.SearchResultView {

    @BindView(R.id.user_toolbar)
    Toolbar mToolBar;
    @BindView(R.id.ns_view)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.sr_refresh)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.iv_parallax)
    ImageView ivParallax;
    @BindView(R.id.buttonBarLayout)
    ButtonBarLayout mButtonBarLayout;

    @BindView(R.id.tv_nickname)
    TextView tvNickName;
    @BindView(R.id.tv_toolbar_name)
    TextView tvTbNickName;
    @BindView(R.id.tv_user_center_rank)
    TextView tvUserCenterRank;
    @BindView(R.id.tv_user_center_coin)
    TextView tvUserCenterCoin;


    @BindView(R.id.collection_tab)
    TabLayout mCollectionTab;
    @BindView(R.id.collection_view_pager)
    ViewPager mViewPager;

    List<String> mTitle;
    List<Fragment> mFragments;

    private int mOffset = 0;
    private int mScrollY = 0;

    @Override
    protected void initToolbar() {
        mToolBar.setNavigationOnClickListener(v -> {
            finish();
        });
        // 状态栏透明并加入状态栏宽度
        StatusBarUtil.setTranslucentForImageView(this,0,mToolBar);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search_result_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        mSmartRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener(){
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(3000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                ivParallax.setTranslationY(mOffset - mScrollY);
                mToolBar.setAlpha(1 - Math.min(percent, 1));
            }
        });

        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = ToolsUtils.dp2px(170);
            private int color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)&0x00ffffff;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    mButtonBarLayout.setAlpha(1f * mScrollY / h);
                    mToolBar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    ivParallax.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
        mButtonBarLayout.setAlpha(0);
        mToolBar.setBackgroundColor(0);

        initCollectionView();
    }

    private void initCollectionView() {
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTitle.add(getString(R.string.collection_article));
        mTitle.add(getString(R.string.collection_web));
        mFragments.add(CollectionFragment.newInstance());
        mFragments.add(CollectionWebFragment.newInstance());

        //下划线间距
        ToolsUtils.setIndicatorWidth(mCollectionTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
        mViewPager.setAdapter(new HomeTabPageAdapter(getSupportFragmentManager(),mTitle,mFragments));
        mCollectionTab.setupWithViewPager(mViewPager);
    }

}
