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
 * @Description 个人分享文章 item Holder
 * @Time 2019/5/19 0019 18:13
 */
public class PrivateArticleItemHolder extends BaseViewHolder {

    @BindView(R.id.tv_private_article_title)
    TextView mArticalTitle;
    @BindView(R.id.tv_private_article_date)
    TextView mNiceSharedata;
    @BindView(R.id.iv_delete_article)
    ImageView mImageDelete;

    public PrivateArticleItemHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
