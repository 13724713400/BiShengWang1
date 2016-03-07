package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

public class Activity_HisAuthPeaple extends Basetivity {

    private MainVu mainVu = new MainVu();
    private ImageView his_authentication_img1,his_authentication_img2;
    private TextView his_antentication_titles,his_authentication_address,his_authentication_gongsiqunacheng,his_authentication_zhiwei,his_authentication_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_activity__his_auth_peaple));
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
        his_authentication_img1 =  mainVu.getItemView(R.id.his_authentication_img1);
        his_authentication_img2 =  mainVu.getItemView(R.id.his_authentication_img2);
        his_authentication_address =  mainVu.getItemView(R.id.his_authentication_address);
        his_authentication_gongsiqunacheng =  mainVu.getItemView(R.id.his_authentication_gongsiqunacheng);
        his_authentication_zhiwei =  mainVu.getItemView(R.id.his_authentication_zhiwei);
        his_authentication_status =  mainVu.getItemView(R.id.his_authentication_status);
        his_antentication_titles =  mainVu.getItemView(R.id.his_antentication_titles);
        EventBus.getDefault().register(this);

    }

    @Override
    public void initData() {
        BackActivity.finishActivity(this,R.id.his_antentication_back);
        HashMap<String, String> map = new HashMap<String, String>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("user_id")!=null) {
                his_antentication_titles.setText(getResources().getString(R.string.taderenzhengziliao));
                String user_id = bundle.getString("user_id");
                map.put("user_id", user_id);
                HttpFactory.getInstance().Hiscustomeauth(this, map);
            }
            if (bundle.getString("flag")!=null)
            {   his_antentication_titles.setText(getResources().getString(R.string.woderenzhengziliao));
                map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                HttpFactory.getInstance().Mycustomeauth(this, map);
            }

        }
    }


    public void onEventMainThread(AuthEntry authEntry)
    {
        Log.i("www","his"+authEntry);

            switch (Integer.parseInt(authEntry.getData().getPeople_data().getStatus())) {

                case 1:

                    if (!authEntry.getData().getPeople_data().getImg_head_thumb().equals("")) {
                        mainVu.getItemView(R.id.his_authentication_img1).setVisibility(View.VISIBLE);
                        mainVu.setImageUrl(his_authentication_img1, this, HttpConstance.URL + authEntry.getData().getPeople_data().getImg_head_thumb());
                    }
                    if (!authEntry.getData().getPeople_data().getImg_back_thumb().equals("")) {
                        mainVu.getItemView(R.id.his_authentication_img2).setVisibility(View.VISIBLE);
                        mainVu.setImageUrl(his_authentication_img2, this, HttpConstance.URL + authEntry.getData().getPeople_data().getImg_back_thumb());
                    }
                    mainVu.setText(his_authentication_zhiwei, getResources().getString(R.string.certificatedinfomation_zhiwei)+" : "+authEntry.getData().getPeople_data().getRule())
                            .setText(his_authentication_gongsiqunacheng, getResources().getString(R.string.certificatedinfomation_gongsi)+" : "+authEntry.getData().getPeople_data().getCompany_name())
                            .setText(his_authentication_address, getResources().getString(R.string.certificatedinfomation_gongsidizhi)+" : " + authEntry.getData().getPeople_data().getCompany_area());

                    break;

                case 2:

                    if (!authEntry.getData().getPeople_data().getImg_head_thumb().equals("")) {
                        mainVu.getItemView(R.id.his_authentication_img1).setVisibility(View.VISIBLE);
                        mainVu.setImageUrl(his_authentication_img1, this, HttpConstance.URL + authEntry.getData().getPeople_data().getImg_head_thumb());
                    }
                    if (!authEntry.getData().getPeople_data().getImg_back_thumb().equals("")) {
                        mainVu.getItemView(R.id.his_authentication_img2).setVisibility(View.VISIBLE);
                        mainVu.setImageUrl(his_authentication_img2, this, HttpConstance.URL + authEntry.getData().getPeople_data().getImg_back_thumb());
                    }
                    mainVu.setText(his_authentication_zhiwei, getResources().getString(R.string.certificatedinfomation_zhiwei)+" : "+authEntry.getData().getPeople_data().getRule())
                            .setText(his_authentication_gongsiqunacheng, getResources().getString(R.string.certificatedinfomation_gongsi)+" : "+authEntry.getData().getPeople_data().getCompany_name())
                            .setText(his_authentication_address, getResources().getString(R.string.certificatedinfomation_gongsidizhi)+" : " + authEntry.getData().getPeople_data().getCompany_area());

                    break;


                case 3:
                    if (!authEntry.getData().getPeople_data().getImg_head_thumb().equals("")) {
                        mainVu.getItemView(R.id.his_authentication_img1).setVisibility(View.VISIBLE);
                        mainVu.setImageUrl(his_authentication_img1, this, HttpConstance.URL + authEntry.getData().getPeople_data().getImg_head_thumb());
                    }
                    if (!authEntry.getData().getPeople_data().getImg_back_thumb().equals("")) {
                        mainVu.getItemView(R.id.his_authentication_img2).setVisibility(View.VISIBLE);
                        mainVu.setImageUrl(his_authentication_img2, this, HttpConstance.URL + authEntry.getData().getPeople_data().getImg_back_thumb());
                    }
                    mainVu.setText(his_authentication_zhiwei, getResources().getString(R.string.certificatedinfomation_zhiwei)+" : "+authEntry.getData().getPeople_data().getRule())
                            .setText(his_authentication_gongsiqunacheng, getResources().getString(R.string.certificatedinfomation_gongsi)+" : "+authEntry.getData().getPeople_data().getCompany_name())
                            .setText(his_authentication_address, getResources().getString(R.string.certificatedinfomation_gongsidizhi)+" : " + authEntry.getData().getPeople_data().getCompany_area());
                    break;


            }

        }



    @Override
    public void initListener() {

    }
}
