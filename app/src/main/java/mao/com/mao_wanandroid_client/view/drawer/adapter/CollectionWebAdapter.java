package mao.com.mao_wanandroid_client.view.drawer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.webBookMark;
import mao.com.mao_wanandroid_client.view.drawer.holder.CollectionWebItemHolder;

/**
 * @author maoqitian
 * @Description 收藏网站 Adapter
 * @Time 2019/8/20 0020 23:29
 */
public class CollectionWebAdapter extends BaseQuickAdapter<webBookMark, CollectionWebItemHolder> {


    public CollectionWebAdapter(int layoutResId, @Nullable List<webBookMark> data) {
        super(layoutResId, data);
    }

    public CollectionWebAdapter(@Nullable List<webBookMark> data) {
        super(data);
    }

    public CollectionWebAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(CollectionWebItemHolder helper, webBookMark item) {
          helper.setText(R.id.tv_collection_web_article_title,item.getName());
    }
}
