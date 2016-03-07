package com.zhushan.bishengwang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.AuthEntry;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.OkHttpUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_DirectorSetting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/11.
 */
public class Fragment_authentication_pringtingpp extends Fragment implements View.OnClickListener {

    private MainVu mainVu = new MainVu();
    private ImageView fragment_authentication_img1,fragment_authentication_img2;
    private TextView fragment_authentication_text1,fragment_authentication_shenhe,fragment_authentication_text2,fragment_authentication_biaoqian,fragment_authentication_reset,fragment_authentication_commit,fragment_authentication_biaoqiantext;
    private EditText fragment_authentication_gongsiqunacheng,fragment_authentication_address,fragment_authentication_zhiwei;
    private RelativeLayout fragment_authentication_rl;
    private boolean isFirst;
    private StringBuilder stringBuilder2;
    private File fileOne,fileTwo;
    private AlertDialogUtils alertDialogUtils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainVu.initByLayout(getActivity(), R.layout.fragment_authentication_printingpeaple);
        initView();
        initData();
        initLisenner();
       // View view = inflater.inflate(R.layout.fragment_authentication_printingpeaple,null);
        return mainVu.getView();
    }

    private void initLisenner() {


        fragment_authentication_biaoqian.setOnClickListener(this);
        fragment_authentication_reset.setOnClickListener(this);
        fragment_authentication_commit.setOnClickListener(this);
        fragment_authentication_img1.setOnClickListener(this);
        fragment_authentication_img2.setOnClickListener(this);
    }

    private void initData() {

        alertDialogUtils = new AlertDialogUtils(getActivity(),R.layout.loading);
        HashMap<String,String> map = new HashMap<String,String>();
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle!=null)
        {
            String user_id =  bundle.getString("user_id");
            map.put("user_id",user_id);
            HttpFactory.getInstance().Hiscustomeauth(getActivity(),map);
        }else {
            map.put("token", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
            HttpFactory.getInstance().Mycustomeauth(getActivity(), map);

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {

        EventBus.getDefault().register(this);
        fragment_authentication_rl =  mainVu.getItemView(R.id.fragment_authentication_rl);
        fragment_authentication_img1 = mainVu.getItemView(R.id.fragment_authentication_img1);
        fragment_authentication_img2 = mainVu.getItemView(R.id.fragment_authentication_img2);
        fragment_authentication_text1 =  mainVu.getItemView(R.id.fragment_authentication_text1);
        fragment_authentication_text2 =  mainVu.getItemView(R.id.fragment_authentication_text2);
        fragment_authentication_address =  mainVu.getItemView(R.id.fragment_authentication_address);
        fragment_authentication_biaoqian =  mainVu.getItemView(R.id.fragment_authentication_biaoqian);
        fragment_authentication_gongsiqunacheng =  mainVu.getItemView(R.id.fragment_authentication_gongsiqunacheng);
        fragment_authentication_zhiwei = mainVu.getItemView(R.id.fragment_authentication_zhiwei);
        fragment_authentication_commit = mainVu.getItemView(R.id.fragment_authentication_commit);
        fragment_authentication_reset = mainVu.getItemView(R.id.fragment_authentication_reset);
        fragment_authentication_biaoqiantext =  mainVu.getItemView(R.id.fragment_authentication_biaoqiantext);
        fragment_authentication_shenhe  = mainVu.getItemView(R.id.fragment_authentication_shenhe);

    }

    public void onEventMainThread(StringBuilder stringBuildertringBuilder) {
        stringBuilder2 = stringBuildertringBuilder;
        fragment_authentication_biaoqiantext.setText(stringBuildertringBuilder.toString());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
          CameraUtils.dialogdiss();

        if (requestCode== CameraUtils.CAPTURE1&&resultCode==getActivity().RESULT_OK)
        {
            Log.i("www","第一张图片");
            try {
               fileOne = new File(CameraUtils.getCameraBitmap(data,fragment_authentication_img1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK1&&resultCode==getActivity().RESULT_OK)
        {
            Log.i("www","第一张图片pic");
            Log.i("www","fragment_authentication_img1"+fragment_authentication_img1);
           fileOne = new File(CameraUtils.getPicBitmap(data,getActivity(),fragment_authentication_img1));

        }

        if (requestCode==CameraUtils.CAPTURE2&&resultCode==getActivity().RESULT_OK)
        {
            Log.i("www","requestCode"+requestCode+"resultCode"+resultCode);
            try {
                fileTwo = new File(CameraUtils.getCameraBitmap(data,fragment_authentication_img2));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK2&&resultCode==getActivity().RESULT_OK)
        {
            fileTwo = new File(CameraUtils.getPicBitmap(data,getActivity(),fragment_authentication_img2));

        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.fragment_authentication_img1:

                    CameraUtils.photographByFragment(getActivity(), Fragment_authentication_pringtingpp.this, CameraUtils.CAPTURE1, CameraUtils.ACTION_PICK1);

                break;

            case R.id.fragment_authentication_img2:

                CameraUtils.photographByFragment(getActivity(), Fragment_authentication_pringtingpp.this, CameraUtils.CAPTURE2, CameraUtils.ACTION_PICK2);

                break;

            case R.id.fragment_authentication_commit:
                if(!EdiTextUtils.getInstance().CheckedEmpty(fragment_authentication_zhiwei,getResources().getString(R.string.fragment_authentication_emptyzhiwei)))
                {
                    return;
                }

                if(!EdiTextUtils.getInstance().CheckedEmpty(fragment_authentication_gongsiqunacheng,getResources().getString(R.string.fragment_authentication_emptygongsi)))
                {
                    return;
                }

                if(!EdiTextUtils.getInstance().CheckedEmpty(fragment_authentication_address,getResources().getString(R.string.fragment_authentication_emptydizhi)))
                {
                    return;
                }

                if (fileOne==null||fileTwo==null)
                {
                    Toast.makeText(getActivity(),"请上传身份证正面或背面",Toast.LENGTH_SHORT).show();

                }
                HashMap<String,String> map  = new HashMap<String,String>();
                map.put("token", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
                map.put("rule",fragment_authentication_zhiwei.getText().toString().trim());
                map.put("company_name",fragment_authentication_gongsiqunacheng.getText().toString().trim());
                map.put("company_area",fragment_authentication_address.getText().toString().trim());
                if (stringBuilder2!=null) {
                    if (stringBuilder2.toString() != null) {
                        map.put("mark", stringBuilder2.toString());
                    }
                }
                ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
                Glide.with(this).load(R.mipmap.run).into(imageView);
                alertDialogUtils.showDialog(false);
                HttpFactory.getInstance().UserAuth(getActivity(),map,alertDialogUtils,new Pair<String,File>("file[0]",fileOne),new Pair<String,File>("file[1]",fileTwo));
                break;

            case R.id.fragment_authentication_reset:
                fragment_authentication_img1.setImageBitmap(null);
                fragment_authentication_img2.setImageBitmap(null);
                EdiTextUtils.getInstance().resetText(fragment_authentication_zhiwei);
                EdiTextUtils.getInstance().resetText(fragment_authentication_gongsiqunacheng);
                EdiTextUtils.getInstance().resetText(fragment_authentication_address);
                fragment_authentication_biaoqiantext.setText(null);
                break;

            case R.id.fragment_authentication_biaoqian:
                IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_DirectorSetting.class,null);
                break;

        }

    }
}
