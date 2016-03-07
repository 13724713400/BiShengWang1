package com.zhushan.bishengwang.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Itools.FTPUtils;
import com.zhushan.bishengwang.Itools.FTPUtils.UploadSuccess;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.Mediaplayutils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.SoundMeter;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class Activity_publishisuply extends Basetivity implements View.OnClickListener,Mediaplayutils.RecorderSettext,UploadSuccess {
    private Mediaplayutils mediaplayutils;
    private MainVu mainVu = new MainVu();
    private ImageView publishi_suplyinfo_recorder_txt,publishsuplyinfo_img1,publishsuplyinfo_img2,publishsuplyinfo_img3,publishsuply_back,publishi_suplyinfo_recorder;
    private EditText publishi_suplyinfo_biaoti,publishi_suplyinfo_content;
    private Button publishsuply_btn;
    private TextView publishsuplyinfo_directorsetting,publishi_suplyinfo_directorsettingtext;
    private String recorderfilename;
    private File fileOne,fileTwo,fileThree;
    private StringBuilder stringBuilderLle;
    private AlertDialogUtils alertDialogUtils;
    private boolean isRecord;
    private SoundMeter soundMeter;
    private FTPUtils ftpUtils = FTPUtils.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_activity_publishisuply));
        initView();
        initData();
        initListener();
    }
    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        publishsuply_back = mainVu.getItemView(R.id.publishsuply_back);
        publishsuplyinfo_img1 = mainVu.getItemView(R.id.publishsuplyinfo_img1);
        publishsuplyinfo_img2 = mainVu.getItemView(R.id.publishsuplyinfo_img2);
        publishsuplyinfo_img3 =  mainVu.getItemView(R.id.publishsuplyinfo_img3);
        publishi_suplyinfo_biaoti = mainVu.getItemView(R.id.publishi_suplyinfo_biaoti);
        publishi_suplyinfo_content =  mainVu.getItemView(R.id.publishi_suplyinfo_content);
        publishi_suplyinfo_recorder =  mainVu.getItemView(R.id.publishi_suplyinfo_recorder);
        publishi_suplyinfo_recorder_txt =  mainVu.getItemView(R.id.publishi_suplyinfo_recorder_txt);
        publishsuply_btn =  mainVu.getItemView(R.id.publishsuply_btn);
        publishsuplyinfo_directorsetting =  mainVu.getItemView(R.id.publishsuplyinfo_directorsetting);
        publishi_suplyinfo_directorsettingtext =  mainVu.getItemView(R.id.publishi_suplyinfo_directorsettingtext);
    }

    @Override
    public void initData() {
        soundMeter = new SoundMeter();
      /*  PositionMySeft positionMySeft = new PositionMySeft();
        positionMySeft.initPosition(this, TBaplication.locationClient);
        positionMySeft.StartPosition(TBaplication.locationClient);
        positionMySeft.setGetPosition(this);*/
        alertDialogUtils = new AlertDialogUtils(this,R.layout.loading);
        mediaplayutils = new Mediaplayutils();
        BackActivity.finishActivity(Activity_publishisuply.this, R.id.publishsuply_back);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initListener() {
        publishsuplyinfo_img1.setOnClickListener(this);
        publishsuplyinfo_img2.setOnClickListener(this);
        publishsuplyinfo_img3.setOnClickListener(this);
        publishi_suplyinfo_recorder.setOnClickListener(this);
        publishsuply_btn.setOnClickListener(this);
        publishsuplyinfo_directorsetting.setOnClickListener(this);
        mediaplayutils.setRecorderSettext(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null)
        {
            return;

        }
        mediaplayutils = new Mediaplayutils();
        CameraUtils.dialogdiss();
        mediaplayutils.dialogdiss();

        Log.i("www", "requestCode" + requestCode + "resultCode" + resultCode);
        if (requestCode==CameraUtils.CAPTURE1&&resultCode==RESULT_OK)
        {
            try {
                publishsuplyinfo_img2.setVisibility(View.VISIBLE);
               String path =  CameraUtils.getCameraBitmap(data,publishsuplyinfo_img1);
                fileOne = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK1&&resultCode==RESULT_OK)
        {
            publishsuplyinfo_img2.setVisibility(View.VISIBLE);
           String path =  CameraUtils.getPicBitmap(data,Activity_publishisuply.this,publishsuplyinfo_img1);
            fileOne = new File(path);
        }

        if (requestCode==CameraUtils.CAPTURE2&&resultCode==RESULT_OK)
        {
            try {
                publishsuplyinfo_img3.setVisibility(View.VISIBLE);
               String path =  CameraUtils.getCameraBitmap(data,publishsuplyinfo_img2);
                fileTwo = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK2&&resultCode==RESULT_OK)
        {
            publishsuplyinfo_img3.setVisibility(View.VISIBLE);
           String path =  CameraUtils.getPicBitmap(data,Activity_publishisuply.this,publishsuplyinfo_img2);
            fileTwo = new File(path);
        }


        if (requestCode==CameraUtils.CAPTURE3&&resultCode==RESULT_OK)
        {
            try {
               String path =  CameraUtils.getCameraBitmap(data,publishsuplyinfo_img3);
               fileThree = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK3&&resultCode==RESULT_OK)
        {
           String path =  CameraUtils.getPicBitmap(data,Activity_publishisuply.this,publishsuplyinfo_img3);
            fileThree = new File(path);
        }
      /*  if (requestCode==Mediaplayutils.RECORDER&&resultCode==RESULT_OK)
        {

            recorderfilename =  CameraUtils.getAudioInfo(Activity_publishisuply.this,data);
            publishi_suplyinfo_recorder_txt.setText(mediaplayutils.getLength(recorderfilename));
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaplayutils.setRecorderSettext(this);
        ftpUtils.setUploadSuccess(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.publishsuplyinfo_directorsetting:
                IntentUtils.getInstance().startToAnoterActivity(Activity_publishisuply.this,Activity_DirectorSetting.class,null);
                break;
            case R.id.publishsuplyinfo_img1:
                CameraUtils.photograph(Activity_publishisuply.this,CameraUtils.CAPTURE1,CameraUtils.ACTION_PICK1);
                break;

            case R.id.publishsuplyinfo_img2:
                CameraUtils.photograph(Activity_publishisuply.this,CameraUtils.CAPTURE2,CameraUtils.ACTION_PICK2);
                break;

            case R.id.publishsuplyinfo_img3:
                CameraUtils.photograph(Activity_publishisuply.this,CameraUtils.CAPTURE3,CameraUtils.ACTION_PICK3);
                break;

            case R.id.publishsuply_btn:

                if(!EdiTextUtils.getInstance().CheckedEmpty(publishi_suplyinfo_biaoti,getResources().getString(R.string.pulishipurchase_empty_biaoty)))
                {
                    return;
                }

               if (fileOne==null&&fileTwo==null&&fileThree!=null)
                {
                    Toast.makeText(Activity_publishisuply.this, "请添加至少一张图片", Toast.LENGTH_SHORT).show();
                    return;
                }


                HashMap<String,String> map = new HashMap<String,String>();
                String token =  SharePreferenceUtils.getInstance(Activity_publishisuply.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
                if (token!=null)
                {
                    map.put("token", SharePreferenceUtils.getInstance(Activity_publishisuply.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                }
                map.put("cate_id", String.valueOf(1));
                map.put("title", publishi_suplyinfo_biaoti.getText().toString().trim());
                if (publishi_suplyinfo_content.getText().toString().trim()!=null) {
                    map.put("content", publishi_suplyinfo_content.getText().toString().trim());
                }
                if (recorderfilename!=null) {
                    map.put("voice", recorderfilename);
                }
                map.put("lat",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
                map.put("lng",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG,null));
          if (stringBuilderLle.toString().trim()!=null) {
              map.put("label_name", stringBuilderLle.toString().trim());
          }
                map.put("area", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNADREESS, null));
                Log.i("www", fileOne + "===" + fileTwo + "---" + fileThree);
                ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
                Glide.with(this).load(R.mipmap.run).into(imageView);
                alertDialogUtils.showDialog(false);
//                HttpFactory.getInstance().PublishSuply(this,map,alertDialogUtils,new Pair<String,File>("file[0]",fileOne),new Pair<String,File>("file[1]",fileTwo),new Pair<String,File>("file[2]",fileThree));

                if (fileOne!=null&&fileTwo==null&&fileThree==null)
                {
                    HttpFactory.getInstance().PublishSuply(Activity_publishisuply.this,map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne));
                }
                if (fileOne!=null&&fileTwo!=null&&fileThree==null)
                {
                    HttpFactory.getInstance().PublishSuply(Activity_publishisuply.this, map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne), new Pair<String, File>("file[1]", fileTwo));
                }

                if (fileOne!=null&&fileTwo!=null&&fileThree!=null)
                {
                    HttpFactory.getInstance().PublishSuply(Activity_publishisuply.this, map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne), new Pair<String, File>("file[1]", fileTwo), new Pair<String, File>("file[2]", fileThree));
                }


                if (recorderfilename!=null) {
                    ftpUtils.setUploadSuccess(this);
                    ftpUtils.uploadSingleFile(new File(android.os.Environment.getExternalStorageDirectory() + "/" + recorderfilename), "Voice");
                }

                break;
            case R.id.publishi_suplyinfo_recorder:

                if (!isRecord) {
                    isRecord  = !isRecord;
                    recorderfilename = soundMeter.start();
                    Toast.makeText(Activity_publishisuply.this, "正在录音，再按一次停止录音", Toast.LENGTH_SHORT).show();
                    publishi_suplyinfo_recorder.setImageResource(R.mipmap.voice);
                    publishi_suplyinfo_recorder_txt.setVisibility(View.VISIBLE);
                }else{
                    isRecord  = !isRecord;
                    soundMeter.stop();
                    Toast.makeText(Activity_publishisuply.this, "停止录音", Toast.LENGTH_SHORT).show();
                    publishi_suplyinfo_recorder_txt.setVisibility(View.INVISIBLE);
                    publishi_suplyinfo_recorder.setImageResource(R.mipmap.icon_voice);
                }
                //mediaplayutils.startRecorder(Activity_publishisuply.this,Mediaplayutils.RECORDER,recorderfilename);

                break;
        }
    }

    @Override
    public void setText(String textview,String filename) {
        recorderfilename = filename;
        ((TextView)mainVu.getItemView(R.id.publishi_suplyinfo_recorder_txt)).setText(textview);
    }

    public void onEventMainThread(StringBuilder stringBuilder)
    {
        Log.i("www","suply"+stringBuilder.toString());
        stringBuilderLle = stringBuilder;
        publishi_suplyinfo_directorsettingtext.setText(stringBuilder.toString().replace(",", " "));
    }


    @Override
    public void success() {



    }

   /* @Override
    public void getposition(double lat, double lng) {

    }*/
}
