package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.LoginStatusEvent;
import mao.com.mao_wanandroid_client.compoent.event.ThemeModeEvent;
import mao.com.mao_wanandroid_client.model.modelbean.setting.SettingData;
import mao.com.mao_wanandroid_client.presenter.drawer.SettingsContract;
import mao.com.mao_wanandroid_client.presenter.drawer.SettingsPresenter;
import mao.com.mao_wanandroid_client.utils.CacheManager;
import mao.com.mao_wanandroid_client.utils.NormalAlertDialog;
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
    @BindView(R.id.tv_quit_login)
    TextView mQuitLogin;

    private RecyclerView.LayoutManager layoutManager;
    SettingsPageAdapter mAdapter;

    List<SettingData> mSettingDataList;

    //缓存文件
    private File cacheFile;
    //缓存文件大小
    private String cacheSize;


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
        mQuitLogin.setOnClickListener(this);
        initRecyclerView();
        getCacheSize();
        //获取设置数据
        mPresenter.getSettingsItemData();
        if(mPresenter.getLoginStatus()){
            mQuitLogin.setVisibility(View.VISIBLE);
        }
        //设置 switch 状态
        mAdapter.setMode(mPresenter.getThemeMode());
    }

    private void getCacheSize() {
        try {
            cacheFile = new File(Constants.PATH_CACHE_DATA);
            cacheSize = CacheManager.getCacheSize(cacheFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new SettingsPageAdapter(R.layout.setting_item_layout);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_quit_login:
                //退出登录
                NormalAlertDialog.getInstance().showAlertDialog(getActivity(), getString(R.string.login_out_text),
                        getString(R.string.login_out_positive_text), getString(R.string.login_out_negative_text), (dialog, which) -> {
                            mPresenter.getSingOut();
                        }, (dialog, which) -> dialog.dismiss());
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SettingData settingData = (SettingData) adapter.getItem(position);
        assert settingData != null;
        switch (settingData.getType()){
            case Constants.SETTINGS_CLEAR_CACHE_TYPE:
                //清除缓存
                NormalAlertDialog.getInstance().showAlertDialog(getActivity(), getString(R.string.confirm_clear_cache_text),
                        getString(R.string.confirm_text), getString(R.string.cancel_text), (dialog, which) -> {
                            CacheManager.deleteFolderFile(cacheFile, true);
                            Toast.makeText(getActivity(),getString(R.string.finish_clear_cache_text),Toast.LENGTH_SHORT).show();
                        }, (dialog, which) -> dialog.dismiss());
                break;
            case Constants.SETTINGS_NIGHT_MODE_TYPE:
                //夜间切换操作模式
                ChangeThemeMode();
                Switch switchView = view.findViewById(R.id.setting_switch);
                switchView.setChecked(!switchView.isChecked());
                break;
            case Constants.SETTINGS_VERSION_TYPE:
                //版本 关于我们
                break;
              default:
                  break;
        }
    }
    //切换日常模式与 夜间模式
    private void ChangeThemeMode() {
        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(mode == Configuration.UI_MODE_NIGHT_YES) {
            RxBus.getDefault().post(new ThemeModeEvent(AppCompatDelegate.MODE_NIGHT_NO));
        } else if(mode == Configuration.UI_MODE_NIGHT_NO) {
            RxBus.getDefault().post(new ThemeModeEvent(AppCompatDelegate.MODE_NIGHT_YES));
        }else {
            //否则保持亮色主题
            RxBus.getDefault().post(new ThemeModeEvent(AppCompatDelegate.MODE_NIGHT_NO));
        }
    }

    @Override
    public void showSettingsItemData(List<SettingData> settingDataList) {
        mSettingDataList.clear();
        mSettingDataList.addAll(settingDataList);
        mAdapter.addData(mSettingDataList);
    }

    @Override
    public void showSingOutSuccess() {
        //退出登录成功
        RxBus.getDefault().post(new LoginStatusEvent(false,true));
        //隐藏退出登录按钮
        mQuitLogin.setVisibility(View.GONE);
    }

    @Override
    public void showSingOutFail(String errorMsg) {
        Toast.makeText(getActivity(),errorMsg,Toast.LENGTH_SHORT).show();
    }
}
