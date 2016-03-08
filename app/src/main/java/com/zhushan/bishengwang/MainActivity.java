package com.zhushan.bishengwang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.AuthCheckEntry;
import com.zhushan.bishengwang.Fragment.Dialog_ershou_Fragment;
import com.zhushan.bishengwang.Fragment.Dialog_gongying_Fragment;
import com.zhushan.bishengwang.Fragment.Dialog_zhaopin_Fragment;
import com.zhushan.bishengwang.Fragment.Fragment_homepage;
import com.zhushan.bishengwang.Fragment.Fragment_me;
import com.zhushan.bishengwang.Fragment.Fragment_pringtingpeaple;
import com.zhushan.bishengwang.Fragment.Fragment_typographer;
import com.zhushan.bishengwang.Iadapter.MainViewPagerAdapter;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.DialogFragmentUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.activity.Activity_Login;
import com.zhushan.bishengwang.activity.Activity_publishinviteinfo;
import com.zhushan.bishengwang.activity.Activity_publishisuply;
import com.zhushan.bishengwang.activity.Activity_publishpurchaseinfo;
import com.zhushan.bishengwang.activity.Activity_publishusedinfo;
import com.zhushan.bishengwang.activity.Basetivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;
public class MainActivity extends Basetivity{
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private RadioGroup radioGroup;
    private ImageView mainimg,main_dialog_imgthree,main_dialog_imgfive;
    private boolean isPress;
    private TextView main_printpealple_tv,main_me_tv,main_typograpter_tv,main_homepage_tv;
    private boolean isExit;

