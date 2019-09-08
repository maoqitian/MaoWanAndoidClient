package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.collect.CollectData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionPresenter;
import mao.com.mao_wanandroid_client.utils.NormalAlertDialog;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.ToastUtils;
import mao.com.mao_wanandroid_client.view.drawer.adapter.CollectionAdapter;

/**
 * @author maoqitian
 * @Description: 收藏文章 Fragment
 * @date 2019/7/26 0026 15:50
 */
public class CollectionFragment extends BaseFragment<CollectionPresenter>
        implements CollectionContract.CollectionView, BaseQuickAdapter.OnItemClickListener, View.OnClickListener {

    //收藏文章数据
    List<CollectData> mCollectDataList;

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
    CollectionAdapter mAdapter;
    // 添加收藏 dialog
    CollectionDialogFragment collectionDialogFragment;

    //下拉刷新头部
    private MaterialHeader mMaterialHeader;

    String mType;

    /**
     * @param type type 代表是否有下拉刷新和上拉加载更多
     * @return
     */
    public static CollectionFragment newInstance(String type) {

        Bundle args = new Bundle();
        CollectionFragment fragment = new CollectionFragment();
        args.putString(Constants.COLLECTION_REFRESH_TAG,type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mType = getArguments().getString(Constants.COLLECTION_REFRESH_TAG,Constants.COLLECTION_REFRESH_TYPE);
    }

    @Override
    protected void initView() {
        mCollectDataList = new ArrayList<>();
        mMaterialHeader = (MaterialHeader)mSmartRefreshLayout.getRefreshHeader();
        //拖动Header的时候是否同时拖动内容（默认true）
        mSmartRefreshLayout.setEnableHeaderTranslationContent(false);
        mMaterialHeader.setColorSchemeResources(R.color.colorPrimary,android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        initRecyclerView();
        //添加收藏站外文章监听
        tvAddFavorites.setOnClickListener(this);
        mFabAdd.setOnClickListener(this);
    }

    private void initRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CollectionAdapter(R.layout.collection_item_cardview_layout);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            CollectData collectData = (CollectData) adapter.getItem(position);
            //点击收藏
            switch (view.getId()){
                case R.id.more_collect: //点击收藏
                    Log.e("毛麒添","点击收藏");
                    NormalAlertDialog.getInstance().showBottomAlertDialog(_mActivity, v -> {
                        if (collectData != null){
                            mPresenter.getCancelCollectArticleData(_mActivity,collectData.getId(),collectData.getOriginId(),position);
                        }else {
                            ToastUtils.showToastShort(_mActivity,"取消收藏数据为空");
                        }
                        NormalAlertDialog.getInstance().cancelBottomDialog();
                    });
                    break;
                case R.id.collection_super_chapterName:
                    //点击 收藏tag
                    //暂不 实现 没必要
                   /* HomeArticleData homeArticleData = new HomeArticleData();
                    homeArticleData.setTitle(collectData.getTitle());
                    homeArticleData.setLink(collectData.getLink());
                    homeArticleData.setChapterId(collectData.getChapterId());
                    homeArticleData.setChapterName(collectData.getChapterName());
                    homeArticleData.setSuperChapterId(collectData.getChapterId());
                    StartDetailPage.start(_mActivity,homeArticleData,Constants.RESULT_CODE_HOME_PAGE,Constants.ACTION_KNOWLEDGE_LEVEL2_ACTIVITY);*/
                    break;
            }
        });
        //下拉刷新
        setSmartRefreshLayoutListener();

    }

    private void setSmartRefreshLayoutListener() {
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            Log.e("毛麒添","下拉刷新");
            mPresenter.getCollectListData();
            refreshLayout.autoRefresh();
        });
        //加载更多
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            Log.e("毛麒添","加载更多");
            mPresenter.getLoadCollectListData();
            refreshLayout.autoLoadMore();
        });
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        if(Constants.COLLECTION_NOT_REFRESH_TYPE.equals(mType)){
            mSmartRefreshLayout.setEnableLoadMore(false);
            mSmartRefreshLayout.setEnableRefresh(false);
        }else {
            mSmartRefreshLayout.autoRefresh();
        }
        mPresenter.getCollectListData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.collection_fragment_layout;
    }

    @Override
    public void showCollectListData(List<CollectData> collectDataList,boolean isRefresh) {
        if(isRefresh){ //加载更多
            mAdapter.addData(collectDataList);
        }else {
            mCollectDataList.clear();
            mCollectDataList.addAll(collectDataList);
            mAdapter.replaceData(mCollectDataList);
        }
        showCollectionDataChange();
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
        showNormal();
    }

    @Override
    public void showLoadDataMessage(String msg) {
        ToastUtils.showToast(msg);
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void showAddCollectData(CollectData collectData) {
        mAdapter.addData(collectData);
        showCollectionDataChange();
    }

    @Override
    public void showCancelCollectArticleSuccess(int position, String msg) {
        ToastUtils.showToast(msg);
        mAdapter.remove(position);
        showCollectionDataChange();
        //mAdapter.notifyDataSetChanged();
    }

    //是否显示空白添加新数据
    @SuppressLint("RestrictedApi")
    private void showCollectionDataChange() {
        if(Constants.COLLECTION_NOT_REFRESH_TYPE.equals(mType)){
            return;
        }
        List<CollectData> data = mAdapter.getData();
        if(data.size() == 0){
            mClEmpty.setVisibility(View.VISIBLE);
            mFabAdd.setVisibility(View.GONE);
            mSmartRefreshLayout.setVisibility(View.GONE);
        }else {
            mClEmpty.setVisibility(View.GONE);
            mFabAdd.setVisibility(View.VISIBLE);
            mSmartRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showCancelCollectArticleFail(String msg) {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
        ToastUtils.showToast(msg);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CollectData collectData = mCollectDataList.get(position);
        HomeArticleData homeArticleData = new HomeArticleData();
        homeArticleData.setTitle(collectData.getTitle());
        homeArticleData.setLink(collectData.getLink());
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_NOT_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //添加收藏
            case R.id.fab_add :
            case R.id.tv_add_collection :
                if (collectionDialogFragment == null) {
                    collectionDialogFragment = CollectionDialogFragment.newInstance(Constants.COLLECTION_ARTICLE_TYPE, true, null,-1);
                }
                if (!getActivity().isDestroyed() && collectionDialogFragment.isAdded()) {
                    collectionDialogFragment.dismiss();
                }
                collectionDialogFragment.show(getChildFragmentManager(),"showArticleCollectionDialog");
                break;
        }
    }

    public void updateDate() {
        //刷新数据
        mPresenter.getCollectListData();
    }
}
