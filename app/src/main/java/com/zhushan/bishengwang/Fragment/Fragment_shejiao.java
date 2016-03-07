package com.zhushan.bishengwang.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.bumptech.glide.Glide;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.PringTingPeapleEntry;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.GlideCircleTransform;
import com.zhushan.bishengwang.Itools.PopuwindowUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class Fragment_shejiao extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    View view;

private TextView shejiao_text_personality,shejiao_text_birthday,shejiao_text_constellation,shejiao_text_profession,shejiao_Text_region,
        shejiao_text_hometown,shejiao_text_emotion,shejiao_text_hobbies,shejiao_text_regdate;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = mainVu.initByLayout(getActivity(), R.layout.fragment_shejiao);

        return view;
    }


    public void iniView(){

        shejiao_text_personality=(TextView)view.findViewById(R.id.shejiao_text_personality);
        shejiao_text_birthday=(TextView) view.findViewById(R.id.shejiao_text_birthday);
        shejiao_text_constellation=(TextView) view.findViewById(R.id.shejiao_text_constellation);
        shejiao_text_profession=(TextView) view.findViewById(R.id.shejiao_text_profession);
        shejiao_Text_region=(TextView) view.findViewById(R.id.shejiao_Text_region);
        shejiao_text_hometown=(TextView) view.findViewById(R.id.shejiao_text_hometown);
        shejiao_text_emotion=(TextView) view.findViewById(R.id.shejiao_text_emotion);
        shejiao_text_hobbies=(TextView) view.findViewById(R.id.shejiao_text_hobbies);
        shejiao_text_regdate=(TextView) view.findViewById(R.id.shejiao_text_regdate);
    }
        public void inidata(){
            mainVu
                    .setTextById(R.id.shejiao_text_personality,pringTingPeapleEntry2.getData().getNickname());
    }

    public void onEventMainThread(PringTingPeapleEntry pringTingPeapleEntry)
    {
        pringTingPeapleEntry2  = pringTingPeapleEntry;
       /* if (pringTingPeapleEntry.getData().getHead_thumb().contains("Uploads")) {

            Glide.with(this)
                    .load(HttpConstance.URL + pringTingPeapleEntry.getData().getHead_thumb())
                    .transform(new GlideCircleTransform(get)).placeholder(R.mipmap.logotwo).into(pringtingpeaple_details_head);
            // mainVu.setImageUrlById(R.id.pringtingpeaple_details_head, this, HttpConstance.URL + pringTingPeapleEntry.getData().getHead_thumb());
        }else{
            Glide.with(this)
                    .load(pringTingPeapleEntry.getData().getHead_thumb())
                    .transform(new GlideCircleTransform(this)).placeholder(R.mipmap.logotwo).into(pringtingpeaple_details_head);

            // mainVu.setImageUrlById(R.id.pringtingpeaple_details_head, this,  pringTingPeapleEntry.getData().getHead_thumb());
        }*/
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

       /* switch (pringTingPeapleEntry.getData().getSex())
        {
            case "男":
                ((TextView)mainVu.getItemView(R.id.pringtingpeaple_details_femail)).setText(pringTingPeapleEntry.getData().getNickname());
                ((ImageView)mainVu.getItemView(R.id.pringtingpeaple_details_sex)).setImageResource(R.mipmap.icon_male);
                break;
            case "女":
                ((TextView)mainVu.getItemView(R.id.pringtingpeaple_details_femail)).setText(pringTingPeapleEntry.getData().getNickname());
                ((ImageView)mainVu.getItemView(R.id.pringtingpeaple_details_sex)).setImageResource(R.mipmap.icon_female);
                break;

        }*/

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
                .setTextById(R.id.shejiao_text_personality,pringTingPeapleEntry.getData().getNickname());
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
                    break;*/


        }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void initData() {
  Bundle bundle = getActivity().getIntent().getExtras();
        String userId = null;
        if (bundle!=null)
        {
            userId =  bundle.getString("user_id");
            userType = bundle.getString("flag");
        }
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("user_id", userId);
        HttpFactory.getInstance().pringtingPeapleDetails(getActivity(),map);
    }
}
