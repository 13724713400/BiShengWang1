package com.zhushan.bishengwang.Fragment;

import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.zhushan.bishengwang.Constance.ConstanceTag;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;

import com.zhushan.bishengwang.Entry.PrintingPeapleData;
import com.zhushan.bishengwang.Entry.PrintingPeapleEntry;
import com.zhushan.bishengwang.Entry.ShuaiXuanEntry;

import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.PringtingPeapleAdapter;
import com.zhushan.bishengwang.Iadapter.PringtingPeapleGridViewAdapter;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Ifactory.HttpFactory;

import com.zhushan.bishengwang.Itools.ChangeStateUtils;
import com.zhushan.bishengwang.Itools.DisLruCacheUtils;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PeapleExpandableListviewUtils;
import com.zhushan.bishengwang.Itools.PopuwindowUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Itools.ZxingUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_province;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;


/**
 * Created by Administrator on 2015/11/28.
 * Created by Administrator on 2015/11/28.
 */
public class Fragment_entire extends Fragment implements View.OnClickListener {
    private UMSocialService umSocialService = UMServiceFactory.getUMSocialService("");
    private LinearLayout linearLayout1, linearLayout2;
    private ImageView img1, img2, img3,printing_share;
    private TextView pringtingpeaple_city,textView1, textView2;
    private RadioButton girld_txt,all_txt,man_txt;
    private RadioGroup rg;
    private RelativeLayout relativeLayout1;
    private boolean isPress;
    AlertDialog mDialog;
    private AlertDialog alertDialog;
    private List<ImageView> views = new ArrayList<ImageView>();
    private RelativeLayout relative;
    private  PopuwindowUtils popuwindowUtils,popuwindowUtilspaiixu,popuwindowUtilsshuaixuan;
    private  List<PrintingPeapleData> printingPeapleData2 = new ArrayList<PrintingPeapleData>();
    private PringtingPeapleAdapter pringtingPeapleAdapter;
    private ShuaiXuanEntry shuaiXuanEntry= new ShuaiXuanEntry();
    private StringBuilder stringBuildertwo;
    private int index;
    private boolean isCat;
    RelativeLayout jubu;
    private boolean isGridview;
    private boolean isFirst;
    private HashMap<String,String> map = new HashMap<String,String>();
    private MainVu mainVu = new MainVu();
    private boolean isPressTwo,isvisible;
    private boolean isFirstTwo;
    ViewPager viewPager;
    private Handler handler;
    Fragmnet_pringtingpeaple_list  fragmnet_pringtingpeaple_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = mainVu.initByLayout(getActivity(), R.layout.fragment_entire);
        // MainVu.getInstance().initByLayout(TBaplication.getInstance(), R.layout.fragment_printingpeaple);
     /*   configImageLoader();
        initialize();*/
        initView();
        isPressTwo = true;
        loadData();
        Log.i("www", "isPreespeaple");
        initData();
        initShareContent();
        loadData();
        return mainVu.getView();
    }


    /*    viewPager = (ViewPager)view.findViewById(R.id.vPager);
        LayoutInflater inflate = LayoutInflater.from(getActivity());
        ImageView view1 = (ImageView) inflater.inflate(R.layout.item, null);
        ImageView view2 = (ImageView) inflater.inflate(R.layout.item, null);
        ImageView view3 = (ImageView) inflater.inflate(R.layout.item, null);
        view1.setImageResource(R.mipmap.btn_logo_tmall);
        view2.setImageResource(R.mipmap.btn_arrow_down);
        view3.setImageResource(R.mipmap.btn_arrow_downtwo);
        ArrayList<ImageView> views = new ArrayList<ImageView>();
        views.add(view1);
        views.add(view2);
        views.add(view3);*/
      /*  viewPager.setAdapter(new ImageAdapter(views));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //配合Adapter的currentItem字段进行设置。
            @Override
            public void onPageSelected(int arg0) {
                handler.sendEmptyMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, arg0, 0));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            //覆写该方法实现轮播效果的暂停和恢复
            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });
        viewPager.setCurrentItem(Integer.MAX_VALUE/2);//默认在中间，使用户看不到边界
        //开始轮播效果
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);


    }
*/
    public void getPositionData(String resptring) {
        Log.i("www", "xnxi" + resptring);
    }

    private void loadData() {
        if (isPressTwo&&isvisible)
        {

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

            Log.i("www","kejianpringtingpeaple");
            //相当于Fragment的onResume
        } else {
            isvisible = false;
            //相当于Fragment的onPause
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        de.greenrobot.event.EventBus.getDefault().unregister(this);

    }

    private void initShareContent() {
        UmengUtils.getInstance().InitPlatform(getActivity(), umSocialService);
        //  UmengUtils.getInstance().setShareContent(getActivity(), TBaplication.mController, (Bitmap) DisLruCacheUtils.getInstance().getValue(getActivity(), "wodeerweima", DisLruCacheUtils.BITMAP));
      /*  new ShareAction(this)
                .setPlatform(SHARE_MEDIA.SINA)
                .setCallback(umShareListener)
                .withText("hello umeng video")
                .withTargetUrl("http://www.baidu.com")
                .withMedia(image)
                .share();*/
    }



    public void onEventMainThread(StringBuilder stringBuilder)
    {
        shuaiXuanEntry.setIndex(0);
        shuaiXuanEntry.setIsPress(true);
        shuaiXuanEntry.setLabel(stringBuilder.toString());
        de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
        map.put("page", String.valueOf(index));
        map.put("sex", shuaiXuanEntry.getSex());
        map.put("label", shuaiXuanEntry.getLabel());
        map.put("filter1", shuaiXuanEntry.getOrderField());
        HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
    }

    private void initData() {

        Bundle bundle1 =  getActivity().getIntent().getExtras();
        if (bundle1!=null) {
            if (bundle1.getString("Fragment_pringtingpeaple") != null) {
                String city = bundle1.getString("Fragment_pringtingpeaple");
                shuaiXuanEntry.setCity(city);
                Log.i("www","xuanzedecity"+city);
                pringtingpeaple_city.setText(city);
            }else{
                String city = SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY, null);
                if (city != null) {
                    String city3 = city.replace("市","");
                    shuaiXuanEntry.setCity(city3);
                    pringtingpeaple_city.setText(city3);
                }
                // pringtingpeaple_city.setText(SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY,null).replace("市",""));
                //  shuaiXuanEntry.setCity(SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY,null).replace("市",""));
            }
        }else{
            String city = SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY, null);
            if (city != null) {
                String city3 = city.replace("市","");
                shuaiXuanEntry.setCity(city3);
                pringtingpeaple_city.setText(city3);
            }
            // pringtingpeaple_city.setText(SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY,null).replace("市",""));
            //  shuaiXuanEntry.setCity(SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONCITY,null).replace("市",""));
        }

        UmengUtils.getInstance().InitPlatform(getActivity(),umSocialService);

        String token = SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null);
        if (token!=null)
        {
            map.put("token",SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
        }
        //  map.put("token",SharePreferenceUtils.getInstance(getActivity(),SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
        popuwindowUtils = new PopuwindowUtils(getActivity(),R.layout.popuwindwo_item,false);
        popuwindowUtils.getView(R.id.share_text).setVisibility(View.GONE);
       /* popuwindowUtils.getView(R.id.tofriends).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog3 = null;
                Bitmap bitmap = ZxingUtils.getInstance().createQRImage(getActivity(),"www.baidu.com",400,400, BitmapFactory.decodeResource(getResources(), R.mipmap.login_logo), ImageUtil.getSDPath()+"touxiang.png",null);
                UmengUtils.getInstance().setShareContentFriends(getActivity(), umSocialService,bitmap);
                UmengUtils.getInstance().showCustomUI(getActivity(), umSocialService, alertDialog3);
            }
        });*/
        popuwindowUtilspaiixu = new PopuwindowUtils(getActivity(),R.layout.popuwindow_paixu,true);
        popuwindowUtilsshuaixuan = new PopuwindowUtils(getActivity(), R.layout.main_shuaixuan_peaple,true);

        initshuaxuanData(popuwindowUtilsshuaixuan);
        shuaiXuanEntry.setLabel("");
        shuaiXuanEntry.setOrderField("distance");
        shuaiXuanEntry.setLat(SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
        shuaiXuanEntry.setLng(SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG, null));
        shuaiXuanEntry.setSex("");

        Fragmnet_pringtingpeaple_list fragmnet_pringtingpeaple_list = new Fragmnet_pringtingpeaple_list();
        final Bundle bundle = new Bundle();
        bundle.putSerializable("shuaiXuanEntry", shuaiXuanEntry);
        fragmnet_pringtingpeaple_list.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_pringtingpeaple_framelayout, fragmnet_pringtingpeaple_list)
                .commit();

        // popuwindowUtilspaiixu.getView(R.id.homepage_pp_paixu).setOnClickListener(this);
        //   popuwindowUtilspaiixu.getView(R.id.homepage_pp_gaodi).setOnClickListener(this);
        // popuwindowUtilspaiixu.getView(R.id.homepage_pp_guanzhuggaodi).setOnClickListener(this);
        //  popuwindowUtilspaiixu.getView(R.id.homepage_pp_yunajin).setOnClickListener(this);
        pringtingpeaple_city.setOnClickListener(this);
     /*   pringtingpeaple_girld_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isCat = true;
                shuaiXuanEntry.setIndex(0);
                shuaiXuanEntry.setIsPress(true);
                shuaiXuanEntry.setSex("女");
                de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                map.put("page", String.valueOf(index));
                map.put("sex", shuaiXuanEntry.getSex());
                map.put("label", shuaiXuanEntry.getLabel());
                map.put("filter1", shuaiXuanEntry.getOrderField());
                map.put("lat",shuaiXuanEntry.getLat());
                map.put("lng",shuaiXuanEntry.getLng());
                map.put("city", shuaiXuanEntry.getCity());
                HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
             //   ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_girld_txt, getResources().getColor(R.color.textcoloryellow), R.drawable.homepage_radiobutton_bg);
               // ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_all_txt, getResources().getColor(R.color.textcolorback), R.drawable.homepage_radiobutton_bg_defalt);
               // ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_man_txt, getResources().getColor(R.color.textcolorback), R.drawable.homepage_radiobutton_bg_defalt);

            }
        });
        pringtingpeaple_all_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCat = true;
                shuaiXuanEntry.setIndex(0);
                shuaiXuanEntry.setIsPress(true);
                shuaiXuanEntry.setSex("");
                de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                map.put("page", String.valueOf(index));
                map.put("sex", shuaiXuanEntry.getSex());
                map.put("label", shuaiXuanEntry.getLabel());
                map.put("filter1", shuaiXuanEntry.getOrderField());
                map.put("lat",shuaiXuanEntry.getLat());
                map.put("lng", shuaiXuanEntry.getLng());
                map.put("city", shuaiXuanEntry.getCity());
                HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
               // ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_all_txt, getResources().getColor(R.color.textcoloryellow), R.drawable.homepage_radiobutton_bg);
            //    ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_girld_txt, getResources().getColor(R.color.textcolorback), R.drawable.homepage_radiobutton_bg_defalt);
               // ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_man_txt, getResources().getColor(R.color.textcolorback), R.drawable.homepage_radiobutton_bg_defalt);
            }
        });
       pringtingpeaple_man_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuaiXuanEntry.setIndex(0);
                shuaiXuanEntry.setIsPress(true);
                shuaiXuanEntry.setSex("男");
                de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                map.put("page", String.valueOf(index));
                map.put("sex", shuaiXuanEntry.getSex());
                map.put("label", shuaiXuanEntry.getLabel());
                map.put("filter1", shuaiXuanEntry.getOrderField());
                map.put("lat",shuaiXuanEntry.getLat());
                map.put("lng", shuaiXuanEntry.getLng());
                map.put("city", shuaiXuanEntry.getCity());
                HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
               // ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_man_txt, getResources().getColor(R.color.textcoloryellow), R.drawable.homepage_radiobutton_bg);
               // ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_girld_txt, getResources().getColor(R.color.textcolorback), R.drawable.homepage_radiobutton_bg_defalt);
               // ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_all_txt, getResources().getColor(R.color.textcolorback), R.drawable.homepage_radiobutton_bg_defalt);


            }
        });*/
      /*  linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeStateUtils.getInstance().changeSate(textView1, getResources().getColor(R.color.textcoloryellow), img1, R.mipmap.icon_comprehensive_on);
                ChangeStateUtils.getInstance().changeSate(textView2, getResources().getColor(R.color.mypublish_textcolor), img2, R.mipmap.icon_newest_off);

                popuwindowUtilspaiixu.showPopuwindow(linearLayout1);
            }
        });*/
        Fragmnet_pringtingpeaple_list fragmnet_pringtingpeaple_lis = new Fragmnet_pringtingpeaple_list();
        ImageView fork1 =mainVu.getItemView(R.id.fork);
        Button confirm =mainVu.getItemView(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (girld_txt.isChecked()){
                    isCat = true;
                    shuaiXuanEntry.setIndex(0);
                    shuaiXuanEntry.setIsPress(true);
                    shuaiXuanEntry.setSex("女");
                    de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                    map.put("page", String.valueOf(index));
                    map.put("sex", shuaiXuanEntry.getSex());
                    map.put("label", shuaiXuanEntry.getLabel());
                    map.put("filter1", shuaiXuanEntry.getOrderField());
                    map.put("lat",shuaiXuanEntry.getLat());
                    map.put("lng",shuaiXuanEntry.getLng());
                    map.put("city", shuaiXuanEntry.getCity());
                    HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
                }if(all_txt.isChecked()){
                    isCat = true;
                    shuaiXuanEntry.setIndex(0);
                    shuaiXuanEntry.setIsPress(true);
                    shuaiXuanEntry.setSex("");
                    de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                    map.put("page", String.valueOf(index));
                    map.put("sex", shuaiXuanEntry.getSex());
                    map.put("label", shuaiXuanEntry.getLabel());
                    map.put("filter1", shuaiXuanEntry.getOrderField());
                    map.put("lat",shuaiXuanEntry.getLat());
                    map.put("lng", shuaiXuanEntry.getLng());
                    map.put("city", shuaiXuanEntry.getCity());
                    HttpFactory.getInstance().PrintingPeaple(getActivity(), map);

                }if(man_txt.isChecked()){
                    shuaiXuanEntry.setIndex(0);
                    shuaiXuanEntry.setIsPress(true);
                    shuaiXuanEntry.setSex("男");
                    de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                    map.put("page", String.valueOf(index));
                    map.put("sex", shuaiXuanEntry.getSex());
                    map.put("label", shuaiXuanEntry.getLabel());
                    map.put("filter1", shuaiXuanEntry.getOrderField());
                    map.put("lat",shuaiXuanEntry.getLat());
                    map.put("lng", shuaiXuanEntry.getLng());
                    map.put("city", shuaiXuanEntry.getCity());
                    HttpFactory.getInstance().PrintingPeaple(getActivity(), map);

                }
                jubu.setVisibility(View.GONE);

            }
        });
        fork1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jubu.setVisibility(View.GONE);
            }
        });

        //筛选点击事件
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // popuwindowUtilspaiixu.showPopuwindow(
                // linearLayout1)
                Toast.makeText(getActivity(), "筛选点击事件", Toast.LENGTH_SHORT).show();
                jubu.setVisibility(View.VISIBLE);
                jubu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jubu.setVisibility(View.GONE);
                    }
                });

            }
        });



        //  ChangeStateUtils.getInstance().changeTextSate(pringtingpeaple_all_txt, getResources().getColor(R.color.textcoloryellow), R.drawable.homepage_radiobutton_bg);

        relativeLayout1.setOnClickListener(this);
        printing_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuwindowUtils.showPopuwindow(printing_share);
            }
        });
    }

    private void initshuaxuanData(PopuwindowUtils popuwindowUtilsshuaixuan) {
        PeapleExpandableListviewUtils expandableListviewUtils = new PeapleExpandableListviewUtils();
        //筛选中间文字
        //expandableListviewUtils.initDataHomepage((ExpandableListView) popuwindowUtilsshuaixuan.getView(R.id.director_shuaixuan_peaple), getActivity(), (Button) popuwindowUtilsshuaixuan.getView(R.id.director_shuaixuan_peaplesure), popuwindowUtilsshuaixuan);

    }

    private void initView() {

        girld_txt=mainVu.getItemView(R.id.girld_txt);
        all_txt=mainVu.getItemView(R.id.all_txt);
        man_txt=mainVu.getItemView(R.id.man_txt);


        de.greenrobot.event.EventBus.getDefault().register(this);
        jubu = mainVu.getItemView(R.id.jubu);
        linearLayout1 = mainVu.getItemView(R.id.pringtingpeaple_paixu);
        textView1 = mainVu.getItemView(R.id.pringtingpeaple_paixu_txt);
        //  img1 = mainVu.getItemView(R.id.pringtingpeaple_paixu_img);
        linearLayout2 = mainVu.getItemView(R.id.pringtingpeaple_select);
        textView2 = mainVu.getItemView(R.id.pringtingpeaple_select_txt);
        img2 = mainVu.getItemView(R.id.pringtingpeaple_select_img);
        relativeLayout1 = mainVu.getItemView(R.id.pringtingpeaple_pailie);
        img3 = mainVu.getItemView(R.id.pringtingpeaple_pailie_img);
        // pringtingpeaple_girld_txt = mainVu.getItemView(R.id.pringtingpeaple_girld_txt);
        // pringtingpeaple_all_txt = mainVu.getItemView(R.id.pringtingpeaple_all_txt);
        // pringtingpeaple_man_txt = mainVu.getItemView(R.id.pringtingpeaple_man_txt);
        printing_share = mainVu.getItemView(R.id.printing_share);
        pringtingpeaple_city = mainVu.getItemView(R.id.pringtingpeaple_city);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.pringtingpeaple_city:
                Bundle bundle2 = new Bundle();
                bundle2.putString("flag","Fragment_pringtingpeaple");
                IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_province.class,bundle2);
                break;

            case R.id.pringtingpeaple_pailie:
                if (!isPress) {
                    isPress = !isPress;
                    Fragmnet_pringtingpeaple_Gridview Fragmnet_pringtingpeaple_gridview = new Fragmnet_pringtingpeaple_Gridview();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("shuaiXuanEntry", shuaiXuanEntry);
                    Fragmnet_pringtingpeaple_gridview.setArguments(bundle);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_pringtingpeaple_framelayout, Fragmnet_pringtingpeaple_gridview)
                            .commit();
                    ChangeStateUtils.getInstance().changeSate(null, 0, img3, R.mipmap.list1);
                } else {
                    isPress = !isPress;
                    Fragmnet_pringtingpeaple_list fragmnet_pringtingpeaple_list = new Fragmnet_pringtingpeaple_list();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("shuaiXuanEntry", shuaiXuanEntry);
                    fragmnet_pringtingpeaple_list.setArguments(bundle);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_pringtingpeaple_framelayout, fragmnet_pringtingpeaple_list)
                            .commit();
                    ChangeStateUtils.getInstance().changeSate(null, 0, img3, R.mipmap.list2);
                }
                break;
