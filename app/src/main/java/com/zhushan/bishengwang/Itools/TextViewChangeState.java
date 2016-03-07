package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhushan.bishengwang.R;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2015/12/1.
 */
public class TextViewChangeState{
    private TextView textViewone,textViewtwo,textViewthree;
    private Context context;
    public TextViewChangeState(TextView textViewone,TextView textViewtwo,TextView textViewthree,Context context)
    {
        this.textViewone = textViewone;
        this.textViewtwo  =textViewtwo;
        this.textViewthree = textViewthree;
        this.context = context;
    }


    public void chageTextView()
    {
        textViewone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewone.setTextColor(context.getResources().getColor(R.color.textcoloryellow));
                textViewone.setBackgroundResource(R.drawable.homepage_radiobutton_bg);
                textViewtwo.setBackgroundResource(R.drawable.homepage_radiobutton_bg_defalt);
                textViewtwo.setTextColor(context.getResources().getColor(R.color.textcolorback));
                textViewthree.setBackgroundResource(R.drawable.homepage_radiobutton_bg_defalt);
                textViewthree.setTextColor(context.getResources().getColor(R.color.textcolorback));
            }
        });
        textViewtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewtwo.setTextColor(context.getResources().getColor(R.color.textcoloryellow));
                textViewtwo.setBackgroundResource(R.drawable.homepage_radiobutton_bg);
                textViewone.setBackgroundResource(R.drawable.homepage_radiobutton_bg_defalt);
                textViewone.setTextColor(context.getResources().getColor(R.color.textcolorback));
                textViewthree.setBackgroundResource(R.drawable.homepage_radiobutton_bg_defalt);
                textViewthree.setTextColor(context.getResources().getColor(R.color.textcolorback));

            }
        });
        textViewthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewthree.setTextColor(context.getResources().getColor(R.color.textcoloryellow));
                textViewthree.setBackgroundResource(R.drawable.homepage_radiobutton_bg);
                textViewone.setBackgroundResource(R.drawable.homepage_radiobutton_bg_defalt);
                textViewone.setTextColor(context.getResources().getColor(R.color.textcolorback));
                textViewtwo.setBackgroundResource(R.drawable.homepage_radiobutton_bg_defalt);
                textViewtwo.setTextColor(context.getResources().getColor(R.color.textcolorback));
            }
        });

    }



}
