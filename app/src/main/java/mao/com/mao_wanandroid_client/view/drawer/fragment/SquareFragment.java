package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.presenter.drawer.SquareContract;
import mao.com.mao_wanandroid_client.presenter.drawer.SquarePresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.ToastUtils;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;

/**
 * @Description: 广场
 * @Author: maoqitian
 * @Date: 2019-10-04 13:01
 */
public class SquareFragment extends BaseFragment<SquarePresenter> implements
        SquareContract.SquareView,
        BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.inflate_view)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.square_recyclerview)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private HomePageAdapter mAdapter;

    List<HomeArticleData> mHomeArticleDataList;


    public static SquareFragment newInstance() {

        Bundle args = new Bundle();

        SquareFragment fragment = new SquareFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {
        mHomeArticleDataList =new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new HomePageAdapter(R.layout.article_item_cardview_layout);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        setHomePageItemClickListener();
    }

    private void setHomePageItemClickListener() {
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeArticleData homeArticleData= (HomeArticleData) adapter.getItem(position);
            switch (view.getId()){
                case R.id.image_collect: //点击收藏
                    Log.e("毛麒添","点击收藏");
                    if(homeArticleData!=null){
                        addOrCancelCollect(position,homeArticleData);
                    }
                    break;
                case R.id.tv_super_chapterName:
                    //点击 知识体系 tag
                    StartDetailPage.start(_mActivity,homeArticleData, Constants.RESULT_CODE_HOME_PAGE,Constants.ACTION_KNOWLEDGE_LEVEL2_ACTIVITY);
                    break;
            }
        });
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.getSquareArticleList();

    }



    @Override
    protected int getLayoutId() {
        return R.layout.square_fragment_layout;
    }

    @Override
    public void showSquareArticleData(boolean isLoadData, List<HomeArticleData> datas) {

        if(isLoadData){
            mHomeArticleDataList.addAll(datas);
        }else {
            mHomeArticleDataList.clear();
            mHomeArticleDataList.addAll(datas);
            mAdapter.replaceData(mHomeArticleDataList);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
        StartDetailPage.start(_mActivity,homeArticleData, Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }

    @Override
    public void showAddArticleCollectStatus(int position,HomeArticleData homeArticleData,String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }
    //显示收藏 或取消 收藏之后的状态
    private void showCollectStatus(int position,HomeArticleData homeArticleData,String msg){
        if(homeArticleData!=null && mAdapter!=null){
            mAdapter.setData(position,homeArticleData);
        }
        ToastUtils.showToast(msg);
    }

    @Override
    public void showCancelArticleCollectStatus(int position, HomeArticleData homeArticleData,String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }
}
