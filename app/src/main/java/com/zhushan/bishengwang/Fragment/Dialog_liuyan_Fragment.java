package com.zhushan.bishengwang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zhushan.bishengwang.R;

/**
 * Created by Administrator on 2015/12/23.
 */
public class Dialog_liuyan_Fragment extends DialogFragment implements View.OnClickListener{

    private EditText liuyan_content;
    private Button liuyan_cancle,liuyan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.dialog_liuyan,container,false);

        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        liuyan_cancle.setOnClickListener(this);
        liuyan.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView(View view) {
        liuyan_content = (EditText) view.findViewById(R.id.liuyan_content);
        liuyan_cancle = (Button) view.findViewById(R.id.liuyan_cancle);
        liuyan = (Button) view.findViewById(R.id.liuyan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.liuyan_cancle:
                Dialog_liuyan_Fragment.this.dismiss();
                break;

            case R.id.liuyan:
                Dialog_liuyan_Fragment.this.dismiss();
                break;


        }
    }
}
