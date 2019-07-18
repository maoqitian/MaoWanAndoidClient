package mao.com.mao_wanandroid_client.view.main.fragment;

import android.os.Bundle;
import android.view.View;
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
public class SearchFragment extends BaseDialogFragment<SearchPagePresenter> implements SearchPageContract.SearchPageView {



    @BindView(R.id.tv_search)
    TextView mTextView;

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.search_fragment_layout;
    }

    @Override
    protected void initViewAndData() {
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
