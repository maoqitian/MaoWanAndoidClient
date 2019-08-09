package mao.com.mao_wanandroid_client.model.http.control;

import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import mao.com.mao_wanandroid_client.model.modelbean.ResponseBody;
import mao.com.mao_wanandroid_client.utils.RxExceptionUtils;

/**
 * @author maoqitian
 * @Description BaseObserver
 * @Time 2018/9/4 0004 22:42
 */
public abstract class BaseObserver<T> implements Observer<ResponseBody<T>> {

    public abstract void onSuccess(T result);
    public abstract void onFailure(Throwable e, String errorMsg);


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(@Nullable ResponseBody<T> t) {
        if(t.getErrorCode()!=0){
           onFailure(new Exception(t.getErrorMsg()), t.getErrorMsg());
        }else {
           onSuccess(t.getData());
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
       onFailure(e, RxExceptionUtils.exceptionHandler(e));
    }
}
