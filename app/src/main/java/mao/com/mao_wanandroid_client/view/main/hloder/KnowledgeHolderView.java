package mao.com.mao_wanandroid_client.view.main.hloder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.widget.flowlayout.FlowLayout;

/**
 * @author maoqitian
 * @Description 知识体系 holder
 * @Time 2019/5/18 0018 22:30
 */
public class KnowledgeHolderView extends BaseViewHolder {

    @BindView(R.id.tv_knowledge_title)
    TextView mKnowledgeTitle;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;

    public KnowledgeHolderView(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }

}
