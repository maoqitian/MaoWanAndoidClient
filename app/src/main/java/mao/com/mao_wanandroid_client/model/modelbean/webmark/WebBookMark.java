package mao.com.mao_wanandroid_client.model.modelbean.webmark;

import java.io.Serializable;

/**
 * @author maoqitian
 * @Description 常用网站 收藏网站
 * @Time 2018/9/27 0027 23:54
 */
public class WebBookMark implements Serializable {

    private static final long serialVersionUID = -4853818540891801685L;
    /**
     * desc :
     * icon :
     * id : 756
     * link : www.baidu.com
     * name : mao
     * order : 0
     * userId : 10473
     * visible : 1
     */

    private String desc;
    private String icon;
    private int id;
    private String link;
    private String name;
    private int order;
    private int userId;
    private int visible;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "WebBookMark{" +
                "desc='" + desc + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", userId=" + userId +
                ", visible=" + visible +
                '}';
    }
}
