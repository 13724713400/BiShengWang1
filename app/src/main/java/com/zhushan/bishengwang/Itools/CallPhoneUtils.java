package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Entry.DataList;
import com.zhushan.bishengwang.Entry.HomePageInfoData;
import com.zhushan.bishengwang.Entry.MyFocusTypographerData;
import com.zhushan.bishengwang.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
public class CallPhoneUtils {


    private static CallPhoneUtils callPhoneUtils;
    private CallPhoneUtils(){}
    public static CallPhoneUtils getInstance() {
        if (callPhoneUtils == null) {
            synchronized (CallPhoneUtils.class) {
                if (callPhoneUtils == null) {
                    callPhoneUtils = new CallPhoneUtils();

                }

            }

        }
        return callPhoneUtils;
    }

    public void call(final String phone,final Context context,final ImageView imageView, final List<HomePageInfoData> list, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
      final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.addressdelete, null);
        TextView textView = (TextView) view.findViewById(R.id.delete_text);
        TextView cancletextView = (TextView) view.findViewById(R.id.cancel_delete);
        TextView sureTextview = (TextView) view.findViewById(R.id.sure_delete);
        cancletextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                imageView.setImageResource(R.mipmap.icon_phone_on1);
                list.get(position).setIsCall(true);
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
        sureTextview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String string_phoneNum = phone;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + string_phoneNum));
                context.startActivity(intent);
                alertDialog.dismiss();
                if (list!=null) {
                    list.get(position).setIsCall(true);
                }
                imageView.setImageResource(R.mipmap.icon_phone_on1);
            }
        });
    }


    public void callTwo(final String phone,final Context context,final ImageView imageView, final List<DataList> list, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.addressdelete, null);
        TextView textView = (TextView) view.findViewById(R.id.delete_text);
        TextView cancletextView = (TextView) view.findViewById(R.id.cancel_delete);
        TextView sureTextview = (TextView) view.findViewById(R.id.sure_delete);
        cancletextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                imageView.setImageResource(R.mipmap.icon_phone_on1);
                list.get(position).setIsCall(true);
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
        sureTextview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String string_phoneNum = phone;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + string_phoneNum));
                context.startActivity(intent);
                alertDialog.dismiss();
                list.get(position).setIsCall(true);
                imageView.setImageResource(R.mipmap.icon_phone_on1);
            }
        });
    }

    public void callThree(final String phone,final Context context,final ImageView imageView, final List<MyFocusTypographerData> datas, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.addressdelete, null);
        TextView textView = (TextView) view.findViewById(R.id.delete_text);
        TextView cancletextView = (TextView) view.findViewById(R.id.cancel_delete);
        TextView sureTextview = (TextView) view.findViewById(R.id.sure_delete);
        cancletextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                imageView.setImageResource(R.mipmap.icon_phone_on1);

            }
        });
        alertDialog.setView(view);
        alertDialog.show();
        sureTextview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String string_phoneNum = phone;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + string_phoneNum));
                context.startActivity(intent);
                alertDialog.dismiss();
                imageView.setImageResource(R.mipmap.icon_phone_on1);
            }
        });
    }


}
