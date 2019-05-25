package mao.com.mao_wanandroid_client.view.main.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author maoqitian
 * @Description 首页 tablayout fragment 适配器
 * @Time 2019/5/23 0023 23:01
 */
public class HomeTabPageAdapter extends FragmentPagerAdapter {

    List<String> mTitle;
    List<Fragment> mFragments;

    public HomeTabPageAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.mTitle = titles;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }

    //注释父类实现，避免出现fragment 空白
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container,position,object);
    }
}
