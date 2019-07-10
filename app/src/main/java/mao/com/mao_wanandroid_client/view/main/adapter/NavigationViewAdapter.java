package mao.com.mao_wanandroid_client.view.main.adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.view.main.hloder.NavigationHolderView;
import mao.com.mao_wanandroid_client.widget.flowlayout.FlowLayout;
import mao.com.mao_wanandroid_client.widget.flowlayout.TagAdapter;
import mao.com.mao_wanandroid_client.widget.flowlayout.TagFlowLayout;

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
        helper.setText(R.id.tv_nav_article_title,item.getName());
        TagFlowLayout flowLayout = helper.getView(R.id.nav_flow_layout);
        flowLayout.setAdapter(new TagAdapter() {
            @Override
            public int getItemCount() {
                return item.getArticles().size();
            }

            @Override
            public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.flow_text_tag_layout,parent,false);
            }

            @Override
            public void bindView(View view, int position) {
                TextView viewTag = view.findViewById(R.id.flow_text_tag);
                viewTag.setText(item.getArticles().get(position).getTitle());
            }
        });
        /*List<HomeArticleData> childrens = item.getArticles();
        if(childrens.size()>0){
            flowLayout.removeAllViews();
            for (HomeArticleData homeArticleData:childrens) {
                TextView viewTag = (TextView) LayoutInflater.from(mContext).inflate(R.layout.flow_text_tag_layout, flowLayout, false);
                viewTag.setText(homeArticleData.getTitle());
                flowLayout.addView(viewTag);
            }
        }*/
    }
}
