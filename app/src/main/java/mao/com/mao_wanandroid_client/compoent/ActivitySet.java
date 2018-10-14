package mao.com.mao_wanandroid_client.compoent;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author maoqitian
 * @Description Activity 集合 Set 集合保证元素不重复
 * @Time 2018/10/14 0014 14:52
 */
public class ActivitySet {

    private static ActivitySet activitySet;

    private Set<Activity> allActivities;

    public synchronized static ActivitySet getInstance() {
        if (activitySet == null) {
            activitySet = new ActivitySet();
        }
        return activitySet;
    }

    public void addActivity(Activity activity){
        if(allActivities == null){
            allActivities=new HashSet<>();
        }
        allActivities.add(activity);
    }
    public void removeActivity(Activity activity){
        if(allActivities!=null){
            allActivities.remove(activity);
        }
    }

    public void exitApp(){
        if(allActivities!=null){
            synchronized (allActivities){
                for (Activity activity:
                 allActivities) {
                  activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
