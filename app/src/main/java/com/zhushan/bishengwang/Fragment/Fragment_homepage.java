package com.zhushan.bishengwang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.mylibrary.CoverManager;
import com.example.administrator.mylibrary.DropCover;
import com.example.administrator.mylibrary.WaterDrop;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Attention_i;
import com.zhushan.bishengwang.activity.Group;
import com.zhushan.bishengwang.activity.MyWatchlist;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Created by Administrator on 2015/11/28.
 */
public class Fragment_homepage extends Fragment {
    private List<HashMap<String, Object>> mHashMaps;
    private HashMap<String, Object> map;
    private SimpleAdapter mAdapter;
    private SimpleAdapter mAdapter1;
    private ListView listview, listview2;
    private int itemCount = 28;

    private BaseAdapter adapter;
    private MainVu mainVu = new MainVu();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // getActivity().getActionBar().hide();
        mainVu.initByLayout(getActivity(), R.layout.fragment_main);
        //  getActivity().getActionBar().hide();
        listdata();

        return mainVu.getView();
    }
    public void listdata() {
        listview = (ListView) mainVu.getView().findViewById(R.id.lis5);
        listview2 = (ListView) mainVu.getView().findViewById(R.id.lis6);
        CoverManager.getInstance().init(getActivity());
        // listview.setAdapter(new DemoAdapter());
        CoverManager.getInstance().setMaxDragDistance(150);
        CoverManager.getInstance().setExplosionTime(150);
        mAdapter = new SimpleAdapter(getActivity(),
                getData(), R.layout.homepage_message, new String[]{"image", "info"},
                new int[]{R.id.fragment_me_touxiang, R.id.fragment_me_name});
        mAdapter1 = new SimpleAdapter(getActivity(), getData1(), R.layout.homepage_message,
                new String[]{"image", "info"},
                new int[]{R.id.fragment_me_touxiang, R.id.fragment_me_name});
//        listview.setAdapter(mAdapter);





        //   listview2.setAdapter(mAdapter1);
        setListViewHeightBasedOnChildren(listview2);
        setListViewHeightBasedOnChildren(listview);
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String aa = (String) getData1().get(position).get("info");

            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }


        });

    }
    private List<HashMap<String, Object>> getData1() {
  /*      mHashMaps = new ArrayList<HashMap<String, Object>>();
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
        mHashMaps.add(map);*/
        return mHashMaps;
    }

    private List<HashMap<String, Object>> getData() {
      /*  mHashMaps = new ArrayList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.login_logo);
        map.put("info", "毕昇小助手");
        mHashMaps.add(map);

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
        mHashMaps.add(map);*/
        return mHashMaps;

    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
   /* class DemoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            convertView = LayoutInflater.from(getActivity()).inflate(
                    R.layout.homepage_message, null);
            WaterDrop drop = (WaterDrop) convertView.findViewById(R.id.view);
            drop.setText(String.valueOf(position));

            drop.setOnDragCompeteListener(new DropCover.OnDragCompeteListener() {

                @Override
                public void onDrag() {
                    Toast.makeText(getActivity(), "remove:" + position,
                            Toast.LENGTH_SHORT).show();
                }
            });
   //         setListViewHeightBasedOnChildren(listview);

            return convertView;
        }
    }*/

}


