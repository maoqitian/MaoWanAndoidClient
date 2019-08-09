package mao.com.mao_wanandroid_client.model.modelbean.project;

import java.util.List;

import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;

/**
 * @author maoqitian
 * @Description 项目列表数据
 * @Time 2018/9/12 0012 23:34
 */
public class ProjectListData {
    /**
     * curPage : 1
     * datas : []
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 15
     * total : 8
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<HomeArticleData> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HomeArticleData> getDatas() {
        return datas;
    }

    public void setDatas(List<HomeArticleData> datas) {
        this.datas = datas;
    }

}
