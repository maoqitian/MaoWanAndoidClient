package mao.com.mao_wanandroid_client.view.drawer.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import mao.com.mao_wanandroid_client.R;
import mao.com.mao_wanandroid_client.base.fragment.BaseDialogFragment;
import mao.com.mao_wanandroid_client.presenter.drawer.ArticleShareDialogContract;
import mao.com.mao_wanandroid_client.presenter.drawer.ArticleShareDialogPresenter;
import mao.com.mao_wanandroid_client.utils.EditTextUtils;
import mao.com.mao_wanandroid_client.utils.ToolsUtils;

/**
 * @Description: 分享文章 dialog
 * @Author: maoqitian
 * @Date: 2019-10-06 16:26
 */
public class ArticleShareDialogFragment extends BaseDialogFragment<ArticleShareDialogPresenter> implements
        ArticleShareDialogContract.ArticleShareDialogView , View.OnClickListener{


    @BindView(R.id.btn_share)
    Button btnConfirmShare;
    @BindView(R.id.btn_cancel_share)
    Button btnCancelDialog;
    @BindView(R.id.tv_share_dialog_title)
    TextView tvDialogTitle ;
    @BindView(R.id.et_share_title)
    EditText edShareTitle;
    @BindView(R.id.et_share_author_name)
    EditText edShareAuthorName;
    @BindView(R.id.et_share_link)
    EditText edShareLink ;
    @BindView(R.id.iv_close_share)
    ImageView ivCloseDialog;


    public static ArticleShareDialogFragment newInstance() {

        Bundle args = new Bundle();

        ArticleShareDialogFragment fragment = new ArticleShareDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.square_share_dialog_layout;
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
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = getActivity().getResources().getDisplayMetrics().widthPixels-100;
        view.setLayoutParams(layoutParams);
        //设置焦点并弹出键盘
        ToolsUtils.showSoftInput2(edShareTitle);
    }

    @Override
    protected void initViewAndData() {
        super.initViewAndData();
        //确认按钮不可点击
        btnConfirmShare.setEnabled(false);
        EditTextUtils.textChangeListener textChangeListener = new EditTextUtils.textChangeListener(btnConfirmShare);
        textChangeListener.addAllEditText(edShareTitle,edShareLink);
        edShareAuthorName.setHint(mPresenter.getLoginUserName());
        //分享用户名设置不可编辑且无点击事件
        edShareAuthorName.setFocusable(false);
        edShareAuthorName.setFocusableInTouchMode(false);
        edShareAuthorName.setOnClickListener(null);

        btnConfirmShare.setOnClickListener(this);
        btnCancelDialog.setOnClickListener(this);
        ivCloseDialog.setOnClickListener(this);


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
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(ToolsUtils.isSoftShowing((Activity)mContext)){
            //隐藏软键盘
            Log.e("毛麒添","软键盘没关闭");
            InputMethodManager inputMgr = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMgr.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_share:
                mPresenter.getAddCollectWebData(getActivity(),edShareTitle.getText().toString(),
                                                edShareLink.getText().toString().trim());
                break;
            case R.id.btn_cancel_share:
            case R.id.iv_close_share:
                dismiss();
                break;
        }
    }

    @Override
    public void showConfirmShareStatus() {
        //关闭 dialog
        dismiss();
    }
}
