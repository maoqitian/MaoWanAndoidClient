package mao.com.mao_wanandroid_client.view.main.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.view.main.hloder.HomePageViewItemHolder;

/**
 * @author maoqitian
 * @Description 首页 RecyclerView Adapter
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

    }
}
