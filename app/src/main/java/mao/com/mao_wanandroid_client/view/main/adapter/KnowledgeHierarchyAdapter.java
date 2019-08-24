package mao.com.mao_wanandroid_client.view.main.adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.flexibleflowlayout.TagAdapter;
import mao.com.flexibleflowlayout.TagFlowLayout;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.modelbean.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.view.main.holder.KnowledgeHolderView;

/**
 * @author maoqitian
 * @Description 知识体系 fragment 适配器
 * @Time 2019/5/23 0023 23:01
 */
public class KnowledgeHierarchyAdapter extends BaseQuickAdapter<KnowledgeHierarchyData, KnowledgeHolderView> {


    public KnowledgeHierarchyAdapter(int layoutResId, @Nullable List<KnowledgeHierarchyData> data) {
        super(layoutResId, data);
    }

    public KnowledgeHierarchyAdapter(@Nullable List<KnowledgeHierarchyData> data) {
        super(data);
    }

    public KnowledgeHierarchyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(KnowledgeHolderView helper, KnowledgeHierarchyData item) {
        helper.setText(R.id.tv_knowledge_title,item.getName());
        TagFlowLayout flowLayout = helper.getView(R.id.flow_layout);
        flowLayout.setMaxSelectCount(0);
        List<KnowledgeHierarchyData> children = item.getChildren();
        flowLayout.setAdapter(new TagAdapter() {
            @Override
            public int getItemCount() {
                return children.size();
            }

            @Override
            public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.flow_text_tag_layout,parent,false);
            }

            @Override
            public void bindView(View view, int position) {
                TextView viewTag = view.findViewById(R.id.flow_text_tag);
                viewTag.setText(children.get(position).getName());
            }
        });
    }
}
