package mao.com.mao_wanandroid_client.view.drawer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;
import mao.com.mao_wanandroid_client.view.drawer.holder.CollectionWebItemHolder;
import mao.com.mao_wanandroid_client.view.drawer.holder.PrivateArticleItemHolder;

/**
 * @author maoqitian
 * @Description 个人分享文章 Adapter
 * @Time 2019/8/20 0020 23:29
 */
public class PrivateArticleAdapter extends BaseQuickAdapter<HomeArticleData, PrivateArticleItemHolder> {




    //是否为个人分享文章列表 控制是否显示删除按钮
    boolean isPrivate=false;

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public PrivateArticleAdapter(int layoutResId, @Nullable List<HomeArticleData> data) {
        super(layoutResId, data);
    }

    public PrivateArticleAdapter(@Nullable List<HomeArticleData> data) {
        super(data);
    }

    public PrivateArticleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(PrivateArticleItemHolder helper, HomeArticleData item) {
        helper.setText(R.id.tv_private_article_title,item.getTitle())
              .setText(R.id.tv_private_article_date,item.getNiceShareDate())
              .addOnClickListener(R.id.iv_delete_article);

        helper.setVisible(R.id.iv_delete_article,isPrivate);
    }
}
