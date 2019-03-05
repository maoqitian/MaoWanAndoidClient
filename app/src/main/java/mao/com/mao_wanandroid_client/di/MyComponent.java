package mao.com.mao_wanandroid_client.di;

import dagger.Component;
import mao.com.mao_wanandroid_client.view.welcome.WelcomeActivity;

/**
 * @author maoqitian
 * @Description
 * @Time 2019/3/5 0005 22:49
 */
@Component(modules = {MyModule.class})
public interface MyComponent {
    void inJectWelcomeActivity(WelcomeActivity welcomeActivity);
}
