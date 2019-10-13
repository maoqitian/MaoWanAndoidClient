package mao.com.mao_wanandroid_client.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;
import mao.com.mao_wanandroid_client.presenter.main.UserCenterContract;
import mao.com.mao_wanandroid_client.presenter.main.UserCenterPresenter;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionWebFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.PrivateArticleFragment;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeTabPageAdapter;

/**
 * @author maoqitian
 * @Description 个人中心  SmartRefreshLayout + ViewPager + RecyclerView 动效结合 CoordinatorLayout + AppBarLayout + CollapsingToolbarLayout
 * @Time 2019/7/22 0022 23:22
 */
public class UserCenterActivity extends BaseActivity<UserCenterPresenter> implements UserCenterContract.UserCenterView,AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.user_toolbar)
    Toolbar mToolBar;
    //toolbar1 状态栏卡住 tablayout 不向上滑动
    @BindView(R.id.toolbar1)
    Toolbar mToolBar1;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
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

    HomeTabPageAdapter mAdapter;


    private int userId;

    private String pageType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        userId = intent.getIntExtra(Constants.USER_ID,-1);
        pageType = intent.getStringExtra(Constants.PAGE_TYPE);

        initCollectionView();

    }

    @Override
    protected void initToolbar() {
        mToolBar.setNavigationOnClickListener(v -> finish());
        // 状态栏透明并加入状态栏宽度
        StatusBarUtil.setTranslucentForImageView(this,0,mToolBar);
        StatusBarUtil.setTranslucentForImageView(this,0,mToolBar1);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_center_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        refreshListener();
        mAppbarLayout.addOnOffsetChangedListener(this);
        mToolBar.setBackgroundColor(0);

    }

    @Override
    public void showCoinAndRank(RankData rankData) {
        tvNickName.setText(Constants.SQUARE_USER_TYPE.equals(pageType) ? rankData.getUsername():mPresenter.getLoginUserName());
        tvTbNickName.setText(Constants.SQUARE_USER_TYPE.equals(pageType) ? rankData.getUsername():mPresenter.getLoginUserName());
        tvUserCenterCoin.setText("积分："+rankData.getCoinCount());
        tvUserCenterRank.setText("lv "+ToolsUtils.getRank(rankData.getCoinCount()));
    }

    private void refreshListener() {
        mSmartRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener(){
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                if(Constants.SQUARE_USER_TYPE.equals(pageType)){
                    mPresenter.getUserShareArticlesData(userId,1);
                }else {
                    mPresenter.getCoinAndRank();
                    ToolsUtils.setIndicatorWidth(mCollectionTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
                }
                mAdapter.setType(Constants.REFRESH_TYPE);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                mAdapter.setType(Constants.LOAD_MORE_TYPE);
                mAdapter.notifyDataSetChanged();
                ToolsUtils.setIndicatorWidth(mCollectionTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
            }
            //处理头部下拉刷新
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                ivParallax.setTranslationY(mOffset - mScrollY);
                mToolBar.setAlpha(1 - Math.min(percent, 1));
            }
        });
    }

    private void initCollectionView() {

        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();

        if(Constants.SQUARE_USER_TYPE.equals(pageType)){
            ToolsUtils.setIndicatorWidth(mCollectionTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
            mTitle.add(getString(R.string.share_article_text));
            mFragments.add(PrivateArticleFragment.newInstance(Constants.SQUARE_USER_TYPE,userId));
            mCollectionTab.setTabMode(TabLayout.MODE_SCROLLABLE);
            mPresenter.getUserShareArticlesData(userId,1);
        }else {
            tvNickName.setText(mPresenter.getLoginUserName());
            tvTbNickName.setText(mPresenter.getLoginUserName());
            mTitle.add(getString(R.string.collection_article));
            mTitle.add(getString(R.string.share_article_text));
            mTitle.add(getString(R.string.collection_web));
            mFragments.add(CollectionFragment.newInstance(Constants.COLLECTION_NOT_REFRESH_TYPE));
            mFragments.add(PrivateArticleFragment.newInstance("",-1));
            mFragments.add(CollectionWebFragment.newInstance(Constants.COLLECTION_NOT_REFRESH_TYPE));
            //下划线间距
            ToolsUtils.setIndicatorWidth(mCollectionTab,getResources().getDimensionPixelSize(R.dimen.dp_30));
            mPresenter.getCoinAndRank();
        }
        mAdapter = new HomeTabPageAdapter(getSupportFragmentManager(),mTitle,mFragments);
        mViewPager.setAdapter(mAdapter);
        mCollectionTab.setupWithViewPager(mViewPager);

    }


    //头图 toolbar 动效处理
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //获取最大偏移值
        int scrollRangle = appBarLayout.getTotalScrollRange();
        ivParallax.setTranslationY(verticalOffset);
        /**
         * 这个数值可以自己定义 显示 toolbar 用户名称
         */
        if (verticalOffset < -250) {
            tvTbNickName.setVisibility(View.VISIBLE);
        } else {
            tvTbNickName.setVisibility(View.GONE);
        }
        int mAlpha = (int) Math.abs(255f / scrollRangle * verticalOffset);
        int color = ContextCompat.getColor(appBarLayout.getContext(), R.color.colorPrimary)&0x00ffffff;
        StatusBarUtil.setColorNoTranslucentLightMode(this,((mAlpha) << 24) | color);
        //顶部title渐变效果
        mToolBar.setBackgroundColor(((mAlpha) << 24) | color);
    }
}
