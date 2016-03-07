package com.zhushan.bishengwang.Fragment;

import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.zhushan.bishengwang.Constance.ConstanceTag;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;

import com.zhushan.bishengwang.Entry.PrintingPeapleData;
import com.zhushan.bishengwang.Entry.PrintingPeapleEntry;
import com.zhushan.bishengwang.Entry.ShuaiXuanEntry;

import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.PringtingPeapleAdapter;
import com.zhushan.bishengwang.Iadapter.PringtingPeapleGridViewAdapter;
import com.zhushan.bishengwang.Iaplication.TBaplication;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Iselfview.LvAdapter;
import com.zhushan.bishengwang.Iselfview.MyListView;
import com.zhushan.bishengwang.Itools.ChangeStateUtils;
import com.zhushan.bishengwang.Itools.DisLruCacheUtils;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.PeapleExpandableListviewUtils;
import com.zhushan.bishengwang.Itools.PopuwindowUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Itools.ZxingUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_province;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2015/11/28.
 */
public class Fragment_flock extends Fragment implements View.OnClickListener {
    private UMSocialService umSocialService = UMServiceFactory.getUMSocialService("");
    private LinearLayout linearLayout1, linearLayout2;
    private ImageView img1, img2, img3,printing_share;
    private TextView pringtingpeaple_city,textView1, textView2;
    private RadioButton girld_txt,all_txt,man_txt;
    private RadioGroup rg;
    private RelativeLayout relativeLayout1;
    private boolean isPress;
    private List<HashMap<String, Object>> mHashMaps;
    private HashMap<String, Object> map;
    private SimpleAdapter mAdapter;
    AlertDialog mDialog;
    private AlertDialog alertDialog;
    private RelativeLayout relative;
    private  PopuwindowUtils popuwindowUtils,popuwindowUtilspaiixu,popuwindowUtilsshuaixuan;
    private  List<PrintingPeapleData> printingPeapleData2 = new ArrayList<PrintingPeapleData>();
    private PringtingPeapleAdapter pringtingPeapleAdapter;
    private ShuaiXuanEntry shuaiXuanEntry= new ShuaiXuanEntry();
    private StringBuilder stringBuildertwo;
    private int index;
    private boolean isCat;
    RelativeLayout jubu;
    private boolean isGridview;
    private boolean isFirst;
    ListView listvi;
  /*  private HashMap<String,String> map = new HashMap<String,String>();*/
    private MainVu mainVu = new MainVu();
    private boolean isPressTwo,isvisible;
    private boolean isFirstTwo;
    private List<String> list;
    private MyListView lv;
    private LvAdapter adapter;
    Fragmnet_pringtingpeaple_list  fragmnet_pringtingpeaple_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = mainVu.initByLayout(getActivity(),R.layout.fragment__flock);
        // MainVu.getInstance().initByLayout(TBaplication.getInstance(), R.layout.fragment_printingpeaple);
        lv = (MyListView)view.findViewById(R.id.pringpeapl);

      /*isPressTwo = true;*/
       mAdapter = new SimpleAdapter(getActivity(), getData1(), R.layout.circle_listview_item,
                new String[]{"image", "info"}, new int[]{R.id.printing_item_touxiangl, R.id.printing_item_Nam});
      /*  listvi.setAdapter(mAdapter);*/
        lv.setAdapter(mAdapter);
     /*   initData();*/
        lv.setonRefreshListener(new MyListView.OnRefreshListener() {

            @Override
            public void onRefresh() {
           /*  new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                     Toast.makeText(getActivity(),"天窗",Toast.LENGTH_SHORT).show();
                      return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        lv.onRefreshComplete();
                    }
                }.execute(null, null, null);*/
            }
        });
/*        initShareContent();*/
      /*  loadData();*/
        return mainVu.getView();

    }
