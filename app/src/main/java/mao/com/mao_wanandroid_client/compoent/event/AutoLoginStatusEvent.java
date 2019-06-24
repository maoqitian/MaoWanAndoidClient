package mao.com.mao_wanandroid_client.compoent.event;

/**
 * @author maoqitian
 * @Description: 自动登录状态 event
 * @date 2019/6/12 0012 9:49
 */
public class AutoLoginStatusEvent {

    private boolean isLogin;

    public AutoLoginStatusEvent(boolean islogin){
        this.isLogin = islogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

}
