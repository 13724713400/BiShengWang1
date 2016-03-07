package com.zhushan.bishengwang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.zhushan.bishengwang.Itools.Mediaplayutils.RecorderSettext;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.PositionMySeft.getPosition;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.SoundMeter;
import com.zhushan.bishengwang.Itools.WeelUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class Activity_publishusedinfo extends Basetivity implements View.OnClickListener,RecorderSettext,FTPUtils.UploadSuccess{

    private Mediaplayutils mediaplayutils;
    private MainVu mainVu = new MainVu();
    private EditText publishi_used_address,publishi_used_pinpaiming,publishi_used_day,publishi_used_month,publishi_used_year,publishi_used_fromprice,publishi_used_toprice,publishi_used_biaoti,publishi_used_content;
    private TextView publishi_used_yes,publishi_used_no,publishi_used_jicheng,publishi_used_directorsetting,publishi_used_directorsettingtext;
    private Button publishi_used_send;
    private ImageView publishi_used_recorder_txt,publishi_used_back,publishi_used_img1,publishi_used_img2,publishi_used_img3,publishi_used_recorder;
    private RelativeLayout publishi_used_xinjiuchengdu;
    private String recorderfilename;
    private File fileOne,fileTwo,fileThree;
    private FTPUtils ftpUtils = FTPUtils.getInstance();
    private StringBuilder stringBuilderLle;
    private String yesorno;
    private AlertDialogUtils alertDialogUtils;
    private boolean isRecord;
    private SoundMeter soundMeter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_publish_usedinfo));
        initView();
        initData();
        initListener();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mediaplayutils.setRecorderSettext(this);
        FTPUtils.getInstance().setUploadSuccess(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        publishi_used_back = mainVu.getItemView(R.id.publishi_used_back);
        publishi_used_address = mainVu.getItemView(R.id.publishi_used_address);
        publishi_used_biaoti = mainVu.getItemView(R.id.publishi_used_biaoti);
        publishi_used_content = mainVu.getItemView(R.id.publishi_used_content);
        publishi_used_day =  mainVu.getItemView(R.id.publishi_used_day);
        publishi_used_fromprice = mainVu.getItemView(R.id.publishi_used_fromprice);
        publishi_used_img1 = mainVu.getItemView(R.id.publishi_used_img1);
        publishi_used_img2 = mainVu.getItemView(R.id.publishi_used_img2);
        publishi_used_img3 =  mainVu.getItemView(R.id.publishi_used_img3);
        publishi_used_month = mainVu.getItemView(R.id.publishi_used_month);
        publishi_used_year = mainVu.getItemView(R.id.publishi_used_year);
        publishi_used_yes = mainVu.getItemView(R.id.publishi_used_yes);
        publishi_used_no =  mainVu.getItemView(R.id.publishi_used_no);
        publishi_used_pinpaiming = mainVu.getItemView(R.id.publishi_used_pinpaiming);
        publishi_used_recorder = mainVu.getItemView(R.id.publishi_used_recorder);
        publishi_used_recorder_txt = mainVu.getItemView(R.id.publishi_used_recorder_txt);
        publishi_used_toprice =  mainVu.getItemView(R.id.publishi_used_toprice);
        publishi_used_xinjiuchengdu = mainVu.getItemView(R.id.publishi_used_xinjiuchengdu);
        publishi_used_send = mainVu.getItemView(R.id.publishi_used_send);
        publishi_used_jicheng = mainVu.getItemView(R.id.publishi_used_jicheng);
        publishi_used_directorsetting =  mainVu.getItemView(R.id.publishi_used_directorsetting);
        publishi_used_directorsettingtext =  mainVu.getItemView(R.id.publishi_used_directorsettingtext);
    }

    @Override
    public void initData() {
        soundMeter =  new SoundMeter();
        alertDialogUtils = new AlertDialogUtils(this,R.layout.loading);
      /*  PositionMySeft positionMySeft = new PositionMySeft();
        positionMySeft.initPosition(this, TBaplication.locationClient);
        positionMySeft.StartPosition(TBaplication.locationClient);
        positionMySeft.setGetPosition(this);*/
        mediaplayutils = new Mediaplayutils();
        BackActivity.finishActivity(Activity_publishusedinfo.this, R.id.publishi_used_back);
        publishi_used_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesorno = "是";
                publishi_used_yes.setBackgroundColor(getResources().getColor(R.color.textcoloryellow));
                publishi_used_no.setBackgroundColor(getResources().getColor(R.color.textcolorwite));
            }
        });
        publishi_used_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesorno = "否";
                publishi_used_yes.setBackgroundColor(getResources().getColor(R.color.textcolorwite));
                publishi_used_no.setBackgroundColor(getResources().getColor(R.color.textcoloryellow));
            }
        });


    }
    @Override
    public void initListener() {
        publishi_used_img1.setOnClickListener(this);
        publishi_used_img2.setOnClickListener(this);
        publishi_used_img3.setOnClickListener(this);
        publishi_used_recorder.setOnClickListener(this);
        publishi_used_send.setOnClickListener(this);
        publishi_used_xinjiuchengdu.setOnClickListener(this);
        publishi_used_directorsetting.setOnClickListener(this);
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
                publishi_used_img2.setVisibility(View.VISIBLE);
               String path =  CameraUtils.getCameraBitmap(data,publishi_used_img1);
                fileOne = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK1&&resultCode==RESULT_OK)
        {
            publishi_used_img2.setVisibility(View.VISIBLE);
            String path = CameraUtils.getPicBitmap(data,Activity_publishusedinfo.this,publishi_used_img1);
            fileOne = new File(path);
        }

        if (requestCode==CameraUtils.CAPTURE2&&resultCode==RESULT_OK)
        {
            try {
                publishi_used_img3.setVisibility(View.VISIBLE);
               String path =  CameraUtils.getCameraBitmap(data,publishi_used_img2);
                fileTwo = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK2&&resultCode==RESULT_OK)
        {
            publishi_used_img3.setVisibility(View.VISIBLE);
            String path = CameraUtils.getPicBitmap(data,Activity_publishusedinfo.this,publishi_used_img2);
            fileTwo = new File(path);
        }


        if (requestCode==CameraUtils.CAPTURE3&&resultCode==RESULT_OK)
        {
            try {
               String path =  CameraUtils.getCameraBitmap(data,publishi_used_img3);
                fileThree = new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK3&&resultCode==RESULT_OK)
        {
           String path =  CameraUtils.getPicBitmap(data,Activity_publishusedinfo.this,publishi_used_img3);
            fileThree = new File(path);
        }
       /* if (requestCode==Mediaplayutils.RECORDER&&resultCode==RESULT_OK)
        {
            recorderfilename =  CameraUtils.getAudioInfo(Activity_publishusedinfo.this,data);
            publishi_used_recorder_txt.setText(mediaplayutils.getLength(recorderfilename));
        }*/
    }




    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.publishi_used_directorsetting:
                IntentUtils.getInstance().startToAnoterActivity(Activity_publishusedinfo.this,Activity_DirectorSetting.class,null);
                break;
            case R.id.publishi_used_xinjiuchengdu:

                WeelUtils.getInstance().showWeelView(Activity_publishusedinfo.this,publishi_used_jicheng);
                break;
            case R.id.publishi_used_img1:
                CameraUtils.photograph(Activity_publishusedinfo.this,CameraUtils.CAPTURE1,CameraUtils.ACTION_PICK1);
                break;
            case R.id.publishi_used_img2:
                CameraUtils.photograph(Activity_publishusedinfo.this,CameraUtils.CAPTURE2,CameraUtils.ACTION_PICK2);
                break;
            case R.id.publishi_used_img3:
                CameraUtils.photograph(Activity_publishusedinfo.this,CameraUtils.CAPTURE3,CameraUtils.ACTION_PICK3);
                break;
            case R.id.publishi_used_recorder:

                if (!isRecord) {
                    isRecord  = !isRecord;
                    recorderfilename = soundMeter.start();
                    Toast.makeText(Activity_publishusedinfo.this, "正在录音，再按一次停止录音", Toast.LENGTH_SHORT).show();
                    publishi_used_recorder.setImageResource(R.mipmap.voice);
                    publishi_used_recorder_txt.setVisibility(View.VISIBLE);
                }else{
                    isRecord  = !isRecord;
                    soundMeter.stop();
                    Toast.makeText(Activity_publishusedinfo.this, "停止录音", Toast.LENGTH_SHORT).show();
                    publishi_used_recorder_txt.setVisibility(View.INVISIBLE);
                    publishi_used_recorder.setImageResource(R.mipmap.icon_voice);
                }
               // mediaplayutils.startRecorder(Activity_publishusedinfo.this,Mediaplayutils.RECORDER,recorderfilename);
                break;
            case R.id.publishi_used_send:
                if(!EdiTextUtils.getInstance().CheckedEmpty(publishi_used_biaoti,getResources().getString(R.string.pulishipurchase_empty_biaoty)))
                {
                    return;
                }

                if (fileOne==null&&fileTwo==null&&fileThree==null)
                {
                    Toast.makeText(Activity_publishusedinfo.this, "请添加图片", Toast.LENGTH_SHORT).show();
                    return;

                }

                HashMap<String,String> map = new HashMap<String,String>();
                map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                map.put("cate_id", String.valueOf(3));
                map.put("title", publishi_used_biaoti.getText().toString().trim());
                if (publishi_used_content.getText().toString().trim()!=null) {
                    map.put("content", publishi_used_content.getText().toString().trim());
                }
                if (recorderfilename!=null) {
                    map.put("voice", recorderfilename);
                }
                map.put("lat",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
                map.put("lng",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG, null));
               if (stringBuilderLle.toString().trim()!=null) {
                   map.put("label_name", stringBuilderLle.toString().trim());
               }
                map.put("area",SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNADREESS, null));
                if (publishi_used_jicheng.getText().toString().trim()!=null) {
                    map.put("item[condition]", publishi_used_jicheng.getText().toString().trim());
                }
                if (publishi_used_fromprice.getText().toString().trim()!=null) {
                    map.put("item[price_range]", publishi_used_fromprice.getText().toString().trim() + "-" + publishi_used_toprice.getText().toString().trim());
                }
                if ( publishi_used_year.getText().toString().trim()!=null) {
                    map.put("item[product_date]", publishi_used_year.getText().toString().trim() + "-" + publishi_used_month.getText().toString().trim() + "-" + publishi_used_day.getText().toString().trim());
                }
                if ( publishi_used_pinpaiming.getText().toString().trim()!=null) {
                    map.put("item[brand]", publishi_used_pinpaiming.getText().toString().trim());
                }
                if (publishi_used_address.getText().toString().trim()!=null) {
                    map.put("item[product_area]", publishi_used_address.getText().toString().trim());
                }
               if (yesorno!=null) {
                   map.put("item[is_service]", yesorno);
               }
                ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
                Glide.with(this).load(R.mipmap.run).into(imageView);
                alertDialogUtils.showDialog(false);
             //   HttpFactory.getInstance().PublishUsed(this,map,alertDialogUtils,new Pair<String,File>("file[0]",fileOne),new Pair<String,File>("file[1]",fileTwo),new Pair<String,File>("file[2]",fileThree));
                if (fileOne!=null&&fileTwo==null&&fileThree==null)
                {
                    HttpFactory.getInstance().PublishUsed(Activity_publishusedinfo.this, map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne));
                }
                if (fileOne!=null&&fileTwo!=null&&fileThree==null)
                {
                    HttpFactory.getInstance().PublishUsed(Activity_publishusedinfo.this, map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne), new Pair<String, File>("file[1]", fileTwo));
                }

                if (fileOne!=null&&fileTwo!=null&&fileThree!=null)
                {
                    HttpFactory.getInstance().PublishUsed(Activity_publishusedinfo.this, map, alertDialogUtils, new Pair<String, File>("file[0]", fileOne), new Pair<String, File>("file[1]", fileTwo), new Pair<String, File>("file[2]", fileThree));
                }


                if (recorderfilename!=null) {
                    ftpUtils.setUploadSuccess(this);
                    ftpUtils.uploadSingleFile(new File(android.os.Environment.getExternalStorageDirectory() + "/" + recorderfilename), "Voice");

                }


                break;
        }
    }
    @Override
    public void setText(String textview,String filename) {
        recorderfilename  =filename;
        ((TextView)mainVu.getItemView(R.id.publishi_used_recorder_txt)).setText(textview);
    }


    public void onEventMainThread(StringBuilder stringBuilder)
    {
        stringBuilderLle = stringBuilder;
        Log.i("www","used"+stringBuilderLle.toString());
        publishi_used_directorsettingtext.setText(stringBuilder.toString().replace(",", " "));
    }

    @Override
    public void success() {




    }

    /*@Override
    public void getposition(double lat, double lng) {

    }*/
}
