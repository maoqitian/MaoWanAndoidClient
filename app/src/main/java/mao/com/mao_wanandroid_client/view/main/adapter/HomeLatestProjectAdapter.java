package mao.com.mao_wanandroid_client.view.main.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.utils.GlideUtils;
import mao.com.mao_wanandroid_client.view.main.hloder.HomeLatestProjectHolder;

/**
 * @author maoqitian
 * @Description 首页 最新项目  RecyclerView Adapter
 * @Time 2019/5/29 0029 20:46
 */
public class HomeLatestProjectAdapter extends BaseQuickAdapter<HomeArticleData, HomeLatestProjectHolder> {

    public HomeLatestProjectAdapter(int layoutResId, @Nullable List<HomeArticleData> data) {
        super(layoutResId, data);
    }

    public HomeLatestProjectAdapter(@Nullable List<HomeArticleData> data) {
        super(data);
    }

    public HomeLatestProjectAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(HomeLatestProjectHolder helper, HomeArticleData item) {
         helper.setText(R.id.tv_project_title,item.getTitle())
               .setText(R.id.tv_project_desc,item.getDesc())
               .setText(R.id.tv_project_tag,item.getChapterName())
               .setText(R.id.tv_project_date,item.getNiceDate())
               .setText(R.id.tv_project_author_name,item.getAuthor())
               .addOnClickListener(R.id.image_project_collect) //项目收藏
               .addOnClickListener(R.id.tv_project_tag); //项目tag

         GlideUtils.showBannerImage(mContext,helper.getView(R.id.iv_project_pic),item.getEnvelopePic());
         if(item.isCollect()){
            helper.setImageDrawable(R.id.image_project_collect, ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_collect_24dp));
         }else {
            helper.setImageDrawable(R.id.image_project_collect,ContextCompat.getDrawable(mContext,R.drawable.ic_favorite_gray_24dp));
         }
    }
}
