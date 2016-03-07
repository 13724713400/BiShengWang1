package com.zhushan.bishengwang.activity;

import android.content.Intent;
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
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.Mediaplayutils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.PositionMySeft.getPosition;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.SoundMeter;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class Activity_publishinviteinfo extends Basetivity implements View.OnClickListener,Mediaplayutils.RecorderSettext,FTPUtils.UploadSuccess{

    private Mediaplayutils mediaplayutils;
    private ImageView publish_invite_recorder_txt,publish_invite_back,publish_invite_imag1,publish_invite_imag2,publish_invite_imag3,publish_invite_recorder;
    private Button publish_invite_send;
    private String recorderfilename;
    private TextView publish_invite_directorsetting,publishi_invite_directorsettingtext;
    private EditText publish_invite_gangweizhize,publish_invite_renzhizige,publish_invite_zhiwei;
    private MainVu mainVu = new MainVu();
    private File fileOne,fileTwo,fileThree;
    private FTPUtils ftpUtils = FTPUtils.getInstance();
    private StringBuilder stringBuilderLle;
    private AlertDialogUtils alertDialogUtils;
    private boolean isRecord;
    private SoundMeter soundMeter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_publish_inviteinfo));
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
        EventBus.getDefault().register(this);
        publish_invite_back =  mainVu.getItemView(R.id.publish_invite_back);
        publish_invite_gangweizhize = mainVu.getItemView(R.id.publish_invite_gangweizhize);
        publish_invite_imag1 = mainVu.getItemView(R.id.publish_invite_imag1);
        publish_invite_imag2 = mainVu.getItemView(R.id.publish_invite_imag2);
        publish_invite_imag3 = mainVu.getItemView(R.id.publish_invite_imag3);
        publish_invite_renzhizige =  mainVu.getItemView(R.id.publish_invite_renzhizige);
        publish_invite_recorder = mainVu.getItemView(R.id.publish_invite_recorder);
        publish_invite_recorder_txt =  mainVu.getItemView(R.id.publish_invite_recorder_txt);
        publish_invite_zhiwei = mainVu.getItemView(R.id.publish_invite_zhiwei);
        publish_invite_send = mainVu.getItemView(R.id.publish_invite_send);
        publish_invite_directorsetting =  mainVu.getItemView(R.id.publish_invite_directorsetting);
        publishi_invite_directorsettingtext =  mainVu.getItemView(R.id.publishi_invite_directorsettingtext);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data==null)
        {
            return;

        }
        mediaplayutils = new Mediaplayutils();
        //Log.i("www", "图片路径" + uri);
        CameraUtils.dialogdiss();
        mediaplayutils.dialogdiss();
        Log.i("www", "requestCode" + requestCode + "resultCode" + resultCode);
        if (requestCode==CameraUtils.CAPTURE1&&resultCode==RESULT_OK)
        {
            try {
                publish_invite_imag2.setVisibility(View.VISIBLE);
               String path =  CameraUtils.getCameraBitmap(data,publish_invite_imag1);
                fileOne = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK1&&resultCode==RESULT_OK)
        {
            publish_invite_imag2.setVisibility(View.VISIBLE);
            String path = CameraUtils.getPicBitmap(data,Activity_publishinviteinfo.this,publish_invite_imag1);
            fileOne = new File(path);
        }

        if (requestCode==CameraUtils.CAPTURE2&&resultCode==RESULT_OK)
        {
            try {
                publish_invite_imag3.setVisibility(View.VISIBLE);
               String path =  CameraUtils.getCameraBitmap(data,publish_invite_imag2);
                fileTwo = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK2&&resultCode==RESULT_OK)
        {
            publish_invite_imag3.setVisibility(View.VISIBLE);
           String path =  CameraUtils.getPicBitmap(data,Activity_publishinviteinfo.this,publish_invite_imag2);
            fileTwo = new File(path);
        }


        if (requestCode==CameraUtils.CAPTURE3&&resultCode==RESULT_OK)
        {
            try {
               String path =  CameraUtils.getCameraBitmap(data,publish_invite_imag3);
                fileThree = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK3&&resultCode==RESULT_OK)
        {
           String path =  CameraUtils.getPicBitmap(data,Activity_publishinviteinfo.this,publish_invite_imag3);
            fileThree  = new File(path);
        }
      /*  if (requestCode==Mediaplayutils.RECORDER&&resultCode==RESULT_OK)
        {

            recorderfilename =  CameraUtils.getAudioInfo(Activity_publishinviteinfo.this,data);
            publish_invite_recorder_txt.setText(mediaplayutils.getLength(recorderfilename));
        }*/


    }

    @Override
    public void initData() {
      /*  PositionMySeft positionMySeft = new PositionMySeft();
        positionMySeft.initPosition(this, TBaplication.locationClient);
        positionMySeft.StartPosition(TBaplication.locationClient);
        positionMySeft.setGetPosition(this);*/
        soundMeter = new SoundMeter();
        alertDialogUtils = new AlertDialogUtils(this,R.layout.loading);
        mediaplayutils = new Mediaplayutils();
        BackActivity.finishActivity(Activity_publishinviteinfo.this, R.id.publish_invite_back);
    }

    @Override
    public void initListener() {
        publish_invite_imag1.setOnClickListener(this);
        publish_invite_imag2.setOnClickListener(this);
        publish_invite_imag3.setOnClickListener(this);
        publish_invite_recorder.setOnClickListener(this);
        publish_invite_send.setOnClickListener(this);
        publish_invite_directorsetting.setOnClickListener(this);
        mediaplayutils.setRecorderSettext(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.publish_invite_directorsetting:

                IntentUtils.getInstance().startToAnoterActivity(Activity_publishinviteinfo.this,Activity_DirectorSetting.class,null);

                break;

            case R.id.publish_invite_imag1:

                CameraUtils.photograph(Activity_publishinviteinfo.this,CameraUtils.CAPTURE1,CameraUtils.ACTION_PICK1);

                break;

            case R.id.publish_invite_imag2:
                CameraUtils.photograph(Activity_publishinviteinfo.this,CameraUtils.CAPTURE2,CameraUtils.ACTION_PICK2);

                break;

            case R.id.publish_invite_imag3:

                CameraUtils.photograph(Activity_publishinviteinfo.this,CameraUtils.CAPTURE3,CameraUtils.ACTION_PICK3);

                break;

            case R.id.publish_invite_recorder:

                if (!isRecord) {
                    isRecord  = !isRecord;
                    recorderfilename = soundMeter.start();
                    Toast.makeText(Activity_publishinviteinfo.this, "正在录音，再按一次停止录音", Toast.LENGTH_SHORT).show();
                    publish_invite_recorder.setImageResource(R.mipmap.voice);
                    publish_invite_recorder_txt.setVisibility(View.VISIBLE);
                }else{
                    isRecord  = !isRecord;
                    soundMeter.stop();
                    Toast.makeText(Activity_publishinviteinfo.this, "停止录音", Toast.LENGTH_SHORT).show();
                    publish_invite_recorder_txt.setVisibility(View.INVISIBLE);
                    publish_invite_recorder.setImageResource(R.mipmap.icon_voice);
                }
             //   mediaplayutils.startRecorder(Activity_publishinviteinfo.this,Mediaplayutils.RECORDER,recorderfilename);
                break;

            case R.id.publish_invite_send:

                if(!EdiTextUtils.getInstance().CheckedEmpty(publish_invite_zhiwei,getResources().getString(R.string.publishiinvite_empty_zhiwei)))
                {
                    return;
                }

                if(!EdiTextUtils.getInstance().CheckedEmpty(publish_invite_gangweizhize,getResources().getString(R.string.publishiinvite_empty_gangwei)))
                {
                    return;
                }

                if(!EdiTextUtils.getInstance().CheckedEmpty(publish_invite_renzhizige,getResources().getString(R.string.publishiinvite_empty_renzhizhige)))
                {
                    return;
                }

                if (fileOne==null&&fileTwo==null&&fileThree==null)
                {
                    Toast.makeText(Activity_publishinviteinfo.this, "请添加图片", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String,String> map = new HashMap<String,String>();
                map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                map.put("cate_id",String.valueOf(4));
                map.put("lat",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
                map.put("lng",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG, null));
                map.put("title", publish_invite_zhiwei.getText().toString().trim());
                if ( publish_invite_gangweizhize.getText().toString().trim()!=null&&publish_invite_renzhizige.getText().toString().trim()!=null) {
                    map.put("content", getResources().getString(R.string.publishi_inviteinfo_gangweizhize) + publish_invite_gangweizhize.getText().toString().trim() + "," + getResources().getString(R.string.publishi_inviteinfo_renzzhige) + publish_invite_renzhizige.getText().toString().trim());
                }
                if (recorderfilename!=null) {
                    map.put("voice", recorderfilename);
                }
                map.put("area",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNADREESS, null));
               if (stringBuilderLle.toString().trim()!=null) {
                   map.put("label_name", stringBuilderLle.toString().trim());
               }
                Log.i("www", fileOne + "===" + fileTwo + "---" + fileThree);
                ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
                Glide.with(this).load(R.mipmap.run).into(imageView);
                alertDialogUtils.showDialog(false);
               // HttpFactory.getInstance().PublishInvite(this, map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne), new Pair<String, File>("file[1]", fileTwo), new Pair<String, File>("file[2]", fileThree));

                if (fileOne!=null&&fileTwo==null&&fileThree==null)
                {
                    HttpFactory.getInstance().PublishInvite(Activity_publishinviteinfo.this, map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne));
                }
                if (fileOne!=null&&fileTwo!=null&&fileThree==null)
                {
                    HttpFactory.getInstance().PublishInvite(Activity_publishinviteinfo.this, map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne), new Pair<String, File>("file[1]", fileTwo));
                }

                if (fileOne!=null&&fileTwo!=null&&fileThree!=null)
                {
                    HttpFactory.getInstance().PublishInvite(Activity_publishinviteinfo.this, map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne), new Pair<String, File>("file[1]", fileTwo), new Pair<String, File>("file[2]", fileThree));
                }

                if (recorderfilename!=null) {
                    ftpUtils.setUploadSuccess(this);
                    ftpUtils.uploadSingleFile(new File(android.os.Environment.getExternalStorageDirectory() + "/" + recorderfilename), "Voice");
                }
                break;
        }


    }

    public void onEventMainThread(StringBuilder stringBuilder)
    {
        stringBuilderLle = stringBuilder;
        publishi_invite_directorsettingtext.setText(stringBuilder.toString().replace(","," "));
    }


    @Override
    protected void onResume() {
        super.onResume();
        mediaplayutils.setRecorderSettext(this);
        ftpUtils.setUploadSuccess(this);
    }

    @Override
    public void setText(String textview,String filename) {
        recorderfilename = filename;
        ((TextView)mainVu.getItemView(R.id.publish_invite_recorder_txt)).setText(textview);
    }

    @Override
    public void success() {


    }

   /* @Override
    public void getposition(double lat, double lng) {

    }*/
}
