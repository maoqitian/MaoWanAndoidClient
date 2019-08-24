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
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.model.modelbean.navigation.NavigationListData;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.view.main.holder.NavigationHolderView;

/**
 * @author maoqitian
 * @Description 导航 fragment 适配器
 * @Time 2019/5/23 0023 23:01
 */
public class NavigationViewAdapter extends BaseQuickAdapter<NavigationListData, NavigationHolderView> {

    TagAdapter tagAdapter;
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
        //设置不可选择
        flowLayout.setMaxSelectCount(0);
        flowLayout.setAdapter(tagAdapter=new TagAdapter() {
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
            //每个 tag 点击事件
            @Override
            public void onTagItemViewClick(View v, int position) {
                super.onTagItemViewClick(v, position);
                //Toast.makeText(mContext,flowLayout.getSelectPositionItemList().toString(),Toast.LENGTH_SHORT).show();
                StartDetailPage.start(mContext,item.getArticles().get(position), Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
            }
        });
    }
}
