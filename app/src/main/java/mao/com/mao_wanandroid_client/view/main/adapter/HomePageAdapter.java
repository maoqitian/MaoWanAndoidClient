package mao.com.mao_wanandroid_client.view.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author maoqitian
 * @Description 首页 RecyclerView Adapter
 * 参照官方文档写法 https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=en#java
 * @Time 2019/5/18 0018 21:31
 */
public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>{



    @NonNull
    @Override
    public HomePageAdapter.HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder homePageViewHolder, int i) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class HomePageViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public HomePageViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
}
