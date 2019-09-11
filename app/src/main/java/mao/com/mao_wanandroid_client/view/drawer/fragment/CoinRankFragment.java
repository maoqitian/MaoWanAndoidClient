package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinRankContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinRankPresenter;
import mao.com.mao_wanandroid_client.view.drawer.adapter.CoinRankAdapter;

/**
 * @author maoqitian
 * @Description: 积分排行榜
 * @date 2019/9/11 0011 11:39
 */
public class CoinRankFragment extends BaseDialogFragment <CoinRankPresenter> implements CoinRankContract.CoinRankView {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_page_title)
    TextView mPageTitle;
    @BindView(R.id.coin_rank_recycleview)
    RecyclerView mRecyclerView;

    List<RankData> mRankDataList;

    private RecyclerView.LayoutManager layoutManager;
    CoinRankAdapter mAdapter;

    public static CoinRankFragment newInstance() {

        Bundle args = new Bundle();
        CoinRankFragment fragment = new CoinRankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dialogFragment全屏
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFullScreen);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.coin_rank_fragment_layout;
    }

    @Override
    protected void initViewAndData() {
        mRankDataList = new ArrayList<>();
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(v -> dismiss());
        mPageTitle.setText(getString(R.string.coin_rank_text));
        initRecyclerView();
        mPresenter.getCoinRank();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CoinRankAdapter(R.layout.coin_rank_fragment_layout);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showCoinRankData(List<RankData> rankDataList) {
        mRankDataList.clear();
        mRankDataList.addAll(rankDataList);
        mAdapter.replaceData(mRankDataList);
    }
}
