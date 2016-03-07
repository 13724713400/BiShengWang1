package com.zhushan.bishengwang.Ipresenter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zhushan.bishengwang.Constance.ConstanceTag;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import org.json.JSONObject;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import de.greenrobot.event.EventBus;
/**
 * Created by Administrator on 2015/12/14.
 */
public class PositionPresenter {
    private Context context;
    private MainVu mainVu;
    public PositionPresenter(Context context,MainVu mainVu) {
        this.context = context;
        this.mainVu = mainVu;
      EventBus.getDefault().register(this);

    }
 /*   public void onEventMainThread(String string)
    {
        Log.i("www","presenter位置"+string);
        String responce2 = string.replace("renderReverse&&renderReverse", "").replace(")", "").replace("(", "");
        try {
            JSONObject jsonObject = new JSONObject(responce2);
            JSONObject object = jsonObject.getJSONObject("result");
            String name = object.getString("formatted_address");
            JSONObject text = object.getJSONObject("addressComponent");
            Log.i("www","nmae"+name);
            Log.i("www",((TextView)mainVu.getItemView(R.id.homepage_city))+" ");
            ((TextView)mainVu.getItemView(R.id.homepage_city)).setText("东莞");
            Toast.makeText(context, "您现在的位置"+name, Toast.LENGTH_SHORT).show();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }*/




}
