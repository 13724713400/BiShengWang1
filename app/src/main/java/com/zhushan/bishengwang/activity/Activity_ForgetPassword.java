package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.TimeCount;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

public class Activity_ForgetPassword extends Basetivity implements View.OnClickListener {
    private MainVu mainVu = new MainVu();
    private EditText forget_phoneNum,forget_code_et;
    private Button forget_getCode_btn,forget_next;
    private TimeCount timeCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this, R.layout.activity_forget_password));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        forget_phoneNum =  mainVu.getItemView(R.id.forget_phoneNum);
        forget_getCode_btn =  mainVu.getItemView(R.id.forget_getCode_btn);
        forget_code_et  =  mainVu.getItemView(R.id.forget_code_et);
        forget_next =  mainVu.getItemView(R.id.forget_next);
    }

    @Override
    public void initData() {
        timeCount = new TimeCount(30000,1000,forget_getCode_btn);
    }

    @Override
    public void initListener() {
        forget_getCode_btn.setOnClickListener(this);
        forget_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.forget_getCode_btn:
                timeCount.start();
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("username",forget_phoneNum.getText().toString().trim());
                map.put("type",String.valueOf(1));

                HttpFactory.getInstance().sendAuthCode(Activity_ForgetPassword.this, map);
                break;
            case R.id.forget_next:
                if (!EdiTextUtils.getInstance().CheckedEmpty(forget_phoneNum, getResources().getString(R.string.register_emptyphone)))
                {
                    return;
                }

                if (!EdiTextUtils.getInstance().CheckedEmpty(forget_code_et,getResources().getString(R.string.register_emptycode)))
                {
                    return;
                }

                HashMap<String,String> map2 = new HashMap<String, String>();
                map2.put("telephone",forget_phoneNum.getText().toString().trim());
                map2.put("captcha",forget_code_et.getText().toString().trim());
                map2.put("step",String.valueOf(1));
                HttpFactory.getInstance().forgetPassword(Activity_ForgetPassword.this, map2, forget_phoneNum.getText().toString().trim());



                break;

        }
    }
}