/*    @Subscriber(tag = ConstanceTag.POSIONTIONG)
    public void getPositionData(String resptring) {
        Log.i("www", "xnxi" + resptring);
    }*/

    private List<HashMap<String, Object>> getData1() {
        mHashMaps = new ArrayList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.headportrait2);
        map.put("info", "阿尔贝·加曬");
        mHashMaps.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.pic_1);
        map.put("info", "贾斯丁");
        mHashMaps.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
        mHashMaps.add(map);
        return mHashMaps;
    }


    private void initData() {
        ImageView fork1 =mainVu.getItemView(R.id.fork);
        Button confirm =mainVu.getItemView(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (girld_txt.isChecked()){
                    Toast.makeText(getActivity(),"你选择了女",Toast.LENGTH_SHORT).show();
                   /* isCat = true;
                    shuaiXuanEntry.setIndex(0);
                    shuaiXuanEntry.setIsPress(true);
                    shuaiXuanEntry.setSex("女");
                    de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                    map.put("page", String.valueOf(index));
                    map.put("sex", shuaiXuanEntry.getSex());
                    map.put("label", shuaiXuanEntry.getLabel());
                    map.put("filter1", shuaiXuanEntry.getOrderField());
                    map.put("lat",shuaiXuanEntry.getLat());
                    map.put("lng",shuaiXuanEntry.getLng());
                    map.put("city", shuaiXuanEntry.getCity());
                    HttpFactory.getInstance().PrintingPeaple(getActivity(), map);*/
                }if(all_txt.isChecked()){
                    Toast.makeText(getActivity(),"你选择了全部",Toast.LENGTH_SHORT).show();

                }if(man_txt.isChecked()){
                    Toast.makeText(getActivity(),"你选择了男",Toast.LENGTH_SHORT).show();
                   /* shuaiXuanEntry.setIndex(0);
                    shuaiXuanEntry.setIsPress(true);
                    shuaiXuanEntry.setSex("男");
                    de.greenrobot.event.EventBus.getDefault().post(shuaiXuanEntry);
                    map.put("page", String.valueOf(index));
                    map.put("sex", shuaiXuanEntry.getSex());
                    map.put("label", shuaiXuanEntry.getLabel());
                    map.put("filter1", shuaiXuanEntry.getOrderField());
                    map.put("lat",shuaiXuanEntry.getLat());
                    map.put("lng", shuaiXuanEntry.getLng());
                    map.put("city", shuaiXuanEntry.getCity());
                    HttpFactory.getInstance().PrintingPeaple(getActivity(), map);*/

                }
                jubu.setVisibility(View.GONE);

            }
        });
        fork1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jubu.setVisibility(View.GONE);
            }
        });

        //筛选点击事件
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // popuwindowUtilspaiixu.showPopuwindow(
                // linearLayout1)
                jubu.setVisibility(View.VISIBLE);
                jubu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jubu.setVisibility(View.GONE);
                    }
                });

            }
        });
    }

    private void initView() {

        girld_txt=mainVu.getItemView(R.id.girld_txt);
        all_txt=mainVu.getItemView(R.id.all_txt);
        man_txt=mainVu.getItemView(R.id.man_txt);


//        de.greenrobot.event.EventBus.getDefault().register(this);
        jubu = mainVu.getItemView(R.id.jubu);
        linearLayout1 = mainVu.getItemView(R.id.pringtingpeaple_paixu);
        textView1 = mainVu.getItemView(R.id.pringtingpeaple_paixu_txt);
        //  img1 = mainVu.getItemView(R.id.pringtingpeaple_paixu_img);
        linearLayout2 = mainVu.getItemView(R.id.pringtingpeaple_select);
        textView2 = mainVu.getItemView(R.id.pringtingpeaple_select_txt);
        img2 = mainVu.getItemView(R.id.pringtingpeaple_select_img);
        relativeLayout1 = mainVu.getItemView(R.id.pringtingpeaple_pailie);
        img3 = mainVu.getItemView(R.id.pringtingpeaple_pailie_img);
        printing_share = mainVu.getItemView(R.id.printing_share);
        pringtingpeaple_city = mainVu.getItemView(R.id.pringtingpeaple_city);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.pringtingpeaple_city:
                Bundle bundle2 = new Bundle();
                bundle2.putString("flag","Fragment_pringtingpeaple");
                IntentUtils.getInstance().startToAnoterActivity(getActivity(), Activity_province.class,bundle2);
                break;

            case R.id.pringtingpeaple_pailie:
                if (!isPress) {
                    isPress = !isPress;
                    Fragmnet_pringtingpeaple_Gridview Fragmnet_pringtingpeaple_gridview = new Fragmnet_pringtingpeaple_Gridview();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("shuaiXuanEntry", shuaiXuanEntry);
                    Fragmnet_pringtingpeaple_gridview.setArguments(bundle);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_pringtingpeaple_framelayout, Fragmnet_pringtingpeaple_gridview)
                            .commit();
                    ChangeStateUtils.getInstance().changeSate(null, 0, img3, R.mipmap.list1);
                } else {
                    isPress = !isPress;
                    Fragmnet_pringtingpeaple_list fragmnet_pringtingpeaple_list = new Fragmnet_pringtingpeaple_list();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("shuaiXuanEntry", shuaiXuanEntry);
                    fragmnet_pringtingpeaple_list.setArguments(bundle);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_pringtingpeaple_framelayout, fragmnet_pringtingpeaple_list)
                            .commit();
                    ChangeStateUtils.getInstance().changeSate(null, 0, img3, R.mipmap.list2);
                }
                break;

        }
    }

}