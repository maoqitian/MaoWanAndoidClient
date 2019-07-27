package mao.com.mao_wanandroid_client.view.drawer.fragment;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.model.collect.CollectData;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionPresenter;

/**
 * @author maoqitian
 * @Description: 收藏 Fragment
 * @date 2019/7/26 0026 15:50
 */
public class CollectionFragment extends BaseFragment<CollectionPresenter> implements CollectionContract.CollectionView {




    @Override
    protected int getLayoutId() {
        return R.layout.collection_fragment_layout;
    }

    @Override
    public void showCollectListData(List<CollectData> collectDataList) {

    }
}
