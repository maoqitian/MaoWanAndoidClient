package mao.com.mao_wanandroid_client.compoent.event;

/**
 * @author maoqitian
 * @Description: 主题切换事件
 * @date 2019/8/2 0002 11:26
 */
public class ThemeModeEvent {

    int mode;

    public ThemeModeEvent(int mode){
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
