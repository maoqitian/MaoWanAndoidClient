package mao.com.mao_wanandroid_client.view.main.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import mao.com.flexibleflowlayout.TagAdapter;
import mao.com.flexibleflowlayout.TagFlowLayout;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.model.dao.SearchHistoryData;
import mao.com.mao_wanandroid_client.model.modelbean.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.modelbean.search.HotKeyData;
import mao.com.mao_wanandroid_client.presenter.main.SearchPageContract;
import mao.com.mao_wanandroid_client.presenter.main.SearchPagePresenter;
import mao.com.mao_wanandroid_client.utils.NormalAlertDialog;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;
import mao.com.mao_wanandroid_client.widget.LoadingView;

/**
 * @author maoqitian
 * @Description: 搜索 Fragment
 * @date 2019/7/17 0017 11:21
 */
public class SearchFragment extends BaseDialogFragment<SearchPagePresenter> implements
        SearchPageContract.SearchPageView, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.iv_search_clear)
    ImageView mSearchClear;
    @BindView(R.id.tv_cancel_search)
    TextView mCancelSearch;
    @BindView(R.id.et_search)
    EditText mEditTextSearch;
    @BindView(R.id.ll_search_layout)
    LinearLayout mSearchLayout;
    //搜索 loading
    @BindView(R.id.search_view_loading)
    LoadingView mLoadingView;

    //搜索失败 结果为空
    @BindView(R.id.view_error_container)
    ConstraintLayout mErrorContainer;

    //搜索界面
    @BindView(R.id.inflate_view)
    NestedScrollView mSearchContainer;
    @BindView(R.id.tv_search_history)
    TextView mTvshTitle;
    @BindView(R.id.tv_hot_key)
    TextView tvHotKeyTitle;
    @BindView(R.id.flow_layout_search_history)
    TagFlowLayout mfwSearchHistory;
    //清除历史记录
    @BindView(R.id.ll_clear_history)
    LinearLayout mLlClearHistory;
    @BindView(R.id.flow_layout_hot_key)
    TagFlowLayout mfwHotKey;

    //搜索结果
    @BindView(R.id.search_smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.search_result_recyclerview)
    RecyclerView mSearchResultRecyclerView;

    //搜索结果数据
    List<HomeArticleData> mHomeArticleDataList;

    private RecyclerView.LayoutManager layoutManager;
    private HomePageAdapter mSearchResultAdapter;

    //启动搜索页面的类型 普通搜索 公众号搜索
    String pageType;
    //公众号id
    private int wxid = 0;
    //公众号名称
    private String wxName;
    //搜索热词数据
    List<HotKeyData> mHotKeyDataList;

    List<SearchHistoryData> mSearchHistoryDataList;

    public static SearchFragment newInstance(String pageType,int id,String wxname) {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        args.putString(Constants.PAGE_TYPE,pageType);
        args.putInt(Constants.WX_ID,id);
        args.putString(Constants.WX_NAME,wxname);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dialogFragment全屏
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.DialogFullScreen);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.search_fragment_layout;
    }

    @Override
    protected void initViewAndData() {
        mHomeArticleDataList = new ArrayList<>();
        mHotKeyDataList = new ArrayList<>();
        mSearchHistoryDataList = new ArrayList<>();
        initView();
        EditTextSearchListener();

    }

    private void getIntentData() {
        Bundle arguments = getArguments();
        assert arguments != null;
        //启动搜索页面的类型 普通搜索 公众号搜索
        pageType = arguments.getString(Constants.PAGE_TYPE);
        wxid = arguments.getInt(Constants.WX_ID);
        wxName = arguments.getString(Constants.WX_NAME);
    }

    private void initView() {
        //置空editText
        mEditTextSearch.setText("");
        mSearchLayout.setVisibility(View.VISIBLE);
        mCancelSearch.setVisibility(View.VISIBLE);
        mCancelSearch.setOnClickListener(this);
        mSearchClear.setOnClickListener(this);
        mLlClearHistory.setOnClickListener(this);
        //禁止下拉刷新
        mSmartRefreshLayout.setEnableRefresh(false);
        getIntentData();
        if(!TextUtils.isEmpty(wxName)){
            mEditTextSearch.setHint("搜索"+wxName+"公众号历史文章");
        }
        initSearchResultRecycleView();
        setSmartRefreshLayoutListener();

        getSearchHistoryAndHotKeyData();
    }

    private void setSmartRefreshLayoutListener() {
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e("毛麒添","加载更多");
                if(Constants.RESULT_CODE_OFFICIAL_ACCOUNTS_PAGE.equals(pageType)){
                    //公众号搜索搜索加载更多
                    mPresenter.getLoadMoreSearchData(wxid);
                }else {
                    //普通搜索加载更多
                    mPresenter.getLoadMoreSearchData(0);
                }
                refreshLayout.autoLoadMore();
            }
        });
    }

    private void initSearchResultRecycleView() {
        mSearchResultRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        mSearchResultRecyclerView.setLayoutManager(layoutManager);
        mSearchResultAdapter = new HomePageAdapter(R.layout.article_item_cardview_layout);
        mSearchResultAdapter.setOnItemClickListener(this);
        mSearchResultRecyclerView.setAdapter(mSearchResultAdapter);
        mSearchResultAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeArticleData homeArticleData= (HomeArticleData) adapter.getItem(position);
            if(view.getId() == R.id.image_collect){
                //收藏
                if(homeArticleData!=null){
                    addOrCancelCollect(position,homeArticleData);
                }
            }
        });
    }

    private void EditTextSearchListener() {
        mEditTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (textView.getId() == R.id.et_search) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getKeyCode() == KeyEvent.KEYCODE_SEARCH) {
                        String keyword = "";
                        if(!TextUtils.isEmpty(mEditTextSearch.getText())) {
                            keyword = mEditTextSearch.getText().toString().trim();
                            getSearchData(keyword);
                        } else {
                            Toast.makeText(getContext(),"请输入搜索的关键字"+keyword,Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                }
                return false;
            }
        });
        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEditTextSearch.getEditableText().length() >= 1){
                    mSearchClear.setVisibility(View.VISIBLE);
                } else{
                    mSearchClear.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //设置焦点并弹出键盘
        if(mSmartRefreshLayout != null && View.GONE ==  mSmartRefreshLayout.getVisibility()){
            //防止浏览完搜索内容返回搜索界面弹出小键盘
            Log.e("毛麒添","弹出小键盘" );
            ToolsUtils.showSoftInput2(mEditTextSearch);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel_search:
                if(ToolsUtils.isSoftShowing(Objects.requireNonNull(getActivity()))){
                    //如果软键盘没退出
                    ToolsUtils.hideSoftInput(mEditTextSearch);
                }
                dismiss();
                break;
            case R.id.iv_search_clear:
                //清除搜索内容
                mEditTextSearch.setText("");
                //弹出软键盘
                ToolsUtils.showSoftInput2(mEditTextSearch);
                //重新加载搜索界面数据
                getSearchHistoryAndHotKeyData();
                //隐藏搜索结果
                clearSearchResult();
                break;
            case R.id.ll_clear_history:
                //点击清除搜索历史记录
                NormalAlertDialog.getInstance().showAlertDialog(getContext(), getString(R.string.confirm_clera_search_history),
                        getString(R.string.confirm_text), getString(R.string.cancel_text),
                        (dialog, which) -> mPresenter.getClearAllSearchHistoryData(), (dialog, which) -> dialog.dismiss());
                break;
        }
    }
    //获取搜索界面数据
    private void getSearchHistoryAndHotKeyData() {
        mPresenter.getSearchHistoryData();
        mPresenter.getHotKeyData();
    }

    //显示搜索结果 隐藏搜索界面
    private void showSearchResult(){
        //隐藏搜索界面
        mSearchContainer.setVisibility(View.GONE);
        //显示加载 loading
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.startFallAnimator();
    }
    //显示或者隐藏搜索界面
    private void showOrGoneSearchResult(int visibility){
        if(mSmartRefreshLayout!= null && mSearchResultRecyclerView!= null){
            mSmartRefreshLayout.setVisibility(visibility);
            mSearchResultRecyclerView.setVisibility(visibility);
            mHomeArticleDataList.clear();
        }

    }
    //隐藏搜索结果，显示搜索界面
    private void clearSearchResult() {
        /*if(mSmartRefreshLayout!= null && mSearchResultRecyclerView!= null){
            mSmartRefreshLayout.setVisibility(View.GONE);
            mSearchResultRecyclerView.setVisibility(View.GONE);
            mHomeArticleDataList.clear();
        }*/
        showOrGoneSearchResult(View.GONE);
        mErrorContainer.setVisibility(View.GONE);
        //显示搜索界面
        mSearchContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSearchArticleList(List<HomeArticleData> homeArticleDataList,boolean isLoadMore) {
        //隐藏加载 loading
        mLoadingView.setVisibility(View.GONE);
        showOrGoneSearchResult(View.VISIBLE);
        if(isLoadMore){
            mSearchResultAdapter.addData(homeArticleDataList);
            mSmartRefreshLayout.finishLoadMore();
        }else {
            //正常第一次加载搜索数据
            mHomeArticleDataList.clear();
            mHomeArticleDataList.addAll(homeArticleDataList);
            mSearchResultAdapter.replaceData(mHomeArticleDataList);
            showNormal();
        }
    }

    @Override
    public void showLoadDataMessage(String msg) {
        mSmartRefreshLayout.finishLoadMore();
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
    //大家都在搜
    @Override
    public void showHotKeyListData(List<HotKeyData> hotKeyDataList) {
         mHotKeyDataList.clear();
         mHotKeyDataList.addAll(hotKeyDataList);
         tvHotKeyTitle.setVisibility(View.VISIBLE);
         mfwHotKey.setAdapter(new TagAdapter() {
             @Override
             public int getItemCount() {
                 return mHotKeyDataList.size();
             }

             @Override
             public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                 return inflater.inflate(R.layout.flow_text_hot_search_layout,parent,false);
             }

             @Override
             public void bindView(View view, int position) {
                 TextView textView = view.findViewById(R.id.text_tag_hot_search);
                 GradientDrawable gradientDrawable = new GradientDrawable();
                 gradientDrawable.setShape(GradientDrawable.RECTANGLE);//形状
                 gradientDrawable.setCornerRadius(10f);//设置圆角Radius
                 gradientDrawable.setColor(ToolsUtils.getRandSomeColor(getContext()));//颜色
                 view.setBackground(gradientDrawable);
                 textView.setText(mHotKeyDataList.get(position).getName());
             }

             @Override
             public void onTagItemViewClick(View v, int position) {
                 String keyword = mHotKeyDataList.get(position).getName();
                 getSearchData(keyword);
             }
         });
         mfwHotKey.onDataChanged();
    }
    //搜索历史
    @Override
    public void showSearchHistoryListData(List<SearchHistoryData> searchHistoryData) {
          mTvshTitle.setVisibility(View.VISIBLE);
          mfwSearchHistory.setVisibility(View.VISIBLE);
          mLlClearHistory.setVisibility(View.VISIBLE);
          mSearchHistoryDataList.clear();
          mSearchHistoryDataList.addAll(searchHistoryData);
          mfwSearchHistory.setAdapter(new TagAdapter() {
            @Override
            public int getItemCount() {
                return mSearchHistoryDataList.size();
            }

            @Override
            public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.flow_text_hot_search_layout,parent,false);
            }

            @Override
            public void bindView(View view, int position) {
                TextView textView = view.findViewById(R.id.text_tag_hot_search);
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);//形状
                gradientDrawable.setCornerRadius(10f);//设置圆角Radius
                gradientDrawable.setColor(ToolsUtils.getRandSomeColor(getContext()));//颜色
                view.setBackground(gradientDrawable);
                textView.setText(mSearchHistoryDataList.get(position).getData());
            }

            @Override
            public void onTagItemViewClick(View v, int position) {
                String keyword = mSearchHistoryDataList.get(position).getData();
                getSearchData(keyword);
            }
        });
        mfwSearchHistory.onDataChanged();
    }
    
    @Override
    public void showClearAllSearchHistoryEvent() {
        mTvshTitle.setVisibility(View.GONE);
        mfwSearchHistory.setVisibility(View.GONE);
        mLlClearHistory.setVisibility(View.GONE);
        mSearchHistoryDataList.clear();
    }

    //开始搜索
    private void getSearchData(String keyword) {
        //showLoading();
        mEditTextSearch.setText(keyword);
        if (Constants.RESULT_CODE_OFFICIAL_ACCOUNTS_PAGE.equals(pageType)) {
            //公众号搜索搜索
            mPresenter.getWxArticleHistoryByKey(wxid, keyword);
        } else {
            //普通搜索
            mPresenter.getSearchKeyWordData(keyword);
        }
        //关闭软键盘
        ToolsUtils.hideSoftInput(mEditTextSearch);
        //EditText失去焦点
        mEditTextSearch.clearFocus();
        showSearchResult();
    }
    //搜索结果 item 点击
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeArticleData homeArticleData = (HomeArticleData) adapter.getItem(position);
        StartDetailPage.start(getActivity(),homeArticleData, Constants.PAGE_WEB_COLLECT,Constants.ACTION_PAGE_DETAIL_ACTIVITY);
    }

    @Override
    public void showAddArticleCollectStatus(int position,HomeArticleData homeArticleData,String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }
    //显示收藏 或取消 收藏之后的状态
    private void showCollectStatus(int position,HomeArticleData homeArticleData,String msg){
        if(homeArticleData!=null && mSearchResultAdapter!=null){
            mSearchResultAdapter.setData(position,homeArticleData);
        }
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCancelArticleCollectStatus(int position, HomeArticleData homeArticleData,String msg) {
        showCollectStatus(position,homeArticleData,msg);
    }

    //搜索失败
    @Override
    public void showError() {
        mLoadingView.setVisibility(View.GONE);
        mErrorContainer.setVisibility(View.VISIBLE);
    }
}
