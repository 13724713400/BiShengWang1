
package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.DirectorSettingData;
import com.zhushan.bishengwang.Entry.HomepageDirectorSettingEntry;
import com.zhushan.bishengwang.Entry.Label_list;
import com.zhushan.bishengwang.Entry.TypePepleDirectorSettingEntry;
import com.zhushan.bishengwang.Entry.TypographerEntry;
import com.zhushan.bishengwang.Ifactory.HttpFactory;

import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class TypeExpandableListviewUtils {
    private PopuwindowUtils popuwindowUtils;
    private TypePepleDirectorSettingEntry directorSettingDataTwo = null;
    public void initDataHomepage(ExpandableListView expandableListView,Activity context,Button director_setting_sure,PopuwindowUtils popuwindowUtils,TypographerEntry typographerEntry,HashMap<String,String> map2)
    {
        EventBus.getDefault().register(this);
        this.popuwindowUtils = popuwindowUtils;
        HashMap<String,String> map = new HashMap<String,String>();
        HttpFactory.getInstance().TypePeapleDirectorSetting(expandableListView, context, map);
        initListener(director_setting_sure,typographerEntry,map2, context);
    }
    public void onEventMainThread(TypePepleDirectorSettingEntry directorSettingData)
    {
        directorSettingDataTwo =  directorSettingData;
    }
    public void initListener(Button director_setting_sure,final TypographerEntry typographerEntry,final HashMap<String,String> map, final Activity context) {
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
                org.simple.eventbus.EventBus.getDefault().post(typographerEntry);
                typographerEntry.setFilter3(stringBuilder.toString());
                typographerEntry.setLng(String.valueOf(0));
                typographerEntry.setLat(String.valueOf(0));
                typographerEntry.setIsCheck(true);
                typographerEntry.setIsPress(true);
                map.put("page", String.valueOf(0));
                map.put("filter1",typographerEntry.getFilter1());
                map.put("lat",typographerEntry.getLat());
                map.put("lng",typographerEntry.getLng());
                map.put("filter3",typographerEntry.getFilter3());
                map.put("city",typographerEntry.getCity());
                HttpFactory.getInstance().TypographerFragment(context, map);
                popuwindowUtils.popuwindwodismiss();

            }
        });
    }




}
