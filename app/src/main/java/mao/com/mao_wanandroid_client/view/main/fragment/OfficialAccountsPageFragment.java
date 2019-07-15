package mao.com.mao_wanandroid_client.view.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.model.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsContract;
import mao.com.mao_wanandroid_client.presenter.main.OfficialAccountsPresenter;
import mao.com.mao_wanandroid_client.view.main.adapter.OfficialAccountsAdapter;

/**
 * @author maoqitian
 * @Description 微信公众号 page
 * @Time 2019/5/8 0008 23:19
 */
public class OfficialAccountsPageFragment extends BaseFragment<OfficialAccountsPresenter>
        implements OfficialAccountsContract.OfficialAccountsView,
        BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.official_accounts_recyclerview)
    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager layoutManager;
    OfficialAccountsAdapter mAdapter;

    List<KnowledgeHierarchyData> mOfficialAccountsListData;

    @Override
    protected void initView() {
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new OfficialAccountsAdapter(R.layout.official_accounts_item_cardview_layout);
        mRecyclerView.setAdapter(mAdapter);
        mOfficialAccountsListData = new ArrayList<>();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.official_accounts_fragment_layout;
    }
    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        //Log.e("毛麒添","当前页面状态"+currentState);
        mPresenter.getOfficialAccountsListData();
    }

    @Override
    public void showOfficialAccountsList(List<KnowledgeHierarchyData> officialAccountsListData) {
        mOfficialAccountsListData.addAll(officialAccountsListData);
        if (mOfficialAccountsListData.size() > 0){
            mAdapter.replaceData(mOfficialAccountsListData);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
