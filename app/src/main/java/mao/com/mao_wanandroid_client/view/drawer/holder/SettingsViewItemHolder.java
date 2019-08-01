package mao.com.mao_wanandroid_client.view.drawer.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import mao.com.mao_wanandroid_client.R;

/**
 * @author maoqitian
 * @Description 设置 列表 item Holder
 * @Time 2019/5/19 0019 18:13
 */
public class SettingsViewItemHolder extends BaseViewHolder {

    @BindView(R.id.tv_setting_item_title)
    TextView mItemTitle;
    @BindView(R.id.line)
    TextView mItemLine;
    @BindView(R.id.iv_chevron_right)
    ImageView mChevronRight;
    @BindView(R.id.setting_switch)
    Switch mSettingSwitch;
    @BindView(R.id.tv_version_Name)
    TextView mVersionName;

    public SettingsViewItemHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
