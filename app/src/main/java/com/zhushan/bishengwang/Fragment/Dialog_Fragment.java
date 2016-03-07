package com.zhushan.bishengwang.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.zhushan.bishengwang.Itools.DialogFragmentUtils;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.ZxingUtils;
import com.zhushan.bishengwang.R;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by Administrator on 2015/12/4.
 */
public class Dialog_Fragment extends DialogFragment {
    private ImageView wode_erweima,fragment_dialog_dismiss;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_zxing,container,false);

        initView(view);
        initData();
        return view;
    }
    private void initData() {
        ZxingUtils.getInstance().createQRImage(getActivity(),"www.baidu.com",400,400, BitmapFactory.decodeResource(getResources(),R.mipmap.chuang), ImageUtil.getSDPath()+"touxiang.png",wode_erweima);
        fragment_dialog_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragmentUtils.getInstance().dissDialogFragment(getActivity(),Dialog_Fragment.this);
            }
        });
    }
    private void initView(View view) {
        wode_erweima = (ImageView) view.findViewById(R.id.wode_erweima);
        fragment_dialog_dismiss = (ImageView) view.findViewById(R.id.fragment_dialog_dismiss);

    }
}
