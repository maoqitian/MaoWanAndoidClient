package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.application.Constants;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.model.modelbean.webmark.WebBookMark;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionDialogContract;
import mao.com.mao_wanandroid_client.presenter.drawer.CollectionDialogPresenter;
import mao.com.mao_wanandroid_client.utils.EditTextUtils;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;

/**
 * @author maoqitian
 * @Description CollectionDialog 添加收藏 修改收藏
 * @Time 2019/8/24 0024 15:00
 */
public class CollectionDialogFragment extends BaseDialogFragment<CollectionDialogPresenter> implements
        CollectionDialogContract.CollectionDialogView , View.OnClickListener {


    @BindView(R.id.btn_confirm)
    Button btnConfirmCollection;
    @BindView(R.id.btn_cancel_close)
    Button btnCancelCancelDialog;
    @BindView(R.id.tv_dialog_title)
    TextView tvDialogTitle ;
    @BindView(R.id.et_collection_title)
    EditText edCollectionTitle;
    @BindView(R.id.et_collection_author_name)
    EditText edCollectionAuthorName;
    @BindView(R.id.et_collection_link)
    EditText edCollectionLink ;
    @BindView(R.id.iv_close_dialog)
    ImageView ivCloseDialog;

    private String dialogType;
    //默认 add
    boolean isAdd ;

    WebBookMark webBookMark;
    //更新网站数据的item 位置
    private int position;


    public static CollectionDialogFragment newInstance(String dialogType, boolean isAdd,WebBookMark webBookMark,int position) {

        Bundle args = new Bundle();
        CollectionDialogFragment fragment = new CollectionDialogFragment();
        args.putString("dialogType",dialogType);
        args.putBoolean("isAdd",isAdd);
        args.putSerializable("WebBookMark",webBookMark);
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        //设置软键盘弹出模式 防止遮挡Dialog
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.collection_dialog_layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = getActivity().getResources().getDisplayMetrics().widthPixels-100;
        view.setLayoutParams(layoutParams);
        //设置焦点并弹出键盘
        ToolsUtils.showSoftInput2(edCollectionTitle);
    }

    @Override
    protected void initViewAndData() {
        //确认按钮不可点击
        btnConfirmCollection.setEnabled(false);
        EditTextUtils.textChangeListener textChangeListener = new EditTextUtils.textChangeListener(btnConfirmCollection);
        getIntentData();
        if (Constants.COLLECTION_WEB_TYPE.equals(dialogType)){
            //收藏网站
            textChangeListener.addAllEditText(edCollectionTitle,edCollectionLink);
            edCollectionAuthorName.setVisibility(View.GONE);
            tvDialogTitle.setText(R.string.add_collection_web);
            if(!isAdd && webBookMark!=null){
                //更新则设置好原始数据
                tvDialogTitle.setText(R.string.update_collection_web);
                edCollectionTitle.setText(webBookMark.getName());
                edCollectionLink.setText(webBookMark.getLink());
            }
        }else if(Constants.COLLECTION_ARTICLE_TYPE.equals(dialogType)){
            //收藏文章（主动）
            tvDialogTitle.setText(R.string.add_collection_article);
            textChangeListener.addAllEditText(edCollectionTitle,edCollectionAuthorName,edCollectionLink);
        }
        btnConfirmCollection.setOnClickListener(this);
        btnCancelCancelDialog.setOnClickListener(this);
        ivCloseDialog.setOnClickListener(this);
    }

    private void getIntentData() {
        Bundle arguments = getArguments();
        assert arguments != null;
        //启动搜索页面的类型 普通搜索 公众号搜索
        dialogType = arguments.getString("dialogType");
        isAdd = arguments.getBoolean("isAdd",true);
        webBookMark = (WebBookMark) arguments.getSerializable("WebBookMark");
        position = arguments.getInt("position",-1);
    }

    @Override
    public void onStop() {
        super.onStop();
        //隐藏键盘
        if(ToolsUtils.isSoftShowing((Activity)mContext)){
            //隐藏软键盘，
            Log.e("毛麒添","软键盘没关闭");
            ToolsUtils.hideKeyboard((Activity) mContext);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:
                if (Constants.COLLECTION_WEB_TYPE.equals(dialogType)){
                    //收藏网站
                    if(isAdd){
                        //添加
                        mPresenter.getAddCollectWebData(
                                getActivity(),
                                edCollectionTitle.getText().toString(),
                                edCollectionLink.getText().toString().trim());
                    }else {
                        //更新
                        if (webBookMark!=null){
                            webBookMark.setName(edCollectionTitle.getText().toString());
                            webBookMark.setLink(edCollectionLink.getText().toString().trim());
                            mPresenter.getUpdateCollectWebData(getActivity(),webBookMark,position);
                        }
                    }
                }else if(Constants.COLLECTION_ARTICLE_TYPE.equals(dialogType)){
                    //收藏文章（主动）
                    mPresenter.getAddCollectArticleData(getActivity(),
                            edCollectionTitle.getText().toString(),
                            edCollectionAuthorName.getText().toString(),
                            edCollectionLink.getText().toString().trim());
                }
                dismiss();
                break;
            case R.id.btn_cancel_close:
            case R.id.iv_close_dialog:
                dismiss();
                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(ToolsUtils.isSoftShowing((Activity)mContext)){
            //隐藏软键盘
            Log.e("毛麒添","软键盘没关闭");
            InputMethodManager inputMgr = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMgr.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
        }
    }
}
