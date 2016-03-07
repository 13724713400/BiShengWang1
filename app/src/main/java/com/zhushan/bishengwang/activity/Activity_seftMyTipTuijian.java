package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

public class Activity_seftMyTipTuijian extends Basetivity {
    private MainVu mainVu = new MainVu();
    private ImageView selftitem_tuijian_img1,selftitem_tuijian_img2,selftitem_tuijian_img3,selftitem_tuijian_img4;
    private TextView selftitem_tuijian_titles,selftitem_tuijian_content;
    private Button selftitem_tuijian_beginUse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_activity_selftmytiptuijian_detailes));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        selftitem_tuijian_beginUse =  mainVu.getItemView(R.id.selftitem_tuijian_beginUse);
        selftitem_tuijian_titles =  mainVu.getItemView(R.id.selftitem_tuijian_titles);
        selftitem_tuijian_content =  mainVu.getItemView(R.id.selftitem_tuijian_content);
        selftitem_tuijian_img1 =  mainVu.getItemView(R.id.selftitem_tuijian_img1);
        selftitem_tuijian_img2 =  mainVu.getItemView(R.id.selftitem_tuijian_img2);
        selftitem_tuijian_img3 =  mainVu.getItemView(R.id.selftitem_tuijian_img3);
        selftitem_tuijian_img4 =  mainVu.getItemView(R.id.selftitem_tuijian_img4);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
