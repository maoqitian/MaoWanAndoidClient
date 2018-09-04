package mao.com.mao_wanandroid_client.utils;

import com.google.gson.JsonIOException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * @author maoqitian
 * @Description 异常处理类
 * @Time 2018/9/4 0004 22:55
 */
public class RxExceptionUtils {
    public static String exceptionHandler(Throwable throwable){
        String errorMsg="未知异常";
        if(throwable instanceof UnknownHostException){
           errorMsg="网络不可用";
        }else if(throwable instanceof SocketTimeoutException){
           errorMsg="网络连接超时";
        }else if(throwable instanceof HttpException){
            HttpException httpException= (HttpException) throwable;
           errorMsg=convertStatusCode(httpException);
        }else if(throwable instanceof ParseException || throwable instanceof JSONException
                || throwable instanceof JsonIOException){
            errorMsg = "数据解析错误";
        }
        return errorMsg;
    }

    /**
     * 网络异常类型处理类
     * @param httpException
     * @return
     */
    private static String convertStatusCode(HttpException httpException) {
        String msg;
        if(httpException.code()>=500 && httpException.code()<600){
           msg ="服务器处理请求错误";
        }else if(httpException.code()>=400 && httpException.code()<500){
           msg ="服务器无法处理请求";
        }else {
            msg =httpException.message();
        }
        return msg;
    }
}
