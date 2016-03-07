package com.zhushan.bishengwang.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.DirectorSettingData;
import com.zhushan.bishengwang.Entry.PublishPurchaseEntry;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Itools.FTPUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.Mediaplayutils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.SoundMeter;
import com.zhushan.bishengwang.Itools.VolleyTools;
import com.zhushan.bishengwang.Itools.VolleyUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;

import org.xutils.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class Activity_publishpurchaseinfo extends Basetivity implements View.OnClickListener,Mediaplayutils.RecorderSettext,FTPUtils.UploadSuccess {

    private MainVu mainVu = new MainVu();
    private boolean isPaying;
    private String recorderfilename;
    private TextView publishi_purchaseinfo_directorsetting,publishi_purchaseinfo_directorsettingtext;
    private EditText publishi_purchaseinfo_biaoti,publishi_purchaseinfo_content,publishi_purchaseinfo_monney,publishi_purchaseinfo_count,publishi_purchaseinfo_finish_time;
    private Button publishdesign_btn;
    private   Mediaplayutils mediaplayutils;
    private boolean isSuccess;
    private File fileOne,fileTwo,fileThree;
    private StringBuilder stringBuilderLle;
    private AlertDialogUtils alertDialogUtils;
    private FTPUtils ftpUtils = FTPUtils.getInstance();
    private SoundMeter soundMeter;
    private boolean isRecord;
    private ImageView publishi_purchaseinfo_recorder_txt,publishpurchaseinfo_img1,publishpurchaseinfo_img2,publishpurchaseinfo_img3,publishi_purchaseinfo_recorder,publishpuechase_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_publish_purchaseinfo));
        initView();
        initData();
        initListener();
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        publishpurchaseinfo_img1 = mainVu.getItemView(R.id.publishpurchaseinfo_img1);
        publishpurchaseinfo_img2 = mainVu.getItemView(R.id.publishpurchaseinfo_img2);
        publishpurchaseinfo_img3 = mainVu.getItemView(R.id.publishpurchaseinfo_img3);
        publishi_purchaseinfo_biaoti =  mainVu.getItemView(R.id.publishi_purchaseinfo_biaoti);
        publishi_purchaseinfo_content = mainVu.getItemView(R.id.publishi_purchaseinfo_content);
        publishi_purchaseinfo_recorder = mainVu.getItemView(R.id.publishi_purchaseinfo_recorder);
        publishi_purchaseinfo_recorder_txt = mainVu.getItemView(R.id.publishi_purchaseinfo_recorder_txt);
        publishdesign_btn = mainVu.getItemView(R.id.publishdesign_btn);
        publishpuechase_back = mainVu.getItemView(R.id.publishpuechase_back);
        publishi_purchaseinfo_directorsetting = mainVu.getItemView(R.id.publishi_purchaseinfo_directorsetting);
      /*  publishi_purchaseinfo_monney =  mainVu.getItemView(R.id.publishi_purchaseinfo_monney);
        publishi_purchaseinfo_count = mainVu.getItemView(R.id.publishi_purchaseinfo_count);
        publishi_purchaseinfo_finish_time =  mainVu.getItemView(R.id.publishi_purchaseinfo_finish_time);*/
        publishi_purchaseinfo_directorsettingtext =  mainVu.getItemView(R.id.publishi_purchaseinfo_directorsettingtext);
    }


    @Override
    public void initData() {
       /* PositionMySeft positionMySeft = new PositionMySeft();
        positionMySeft.initPosition(this, TBaplication.locationClient);
        positionMySeft.StartPosition(TBaplication.locationClient);
        positionMySeft.setGetPosition(this);*/
        soundMeter = new SoundMeter();
        alertDialogUtils = new AlertDialogUtils(this,R.layout.loading);
        mediaplayutils = new Mediaplayutils();
        BackActivity.finishActivity(Activity_publishpurchaseinfo.this, R.id.publishpuechase_back);
    }

    @Override
    public void initListener() {
        publishpurchaseinfo_img1.setOnClickListener(this);
        publishpurchaseinfo_img2.setOnClickListener(this);
        publishpurchaseinfo_img3.setOnClickListener(this);
        publishdesign_btn.setOnClickListener(this);
        publishi_purchaseinfo_recorder.setOnClickListener(this);
        publishi_purchaseinfo_directorsetting.setOnClickListener(this);
        mediaplayutils.setRecorderSettext(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
       // Mediaplayutils.stoPlayListnener(mediaPlayer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaplayutils.setRecorderSettext(this);
        FTPUtils.getInstance().setUploadSuccess(this);
        Log.i("www", "resume");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null)
        {
            return;

        }
        mediaplayutils = new Mediaplayutils();
        mediaplayutils.dialogdiss();
        CameraUtils.dialogdiss();
        mediaplayutils.setRecorderSettext(this);
        Log.i("www", "requestCode" + requestCode + "resultCode" + resultCode);
        if (requestCode==CameraUtils.CAPTURE1&&resultCode==RESULT_OK)
        {
            try {
                publishpurchaseinfo_img2.setVisibility(View.VISIBLE);
                String path = CameraUtils.getCameraBitmap(data,publishpurchaseinfo_img1);
                fileOne = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK1&&resultCode==RESULT_OK)
        {
            publishpurchaseinfo_img2.setVisibility(View.VISIBLE);
            String path = CameraUtils.getPicBitmap(data,Activity_publishpurchaseinfo.this,publishpurchaseinfo_img1);
            fileOne = new File(path);
        }

        if (requestCode==CameraUtils.CAPTURE2&&resultCode==RESULT_OK)
        {
            try {
                publishpurchaseinfo_img3.setVisibility(View.VISIBLE);
                String path = CameraUtils.getCameraBitmap(data,publishpurchaseinfo_img2);
                fileTwo = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK2&&resultCode==RESULT_OK)
        {
            publishpurchaseinfo_img3.setVisibility(View.VISIBLE);
            String path = CameraUtils.getPicBitmap(data,Activity_publishpurchaseinfo.this,publishpurchaseinfo_img2);
            fileTwo = new File(path);
        }


        if (requestCode==CameraUtils.CAPTURE3&&resultCode==RESULT_OK)
        {

            try {
               String path =  CameraUtils.getCameraBitmap(data,publishpurchaseinfo_img3);
                fileThree = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK3&&resultCode==RESULT_OK)
        {
            String path = CameraUtils.getPicBitmap(data,Activity_publishpurchaseinfo.this,publishpurchaseinfo_img3);
            fileThree = new File(path);
        }
       /* if (requestCode==Mediaplayutils.RECORDER&&resultCode==RESULT_OK)
        {

            recorderfilename =  CameraUtils.getAudioInfo(Activity_publishpurchaseinfo.this,data);
            //publishi_purchaseinfo_recorder_txt.setText(mediaplayutils.getLength(recorderfilename));
            }*/
    }

    public void onEventMainThread(StringBuilder stringBuilder)
    {
        stringBuilderLle = stringBuilder;
        publishi_purchaseinfo_directorsettingtext.setText(stringBuilder.toString().replace(","," "));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.publishi_purchaseinfo_directorsetting:
                IntentUtils.getInstance().startToAnoterActivity(Activity_publishpurchaseinfo.this,Activity_DirectorSetting.class,null);
                break;
            case R.id.publishpurchaseinfo_img1:
                CameraUtils.photograph(Activity_publishpurchaseinfo.this,CameraUtils.CAPTURE1,CameraUtils.ACTION_PICK1);
                break;

            case R.id.publishpurchaseinfo_img2:
                CameraUtils.photograph(Activity_publishpurchaseinfo.this,CameraUtils.CAPTURE2,CameraUtils.ACTION_PICK2);
                break;

            case R.id.publishpurchaseinfo_img3:
                CameraUtils.photograph(Activity_publishpurchaseinfo.this,CameraUtils.CAPTURE3,CameraUtils.ACTION_PICK3);
                break;

            case R.id.publishdesign_btn:

                if (fileOne==null&&fileTwo==null&&fileThree==null)
                {
                    Toast.makeText(Activity_publishpurchaseinfo.this,"请添加至少一张图片",Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String,String> map = new HashMap<String,String>();
                map.put("token", SharePreferenceUtils.getInstance(Activity_publishpurchaseinfo.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                map.put("cate_id", String.valueOf(2));
                Log.i("www", publishi_purchaseinfo_biaoti.getText().toString().trim());
                map.put("title", publishi_purchaseinfo_biaoti.getText().toString().trim());
                if (publishi_purchaseinfo_content.getText().toString().trim()!=null) {
                    map.put("content", publishi_purchaseinfo_content.getText().toString().trim());
                }
                if (recorderfilename!=null) {
                    map.put("voice", recorderfilename);
                }
                if(stringBuilderLle.toString().trim()!=null) {
                    map.put("label_name", stringBuilderLle.toString().trim());
                }
                map.put("lat",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
                map.put("lng",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG, null));
                map.put("area", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNADREESS, null));
                ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
                Glide.with(this).load(R.mipmap.run).into(imageView);
                alertDialogUtils.showDialog(false);

                if (fileOne!=null&&fileTwo==null&&fileThree==null)
                {
                    HttpFactory.getInstance().PublishPurchase(Activity_publishpurchaseinfo.this, map,alertDialogUtils, new Pair<String, File>("file[0]", fileOne));
                }
              if (fileOne!=null&&fileTwo!=null&&fileThree==null)
              {
                  HttpFactory.getInstance().PublishPurchase(Activity_publishpurchaseinfo.this, map,alertDialogUtils, new Pair<String, File>("file[0]", fileOne), new Pair<String, File>("file[1]", fileTwo));
              }

                if (fileOne!=null&&fileTwo!=null&&fileThree!=null)
                {
                    HttpFactory.getInstance().PublishPurchase(Activity_publishpurchaseinfo.this, map,alertDialogUtils, new Pair<String, File>("file[0]", fileOne), new Pair<String, File>("file[1]", fileTwo), new Pair<String, File>("file[2]", fileThree));
                }

                if (recorderfilename!=null) {
                    ftpUtils.setUploadSuccess(this);
                    ftpUtils.uploadSingleFile(new File(android.os.Environment.getExternalStorageDirectory() + "/" + recorderfilename), "Voice");
                    // ftpUtils.setUploadSuccess(this);
                }

                break;
            case R.id.publishi_purchaseinfo_recorder:
            // mediaplayutils.startRecorder(Activity_publishpurchaseinfo.this,Mediaplayutils.RECORDER,recorderfilename);
                if (!isRecord) {
                    isRecord  = !isRecord;
                    recorderfilename = soundMeter.start();
                    Toast.makeText(Activity_publishpurchaseinfo.this, "正在录音，再按一次停止录音", Toast.LENGTH_SHORT).show();
                    publishi_purchaseinfo_recorder.setImageResource(R.mipmap.voice);
                    publishi_purchaseinfo_recorder_txt.setVisibility(View.VISIBLE);
                }else{
                    isRecord  = !isRecord;
                    soundMeter.stop();
                    Toast.makeText(Activity_publishpurchaseinfo.this, "停止录音", Toast.LENGTH_SHORT).show();
                    publishi_purchaseinfo_recorder_txt.setVisibility(View.INVISIBLE);
                    publishi_purchaseinfo_recorder.setImageResource(R.mipmap.icon_voice);
                }
                break;

        }
    }

    @Override
    public void setText(String textview,String filename) {
        recorderfilename = filename;
        ((TextView)mainVu.getItemView(R.id.publishi_purchaseinfo_recorder_txt)).setText(textview);
    }

    @Override
    public void success() {
        Log.i("www", "上传成功");

    }
/*
    @Override
    public void getposition(double lat, double lng) {

    }*/
}
