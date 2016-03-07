
package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.DirectorSettingData;
import com.zhushan.bishengwang.Entry.Label_list;
import com.zhushan.bishengwang.Entry.PrintingPeapleDirectorSettingEntry;
import com.zhushan.bishengwang.Entry.TypePepleDirectorSettingEntry;
import com.zhushan.bishengwang.Ifactory.HttpFactory;

import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class PeapleExpandableListviewUtils {

    private PopuwindowUtils popuwindowUtils;
    private PrintingPeapleDirectorSettingEntry directorSettingDataTwo = null;

    public void initDataHomepage(ExpandableListView expandableListView,Activity context,Button director_setting_sure,PopuwindowUtils popuwindowUtils)
    {
        EventBus.getDefault().register(this);
        this.popuwindowUtils = popuwindowUtils;
        HashMap<String,String> map = new HashMap<String,String>();
        HttpFactory.getInstance().PrintingPeapleDirectorSetting(expandableListView, context, map);
        initListener(director_setting_sure);
    }
    public void onEventMainThread(PrintingPeapleDirectorSettingEntry directorSettingData)
    {
        directorSettingDataTwo =  directorSettingData;
    }
    public void initListener(Button director_setting_sure) {
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
                Log.i("www","选择的标签"+stringBuilder.toString());
                popuwindowUtils.popuwindwodismiss();
                EventBus.getDefault().post(stringBuilder);
            }
        });
    }




}
