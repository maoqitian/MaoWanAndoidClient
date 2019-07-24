package mao.com.mao_wanandroid_client.view.main.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import mao.com.mao_wanandroid_client.core.dao.SearchHistoryData;
import mao.com.mao_wanandroid_client.model.home.HomeArticleData;
import mao.com.mao_wanandroid_client.model.search.HotKeyData;
import mao.com.mao_wanandroid_client.presenter.main.SearchPageContract;
import mao.com.mao_wanandroid_client.presenter.main.SearchPagePresenter;
import mao.com.mao_wanandroid_client.utils.StartDetailPage;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;
import mao.com.mao_wanandroid_client.view.main.adapter.HomePageAdapter;

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

    //搜索界面
    @BindView(R.id.nested_view)
    NestedScrollView mSearchContainer;
    @BindView(R.id.tv_search_history)
    TextView mTvshTitle;
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
        mEditTextSearch.setText("");
        mSearchLayout.setVisibility(View.VISIBLE);
        mCancelSearch.setVisibility(View.VISIBLE);
        mCancelSearch.setOnClickListener(this);
        mSearchClear.setOnClickListener(this);
        //禁止下拉刷新
        mSmartRefreshLayout.setEnableRefresh(false);
        getIntentData();
        if(!TextUtils.isEmpty(wxName)){
            mEditTextSearch.setHint("搜索"+wxName+"公众号历史文章");
        }
        initSearchResultRecycleView();
        setSmartRefreshLayoutListener();
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
                            //关闭软键盘
                            ToolsUtils.hideSoftInput(mEditTextSearch);
                            showSearchResult();
                            //去除焦点
                            mEditTextSearch.clearFocus();
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
        if(mSmartRefreshLayout!=null && View.GONE ==  mSmartRefreshLayout.getVisibility()){
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
                //清除搜索结果
                clearSearchResult();
                break;
        }
    }
    //显示搜索结果
    private void showSearchResult(){
        if(mSmartRefreshLayout!= null && mSearchResultRecyclerView!= null){
            mSmartRefreshLayout.setVisibility(View.VISIBLE);
            mSearchResultRecyclerView.setVisibility(View.VISIBLE);
            mHomeArticleDataList.clear();
        }
    }

    //清除搜索结果
    private void clearSearchResult() {
        if(mSmartRefreshLayout!= null && mSearchResultRecyclerView!= null){
            mSmartRefreshLayout.setVisibility(View.GONE);
            mSearchResultRecyclerView.setVisibility(View.GONE);
            mHomeArticleDataList.clear();
        }

    }

    @Override
    public void showSearchArticleList(List<HomeArticleData> homeArticleDataList,boolean isLoadMore) {
        if(isLoadMore){
            mSearchResultAdapter.addData(homeArticleDataList);
            mSmartRefreshLayout.finishLoadMore();
        }else {
            //正常第一次加载搜索数据
            mHomeArticleDataList.clear();
            mHomeArticleDataList.addAll(homeArticleDataList);
            mSearchResultAdapter.replaceData(mHomeArticleDataList);
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
                 gradientDrawable.setColor(ToolsUtils.getRandSomeColor());//颜色
                 view.setBackground(gradientDrawable);
                 textView.setText(mHotKeyDataList.get(position).getName());
             }

             @Override
             public void onTagItemViewClick(View v, int position) {
                 String keyword = mHotKeyDataList.get(position).getName();
                 getSearchData(keyword);
             }
         });
    }

    @Override
    public void showSearchHistoryListData(List<SearchHistoryData> searchHistoryData) {

    }
    
    @Override
    public void showClearAllSearchHistoryEvent() {

    }

    //开始搜索
    private void getSearchData(String keyword) {
        if (Constants.RESULT_CODE_OFFICIAL_ACCOUNTS_PAGE.equals(pageType)) {
            //公众号搜索搜索
            mPresenter.getWxArticleHistoryByKey(wxid, keyword);
        } else {
            //普通搜索
            mPresenter.getSearchKeyWordData(keyword);
        }
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
}
