package mao.com.mao_wanandroid_client.view.drawer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.modelbean.collect.CollectData;
import mao.com.mao_wanandroid_client.view.drawer.holder.CollectionViewItemHolder;

/**
 * @author maoqitian
 * @Description 收藏文章 Adapter
 * @Time 2019/7/28 0028 17:14
 */
public class CollectionAdapter extends BaseQuickAdapter<CollectData,CollectionViewItemHolder> {

    public CollectionAdapter(int layoutResId, @Nullable List<CollectData> data) {
        super(layoutResId, data);
    }

    public CollectionAdapter(@Nullable List<CollectData> data) {
        super(data);
    }

    public CollectionAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(CollectionViewItemHolder helper, CollectData item) {
        helper.setText(R.id.tv_collection_author_name,item.getAuthor())
                .setText(R.id.tv_collection_article_title,item.getTitle())
                .setText(R.id.collection_super_chapterName,item.getChapterName())
                .setText(R.id.tv_collection_date,item.getNiceDate())
                .addOnClickListener(R.id.more_collect)
                .addOnClickListener(R.id.tv_super_chapterName);

    }
}
