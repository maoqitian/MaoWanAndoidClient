package mao.com.mao_wanandroid_client.view.main.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;

import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.model.modelbean.banner.HomePageBannerModel;
import mao.com.mao_wanandroid_client.utils.GlideUtils;

/**
 * @author maoqitian
 * @Description 首页 轮播图 holder
 * @Time 2019/5/18 0018 22:30
 */
public class BannerHolderView extends Holder<HomePageBannerModel> {

    private ImageView mImage;
    private TextView mText;
    private Context mContext;
    public BannerHolderView(View itemView, Context context) {
        super(itemView);
        mContext = context;
    }


    @Override
    protected void initView(View itemView) {
        mImage = itemView.findViewById(R.id.image_banner);
        mText = itemView.findViewById(R.id.text_banner);
    }

    @Override
    public void updateUI(HomePageBannerModel data) {
        GlideUtils.showBannerImage(mContext,mImage,data.getImagePath());
        mText.setText(data.getTitle());
    }
}
