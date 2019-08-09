package mao.com.mao_wanandroid_client.model.http.control;

import android.app.ProgressDialog;
import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * @author maoqitian
 * @Description 基础数据的返回加入Dialog封装
 * @Time 2018/9/4 0004 23:22
 */
public abstract class ProgressObserver<T> extends BaseObserver<T> {

    private ProgressDialog progressDialog;
    private Context mContext;
    private String mLoadingText;

    public ProgressObserver(Context context){
         this(context,null);
    }

    public ProgressObserver(Context context,String loadingText){
         this.mContext=context;
         this.mLoadingText=loadingText;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(!d.isDisposed()){
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage(mLoadingText == null ? "正在加载中..." : mLoadingText);
            progressDialog.show();
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
