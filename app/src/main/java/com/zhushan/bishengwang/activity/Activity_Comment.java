package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.SendCommnetUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

public class Activity_Comment extends Basetivity {

    private HashMap<String,String> map;
    private MainVu mainVu = new MainVu();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_activity__comment));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        BackActivity.finishActivity(this,R.id.comment_back);
    }

    @Override
    public void initData() {
        map = new HashMap<String,String>();
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
             bundle.getString("id");
             bundle.getString("id");
             bundle.getString("id");
             map.put("token", SharePreferenceUtils.getInstance(Activity_Comment.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
             map.put("id",  bundle.getString("id"));
             map.put("cate_id",bundle.getString("cate_id"));
             map.put("user_id", bundle.getString("user_id"));

        }

        mainVu.getItemView(R.id.Commnet_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("comment", ((EditText)mainVu.getItemView(R.id.commnent_editText)).getText().toString().trim());
                HttpFactory.getInstance().SendMessage(Activity_Comment.this, map);
            }
        });


    }

    @Override
    public void initListener() {

    }
}
