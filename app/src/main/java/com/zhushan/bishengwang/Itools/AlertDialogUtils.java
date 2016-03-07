package com.zhushan.bishengwang.Itools;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.zhushan.bishengwang.R;

/**
 * Created by Administrator on 2015/11/30.
 */
public class AlertDialogUtils {

   // public static AlertDialogUtils alertDialogUtils;
    private View convertView;
    private SparseArray<View> views;
    private  AlertDialog alertDialog;
    private Context context;
    private int layoutId;
    public AlertDialogUtils(Context context,int layoutId)
    {
        this.context = context;
        this.layoutId = layoutId;
        views = new SparseArray<View>();
        convertView= LayoutInflater.from(context).inflate(layoutId,null);
    }
    public void showDialog(boolean isView)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alertDialog  = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        if(isView)
        {
            alertDialog.setView(convertView);

        }else {
            alertDialog.show();
            alertDialog.setContentView(convertView);
        }
    }



    public <T extends View> T getView(int id){
        View view = views.get(id);
        if (view ==null) {
            view = convertView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }


    public void dissView()
    {
        alertDialog.dismiss();

    }
    public void showdialg()
    {
        alertDialog.show();
    }

    public boolean isShowing()
    {
        return  alertDialog.isShowing();
    }


}
