package mao.com.mao_wanandroid_client.model.http.cookie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import okhttp3.Cookie;

/**
 * @author maoqitian
 * @Description Cookie 对象 仿照android-async-http的SerializableCookie实现，
 * 用处是cookie对象与对象流的互转，保存和读取cookie
 * @Time 2018/12/5 0005 20:41
 */
public class SerializableHttpCookie implements Serializable {
    private static final long serialVersionUID = -179940503582201675L;
    /**
     * transient 关键字 防止属性被序列化 影响性能
     */
    private transient final Cookie cookie;
    private transient Cookie clientCookie;

    public SerializableHttpCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public Cookie getCookie(){
        Cookie bestCookie=cookie;
        if(clientCookie!=null){
            bestCookie=clientCookie;
        }
        return bestCookie;
    }
    /** 将cookie写到对象流中 */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(cookie.name());
        out.writeObject(cookie.value());
        out.writeLong(cookie.expiresAt());
        out.writeObject(cookie.domain());
        out.writeObject(cookie.path());
        out.writeBoolean(cookie.secure());
        out.writeBoolean(cookie.httpOnly());
        out.writeBoolean(cookie.hostOnly());
        out.writeBoolean(cookie.persistent());
    }

    /** 从对象流中构建cookie对象 */
    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        long expiresAt = (long) in.readLong();
        String domain = (String) in.readObject();
        String path = (String) in.readObject();
        boolean secure = in.readBoolean();
        boolean httpOnly = in.readBoolean();
        boolean hostOnly = in.readBoolean();
        boolean persistent = in.readBoolean();
        Cookie.Builder builder = new Cookie.Builder();
        builder = builder.name(name)
                       .value(value)
                       .expiresAt(expiresAt)
                       .path(path);
        builder = hostOnly?builder.hostOnlyDomain(domain):builder.domain(domain);
        builder = secure ? builder.secure() : builder;
        builder = httpOnly ? builder.httpOnly() : builder;
        this.clientCookie=builder.build();
    }
}
