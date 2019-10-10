package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.os.Bundle;
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
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.presenter.drawer.PrivateArticleContract;
import mao.com.mao_wanandroid_client.presenter.drawer.PrivateArticlePresenter;
import mao.com.mao_wanandroid_client.view.drawer.adapter.PrivateArticleAdapter;

/**
 * @Description: 自己分享文章列表
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

    PrivateArticleAdapter mAdapter;

    public static PrivateArticleFragment newInstance() {

        Bundle args = new Bundle();

        PrivateArticleFragment fragment = new PrivateArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        mArticleDataList = new ArrayList<>();
        //拖动Header的时候是否同时拖动内容（默认true）
        mSmartRefreshLayout.setEnableHeaderTranslationContent(false);
        //关闭下拉刷新
        mSmartRefreshLayout.setEnableRefresh(true);

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
            //点击收藏
            if (view.getId() == R.id.iv_delete_article) { //点击删除
                assert homeArticleData != null;
                    /*NormalAlertDialog.getInstance().showAlertDialog(
                            getActivity(), "确定删除" + webBookMark.getName() + "?",
                            getString(R.string.confirm_text), getString(R.string.cancel_text),
                            (dialog, which) -> mPresenter.getDeleteCollectWebData(getActivity(),webBookMark.getId(),position),
                            (dialog, which) -> dialog.dismiss());*/
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.collection_fragment_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        mPresenter.getPrivateArticleData();

    }

    @Override
    public void showPrivateArticleData(List<HomeArticleData> articleDataList) {
        mArticleDataList.clear();
        mArticleDataList.addAll(articleDataList);
        mAdapter.replaceData(mArticleDataList);

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }


}
