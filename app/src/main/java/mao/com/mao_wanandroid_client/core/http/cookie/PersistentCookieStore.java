package mao.com.mao_wanandroid_client.core.http.cookie;

/**
 * @author maoqitian
 * @Description
 * @Time 2018/12/5 0005 20:41
 *  A persistent cookie store which implements the Apache HttpClient CookieStore
 *  interface.Cookies are stored and will persist on the user's device between
 *  application sessions since they are serialized and stored in SharedPreferences.
 *  Instances of this class are * designed to be used with AsyncHttpClient#setCookieStore,
 *  but can also be used with a regular old apache HttpClient/HttpContext if you prefer.
 */
public class PersistentCookieStore {

}
