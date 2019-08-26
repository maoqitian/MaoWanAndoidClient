package mao.com.mao_wanandroid_client.view.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.compoent.RxBus;
import mao.com.mao_wanandroid_client.compoent.event.LoginStatusEvent;
import mao.com.mao_wanandroid_client.model.http.cookie.CookieManager;
import mao.com.mao_wanandroid_client.presenter.main.MainContract;
import mao.com.mao_wanandroid_client.presenter.main.MainPresenter;
import mao.com.mao_wanandroid_client.utils.NavHelper;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionPageFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CommonWebFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.SettingsFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.HomePageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.KnowledgeHierarchyPageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.NavigationFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.OfficialAccountsPageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.ProjectFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.SearchFragment;
import mao.com.mao_wanandroid_client.widget.CircleImageView;


public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.MainView,
        View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener ,
        NavHelper.OnTabChangeListener<String> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.main_bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.tv_page_title)
    TextView pageTitle;
    @BindView(R.id.iv_search)
    ImageView mSearch;


    //用户头像
    private CircleImageView userImageIcon;
    //用户名
    private TextView mUserName;
    private NavHelper mNavHelper;

    SearchFragment mSearchFragment;
    CommonWebFragment mCommonWebFragment;
    SettingsFragment mSettingsFragment;

    // 是否开启了主页，没有开启则会返回主页
    public static boolean isLaunch = false;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLaunch = true;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        //除去toolbar 默认显示的标题
        supportActionBar.setDisplayShowTitleEnabled(false);
        pageTitle.setText(getString(R.string.page_home));
        //沉浸式状态栏
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this,drawer,ContextCompat.getColor(this,R.color.colorPrimary));
        initFragment();
        initView();
    }

    private void initFragment() {
        /*HomePageFragment fragment = findFragment(HomePageFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.page_fragment_container, HomePageFragment.newInstance());
            //loadMultipleRootFragment();
        }*/
       mNavHelper =new NavHelper<String>(this,R.id.page_fragment_container,getSupportFragmentManager(),this)
               .add(R.id.tab_main,new NavHelper.Tab<String>(HomePageFragment.class,getString(R.string.page_home),Constants.TAG_HOME))
               .add(R.id.nav_home,new NavHelper.Tab<String>(HomePageFragment.class,getString(R.string.page_home),Constants.TAG_HOME))
               .add(R.id.tab_knowledge_hierarchy,new NavHelper.Tab<String>(KnowledgeHierarchyPageFragment.class,getString(R.string.knowledge_hierarchy),Constants.TAG_KNOWLEGER))
               .add(R.id.tab_official_accounts,new NavHelper.Tab<String>(OfficialAccountsPageFragment.class,getString(R.string.official_accounts),Constants.TAG_OFFICIAL))
               .add(R.id.tab_navigation,new NavHelper.Tab<String>(NavigationFragment.class,getString(R.string.navigation),Constants.TAG_NAVIGATION))
               .add(R.id.tab_project,new NavHelper.Tab<String>(ProjectFragment.class,getString(R.string.project),Constants.TAG_PROJECT))
               .add(R.id.collect_page,new NavHelper.Tab<String>(CollectionPageFragment.class,getString(R.string.nav_collect),Constants.TAG_COLLECTION));
    }

    private void initView() {
        fab.setOnClickListener(this);
        mSearch.setVisibility(View.VISIBLE);
        mSearch.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //默认选择 首页
        navigationView.setCheckedItem(R.id.nav_home);
        //获取侧边栏头部属性
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        userImageIcon = headerView.findViewById(R.id.imageView_user_icon);
        userImageIcon.setOnClickListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        Menu menu = bottomNavigationView.getMenu();
        menu.performIdentifierAction(R.id.tab_main,0);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
    }
    //左侧导航栏、底部导航栏 点击事件
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id){
            case R.id.collect_page:
                //收藏
                if(!mPresenter.getLoginStatus()){
                    //是否已经登录
                    StartDetailPage.start(this,null, Constants.PAGE_LOGIN,Constants.ACTION_LOGIN_ACTIVITY);
                    return false;
                }
                initPageTitle(getString(R.string.nav_collect));
                bottomNavigationView.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                break;
            case R.id.nav_settings:
                //设置
                if (mSettingsFragment == null) {
                    mSettingsFragment = SettingsFragment.newInstance();
                }
                if (!isDestroyed() && mSettingsFragment.isAdded()) {
                    mSettingsFragment.dismiss();
                }
                mSettingsFragment.show(getSupportFragmentManager(),"showSettings");
                break;
            case R.id.nav_todo:
                //TODO
                Toast.makeText(this,"暂未实现",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_home:
            case R.id.tab_main:
                //主页
                initPageTitle(getString(R.string.page_home));
                bottomNavigationView.setVisibility(View.VISIBLE);
                break;
            case R.id.tab_knowledge_hierarchy:
                //知识体系
                initPageTitle(getString(R.string.knowledge_hierarchy));
                break;
            case R.id.tab_official_accounts:
                //公众号
                initPageTitle(getString(R.string.official_accounts));
                break;
            case R.id.tab_navigation:
                //导航
                initPageTitle(getString(R.string.navigation));
                break;
            case R.id.tab_project:
                //项目
                initPageTitle(getString(R.string.project));
                break;
            case R.id.common_website:
                //常用网站
                if (mCommonWebFragment == null) {
                    mCommonWebFragment = CommonWebFragment.newInstance(getString(R.string.common_web));
                }
                if (!isDestroyed() && mCommonWebFragment.isAdded()) {
                    mCommonWebFragment.dismiss();
                }
                mCommonWebFragment.show(getSupportFragmentManager(),"showCommonWeb");
                break;
                default:
                    break;
        }
       /* if (id == R.id.nav_collect) {
            Toast.makeText(this,"点击了收藏",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this,"点击了设置",Toast.LENGTH_SHORT).show();
        }*/
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //点击之后关闭DrawerLayout
        if(R.id.common_website != id ){
            //如果是 常用网站则不改变选中状态
            //navigationView 选中
            navigationView.setCheckedItem(id);
        }
        /*if(R.id.nav_settings == id){
            //如果是设置 让其选中首页状态
            navigationView.setCheckedItem(R.id.nav_home);
        }*/
        drawer.closeDrawer(GravityCompat.START);
        return mNavHelper.performClickMenu(id);
    }

    /**
     * 加载对应的页面
     */
    private void initPageTitle(String pagetitle) {
       pageTitle.setText(pagetitle);

    }

    //view 点击事件
    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.fab:
                 //Toast.makeText(MainActivity.this,"点击了回到顶部",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.imageView_user_icon: //用户个人头像点击
                 if(!mPresenter.getLoginStatus()){
                     //进入登录界面
                     StartDetailPage.start(MainActivity.this,null, Constants.PAGE_LOGIN,Constants.ACTION_LOGIN_ACTIVITY);
                 }else {
                     //TODO 进入个人中心 暂未实现
                     Toast.makeText(MainActivity.this,"进入个人中心暂未实现",Toast.LENGTH_SHORT).show();
                 }
                 break;
             case R.id.iv_search:
                 if (mSearchFragment == null) {
                     mSearchFragment = SearchFragment.newInstance(Constants.RESULT_CODE_HOME_PAGE,0,"");
                 }
                 if (!isDestroyed() && mSearchFragment.isAdded()) {
                     mSearchFragment.dismiss();
                 }
                 mSearchFragment.show(getSupportFragmentManager(),"showMainSearch");
                 break;
             default:
                 break;
         }
    }

    /**
     * tab 切换回调
     * @param newTab
     * @param oldTab
     */
    @Override
    public void onTabChange(NavHelper.Tab<String> newTab, NavHelper.Tab<String> oldTab) {
        //Log.e("毛麒添","当前tab  "+newTab.extra + "SimpleName"+newTab.getClass().getSimpleName());
    }


    @Override
    public void showLoginView() {
        Log.e("毛麒添","登录成功");
        if(navigationView == null){
            return;
        }
        userImageIcon = navigationView.getHeaderView(0).findViewById(R.id.imageView_user_icon);
        mUserName = navigationView.getHeaderView(0).findViewById(R.id.textView_user_name);
        mUserName.setText(mPresenter.getLoginUserName());
        userImageIcon.setImageDrawable(getDrawable(R.mipmap.ic_launcher));

    }

    @Override
    public void showLogoutView() {
        Log.e("毛麒添","显示为登录页面");
        userImageIcon = navigationView.getHeaderView(0).findViewById(R.id.imageView_user_icon);
        mUserName = navigationView.getHeaderView(0).findViewById(R.id.textView_user_name);
        mUserName.setText(getString(R.string.nav_header_title));
        CookieManager.getInstance().clearAllCookie();
        userImageIcon.setImageDrawable(getDrawable(R.drawable.ic_default_avatar));
    }

    @Override
    public void showSingOutSuccess() {
        RxBus.getDefault().post(new LoginStatusEvent(false,true));
        showLogoutView();
    }

    @Override
    public void showSingOutFail(String errorMsg) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressedSupport() {
        Log.e("毛麒添","onBackPressedSupport MainActivity 调用");
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }else {
                //再点一次退出程序
                doubleClickExit();
            }

    }
    private static Boolean mIsExit = false;

    //再点一次退出程序
    private void doubleClickExit() {
        Timer exitTimer = null;
        if (!mIsExit) {
            mIsExit = true;
            Toast.makeText(getApplicationContext(),getString(R.string.exit_again),Toast.LENGTH_SHORT).show();
            exitTimer = new Timer();
            exitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mIsExit = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        isLaunch = false;
        super.onDestroy();
    }
}
