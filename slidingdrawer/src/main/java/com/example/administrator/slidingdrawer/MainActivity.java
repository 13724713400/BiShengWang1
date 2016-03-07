package com.example.administrator.slidingdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
        private Gridview gv;
    private slidingdrawer sd;
    private ImageView im;
    private int [] icons= { R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher };
    private String []items={"Alarm","Calendar","Clock","Music","TV"} ;
    /**Clled when the activity is first created.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv=(Gridview)findViewById(R.id.mycontent1);
        sd=(slidingdrawer)findViewById(R.id.drawer1);
        im=(ImageView)findViewById(R.id.myImage1);

        MyGridViewAdapter adapter=new MyGridViewAdapter(this,items,icons);
        gv.setAdapter(adapter);

        sd.setOndrawerOpenLestener(new SlidingDrawer.OnDrawerOpenListener()
        {
            public void onDump
        });
    }
}
