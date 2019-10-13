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
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionWebContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionWebPresenter;
import mao.com.mao_wanandroid_client.utils.NormalAlertDialog;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.ToastUtils;
import mao.com.mao_wanandroid_client.view.drawer.adapter.CollectionWebAdapter;

/**
 * @author maoqitian
 * @Description: 收藏网站
 * @date 2019/8/20 0020 17:03
 */
public class CollectionWebFragment extends BaseFragment<CollectionWebPresenter>
        implements CollectionWebContract.CollectionWeb,
        BaseQuickAdapter.OnItemClickListener, View.OnClickListener {

    List<WebBookMark> mCollectionWebDataList;

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

    CollectionWebAdapter mAdapter;

    CollectionDialogFragment collectionDialogFragment;

    //下拉刷新头部
    private MaterialHeader mMaterialHeader;

    String mType;

    public static CollectionWebFragment newInstance(String type) {

        Bundle args = new Bundle();
        CollectionWebFragment fragment = new CollectionWebFragment();
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
        mCollectionWebDataList = new ArrayList<>();
        mMaterialHeader = (MaterialHeader)mSmartRefreshLayout.getRefreshHeader();
        //拖动Header的时候是否同时拖动内容（默认true）
        mSmartRefreshLayout.setEnableHeaderTranslationContent(false);
        //关闭加载更多
        mSmartRefreshLayout.setEnableLoadMore(false);
        mMaterialHeader.setColorSchemeResources(R.color.colorPrimary,android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_blue_light);
        initRecyclerView();
        //添加收藏网站监听
        tvAddFavorites.setOnClickListener(this);
        mFabAdd.setOnClickListener(this);
        setSmartRefreshLayoutListener();
    }

    private void setSmartRefreshLayoutListener() {
        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            Log.e("毛麒添","下拉刷新");
            mPresenter.getCollectWebData();
            refreshLayout.autoRefresh();
        });
    }

    private void initRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CollectionWebAdapter(R.layout.collection_web_item_cardview_layout);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            WebBookMark webBookMark = (WebBookMark) adapter.getItem(position);
            //点击收藏
            switch (view.getId()){
                case R.id.iv_delete_web: //点击删除
                    assert webBookMark != null;
                    NormalAlertDialog.getInstance().showAlertDialog(
                            getActivity(), "确定删除" + webBookMark.getName() + "?",
                            getString(R.string.confirm_text), getString(R.string.cancel_text),
                            (dialog, which) -> mPresenter.getDeleteCollectWebData(getActivity(),webBookMark.getId(),position),
                            (dialog, which) -> dialog.dismiss());
                    break;
                case R.id.iv_web_edit: //编辑
                    collectionDialogFragment = CollectionDialogFragment.newInstance(Constants.COLLECTION_WEB_TYPE, false, webBookMark,position);
                    if (!getActivity().isDestroyed() && collectionDialogFragment.isAdded()) {
                        collectionDialogFragment.dismiss();
                    }
                    collectionDialogFragment.show(getChildFragmentManager(),"showUpdateCollectionDialog");
                    break;
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
        if(Constants.COLLECTION_NOT_REFRESH_TYPE.equals(mType)){
            mSmartRefreshLayout.setEnableLoadMore(false);
            mSmartRefreshLayout.setEnableRefresh(false);
        }else {
            mSmartRefreshLayout.autoRefresh();
        }
        mPresenter.getCollectWebData();
    }

    @Override
    public void showCollectionWebData(List<WebBookMark> collectionWebDataList) {
        mCollectionWebDataList.clear();
        mCollectionWebDataList.addAll(collectionWebDataList);
        mAdapter.replaceData(mCollectionWebDataList);
        mSmartRefreshLayout.finishRefresh();
        showCollectionDataChange();
    }

    /**
     * 添加网站收藏成功
     * @param webBookMark
     * @param msg
     */
    @Override
    public void showAddCollectWebSuccess(WebBookMark webBookMark, String msg) {
        mAdapter.addData(webBookMark);
        showCollectionDataChange();
        ToastUtils.showToastShort(getActivity(),msg);
    }

    /**
     * 更新网站收藏成功
     */
    @Override
    public void showUpdateCollectWebSuccess(int position, WebBookMark webBookMark, String msg) {
        mAdapter.setData(position,webBookMark);
        ToastUtils.showToastShort(getActivity(),msg);
    }


    /**
     * 删除网站收藏成功
     */
    @Override
    public void showDeleteCollectWebSuccess(int position, String msg) {
        mAdapter.remove(position);
        showCollectionDataChange();
        ToastUtils.showToastShort(getActivity(),msg);
    }
    //是否显示空白添加新数据
    @SuppressLint("RestrictedApi")
    private void showCollectionDataChange() {
        if(Constants.COLLECTION_NOT_REFRESH_TYPE.equals(mType)){
            return;
        }
        List<WebBookMark> data = mAdapter.getData();
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
    public void showCollectionWebFailStatus(String msg) {
        mSmartRefreshLayout.finishRefresh();
        ToastUtils.showToastShort(getActivity(),msg);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        WebBookMark webBookMark = (WebBookMark) adapter.getItem(position);
        HomeArticleData homeArticleData = new HomeArticleData();
        assert webBookMark != null;
        homeArticleData.setTitle(webBookMark.getName());
        homeArticleData.setLink(webBookMark.getLink());
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_NOT_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //添加收藏
            case R.id.fab_add :
            case R.id.tv_add_collection :
                collectionDialogFragment = CollectionDialogFragment.newInstance(Constants.COLLECTION_WEB_TYPE, true, null,-1);
                if (!getActivity().isDestroyed() && collectionDialogFragment.isAdded()) {
                    collectionDialogFragment.dismiss();
                }
                collectionDialogFragment.show(getChildFragmentManager(),"showWebCollectionDialog");
                break;
        }
    }

    public void updateDate() {
        mPresenter.getCollectWebData();
    }

    public void loadDate() {
    }
}
