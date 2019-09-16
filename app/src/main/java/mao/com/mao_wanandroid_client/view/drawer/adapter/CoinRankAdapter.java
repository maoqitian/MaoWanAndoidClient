package mao.com.mao_wanandroid_client.view.drawer.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.modelbean.rank.RankData;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.drawer.holder.CoinRankViewItemHolder;


/**
 * @author maoqitian
 * @Description 积分排行榜 Adapter
 * @Time 2019/7/28 0028 17:14
 */
public class CoinRankAdapter extends BaseQuickAdapter<RankData, CoinRankViewItemHolder> {

    //用户名
    String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
        int layoutPosition = helper.getLayoutPosition();
        if(!TextUtils.isEmpty(userName)){
            if(userName.regionMatches(3,item.getUsername(),3,userName.length()-3)){
                helper.setTextColor(R.id.tv_rank_des, ContextCompat.getColor(mContext, R.color.coin_text_color));
                helper.setTextColor(R.id.tv_rank_coin, ContextCompat.getColor(mContext, R.color.coin_text_color));
                helper.setBackgroundRes(R.id.tv_rank_coin,R.drawable.tag_rank_blue_background);
            }else {
                helper.setTextColor(R.id.tv_rank_des, ContextCompat.getColor(mContext, R.color.textColorPrimary));
                helper.setTextColor(R.id.tv_rank_coin, ContextCompat.getColor(mContext, R.color.textColorPrimary));
                helper.setBackgroundRes(R.id.tv_rank_coin,R.drawable.tag_rank_gray_background);
            }
        }
        helper.setText(R.id.tv_rank_des,(layoutPosition+1)+". "+item.getUsername()+" 积分："+item.getCoinCount())
              .setText(R.id.tv_rank_coin,"lv "+ ToolsUtils.getRank(item.getCoinCount()));
    }
}
