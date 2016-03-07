package com.zhushan.bishengwang.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.CommentData;
import com.zhushan.bishengwang.Entry.CommentEntry;
import com.zhushan.bishengwang.Entry.DetailsEntry;
import com.zhushan.bishengwang.Fragment.Fragment_mypulish_jiu;
import com.zhushan.bishengwang.Fragment.Fragment_mypulish_qiu;
import com.zhushan.bishengwang.Fragment.Fragment_mypulish_shuai;
import com.zhushan.bishengwang.Fragment.Fragment_mypulish_zhi;
import com.zhushan.bishengwang.Iadapter.DetailesLiuYanAdapter;
import com.zhushan.bishengwang.Iadapter.EmptyAdapter;
import com.zhushan.bishengwang.Iadapter.MainViewPagerAdapter;
import com.zhushan.bishengwang.Iadapter.ViewPaperAdapter;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Iselfview.PulltoListview;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.BackActivity;
import com.zhushan.bishengwang.Itools.DateUtils;
import com.zhushan.bishengwang.Itools.ImageUtil;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.ListviewUtils;
import com.zhushan.bishengwang.Itools.Mediaplayutils;
import com.zhushan.bishengwang.Itools.PopuwindowUtils;
import com.zhushan.bishengwang.Itools.SendCommnetUtils;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.Itools.SoundMeter;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Itools.ZxingUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;
public class Activity_detailes extends Basetivity implements View.OnClickListener {
    private ImageView details_share,detailes_item_recordericon, detailes_shuai_img, detailes_xiahuaxian_img, detailes_back, detailes_item_shuai, detailes_item_img1, detailes_item_img2, detailes_item_imge3, detailes_item_recorder;
    private ViewPager detailes_viewpaper;
    private int bitmapwidth, offset;
    private List<Fragment> listfragment;
    private int currIndex = 0;
    private MainVu mainVu = new MainVu();
    private String id;
    private Mediaplayutils mediaplayutils = new Mediaplayutils();
    private PulltoListview detailes_item_liuyan_listView;
    private AlertDialogUtils alertDialogUtils;
    private List<View> imageViewList;
    private AlertDialog alertDialog;
    private DetailsEntry detailsEntry2;
    private int index;
    private boolean isFirst;
    private DetailesLiuYanAdapter detailesLiuYanAdapter;
    private HashMap<String, String> map;
    private List<CommentData> commentDataList2 = new ArrayList<CommentData>();
    private SoundMeter soundMeter;
    private boolean isPlay,isFlag;
    private UMSocialService umSocialService = UMServiceFactory.getUMSocialService("");
    private PopuwindowUtils popuwindowUtils;
    private TextView detailes_item_liuyanTwo, detailes_item_liuyan, detailes_tuijian, detailes_liuyan, detailes_sixin, detailes_item_date, detailes_item_titles, detailes_item_content, detailes_item_recorder_txt, detailes_item_address, detailes_item_guanzhutext, detailes_item_liulan, getDetailes_liuyan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this, R.layout.activity_detailes));
        initView();
        initData();
        initListener();
        com.umeng.socialize.utils.Log.LOG = true;
    }

    @Override
    public void initData() {
        popuwindowUtils = new PopuwindowUtils(this,R.layout.popuwindwo_item,false);
        alertDialogUtils = new AlertDialogUtils(this,R.layout.loading);
        ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
        Glide.with(this).load(R.mipmap.run).into(imageView);
        alertDialogUtils.showDialog(false);
        soundMeter = new SoundMeter();
        UmengUtils.getInstance().InitPlatform(this,umSocialService);

        // alertDialogUtils = new AlertDialogUtils(this,R.layout.details_imgitem);
        BackActivity.finishActivity(Activity_detailes.this, R.id.detailes_back);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {

            return;
        }
        id = bundle.getString("id");
        HashMap<String, String> map = new HashMap<String, String>();
        String token = SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
        if (token != null) {
            map.put("token", SharePreferenceUtils.getInstance(this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
        }

        map.put("id", id);
        HttpFactory.getInstance().Details(this, map);

    }

    public void onEventMainThread(String s) {
        Log.i("www","刷新数据");
        if (s.equals(String.valueOf(100))) {
            index = 0;
            commentDataList2.clear();
            map = new HashMap<String, String>();
            String token = SharePreferenceUtils.getInstance(Activity_detailes.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
            if (token != null) {
                map.put("token", SharePreferenceUtils.getInstance(Activity_detailes.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null));
            }

            map.put("page", String.valueOf(0));
            map.put("id", detailsEntry2.getData().getId());
            HttpFactory.getInstance().GetComment(Activity_detailes.this, map);
        }

    }

    public void onEventMainThread(CommentEntry commentEntry) {

        Log.i("www","评论信息");
        if (commentEntry.getData() == null) {
            Toast.makeText(Activity_detailes.this, "无留言信息", Toast.LENGTH_SHORT).show();
            detailes_item_liuyan_listView.onRefreshComplete();

        }
        if(commentEntry.getData() != null) {
            commentDataList2.addAll(commentEntry.getData());
        }
        if (commentDataList2==null) {
            detailes_item_liuyan_listView.setAdapter(EmptyAdapter.getInstanceAdapter(Activity_detailes.this));
        } else {

            if (!isFirst) {
                isFirst = !isFirst;
                Log.i("www", " dtata" + commentDataList2);
                detailesLiuYanAdapter = new DetailesLiuYanAdapter(Activity_detailes.this, commentDataList2, R.layout.detailes_item_listview_item);
                detailes_item_liuyan_listView.setAdapter(detailesLiuYanAdapter);

            } else {
                if (isFlag) {
                    Log.i("www","上拉刷新");
                    detailesLiuYanAdapter.notifyDataSetChanged();
                    detailes_item_liuyan_listView.onRefreshComplete();
                }else{
                    detailesLiuYanAdapter = new DetailesLiuYanAdapter(Activity_detailes.this, commentDataList2, R.layout.detailes_item_listview_item);
                    detailes_item_liuyan_listView.setAdapter(detailesLiuYanAdapter);
                }
            }
        }


    }

    public void onEventMainThread(final DetailsEntry detailsEntry) {
        detailsEntry2 = detailsEntry;
        alertDialogUtils.dissView();
        if (detailsEntry.getData().getImg() != null) {

            if (detailsEntry.getData().getImg().size() == 1) {
                mainVu.setImageUrl(detailes_item_img1, this, HttpConstance.URL + detailsEntry.getData().getImg().get(0).getImg_thumb());
                mainVu.getItemView(R.id.detailes_item_img2).setVisibility(View.INVISIBLE);
                mainVu.getItemView(R.id.detailes_item_imge3).setVisibility(View.INVISIBLE);

            }

            if (detailsEntry.getData().getImg().size() == 2) {
                mainVu.setImageUrl(detailes_item_img1, this, HttpConstance.URL + detailsEntry.getData().getImg().get(0).getImg_thumb())
                        .setImageUrl(detailes_item_img2, this, HttpConstance.URL + detailsEntry.getData().getImg().get(1).getImg_thumb());
                mainVu.getItemView(R.id.detailes_item_imge3).setVisibility(View.INVISIBLE);
            }

            if (detailsEntry.getData().getImg().size() == 3) {
                mainVu.setImageUrl(detailes_item_img1, this, HttpConstance.URL + detailsEntry.getData().getImg().get(0).getImg_thumb())
                        .setImageUrl(detailes_item_img2, this, HttpConstance.URL + detailsEntry.getData().getImg().get(1).getImg_thumb())
                        .setImageUrl(detailes_item_imge3, this, HttpConstance.URL + detailsEntry.getData().getImg().get(2).getImg_thumb());

            }

            mainVu.setText(detailes_item_date, DateUtils.getDateToString(Long.parseLong(detailsEntry.getData().getAddtime() + "000")))
                    .setText(detailes_item_titles, detailsEntry.getData().getTitle())
                    .setText(detailes_item_liulan, getResources().getString(R.string.detailes_liulan) + " " + detailsEntry.getData().getHit())
                    .setText(detailes_item_liuyan, getResources().getString(R.string.detailes_liuyan) + " " + detailsEntry.getData().getMessageCount())
                    .setText(detailes_item_address, detailsEntry.getData().getArea())
                            //  .setText(detailes_item_liuyanTwo, getResources().getString(R.string.detailes_liuyan) + " " + detailsEntry.getData().getComment().size())
                    .setText(detailes_item_recorder_txt, mediaplayutils.getLength(HttpConstance.URL + detailsEntry.getData().getVoice()))
                    .setText(detailes_item_guanzhutext, getResources().getString(R.string.guanzhu2) + " " + detailsEntry.getData().getFoucsCount());



            mainVu.setText(detailes_item_content, detailsEntry.getData().getContent());
            detailes_item_recorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!isPlay) {
                        isPlay = !isPlay;
                        soundMeter.startPlayTwo(HttpConstance.URL + detailsEntry.getData().getVoice());
                        detailes_item_recorder.setImageResource(R.mipmap.voice);

                        detailes_item_recordericon.setVisibility(View.VISIBLE);
                    } else {
                        isPlay = !isPlay;
                        soundMeter.stopPlay();
                          detailes_item_recorder.setImageResource(R.mipmap.icon_voice);
                        detailes_item_recordericon.setVisibility(View.GONE);
                    }


                }
            });

            switch (Integer.parseInt(detailsEntry.getData().getCate_id())) {
                case 2:
                    detailes_item_shuai.setImageResource(R.mipmap.shuai);
                    break;

                case 1:
                    detailes_item_shuai.setImageResource(R.mipmap.qiu);
                    break;

                case 3:
                    detailes_item_shuai.setImageResource(R.mipmap.jiu);
                    break;

                case 4:
                    detailes_item_shuai.setImageResource(R.mipmap.zhi);
                    break;
            }


            map = new HashMap<String, String>();
            map.put("page", String.valueOf(0));
            map.put("id", detailsEntry.getData().getId());
            HttpFactory.getInstance().GetComment(Activity_detailes.this, map);
            detailes_item_liuyan_listView.setMode(PullToRefreshBase.Mode.PULL_UP_TO_REFRESH);
            detailes_item_liuyan_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
         }
                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    index++;
                    isFlag = true;
                    map.put("page", String.valueOf(index));
                    HttpFactory.getInstance().GetComment(Activity_detailes.this, map);
                }
            });


        }}
        @Override
        public void initListener () {
            detailes_liuyan.setOnClickListener(this);
            detailes_sixin.setOnClickListener(this);
            detailes_tuijian.setOnClickListener(this);
            detailes_item_img1.setOnClickListener(this);
            detailes_item_img2.setOnClickListener(this);
            detailes_item_imge3.setOnClickListener(this);
            details_share.setOnClickListener(this);
            popuwindowUtils.getView(R.id.share_text).setOnClickListener(this);
          ///  popuwindowUtils.getView(R.id.tofriends).setOnClickListener(this);
        }

        @Override
        protected void onDestroy () {
            super.onDestroy();
            EventBus.getDefault().unregister(this);
        }

        @Override
        public void initView () {

            EventBus.getDefault().register(this);
            detailes_back = mainVu.getItemView(R.id.detailes_back);
            detailes_liuyan = mainVu.getItemView(R.id.detailes_liuyan);
            detailes_sixin = mainVu.getItemView(R.id.detailes_sixin);
            detailes_tuijian = mainVu.getItemView(R.id.detailes_tuijian);
            detailes_item_shuai = mainVu.getItemView(R.id.detailes_item_shuai);
            detailes_item_date = mainVu.getItemView(R.id.detailes_item_date);
            detailes_item_titles = mainVu.getItemView(R.id.detailes_item_titles);
            detailes_item_content = mainVu.getItemView(R.id.detailes_item_content);
            detailes_item_img1 = mainVu.getItemView(R.id.detailes_item_img1);
            detailes_item_img2 = mainVu.getItemView(R.id.detailes_item_img2);
            detailes_item_imge3 = mainVu.getItemView(R.id.detailes_item_imge3);
            detailes_item_recorder = mainVu.getItemView(R.id.detailes_item_recorder);
            detailes_item_recordericon = mainVu.getItemView(R.id.detailes_item_recordericon);
            detailes_item_recorder_txt = mainVu.getItemView(R.id.detailes_item_recorder_txt);
            detailes_item_address = mainVu.getItemView(R.id.detailes_item_address);
            detailes_item_guanzhutext = mainVu.getItemView(R.id.detailes_item_guanzhutext);
            detailes_item_liulan = mainVu.getItemView(R.id.detailes_item_liulan);
            detailes_item_liuyan = mainVu.getItemView(R.id.detailes_item_liuyan);
           // detailes_item_liuyan_listView = mainVu.getItemView(R.id.detailes_item_liuyan_listView);
            detailes_item_liuyanTwo = mainVu.getItemView(R.id.detailes_item_liuyanTwo);
            details_share = mainVu.getItemView(R.id.details_share);
        }

        @Override
        public void onClick (View v){
            switch (v.getId()) {
            /*    case R.id.tofriends:
                    AlertDialog alertDialog3 = null;
                    Bitmap bitmap = ZxingUtils.getInstance().createQRImage(Activity_detailes.this,"www.baidu.com",400,400, BitmapFactory.decodeResource(getResources(),R.mipmap.login_logo), ImageUtil.getSDPath()+"touxiang.png",null);
                    UmengUtils.getInstance().setShareContentFriends(Activity_detailes.this, umSocialService,bitmap);
                    UmengUtils.getInstance().showCustomUI(Activity_detailes.this, umSocialService, alertDialog3);
                    break;*/
                case R.id.share_text:
                    AlertDialog alertDialog2 = null;
                    UmengUtils.getInstance().setShareContent(this, umSocialService, HttpConstance.URL+detailsEntry2.getData().getImg().get(0).getImg_thumb(),detailsEntry2.getData().getContent(),detailsEntry2.getData().getTitle(),"http://www.baidu.com");
                    UmengUtils.getInstance().showCustomUI(Activity_detailes.this, umSocialService, alertDialog2);
                    break;
                case R.id.details_share:
                    popuwindowUtils.showPopuwindow(details_share);
                    break;

                case R.id.detailes_liuyan:
                    HashMap<String, String> map = new HashMap<String, String>();
                    String token = SharePreferenceUtils.getInstance(Activity_detailes.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
                    if (token != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("id",detailsEntry2.getData().getId());
                        bundle.putString("cate_id",detailsEntry2.getData().getCate_id());
                        bundle.putString("user_id",detailsEntry2.getData().getUser_id());
                        IntentUtils.getInstance().startToAnoterActivity(Activity_detailes.this,Activity_Comment.class,bundle);
                    } else {
                        IntentUtils.getInstance().startToAnoterActivity(Activity_detailes.this, Activity_Login.class, null);
                    }


                    break;
                case R.id.detailes_sixin:

                    String token2 = SharePreferenceUtils.getInstance(Activity_detailes.this, SharePreferenceConstance.USERNAME).getSharePreferenceString(SharePreferenceConstance.USERTOKEN, null);
                    if (token2 != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("user_id",detailsEntry2.getData().getUser_id());
                        IntentUtils.getInstance().startToAnoterActivity(Activity_detailes.this,Activity_sendmsg.class,bundle);
                    } else {
                        IntentUtils.getInstance().startToAnoterActivity(Activity_detailes.this, Activity_Login.class, null);
                    }


                    break;
                case R.id.detailes_tuijian:
                    UmengUtils.getInstance().setShareContent(this, umSocialService, HttpConstance.URL+detailsEntry2.getData().getImg().get(0).getImg_thumb(),detailsEntry2.getData().getContent(),detailsEntry2.getData().getTitle(),"http://www.baidu.com");
                    AlertDialog alertDialog1 = null;
                    UmengUtils.getInstance().showCustomUI(Activity_detailes.this, umSocialService, alertDialog1);
                    break;
                case R.id.detailes_item_img1:
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("detailsEntry2", detailsEntry2.getData().getImg());
                    bundle.putInt("index", 0);
                    IntentUtils.getInstance().startToAnoterActivity(Activity_detailes.this, Activity_Imgdetails.class, bundle);
                   overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                    break;
                case R.id.detailes_item_img2:
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelableArrayList("detailsEntry2", detailsEntry2.getData().getImg());
                    bundle2.putInt("index", 1);

                    IntentUtils.getInstance().startToAnoterActivity(Activity_detailes.this, Activity_Imgdetails.class, bundle2);
                    overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                    break;
                case R.id.detailes_item_imge3:
                    Bundle bundle3 = new Bundle();
                    bundle3.putParcelableArrayList("detailsEntry2", detailsEntry2.getData().getImg());
                    bundle3.putInt("index", 2);
                    IntentUtils.getInstance().startToAnoterActivity(Activity_detailes.this, Activity_Imgdetails.class, bundle3);
                    overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                    break;
            }
        }


  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      UMSsoHandler ssoHandler = umSocialService.getConfig().getSsoHandler(
              resultCode);
      if (ssoHandler != null) {
          ssoHandler.authorizeCallBack(requestCode, resultCode, data);
      }

  }

}
