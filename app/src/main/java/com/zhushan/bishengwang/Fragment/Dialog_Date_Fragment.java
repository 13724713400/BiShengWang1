package com.zhushan.bishengwang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.zhushan.bishengwang.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/12/25.
 */
public class Dialog_Date_Fragment extends DialogFragment implements View.OnClickListener{

    private Button dialog_date_cancle,dialog_date_sure;
    private DatePicker datePicker;
    private GetDate getDate;

    public void setGetDate(GetDate getDate) {
        this.getDate = getDate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.dialog_date,container,false);
        initView(view);
        initData();
        initListenner();
        return view;
    }

    private void initView(View view) {
        dialog_date_cancle = (Button) view.findViewById(R.id.dialog_date_cancle);
        dialog_date_sure = (Button) view.findViewById(R.id.dialog_date_sure);
        datePicker = (DatePicker) view.findViewById(R.id.dialog_date);
    }
    private void initData() {
       Calendar calendar =  Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {

                dialog_date_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dialog_Date_Fragment.this.dismiss();
                        String date;
                        if ((monthOfYear + 1)>10) {
                             date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        }else{
                             date = year + "-" + ("0"+(monthOfYear + 1)) + "-" + dayOfMonth;
                        }
                        getDate.getDate(date);
                    }
                });

            }
        });
    }

    private void initListenner() {
        dialog_date_cancle.setOnClickListener(this);
        dialog_date_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dialog_date_cancle:
                    Dialog_Date_Fragment.this.dismiss();
                break;
        }
    }

    public interface GetDate
    {
        void getDate(String date);
    }



}
