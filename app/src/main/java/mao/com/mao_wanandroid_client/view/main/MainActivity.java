package mao.com.mao_wanandroid_client.view.main;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.activity.BaseActivity;
import mao.com.mao_wanandroid_client.presenter.main.MainContract;
import mao.com.mao_wanandroid_client.presenter.main.MainPresenter;


public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.MainView ,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

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

        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_collect) {
            // Handle the camera action
        } else if (id == R.id.nav_settings) {

        }
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
