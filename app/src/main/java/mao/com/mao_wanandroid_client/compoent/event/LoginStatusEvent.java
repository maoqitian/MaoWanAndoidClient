package mao.com.mao_wanandroid_client.compoent.event;

/**
 * @author maoqitian
 * @Description: 登录状态 event
 * @date 2019/6/12 0012 9:49
 */
public class LoginStatusEvent {

    private boolean isLogin;

    public LoginStatusEvent(boolean islogin){
        this.isLogin = islogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

}
