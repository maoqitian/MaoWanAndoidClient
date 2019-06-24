package mao.com.mao_wanandroid_client.view.main;

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
import android.view.KeyEvent;
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
import mao.com.mao_wanandroid_client.core.http.cookie.CookieManager;
import mao.com.mao_wanandroid_client.presenter.main.MainContract;
import mao.com.mao_wanandroid_client.presenter.main.MainPresenter;
import mao.com.mao_wanandroid_client.utils.NavHelper;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.StatusBarUtil;
import mao.com.mao_wanandroid_client.view.main.fragment.HomePageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.KnowledgeHierarchyPageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.NavigationFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.OfficialAccountsPageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.ProjectFragment;
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

    //用户头像
    private CircleImageView userImageIcon;
    //用户名
    private TextView mUserName;
    private NavHelper mNavHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //测试网络模块。获取首页Banner数据
       /* NetworkUtils.getInstance().getApiService(ApiService.class,ApiService.HOST,true).
                GetHomePageBannerData()
                .compose(RxSchedulers.<ResponseBody<List<HomePageBannerModel>>>observableIO2Main(this))
                .subscribe(new ProgressObserver<List<HomePageBannerModel>>(this,"正在加载首页Banner数据") {
                    @Override
                    public void onSuccess(List<HomePageBannerModel> result) {
                        Log.e("mao",result.toString());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        Log.e("mao",e.getMessage()+errorMsg);
                    }
                });*/

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
        StatusBarUtil.setColorNoTranslucentForDrawerLayoutLightMode(this,drawer,ContextCompat.getColor(this,R.color.colorPrimary));
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
       .add(R.id.tab_main,new NavHelper.Tab<String>(HomePageFragment.class,getString(R.string.page_home)))
       .add(R.id.tab_knowledge_hierarchy,new NavHelper.Tab<String>(KnowledgeHierarchyPageFragment.class,getString(R.string.knowledge_hierarchy)))
       .add(R.id.tab_official_accounts,new NavHelper.Tab<String>(OfficialAccountsPageFragment.class,getString(R.string.official_accounts)))
       .add(R.id.tab_navigation,new NavHelper.Tab<String>(NavigationFragment.class,getString(R.string.navigation)))
       .add(R.id.tab_project,new NavHelper.Tab<String>(ProjectFragment.class,getString(R.string.project)));


    }

    private void initView() {
        fab.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        userImageIcon = headerView.findViewById(R.id.imageView_user_icon);
        userImageIcon.setOnClickListener(this);
        bottomNavigationView.setItemIconSize(70);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //如果侧边栏打开，则先关闭侧边栏
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }else {
                //再点一次退出程序
                doubleClickExit();
            }
        }
        return false;
    }

    private static Boolean mIsExit = false;

    //再点一次退出程序
    private void doubleClickExit() {
        Timer exitTimer = null;
        if (!mIsExit) {
            mIsExit = true;
            Toast.makeText(getApplicationContext(),"再点一次退出应用",Toast.LENGTH_SHORT).show();
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
    //左侧导航栏、底部导航栏 点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id){
            case R.id.nav_home:
                //Toast.makeText(this,"点击了主页",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_collect:
                Toast.makeText(this,"点击了收藏",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this,"点击了设置",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_sign_out:
                Toast.makeText(this,"点击退出登录",Toast.LENGTH_SHORT).show();
                mPresenter.getSingOut();
            case R.id.tab_main:
                initPage(getString(R.string.page_home));
                //Toast.makeText(this,"点击了主页",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_knowledge_hierarchy:
                initPage(getString(R.string.knowledge_hierarchy));
                Toast.makeText(this,"点击了知识体系",Toast.LENGTH_SHORT).show();

                break;
            case R.id.tab_official_accounts:
                initPage(getString(R.string.official_accounts));
                Toast.makeText(this,"点击了公众号",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_navigation:
                initPage(getString(R.string.navigation));
                Toast.makeText(this,"点击了导航",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_project:
                initPage(getString(R.string.project));
                Toast.makeText(this,"点击了项目",Toast.LENGTH_SHORT).show();
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
        navigationView.setCheckedItem(id);
        drawer.closeDrawer(GravityCompat.START);
        return mNavHelper.performClickMenu(id);
    }

    /**
     * 加载对应的页面
     */
    private void initPage(String pagetitle) {
       pageTitle.setText(pagetitle);

    }

    //view 点击事件
    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.fab:
                 /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                 Toast.makeText(MainActivity.this,"点击了回到顶部",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.imageView_user_icon: //用户个人头像点击
                 if(!mPresenter.getLoginStatus()){
                     //进入登录界面
                     StartDetailPage.start(MainActivity.this,null, Constants.PAGE_LOGIN,Constants.ACTION_LOGIN_ACTIVITY);
                 }else {
                     //进入个人中心
                     Toast.makeText(MainActivity.this,"进入个人中心",Toast.LENGTH_SHORT).show();
                 }
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
        RxBus.getDefault().post(new LoginStatusEvent(false));
        showLogoutView();
    }

    @Override
    public void showSingOutFail(String errorMsg) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }
}
