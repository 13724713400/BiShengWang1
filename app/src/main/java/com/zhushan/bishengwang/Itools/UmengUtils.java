package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.tencent.connect.common.Constants;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;
import com.zhushan.bishengwang.Constance.UserConstance;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by Administrator on 2015/12/3.
 */
public class UmengUtils {
    private static UmengUtils umengUtils;
    public static final String  WEINXIN="weixin";
    public static final String  QQ="qq";
    private UmengUtils()
    {}
    public static UmengUtils getInstance()
    {
        if (umengUtils==null)
        {
            umengUtils = new UmengUtils();
        }
        return umengUtils;
    }
   public void InitPlatform(Activity activity,UMSocialService mController)
    {
        //添加qq平台
      String appId = "1105043441";
        String appKey = "OtCnyMbp2QPkd1uI";

     /*  String appId = "100424468";
        String appKey = "c7394704798a158208a74ab60104f0ba";*/
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity,appId, appKey);
        qqSsoHandler.addToSocialSDK();
       /* QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, appId, appKey);
        qZoneSsoHandler.addToSocialSDK();
*/



     /*   QZoneSsoHandler qZoneSsoHandler2 = new QZoneSsoHandler(
                activity, appId, appKey);
        qZoneSsoHandler.addToSocialSDK();*/

