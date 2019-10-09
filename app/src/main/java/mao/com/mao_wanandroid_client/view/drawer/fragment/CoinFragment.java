package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinRecordData;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CoinPresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.view.drawer.adapter.CoinRecordAdapter;

/**
 * @author maoqitian
 * @Description: 我的积分
 * @date 2019/9/3 0003 14:54
 */
public class CoinFragment extends BaseDialogFragment<CoinPresenter> implements CoinContract.CoinView {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_page_title)
    TextView mPageTitle;
    @BindView(R.id.coin_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_coin)
    TextView mTextCoin;
    @BindView(R.id.tv_integral_rule)
    TextView mTvCoinRule;

    private RecyclerView.LayoutManager layoutManager;
    CoinRecordAdapter mAdapter;

    List<CoinRecordData> mCoinDataList;

    public static CoinFragment newInstance() {

        Bundle args = new Bundle();
        CoinFragment fragment = new CoinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.coin_page_fragment_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dialogFragment全屏
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFullScreen);
    }

    @Override
    protected void initViewAndData() {
        mCoinDataList = new ArrayList<>();
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(v -> dismiss());
        mPageTitle.setText(getString(R.string.my_coin));
        mTvCoinRule.setVisibility(View.VISIBLE);
        mTvCoinRule.setOnClickListener(view -> {
            HomeArticleData homeArticleData = new HomeArticleData();
            homeArticleData.setTitle(getString(R.string.coin_rule_text));
            homeArticleData.setLink("https://www.wanandroid.com/blog/show/2653");
            StartDetailPage.start(getActivity(),homeArticleData, Constants.PAGE_WEB_NOT_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
        });
        initRecyclerView();
        mPresenter.getCoinCount();
        mPresenter.getPersonalCoinList();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CoinRecordAdapter(R.layout.coin_record_item_view_layout);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void showCoinCount(RankData rankData) {
         mTextCoin.setText(String.valueOf(rankData.getCoinCount()));
    }

    @Override
    public void showPersonalCoinList(List<CoinRecordData> coinDataList) {
        mCoinDataList.clear();
        mCoinDataList.addAll(coinDataList);
        mAdapter.replaceData(mCoinDataList);
    }

}
