package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.HomePageEntry;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/16.
 */
public class HomepagePaixuUtils {

    private static HomepagePaixuUtils homepagePaixuUtils;

    private HomepagePaixuUtils(){}

    public static HomepagePaixuUtils getInstance()
    {
        if (homepagePaixuUtils==null)
        {
            homepagePaixuUtils = new HomepagePaixuUtils();

        }
        return homepagePaixuUtils;

    }

    public void initShuaiXuanPopuWindow(final PopuwindowUtils popuwindowUtilspaixu,final TextView textView1,final Activity context,final HomePageEntry homePageEntry,final HashMap<String,String> map)
    {
        popuwindowUtilspaixu.getView(R.id.homepage_zuixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(homePageEntry);
                homePageEntry.setFilter2(0);
                homePageEntry.setLng(String.valueOf(0));
                homePageEntry.setLat(String.valueOf(0));
                map.put("cate_id", String.valueOf(homePageEntry.getCate_id()));
                map.put("filter1",homePageEntry.getFilter1());
                map.put("lat",homePageEntry.getLat());
                map.put("lng",homePageEntry.getLng());
                map.put("page", String.valueOf(0));
                map.put("filter2",String.valueOf(homePageEntry.getFilter2()));
                map.put("filter3",homePageEntry.getFilter3());
                map.put("city",homePageEntry.getCity());
                HttpFactory.getInstance().HomePageInfoList(context, map);

                textView1.setText(context.getResources().getText(R.string.zuixin));
                popuwindowUtilspaixu.popuwindwodismiss();
            }
        });

        popuwindowUtilspaixu.getView(R.id.homepage_threeday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(homePageEntry);
                homePageEntry.setLng(String.valueOf(0));
                homePageEntry.setLat(String.valueOf(0));
                homePageEntry.setFilter2(3);
                map.put("cate_id", String.valueOf(homePageEntry.getCate_id()));
                map.put("filter1",homePageEntry.getFilter1());
                map.put("lat",homePageEntry.getLat());
                map.put("lng",homePageEntry.getLng());
                map.put("page", String.valueOf(0));
                map.put("filter2",String.valueOf(homePageEntry.getFilter2()));
                map.put("filter3",homePageEntry.getFilter3());
                map.put("city",homePageEntry.getCity());
                HttpFactory.getInstance().HomePageInfoList(context, map);
                textView1.setText(context.getResources().getText(R.string.tiannei));
                popuwindowUtilspaixu.popuwindwodismiss();
            }
        });

        popuwindowUtilspaixu.getView(R.id.homepage_week).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(homePageEntry);
                homePageEntry.setLng(String.valueOf(0));
                homePageEntry.setLat(String.valueOf(0));
                homePageEntry.setFilter2(7);
                map.put("cate_id", String.valueOf(homePageEntry.getCate_id()));
                map.put("filter1",homePageEntry.getFilter1());
                map.put("lat",homePageEntry.getLat());
                map.put("lng",homePageEntry.getLng());
                map.put("page", String.valueOf(0));
                map.put("filter2",String.valueOf(homePageEntry.getFilter2()));
                map.put("filter3",homePageEntry.getFilter3());

                map.put("city",homePageEntry.getCity());
                HttpFactory.getInstance().HomePageInfoList(context, map);
                textView1.setText(context.getResources().getText(R.string.yizhounei));
                popuwindowUtilspaixu.popuwindwodismiss();
            }
        });

        popuwindowUtilspaixu.getView(R.id.homepage_month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(homePageEntry);
                homePageEntry.setLng(String.valueOf(0));
                homePageEntry.setLat(String.valueOf(0));
                homePageEntry.setFilter2(30);
                map.put("cate_id", String.valueOf(homePageEntry.getCate_id()));
                map.put("filter1",homePageEntry.getFilter1());
                map.put("lat",homePageEntry.getLat());
                map.put("lng",homePageEntry.getLng());
                map.put("page", String.valueOf(0));
                map.put("filter2",String.valueOf(homePageEntry.getFilter2()));
                map.put("filter3",homePageEntry.getFilter3());
                map.put("city",homePageEntry.getCity());
                HttpFactory.getInstance().HomePageInfoList(context, map);
                textView1.setText(context.getResources().getText(R.string.yigeyuenei));
                popuwindowUtilspaixu.popuwindwodismiss();
            }
        });
    }

    public void initPaixuPopuWinDwo(final PopuwindowUtils popuwindowUtilspaixu,final TextView textView1,final Activity context,final HashMap<String,String> map,final HomePageEntry homePageEntry) {

      /*  popuwindowUtilspaixu.getView(R.id.homepage_pp_paixu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePageEntry.setIsPress(true);
                EventBus.getDefault().post(homePageEntry);
                homePageEntry.setLng(String.valueOf(0));
                homePageEntry.setLat(String.valueOf(0));
                homePageEntry.setFilter1("addtime");
                map.put("page", String.valueOf(0));
                map.put("cate_id", String.valueOf(homePageEntry.getCate_id()));
                map.put("filter1",homePageEntry.getFilter1());
                map.put("lat",homePageEntry.getLat());
                map.put("lng",homePageEntry.getLng());
                map.put("filter2",String.valueOf(homePageEntry.getFilter2()));
                map.put("filter3",homePageEntry.getFilter3());
                map.put("city",homePageEntry.getCity());
                HttpFactory.getInstance().HomePageInfoList(context, map);
                textView1.setText(context.getResources().getText(R.string.zonghepaixu));
                popuwindowUtilspaixu.popuwindwodismiss();

            }
        });*/

    /*    popuwindowUtilspaixu.getView(R.id.homepage_pp_yunajin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePageEntry.setIsPress(true);
                EventBus.getDefault().post(homePageEntry);
                homePageEntry.setFilter1("distance");
                homePageEntry.setLat(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
                homePageEntry.setLat(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG,null));
                map.put("cate_id", String.valueOf(homePageEntry.getCate_id()));
                map.put("filter1",homePageEntry.getFilter1());
                map.put("lat",homePageEntry.getLat());
                map.put("lng",homePageEntry.getLng());
                map.put("page", String.valueOf(0));
                map.put("filter2",String.valueOf(homePageEntry.getFilter2()));
                map.put("filter3",homePageEntry.getFilter3());
                map.put("city",homePageEntry.getCity());
                HttpFactory.getInstance().HomePageInfoList(context, map);
                textView1.setText(context.getResources().getText(R.string.julicongjindaoyuan));
                popuwindowUtilspaixu.popuwindwodismiss();

            }
        });*/

       /* popuwindowUtilspaixu.getView(R.id.homepage_pp_gaodi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePageEntry.setIsPress(true);
                EventBus.getDefault().post(homePageEntry);
                homePageEntry.setLng(String.valueOf(0));
                homePageEntry.setLat(String.valueOf(0));
                homePageEntry.setFilter1("point");
                map.put("cate_id", String.valueOf(homePageEntry.getCate_id()));
                map.put("filter1",homePageEntry.getFilter1());
                map.put("lat",homePageEntry.getLat());
                map.put("lng",homePageEntry.getLng());
                map.put("page", String.valueOf(0));
                map.put("filter2",String.valueOf(homePageEntry.getFilter2()));
                map.put("filter3",homePageEntry.getFilter3());
                map.put("city",homePageEntry.getCity());
                HttpFactory.getInstance().HomePageInfoList(context, map);
                textView1.setText(context.getResources().getText(R.string.huoyuezhi));
                popuwindowUtilspaixu.popuwindwodismiss();
            }
        });
*/
       /* popuwindowUtilspaixu.getView(R.id.homepage_pp_guanzhuggaodi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePageEntry.setIsPress(true);
                EventBus.getDefault().post(homePageEntry);
                homePageEntry.setLng(String.valueOf(0));
                homePageEntry.setLat(String.valueOf(0));
                homePageEntry.setFilter1("foucs");
                map.put("cate_id", String.valueOf(homePageEntry.getCate_id()));
                map.put("filter1",homePageEntry.getFilter1());
                map.put("lat",homePageEntry.getLat());
                map.put("lng",homePageEntry.getLng());
                map.put("page", String.valueOf(0));
                map.put("filter2",String.valueOf(homePageEntry.getFilter2()));
                map.put("filter3",homePageEntry.getFilter3());
                map.put("city",homePageEntry.getCity());
                HttpFactory.getInstance().HomePageInfoList(context, map);
                textView1.setText(context.getResources().getText(R.string.guanzhushu));
                popuwindowUtilspaixu.popuwindwodismiss();
            }
        });*/

      /*  popuwindowUtilspaixu.getView(R.id.homepage_pp_qiyelougou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(context.getResources().getText(R.string.zhenshitouxaing));
                popuwindowUtilspaixu.popuwindwodismiss();
            }
        });*/
    }

}
