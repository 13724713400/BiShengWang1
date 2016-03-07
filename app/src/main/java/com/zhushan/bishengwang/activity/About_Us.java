package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhushan.bishengwang.R;

public class About_Us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);
        ImageView typographer_share = (ImageView) findViewById(R.id.typographer_share);
        typographer_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                About_Us.this.finish();
            }
        });


    }
}
