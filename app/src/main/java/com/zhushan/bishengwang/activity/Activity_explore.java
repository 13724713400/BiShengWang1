package com.zhushan.bishengwang.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.R;

public class Activity_explore extends Basetivity implements View.OnClickListener{

    private ImageView explore_mytip,tansuo_leida_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        explore_mytip = (ImageView) findViewById(R.id.tansuo_mytip_img);
        tansuo_leida_img = (ImageView) findViewById(R.id.tansuo_leida_img);
    }

    @Override
    public void initData() {
        BackActivity.finishActivity(this,R.id.tansuo_back);
        Bitmap bitmap = ImageUtil.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.mipmap.secretary), 20);
        explore_mytip.setImageBitmap(bitmap);
        tansuo_leida_img.setImageBitmap(ImageUtil.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.mipmap.leida), 20));
    }

    @Override
    public void initListener() {
        explore_mytip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tansuo_mytip_img:
                IntentUtils.getInstance().startToAnoterActivity(Activity_explore.this,Activity_main_mytip.class,null);
                break;
        }

    }
}
