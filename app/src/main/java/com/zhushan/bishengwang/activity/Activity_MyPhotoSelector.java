package com.zhushan.bishengwang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.MyPhotoSelectorEntry;
import com.zhushan.bishengwang.Iadapter.MyPhotoSelectorAdapter;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.CameraUtils;
import com.zhushan.bishengwang.Itools.DateUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class Activity_MyPhotoSelector extends Basetivity implements View.OnClickListener{

    private MainVu mainVu = new MainVu();
    private List<MyPhotoSelectorEntry> myPhotoSelectorEntryList;
    private MyPhotoSelectorEntry myPhotoSelectorEntry;
    private GridView gridView;
    private StringBuilder stringBuilder = new StringBuilder();
    private MyPhotoSelectorAdapter myPhotoSelectorAdapter;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this, R.layout.activity_activity__my_photo_selector));
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        gridView = mainVu.getItemView(R.id.myphoto_gridview);
    }

    public void onEventMainThread(String s)
    {   stringBuilder.append(s+",");
        Log.i("www","图片地址"+stringBuilder.toString());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data==null)
        {
            return;
        }
        CameraUtils.dialogdiss();
        if (requestCode== CameraUtils.CAPTURE1&&resultCode==RESULT_OK) {

            String path = CameraUtils.getPicBitmap(data,this,null);
            MyPhotoSelectorEntry myPhotoSelectorEntry =  new MyPhotoSelectorEntry();
            myPhotoSelectorEntry.setPath(path);
            myPhotoSelectorEntryList.add(myPhotoSelectorEntry);
            myPhotoSelectorAdapter.notifyDataSetChanged();
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
            HttpFactory.getInstance().uploadMyphoto(this, map, new Pair<String, File>("file[0]", new File(path)));

        }
        if (requestCode == CameraUtils.ACTION_PICK1 && resultCode == RESULT_OK) {
            String path = CameraUtils.getPicBitmap(data,this,null);
            MyPhotoSelectorEntry myPhotoSelectorEntry =  new MyPhotoSelectorEntry();
            myPhotoSelectorEntry.setPath(path);
            myPhotoSelectorEntryList.add(myPhotoSelectorEntry);
            myPhotoSelectorAdapter.notifyDataSetChanged();
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
            HttpFactory.getInstance().uploadMyphoto(this, map, new Pair<String, File>("file[0]", new File(path)));
        }
    }

    @Override
    public void initData() {

        BackActivity.finishActivity(this,R.id.myphotosele_back);
       Bundle bundle =  getIntent().getExtras();
        String path=null;
        if (bundle!=null)
        {
            path =  bundle.getString("imgurl");
            id = bundle.getInt("id");
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN,null));
            HttpFactory.getInstance().uploadMyphoto(this,map,new Pair<String,File>("file[0]",new File(path)));

        }
        myPhotoSelectorEntryList = new ArrayList<MyPhotoSelectorEntry>();
        myPhotoSelectorEntry = new MyPhotoSelectorEntry();
        myPhotoSelectorEntry.setId(R.mipmap.purchase);
        MyPhotoSelectorEntry myPhotoSelectorEntry2 = new MyPhotoSelectorEntry();
        myPhotoSelectorEntry2.setPath(path);
        myPhotoSelectorEntryList.add(myPhotoSelectorEntry);
        myPhotoSelectorEntryList.add(myPhotoSelectorEntry2);
        Log.i("www","size"+myPhotoSelectorEntryList.size());
        myPhotoSelectorAdapter = new MyPhotoSelectorAdapter(this,myPhotoSelectorEntryList,R.layout.myphotoitemtwo);
        gridView.setAdapter(myPhotoSelectorAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    CameraUtils.photograph(Activity_MyPhotoSelector.this, CameraUtils.CAPTURE1, CameraUtils.ACTION_PICK1);

                }
            }
        });


    }

    @Override
    public void initListener() {
        mainVu.getItemView(R.id.myphoto_send).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.myphoto_send:


                HashMap<String,String> map = new HashMap<String,String>();
                map.put("token",SharePreferenceUtils.getInstance(Activity_MyPhotoSelector.this,SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
                map.put("galleryList",stringBuilder.toString());
                map.put("gallery_id",String.valueOf(id));
                map.put("addtime", DateUtils.getDateToString());
                if (mainVu.getItemView(R.id.myphoto_describe).toString().trim()!=null) {
                    map.put("description", ((EditText) mainVu.getItemView(R.id.myphoto_describe)).getText().toString().trim());
                }
                HttpFactory.getInstance().albumadd(Activity_MyPhotoSelector.this,map);
                break;

        }
    }
}
