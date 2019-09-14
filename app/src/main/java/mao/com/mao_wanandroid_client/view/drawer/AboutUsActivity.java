package mao.com.mao_wanandroid_client.view.drawer;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.AbstractSimpleActivity;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;

/**
 * @author maoqitian
 * @Description: 关于我们
 * @date 2019/9/9 0009 17:41
 */
public class AboutUsActivity extends AbstractSimpleActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_about_version_code)
    TextView tvVersionCode;
    @BindView(R.id.tv_about_content)
    TextView tvAboutContent;
    @BindView(R.id.tv_code_content)
    TextView tvCodeContent;

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void initToolbar() {
        mToolbar.setTitle(getString(R.string.about_us_text));
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        StatusBarUtil.setColorNoTranslucentLightMode(this, ContextCompat.getColor(this,R.color.colorPrimary));
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initEventAndData() {
        tvVersionCode.setText("当前版本 V"+ ToolsUtils.getVersion(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvAboutContent.setText(Html.fromHtml(getString(R.string.about_us_content),Html.FROM_HTML_MODE_LEGACY));
            tvCodeContent.setText(Html.fromHtml(getString(R.string.code_content),Html.FROM_HTML_MODE_LEGACY));
        }else {
            tvAboutContent.setText(Html.fromHtml(getString(R.string.about_us_content)));
            tvCodeContent.setText(Html.fromHtml(getString(R.string.code_content)));
        }
        tvCodeContent.setOnClickListener(view -> {
            HomeArticleData homeArticleData = new HomeArticleData();
            homeArticleData.setTitle("MaoWanAndoidClient");
            homeArticleData.setLink("https://github.com/maoqitian/MaoWanAndoidClient");
            StartDetailPage.start(this,homeArticleData, Constants.PAGE_WEB_NOT_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about_us_layout;
    }
}
