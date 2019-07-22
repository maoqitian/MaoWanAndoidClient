package mao.com.mao_wanandroid_client.view.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.presenter.main.SearchPageContract;
import mao.com.mao_wanandroid_client.presenter.main.SearchPagePresenter;

/**
 * @author maoqitian
 * @Description: 搜索 Fragment
 * @date 2019/7/17 0017 11:21
 */
public class SearchFragment extends BaseDialogFragment<SearchPagePresenter> implements SearchPageContract.SearchPageView, View.OnClickListener {




    @BindView(R.id.tv_cancel_search)
    TextView mCancelSearch;

    @BindView(R.id.et_search)
    EditText mEditTextSearch;

    @BindView(R.id.ll_search_layout)
    LinearLayout mSearchLayout;

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dialog全屏
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.DialogFullScreen);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.search_fragment_layout;
    }

    @Override
    protected void initViewAndData() {
        mSearchLayout.setVisibility(View.VISIBLE);
        mCancelSearch.setVisibility(View.VISIBLE);
        mCancelSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel_search :
                dismiss();
                break;
        }
    }
}
