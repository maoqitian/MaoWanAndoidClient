package mao.com.mao_wanandroid_client.view.pagedetail;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.activity.RootActivity;
import mao.com.mao_wanandroid_client.presenter.pagedetail.PageDetailContract;
import mao.com.mao_wanandroid_client.presenter.pagedetail.PageDetailPresenter;

/**
 * @author maoqitian
 * @Description 详情页
 * @Time 2019/5/31 0031 0:01
 */
public class PageDetailActivity extends RootActivity<PageDetailPresenter> implements PageDetailContract.PageDetailView {


    @Override
    protected int getLayout() {
        return R.layout.page_detail_activity_layout;
    }
}
