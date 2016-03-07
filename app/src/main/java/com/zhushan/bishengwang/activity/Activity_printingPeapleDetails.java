package com.zhushan.bishengwang.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.PringTingPeapleEntry;
import com.zhushan.bishengwang.Entry.PrintingPeapleEntry;
import com.zhushan.bishengwang.Fragment.BlankFragment;
import com.zhushan.bishengwang.Fragment.Fragment_entire;
import com.zhushan.bishengwang.Fragment.Fragment_flock;
import com.zhushan.bishengwang.Fragment.Fragment_shejiao;
import com.zhushan.bishengwang.Fragment.Fragment_yins;
import com.zhushan.bishengwang.Iadapter.CommonViewHolder;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.CallPhoneUtils;
import com.zhushan.bishengwang.Itools.CircleTransform;
import com.zhushan.bishengwang.Itools.GlideCircleTransform;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PopuwindowUtils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Itools.ZxingUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class Activity_printingPeapleDetails extends Basetivity implements View.OnClickListener {
    private UMSocialService umSocialService = UMServiceFactory.getUMSocialService("");
    private MainVu mainVu = new MainVu();
    private double lat,lng;
    private Fragment fragment1,fragment3,fragment2,fragmentById;
    private PringTingPeapleEntry pringTingPeapleEntry2;
    private String userType;
    private TextView yins_text,yins_line,shejiao_text,shejiao_line;
    private LinearLayout shejiao,yins_foio;
    private ImageView pringtingpeaple_details_head;
    private PopuwindowUtils popuwindowUtils;
    private ImageView printingpeaple_share,phone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this,R.layout.activity_printtingpeple_detaile));
        initView();
        initData();
        initListener();
    }

    public void onEventMainThread(PringTingPeapleEntry pringTingPeapleEntry)
    {
        pringTingPeapleEntry2  = pringTingPeapleEntry;
        if (pringTingPeapleEntry.getData().getHead_thumb().contains("Uploads")) {

            Glide.with(this)
                    .load(HttpConstance.URL + pringTingPeapleEntry.getData().getHead_thumb())
                    .transform(new GlideCircleTransform(this)).placeholder(R.mipmap.logotwo).into(pringtingpeaple_details_head);
           // mainVu.setImageUrlById(R.id.pringtingpeaple_details_head, this, HttpConstance.URL + pringTingPeapleEntry.getData().getHead_thumb());
        }else{
            Log.i("www","院2");
            Glide.with(this)
                    .load(pringTingPeapleEntry.getData().getHead_thumb())
                    .transform(new GlideCircleTransform(this)).placeholder(R.mipmap.logotwo).into(pringtingpeaple_details_head);

           // mainVu.setImageUrlById(R.id.pringtingpeaple_details_head, this,  pringTingPeapleEntry.getData().getHead_thumb());
        }
        if (pringTingPeapleEntry.getData().getGallery()!=null) {
            switch (pringTingPeapleEntry.getData().getGallery().size()) {
              /*  case 0:
                    mainVu.getItemView(R.id.pringtingpeaple_details_img1).setVisibility(View.INVISIBLE);
                    mainVu.getItemView(R.id.pringtingpeaple_details_img2).setVisibility(View.INVISIBLE);
                    mainVu.getItemView(R.id.pringtingpeaple_details_img3).setVisibility(View.INVISIBLE);
                    mainVu.getItemView(R.id.pringtingpeaple_details_img4).setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img1, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(0).getMid_img());
                    mainVu.getItemView(R.id.pringtingpeaple_details_img2).setVisibility(View.INVISIBLE);
                    mainVu.getItemView(R.id.pringtingpeaple_details_img3).setVisibility(View.INVISIBLE);
                    mainVu.getItemView(R.id.pringtingpeaple_details_img4).setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img1, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(0).getMid_img());
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img2, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(1).getMid_img());
                    mainVu.getItemView(R.id.pringtingpeaple_details_img3).setVisibility(View.INVISIBLE);
                    mainVu.getItemView(R.id.pringtingpeaple_details_img4).setVisibility(View.INVISIBLE);
                    break;

                case 3:
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img1, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(0).getMid_img());
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img2, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(1).getMid_img());
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img3, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(2).getMid_img());
                    mainVu.getItemView(R.id.pringtingpeaple_details_img4).setVisibility(View.INVISIBLE);
                case 4:
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img1, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(0).getMid_img());
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img2, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(1).getMid_img());
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img3, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(2).getMid_img());
                    mainVu.setImageUrlById(R.id.pringtingpeaple_details_img4, this, HttpConstance.URL + pringTingPeapleEntry.getData().getGallery().get(3).getMid_img());

                    break;
*/
            }
        }

        switch (pringTingPeapleEntry.getData().getSex())
        {
            case "男":
                ((TextView)mainVu.getItemView(R.id.pringtingpeaple_details_femail)).setText(pringTingPeapleEntry.getData().getNickname());
                ((ImageView)mainVu.getItemView(R.id.pringtingpeaple_details_sex)).setImageResource(R.mipmap.icon_male);
                break;
            case "女":
                ((TextView)mainVu.getItemView(R.id.pringtingpeaple_details_femail)).setText(pringTingPeapleEntry.getData().getNickname());
                ((ImageView)mainVu.getItemView(R.id.pringtingpeaple_details_sex)).setImageResource(R.mipmap.icon_female);
                break;

        }

        // ((ImageView)mainVu.getItemView(R.id.pringtingpeaple_details_type)).setImageResource(R.mipmap.ren);
        String tmpstr=pringTingPeapleEntry.getData().getDescription().replace("\n|\t","");
      /*  if(tmpstr==null){
            mainVu.setTextById(R.id.pringtingpeaple_details_describe,"这家伙很懒什么都没留下！");
        }else{
            mainVu.setTextById(R.id.pringtingpeaple_details_describe,pringTingPeapleEntry.getData().getDescription());

        }*/
    /*    if(pringTingPeapleEntry.getData().getDescription().equals(null)||
                pringTingPeapleEntry.getData().getDescription()==" "||
                pringTingPeapleEntry.getData().getDescription()==""||
                pringTingPeapleEntry.getData().getDescription()==null
                || pringTingPeapleEntry.getData().getDescription().equals(" ")
                ||  pringTingPeapleEntry.getData().getDescription().equals("")){
            mainVu.setTextById(R.id.pringtingpeaple_details_describe,"这家伙很懒什么都没留下！");
        }else{
            mainVu.setTextById(R.id.pringtingpeaple_details_describe,pringTingPeapleEntry.getData().getDescription());
        }*/

        mainVu
                .setTextById(R.id.pringtingpeaple_details_name,pringTingPeapleEntry.getData().getNickname());
                //    .setTextById(R.id.pringtingpeaple_details_huo, pringTingPeapleEntry.getData().getPoint())
              //  .setTextById(R.id.pringtingpeaple_details_address, pringTingPeapleEntry.getData().getCompany_area())
             /*   .setTextById(R.id.accoun2,"ID:"+pringTingPeapleEntry.getData().getId())*/
                //   .setTextById(R.id.pringtingpeaple_details_zhiwei,pringTingPeapleEntry.getData().getRule())
                /// .setTextById(R.id.pringtingpeaple_details_gongsi, getResources().getString(R.string.gongsi) + "   " + pringTingPeapleEntry.getData().getCompany_name())
                //  .setTextById(R.id.pringtingpeaple_details_gunzhutext, getResources().getString(R.string.guanzhutaderen) + "   " + pringTingPeapleEntry.getData().getNotice_number())


                //已在此处修改过经纬度
               // .setTextById(R.id.pringtingpeaple_details_distance, String.valueOf(PositionMySeft.getDistance(lat, lng, Double.parseDouble(pringTingPeapleEntry.getData().getLat()), Double.parseDouble(pringTingPeapleEntry.getData().getLng()))) + "km");


        boolean isContaint =  pringTingPeapleEntry.getData().getMark().toString().contains(",");
        boolean isEmpty = pringTingPeapleEntry.getData().getMark().toString().equals("");
      /*  if (!isContaint&&!isEmpty)
        {
            mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian1).setVisibility(View.VISIBLE);
            mainVu.setTextById(R.id.pringtingpeaple_details_biaoqian1, pringTingPeapleEntry.getData().getMark().toString());
            // mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian2).setVisibility(View.INVISIBLE);
            //  mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian3).setVisibility(View.INVISIBLE);
        }*/
        String[] mark =  pringTingPeapleEntry.getData().getMark().split(",");
      /*  for (int i = 0;i<mark.length;i++)
        {
            switch (i)
            {
            *//*  case 0:
                  mainVu.setImageUrlById(R.id.pringtingpeaple_details_biaoqian1, this, mark[0]);
                  mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian2).setVisibility(View.INVISIBLE);
                  mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian3).setVisibility(View.INVISIBLE);
                  break;*//*
                case 1:
                    mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian1).setVisibility(View.VISIBLE);
                    //     mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian2).setVisibility(View.VISIBLE);
                    mainVu.setTextById(R.id.pringtingpeaple_details_biaoqian1,  mark[0]);
                    //   mainVu.setTextById(R.id.pringtingpeaple_details_biaoqian2,  mark[1]);
                    //   mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian3).setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian1).setVisibility(View.VISIBLE);
                    //  mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian2).setVisibility(View.VISIBLE);
                    //   mainVu.getItemView(R.id.pringtingpeaple_details_biaoqian3).setVisibility(View.VISIBLE);
                    mainVu.setTextById(R.id.pringtingpeaple_details_biaoqian1, mark[0]);
                    //       mainVu.setTextById(R.id.pringtingpeaple_details_biaoqian2,  mark[1]);
                    //    mainVu.setTextById(R.id.pringtingpeaple_details_biaoqian3, mark[2]);
                    break;

            }
        }*/
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void initView() {
        fragment1 = new Fragment_yins();
        fragment2 = new Fragment_shejiao();
      yins_foio=(LinearLayout)findViewById(R.id.yins_foio);
        yins_text=(TextView)findViewById(R.id.yins_text);
        yins_line=(TextView)findViewById(R.id.yins_line);
        shejiao=(LinearLayout)findViewById(R.id.shejiao);
        shejiao_text=(TextView)findViewById(R.id.shejiao_text);
        shejiao_line=(TextView)findViewById(R.id.shejiao_line);
        shejiao_text.setTextColor(this.getResources().getColor(R.color.A2A3A3));
        yins_text.setTextColor(this.getResources().getColor(R.color.explore_textcontent));
        shejiao_line.setBackgroundResource(R.color.com_facebook_button_login_silver_background_color);
        yins_line.setBackgroundResource(R.color.textcoloryellow);
/*        FragmentManager mFragmentMan = getSupportFragmentManager();
        FragmentTransaction transaction2 = mFragmentMan.beginTransaction();
        transaction2.add(R.id.vPager, fragment1).commit();*/
        getSupportFragmentManager().beginTransaction().add(R.id.vPager, fragment1).commit();
        shejiao.setOnClickListener(this);
        yins_foio.setOnClickListener(this);
        EventBus.getDefault().register(this);


    }

    @Override
    public void initData() {
        UmengUtils.getInstance().InitPlatform(this,umSocialService);
        popuwindowUtils = new PopuwindowUtils(Activity_printingPeapleDetails.this,R.layout.popuwindwo_item,false);
        BackActivity.finishActivity(this,R.id.pringtingpeaple_details_back);
        lat =  Double.parseDouble(SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT,null));
        lng =  Double.parseDouble(SharePreferenceUtils.getInstance(this, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG,null));
        Bundle bundle = getIntent().getExtras();
        String userId = null;
        if (bundle!=null)
        {
            userId =  bundle.getString("user_id");
            userType = bundle.getString("flag");
        }
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("user_id", userId);
        HttpFactory.getInstance().pringtingPeapleDetails(this,map);


    }

    @Override
    public void initListener() {

        pringtingpeaple_details_head= mainVu.getItemView(R.id.pringtingpeaple_details_head);
        //  mainVu.getItemView(R.id.pringtingpeaple_details_relayout).setOnClickListener(this);
        //   mainVu.getItemView(R.id.pringtingpeaple_details_renzheng).setOnClickListener(this);
        // mainVu.getItemView(R.id.pringtingpeaple_details_xiangce).setOnClickListener(this);
        // mainVu.getItemView(R.id.pringtingpeaple_gunzhubtn).setOnClickListener(this);
        //   mainVu.getItemView(R.id.pringtingpeaple_details_focus).setOnClickListener(this);
        //    mainVu.getItemView(R.id.pringtingpeaple_details_call).setOnClickListener(this);
        printingpeaple_share = mainVu.getItemView(R.id.printingpeaple_share);
   /*     accoun2 = mainVu.getItemView(R.id.accoun2);*/
        phone2 = mainVu.getItemView(R.id.phone2);


        printingpeaple_share.setOnClickListener(this);
        popuwindowUtils.getView(R.id.share_text).setOnClickListener(this);
        //    popuwindowUtils.getView(R.id.tofriends).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.printingpeaple_share:
                popuwindowUtils.showPopuwindow(printingpeaple_share);
                break;
        /*    case R.id.tofriends:
                AlertDialog alertDialog3 = null;
                Bitmap bitmap = ZxingUtils.getInstance().createQRImage(Activity_printingPeapleDetails.this,"http//www.baidu.com",400,400, BitmapFactory.decodeResource(getResources(), R.mipmap.login_logo), ImageUtil.getSDPath()+"touxiang.png",null);
                UmengUtils.getInstance().setShareContentFriends(Activity_printingPeapleDetails.this, umSocialService,bitmap);
                UmengUtils.getInstance().showCustomUI(Activity_printingPeapleDetails.this, umSocialService, alertDialog3);
                break;*/
            case R.id.share_text:
                AlertDialog alertDialog2 = null;
                UmengUtils.getInstance().setShareContent(this, umSocialService, HttpConstance.URL+pringTingPeapleEntry2.getData().getHead_thumb(),pringTingPeapleEntry2.getData().getDescription(),pringTingPeapleEntry2.getData().getNickname(),"http://www.baidu.com");
                UmengUtils.getInstance().showCustomUI(Activity_printingPeapleDetails.this, umSocialService, alertDialog2);
                break;
            case R.id.yins_foio:
                shejiao_text.setTextColor(this.getResources().getColor(R.color.A2A3A3));
                yins_text.setTextColor(this.getResources().getColor(R.color.explore_textcontent));
                shejiao_line.setBackgroundResource(R.color.com_facebook_button_login_silver_background_color);
                yins_line.setBackgroundResource(R.color.textcoloryellow);

                    switchContent(fragment2, fragment1);





                break;
            case R.id.shejiao:
                shejiao_text.setTextColor(this.getResources().getColor(R.color.explore_textcontent));
                yins_text.setTextColor(this.getResources().getColor(R.color.A2A3A3));
                shejiao_line.setBackgroundResource(R.color.textcoloryellow);
                yins_line.setBackgroundResource(R.color.com_facebook_button_login_silver_background_color);

                    switchContent(fragment1,fragment2);


                break;

         /*   case R.id.pringtingpeaple_details_relayout:
                Bundle bundle = new Bundle();
                bundle.putString("user_id",pringTingPeapleEntry2.getData().getUser_id());
                IntentUtils.getInstance().startToAnoterActivity(Activity_printingPeapleDetails.this,Activity_Mypublish.class,bundle);
                break;*/

          /*  case R.id.pringtingpeaple_details_renzheng:
                Bundle bundle2 = new Bundle();
                bundle2.putString("user_id",pringTingPeapleEntry2.getData().getUser_id());
                if (userType.equals(String.valueOf(1))) {
                    IntentUtils.getInstance().startToAnoterActivity(Activity_printingPeapleDetails.this, Activity_HisAuthPeaple.class, bundle2);
                }
                if (userType.equals(String.valueOf(2)))
                {
                    IntentUtils.getInstance().startToAnoterActivity(Activity_printingPeapleDetails.this, Activity_hisAuthTypeGrapher.class, bundle2);

                }
                break;*/

         /*   case R.id.pringtingpeaple_details_xiangce:
                Bundle bundle3 = new Bundle();
                bundle3.putString("user_id",pringTingPeapleEntry2.getData().getUser_id());
                bundle3.putString("flag","Activity_printingPeapleDetails");
                IntentUtils.getInstance().startToAnoterActivity(Activity_printingPeapleDetails.this,Activity_HisPhoto.class,bundle3);
                break;
*/
          /*  case R.id.pringtingpeaple_gunzhubtn:
                String token = SharePreferenceUtils.getInstance(Activity_printingPeapleDetails.this,SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null);
             if (token!=null) {
                 HashMap<String, String> map = new HashMap<String, String>();
                 map.put("token", token);
                 map.put("user_id", pringTingPeapleEntry2.getData().getUser_id());
                 map.put("user_type", userType);
                 HttpFactory.getInstance().focusPp(Activity_printingPeapleDetails.this, map);
                 IntentUtils.getInstance().startToAnoterActivity(Activity_printingPeapleDetails.this, MainActivity.class, null);
             }else{
                 IntentUtils.getInstance().startToAnoterActivity(Activity_printingPeapleDetails.this,Activity_Login.class,null);
             }
                break;*/

            /*case R.id.pringtingpeaple_details_focus:
                String token2 = SharePreferenceUtils.getInstance(Activity_printingPeapleDetails.this,SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null);
                if (token2!=null) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("token", token2);
                    map.put("user_id", pringTingPeapleEntry2.getData().getUser_id());
                    map.put("user_type", userType);
                    HttpFactory.getInstance().focusPp(Activity_printingPeapleDetails.this, map);
                    ((ImageView)mainVu.getItemView(R.id.pringtingpeaple_details_focus)).setImageResource(R.mipmap.iconffocusonofftwo);
                 //   IntentUtils.getInstance().startToAnoterActivity(Activity_printingPeapleDetails.this, MainActivity.class,null);
                }else{
                    IntentUtils.getInstance().startToAnoterActivity(Activity_printingPeapleDetails.this,Activity_Login.class,null);
                }
                break;*/

         /*   case R.id.pringtingpeaple_details_call:
                    ((ImageView)mainVu.getItemView(R.id.pringtingpeaple_details_call)).setImageResource(R.mipmap.icon_phone_on2);
                CallPhoneUtils.getInstance().callThree(pringTingPeapleEntry2.getData().getTelephone(), Activity_printingPeapleDetails.this, ((ImageView) mainVu.getItemView(R.id.pringtingpeaple_details_call)),null,0);
                break;*/
        }
    }
    public void switchContent(Fragment from, Fragment to) {

        //Fragment[] fragment =new Fragment[]{fragment1,fragment2,fragment3};

        FragmentManager mFragmentMan = getSupportFragmentManager();

        if (fragmentById != to) {
            fragmentById = to;
            FragmentTransaction transaction = mFragmentMan.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.vPager, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
