package com.zhushan.bishengwang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Fragment.Dialog_Date_Fragment;
import com.zhushan.bishengwang.Fragment.Dialog_Date_Fragment.GetDate;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Constance.BundleConstance;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.DialogFragmentUtils;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.Mediaplayutils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.PositionMySeft.getPosition;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
public class Activity_UploadHeaderImg extends Basetivity implements GetDate {
    private Button uploader_img_finish;
    private EditText uploader_Name,uploader_birtherday;
    private RadioButton uploader_mail,uploader_femail;
    private ImageView uploader_headImg;
    private RadioGroup uploader_rg;
    private int uid;
    private File Imgfile;
    private double lattwo,lngtwo;
    private PositionMySeft positionMySeft;
    private String sex;
    private String path;
    private Dialog_Date_Fragment dialog_date_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_img);
        initView();
        initData();
        initListener();
    }
    @Override
    public void initView() {
        uploader_img_finish = (Button) findViewById(R.id.uploader_img_finish);
        uploader_Name = (EditText) findViewById(R.id.uploader_Name);
        uploader_birtherday = (EditText) findViewById(R.id.uploader_birtherday);
        uploader_mail = (RadioButton) findViewById(R.id.uploader_mail);
        uploader_femail = (RadioButton) findViewById(R.id.uploader_femail);
        uploader_headImg = (ImageView) findViewById(R.id.uploader_headImg);
        uploader_rg = (RadioGroup) findViewById(R.id.uploader_rg);
    }
    @Override
    public void initData() {
        if (getIntent().getExtras()==null)
        {
            return;
        }
        uid = (int) getIntent().getExtras().get(BundleConstance.UID);
        uploader_mail.setChecked(true);
        sex="男";
        uploader_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.uploader_mail:
                        sex = "男";
                        break;
                    case R.id.uploader_femail:
                        sex = "女";
                        break;
                }
            }
        });
        dialog_date_fragment = new Dialog_Date_Fragment();
      /*  positionMySeft = new PositionMySeft();
        positionMySeft.initPosition(Activity_UploadHeaderImg.this, TBaplication.locationClient);
        positionMySeft.StartPosition(TBaplication.locationClient);*/
        uploader_headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUtils.photograph(Activity_UploadHeaderImg.this, CameraUtils.CAPTURE1, CameraUtils.ACTION_PICK1);
            }
        });

        uploader_birtherday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragmentUtils.getInstance().showDialogfragment(Activity_UploadHeaderImg.this,dialog_date_fragment);
            }
        });

        uploader_img_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!EdiTextUtils.getInstance().CheckedEmpty(uploader_Name,getResources().getString(R.string.uploader_Name)))
                {
                    return;
                }
                if(!EdiTextUtils.getInstance().CheckedEmpty(uploader_birtherday,getResources().getString(R.string.uploader_birthday)))
                {
                    return;
                }

                Log.i("www","uid"+uid+"uploader_Name"+uploader_Name.getText().toString().trim()+sex+"lng"+lngtwo+" "+Imgfile+"Imgfile");
                if (Imgfile==null)
                {
                    Toast.makeText(Activity_UploadHeaderImg.this,"请上传头像",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("www","uid"+uid);
                /*if (uid==0|| uploader_Name.getText().toString().trim()==null||uploader_birtherday.getText().toString().trim()==null||sex==null||lngtwo==0||lattwo==0)
                {
                    return;
                }*/
                Log.i("www", "uid" + uid);
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("uid",String.valueOf(uid));
                map.put("nickname", uploader_Name.getText().toString().trim());
                map.put("age",uploader_birtherday.getText().toString().trim());
                map.put("sex",sex);
                map.put("lng",SharePreferenceUtils.getInstance(Activity_UploadHeaderImg.this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG, null));
                map.put("lat",SharePreferenceUtils.getInstance(Activity_UploadHeaderImg.this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
                String city = SharePreferenceUtils.getInstance(Activity_UploadHeaderImg.this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY, null);
                if (city != null) {
                    String city3 = city.replace("市","");
                    map.put("city",city3);
                }
              //  map.put("city",SharePreferenceUtils.getInstance(Activity_UploadHeaderImg.this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY, null).replace("市", ""));
                map.put("area",SharePreferenceUtils.getInstance(Activity_UploadHeaderImg.this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNADREESS, null) );
                map.put("step",String.valueOf(2));
                String province = SharePreferenceUtils.getInstance(Activity_UploadHeaderImg.this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONPROVICE,null);
                if (province!=null) {
                    String provincetwo =  province.replace("省","");
                    map.put("province", provincetwo);
                }
                Log.i("www", "" + map);
                SharePreferenceUtils.getInstance(Activity_UploadHeaderImg.this,SharePreferenceConstance.USERNAME).CreateSharePreference(SharePreferenceConstance.USERIMG,path);
                HttpFactory.getInstance().Uplaoader(Activity_UploadHeaderImg.this,map,new Pair<String, File>("file", Imgfile));


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CameraUtils.dialogdiss();
        if (requestCode == CameraUtils.CAPTURE1 && resultCode == RESULT_OK) {
            try {
               // CameraUtils.getCameraBitmap(data, uploader_headImg);
               path =  CameraUtils.getCameraBitmapCircle(data, uploader_headImg);
                Imgfile =  new File(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CameraUtils.ACTION_PICK1 && resultCode == RESULT_OK) {
            path =  CameraUtils.getPicBitmapCilcle(data, Activity_UploadHeaderImg.this, uploader_headImg);
            Imgfile =  new File(path);
        }
    }
    @Override
    public void initListener() {
      //  positionMySeft.setGetPosition(this);
        dialog_date_fragment.setGetDate(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

/*    @Override
    public void getposition(double lat, double lng) {
        lattwo = lat;
        lngtwo = lng;
        Log.i("www", "lat" + lat + "lng" + lng);
      //  SharePreferenceUtils.getInstance(Activity_UploadHeaderImg.this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNADREESS, null)
    }*/
    @Override
    public void getDate(String date) {
        uploader_birtherday.setText(date);
    }
}
