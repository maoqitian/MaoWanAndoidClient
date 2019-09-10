package mao.com.mao_wanandroid_client.view.drawer;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.activity.AbstractSimpleActivity;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;

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

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about_us_layout;
    }
}
