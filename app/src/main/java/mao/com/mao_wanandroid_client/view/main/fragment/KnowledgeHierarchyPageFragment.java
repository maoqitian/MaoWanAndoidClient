package mao.com.mao_wanandroid_client.view.main.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.presenter.main.KnowledgeHierarchyContract;
import mao.com.mao_wanandroid_client.presenter.main.KnowledgeHierarchyPresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.view.main.adapter.KnowledgeHierarchyAdapter;

/**
 * @author maoqitian
 * @Description: 知识体系 Fragment
 * @date 2019/5/7 0007 11:43
 */
public class KnowledgeHierarchyPageFragment extends RootBaseFragment<KnowledgeHierarchyPresenter>
        implements KnowledgeHierarchyContract.KnowledgeHierarchyView,
        BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.knowledge_page_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.knowledge_smartRefresh)
    SmartRefreshLayout smartRefreshLayout;
    StaggeredGridLayoutManager layoutManager;
    KnowledgeHierarchyAdapter mAdapter;
    List<KnowledgeHierarchyData> mKnowledgeHierarchyData;

    @Override
    protected void initView() {
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new KnowledgeHierarchyAdapter(R.layout.knowledge_item_cardview_layout);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mKnowledgeHierarchyData = new ArrayList<>();
        smartRefreshLayoutListener();
    }

    private void smartRefreshLayoutListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartRefreshLayout.autoRefresh();
                mPresenter.getKnowledgeHierarchyData();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.knowledge_hierarchy_fragment_layout;
    }
    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        //Log.e("毛麒添","当前页面状态"+currentState);
        showLoading();
        mPresenter.getKnowledgeHierarchyData();
    }

    @Override
    public void showKnowledgeHierarchyData(List<KnowledgeHierarchyData> knowledgeHierarchyData) {
       mKnowledgeHierarchyData.clear();
       mKnowledgeHierarchyData.addAll(knowledgeHierarchyData);
       mAdapter.replaceData(mKnowledgeHierarchyData);
       smartRefreshLayout.finishRefresh();
       showNormal();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        KnowledgeHierarchyData knowledgeHierarchyData = mKnowledgeHierarchyData.get(position);
        StartDetailPage.start2(_mActivity,knowledgeHierarchyData,Constants.RESULT_CODE_KNOWLEDGE_PAGE,Constants.ACTION_KNOWLEDGE_LEVEL2_ACTIVITY);
       /* Intent intent = new Intent(Constants.ACTION_KNOWLEDGE_LEVEL2_ACTIVITY);
        intent.putExtra(Constants.KNOWLEDGE_DATA, knowledgeHierarchyData);
        intent.putExtra(Constants.PAGE_TYPE, Constants.RESULT_CODE_KNOWLEDGE_PAGE);
        _mActivity.startActivity(intent);*/
    }
    @Override
    public void reload() {
        showLoading();
        mPresenter.getKnowledgeHierarchyData();
    }
}
