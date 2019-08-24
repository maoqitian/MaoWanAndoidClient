package mao.com.mao_wanandroid_client.view.main.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import mao.com.mao_wanandroid_client.R;

/**
 * @author maoqitian
 * @Description 首页 最新项目 holder
 * @Time 2019/5/29 0029 20:47
 */
public class HomeLatestProjectHolder extends BaseViewHolder {
    @BindView(R.id.tv_project_title)
    TextView mProjectTitle;
    @BindView(R.id.tv_project_desc)
    TextView mProjectDesc;
    @BindView(R.id.iv_project_pic)
    ImageView mProjectPic;
    @BindView(R.id.image_project_collect)
    ImageView mImageProjectCollect;
    @BindView(R.id.tv_project_date)
    TextView mProjectDate;
    @BindView(R.id.tv_project_author_name)
    TextView mProjectAuthorName;

    public HomeLatestProjectHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
