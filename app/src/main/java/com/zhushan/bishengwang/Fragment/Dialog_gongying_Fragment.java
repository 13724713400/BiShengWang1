package com.zhushan.bishengwang.Fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhushan.bishengwang.Itools.DialogFragmentUtils;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.ZxingUtils;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_certificatedinfomation;

/**
 * Created by Administrator on 2015/12/4.
 */
public class Dialog_gongying_Fragment extends DialogFragment implements View.OnClickListener{
    private ImageView wode_erweima, fragment_dialog_gongying_dismiss,fragment_dialog_img;
    private TextView goto_authentication,fragment_dialog_content;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_gongying, container, false);
        initView(view);
        initData();
        initListenner();
        return view;
    }
    private void initListenner() {
        fragment_dialog_gongying_dismiss.setOnClickListener(this);
        goto_authentication.setOnClickListener(this);
    }
    private void initView(View view) {
        fragment_dialog_gongying_dismiss = (ImageView) view.findViewById(R.id.fragment_dialog_gongying_dismiss);
        goto_authentication = (TextView) view.findViewById(R.id.goto_authentication);
        fragment_dialog_content = (TextView) view.findViewById(R.id.fragment_dialog_content);
        fragment_dialog_img = (ImageView) view.findViewById(R.id.fragment_dialog_img);

    }

    private void initData() {
        fragment_dialog_content.setText(getResources().getText(R.string.fragment_dialog_gongying_content));
        fragment_dialog_img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.purple));
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fragment_dialog_gongying_dismiss:
                DialogFragmentUtils.getInstance().dissDialogFragment(getActivity(),Dialog_gongying_Fragment.this);
                break;

            case R.id.goto_authentication:
                IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_certificatedinfomation.class,null);
              //  DialogFragmentUtils.getInstance().dissDialogFragment(getActivity(),Dialog_gongying_Fragment.this);
                break;

        }
    }
}