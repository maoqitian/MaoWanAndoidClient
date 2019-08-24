package mao.com.mao_wanandroid_client.view.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.view.main.holder.OfficialAccountsViewHolder;

/**
 * @author maoqitian
 * @Description 导航 fragment 适配器
 * @Time 2019/5/23 0023 23:01
 */
public class OfficialAccountsAdapter extends BaseQuickAdapter<KnowledgeHierarchyData, OfficialAccountsViewHolder> {


    public OfficialAccountsAdapter(int layoutResId, @Nullable List<KnowledgeHierarchyData> data) {
        super(layoutResId, data);
    }

    public OfficialAccountsAdapter(@Nullable List<KnowledgeHierarchyData> data) {
        super(data);
    }

    public OfficialAccountsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(OfficialAccountsViewHolder helper, KnowledgeHierarchyData item) {
        helper.setText(R.id.tv_wx_name,item.getName());
    }
}
