package mao.com.mao_wanandroid_client.view.main.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import mao.com.flexibleflowlayout.TagFlowLayout;
import mao.com.mao_wanandroid_client.R;

/**
 * @author maoqitian
 * @Description 导航 holder
 * @Time 2019/5/18 0018 22:30
 */
public class NavigationHolderView extends BaseViewHolder {

    @BindView(R.id.tv_nav_article_title)
    TextView mNavgationTitle;
    @BindView(R.id.nav_flow_layout)
    TagFlowLayout mFlowLayout;

    public NavigationHolderView(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }

}
