package com.zhushan.bishengwang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;
import com.zhushan.bishengwang.Constance.BundleConstance;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.TimeCount;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

public class Activity_Register extends Basetivity implements View.OnClickListener {
    private Button register_btn,register_getCode;
    private EditText register_phoneNum,register_code,register_password;
    private MainVu mainVu = new MainVu();
    private TimeCount timeCount;
    private TextView user_greetment_text;
    private ImageView register_weixinlogin,register_qqlogin;

    private UMSocialService mController = UMServiceFactory
            .getUMSocialService("com.umeng.login");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this, R.layout.activity_register));
        com.umeng.socialize.utils.Log.LOG = true;
        initView();
        initData();
        initListener();
    }
    @Override
    public void initData() {
        UmengUtils.getInstance().InitPlatform(Activity_Register.this,mController);
        timeCount = new TimeCount(30000,1000,register_getCode);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!EdiTextUtils.getInstance().CheckedEmpty(register_phoneNum, getResources().getString(R.string.register_emptyphone))) {
                    return;

                }
                if (!EdiTextUtils.getInstance().CheckedEmpty(register_code, getResources().getString(R.string.register_emptycode))) {
                    return;
                }
                if (!EdiTextUtils.getInstance().CheckedEmpty(register_password, getResources().getString(R.string.register_emptypssword))) {
                    return;
                }
                if (!EdiTextUtils.getInstance().CheckedPassword(register_password, getResources().getString(R.string.register_emptypssword_less))) {
                    return;
                }

                HashMap<String,String> mapregister = new HashMap<String, String>();
                mapregister.put("username",register_phoneNum.getText().toString().trim());
                mapregister.put("captcha",register_code.getText().toString().trim());
                mapregister.put("pwd", register_password.getText().toString().trim());
                mapregister.put("step",String.valueOf(1));
                HttpFactory.getInstance().Register(Activity_Register.this, mapregister);

            }
        });

        register_getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!EdiTextUtils.getInstance().CheckedEmpty(register_phoneNum, getResources().getString(R.string.register_emptyphone))) {
                    return;

                }

                timeCount.start();
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("username",register_phoneNum.getText().toString().trim());
                HttpFactory.getInstance().sendAuthCode(Activity_Register.this,map);
            }
        });
        user_greetment_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getInstance().startToAnoterActivity(Activity_Register.this, Activity_UserGreetment.class, null);
            }
        });
    }

    @Override
    public void initListener() {
        register_qqlogin.setOnClickListener(this);
        register_weixinlogin.setOnClickListener(this);
    }
    @Override
    public void initView() {
        register_btn = mainVu.getItemView(R.id.register_btn);
        register_phoneNum = mainVu.getItemView(R.id.register_phoneNum);
        register_code = mainVu.getItemView(R.id.register_code);
        register_password = mainVu.getItemView(R.id.register_password);
        register_getCode =  mainVu.getItemView(R.id.register_getCode);
        user_greetment_text =  mainVu.getItemView(R.id.user_greetment_text);
        register_weixinlogin = mainVu.getItemView(R.id.register_weixinlogin);
        register_qqlogin = mainVu.getItemView(R.id.register_qqlogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register_weixinlogin:

                //UmengUtils.getInstance().cancleLogin(Activity_Register.this,mController,SHARE_MEDIA.QQ);
               UmengUtils.getInstance().Login(Activity_Register.this,mController, SHARE_MEDIA.WEIXIN,UmengUtils.WEINXIN);
                break;

            case R.id.register_qqlogin:
              //  UmengUtils.getInstance().cancleLogin(Activity_Register.this,mController,SHARE_MEDIA.QQ);
                UmengUtils.getInstance().Login(Activity_Register.this, mController, SHARE_MEDIA.QQ,UmengUtils.QQ);

                break;

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode) ;
        if(ssoHandler != null){
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
