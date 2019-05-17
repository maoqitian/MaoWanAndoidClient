package mao.com.mao_wanandroid_client.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author maoqitian
 * @Description 无 MVP Fragment 基类  继承SupportFragment （fragmentation）
 * @Time 2018/12/14 0014 22:43
 */
public abstract class SimpleFragment extends SupportFragment {

    //ButterKnife
    private Unbinder mUnbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
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
        return super.onBackPressedSupport();
    }
}
