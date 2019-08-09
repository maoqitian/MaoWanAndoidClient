package mao.com.mao_wanandroid_client.model.modelbean.login;

import java.util.List;

/**
 * @author maoqitian
 * @Description 登录 登录后会在cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证
 * @Time 2018/9/13 0013 23:28
 */
public class LoginData {

    /**
     * collectIds : []
     * email : maoqitian068@163.com
     * icon :
     * id : 863
     * password :
     * token :
     * type : 0
     * username : maoqitian
     */

    private String email;
    private String icon;
    private int id;
    private String password;
    private String token;
    private int type;
    private String username;
    private List<Integer> collectIds;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
