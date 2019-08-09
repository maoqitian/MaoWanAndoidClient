package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mao.com.flexibleflowlayout.TagAdapter;
import mao.com.flexibleflowlayout.TagFlowLayout;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.model.modelbean.frienduser.CommonWebData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.presenter.drawer.CommonWebContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CommonWebPresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;

/**
 * @author maoqitian
 * @Description: 常用网站
 * @date 2019/7/26 0026 15:52
 */
public class CommonWebFragment extends BaseDialogFragment<CommonWebPresenter> implements CommonWebContract.CommonWebView {

    @BindView(R.id.flow_layout_common_web)
    TagFlowLayout mTagFlowLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_page_title)
    TextView mPageTitle;

    List<CommonWebData> mCommonWebDataList;

    String mTabTitle;

    public static CommonWebFragment newInstance(String titleName) {
        Bundle args = new Bundle();
        args.putString(Constants.TAG_TAB_NAME,titleName);
        CommonWebFragment fragment = new CommonWebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dialogFragment全屏
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFullScreen);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_web_fragment_layout;
    }

    @Override
    protected void initViewAndData() {
        mCommonWebDataList = new ArrayList<>();
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mToolbar.setNavigationOnClickListener(v -> dismiss());
        if (getArguments() != null) {
            mTabTitle = getArguments().getString(Constants.TAG_TAB_NAME);
            mPageTitle.setText(mTabTitle);
        }
        mPresenter.getCommonWebData();
    }

    @Override
    public void showCommonWebData(List<CommonWebData> commonWebDataList) {
        mCommonWebDataList.clear();
        mCommonWebDataList.addAll(commonWebDataList);
        mTagFlowLayout.setAdapter(new TagAdapter() {
            @Override
            public int getItemCount() {
                return mCommonWebDataList.size();
            }

            @Override
            public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.flow_text_common_web_layout,parent,false);
            }

            @Override
            public void bindView(View view, int position) {
                TextView textView = view.findViewById(R.id.text_tag_common_web);
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);//形状
                gradientDrawable.setCornerRadius(10f);//设置圆角Radius
                gradientDrawable.setColor(ToolsUtils.getRandSomeColor(getContext()));//颜色
                view.setBackground(gradientDrawable);
                textView.setText(mCommonWebDataList.get(position).getName());
            }

            @Override
            public void onTagItemViewClick(View v, int position) {
                HomeArticleData homeArticleData = new HomeArticleData();
                homeArticleData.setTitle(mCommonWebDataList.get(position).getName());
                homeArticleData.setLink(mCommonWebDataList.get(position).getLink());
                StartDetailPage.start(getActivity(),homeArticleData, Constants.PAGE_WEB_NOT_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
            }
        });
        mTagFlowLayout.onDataChanged();
    }
}
