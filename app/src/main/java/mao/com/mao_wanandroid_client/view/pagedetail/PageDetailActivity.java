package mao.com.mao_wanandroid_client.view.pagedetail;

import android.annotation.SuppressLint;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.presenter.pagedetail.PageDetailContract;
import mao.com.mao_wanandroid_client.presenter.pagedetail.PageDetailPresenter;

/**
 * @author maoqitian
 * @Description 详情页
 * @Time 2019/5/31 0031 0:01
 */
public class PageDetailActivity extends BaseActivity<PageDetailPresenter> implements PageDetailContract.PageDetailView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webview_container)
    FrameLayout mWebviewContainer;
    HomeArticleData homeArticleData;

    private AgentWeb agentWeb;

    private String pageType;//当前web 页面类型（是否可以收藏）

    @Override
    protected int getLayout() {
        return R.layout.page_detail_activity_layout;
    }


    @Override
    protected void initToolbar() {
        getIntentData();
        mToolbar.setTitle(Html.fromHtml(homeArticleData.getTitle()));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIntentData() {
        homeArticleData= (HomeArticleData) getIntent().getSerializableExtra(Constants.HOME_ARTICLE_DATA);
        pageType = getIntent().getStringExtra(Constants.PAGE_TYPE);
        if(homeArticleData!=null){
            Log.e("毛麒添","详情页PageDetailActivity数据 :"+homeArticleData.toString());
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebviewContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(homeArticleData.getLink());

        WebView webView = agentWeb.getWebCreator().getWebView();
        WebSettings mSettings = webView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

    }


    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
