package mao.com.mao_wanandroid_client.view.main;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.main.SearchResultContract;
import mao.com.mao_wanandroid_client.presenter.main.SearchResultPresenter;

/**
 * @author maoqitian
 * @Description 搜索结果(目前没有用到该 Activity )
 * @Time 2019/7/22 0022 23:22
 */
public class SearchResultActivity extends BaseActivity<SearchResultPresenter> implements SearchResultContract.SearchResultView {

    @Override
    protected void initToolbar() {
        super.initToolbar();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search_result_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
    }
}
