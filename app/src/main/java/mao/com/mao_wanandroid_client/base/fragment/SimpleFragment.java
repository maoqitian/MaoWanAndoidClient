package mao.com.mao_wanandroid_client.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mao.com.mao_wanandroid_client.R;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author maoqitian
 * @Description  Fragment 基类  继承SupportFragment （fragmentation）
 * @Time 2018/12/14 0014 22:43
 */
public abstract class SimpleFragment extends SupportFragment {

    //ButterKnife
    private Unbinder mUnbinder;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder=ButterKnife.bind(this,view);
        initView();
        return view;
    }

    /**
     * 初始化 view
     */
    protected  void initView(){

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initEventAndData();
    }

    //获取LayoutId
    protected abstract int getLayoutId();
    //初始化数据
    protected abstract void initEventAndData();

    /**
     * 回退事件处理
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        Log.e("毛麒添","onBackPressedSupport SimpleFragment  调用");
       /* if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        }else {
            doubleClickExit();
        }*/
        return super.onBackPressedSupport();
    }
    private static Boolean mIsExit = false;
    private void doubleClickExit() {
        Timer exitTimer = null;
        if (!mIsExit) {
            mIsExit = true;
            Toast.makeText(_mActivity,_mActivity.getString(R.string.exit_again),Toast.LENGTH_SHORT).show();
            exitTimer = new Timer();
            exitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mIsExit = false;
                }
            }, 2000);
        } else {
            _mActivity.finish();
            System.exit(0);
        }
    }
}
