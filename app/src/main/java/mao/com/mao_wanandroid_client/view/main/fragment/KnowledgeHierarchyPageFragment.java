package mao.com.mao_wanandroid_client.view.main.fragment;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.RootBaseFragment;
import mao.com.mao_wanandroid_client.presenter.main.KnowledgeHierarchyContract;
import mao.com.mao_wanandroid_client.presenter.main.KnowledgeHierarchyPresenter;

/**
 * @author maoqitian
 * @Description: 知识体系 Fragment
 * @date 2019/5/7 0007 11:43
 */
public class KnowledgeHierarchyPageFragment extends RootBaseFragment<KnowledgeHierarchyPresenter> implements KnowledgeHierarchyContract.KnowledgeHierarchyView {

    @Override
    protected int getLayoutId() {
        return R.layout.knowledge_hierarchy_fragment_layout;
    }
}
