package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.webBookMark;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionWebContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionWebPresenter;

/**
 * @author maoqitian
 * @Description: 收藏网站
 * @date 2019/8/20 0020 17:03
 */
public class CollectionWebFragment extends BaseFragment<CollectionWebPresenter> implements CollectionWebContract.CollectionWeb {

    List<webBookMark> mCollectionWebDataList;

    @BindView(R.id.collection_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.collection_smartrefreshlayout)
    SmartRefreshLayout mSmartRefreshLayout;

    private RecyclerView.LayoutManager layoutManager;



    public static CollectionWebFragment newInstance() {

        Bundle args = new Bundle();

        CollectionWebFragment fragment = new CollectionWebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.collection_fragment_layout;
    }

    @Override
    public void showCollectionWebData(List<webBookMark> collectionWebDataList) {

    }
}
