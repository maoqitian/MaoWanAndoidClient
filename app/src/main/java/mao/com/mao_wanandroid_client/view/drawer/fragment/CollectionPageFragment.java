package mao.com.mao_wanandroid_client.view.drawer.fragment;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseFragment;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionPageContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionPagePresenter;

/**
 * @author maoqitian
 * @Description: 我的收藏页面
 * @date 2019/8/20 0020 16:57
 */
public class CollectionPageFragment extends BaseFragment<CollectionPagePresenter> implements CollectionPageContract.CollectionPageView {

    @Override
    protected int getLayoutId() {
        return R.layout.collection_page_fragment_layout;
    }
}
