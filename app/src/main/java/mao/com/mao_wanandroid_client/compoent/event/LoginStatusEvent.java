package mao.com.mao_wanandroid_client.compoent.event;

/**
 * @author maoqitian
 * @Description: 登录状态 event
 * @date 2019/6/12 0012 9:49
 */
public class LoginStatusEvent {

    private boolean isLogin;

    private boolean isSignOut; //退出登录

    public LoginStatusEvent(boolean islogin,boolean signout){
        this.isLogin = islogin;
        this.isSignOut = signout;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isSignOut() {
        return isSignOut;
    }

    public void setSignOut(boolean signOut) {
        isSignOut = signOut;
    }

}
