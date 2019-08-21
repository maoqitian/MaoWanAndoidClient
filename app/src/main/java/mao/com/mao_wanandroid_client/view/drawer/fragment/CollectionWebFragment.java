package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.webBookMark;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionWebContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionWebPresenter;
import mao.com.mao_wanandroid_client.view.drawer.adapter.CollectionWebAdapter;

/**
 * @author maoqitian
 * @Description: 收藏网站
 * @date 2019/8/20 0020 17:03
 */
public class CollectionWebFragment extends BaseFragment<CollectionWebPresenter>
        implements CollectionWebContract.CollectionWeb, BaseQuickAdapter.OnItemClickListener {

    List<webBookMark> mCollectionWebDataList;

    @BindView(R.id.collection_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.collection_smartrefreshlayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private RecyclerView.LayoutManager layoutManager;

    CollectionWebAdapter mAdapter;

    public static CollectionWebFragment newInstance() {

        Bundle args = new Bundle();

        CollectionWebFragment fragment = new CollectionWebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        mCollectionWebDataList = new ArrayList<>();
        initRecyclerView();
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
            webBookMark collectData = (webBookMark) adapter.getItem(position);
            //点击收藏
            switch (view.getId()){
                case R.id.iv_delete_web: //点击删除

                    break;
                case R.id.iv_web_edit: //编辑
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

    }

    @Override
    protected int getLayoutId() {
        return R.layout.collection_fragment_layout;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getCollectWebData();
    }

    @Override
    public void showCollectionWebData(List<webBookMark> collectionWebDataList) {
        mCollectionWebDataList.clear();
        mCollectionWebDataList.addAll(collectionWebDataList);
        mAdapter.replaceData(mCollectionWebDataList);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
