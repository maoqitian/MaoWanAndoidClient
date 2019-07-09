package mao.com.mao_wanandroid_client.view.main.adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.knowlegetree.KnowledgeHierarchyData;
import mao.com.mao_wanandroid_client.model.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.view.main.hloder.KnowledgeHolderView;
import mao.com.mao_wanandroid_client.view.main.hloder.NavigationHolderView;
import mao.com.mao_wanandroid_client.widget.FlowLayout;

/**
 * @author maoqitian
 * @Description 导航 fragment 适配器
 * @Time 2019/5/23 0023 23:01
 */
public class NavigationViewAdapter extends BaseQuickAdapter<NavigationListData, NavigationHolderView> {


    public NavigationViewAdapter(int layoutResId, @Nullable List<NavigationListData> data) {
        super(layoutResId, data);
    }

    public NavigationViewAdapter(@Nullable List<NavigationListData> data) {
        super(data);
    }

    public NavigationViewAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(NavigationHolderView helper, NavigationListData item) {
        /*helper.setText(R.id.tv_nav_article_title,item.getTitle());
        helper.setText(R.id.tv_nav_article_date,item.getNiceDate());*/
    }
}
