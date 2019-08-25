package mao.com.mao_wanandroid_client.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mao.com.mao_wanandroid_client.di.scope.FragmentScope;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionDialogFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionPageFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CollectionWebFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.CommonWebFragment;
import mao.com.mao_wanandroid_client.view.drawer.fragment.SettingsFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.HomeFirstTabFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.HomePageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.HomeSecondTabFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.KnowledgeHierarchyPageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.KnowledgeLevel2PageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.NavigationFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.OfficialAccountsPageFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.ProjectFragment;
import mao.com.mao_wanandroid_client.view.main.fragment.SearchFragment;


/**
 * @author maoqitian
 * @Description 用于生成 Fragment 注入器的Module，使用@ContributesAndroidInjector注解并指定modules为
 * @Time 2019/4/14 0014 14:09
 */
@Module
public abstract class FragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = HomePageFragmentModule.class)
    abstract HomePageFragment contributeHomePageFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = HomeFirstTabFragmentModule.class)
    abstract HomeFirstTabFragment contributeHomeFirstTabFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = HomeSecondTabFragmentModule.class)
    abstract HomeSecondTabFragment contributeHomeSecondTabFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = KnowledgeHierarchyFragmentModule.class)
    abstract KnowledgeHierarchyPageFragment contributeKnowledgeHierarchyPageFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = OfficialAccountsFragmentModule.class)
    abstract OfficialAccountsPageFragment contributeOfficialAccountsPageFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = NavigationFragmentModule.class)
    abstract NavigationFragment contributeNavigationFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = ProjectFragmentModule.class)
    abstract ProjectFragment contributeProjectFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = KnowledgeLevel2PageFragmentModule.class)
    abstract KnowledgeLevel2PageFragment contributeKnowledgeLevel2PageFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = SearchFragmentModule.class)
    abstract SearchFragment contributeSearchFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = CollectionPageFragmentModule.class)
    abstract CollectionPageFragment contributeCollectionPageFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = CollectionFragmentModule.class)
    abstract CollectionFragment contributeCollectionFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = CollectionWebFragmentModule.class)
    abstract CollectionWebFragment contributeCollectionWebFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = SettingsFragmentModule.class)
    abstract SettingsFragment contributeSettingsFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = CommonWebFragmentModule.class)
    abstract CommonWebFragment contributeCommonWebFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = CollectionDialogFragmentModule.class)
    abstract CollectionDialogFragment contributeCollectionDialogFragment();

}
