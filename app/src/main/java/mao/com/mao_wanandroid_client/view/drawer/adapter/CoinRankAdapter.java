package mao.com.mao_wanandroid_client.view.drawer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;
import mao.com.mao_wanandroid_client.view.drawer.holder.CoinRankViewItemHolder;


/**
 * @author maoqitian
 * @Description 积分排行榜 Adapter
 * @Time 2019/7/28 0028 17:14
 */
public class CoinRankAdapter extends BaseQuickAdapter<RankData, CoinRankViewItemHolder> {


    public CoinRankAdapter(int layoutResId, @Nullable List<RankData> data) {
        super(layoutResId, data);
    }

    public CoinRankAdapter(@Nullable List<RankData> data) {
        super(data);
    }

    public CoinRankAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(CoinRankViewItemHolder helper, RankData item) {
         helper.setText(R.id.tv_rank_des,item.getUsername()+" 积分："+item.getCoinCount());
    }
}
