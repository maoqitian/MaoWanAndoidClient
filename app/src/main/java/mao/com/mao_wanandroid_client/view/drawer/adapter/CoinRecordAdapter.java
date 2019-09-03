package mao.com.mao_wanandroid_client.view.drawer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.modelbean.rank.CoinRecordData;
import mao.com.mao_wanandroid_client.view.drawer.holder.CoinRecordViewItemHolder;


/**
 * @author maoqitian
 * @Description 积分记录 Adapter
 * @Time 2019/7/28 0028 17:14
 */
public class CoinRecordAdapter extends BaseQuickAdapter<CoinRecordData, CoinRecordViewItemHolder> {


    public CoinRecordAdapter(int layoutResId, @Nullable List<CoinRecordData> data) {
        super(layoutResId, data);
    }

    public CoinRecordAdapter(@Nullable List<CoinRecordData> data) {
        super(data);
    }

    public CoinRecordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(CoinRecordViewItemHolder helper, CoinRecordData item) {
         helper.setText(R.id.tv_record_text,item.getDesc());
    }
}
