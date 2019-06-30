package mao.com.mao_wanandroid_client.view.main;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.presenter.main.KnowledgeLevel2PageContract;
import mao.com.mao_wanandroid_client.presenter.main.KnowledgeLevel2PagePresenter;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.main.adapter.HomeTabPageAdapter;
import mao.com.mao_wanandroid_client.view.main.fragment.KnowledgeLevel2PageFragment;

/**
 * @author maoqitian
 * @Description: 知识体系二级页
 * @date 2019/6/28 0028 13:28
 */
public class KnowledgeLevel2PageActivity extends BaseActivity<KnowledgeLevel2PagePresenter> implements KnowledgeLevel2PageContract.KnowledgeLevel2PageView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_page_title)
    TextView mTextTitle;
    @BindView(R.id.level2_page_tab)
    TabLayout mLevel2PageTab;

    @BindView(R.id.view_pager_level2)
    ViewPager mViewPager;

    List<String> mTitle;
    List<Fragment> mFragments;

    //点击文章对应数据
    HomeArticleData homeArticleData;
    private String pageType;//当前 页面类型 由哪个页面跳转（是否可以收藏）

    @Override
    protected void initToolbar() {
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        StatusBarUtil.setColorNoTranslucentLightMode(this, ContextCompat.getColor(this,R.color.colorPrimary));
        mToolbar.setNavigationOnClickListener(v -> finish());
        ToolsUtils.setIndicatorWidth(mLevel2PageTab,160);
    }

    private void getIntentInitViewData() {
        homeArticleData= (HomeArticleData) getIntent().getSerializableExtra(Constants.HOME_ARTICLE_DATA);
        pageType = getIntent().getStringExtra(Constants.PAGE_TYPE);
        mTitle = new ArrayList<>();
        mFragments = new ArrayList<>();

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_level2_page_layout;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
    }

    @Override
    public void showLevel2PageView() {
        getIntentInitViewData();
        if(homeArticleData!=null){
            mTextTitle.setText(homeArticleData.getChapterName());
        }
        if(homeArticleData!=null && Constants.RESULT_CODE_HOME_PAGE.equals(pageType)){
            //首页 点击 知识体系 tag
            mTitle.add(homeArticleData.getSuperChapterName());
            mFragments.add(KnowledgeLevel2PageFragment.newInstance(homeArticleData.getSuperChapterId()));
        }else {
            //知识体系 模块正常加载

        }
        mViewPager.setAdapter(new HomeTabPageAdapter(getSupportFragmentManager(),mTitle,mFragments));
        mLevel2PageTab.setupWithViewPager(mViewPager);
    }
}