    private final static int ISEXIT=1;
    private String token;
    private AlertDialogUtils alertDialogUtils;
    private String flag = null;
    private PositionMySeft positionMySeft = new PositionMySeft();
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case ISEXIT:
                    isExit = false;
                    break;
            }
        }
    };
    private MainVu mainVu = new MainVu();
    private UMSocialService mController = UMServiceFactory.getUMSocialService("app");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this, R.layout.activity_main));
        initView();
        initData();
        initListener();
        main_homepage_tv.setTextColor(Color.parseColor("#ffdc2e"));

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
        {
            if (isExit)
            {
  Intent intent = new Intent("com.zhushan.mybroadcast");
               sendBroadcast(intent);

                TBaplication.exit();
            }else {
                Toast.makeText(MainActivity.this,"再点一次退出！",Toast.LENGTH_SHORT).show();
                isExit = true;
                handler.sendEmptyMessageDelayed(ISEXIT, 3000);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        viewPager = mainVu.getItemView(R.id.maintwo_viewpaper);
        radioGroup =  mainVu.getItemView(R.id.main_radiogroupId);
        AutoUtils.auto(radioGroup);
        main_me_tv    = mainVu.getItemView(R.id.main_me_tv);
        main_printpealple_tv  = mainVu.getItemView(R.id.main_printpealple_tv);
        main_typograpter_tv = mainVu.getItemView(R.id.main_typograpter_tv);
        main_homepage_tv= mainVu.getItemView(R.id.main_homepage_tv);

        //   mainimg =  mainVu.getItemView(R.id.main_dialog_img);
 // main_dialog_imgtwo = mainVu.getItemView(R.id.main_dialog_imgtwo);
      //  main_dialog_imgtwo =  mainVu.getItemView(R.id.main_dialog_imgthree);
       // main_dialog_imgfore =  mainVu.getItemView(R.id.main_dialog_imgfore);
      //  main_dialog_imgfive =  mainVu.getItemView(R.id.main_dialog_imgfive);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        TBaplication.locationClient.stop();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void initData() {


        token = SharePreferenceUtils.getInstance(this,SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null);
        String addr =  SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNADREESS,null);
//        DbUtils.getInstance().LoginDelete();
        fragmentList = new ArrayList<Fragment>();
        alertDialogUtils = new AlertDialogUtils(MainActivity.this,R.layout.dialog_main);
        Fragment_homepage fragment_homepage = new Fragment_homepage();
        Fragment_typographer fragment_typographer = new Fragment_typographer();
        Fragment_pringtingpeaple fragment_pringtingpeaple = new Fragment_pringtingpeaple();
        Fragment_me fragment_me = new Fragment_me();
        fragmentList.add(fragment_pringtingpeaple);
        fragmentList.add(fragment_homepage);
        fragmentList.add(fragment_typographer);
        fragmentList.add(fragment_me);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setCurrentItem(0);
        Bundle  bundle = getIntent().getExtras();
        ((RadioButton) (radioGroup.getChildAt(0))).setChecked(true);
        if (bundle!=null)
        {
            switch (bundle.getString("flag"))
            {
                case "Fragment_pringtingpeaple":
                    viewPager.setCurrentItem(2);
                    ((RadioButton) (radioGroup.getChildAt(2))).setChecked(true);
                    break;

                case "Fragment_typographer":
                    viewPager.setCurrentItem(1);
                    ((RadioButton) (radioGroup.getChildAt(1))).setChecked(true);
                    break;
                case "Fragment_homepage":
                    viewPager.setCurrentItem(0);
                    ((RadioButton) (radioGroup.getChildAt(0))).setChecked(true);
                    break;
            }



        }

        //   ImageViewCheckUtils.getInstance().MainFirstVisibly(MainActivity.this, main_dialog_imgtwo, main_dialog_imgthree, main_dialog_imgfore, main_dialog_imgfive);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                String yellow="#ffdc2e";
                String grey="#a1a1a1";
                Log.i("www","viewpaper");

                if (position == 0) {
                    ((RadioButton) (radioGroup.getChildAt(position))).setChecked(true);
                    //   ((RadioButton) (radioGroup.getChildAt(position))).setVisibility(View.INVISIBLE);
                    main_printpealple_tv.setTextColor(Color.parseColor(grey));
                    main_me_tv.setTextColor(Color.parseColor(grey));
                    main_typograpter_tv.setTextColor(Color.parseColor(grey));
                    main_homepage_tv.setTextColor(Color.parseColor(yellow));
                    //  ImageViewCheckUtils.getInstance().MainFirstVisibly(MainActivity.this,main_dialog_imgtwo,main_dialog_imgthree,main_dialog_imgfore,main_dialog_imgfive);
                }

                if (position == 1) {
                    ((RadioButton) (radioGroup.getChildAt(position))).setChecked(true);
                    // ((RadioButton) (radioGroup.getChildAt(position))).setVisibility(View.INVISIBLE);
                    main_printpealple_tv.setTextColor(Color.parseColor(grey));
                    main_me_tv.setTextColor(Color.parseColor(grey));
                    main_typograpter_tv.setTextColor(Color.parseColor(yellow));
                    main_homepage_tv.setTextColor(Color.parseColor(grey));
                      //  ImageViewCheckUtils.getInstance().MainSecondVisibly(MainActivity.this, main_dialog_imgtwo, main_dialog_imgthree, main_dialog_imgfore, main_dialog_imgfive);
                }
                if (position == 2) {
                    ((RadioButton) (radioGroup.getChildAt(position + 1))).setChecked(true);
                    //   ((RadioButton) (radioGroup.getChildAt(position))).setVisibility(View.INVISIBLE);
                    main_printpealple_tv.setTextColor(Color.parseColor(yellow));
                    main_me_tv.setTextColor(Color.parseColor(grey));
                    main_typograpter_tv.setTextColor(Color.parseColor(grey));
                    main_homepage_tv.setTextColor(Color.parseColor(grey));
                    //  ImageViewCheckUtils.getInstance().MainThreeVisibly(MainActivity.this, main_dialog_imgtwo, main_dialog_imgthree, main_dialog_imgfore, main_dialog_imgfive);
                }

                if (position == 3) {
                    main_typograpter_tv.setTextColor(Color.parseColor(grey));
                    ((RadioButton) (radioGroup.getChildAt(position + 1))).setChecked(true);
                    //   ((RadioButton) (radioGroup.getChildAt(position))).setVisibility(View.INVISIBLE);
                    main_printpealple_tv.setTextColor(Color.parseColor(grey));
                    main_me_tv.setTextColor(Color.parseColor(yellow));
                    main_homepage_tv.setTextColor(Color.parseColor(grey));
                    // ImageViewCheckUtils.getInstance().MainFoureVisibly(MainActivity.this, main_dialog_imgtwo, main_dialog_imgthree, main_dialog_imgfore, main_dialog_imgfive);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.main_homepage_rb:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.main_typograpter_rb:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.main_printpealple_rb:
                        viewPager.setCurrentItem(2);
                        break;

                    case R.id.main_me_rb:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
/* mainimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainimg.setImageResource(R.mipmap.home_on);
                if (!isPress) {
                    isPress = !isPress;
                    alertDialogUtils.showDialog(false);
                } else {
                    alertDialogUtils.showdialg();
                }
            }
        });*/
        //  Log.i("www", "alertDialogUtils"+alertDialogUtils);
        alertDialogUtils.getView(R.id.main_dialog_diss_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogUtils.dissView();
                mainimg.setImageResource(R.mipmap.home_off);
            }
        });

        alertDialogUtils.getView(R.id.publish_purchase_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DialogFragmentUtils.getInstance().showDialogfragment(MainActivity.this, new Dialog_gongying_Fragment());

                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_Login.class, null);

                }else{
                    flag = "publish_purchase_info";
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put("token",token);
                    HttpFactory.getInstance().authCheck(MainActivity.this, map);

                }





            }
        });

        alertDialogUtils.getView(R.id.publish_invite_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  DialogFragmentUtils.getInstance().showDialogfragment(MainActivity.this, new Dialog_zhaopin_Fragment());
                //  IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_publishinviteinfo.class, null);
                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_Login.class, null);

                }else{
                    flag = "publish_invite_info";
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put("token",token);
                    HttpFactory.getInstance().authCheck(MainActivity.this, map);

                }


            }
        });

        alertDialogUtils.getView(R.id.publishi_used_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_Login.class, null);

                }else{
                    flag = "publishi_used_info";
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put("token",token);
                    HttpFactory.getInstance().authCheck(MainActivity.this, map);

                }
                //  DialogFragmentUtils.getInstance().showDialogfragment(MainActivity.this, new Dialog_ershou_Fragment());
                //IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_publishusedinfo.class, null);
            }
        });

        alertDialogUtils.getView(R.id.publishi_gongying_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_Login.class, null);

                }else{
                    flag = "publishi_gongying_info";
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put("token",token);
                    HttpFactory.getInstance().authCheck(MainActivity.this, map);

                }
                // DialogFragmentUtils.getInstance().showDialogfragment(MainActivity.this, new Dialog_gongying_Fragment());
                // IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_publishisuply.class, null);
            }
        });


    }


    public void onEventMainThread(AuthCheckEntry authEntry)
    {

        switch (Integer.parseInt(authEntry.getData()))
        {
            case 0:

                switch (flag)
                {
                    case "publish_purchase_info":
                        IntentUtils.getInstance().startToAnoterActivity(MainActivity.this,Activity_publishpurchaseinfo.class,null);
                        break;
                    case "publish_invite_info":
                        DialogFragmentUtils.getInstance().showDialogfragment(MainActivity.this, new Dialog_zhaopin_Fragment());
                        break;
                    case "publishi_used_info":
                        DialogFragmentUtils.getInstance().showDialogfragment(MainActivity.this, new Dialog_ershou_Fragment());
                        break;
                    case "publishi_gongying_info":

                        DialogFragmentUtils.getInstance().showDialogfragment(MainActivity.this, new Dialog_gongying_Fragment());
                        break;

                }


                break;
            case 1:

                switch (flag)
                {
                    case "publish_purchase_info":
                        IntentUtils.getInstance().startToAnoterActivity(MainActivity.this,Activity_publishpurchaseinfo.class,null);
                        break;
                    case "publish_invite_info":
                        IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_publishinviteinfo.class, null);
                        break;
                    case "publishi_used_info":
                        IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_publishusedinfo.class, null);
                        break;
                    case "publishi_gongying_info":
                        Log.i("www","杩涙潵publishi_gongying_info");
                        IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_publishisuply.class, null);
                        break;

                }

                break;
            case 2:

                switch (flag)
                {
                    case "publish_purchase_info":
                        IntentUtils.getInstance().startToAnoterActivity(MainActivity.this,Activity_publishpurchaseinfo.class,null);
                        break;
                    case "publish_invite_info":
                        IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_publishinviteinfo.class, null);
                        break;
                    case "publishi_used_info":
                        IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_publishusedinfo.class, null);
                        break;
                    case "publishi_gongying_info":
                        Log.i("www","杩涙潵publishi_gongying_info2");
                        IntentUtils.getInstance().startToAnoterActivity(MainActivity.this, Activity_publishisuply.class, null);
                        break;

                }

                break;

        }

    }

    @Override
    public void initListener() {

        Log.i("www", "绾害" + SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
        Log.i("www", "缁忓害" + SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG, null));
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

