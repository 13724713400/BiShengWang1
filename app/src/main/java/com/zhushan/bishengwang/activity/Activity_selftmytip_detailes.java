package com.zhushan.bishengwang.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

public class Activity_selftmytip_detailes extends Basetivity {

    private MainVu mainVu = new MainVu();
    private ImageView selft_mytip_img1,selft_mytip_img2,selft_mytip_img3,selft_mytip_img4;
    private TextView selft_mytip_titles,selft_mytip_content;
    private Button selft_mytip_beginused;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_activity_selftmytip_detailes));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        selft_mytip_img1 =   mainVu.getItemView(R.id.selft_mytip_img1);
        selft_mytip_img2 =  mainVu.getItemView(R.id.selft_mytip_img2);
        selft_mytip_img3 =  mainVu.getItemView(R.id.selft_mytip_img3);
        selft_mytip_img4 =  mainVu.getItemView(R.id.selft_mytip_img4);
        selft_mytip_titles =  mainVu.getItemView(R.id.selft_mytip_titles);
        selft_mytip_content =  mainVu.getItemView(R.id.selft_mytip_content);
        selft_mytip_beginused  = mainVu.getItemView(R.id.selft_mytip_beginused);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}
