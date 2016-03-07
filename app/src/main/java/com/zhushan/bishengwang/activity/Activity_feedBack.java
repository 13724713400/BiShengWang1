package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;

public class Activity_feedBack extends Basetivity implements View.OnClickListener {

    private MainVu mainVu = new MainVu();
    private Button FeedBack_submit;
    private EditText feedBack_editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_activity_feed_back));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {

        feedBack_editText =  mainVu.getItemView(R.id.feedBack_editText);
        FeedBack_submit =  mainVu.getItemView(R.id.FeedBack_submit);
    }

    @Override
    public void initData() {

        BackActivity.finishActivity(this,R.id.feet_back);

    }

    @Override
    public void initListener() {
        FeedBack_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.FeedBack_submit:
                if(!EdiTextUtils.getInstance().CheckedEmpty(feedBack_editText,getResources().getString(R.string.feet_back_empty_content)))
                {
                    return;
                }
                IntentUtils.getInstance().startToAnoterActivity(Activity_feedBack.this, MainActivity.class,null);
                break;

        }

    }
}
