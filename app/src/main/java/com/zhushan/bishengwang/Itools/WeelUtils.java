package com.zhushan.bishengwang.Itools;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhushan.bishengwang.Iselfview.WheelView;
import com.zhushan.bishengwang.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/16.
 */
public class WeelUtils {
    private static  WeelUtils weelUtils;
    private WeelUtils(){}
    public static WeelUtils getInstance()
    {
        if (weelUtils==null)
        {
            weelUtils = new WeelUtils();

        }

        return weelUtils;
    }
    public   void showWeelView(final Context context,final TextView textView) {
    final View view = LayoutInflater.from(context).inflate(R.layout.cooker_dishstyles, null);
    final Button cooker_dishstyle_sure = (Button) view.findViewById(R.id.cooker_dishstyle_sure);
    WheelView wv = (WheelView) view.findViewById(R.id.Cooker_OptionalList_wheelView);
        AutoUtils.auto(wv);
    wv.setOffset(2);
    final List<String> list = new ArrayList<String>();
        list.add("九成");
        list.add("八成");
        list.add("七成");
        list.add("六成");
        list.add("五成以下");
    wv.setItems(list);
    wv.setSeletion(3);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final  AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);
        alertDialog.show();
    wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
        @Override
        public void onSelected(final int selectedIndex, final String item) {

            cooker_dishstyle_sure.setOnClickListener(new View.OnClickListener() {

                                                         @Override
                                                         public void onClick(View v) {
                                                             textView.setText(list.get(selectedIndex - 2));
                                                             alertDialog.dismiss();

                                                         }

                                                     }

            );
        }
    });

}
}
