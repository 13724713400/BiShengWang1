package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

public class Activity_resetPassword extends Basetivity implements View.OnClickListener{

    private MainVu mainVu = new MainVu();
    private EditText reset_password,reset_againpssword;
    private Button reset_finish;
    private String telephone,authCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this, R.layout.activity_reset_password));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {

        reset_password =  mainVu.getItemView(R.id.reset_password);
        reset_againpssword =  mainVu.getItemView(R.id.reset_againpssword);
        reset_finish =  mainVu.getItemView(R.id.reset_finish);
    }

    @Override
    public void initData() {

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            authCode  = bundle.getString("authCode");
            telephone =  bundle.getString("telephone");
        }

    }

    @Override
    public void initListener() {
        reset_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.reset_finish:

                if (!EdiTextUtils.getInstance().CheckedEmpty(reset_password,getResources().getString(R.string.register_emptypssword)))
                {
                    return;
                }

                if (!EdiTextUtils.getInstance().CheckedEmpty(reset_againpssword,getResources().getString(R.string.register_emptypssword)))
                {
                    return;
                }

                if (!EdiTextUtils.getInstance().CheckPasswoeEqual(reset_password,reset_againpssword))
                {
                        return;
                }

                HashMap<String,String> map = new HashMap<String,String>();
                map.put("authCode",authCode);
                map.put("telephone",telephone);
                map.put("pwd",reset_againpssword.getText().toString().trim());
                map.put("step",String.valueOf(2));
                HttpFactory.getInstance().resetPassword(Activity_resetPassword.this, map);
                break;

        }
    }
}
