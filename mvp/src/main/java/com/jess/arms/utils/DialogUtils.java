package com.jess.arms.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;

import com.jess.arms.R;
import com.jess.arms.widget.loading.ACProgressConstant;
import com.jess.arms.widget.loading.ACProgressFlower;

/**
 * ================================================================
 * 创建时间：2017/10/31 17:14
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class DialogUtils {
    private static DialogUtils mDialogUtils;

    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        mDialogUtils = mDialogUtils == null ? new DialogUtils() : mDialogUtils;
        return mDialogUtils;
    }

    public Dialog getLoadingDialog(Activity mActivity, DialogInterface.OnCancelListener mCancelListener) {
        ACProgressFlower dialog = new ACProgressFlower.Builder(mActivity, R.style.NonDimACProgressDialog)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
        dialog.setCanceledOnTouchOutside(false);
        if (mCancelListener != null) dialog.setOnCancelListener(mCancelListener);
        dialog.show();
        return dialog;
    }
}
