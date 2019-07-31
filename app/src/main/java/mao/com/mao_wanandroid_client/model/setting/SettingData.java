package mao.com.mao_wanandroid_client.model.setting;

/**
 * @author maoqitian
 * @Description 设置数据
 * @Time 2019/7/31 0031 20:19
 */
public class SettingData {

    String mName;
    boolean mIsSwitch;

    public SettingData (String name,boolean isSwitch){
        this.mName = name;
        this.mIsSwitch = isSwitch;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean ismIsSwitch() {
        return mIsSwitch;
    }

    public void setmIsSwitch(boolean mIsSwitch) {
        this.mIsSwitch = mIsSwitch;
    }
}
