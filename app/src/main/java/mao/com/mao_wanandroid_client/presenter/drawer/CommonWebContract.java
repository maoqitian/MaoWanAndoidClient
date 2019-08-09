package mao.com.mao_wanandroid_client.presenter.drawer;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.frienduser.CommonWebData;

/**
 * @author maoqitian
 * @Description: 常用网站
 * @date 2019/7/26 0026 16:04
 */
public interface CommonWebContract {

    interface CommonWebView extends BaseView{
        void showCommonWebData(List<CommonWebData> commonWebDataList);
    }
    

    interface  CommonFragmentPresenter extends AbstractBasePresenter<CommonWebView>{
         void getCommonWebData();
    }
}

