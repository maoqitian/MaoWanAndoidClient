package mao.com.mao_wanandroid_client.utils;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import mao.com.mao_wanandroid_client.R;

/**
 * @author maoqitian
 * @Description: AlertDialog
 * @date 2019/7/26 0026 10:16
 */
public class NormalAlertDialog {

    private AlertDialog.Builder alertDialog;

    Dialog bottomDialog;

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

    /**
     * 自定义 取消收藏 dialog
     * @param context 上下文
     * @param cancelCollectionOnClick 取消收藏点击回调
     */
    public void showBottomAlertDialog(Context context, View.OnClickListener cancelCollectionOnClick){
        if (context == null) {
            return;
        }
        if (bottomDialog == null) {
            bottomDialog = new Dialog(context, R.style.BottomDialog);
        }
            View contentView = LayoutInflater.from(context).inflate(R.layout.bottom_alert_dialog, null);
            bottomDialog.setContentView(contentView);
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
            contentView.setLayoutParams(layoutParams);
            Window window = bottomDialog.getWindow();
            if(window!=null){
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.BottomInAndOutStyle);
            }
            LinearLayout cancelCollection = contentView.findViewById(R.id.ll_cancel_collection);
            TextView tvCancelCancelDialog = contentView.findViewById(R.id.tv_cancel_dialog);
            tvCancelCancelDialog.setOnClickListener(v -> {
                if (bottomDialog != null) {
                    bottomDialog.cancel();
                    bottomDialog = null;
                }
            });
            cancelCollection.setOnClickListener(cancelCollectionOnClick);
            bottomDialog.show();
    }


    public void showAddCollectionDialog(Context context,OnClickAddCollectionListener listener){
        if (context == null) {
            return;
        }
        if (bottomDialog == null) {
            bottomDialog = new Dialog(context, R.style.BottomDialog);
        }
        View contentView = LayoutInflater.from(context).inflate(R.layout.add_collection_dialog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels-100;
        contentView.setLayoutParams(layoutParams);
        Window window = bottomDialog.getWindow();
        if(window!=null){
            window.setGravity(Gravity.CENTER);
            window.setWindowAnimations(R.style.BottomInAndOutStyle);
        }
        TextView confirmCollection = contentView.findViewById(R.id.tv_confirm);
        TextView tvCancelCancelDialog = contentView.findViewById(R.id.tv_cancel_close);
        TextView dialogTitle = contentView.findViewById(R.id.tv_dialog_title);
        EditText edCollectionTitle = contentView.findViewById(R.id.collection_title);
        EditText edCollectionAuthorName = contentView.findViewById(R.id.collection_author_name);
        EditText edCollectionLink = contentView.findViewById(R.id.collection_link);
        tvCancelCancelDialog.setOnClickListener(v -> {
            if (bottomDialog != null) {
                bottomDialog.cancel();
                bottomDialog = null;
            }
        });
        confirmCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.addCollection(edCollectionTitle.getText().toString().trim(),
                        edCollectionAuthorName.getText().toString().trim(),
                        edCollectionLink.getText().toString().trim());
            }
        });
        bottomDialog.show();
    }



    public void cancelBottomDialog(){
        if (bottomDialog != null) {
            bottomDialog.cancel();
            bottomDialog = null;
        }
    }


    /**
     * 接口回调的方式通知 对应界面获取对应数据进行收藏数据逻辑
     */
    private OnClickAddCollectionListener mListener;

    public void setOnClickAddCollectionListener(OnClickAddCollectionListener listener){
        this.mListener = listener;
    }
    public interface OnClickAddCollectionListener{
        void addCollection(String edCollectionTitle,String edCollectionAuthorName,String edCollectionLink);
    }



}
