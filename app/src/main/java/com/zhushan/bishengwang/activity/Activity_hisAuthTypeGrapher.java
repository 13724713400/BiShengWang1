package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.AuthEntry;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class Activity_hisAuthTypeGrapher extends Basetivity {

    private MainVu mainVu = new MainVu();
    private LinearLayout his_typagrapher_ll;
    private TextView his_authentication_titles,his_typagrapher_zhiwei,his_typagrapher_address,his_typagrapher_gongsiqunacheng,his_typagrapher_jianjie,his_typagrapher_qiyetupain,his_typagrapher_quhao,his_typagrapher_text;
    private ImageView his_typagrapher_img3,his_typagrapher_img4,his_typagrapher_img5,his_typagrapher_yingyezhizhao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.his_auth_ytpegrapher));
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initView() {

        his_typagrapher_address = mainVu.getItemView(R.id.his_typagrapher_address);
        his_typagrapher_gongsiqunacheng  = mainVu.getItemView(R.id.his_typagrapher_gongsiqunacheng);
        his_typagrapher_img3 =  mainVu.getItemView(R.id.his_typagrapher_img3);
        his_typagrapher_img4 =  mainVu.getItemView(R.id.his_typagrapher_img4);
        his_typagrapher_img5 =  mainVu.getItemView(R.id.his_typagrapher_img5);
        his_typagrapher_jianjie =  mainVu.getItemView(R.id.his_typagrapher_jianjie);
        his_typagrapher_ll =  mainVu.getItemView(R.id.his_typagrapher_ll);
        his_typagrapher_qiyetupain =  mainVu.getItemView(R.id.his_typagrapher_qiyetupain);
        his_typagrapher_quhao =  mainVu.getItemView(R.id.his_typagrapher_quhao);
        his_typagrapher_text =  mainVu.getItemView(R.id.his_typagrapher_text);
        his_typagrapher_yingyezhizhao =  mainVu.getItemView(R.id.his_typagrapher_yingyezhizhao);
        his_authentication_titles =  mainVu.getItemView(R.id.his_authentication_titles);
        EventBus.getDefault().register(this);

    }

    @Override
    public void initData() {
        BackActivity.finishActivity(this,R.id.his_typegrapher_back);
        HashMap<String, String> map = new HashMap<String, String>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("user_id")!=null) {
                his_authentication_titles.setText(getResources().getString(R.string.taderenzhengziliao));
                String user_id = bundle.getString("user_id");
                map.put("user_id", user_id);
                HttpFactory.getInstance().Hiscustomeauth(this, map);
            }
            if (bundle.getString("flag")!=null)
            {  his_authentication_titles.setText(getResources().getString(R.string.woderenzhengziliao));
                map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                HttpFactory.getInstance().Mycustomeauth(this, map);
            }


        }
    }
    public void onEventMainThread(AuthEntry authEntry)
    {
        Log.i("www","印刷商认证"+authEntry);
        mainVu.setText(his_typagrapher_gongsiqunacheng,getResources().getString(R.string.certificatedinfomation_gongsi)+" : "+authEntry.getData().getCustomer_data().getCompany_name())
                .setText(his_typagrapher_address,getResources().getString(R.string.certificatedinfomation_gongsidizhi)+" : " +authEntry.getData().getCustomer_data().getCompany_area())
                .setText(his_typagrapher_jianjie,"公司简介 :"+authEntry.getData().getCustomer_data().getCompany_description())
                .setText(his_typagrapher_quhao, "公司区号 :"+authEntry.getData().getCustomer_data().getCompany_telephone());
            if (!authEntry.getData().getCustomer_data().getImg_thumb().equals("")) {
                his_typagrapher_text.setVisibility(View.VISIBLE);
                mainVu.setImageUrl(his_typagrapher_yingyezhizhao, this, HttpConstance.URL + authEntry.getData().getCustomer_data().getImg_thumb());
            }
            switch (authEntry.getData().getCustomer_data().getCustomer_gallery().size())
            {
                case 1:
                    mainVu.getItemView(R.id.his_typagrapher_qiyetupain).setVisibility(View.VISIBLE);
                    mainVu.getItemView(R.id.his_typagrapher_img3).setVisibility(View.VISIBLE);

                    mainVu.setImageUrl(his_typagrapher_img3, this, HttpConstance.URL + authEntry.getData().getCustomer_data().getCustomer_gallery().get(0).getImg_thumb());

                    break;
                case 2:
                    mainVu.getItemView(R.id.his_typagrapher_qiyetupain).setVisibility(View.VISIBLE);
                    mainVu.getItemView(R.id.his_typagrapher_img3).setVisibility(View.VISIBLE);
                    mainVu.getItemView(R.id.his_typagrapher_img4).setVisibility(View.VISIBLE);

                    mainVu.setImageUrl(his_typagrapher_img3, this, HttpConstance.URL + authEntry.getData().getCustomer_data().getCustomer_gallery().get(0).getImg_thumb())
                            .setImageUrl(his_typagrapher_img4,this,HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(1).getImg_thumb());

                    break;
                case 3:
                    mainVu.getItemView(R.id.his_typagrapher_qiyetupain).setVisibility(View.VISIBLE);
                    mainVu.getItemView(R.id.his_typagrapher_img3).setVisibility(View.VISIBLE);
                    mainVu.getItemView(R.id.his_typagrapher_img4).setVisibility(View.VISIBLE);
                    mainVu.getItemView(R.id.his_typagrapher_img5).setVisibility(View.VISIBLE);
                    mainVu.setImageUrl(his_typagrapher_img3, this, HttpConstance.URL + authEntry.getData().getCustomer_data().getCustomer_gallery().get(0).getImg_thumb())
                        .setImageUrl(his_typagrapher_img4,this,HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(1).getImg_thumb())
                        .setImageUrl(his_typagrapher_img5,this,HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(2).getImg_thumb());
                    break;

            }






    }


    @Override
    public void initListener() {

    }
}