        //添加微信平台
        String appId2 = "wx06a9ceef6a0738d2";
        String appSecret2 = "1aa4186aa7d30ab09a002ee1bbd4e321";
        UMWXHandler wxHandler = new UMWXHandler(activity, appId2, appSecret2);
        wxHandler.addToSocialSDK();
        UMWXHandler wxCircleHandler = new UMWXHandler(activity, appId2, appSecret2);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();

    }

    /**
     * 微信和qq分享内容
     *
     */
    public void showCustomUI(final Context context,final UMSocialService mController,AlertDialog alertDialog) {
        final SocializeListeners.SnsPostListener mShareListener = new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {

            }
            @Override
            public void onComplete(SHARE_MEDIA platform, int stCode,
                                   SocializeEntity entity) {
                if (stCode == 200) {
                } else {

                }
            }
        };
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
                context);
        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.sharetofiendsdialog, null);
        LinearLayout sharetoweixin = (LinearLayout) view.findViewById(R.id.sharetoweixin);
        LinearLayout sharetoweixincommnet = (LinearLayout) view.findViewById(R.id.sharetoweixincommnet);
        LinearLayout sharetoQQ = (LinearLayout) view.findViewById(R.id.sharetoQQ);
        alertDialog.setView(view);
        alertDialog.show();
        sharetoweixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mController.directShare(context, SHARE_MEDIA.WEIXIN, mShareListener);

            }
        });

        sharetoweixincommnet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mController.directShare(context, SHARE_MEDIA.WEIXIN_CIRCLE, mShareListener);
            }
        });
        sharetoQQ.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mController.directShare(context, SHARE_MEDIA.QQ, mShareListener);

            }
        });
    }

    public void setShareContent(Activity context,UMSocialService mController,String bitmapUrl,String content,String title,String targetUrl) {
        UMImage localImage = new UMImage(context,bitmapUrl);
       /* QQShareContent qqShareContent1 = new QQShareContent();
        qqShareContent1.setShareContent(content);
        qqShareContent1.setTitle(title);
        qqShareContent1.setShareMedia(localImage);
        qqShareContent1.setTargetUrl(targetUrl);
        mController.setShareMedia(qqShareContent1);*/


        QQShareContent qqShareContent = new QQShareContent();
        if (!content.equals("")) {
            Log.i("www","进来");
            qqShareContent.setShareContent(content);
        }else{
            qqShareContent.setShareContent("内容");
        }
        qqShareContent.setShareImage(localImage);
        qqShareContent.setTitle(title);
        qqShareContent.setTargetUrl(targetUrl);
        mController.setShareMedia(qqShareContent);

        WeiXinShareContent weiXinShareContent = new WeiXinShareContent();
        if (!content.equals("")) {
            weiXinShareContent.setShareContent(content);
        }
        weiXinShareContent.setShareImage(localImage);
        weiXinShareContent.setTitle(title);
        weiXinShareContent.setTargetUrl(targetUrl);

        mController.setShareMedia(weiXinShareContent);

        CircleShareContent circleShareContent = new CircleShareContent();
        if (!content.equals("")) {
            circleShareContent.setShareContent(content);
        }
        circleShareContent.setShareImage(localImage);
        circleShareContent.setTitle(title);
        circleShareContent.setTargetUrl(targetUrl);

        mController.setShareMedia(circleShareContent);

     //   mController.openShare(context, false);//打开分享面板

    }

    public void setShareContentFriends(Activity context,UMSocialService mController,Bitmap bitmap) {
        UMImage localImage = new UMImage(context,bitmap);
       /* QQShareContent qqShareContent1 = new QQShareContent();
        qqShareContent1.setShareContent(content);
        qqShareContent1.setTitle(title);
        qqShareContent1.setShareMedia(localImage);
        qqShareContent1.setTargetUrl(targetUrl);
        mController.setShareMedia(qqShareContent1);*/


        QQShareContent qqShareContent = new QQShareContent();

        qqShareContent.setShareImage(localImage);

        mController.setShareMedia(qqShareContent);

        WeiXinShareContent weiXinShareContent = new WeiXinShareContent();

        weiXinShareContent.setShareImage(localImage);


        mController.setShareMedia(weiXinShareContent);

        CircleShareContent circleShareContent = new CircleShareContent();

        circleShareContent.setShareImage(localImage);

        mController.setShareMedia(circleShareContent);

        //   mController.openShare(context, false);//打开分享面板

    }


    public void cancleLogin(final Activity context,UMSocialService mController,final SHARE_MEDIA platform)
    {
        mController.deleteOauth(context, platform, new SocializeListeners.SocializeClientListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, SocializeEntity entity) {
                String showText = "解除" + platform.toString() + "平台授权成功";
                if (status != StatusCode.ST_CODE_SUCCESSED) {
                    showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
                }
                Toast.makeText(context, showText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Login(final Context mContext,final UMSocialService mController, SHARE_MEDIA platform,final String flag)
    {
        mController.doOauthVerify(mContext, platform, new SocializeListeners.UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                Toast.makeText(mContext, "授权开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
                Toast.makeText(mContext, "授权错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
               final  AlertDialogUtils  alertDialogUtils = new AlertDialogUtils(mContext,R.layout.loading);
                ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
                Glide.with(mContext).load(R.mipmap.run).into(imageView);
                alertDialogUtils.showDialog(false);
                Log.i("www", "授权完成");
                Toast.makeText(mContext, "授权完成", Toast.LENGTH_SHORT).show();
                //获取相关授权信息
                final String uid = value.getString("uid");
                Log.i("www", "uid" + uid);
                if (!TextUtils.isEmpty(uid)) {
                    Log.i("www","进来");
                    mController.getPlatformInfo(mContext, platform, new SocializeListeners.UMDataListener() {
                        @Override
                        public void onStart() {
                            Log.i("www","kaishi");
                            Toast.makeText(mContext, "获取平台数据开始...", Toast.LENGTH_SHORT).show();
                        }



                        @Override
                        public void onComplete(int status, Map<String, Object> info) {

                            Log.i("www",info+"");
                            if(status == 200 && info != null) {
                                if (flag.equals(WEINXIN)) {
                                    String sex = null;
                                    int sex1 = (int) info.get("sex");
                                    switch (sex1) {
                                        case 0:
                                            sex = "女";
                                            break;
                                        case 1:
                                            sex = "男";
                                            break;

                                    }
                                    HashMap<String, String> map = new HashMap<String, String>();
                                    String city = (String) info.get("city");
                                    String province = (String) info.get("province");
                                    map.put("sex", sex);
                                    map.put("nickname", (String) info.get("nickname"));
                                    map.put("unionid", (String) info.get("unionid"));
                                    map.put("city",city);
                                    map.put("loginType","weixin");
                                    map.put("province",province);
                                    if ((String) info.get("headimgurl") != null) {
                                        map.put("headimgurl", (String) info.get("headimgurl"));
                                    }
                                 //   map.put("loginType","weixin");
                                    HttpFactory.getInstance().weinxinLogin(mContext, map,alertDialogUtils);
                                }
                                if (flag.equals(QQ)) {
                                    String name= (String) info.get("screen_name");
                                    String imgUrl = (String) info.get("profile_image_url");
                                    String sex = (String) info.get("gender");
                                    String province = (String) info.get("province");
                                   String city = (String) info.get("city");
                                    HashMap<String, String> map2 = new HashMap<String, String>();
                                    map2.put("sex", sex);
                                    map2.put("nickname", name);
                                    map2.put("unionid", uid);
                                    map2.put("province",province);
                                    map2.put("loginType","qq");
                                    map2.put("city",city);
                                  //  map2.put("loginType","qq");
                                    if ((String) info.get("profile_image_url") != null) {
                                        map2.put("headimgurl", imgUrl);
                                    }
                                    HttpFactory.getInstance().weinxinLogin(mContext, map2,alertDialogUtils);

                                }
                            }


                      /*  if(status == 200 && info != null){
                            String name= (String) info.get("screen_name");
                            String imgUrl = (String) info.get("profile_image_url");
                            String sex = (String) info.get("gender");
                            String uid= (String) info.get("uid");
                            Log.i("www", "name" + name + "imgUrl" + imgUrl + "sex" + sex);
                            VolleyUtils.getInsance().getBitmapFromHttp(mContext, " ", imgUrl);
                            SharePreferenceUtils.getInstance((Activity) mContext, UserConstance.USERTB).CreateSharePreference(UserConstance.USERID, uid);
                            SharePreferenceUtils.getInstance((Activity) mContext,UserConstance.USERTB).CreateSharePreference(UserConstance.USERNAME,name);
                            SharePreferenceUtils.getInstance((Activity) mContext,UserConstance.USERTB).CreateSharePreference(UserConstance.USERSEX,sex);
                            IntentUtils.getInstance().startToAnoterActivity(mContext,MainActivity.class,null);


                         *//*   StringBuilder sb = new StringBuilder();
                            Set<String> keys = info.keySet();
                            for(String key : keys){
                                sb.append(key+"="+info.get(key).toString()+"\r\n");
                            }
                            Log.i("www",sb.toString());*//*
                           // Log.d("TestData", sb.toString());
                        }else{
                           // Log.d("TestData","发生错误："+status);
                        }*/
                        }

                    });
                }
            }


            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(mContext, "授权取消", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void login(final Context mContext,final UMSocialService mController,final SHARE_MEDIA platform) {
        mController.doOauthVerify(mContext, platform,
                new SocializeListeners.UMAuthListener() {

                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                        Toast.makeText(mContext, "授权开始",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SocializeException e,
                                        SHARE_MEDIA platform) {
                        Toast.makeText(mContext, "授权失败",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(Bundle value, SHARE_MEDIA platform) {
                        // 获取uid
                        String uid = value.getString("uid");
                        if (!TextUtils.isEmpty(uid)) {
                            // uid不为空，获取用户信息
                            getUserInfo(mContext,mController,platform);
                        } else {
                            Toast.makeText(mContext, "授权失败...",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(mContext, "授权取消",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void getUserInfo(final Context mContext,final UMSocialService mController,SHARE_MEDIA platform) {
        mController.getPlatformInfo(mContext, platform,
                new SocializeListeners.UMDataListener() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {
                        String showText = "";
                        if (status == StatusCode.ST_CODE_SUCCESSED) {
                            showText = "用户名：" +
                                    info.get("screen_name").toString();
                            Log.i("www", "信息" + info.toString());
                            Toast.makeText(mContext, "信息" + info.toString(), Toast.LENGTH_LONG).show();
                            //Log.d("#########", "##########" + info.toString());
                        } else {
                            showText = "获取用户信息失败";
                        }

                        if (info != null) {
                            Toast.makeText(mContext, info.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
