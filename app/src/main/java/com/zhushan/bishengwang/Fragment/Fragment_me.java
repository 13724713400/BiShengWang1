package com.zhushan.bishengwang.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.BaseMediaObject;
import com.zhushan.bishengwang.Constance.ConstanceTag;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Constance.UserConstance;
import com.zhushan.bishengwang.Entry.HomePageInfoEntry;
import com.zhushan.bishengwang.Entry.UserInfoEntry;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.DialogFragmentUtils;
import com.zhushan.bishengwang.Itools.DisLruCacheUtils;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PopuwindowUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Itools.ZxingUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.About_Us;
import com.zhushan.bishengwang.activity.Activity_HisAuthPeaple;
import com.zhushan.bishengwang.activity.Activity_Login;
import com.zhushan.bishengwang.activity.Activity_MyFocus;
import com.zhushan.bishengwang.activity.Activity_MyMessage;
import com.zhushan.bishengwang.activity.Activity_MyPhoto;
import com.zhushan.bishengwang.activity.Activity_Mypublish;
import com.zhushan.bishengwang.activity.Activity_PrivateLetter;
import com.zhushan.bishengwang.activity.Activity_Register;
import com.zhushan.bishengwang.activity.Activity_certificatedinfomation;
import com.zhushan.bishengwang.activity.Activity_explore;
import com.zhushan.bishengwang.activity.Activity_feedBack;
import com.zhushan.bishengwang.activity.Activity_hisAuthTypeGrapher;
import com.zhushan.bishengwang.activity.Activity_printingPeapleDetails;
import com.zhushan.bishengwang.activity.Activity_setting;
import com.zhy.autolayout.utils.AutoUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.os.Handler;

import android.os.Message;

/**
 * Created by Administrator on 2015/11/28.
 */
public class Fragment_me extends Fragment implements View.OnClickListener{
    private RelativeLayout main_me_mypublish,main_me_myrenzheng,geren ,/*certificated_infomation,*/main_me_explore,main_me_mymessage/*,main_me_myphoto*/,main_me_myprivate,main_me_setting;
    /* private ImageView main_me_share,main_me_wodeerweima;*/
    //  private  PopuwindowUtils popuwindowUtils;
    private AlertDialog alertDialog;
    private MainVu mainVu = new MainVu();
    private String token;
    private int usertype;
    Bitmap bitmap;

