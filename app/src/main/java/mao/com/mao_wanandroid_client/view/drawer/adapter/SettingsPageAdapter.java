package mao.com.mao_wanandroid_client.view.drawer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.model.setting.SettingData;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.drawer.holder.SettingsViewItemHolder;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/7/31 0031 20:27
 */
public class SettingsPageAdapter extends BaseQuickAdapter<SettingData, SettingsViewItemHolder> {
    public SettingsPageAdapter(int layoutResId, @Nullable List<SettingData> data) {
        super(layoutResId, data);
    }

    public SettingsPageAdapter(@Nullable List<SettingData> data) {
        super(data);
    }

    public SettingsPageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(SettingsViewItemHolder helper, SettingData item) {
        helper.setText(R.id.tv_setting_item_title,item.getmName());
        if(item.ismIsSwitch()){
           helper.setVisible(R.id.setting_switch,true);
        }else {
            helper.setVisible(R.id.iv_chevron_right,true);
        }
        if(Constants.SETTINGS_VERSION_TYPE.equals(item.getType())){
            helper.setVisible(R.id.tv_version_Name,true);
            helper.setText(R.id.tv_version_Name,ToolsUtils.getVersion(mContext));
        }
    }
}
