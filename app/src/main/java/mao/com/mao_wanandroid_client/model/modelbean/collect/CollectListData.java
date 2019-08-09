package mao.com.mao_wanandroid_client.model.modelbean.collect;

import java.util.List;

/**
 * @author maoqitian
 * @Description  收藏文章 list data
 * @Time 2019/6/23 0023 16:00
 */
public class CollectListData {

    /**
     * curPage : 1
     * datas : [{"author":"mao","chapterId":0,"chapterName":"","courseId":13,"desc":"","envelopePic":"","id":69160,"link":"www.baidu.com","niceDate":"10分钟前","origin":"","originId":-1,"publishTime":1561276596000,"title":"测试站外链接收藏","userId":863,"visible":0,"zan":0},{"author":"鸿洋","chapterId":249,"chapterName":"干货资源","courseId":13,"desc":"","envelopePic":"","id":26560,"link":"https://mp.weixin.qq.com/s/XREOq821aaY0gwrlwrOQJA","niceDate":"2018-09-26","origin":"","originId":3446,"publishTime":1537975882000,"title":"Android 技能图谱学习路线","userId":863,"visible":0,"zan":0},{"author":"鸿洋","chapterId":361,"chapterName":"课程推荐","courseId":13,"desc":"","envelopePic":"","id":26559,"link":"http://www.wanandroid.com/blog/show/2359","niceDate":"2018-09-26","origin":"","originId":3409,"publishTime":1537975879000,"title":"课程推荐 | 轻松搞定BAT的面试通关秘籍 算法之美","userId":863,"visible":0,"zan":0},{"author":"Ruheng","chapterId":26,"chapterName":"基础UI控件","courseId":13,"desc":"详解Android图文混排实现。","envelopePic":"","id":25999,"link":"http://www.jianshu.com/p/6843f332c8df","niceDate":"2018-09-18","origin":"","originId":1165,"publishTime":1537283545000,"title":"Android图文混排实现方式详解","userId":863,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 4
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<CollectData> datas;

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

    public List<CollectData> getDatas() {
        return datas;
    }

    public void setDatas(List<CollectData> datas) {
        this.datas = datas;
    }

}
