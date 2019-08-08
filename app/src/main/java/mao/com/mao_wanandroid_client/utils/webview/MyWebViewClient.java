package mao.com.mao_wanandroid_client.utils.webview;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author maoqitian
 * @Description:
 * @date 2019/8/8 0008 11:09
 */
public class MyWebViewClient extends WebViewClient {
    private IWebPageView mIWebPageView;

    public MyWebViewClient(IWebPageView mIWebPageView) {
        this.mIWebPageView = mIWebPageView;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e("毛麒添", "----url:" + url);
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        return mIWebPageView.isOpenThirdApp(url);
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        // html加载完成之后，添加监听图片的点击js函数
        mIWebPageView.onPageFinished(view, url);
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if (errorCode == 404) {
            //用javascript隐藏系统定义的404页面信息
            String data = "Page NO FOUND！";
            view.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");
        }
    }

    // 视频全屏播放按返回页面被放大的问题
    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
        if (newScale - oldScale > 7) {
            view.setInitialScale((int) (oldScale / newScale * 100)); //异常放大，缩回去。
        }
    }
}
