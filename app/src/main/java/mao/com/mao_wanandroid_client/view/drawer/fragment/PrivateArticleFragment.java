package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.presenter.drawer.PrivateArticleContract;
import mao.com.mao_wanandroid_client.presenter.drawer.PrivateArticlePresenter;
import mao.com.mao_wanandroid_client.utils.NormalAlertDialog;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.view.drawer.adapter.PrivateArticleAdapter;

/**
 * @Description: 自己分享 用户分享 文章列表
 * @Author: maoqitian
 * @Date: 2019-10-09 23:49
 */
public class PrivateArticleFragment extends BaseFragment<PrivateArticlePresenter> implements
        PrivateArticleContract.PrivateArticleView, BaseQuickAdapter.OnItemClickListener, View.OnClickListener {


    List<HomeArticleData> mArticleDataList;

    @BindView(R.id.collection_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.collection_smartrefreshlayout)
    SmartRefreshLayout mSmartRefreshLayout;

    @BindView(R.id.cl_collection_empty)
    ConstraintLayout mClEmpty;
    @BindView(R.id.tv_add_collection)
    TextView tvAddFavorites;
    @BindView(R.id.fab_add)
    FloatingActionButton mFabAdd;

    private RecyclerView.LayoutManager layoutManager;

    String mType;

    int mUserId;

    PrivateArticleAdapter mAdapter;


    ArticleShareDialogFragment articleShareDialogFragment;

    /**
     *
     * @param pageType 代表页面展示类型 广场进入展示用户信息和用户分享文章 用户中心进入展示用户的个人分享文章和用户信息
     * @param userId
     * @return
     */
    public static PrivateArticleFragment newInstance(String pageType,int userId) {

        Bundle args = new Bundle();
        PrivateArticleFragment fragment = new PrivateArticleFragment();
        args.putString(Constants.PAGE_TYPE,pageType);
        args.putInt(Constants.USER_ID,userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        assert arguments != null;
        mType = arguments.getString(Constants.PAGE_TYPE);
        mUserId = arguments.getInt(Constants.USER_ID,-1);

    }

    @Override
    protected void initView() {
        mArticleDataList = new ArrayList<>();
        //拖动Header的时候是否同时拖动内容（默认true）
        mSmartRefreshLayout.setEnableHeaderTranslationContent(false);
        //关闭下拉刷新
        mSmartRefreshLayout.setEnableRefresh(false);
        mSmartRefreshLayout.setEnableLoadMore(false);
        initRecyclerView();
        //添加收藏网站监听
        tvAddFavorites.setOnClickListener(this);
        mFabAdd.setOnClickListener(this);
        setSmartRefreshLayoutListener();
    }

    private void setSmartRefreshLayoutListener() {

    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new PrivateArticleAdapter(R.layout.private_article_item_cardview_layout);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
            //点击删除分享文章
            if (view.getId() == R.id.iv_delete_article) {
                assert homeArticleData != null;
                NormalAlertDialog.getInstance().showAlertDialog(
                            getActivity(), "确定删除" + homeArticleData.getTitle() + "文章?",
                            getString(R.string.confirm_text), getString(R.string.cancel_text),
                            (dialog, which) -> mPresenter.getUserArticleDelete(getActivity(),homeArticleData.getId()),
                            (dialog, which) -> dialog.dismiss());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.collection_fragment_layout;
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        if(Constants.SQUARE_USER_TYPE.equals(mType)){
            //广场用户个人中心
            mFabAdd.setVisibility(View.GONE);
            mPresenter.getUserShareArticlesData(mUserId);
        }else {
            //登录用户个人中心
            mPresenter.getPrivateArticleData();
            mAdapter.setPrivate(true);
        }

    }

    @Override
    public void showPrivateArticleData(List<HomeArticleData> articleDataList) {
        mArticleDataList.clear();
        mArticleDataList.addAll(articleDataList);
        mAdapter.replaceData(mArticleDataList);

    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.fab_add){
            //开启 分享 dialog
            articleShareDialogFragment = ArticleShareDialogFragment.newInstance();
            if (!getActivity().isDestroyed() && articleShareDialogFragment.isAdded()) {
                articleShareDialogFragment.dismiss();
            }
            articleShareDialogFragment.show(getChildFragmentManager(),"showArticleCollectionDialog");
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_NOT_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }


    public void updateDate() {
        if(Constants.SQUARE_USER_TYPE.equals(mType)){
            //广场用户个人中心
            mPresenter.getUserShareArticlesData(mUserId);
        }else {
            //登录用户个人中心
            mPresenter.getPrivateArticleData();
        }
    }
}
