package mao.com.mao_wanandroid_client.model.navigation;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import mao.com.mao_wanandroid_client.model.home.HomeArticleData;

/**
 * @author maoqitian
 * @Description:
 * @date 2019/7/10 0010 10:45
 */
public class NavigationTypeData implements MultiItemEntity{

        private List<HomeArticleData> articles;

        private int itemType;
        public String mTitleName;

        public NavigationTypeData(int itemType) {
            this.itemType = itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
        public NavigationTypeData(int itemType, String titleName) {
            this(itemType);
            this.mTitleName = titleName;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

}
