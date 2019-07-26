package mao.com.mao_wanandroid_client.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * @author maoqitian
 * @Description: AlertDialog
 * @date 2019/7/26 0026 10:16
 */
public class NormalAlertDialog {

    private AlertDialog.Builder alertDialog;

    //双重效验锁实现单例
    private static volatile NormalAlertDialog mInstance;

    public static NormalAlertDialog getInstance() {
        if(mInstance==null){
            synchronized (NormalAlertDialog.class){
                if(mInstance==null){
                    mInstance=new NormalAlertDialog();
                }
            }
        }
        return mInstance;
    }

    /**
     *
     * @param context 上下文
     * @param dialogMessage dialog 信息
     * @param positiveText 确认
     * @param cancelText 取消
     * @param positiveOnClick 确认 点击回调
     * @param cancelOnClick 取消 点击回调
     */
    public void showAlertDialog(Context context,String dialogMessage,String positiveText, String cancelText, DialogInterface.OnClickListener positiveOnClick, DialogInterface.OnClickListener cancelOnClick ){
        if (context == null) {
            return;
        }
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(context);
        }
        alertDialog.setCancelable(false);
        alertDialog.setMessage(dialogMessage);
        alertDialog.setPositiveButton(positiveText,positiveOnClick);
        alertDialog.setNegativeButton(cancelText,cancelOnClick);
        alertDialog.create().show();
    }
}
