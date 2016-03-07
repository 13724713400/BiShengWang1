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
import com.zhushan.bishengwang.Entry.DirectorSettingData;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.DateUtils;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Itools.FTPUtils;
import com.zhushan.bishengwang.Itools.FTPUtils.UploadSuccess;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PositionMySeft;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_DirectorSetting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/11.
 */
public class Fragment_authentication_typographer extends Fragment implements View.OnClickListener,UploadSuccess {

    private MainVu mainVu = new MainVu();
    private RelativeLayout fragment_authentication_typegrapher_rltwo;
    private PositionMySeft positionMySeft;
    private StringBuilder stringBuildertwo;
    private FTPUtils ftpUtils;
    private String yingyepath;
    private AlertDialogUtils alertDialogUtils;
    private File fileOne,fileTwo,fileThree,fileFore;
    private ImageView fragment_authentication_typegrapher_img3,fragment_authentication_typegrapher_img4,fragment_authentication_typegrapher_img5,fragment_authentication_typegrapher_yingyezhizhao;
    private TextView fragment_authentication_typegrapher_text3,fragment_authentication_typegrapher_shenhe,fragment_authentication_typegrapher_biaoqian,fragment_authentication_typegrapher_biaoqiantwo,fragment_authentication_typegrapher_reset,fragment_authentication_typegrapher_submit,fragment_authentication_typegrapher_biaoqiantext;
    private EditText fragment_authentication_typegrapher_address,fragment_authentication_typegrapher_jianjie,fragment_authentication_typegrapher_quhao,fragment_authentication_typegrapher_zhiwei,fragment_authentication_typegrapher_gongsiquanceng;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      // View view =  inflater.inflate(R.layout.fragment_authentication_typographer,null);
        mainVu.initByLayout(getActivity(),R.layout.fragment_authentication_typographer);
        initView();
        initData();
        initListenner();
        return mainVu.getView();
    }


    public void onEventMainThread(AuthEntry authEntry)
    {
        if (authEntry.getData().getCustomer_data()!=null)
        {
            switch (Integer.parseInt(authEntry.getData().getCustomer_data().getStatus())) {

                case 1:
                    fragment_authentication_typegrapher_yingyezhizhao.setClickable(false);
                    fragment_authentication_typegrapher_rltwo.setClickable(false);
                    fragment_authentication_typegrapher_gongsiquanceng.setEnabled(false);
                    fragment_authentication_typegrapher_address.setEnabled(false);
                    fragment_authentication_typegrapher_jianjie.setEnabled(false);
                    fragment_authentication_typegrapher_quhao.setEnabled(false);
                    fragment_authentication_typegrapher_reset.setClickable(false);
                    fragment_authentication_typegrapher_submit.setClickable(false);
                    fragment_authentication_typegrapher_biaoqian.setClickable(false);
                    fragment_authentication_typegrapher_img3.setClickable(false);
                    fragment_authentication_typegrapher_img4.setClickable(false);
                    fragment_authentication_typegrapher_img5.setClickable(false);
                    mainVu.setImageUrl(fragment_authentication_typegrapher_yingyezhizhao, getActivity(), HttpConstance.URL + authEntry.getData().getCustomer_data().getImg_thumb())
                            .setImageUrl(fragment_authentication_typegrapher_img3,getActivity(),HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(0).getImg_thumb())
                            .setImageUrl(fragment_authentication_typegrapher_img4,getActivity(),HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(1).getImg_thumb())
                            .setImageUrl(fragment_authentication_typegrapher_img5,getActivity(),HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(2).getImg_thumb());
                    mainVu.setText(fragment_authentication_typegrapher_gongsiquanceng,authEntry.getData().getCustomer_data().getCompany_name())
                            .setText(fragment_authentication_typegrapher_address,authEntry.getData().getCustomer_data().getCompany_area())
                            .setText(fragment_authentication_typegrapher_jianjie,authEntry.getData().getCustomer_data().getCompany_description())
                            .setText(fragment_authentication_typegrapher_quhao,authEntry.getData().getCustomer_data().getCompany_telephone())
                            .setText(fragment_authentication_typegrapher_biaoqiantext,authEntry.getData().getCustomer_data().getMark())
                            .setText(fragment_authentication_typegrapher_submit,getResources().getString(R.string.shenhezhong))
                            .setText(fragment_authentication_typegrapher_shenhe, getResources().getString(R.string.shenhezhongtwo));
                    break;

                case 2:
                    fragment_authentication_typegrapher_yingyezhizhao.setClickable(false);
                    fragment_authentication_typegrapher_rltwo.setClickable(false);
                    fragment_authentication_typegrapher_gongsiquanceng.setEnabled(false);
                    fragment_authentication_typegrapher_address.setEnabled(false);
                    fragment_authentication_typegrapher_jianjie.setEnabled(false);
                    fragment_authentication_typegrapher_quhao.setEnabled(false);
                    fragment_authentication_typegrapher_reset.setClickable(false);
                    fragment_authentication_typegrapher_submit.setClickable(false);
                    fragment_authentication_typegrapher_biaoqian.setClickable(false);
                    fragment_authentication_typegrapher_img3.setClickable(false);
                    fragment_authentication_typegrapher_img4.setClickable(false);
                    fragment_authentication_typegrapher_img5.setClickable(false);
                    mainVu.setImageUrl(fragment_authentication_typegrapher_yingyezhizhao, getActivity(), HttpConstance.URL + authEntry.getData().getCustomer_data().getImg_thumb())
                            .setImageUrl(fragment_authentication_typegrapher_img3,getActivity(),HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(0).getImg_thumb())
                            .setImageUrl(fragment_authentication_typegrapher_img4,getActivity(),HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(1).getImg_thumb())
                            .setImageUrl(fragment_authentication_typegrapher_img5, getActivity(), HttpConstance.URL + authEntry.getData().getCustomer_data().getCustomer_gallery().get(2).getImg_thumb());
                    mainVu.setText(fragment_authentication_typegrapher_gongsiquanceng,authEntry.getData().getCustomer_data().getCompany_name())
                            .setText(fragment_authentication_typegrapher_address, authEntry.getData().getCustomer_data().getCompany_area())
                            .setText(fragment_authentication_typegrapher_jianjie,authEntry.getData().getCustomer_data().getCompany_description())
                            .setText(fragment_authentication_typegrapher_quhao,authEntry.getData().getCustomer_data().getCompany_telephone())
                            .setText(fragment_authentication_typegrapher_biaoqiantext,authEntry.getData().getCustomer_data().getMark())
                            .setText(fragment_authentication_typegrapher_submit,getResources().getString(R.string.shenhezhongthee))
                            .setText(fragment_authentication_typegrapher_shenhe, getResources().getString(R.string.shenhezhongfore));
                    break;

                case 3:

                    mainVu.setImageUrl(fragment_authentication_typegrapher_yingyezhizhao, getActivity(), HttpConstance.URL + authEntry.getData().getCustomer_data().getImg_thumb())
                    .setImageUrl(fragment_authentication_typegrapher_img3,getActivity(),HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(0).getImg_thumb())
                        .setImageUrl(fragment_authentication_typegrapher_img4,getActivity(),HttpConstance.URL+authEntry.getData().getCustomer_data().getCustomer_gallery().get(1).getImg_thumb())
                        .setImageUrl(fragment_authentication_typegrapher_img5, getActivity(), HttpConstance.URL + authEntry.getData().getCustomer_data().getCustomer_gallery().get(2).getImg_thumb());
                    mainVu.setText(fragment_authentication_typegrapher_gongsiquanceng,authEntry.getData().getCustomer_data().getCompany_name())
                            .setText(fragment_authentication_typegrapher_address, authEntry.getData().getCustomer_data().getCompany_area())
                            .setText(fragment_authentication_typegrapher_jianjie,authEntry.getData().getCustomer_data().getCompany_description())
                            .setText(fragment_authentication_typegrapher_quhao,authEntry.getData().getCustomer_data().getCompany_telephone())
                            .setText(fragment_authentication_typegrapher_biaoqiantext,authEntry.getData().getCustomer_data().getMark())
                            .setText(fragment_authentication_typegrapher_submit,getResources().getString(R.string.shenhezhongfive))
                            .setText(fragment_authentication_typegrapher_shenhe, getResources().getString(R.string.shenhezhongsix));
                    break;


            }

        }

    }

    private void initListenner() {
       /* fragment_authentication_typegrapher_img1.setOnClickListener(this);
        fragment_authentication_typegrapher_img2.setOnClickListener(this);*/
        fragment_authentication_typegrapher_img3.setOnClickListener(this);
        fragment_authentication_typegrapher_img4.setOnClickListener(this);
        fragment_authentication_typegrapher_img5.setOnClickListener(this);
        fragment_authentication_typegrapher_submit.setOnClickListener(this);
        fragment_authentication_typegrapher_reset.setOnClickListener(this);
        fragment_authentication_typegrapher_biaoqian.setOnClickListener(this);
//        fragment_authentication_typegrapher_biaoqiantwo.setOnClickListener(this);
        fragment_authentication_typegrapher_biaoqian.setOnClickListener(this);
        fragment_authentication_typegrapher_yingyezhizhao.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null)
        {
            return;

        }
        CameraUtils.dialogdiss();
        if (requestCode==CameraUtils.CAPTURE6&&resultCode==getActivity().RESULT_OK)
        {

            try {
               yingyepath =  CameraUtils.getCameraBitmap(data,fragment_authentication_typegrapher_yingyezhizhao);
                fileOne = new File(yingyepath);
                Log.i("www", "营业执照" + fileOne.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK6&&resultCode==getActivity().RESULT_OK)
        {
           yingyepath = CameraUtils.getPicBitmap(data,getActivity(),fragment_authentication_typegrapher_yingyezhizhao);
            fileOne = new File(yingyepath);
            Log.i("www","营业执照"+fileOne.getName());
        }


        if (requestCode==CameraUtils.CAPTURE3&&resultCode==getActivity().RESULT_OK)
        {

            try {
                fileTwo = new File(CameraUtils.getCameraBitmap(data,fragment_authentication_typegrapher_img3));
                fragment_authentication_typegrapher_img4.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK3&&resultCode==getActivity().RESULT_OK)
        {
           fileTwo = new File(CameraUtils.getPicBitmap(data,getActivity(),fragment_authentication_typegrapher_img3));
            fragment_authentication_typegrapher_img4.setVisibility(View.VISIBLE);
        }


        if (requestCode==CameraUtils.CAPTURE4&&resultCode==getActivity().RESULT_OK)
        {

            try {
               fileThree = new File(CameraUtils.getCameraBitmap(data,fragment_authentication_typegrapher_img4));
                fragment_authentication_typegrapher_img5.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==CameraUtils.ACTION_PICK4&&resultCode==getActivity().RESULT_OK)
        {
            fileThree = new File(CameraUtils.getPicBitmap(data,getActivity(),fragment_authentication_typegrapher_img4));
            fragment_authentication_typegrapher_img5.setVisibility(View.VISIBLE);
        }

        if (requestCode==CameraUtils.CAPTURE5&&resultCode==getActivity().RESULT_OK)
        {

            try {
                fileFore = new File(CameraUtils.getCameraBitmap(data,fragment_authentication_typegrapher_img5));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (requestCode==CameraUtils.ACTION_PICK5&&resultCode==getActivity().RESULT_OK)
        {
            fileFore = new File(CameraUtils.getPicBitmap(data,getActivity(),fragment_authentication_typegrapher_img5));

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ftpUtils.setUploadSuccess(this);
    }

    private void initData() {
        alertDialogUtils = new AlertDialogUtils(getActivity(),R.layout.loading);
        positionMySeft = new PositionMySeft();
        ftpUtils =FTPUtils.getInstance();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {

        EventBus.getDefault().register(this);
        fragment_authentication_typegrapher_text3 = mainVu.getItemView(R.id.fragment_authentication_typegrapher_text3);
       /* fragment_authentication_typegrapher_img1 = mainVu.getItemView(R.id.fragment_authentication_typegrapher_img1);
        fragment_authentication_typegrapher_img2 =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_img2);*/
        fragment_authentication_typegrapher_img3 =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_img3);
        fragment_authentication_typegrapher_img4 = mainVu.getItemView(R.id.fragment_authentication_typegrapher_img4);
        fragment_authentication_typegrapher_img5 = mainVu.getItemView(R.id.fragment_authentication_typegrapher_img5);
        fragment_authentication_typegrapher_address =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_address);
        fragment_authentication_typegrapher_biaoqian =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_biaoqian);
      //  fragment_authentication_typegrapher_biaoqiantwo =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_biaoqiantwo);
        fragment_authentication_typegrapher_jianjie =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_jianjie);
        fragment_authentication_typegrapher_quhao =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_quhao);
        fragment_authentication_typegrapher_reset =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_reset);
        fragment_authentication_typegrapher_submit =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_submit);
        fragment_authentication_typegrapher_yingyezhizhao =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_yingyezhizhao);
      //  fragment_authentication_typegrapher_zhiwei = mainVu.getItemView(R.id.fragment_authentication_typegrapher_zhiwei);
        fragment_authentication_typegrapher_gongsiquanceng = mainVu.getItemView(R.id.fragment_authentication_typegrapher_gongsiquanceng);
        fragment_authentication_typegrapher_rltwo = mainVu.getItemView(R.id.fragment_authentication_typegrapher_rltwo);
        fragment_authentication_typegrapher_biaoqiantext =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_biaoqiantext);
        fragment_authentication_typegrapher_shenhe =  mainVu.getItemView(R.id.fragment_authentication_typegrapher_shenhe);
    }


    public void onEventMainThread(StringBuilder stringBuilder)
    {
        stringBuildertwo = stringBuilder;
        fragment_authentication_typegrapher_biaoqiantext.setText(stringBuilder.toString().trim());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
         case R.id.fragment_authentication_typegrapher_biaoqian:
             Log.i("www","点击");
              IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_DirectorSetting.class,null);
             //   CameraUtils.photographByFragment(getActivity(), Fragment_authentication_typographer.this, CameraUtils.CAPTURE1, CameraUtils.ACTION_PICK1);
                break;

            case R.id.fragment_authentication_typegrapher_img3:
                CameraUtils.photographByFragment(getActivity(), Fragment_authentication_typographer.this, CameraUtils.CAPTURE3, CameraUtils.ACTION_PICK3);
                break;

            case R.id.fragment_authentication_typegrapher_img4:
                CameraUtils.photographByFragment(getActivity(), Fragment_authentication_typographer.this, CameraUtils.CAPTURE4, CameraUtils.ACTION_PICK4);
                break;

            case R.id.fragment_authentication_typegrapher_img5:
                CameraUtils.photographByFragment(getActivity(), Fragment_authentication_typographer.this, CameraUtils.CAPTURE5, CameraUtils.ACTION_PICK5);
                break;

            case R.id.fragment_authentication_typegrapher_yingyezhizhao:
                fragment_authentication_typegrapher_text3.setVisibility(View.GONE);
                CameraUtils.photographByFragment(getActivity(), Fragment_authentication_typographer.this, CameraUtils.CAPTURE6, CameraUtils.ACTION_PICK6);
                break;

            case R.id.fragment_authentication_typegrapher_reset:
                EdiTextUtils.getInstance().resetText(fragment_authentication_typegrapher_address);
                EdiTextUtils.getInstance().resetText(fragment_authentication_typegrapher_gongsiquanceng);
                EdiTextUtils.getInstance().resetText(fragment_authentication_typegrapher_jianjie);
                EdiTextUtils.getInstance().resetText(fragment_authentication_typegrapher_quhao);
               // EdiTextUtils.getInstance().resetText(fragment_authentication_typegrapher_zhiwei);
               /* fragment_authentication_typegrapher_img1.setImageBitmap(null);
                fragment_authentication_typegrapher_img2.setImageBitmap(null);*/
                fragment_authentication_typegrapher_img3.setImageResource(R.mipmap.purchase);
                fragment_authentication_typegrapher_img4.setImageResource(R.mipmap.purchase);
                fragment_authentication_typegrapher_img5.setImageResource(R.mipmap.purchase);
                fragment_authentication_typegrapher_yingyezhizhao.setImageBitmap(null);
                fragment_authentication_typegrapher_text3.setVisibility(View.VISIBLE);
                break;
            case R.id.fragment_authentication_typegrapher_submit:


                if (!EdiTextUtils.getInstance().CheckedEmpty(fragment_authentication_typegrapher_gongsiquanceng,getResources().getString(R.string.fragment_authentication_emptygongsi)))
                {
                    return;
                }

                if (!EdiTextUtils.getInstance().CheckedEmpty(fragment_authentication_typegrapher_address,getResources().getString(R.string.fragment_authentication_emptydizhi)))
                {
                    return;
                }

                if (!EdiTextUtils.getInstance().CheckedEmpty(fragment_authentication_typegrapher_jianjie,getResources().getString(R.string.fragment_authentication_emptyjianjie)))
                {
                    return;
                }

                if (!EdiTextUtils.getInstance().CheckedEmpty(fragment_authentication_typegrapher_quhao,getResources().getString(R.string.fragment_authentication_emptyquhao)))
                {
                    return;
                }

                if (fileTwo==null||fileThree==null)
                {
                    Toast.makeText(getActivity(), "请至少上传两张企业图片", Toast.LENGTH_SHORT).show();
                    return;
                }

                ftpUtils.setUploadSuccess(this);
                if (yingyepath!=null) {
                    ftpUtils.uploadSingleFile(new File(yingyepath), "Customer/" + DateUtils.getDateToString());
                }
             //   positionMySeft.AddressChangeTolocation(getActivity(), fragment_authentication_typegrapher_address.getText().toString().trim());
                HashMap<String, String> map = new HashMap<String,String>();
                map.put("token", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
                map.put("company_name",fragment_authentication_typegrapher_gongsiquanceng.getText().toString().trim());
                map.put("company_area", fragment_authentication_typegrapher_address.getText().toString().trim());
                map.put("lng", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALNG, null));
                map.put("lat", SharePreferenceUtils.getInstance(getActivity(), SharePreferenceConstance.POSITIONNAME).getSharePreferenceString(SharePreferenceConstance.POSITIONNALAT, null));
                map.put("company_telephone",fragment_authentication_typegrapher_quhao.getText().toString().trim());
                map.put("company_description",fragment_authentication_typegrapher_jianjie.getText().toString().trim());
                if (stringBuildertwo!=null) {
                    if (stringBuildertwo.toString() != null) {
                        map.put("mark", stringBuildertwo.toString());
                    }
                }
                map.put("img",DateUtils.getDateToString()+"/"+fileOne.getName());
                if (fileTwo!=null&&fileThree!=null&&fileFore==null) {

                    Log.i("www","tupiuan"+fileTwo+"   "+fileThree);
                    HttpFactory.getInstance().Typographer(getActivity(), map, new Pair<String, File>("file[0]", fileTwo), new Pair<String, File>("file[1]", fileThree));
                }
                if (fileTwo!=null&&fileThree!=null&&fileFore!=null) {
                    Log.i("www","tupiuan"+fileTwo+"   "+fileThree+"  "+fileFore);
                    HttpFactory.getInstance().Typographer(getActivity(), map, new Pair<String, File>("file[0]", fileTwo), new Pair<String, File>("file[1]", fileThree), new Pair<String, File>("file[2]", fileFore));
                }
               /* ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
                Glide.with(this).load(R.mipmap.run).into(imageView);
                alertDialogUtils.showDialog(false);*/

                break;


        }

    }


    @Override
    public void success() {

    }
}
