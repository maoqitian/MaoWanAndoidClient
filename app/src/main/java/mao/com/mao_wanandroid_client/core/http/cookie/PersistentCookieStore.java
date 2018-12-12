package mao.com.mao_wanandroid_client.core.http.cookie;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mao.com.mao_wanandroid_client.application.MyApplication;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * @author maoqitian
 * @Description Cookie缓存持久化实现类
 * @Time 2018/12/5 0005 20:41
 *  A persistent cookie store which implements the Apache HttpClient CookieStore
 *  interface.Cookies are stored and will persist on the user's device between
 *  application sessions since they are serialized and stored in SharedPreferences.
 *  Instances of this class are * designed to be used with AsyncHttpClient#setCookieStore,
 *  but can also be used with a regular old apache HttpClient/HttpContext if you prefer.
 */
public class PersistentCookieStore {

    private static final String LOG_TAG = "PersistentCookieStore";
    private static final String COOKIE_PREFS = "CookiePrefsFile";
    private static final String COOKIE_NAME_PREFIX = "cookie_";
    //存放 cookie HashMap
    private final HashMap<String, ConcurrentHashMap<String, Cookie>> mCookies;
    //cookie 持久化
    private final SharedPreferences mCookiePrefs;


    public PersistentCookieStore(){
        mCookiePrefs=MyApplication.getInstance().getSharedPreferences(COOKIE_PREFS,0);
        //Load any previously stored cookies into the store
        mCookies=new HashMap<>();

        Map<String, ?> prefsAll = mCookiePrefs.getAll();
        for (Map.Entry<String,?> entry:prefsAll.entrySet()){
            if(null != entry.getValue() && !((String)entry.getValue()).startsWith(COOKIE_NAME_PREFIX)){
                String[] cookNames = TextUtils.split((String) entry.getValue(), ",");
                for (String cookName : cookNames){
                    String encodedCookie = mCookiePrefs.getString(COOKIE_NAME_PREFIX + cookName, null);
                    if(encodedCookie != null){
                        Cookie decodedCookie=decodedCookie(encodedCookie);
                        if(decodedCookie != null){
                            if(!mCookies.containsKey(entry.getKey())){
                                //如果mCookies 不存在则 保存 cookie key value
                                mCookies.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                                mCookies.get(entry.getKey()).put(cookName, decodedCookie);
                            }
                        }
                    }
                }
            }
        }
    }

    public void add(HttpUrl httpUrl, List<Cookie> cookies) {
        if (null != cookies && cookies.size() > 0) {
            for (Cookie cookie : cookies) {
                add(cookie);
            }
        }
    }

    private void add(Cookie cookie) {
        //应该以 Cookie 的 domain 来做缓存 Key 才合适，解决无法子跨域名使用问题
        if(!mCookies.containsKey(cookie.domain()))
            mCookies.put(cookie.domain(), new ConcurrentHashMap<String, Cookie>());
        if(cookie.expiresAt() > System.currentTimeMillis()){
            mCookies.get(cookie.domain()).put(cookie.name(), cookie);
        }else {
            mCookies.get(cookie.domain()).remove(cookie.domain());
        }
        //cookie 数据持久化本地
        SharedPreferences.Editor prefsWriter = mCookiePrefs.edit();
        prefsWriter.putString(cookie.domain(), TextUtils.join(",", mCookies.get(cookie.domain()).keySet()));
        prefsWriter.putString(COOKIE_NAME_PREFIX + cookie.name(), encodedCookie(new SerializableHttpCookie(cookie)));
        prefsWriter.apply();
    }

    public List<Cookie> get(HttpUrl url) {
        ArrayList<Cookie> ret = new ArrayList<>();
        for (String key : mCookies.keySet()) {
            if (url.host().contains(key)) {
                ret.addAll(mCookies.get(key).values());
            }
        }
        return ret;
    }

    /**
     * 把 cookie 序列化成 string
     * @param serializableHttpCookie
     * @return
     */
    private String encodedCookie(SerializableHttpCookie serializableHttpCookie) {
        if(serializableHttpCookie == null) return null;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        try {
            ObjectOutputStream os=new ObjectOutputStream(bos);
            os.writeObject(serializableHttpCookie);
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in encodedCookie", e);
            e.printStackTrace();
        }
        return byteArrayToHexString(bos.toByteArray());
    }

    /**
     * 将 string 反序列化成 cookie
     * @param encodeCookie
     * @return
     */
    private Cookie decodedCookie(String encodeCookie) {
        byte[] bytes=hexStringToByteArray(encodeCookie);
        ByteArrayInputStream bis=new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            cookie = ((SerializableHttpCookie) ois.readObject()).getCookie();
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in decodeCookie", e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e);
            e.printStackTrace();
        }
        return cookie;
    }

    /**
     * Converts hex values from strings to byte arra
     * 十六进制字符串转二进制数组
     * @param encodeCookie
     * @return
     */
    private byte[] hexStringToByteArray(String encodeCookie) {
        int length = encodeCookie.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(encodeCookie.charAt(i), 16) << 4) + Character.digit(encodeCookie.charAt(i + 1), 16));
        }
        return bytes;
    }

    /**
     * 二进制数组转十六进制字符串
     * Using some super basic byte array <-> hex conversions so we don't have to rely on any
     * large Base64 libraries. Can be overridden if you like!
     * @param bytes
     * @return
     */
    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder(bytes.length*2);
        for(byte element : bytes){
            int v = element & 0xff;
            if (v < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(v));
        }
        return stringBuilder.toString().toUpperCase(Locale.US);
    }
}
