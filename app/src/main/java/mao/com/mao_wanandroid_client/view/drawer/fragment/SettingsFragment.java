package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.presenter.drawer.SettingsContract;
import mao.com.mao_wanandroid_client.presenter.drawer.SettingsPresenter;

/**
 * @author maoqitian
 * @Description: 设置 Fragment
 * @date 2019/7/26 0026 15:49
 */
public class SettingsFragment extends BaseDialogFragment<SettingsPresenter> implements SettingsContract.SettingsView {



    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_page_title)
    TextView mPageTitle;
    @BindView(R.id.settings_recycleview)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_quit_login)
    TextView mQuitLogin;

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dialogFragment全屏
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFullScreen);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.settings_fragment_layout;
    }

    @Override
    protected void initViewAndData() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(v -> dismiss());
        mPageTitle.setText(getString(R.string.nav_settings));
    }
}
