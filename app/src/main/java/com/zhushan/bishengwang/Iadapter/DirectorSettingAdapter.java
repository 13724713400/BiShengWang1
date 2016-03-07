package com.zhushan.bishengwang.Iadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Itools.ListviewUtils;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/12/21.
 */
public class DirectorSettingAdapter extends BaseExpandableListAdapter {


    private  List<String> parenteslist;
    private Context context;
    private Director_setting_ItemAdapter director_setting_itemAdapter;
    private  HashMap<String,List<String>> map;
    public DirectorSettingAdapter(Context context)
    {
        this.context = context;
        ininData();
    }
    private void ininData() {

       parenteslist = new ArrayList<String>();
        parenteslist.add("职位");
        parenteslist.add("印刷");
        parenteslist.add("原纸");
        parenteslist.add("油墨耗材");
        parenteslist.add("机械配件");
        parenteslist.add("包装纸箱");

        map = new HashMap<String,List<String>>();
        List<String> child = new ArrayList<String>();
        child.add("销售1");
        child.add("销售2");
        child.add("销售3");
        child.add("销售4");
        child.add("销售5");
        child.add("销售6");
        child.add("销售7");
        child.add("销售8");
        map.put("职位",child);


        List<String> child2 = new ArrayList<String>();
        child.add("销售11");
        child.add("销售21");
        child.add("销售31");
        child.add("销售41");
        child.add("销售51");
        child.add("销售61");
        child.add("销售71");
        child.add("销售81");
        map.put("印刷",child2);


        List<String> child3 = new ArrayList<String>();
        child.add("销售12");
        child.add("销售22");
        child.add("销售32");
        child.add("销售42");
        child.add("销售52");
        child.add("销售62");
        child.add("销售72");
        child.add("销售82");
        map.put("原纸",child3);


        List<String> child4 = new ArrayList<String>();
        child.add("销售14");
        child.add("销售24");
        child.add("销售34");
        child.add("销售44");
        child.add("销售54");
        child.add("销售64");
        child.add("销售74");
        child.add("销售84");
        map.put("油墨耗材",child4);


        List<String> child5 = new ArrayList<String>();
        child.add("销售15");
        child.add("销售25");
        child.add("销售35");
        child.add("销售45");
        child.add("销售55");
        child.add("销售65");
        child.add("销售75");
        child.add("销售85");
        map.put("机械配件",child5);


        List<String> child6 = new ArrayList<String>();
        child.add("销售16");
        child.add("销售26");
        child.add("销售36");
        child.add("销售46");
        child.add("销售56");
        child.add("销售66");
        child.add("销售76");
        child.add("销售86");
        map.put("包装纸箱",child6);









    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
      String key =   parenteslist.get(groupPosition);
        return map.get(key).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parenteslist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = parenteslist.get(groupPosition);
        return map.get(key);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        parentViewHold parentViewHold;
       if (convertView==null)
       { parentViewHold = new parentViewHold();
         convertView =   LayoutInflater.from(context).inflate(R.layout.director_setting_group,null);
         parentViewHold.textView = (TextView) convertView.findViewById(R.id.director_setting_group_tt);
         parentViewHold.imageView = (ImageView) convertView.findViewById(R.id.director_setting_group_img);
           convertView.setTag(parentViewHold);
       }else{
           parentViewHold = (DirectorSettingAdapter.parentViewHold) convertView.getTag();
       }
        parentViewHold.textView.setText(parenteslist.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChiledViewHold chiledViewHold;
        if (convertView==null)
        {   chiledViewHold = new ChiledViewHold();
            convertView =   LayoutInflater.from(context).inflate(R.layout.director_setting_chiled,null);
          //  chiledViewHold.textView = (TextView) convertView.findViewById(R.id.director_setting_chiled_text);
            convertView.setTag(chiledViewHold);
        }else{
            chiledViewHold = (ChiledViewHold) convertView.getTag();
        }
        String key = parenteslist.get(groupPosition);
        ArrayList<String> mapitem = (ArrayList<String>) map.get(key);
        StringBuilder stringBuilder = new StringBuilder();
        for (String t:mapitem)
        {
            Log.i("www","t"+t);
            stringBuilder.append(t);

        }
        chiledViewHold.textView.setText(stringBuilder.toString());
    //    director_setting_itemAdapter = new Director_setting_ItemAdapter(context,mapitem,R.layout.director_setting_chiled_item);
      //  chiledViewHold.gridView.setAdapter(director_setting_itemAdapter);
     //   ListviewUtils.setListViewHeightBasedOnChildren(chiledViewHold.gridView);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class parentViewHold{

        TextView textView;
        ImageView imageView;


    }

    class ChiledViewHold
    {
        GridView gridView;
        TextView textView;
    }


}
