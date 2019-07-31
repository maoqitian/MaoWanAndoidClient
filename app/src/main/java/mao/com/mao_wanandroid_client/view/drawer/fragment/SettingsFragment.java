package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.model.setting.SettingData;
import mao.com.mao_wanandroid_client.presenter.drawer.SettingsContract;
import mao.com.mao_wanandroid_client.presenter.drawer.SettingsPresenter;
import mao.com.mao_wanandroid_client.view.drawer.adapter.SettingsPageAdapter;

/**
 * @author maoqitian
 * @Description: 设置 Fragment
 * @date 2019/7/26 0026 15:49
 */
public class SettingsFragment extends BaseDialogFragment<SettingsPresenter> implements
        SettingsContract.SettingsView, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {



    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_page_title)
    TextView mPageTitle;
    @BindView(R.id.settings_recycleview)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;

    SettingsPageAdapter mAdapter;

    @BindView(R.id.tv_quit_login)
    TextView mQuitLogin;

    List<SettingData> mSettingDataList;

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
        mSettingDataList = new ArrayList<>();
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(v -> dismiss());
        mPageTitle.setText(getString(R.string.nav_settings));

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new SettingsPageAdapter(R.layout.setting_item_layout);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mSettingDataList.clear();
        mSettingDataList.add(new SettingData("清除缓存",false));
        mSettingDataList.add(new SettingData("夜间模式",true));

        mAdapter.addData(mSettingDataList);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
