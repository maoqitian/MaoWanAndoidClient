package mao.com.mao_wanandroid_client.compoent.event;

import mao.com.mao_wanandroid_client.model.modelbean.collect.CollectData;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;

/**
 * @author maoqitian
 * @Description 收藏  网站  文章 事件
 * @Time 2019/8/25 0025 11:00
 */
public class CollectionWebArticleEvent {

    private WebBookMark webBookMark;
    private CollectData collectData;

    private String dialogType;
    private boolean isAdd;
    private int position;

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    private String mMsg;

    public CollectionWebArticleEvent(int error,String msg){
        this.errorCode = error;
        this.mMsg = msg;
    }

    public WebBookMark getWebBookMark() {
        return webBookMark;
    }

    public void setWebBookMark(WebBookMark webBookMark) {
        this.webBookMark = webBookMark;
    }

    public CollectData getCollectData() {
        return collectData;
    }

    public void setCollectData(CollectData collectData) {
        this.collectData = collectData;
    }

    public String getDialogType() {
        return dialogType;
    }

    public void setDialogType(String dialogType) {
        this.dialogType = dialogType;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
