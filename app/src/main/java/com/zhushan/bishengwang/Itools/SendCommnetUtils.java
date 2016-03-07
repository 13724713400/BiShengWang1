package com.zhushan.bishengwang.Itools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.R;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/1/8.
 */
public class SendCommnetUtils {


    public static SendCommnetUtils sendCommnetUtils;
    private SendCommnetUtils(){}
    public static SendCommnetUtils getInstance()
    {
        if (sendCommnetUtils==null)
        {
            synchronized (SendCommnetUtils.class)
            {
                if (sendCommnetUtils==null)
                {
                    sendCommnetUtils = new SendCommnetUtils();

                }

            }

        }

        return sendCommnetUtils;
    }

    public void showMessage(final Context context,final HashMap<String,String> map)
    {
      final  Dialog alertDialog = new Dialog(context, R.style.my_dialog);
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.message_dialog, null);
       final  EditText editText = (EditText) view.findViewById(R.id.message_dialog_et);
        TextView message_dialog_cancle = (TextView) view.findViewById(R.id.message_dialog_cancle);
        TextView message_dialog_Send = (TextView) view.findViewById(R.id.message_dialog_Send);
        message_dialog_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (!EdiTextUtils.getInstance().CheckedEmpty(editText,context.getResources().getString(R.string.empty_message)))
               {
                   return;
               }
                map.put("comment",editText.getText().toString().trim());
                //HttpFactory.getInstance().SendMessage(context,map);

            }
        });
        message_dialog_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setContentView(view);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
    }

}
