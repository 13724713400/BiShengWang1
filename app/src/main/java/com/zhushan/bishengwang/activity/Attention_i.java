package com.zhushan.bishengwang.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zhushan.bishengwang.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogRecord;

public class Attention_i extends Activity {
    private ListView listview;
    private TextView textview, qin, ban;
    private LayoutInflater inflater;
    private  int p;
    private SimpleAdapter mAdapter;
    private List<Map<String, Object>> data;
    AlertDialog mDialog;
   // private ProgressDialog progressDialog = null;


    //use of Handler
    //messge info
    private static final int DISMISS_PROGRESS_DIALOG = 1;
    Message message = null;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case DISMISS_PROGRESS_DIALOG:
                    data.remove(p);
                    listview.setAdapter(adapter);
                    if (adapter.getCount() == 0) {
                        qin.setVisibility(View.VISIBLE);
                        rl2.setBackgroundResource(R.color.main_me_text);
                        ban.setBackgroundResource(R.color.main_me_text);
                    }

                    textview.setText("我关注的(" + adapter.getCount() + ")");
                    if (mDialog != null) {
                        mDialog.setView(null);
                        mDialog.dismiss();
                    }

                    break;
            }
        }
    };
    RelativeLayout rl2;
    MyAdapter1 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_i);

        rl2 = (RelativeLayout) findViewById(R.id.rl2);
        textview = (TextView) findViewById(R.id.typographer_city);
        ban = (TextView) findViewById(R.id.ban);
        qin = (TextView) findViewById(R.id.textView5);
        listview = (ListView) findViewById(R.id.listView4);

        adapter = new MyAdapter1(this);
        data = getData();
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String aa=(String) getData().get(position).get("info");

            }
        });
        textview.setText("我关注的(" + adapter.getCount() + ")");
        qin.setVisibility(View.GONE);
        if (adapter.getCount() == 0) {
            rl2.setBackgroundResource(R.color.main_me_text);
            ban.setBackgroundResource(R.color.main_me_text);
            qin.setVisibility(View.VISIBLE);
        }
        itemvied();
    }

    public void itemvied() {
        ImageView typographer_share = (ImageView) findViewById(R.id.typographer_share);
        typographer_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attention_i.this.finish();
            }
        });

    }

    class ViewHolder1 {
        public ImageView img;

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
            return data.size();
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
            p=position;
            ViewHolder1 holder = null;
            inflater = Attention_i.this.getLayoutInflater();
            view = inflater.inflate(R.layout.dialog_alert, null);

            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder1();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.checkbox_attention_i, null);
                holder.img = (ImageView) convertView.findViewById(R.id.imageView2);
                holder.info = (TextView) convertView.findViewById(R.id.textView);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder1) convertView.getTag();
            }
            holder.img.setImageResource((Integer) data.get(position).get("image"));
            holder.info.setText((String) data.get(position).get("info"));
          final CheckBox btn = (CheckBox) convertView.findViewById(R.id.yi_guanzhu);

            btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        btn.setText("已关注");
                        /*handler.postDelayed(runnable,1000);*/
                        listview.setAdapter(adapter);
                        //  mDialog = new AlertDialog.Builder(Attention_i.this).setView(view).show();
                        /*mDialog = new AlertDialog.Builder(Attention_i.this).setView(view).show();
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                message = handler.obtainMessage(DISMISS_PROGRESS_DIALOG);
                                handler.sendMessage(message);
                            }
                        }.start();*/

                        textview.setText("关注我的(" + adapter.getCount() + ")");
                      /* new T
                        {public void run()GsmCellLocationhengwang:remote E/CellLocation: create GsmCellLocation
                            {try {sleep(3000);     //等待三秒,自动进入软件主窗口


                                listview.setAdapter(adapter);
                                if (adapter.getCount() == 0) {
                                 *//*   qin.setVisibility(View.VISIBLE);
                                    rl2.setBackgroundResource(R.color.main_me_text);
                                    ban.setBackgroundResource(R.color.main_me_text);*//*
                                }


                                if (mDialog != null) {
                                    mDialog.setView(null);
                                    mDialog.dismiss();
                                }

                                    }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                                //progressDialog.dismiss();
                            }
                        }.start();*/

                    } else {
                        btn.setChecked(true);
                        listview.setAdapter(adapter);
                        //mDialog = new AlertDialog.Builder(Attention_i.this).setView(view).show();
                        listview.setAdapter(adapter);
                    }

                }
            });



            return convertView;
        }

    }


    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> mHashMaps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
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


}
