package mao.com.mao_wanandroid_client.view.main.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.view.main.hloder.HomePageViewItemHolder;

/**
 * @author maoqitian
 * @Description 首页推荐  RecyclerView Adapter
 * 使用 BRVAH 库的 Adapter https://www.jianshu.com/p/b343fcff51b0
 * @Time 2019/5/18 0018 21:31
 */
public class HomePageAdapter extends BaseQuickAdapter<HomeArticleData, HomePageViewItemHolder> {


    public HomePageAdapter(int layoutResId, @Nullable List<HomeArticleData> data) {
        super(layoutResId, data);
    }

    public HomePageAdapter(@Nullable List<HomeArticleData> data) {
        super(data);
    }

    public HomePageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(HomePageViewItemHolder helper, HomeArticleData item) {
         helper.setText(R.id.tv_author_name,item.getAuthor());
         helper.setText(R.id.tv_artical_title,item.getTitle());
         helper.setText(R.id.tv_super_chapterName,item.getSuperChapterName());
         helper.setText(R.id.tv_chapterName,item.getChapterName());
         helper.setText(R.id.tv_artical_date,item.getNiceDate());
    }
}
