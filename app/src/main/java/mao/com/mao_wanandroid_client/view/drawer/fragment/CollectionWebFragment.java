package mao.com.mao_wanandroid_client.view.drawer.fragment;

import java.util.List;

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


    @Override
    protected int getLayoutId() {
        return R.layout.collection_web_fragment_layout;
    }

    @Override
    public void showCollectionWebData(List<webBookMark> collectionWebDataList) {

    }
}
