package com.zhushan.bishengwang.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.umeng.socialize.utils.BitmapUtils;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Constance.UserConstance;
import com.zhushan.bishengwang.Itools.ArithmeticUtils;
import com.zhushan.bishengwang.Itools.DisLruCacheUtils;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.PrintCallStatckUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.Iaplication.TBaplication;

import java.util.Collection;
import java.util.Comparator;

public class Activity_Login_Main extends Basetivity implements PositionMySeft.getPosition {

    private Button loginmain_btn,register_main_btn;
    private ImageView loginmain_touxiang;
    private PositionMySeft positionMySeft;
/*    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
           if (msg.what==110)
           {
               IntentUtils.getInstance().startToAnoterActivity(Activity_Login_Main.this, MainActivity.class, null);

           }
        }
    };*/

    private static final int GOHOME=1000;
    private static final int GOGUid=1001;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what) {
                case GOHOME:
                    goHome();
                    break;
                case GOGUid:
                    goGuid();
                    break;
            }
        }

        private void goGuid() {
            Intent intent = new Intent( Activity_Login_Main.this,GuidActivity.class);
            startActivity(intent);
            Activity_Login_Main.this.finish();

        }

        private void goHome() {
            Intent intent = new Intent( Activity_Login_Main.this,MainActivity.class);
            startActivity(intent);
            Activity_Login_Main.this.finish();

        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);
        initView();
        initData();
        initListener();

    }
    @Override
    public void initData() {

        positionMySeft = new PositionMySeft();
        positionMySeft.initPosition(this, TBaplication.locationClient);
        positionMySeft.StartPosition(TBaplication.locationClient);
        positionMySeft.setGetPosition(this);
        Bitmap bitmap = null;
        String path = SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERIMG, null);
        if (path != null) {
          ImageUtil.setImageByPath(loginmain_touxiang,path,ImageUtil.ISCIRCLE);
        }
        SharedPreferences preferences=getSharedPreferences("first_pref", MODE_PRIVATE);
        boolean isfirstlogin=preferences.getBoolean("key", false);

        if(isfirstlogin)
        {
            handler.sendEmptyMessageDelayed(GOHOME, 2000);
        }else{
            handler.sendEmptyMessageDelayed(GOGUid, 2000);
        }

        handler.sendEmptyMessageDelayed(110,1000);

      /*  try {
            Thread.sleep(2000);
            IntentUtils.getInstance().startToAnoterActivity(Activity_Login_Main.this, MainActivity.class, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
          /* loginmain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String usertoken =  SharePreferenceUtils.getInstance(Activity_Login_Main.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null);
                if (usertoken!=null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(Activity_Login_Main.this, MainActivity.class,null);
                }else{
                    IntentUtils.getInstance().startToAnoterActivity(Activity_Login_Main.this, Activity_Login.class,null);
                }
            }
        });
        register_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getInstance().startToAnoterActivity(Activity_Login_Main.this, Activity_Register.class,null);
            }
        });
    }*/

    }
    @Override
    public void initListener() {
      /*  int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        int dex = ArithmeticUtils.binarySearch(arr,9,3);
        Log.i("www","下标"+dex);*/
    }


    @Override
    public void initView() {
        Log.i("www","初始化数据");
    /*    loginmain_btn = (Button) findViewById(R.id.loginmain_btn);
        register_main_btn = (Button) findViewById(R.id.register_main_btn);*/
        loginmain_touxiang = (ImageView) findViewById(R.id.loginmain_touxiang);
        Log.i("www","数据"+register_main_btn);
    }

    @Override
    public void getposition(double lat, double lng) {

    }
}