/*

            case R.id.homepage_pp_paixu:
                isCat = true;
                shuaiXuanEntry.setIndex(0);
                shuaiXuanEntry.setIsPress(true);
                shuaiXuanEntry.setLng("0");
                shuaiXuanEntry.setLat("0");
                de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                shuaiXuanEntry.setOrderField("addtime");
                map.put("page", String.valueOf(index));
                map.put("sex", shuaiXuanEntry.getSex());
                map.put("label", shuaiXuanEntry.getLabel());
                map.put("filter1", shuaiXuanEntry.getOrderField());
                map.put("lat", shuaiXuanEntry.getLat());
                map.put("lng", shuaiXuanEntry.getLng());
                map.put("city",shuaiXuanEntry.getCity());
                HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
                textView1.setText(getResources().getText(R.string.zonghepaixu));
                popuwindowUtilspaiixu.popuwindwodismiss();
                break;
*/


      /*      case R.id.homepage_pp_gaodi:
                isCat = true;
                shuaiXuanEntry.setLng("0");
                shuaiXuanEntry.setLat("0");
                shuaiXuanEntry.setIndex(0);
                shuaiXuanEntry.setIsPress(true);
                de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                shuaiXuanEntry.setOrderField("point");
                map.put("page", String.valueOf(index));
                map.put("sex", shuaiXuanEntry.getSex());
                map.put("label", shuaiXuanEntry.getLabel());
                map.put("filter1", shuaiXuanEntry.getOrderField());
                map.put("lat", shuaiXuanEntry.getLat());
                map.put("lng", shuaiXuanEntry.getLng());
                map.put("city",shuaiXuanEntry.getCity());
                HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
                textView1.setText(getResources().getText(R.string.huoyuezhi));
                popuwindowUtilspaiixu.popuwindwodismiss();
                break;*/

       /*     case R.id.homepage_pp_guanzhuggaodi:
                isCat = true;
                shuaiXuanEntry.setLng("0");
                shuaiXuanEntry.setLat("0");
                shuaiXuanEntry.setIndex(0);
                shuaiXuanEntry.setIsPress(true);
                de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                shuaiXuanEntry.setOrderField("notice_number");
                map.put("page", String.valueOf(index));
                map.put("sex", shuaiXuanEntry.getSex());
                map.put("label", shuaiXuanEntry.getLabel());
                map.put("filter1", shuaiXuanEntry.getOrderField());
                map.put("lat", shuaiXuanEntry.getLat());
                map.put("lng", shuaiXuanEntry.getLng());
                map.put("city",shuaiXuanEntry.getCity());
                HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
                textView1.setText(getResources().getText(R.string.guanzhushu));
                popuwindowUtilspaiixu.popuwindwodismiss();
                break;
*/

          /*  case R.id.homepage_pp_yunajin:
                isCat = true;
                shuaiXuanEntry.setLat(SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
                shuaiXuanEntry.setLng(SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG, null));
                shuaiXuanEntry.setIndex(0);
                shuaiXuanEntry.setIsPress(true);
                de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                shuaiXuanEntry.setOrderField("distance");
                map.put("page", String.valueOf(index));
                map.put("sex", shuaiXuanEntry.getSex());
                map.put("label", shuaiXuanEntry.getLabel());
                map.put("filter1", shuaiXuanEntry.getOrderField());
                map.put("lat", shuaiXuanEntry.getLat());
                map.put("lng", shuaiXuanEntry.getLng());
                map.put("city",shuaiXuanEntry.getCity());
                HttpFactory.getInstance().PrintingPeaple(getActivity(), map);
                textView1.setText(getResources().getText(R.string.julicongjindaoyuan));
                popuwindowUtilspaiixu.popuwindwodismiss();
                break;*/
        }
    }

  /*  @SuppressLint("NewApi")
    private void initialize() {

        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);

        for(int i = 0; i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i );
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                Toast.makeText(MainActivity.this,
                        "position-->" + info.getContent(), Toast.LENGTH_SHORT)
                        .show();
            }

        }

    };

    *//**
     * 配置ImageLoder
     *//*
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
*/
}