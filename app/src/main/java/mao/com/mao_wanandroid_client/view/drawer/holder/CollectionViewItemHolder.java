package mao.com.mao_wanandroid_client.view.drawer.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import mao.com.mao_wanandroid_client.R;

/**
 * @author maoqitian
 * @Description 收藏 列表 item Holder
 * @Time 2019/5/19 0019 18:13
 */
public class CollectionViewItemHolder extends BaseViewHolder {

    @BindView(R.id.collection_author_icon)
    ImageView mImageAuthor;
    @BindView(R.id.more_collect)
    ImageView mImageCollect;
    @BindView(R.id.tv_collection_author_name)
    TextView mArticalAuthor;
    @BindView(R.id.tv_collection_artical_title)
    TextView mArticalTitle;
    @BindView(R.id.collection_super_chapterName)
    TextView mSuperChapterName;
    @BindView(R.id.tv_collection_date)
    TextView mArticalDate;

    public CollectionViewItemHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
