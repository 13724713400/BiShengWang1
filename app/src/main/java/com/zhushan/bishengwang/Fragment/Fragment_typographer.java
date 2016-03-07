package com.zhushan.bishengwang.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_printingPeapleDetails;
import com.zhushan.bishengwang.activity.Attention_i;
import com.zhushan.bishengwang.activity.Group;
import com.zhushan.bishengwang.activity.MyWatchlist;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2015/11/28.
 */
public class Fragment_typographer extends Fragment  {
    private List<HashMap<String, Object>> mHashMaps;
    private HashMap<String, Object> map;
    private SimpleAdapter mAdapter;
    private SimpleAdapter mAdapter1;
    private ListView /*listview,*/ listview2;
    private  List<Map<String, Object>> data;
    private MainVu mainVu = new MainVu();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  EventBus.getDefault().register(this);
        View view =mainVu.initByLayout(getActivity(),R.layout.fragment_typographer);
        // MainVu.getInstance().initByLayout(TBaplication.getInstance(), R.layout.fragment_typographer);
        listdata();
        data=getData1();
        return  mainVu.getView();
    }
    public void listdata() {
        // listview = (ListView) mainVu.getView().findViewById(R.id.lis5);
        listview2 = (ListView) mainVu.getView().findViewById(R.id.lis6);
        mAdapter = new SimpleAdapter(getActivity(), getData1(),
                R.layout.contact_list, new String[]{"image", "info"},
                new int[]{R.id.imageView2, R.id.textView});
        // mAdapter1 = new SimpleAdapter(getActivity(), getData1(), R.layout.contact_friend_list, new String[]{"image", "info"}, new int[]{R.id.imageView2, R.id.textView});
        //  listview.setAdapter(mAdapter);
        // listview2.setAdapter(new MyAdapter1(getActivity()));

     /*   listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          //'      Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
            }
        });*/
        //  setListViewHeightBasedOnChildren(listview);
        //  setListViewHeightBasedOnChildren(listview2);
 /*       listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String aa = (String) getData1().get(position).get("info");
                new AlertDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage("你确定打吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent phoneIntent = new Intent("android.intent.action.CALL",
                                Uri.parse("tel:13724713400"));
                        startActivity(phoneIntent);
                    }
                }).setNegativeButton("取消",null).show();

            }
        });*/
       /* listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch (position) {
                    case 0:
                        Intent intent = new Intent(getActivity(), Attention_i.class);
                        startActivity(intent);
                        break;
                    case 1:
                       intent = new Intent(getActivity(), MyWatchlist.class);
                        startActivity(intent);

                        break;
                    case 2:
                        intent = new Intent(getActivity(), Group.class);
                        startActivity(intent);
                        break;

                }

            }
        });*/
    }
    private List<Map<String, Object>> getData1() {
        List<Map<String, Object>> mHashMaps = new ArrayList<Map<String, Object>>();
           /*   Map<String, Object> map = new HashMap<String, Object>();
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
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.touxiang_01);
        map.put("info", "发斯蒂芬");
        mHashMaps.add(map);
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "死人头");
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
    private List<HashMap<String, Object>> getData() {
        List<HashMap<String, Object>>  mHashMaps = new ArrayList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.login_logo);
        map.put("info", "我关注的");
        mHashMaps.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.login_logo);
        map.put("info", "关注我的");
        mHashMaps.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.login_logo);
        map.put("info", "圈");
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
    class ViewHolder1 {
        public ImageView img;
        public ImageView img2;
        public TextView info;
    }

    class MyAdapter1 extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private View view;
        private MyAdapter1(Context context) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适配器中所代表的数据集中的条目数
            return 4;
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            //获取数据集中与指定索引对应的数据项
            return position;
        }

        @Override
        public long getItemId(int position) {
            //Get the row id associated with the specified position in the list.
            //获取在列表中与指定索引对应的行id
            return position;
        }

        //Get a View that displays the data at the specified position in the data set.
        //获取一个在数据集中指定索引的视图来显示数据
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder1 holder = null;

            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder1();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.contact_friend_list, null);
                holder.img = (ImageView) convertView.findViewById(R.id.imag);
                holder.info = (TextView) convertView.findViewById(R.id.textView);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder1) convertView.getTag();
            }
            holder.img.setImageResource((Integer) data.get(position).get("image"));
            holder.info.setText((String) data.get(position).get("info"));
            LinearLayout ll=(LinearLayout)convertView.findViewById(R.id.ll);

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), Activity_printingPeapleDetails.class);
                    startActivity(intent);
                }
            });

            LinearLayout btn = (LinearLayout) convertView.findViewById(R.id.imageView3);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       /* data.remove(position);
                            listview.setAdapter(adapter);*/
                    String aa = (String) getData1().get(position).get("info");
                    AlertDialog dialog= new AlertDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setMessage("你确定要泼打‘" + aa + ":'的打号码吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent phoneIntent = new Intent("android.intent.action.CALL",
                                            Uri.parse("tel:13724713400"));
                                    startActivity(phoneIntent);
                                }
                            }).setNegativeButton("取消", null).show();
                }

            });
/*            bean =(KhMessageBean).position;
            viewHolder.message.setText(bean.getMessage());*/
            return convertView;
        }

    }

}
