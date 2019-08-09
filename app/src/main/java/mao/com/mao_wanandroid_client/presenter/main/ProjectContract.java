package mao.com.mao_wanandroid_client.presenter.main;

import java.util.List;

import mao.com.mao_wanandroid_client.base.BaseView;
import mao.com.mao_wanandroid_client.base.presenter.AbstractBasePresenter;
import mao.com.mao_wanandroid_client.model.modelbean.project.ProjectClassifyData;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/5/8 0008 23:36
 */
public interface ProjectContract {

    interface ProjectView extends BaseView{
        void showProjectClassifyData(List<ProjectClassifyData> projectClassifyDataList);
    }

    interface ProjectFragmentPresenter extends AbstractBasePresenter<ProjectView>{
        void getProjectClassifyData();
    }
}
