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
 * @Description 收藏 网站 item Holder
 * @Time 2019/5/19 0019 18:13
 */
public class CollectionWebItemHolder extends BaseViewHolder {

    /*@BindView(R.id.collection_web_author_icon)
    ImageView mImageAuthor;
    @BindView(R.id.tv_collection_web_author_name)
    TextView mArticalAuthor;*/
    @BindView(R.id.tv_collection_web_article_title)
    TextView mArticalTitle;
    @BindView(R.id.iv_delete_web)
    ImageView mImageDeleteWeb;
    @BindView(R.id.iv_web_edit)
    ImageView mImageWebEdit;

    public CollectionWebItemHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
