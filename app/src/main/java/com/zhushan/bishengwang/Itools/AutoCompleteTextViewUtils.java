package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * Created by Administrator on 2015/12/4.
 */
public class AutoCompleteTextViewUtils {

    private static AutoCompleteTextViewUtils autoCompleteTextViewUtils;
  /*  private AutoCompleteTextViewUtils()
    {}
    public static AutoCompleteTextViewUtils getInstance()
    {
        if (autoCompleteTextViewUtils==null)
        {
            autoCompleteTextViewUtils = new AutoCompleteTextViewUtils();

        }

        return autoCompleteTextViewUtils;
    }*/
    public void saveHistory(Activity activity,AutoCompleteTextView autoCompleteTextView)
    {
        String content = autoCompleteTextView.getText().toString().trim();
        SharedPreferences sharedPreferences = activity.getSharedPreferences("search_history", Context.MODE_PRIVATE);
       String oldtext =  sharedPreferences.getString("history", "没有记录");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content+",");

        if (!oldtext.contains(content+","))
        {
           SharedPreferences.Editor editor =  sharedPreferences.edit();
            editor.putString("history",stringBuilder.toString());
            editor.commit();
            Log.i("www","添加成功");
        }


    }

    public void initAutoCompleteText(Activity activity,AutoCompleteTextView autoCompleteTextView)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("search_history", Context.MODE_PRIVATE);
        String history = sharedPreferences.getString("history", "没有记录");
        Log.i("www","历史"+history);
        String[] item = history.split(",");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity,android.R.layout.simple_dropdown_item_1line,item);
    for (int i = 0;i<item.length;i++) {
        Log.i("www", "item" +item[i]);
    }
        if (item.length>50)
        {
            String[] newArrars = new String[50];
            System.arraycopy(item,0,newArrars,0,50);
            arrayAdapter = new ArrayAdapter<String>(activity,android.R.layout.simple_dropdown_item_1line,newArrars);

        }

        autoCompleteTextView.setAdapter(arrayAdapter);




    }



}
