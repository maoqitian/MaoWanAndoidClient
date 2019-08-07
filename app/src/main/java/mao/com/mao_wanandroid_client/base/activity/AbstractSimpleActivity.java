package mao.com.mao_wanandroid_client.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author maoqitian
 * @Description activity基类 （不是MVP 模式）
 * @Time 2018/10/14 0014 13:53
 */
public abstract class AbstractSimpleActivity extends SupportActivity{

    protected AbstractSimpleActivity mContext;
    //ButterKnife
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnbinder= ButterKnife.bind(this);
        mContext=this;
        onViewCreated();
        initToolbar();
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mUnbinder=null;
    }


    /**
     * view 的创建 留给子类实现
     */
    protected abstract void onViewCreated();
    /**
     * 初始化 toolbar
     */
    protected abstract void initToolbar();
    /**
     * 初始化数据留给子类实现
     */
    protected abstract void initEventAndData();

    /**
     * 获取布局对象 留给子类实现
     */
    protected abstract int getLayout();

}
