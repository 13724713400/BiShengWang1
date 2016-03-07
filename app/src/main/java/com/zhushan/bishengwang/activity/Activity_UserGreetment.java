package com.zhushan.bishengwang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.zhushan.bishengwang.Entry.UserGreetMentEntry;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import de.greenrobot.event.EventBus;

public class Activity_UserGreetment extends Basetivity {
    private MainVu mainVu = new MainVu();
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this, R.layout.activity_activity__user_greetment));
        EventBus.getDefault().register(this);
        initView();
        initData();
        initListener();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void onEventMainThread(UserGreetMentEntry userGreetMentEntry) {
        userGreetMentEntry.getData().getContent();
        webView.loadDataWithBaseURL(null,userGreetMentEntry.getData().getContent(),"text/html","utf-8",null);
    }
    @Override
    public void initView() {
        webView =  mainVu.getItemView(R.id.user_greetment_webview);
    }
    @Override
    public void initData() {
            HttpFactory.getInstance().UserGreetment(this);
            webView.getSettings().setJavaScriptEnabled(true);
            BackActivity.finishActivity(Activity_UserGreetment.this,R.id.user_greetment_back);
    }
    @Override
    public void initListener() {

    }
}
