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
 * @Description 首页推荐 文章列表  Holder
 * @Time 2019/5/19 0019 18:13
 */
public class HomePageViewItemHolder extends BaseViewHolder {

    @BindView(R.id.image_author_icon)
    ImageView mImageAuthor;
    @BindView(R.id.image_collect)
    ImageView mImageCollect;
    @BindView(R.id.tv_author_name)
    TextView mArticalAuthor;
    @BindView(R.id.tv_artical_title)
    TextView mArticalTitle;
    @BindView(R.id.tv_super_chapterName)
    TextView mSuperChapterName;
    /*@BindView(R.id.tv_chapterName)
    TextView mChapterName;*/
    @BindView(R.id.tv_artical_top_tag)
    TextView mArticalTopTag;
    @BindView(R.id.tv_artical_new_tag)
    TextView mArticalNewTag;
    @BindView(R.id.tv_artical_tag)
    TextView mArticalTag;
    @BindView(R.id.tv_artical_date)
    TextView mArticalDate;
    //@BindView(R.id.image_heart_collect)
    //LottieAnimationView mCollectView;

    public HomePageViewItemHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
