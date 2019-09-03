package mao.com.mao_wanandroid_client.view.drawer.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import mao.com.mao_wanandroid_client.R;

/**
 * @author maoqitian
 * @Description 积分获取列表 item Holder
 * @Time 2019/5/19 0019 18:13
 */
public class CoinRecordViewItemHolder extends BaseViewHolder {


    @BindView(R.id.tv_record_text)
    TextView mTvRecord;

    public CoinRecordViewItemHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
