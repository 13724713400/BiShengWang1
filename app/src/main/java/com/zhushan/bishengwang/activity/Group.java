package com.zhushan.bishengwang.activity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zhushan.bishengwang.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Group extends Activity {
    private ListView listview;
    private TextView textview, qin, ban;
    private SimpleAdapter mAdapter;
    RelativeLayout rl2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        rl2 = (RelativeLayout) findViewById(R.id.rl2);
        textview = (TextView) findViewById(R.id.typographer_city);
        ban = (TextView) findViewById(R.id.ban);
        qin = (TextView) findViewById(R.id.textView5);
        listview = (ListView) findViewById(R.id.listView4);
        mAdapter = new SimpleAdapter(this, getData(), R.layout.typographer_group, new String[]{"image", "info"}, new int[]{R.id.imageView2, R.id.textView});
        listview.setAdapter(mAdapter);
        textview.setText("群组(" +  mAdapter .getCount() + ")");
        qin.setVisibility(View.GONE);
        if ( mAdapter .getCount() == 0) {
            rl2.setBackgroundResource(R.color.main_me_text);
            ban.setBackgroundResource(R.color.main_me_text);
            qin.setVisibility(View.VISIBLE);
        }
        itemvied();
    }
    public void itemvied() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String aa=(String) getData().get(position).get("info");

            }
        });
        ImageView typographer_share = (ImageView) findViewById(R.id.typographer_share);
        typographer_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Group.this.finish();
            }
        });

    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> mHashMaps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", R.mipmap.headportrait2);
        map.put("info", "印刷俱乐部");
        mHashMaps.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.pic_1);
        map.put("info", "二次元");
        mHashMaps.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.secretary_smallman);
        map.put("info", "印刷行业交流群");
        mHashMaps.add(map);
        return mHashMaps;
    }




}
