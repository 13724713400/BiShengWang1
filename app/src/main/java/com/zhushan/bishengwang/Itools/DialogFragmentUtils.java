package com.zhushan.bishengwang.Itools;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Administrator on 2015/12/4.
 */
public class DialogFragmentUtils {
    private static DialogFragmentUtils dialogFragmentUtils;
    private DialogFragmentUtils(){}
    public static DialogFragmentUtils getInstance()
    {
        if (dialogFragmentUtils==null)
        {
            dialogFragmentUtils = new DialogFragmentUtils();
        }

        return dialogFragmentUtils;

    }
    public void showDialogfragment(FragmentActivity fragmentActivity,DialogFragment dialogFragment)
    {
        FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager()
                .beginTransaction();

        dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        dialogFragment.setCancelable(false);

        dialogFragment.show(fragmentTransaction,"dialog");

    }
    public void dissDialogFragment(FragmentActivity fragmentActivity,DialogFragment dialogFragment)
    {
        FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager()
                .beginTransaction();
        dialogFragment.dismiss();
    }

}
