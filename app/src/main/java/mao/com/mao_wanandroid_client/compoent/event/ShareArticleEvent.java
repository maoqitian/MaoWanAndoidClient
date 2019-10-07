package mao.com.mao_wanandroid_client.compoent.event;

/**
 * @Description:
 * @Author: maoqitian
 * @Date: 2019-10-07 00:19
 */
public class ShareArticleEvent {
    private boolean mIsShareSuccess;

    private String mMsg;

    public ShareArticleEvent(boolean isShareSuccess,String msg){
        this.mIsShareSuccess = isShareSuccess;
        this.mMsg = msg;
    }


    public boolean ismIsShareSuccess() {
        return mIsShareSuccess;
    }

    public void setmIsShareSuccess(boolean mIsShareSuccess) {
        this.mIsShareSuccess = mIsShareSuccess;
    }
    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }
}
