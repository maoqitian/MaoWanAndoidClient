package mao.com.mao_wanandroid_client.core.http.cookie;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author maoqitian
 * @Description cookie 管理类 继承
 * @Time 2018/12/5 0005 20:41
 */
public class CookieManager implements CookieJar {

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return null;
    }

}
