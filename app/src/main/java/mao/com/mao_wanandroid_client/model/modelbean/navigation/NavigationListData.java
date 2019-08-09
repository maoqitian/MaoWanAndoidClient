package mao.com.mao_wanandroid_client.model.modelbean.navigation;

import java.io.Serializable;
import java.util.List;

import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;

/**
 * @author maoqitian
 * @Description 导航数据
 * @Time 2018/9/6 0006 23:42
 */
public class NavigationListData implements Serializable{
    private List<HomeArticleData> articles;
    private int cid;
    private String name;

    public List<HomeArticleData> getArticles() {
        return articles;
    }

    public void setArticles(List<HomeArticleData> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NavigationListData{" +
                "articles=" + articles +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
