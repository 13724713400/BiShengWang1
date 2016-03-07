package com.zhushan.bishengwang.Itools;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/12/1.
 */
public class ChangeStateUtils{

    private ChangeStateUtils()
    {}
    private static ChangeStateUtils changeStateUtils;

    public static ChangeStateUtils getInstance()
    {
        if (changeStateUtils==null)
        {
            changeStateUtils  = new ChangeStateUtils();
        }

        return  changeStateUtils;
    }
    public void changeSate(final TextView textView,final int color, final ImageView imageView ,final int imgRS)
    {
        if (imageView!=null) {
            imageView.setImageResource(imgRS);
        }
        if (textView!=null) {
            textView.setTextColor(color);
        }
    }

    public void changeTextSate(final TextView textView,final int color,int RS)
    {

        if (textView!=null) {
            textView.setBackgroundResource(RS);
            textView.setTextColor(color);
        }
    }



}
