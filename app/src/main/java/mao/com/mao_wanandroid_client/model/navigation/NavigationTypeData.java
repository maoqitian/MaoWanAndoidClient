package mao.com.mao_wanandroid_client.model.navigation;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import mao.com.mao_wanandroid_client.model.home.HomeArticleData;

/**
 * @author maoqitian
 * @Description:
 * @date 2019/7/10 0010 10:45
 */
public class NavigationTypeData {

    private List<HomeArticleData> articles;

    public static class NavigationInfo implements MultiItemEntity {

        public static final int TYPE_HEADER = 1;
        public static final int TYPE_DATA = 2;

        private int itemType;
        public String titleName;

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

    }
}
