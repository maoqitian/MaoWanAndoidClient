package mao.com.mao_wanandroid_client.view.main.holder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.widget.CircleImageView;

/**
 * @author maoqitian
 * @Description 微信公众号 holder
 * @Time 2019/5/18 0018 22:30
 */
public class OfficialAccountsViewHolder extends BaseViewHolder {

    @BindView(R.id.image_wx_icon)
    CircleImageView circleImageView;
    @BindView(R.id.tv_wx_name)
    TextView tvWxName;

    public OfficialAccountsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }

}