    private boolean isPressTwo,isvisible;
    private UMSocialService umSocialService = UMServiceFactory.getUMSocialService("");
    private TextView /*main_me_city,*//*fragment_me_renzheng,*//*,main_me_huoyue,*/main_me_describe;
    private ImageView fragment_me_touxiang/*,fragment_me_sex,*//*main_me_type,*//*main_me_gunzhu*/;
    private TextView fragment_me_name,fragment_me_sex/*,main_me_huoyuezhitext*//*,main_me_line*/,main_me_myerweimatext;
    private boolean isFirstTwo;
    Handler handler=new Handler(){

        @Override

        public void handleMessage(Message msg) {

            if (msg.what==0x9527) {
//显示从网上下载的图片
                fragment_me_touxiang.setImageBitmap(bitmap);

            }

        }

    };
    SharedPreferences sp;
    String tel;
    String imgUrl;
    String sex;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_me,container,false);
        View view  =  mainVu.initByLayout(getActivity(),R.layout.fragment_me);
      /*  MainVu.getInstance().initByLayout(TBaplication.getInstance(), R.layout.fragment_me);*/
        initView();
        initListenner();
        // initShareContent();
        isPressTwo  = true;
        Log.i("www","isPreeme");
        loadData();
        new Thread(){
            @Override
            public void run() {
                try {
//创建一个url对象

                    sp = getActivity().getSharedPreferences("config.txt", Context.MODE_PRIVATE);
                    tel = sp.getString("name","未登录");
                    imgUrl = sp.getString("imgUrl","0");
                    sex = sp.getString("sex","0");
                    URL url=new URL(imgUrl);
                    if (tel==null&&token==null){
                        fragment_me_sex.setText(sex);
                        fragment_me_name.setText("未登录");
                    }else{
                        fragment_me_name.setText(tel);
                        fragment_me_sex.setText(sex);
                    }
//打开URL对应的资源输入流
                    InputStream is= url.openStream();

//从InputStream流中解析出图片

                    bitmap = BitmapFactory.decodeStream(is);

//  imageview.setImageBitmap(bitmap);

//发送消息，通知UI组件显示图片

                    handler.sendEmptyMessage(0x9527);

                    is.close();

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }.start();
        sp = getActivity().getSharedPreferences("config.txt", Context.MODE_PRIVATE);
        tel = sp.getString("name","未登录");
        sex = sp.getString("sex","sex");
        if (tel==null){
            fragment_me_name.setText(tel);
            fragment_me_name.setText("未登录");
        }else{
            fragment_me_sex.setText(sex);
            fragment_me_name.setText(tel);
        }
        // mainVu.setText(fragment_me_name,UserInfoEntry.getData().getNickname());
        return mainVu.getView();
    }

    private void loadData() {

        if (isPressTwo && isvisible) {
            initData();
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isvisible = true;
            if (!isFirstTwo) {
                isFirstTwo = !isFirstTwo;
                loadData();
            }
            Log.i("www","kejianme");
        } else {
            isvisible = false;
            //相当于Fragment的onPause
        }
    }
    private void initShareContent() {
        UmengUtils.getInstance().InitPlatform(getActivity(),umSocialService);
        // UmengUtils.getInstance().setShareContent(getActivity(), TBaplication.mController, (Bitmap) DisLruCacheUtils.getInstance().getValue(getActivity(), "wodeerweima", DisLruCacheUtils.BITMAP));
    }
    private void initListenner() {
//        main_me_mypublish.setOnClickListener(this);
        geren.setOnClickListener(this);
        //  main_me_share.setOnClickListener(this);
        // popuwindowUtils = new PopuwindowUtils(getActivity(),R.layout.popuwindwo_item);
        //  certificated_infomation.setOnClickListener(this);
        main_me_setting.setOnClickListener(this);
        main_me_explore.setOnClickListener(this);
        main_me_mymessage.setOnClickListener(this);
        //  main_me_wodeerweima.setOnClickListener(this);
        //   main_me_myphoto.setOnClickListener(this);
//        main_me_myprivate.setOnClickListener(this);
        // fragment_me_renzheng.setOnClickListener(this);
//        main_me_myrenzheng.setOnClickListener(this);
        fragment_me_touxiang.setOnClickListener(this);
        // mainVu.getItemView(R.id.main_me_gunzhu).setOnClickListener(this);
     /*   main_me_login.setOnClickListener(this);
        main_me_register.setOnClickListener(this);*/
    }
    public void onEventMainThread(UserInfoEntry userInfoEntry) {

      /*  if (userInfoEntry.getData().getHead_thumb().contains("Uploads")) {
            mainVu.setCircleImageUrl(getActivity(), HttpConstance.URL + userInfoEntry.getData().getHead_thumb(), fragment_me_touxiang);
        }else{
            mainVu.setCircleImageUrl(getActivity(), userInfoEntry.getData().getHead_thumb(), fragment_me_touxiang);
        }*/
        mainVu.setText(fragment_me_name,userInfoEntry.getData().getNickname())
                   /* .setText(main_me_huoyue, userInfoEntry.getData().getPoint())*/;
        usertype = userInfoEntry.getData().getUser_type();
        switch (userInfoEntry.getData().getUser_type())
        {
            case 0:
                // main_me_type.setImageResource(R.mipmap.cai);
                //   fragment_me_renzheng.setVisibility(View.VISIBLE);
                //    fragment_me_renzheng.setText(getResources().getString(R.string.renzhengchengweiyinshuaren));
                break;
            case 1:
                //main_me_type.setImageResource(R.mipmap.ren);
                // fragment_me_renzheng.setVisibility(View.VISIBLE);
                //  fragment_me_renzheng.setText(getResources().getString(R.string.renzhengchengweiyinshushang));
                break;
            case 2:
                // fragment_me_renzheng.setVisibility(View.GONE);
                // main_me_type.setImageResource(R.mipmap.shang);
                break;
        }

      /*  switch (userInfoEntry.getData().getSex())
        {
            case "男":
                fragment_me_sex.setImageResource(R.mipmap.icon_male);
                break;

            case "女":
                fragment_me_sex.setImageResource(R.mipmap.icon_female);
                break;
        }*/
    }
    private void initData() {
        //  main_me_city.setText(SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY,null).replace("市",""));
        UmengUtils.getInstance().InitPlatform(getActivity(),umSocialService);
        //  popuwindowUtils = new PopuwindowUtils(getActivity(), R.layout.popuwindwo_item, false);
        //  popuwindowUtils.getView(R.id.share_text).setVisibility(View.GONE);
        /*popuwindowUtils.getView(R.id.tofriends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog3 = null;
                Bitmap bitmap = ZxingUtils.getInstance().createQRImage(getActivity(),"www.baidu.com",400,400, BitmapFactory.decodeResource(getResources(), R.mipmap.login_logo), ImageUtil.getSDPath()+"touxiang.png",null);
                UmengUtils.getInstance().setShareContentFriends(getActivity(), umSocialService,bitmap);
                UmengUtils.getInstance().showCustomUI(getActivity(), umSocialService, alertDialog3);
            }
        });*/
        //main_me_city.setText(SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY,null).replace("市",""));
        token = SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);

        if (token==null)
        {

            //  fragment_me_renzheng.setText(getResources().getString(R.string.lijidenglu));
            //fragment_me_renzheng.setTextColor(getResources().getColor(R.color.textcoloryellow));
         /*   fragment_me_renzheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }
            });*/
        }else {
          /*  main_me_login.setVisibility(View.INVISIBLE);
            main_me_register.setVisibility(View.INVISIBLE);*/
            fragment_me_touxiang.setVisibility(View.VISIBLE);
            fragment_me_name.setVisibility(View.VISIBLE);
            //fragment_me_sex.setVisibility(View.VISIBLE);
            // main_me_type.setVisibility(View.VISIBLE);
            //  mainVu.setText(fragment_me_name,userInfoEntry.getData().getNickname()+UmengUtils.getInstance().getAge());
            main_me_describe.setVisibility(View.VISIBLE);
            //main_me_gunzhu.setVisibility(View.VISIBLE);
            // main_me_huoyue.setVisibility(View.VISIBLE);
            //  main_me_huoyuezhitext.setVisibility(View.VISIBLE);
            //main_me_line.setVisibility(View.VISIBLE);
            main_me_myerweimatext.setVisibility(View.VISIBLE);
            // main_me_wodeerweima.setVisibility(View.VISIBLE);
            // String token2 =  SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
            HashMap<String, String> map = new HashMap<String, String>();

            map.put("token", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
            HttpFactory.getInstance().UerInfo(getActivity(), map);

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        de.greenrobot.event.EventBus.getDefault().unregister(this);
    }
    private void initView() {

        de.greenrobot.event.EventBus.getDefault().register(this);

        //main_me_mypublish = mainVu.getItemView(R.id.main_me_mypublish);
        geren = mainVu.getItemView(R.id.geren);
        // main_me_share = mainVu.getItemView(R.id.main_me_share);
        //tificated_infomation = (RelativeLayout) view.findViewById(R.id.certificated_infomation);
        main_me_setting = mainVu.getItemView(R.id.main_me_setting);
        main_me_explore = mainVu.getItemView(R.id.main_me_explore);
        //   main_me_wodeerweima = mainVu.getItemView(R.id.main_me_wodeerweima);
        main_me_mymessage =mainVu.getItemView(R.id.main_me_mymessage);
        //  main_me_myphoto = mainVu.getItemView(R.id.main_me_myphoto);
        //     main_me_myprivate = mainVu.getItemView(R.id.main_me_myprivate);
        // fragment_me_renzheng = mainVu.getItemView(R.id.fragment_me_renzheng);
        fragment_me_touxiang =  mainVu.getItemView(R.id.fragment_me_touxiang);
        fragment_me_name =  mainVu.getItemView(R.id.fragment_me_name);
        fragment_me_sex=mainVu.getItemView(R.id.textView6);
        //   fragment_me_sex =  mainVu.getItemView(R.id.fragment_me_sex);
        //  main_me_huoyue =  mainVu.getItemView(R.id.main_me_huoyue);
        //   main_me_type = mainVu.getItemView(R.id.main_me_type);
        // main_me_myrenzheng =  mainVu.getItemView(R.id.main_me_myrenzheng);
        // main_me_city = mainVu.getItemView(R.id.main_me_city);
        //  main_me_login =  mainVu.getItemView(R.id.main_me_login);
        // main_me_register =  mainVu.getItemView(R.id.main_me_register);
        main_me_describe =  mainVu.getItemView(R.id.main_me_describe);
        //  main_me_gunzhu =  mainVu.getItemView(R.id.main_me_gunzhu);
        //  main_me_huoyuezhitext =  mainVu.getItemView(R.id.main_me_huoyuezhitext);
        //  main_me_huoyue =  mainVu.getItemView(R.id.main_me_huoyue);
        //   main_me_line =  mainVu.getItemView(R.id.main_me_line);
        //   main_me_myerweimatext =  mainVu.getItemView(R.id.main_me_myerweimatext);
        //   main_me_wodeerweima =  mainVu.getItemView(R.id.main_me_wodeerweima);
        //    main_me_city = mainVu.getItemView(R.id.main_me_city);
        SharePreferenceUtils.getInstance((Activity) getActivity(), UserConstance.USERTB).CreateSharePreference(UserConstance.USERID,null);
        sp = getActivity().getSharedPreferences("config.txt", Context.MODE_PRIVATE);
        tel = sp.getString("name","未登录");



    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
         /*   case R.id.main_me_myrenzheng:
                if (token!=null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else{
                    if (usertype==1) {
                        Bundle bundle = new Bundle();
                        bundle.putString("flag","me");
                        IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_HisAuthPeaple.class, bundle);
                    }
                    if (usertype==2)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString("flag","me");
                        IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_hisAuthTypeGrapher.class, bundle);

                    }

                    if (usertype==0)
                    {
                        Toast.makeText(getActivity(),"亲！还没认证为印刷人，快去认证吧！",Toast.LENGTH_SHORT).show();

                    }
                }
                // IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_certificatedinfomation.class,null);
                break;*/
        /*    case R.id.main_me_mypublish:
                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else{
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_Mypublish.class,null);
                }

                break;*/
            case R.id.geren:
                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else{
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_Mypublish.class,null);
                }
                break;
           /* case R.id.main_me_share:
                Log.i("www",popuwindowUtils+"kong");
                Log.i("www",popuwindowUtils+"main_me_share");
                popuwindowUtils.showPopuwindow(main_me_share);
                break;*/
          /*  case R.id.certificated_infomation:
                IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_certificatedinfomation.class,null);
                break;*/
            case R.id.main_me_explore:
             /*   if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else {*/
                IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_feedBack.class, null);
                //   }
                break;
           /* case R.id.main_me_wodeerweima:
                if (token==null) {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else{
                    DialogFragmentUtils.getInstance().showDialogfragment(getActivity(), new Dialog_Fragment());
                }
                break;
*/
            case R.id.main_me_mymessage:
                // DialogFragmentUtils.getInstance().showDialogfragment(getActivity(),new Dialog_liuyan_Fragment());
              /*  if (token==null)
                {*/
                IntentUtils.getInstance().startToAnoterActivity(getActivity(),About_Us.class,null);
                /*
                }else {*/
                //   IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_MyMessage.class, null);
        /*        }*/
                break;
          /*  case R.id.main_me_myphoto:
                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_MyPhoto.class, null);
                }
                break;*/
        /*    case R.id.main_me_myprivate:
                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_PrivateLetter.class, null);
                }else {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);

                }
                break;*/
            case R.id.main_me_setting:
             /*   if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else {*/
                IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_setting.class, null);
                //    }
                break;
           /* case R.id.fragment_me_renzheng:
                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else {

                    IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_certificatedinfomation.class, null);
                }
                break;*/
            case R.id.fragment_me_touxiang:
                if (tel==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_printingPeapleDetails.class,null);
                }
                break;
            /*case R.id.main_me_gunzhu:
                if (token==null)
                {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(),Activity_Login.class,null);
                }else {
                    IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_MyFocus.class,null);
                }
                break;*/
        }
    }
}
