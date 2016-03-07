package com.zhushan.bishengwang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhushan.bishengwang.Constance.XioamishuConstance;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.io.FileNotFoundException;

public class Activity_SelftMytip extends Basetivity implements View.OnClickListener{

  private MainVu mainVu = new MainVu();
    private ImageView selftMytip_img1,selftMytip_img2,selftMytip_img3,selftMytip_img4;
    private Button selftMytip_sure;
    private String fileOne,fileTwo,fileThree,fileFore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_activity__selft_mytip));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {

        selftMytip_img1 = mainVu.getItemView(R.id.selftMytip_img1);
        selftMytip_img2 = mainVu.getItemView(R.id.selftMytip_img2);
        selftMytip_img3 = mainVu.getItemView(R.id.selftMytip_img3);
        selftMytip_img4 =  mainVu.getItemView(R.id.selftMytip_img4);
        selftMytip_sure = mainVu.getItemView(R.id.selftMytip_sure);
}

    @Override
    public void initData() {
        BackActivity.finishActivity(this,R.id.selftMytip_back);
    }

    @Override
    public void initListener() {
        selftMytip_img1.setOnClickListener(this);
        selftMytip_img2.setOnClickListener(this);
        selftMytip_img3.setOnClickListener(this);
        selftMytip_img4.setOnClickListener(this);
        selftMytip_sure.setOnClickListener(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        CameraUtils.dialogdiss();
        if (requestCode == CameraUtils.CAPTURE1 && resultCode == RESULT_OK) {

            try {
                fileOne =  CameraUtils.getCameraBitmap(data, selftMytip_img1);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CameraUtils.ACTION_PICK1 && resultCode == RESULT_OK) {

            fileOne =  CameraUtils.getPicBitmap(data,
                    Activity_SelftMytip.this, selftMytip_img1);

        }

        if (requestCode == CameraUtils.CAPTURE2 && resultCode == RESULT_OK) {

            try {
               fileTwo =  CameraUtils.getCameraBitmap(data, selftMytip_img2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CameraUtils.ACTION_PICK2 && resultCode == RESULT_OK) {
            fileTwo =  CameraUtils.getPicBitmap(data, Activity_SelftMytip.this, selftMytip_img2);

        }

        if (requestCode == CameraUtils.CAPTURE3 && resultCode == RESULT_OK) {

            try {
             fileThree =    CameraUtils.getCameraBitmap(data, selftMytip_img3);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CameraUtils.ACTION_PICK3 && resultCode == RESULT_OK) {
          fileThree =   CameraUtils.getPicBitmap(data, Activity_SelftMytip.this, selftMytip_img3);

        }


        if (requestCode == CameraUtils.CAPTURE4 && resultCode == RESULT_OK) {

            try {
             fileFore =    CameraUtils.getCameraBitmap(data, selftMytip_img4);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CameraUtils.ACTION_PICK4 && resultCode == RESULT_OK) {
            fileFore =   CameraUtils.getPicBitmap(data, Activity_SelftMytip.this, selftMytip_img4);

        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.selftMytip_img1:
                CameraUtils.photograph(Activity_SelftMytip.this,CameraUtils.CAPTURE1,CameraUtils.ACTION_PICK1);
                break;

            case R.id.selftMytip_img2:
                CameraUtils.photograph(Activity_SelftMytip.this,CameraUtils.CAPTURE2,CameraUtils.ACTION_PICK2);
                break;

            case R.id.selftMytip_img3:
                CameraUtils.photograph(Activity_SelftMytip.this,CameraUtils.CAPTURE3,CameraUtils.ACTION_PICK3);
                break;

            case R.id.selftMytip_img4:
                CameraUtils.photograph(Activity_SelftMytip.this,CameraUtils.CAPTURE4,CameraUtils.ACTION_PICK4);
                break;

            case R.id.selftMytip_sure:

                if (fileOne==null)
                {
                    Toast.makeText(Activity_SelftMytip.this,"请添加图片",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (fileTwo==null)
                {
                    Toast.makeText(Activity_SelftMytip.this,"请添加图片",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (fileThree==null)
                {
                    Toast.makeText(Activity_SelftMytip.this,"请添加图片",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (fileFore==null)
                {
                    Toast.makeText(Activity_SelftMytip.this,"请添加图片",Toast.LENGTH_SHORT).show();
                    return;
                }

                SharePreferenceUtils.getInstance(Activity_SelftMytip.this, XioamishuConstance.XIOAMISHUTB).CreateSharePreference(XioamishuConstance.ISUSED,true);
                SharePreferenceUtils.getInstance(Activity_SelftMytip.this, XioamishuConstance.XIOAMISHUTB).CreateSharePreference(XioamishuConstance.XIAOMISHUIMGONE,fileOne);
                SharePreferenceUtils.getInstance(Activity_SelftMytip.this, XioamishuConstance.XIOAMISHUTB).CreateSharePreference(XioamishuConstance.XIAOMISHUIMTWO,fileTwo);
                SharePreferenceUtils.getInstance(Activity_SelftMytip.this, XioamishuConstance.XIOAMISHUTB).CreateSharePreference(XioamishuConstance.XIAOMISHUIMGTHREE,fileThree);
                SharePreferenceUtils.getInstance(Activity_SelftMytip.this, XioamishuConstance.XIOAMISHUTB).CreateSharePreference(XioamishuConstance.XIAOMISHUIMFORE,fileFore);



                break;

        }
    }
}
