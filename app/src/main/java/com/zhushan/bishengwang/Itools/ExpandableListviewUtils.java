
package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.zhushan.bishengwang.Constance.ConstanceTag;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.DirectorSettingData;
import com.zhushan.bishengwang.Entry.DirectorSettingEntry;
import com.zhushan.bishengwang.Entry.HomePageEntry;
import com.zhushan.bishengwang.Entry.HomePageInfoData;
import com.zhushan.bishengwang.Entry.HomepageDirectorSettingEntry;
import com.zhushan.bishengwang.Entry.Label_list;
import com.zhushan.bishengwang.Iadapter.BaseEpandable;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ExpandableListviewUtils{

    private PopuwindowUtils popuwindowUtils;
    private HomepageDirectorSettingEntry directorSettingDataTwo = null;
    private int cateId;
    public void initDataHomepage(ExpandableListView expandableListView,Activity context,Button director_setting_sure,PopuwindowUtils popuwindowUtils,HomePageEntry homePageEntry,HashMap<String,String> map2)
    {
        EventBus.getDefault().register(this);
        this.popuwindowUtils = popuwindowUtils;
        HashMap<String,String> map = new HashMap<String,String>();
      //  String token =  SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null);
        HttpFactory.getInstance().HomepageDirectorSetting(expandableListView, context, map);
        initShuaixuanView(popuwindowUtils, context);
        initListener(director_setting_sure, homePageEntry, map2, context);
    }

    private void initShuaixuanView(PopuwindowUtils popuwindowUtilshuaixuan,Activity context) {
        ImageView director_shuaixuan_shuai =  popuwindowUtilshuaixuan.getView(R.id.director_shuaixuan_shuai);
        ImageView director_shuaixuan_qiu =  popuwindowUtilshuaixuan.getView(R.id.director_shuaixuan_qiu);
        ImageView director_shuaixuan_jiu =  popuwindowUtilshuaixuan.getView(R.id.director_shuaixuan_jiu);
        ImageView director_shuaixuan_zhi =  popuwindowUtilshuaixuan.getView(R.id.director_shuaixuan_zhi);

      ImageViewCheckUtils.getInstance().ShuaiIscheck(context,director_shuaixuan_shuai,director_shuaixuan_qiu,director_shuaixuan_jiu,director_shuaixuan_zhi);
       ImageViewCheckUtils.getInstance().qiuIscheck(context,director_shuaixuan_shuai,director_shuaixuan_qiu,director_shuaixuan_jiu,director_shuaixuan_zhi);
        ImageViewCheckUtils.getInstance().JiuIscheck(context, director_shuaixuan_shuai, director_shuaixuan_qiu, director_shuaixuan_jiu, director_shuaixuan_zhi);
       ImageViewCheckUtils.getInstance().ZhiIscheck(context, director_shuaixuan_shuai, director_shuaixuan_qiu, director_shuaixuan_jiu, director_shuaixuan_zhi);

    }


    public void onEventMainThread(HomepageDirectorSettingEntry directorSettingData)
    {
        directorSettingDataTwo =  directorSettingData;
    }
    public void initListener(Button director_setting_sure,final HomePageEntry homePageEntry,final HashMap<String,String> map,final Activity context) {

        director_setting_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder stringBuilder = new StringBuilder();

                List<DirectorSettingData>  directorSetting = directorSettingDataTwo.getData();

                for (DirectorSettingData directorSettingData:directorSetting)
                {
                    for (Label_list label_list:directorSettingData.getLabel_list())
                    {
                        if (label_list.ischeck())
                        {
                            label_list.getLabel_name();
                            stringBuilder.append(label_list.getLabel_name()+",");

                        }
                    }
                }
                homePageEntry.setIsPress(true);
                EventBus.getDefault().post(homePageEntry);
                homePageEntry.setIsCkeck(true);
                homePageEntry.setCate_id(SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).getSharePreferenceInt(SharePreferenceConstance.CATEID,0));
                homePageEntry.setFilter3(stringBuilder.toString());
                Log.i("www","cateid"+homePageEntry.getCate_id());
                map.put("cate_id", String.valueOf(homePageEntry.getCate_id()));
                map.put("filter1",homePageEntry.getFilter1());
                map.put("lat",homePageEntry.getLat());
                map.put("lng",homePageEntry.getLng());
                map.put("page",String.valueOf(0));
                map.put("filter2",String.valueOf(homePageEntry.getFilter2()));
                map.put("filter3",homePageEntry.getFilter3());
                map.put("city",homePageEntry.getCity());
                HttpFactory.getInstance().HomePageInfoList(context, map);
                Log.i("www", "选择的标签" + stringBuilder.toString());
                popuwindowUtils.popuwindwodismiss();

            }
        });
    }




}
