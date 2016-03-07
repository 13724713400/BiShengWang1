package com.zhushan.bishengwang.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

public class Activity_main_mytip extends Basetivity{

    private RelativeLayout selft_mytip_rl;
    private ImageView goto_mytip_list;
    private MainVu mainVu = new MainVu();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_main_mytip));
        initView();
        initData();
        initListener();


    }

    @Override
    public void initView() {
        selft_mytip_rl = mainVu.getItemView(R.id.selft_mytip_rl);
        goto_mytip_list = mainVu.getItemView(R.id.goto_mytip_list);
    }
    @Override
    public void initData() {
        BackActivity.finishActivity(this,R.id.main_mytip_back);
        selft_mytip_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getInstance().startToAnoterActivity(Activity_main_mytip.this,Activity_SelftMytip.class,null);
            }
        });

        goto_mytip_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getInstance().startToAnoterActivity(Activity_main_mytip.this,Activity_MyTip.class,null);
            }
        });
    }

    @Override
    public void initListener() {

    }



}
