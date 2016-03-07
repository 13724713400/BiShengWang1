package com.zhushan.bishengwang.Ifactory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhushan.bishengwang.Constance.BundleConstance;
import com.zhushan.bishengwang.Constance.HttpConstance;
import com.zhushan.bishengwang.Constance.SharePreferenceConstance;
import com.zhushan.bishengwang.Entry.AlbumAddEntry;
import com.zhushan.bishengwang.Entry.AuthCheckEntry;
import com.zhushan.bishengwang.Entry.AuthEntry;
import com.zhushan.bishengwang.Entry.CityEntry;
import com.zhushan.bishengwang.Entry.CommentEntry;
import com.zhushan.bishengwang.Entry.DetailsEntry;
import com.zhushan.bishengwang.Entry.DirectorSettingData;
import com.zhushan.bishengwang.Entry.DirectorSettingEntry;
import com.zhushan.bishengwang.Entry.FogetEntry;
import com.zhushan.bishengwang.Entry.FucusEntry;
import com.zhushan.bishengwang.Entry.HomePageInfoEntry;
import com.zhushan.bishengwang.Entry.HomepageDirectorSettingEntry;
import com.zhushan.bishengwang.Entry.JiuEntry;
import com.zhushan.bishengwang.Entry.LoginEntry;
import com.zhushan.bishengwang.Entry.MessageEntry;
import com.zhushan.bishengwang.Entry.MyFocusTypographerEntry;
import com.zhushan.bishengwang.Entry.MyMessageEntry;
import com.zhushan.bishengwang.Entry.MyPhotoEnrty;
import com.zhushan.bishengwang.Entry.MyPhotoEntry;
import com.zhushan.bishengwang.Entry.MyPublicEntry;
import com.zhushan.bishengwang.Entry.MyPublicJiuEntry;
import com.zhushan.bishengwang.Entry.MyPublicQiuEntry;
import com.zhushan.bishengwang.Entry.MyPublicZhiEntry;
import com.zhushan.bishengwang.Entry.PringTingPeapleEntry;
import com.zhushan.bishengwang.Entry.PrintingPeapleDirectorSettingEntry;
import com.zhushan.bishengwang.Entry.PrintingPeapleEntry;
import com.zhushan.bishengwang.Entry.PrintingPeapleEntryTwo;
import com.zhushan.bishengwang.Entry.PrivateDeleteEntry;
import com.zhushan.bishengwang.Entry.ProvinceEntry;
import com.zhushan.bishengwang.Entry.PublishPurchaseEntry;
import com.zhushan.bishengwang.Entry.QiuEntry;
import com.zhushan.bishengwang.Entry.RegisterEntry;
import com.zhushan.bishengwang.Entry.SendCodeEntry;
import com.zhushan.bishengwang.Entry.ShuaiEntry;
import com.zhushan.bishengwang.Entry.TypePepleDirectorSettingEntry;
import com.zhushan.bishengwang.Entry.TypographerEntry;
import com.zhushan.bishengwang.Entry.TypograpterEntry;
import com.zhushan.bishengwang.Entry.UploadHeaderEntry;
import com.zhushan.bishengwang.Entry.UserGreetMentEntry;
import com.zhushan.bishengwang.Entry.UserInfoEntry;
import com.zhushan.bishengwang.Entry.UserOutEntry;
import com.zhushan.bishengwang.Entry.WeinxinLoginEnrty;
import com.zhushan.bishengwang.Entry.ZhiEntry;
import com.zhushan.bishengwang.Entry.myPhoto1Entry;
import com.zhushan.bishengwang.Entry.privateEntry;
import com.zhushan.bishengwang.Iadapter.BaseEpandable;
import com.zhushan.bishengwang.Iadapter.CityAdapter;
import com.zhushan.bishengwang.Iadapter.ProvinceAdapter;
import com.zhushan.bishengwang.Itools.AlertDialogUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.OkHttpUtils;
import com.zhushan.bishengwang.Itools.RefreshLayout;
import com.zhushan.bishengwang.Itools.SharePreferenceUtils;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;
import com.zhushan.bishengwang.activity.Activity_Login;
import com.zhushan.bishengwang.activity.Activity_MyPhoto;
import com.zhushan.bishengwang.activity.Activity_UploadHeaderImg;
import com.zhushan.bishengwang.activity.Activity_detailes;
import com.zhushan.bishengwang.activity.Activity_resetPassword;
import com.zhy.http.okhttp.callback.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/15.
 */
public class HttpFactory {
    private static HttpFactory httpFactory;
    private HttpFactory(){}
    public static HttpFactory getInstance()
    {
        if (httpFactory==null)
        {
            synchronized (HttpFactory.class)
            {
                if (httpFactory==null)
                {
                    httpFactory = new HttpFactory();
                }

            }

        }

        return httpFactory;
    }
  /*  public void AddressCahnge(Context context,String address)
    {
        String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=QqT269VndwONZhfzeURGejBG&callback=showLocation";
       // OkHttpUtils.getAsyn(context, url, true);
    }
    public  void TestFactory(Context context,Map<String, String> params,String url)
    {
        OkHttpUtils.postAsyn(context, params, url, OkHttpUtils.getInstance().new MyResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                EventBus.getDefault().post(response);
            }
        });
    }*/

    public void sendAuthCode(final Context context, HashMap<String,String> params) {

        OkHttpUtils.postAsyn(context, params, HttpConstance.GETAUTHCODE, OkHttpUtils.getInstance().new MyResultCallback<SendCodeEntry>() {

            @Override
            public void onError(Request request, Exception e) {



            }

            @Override
            public void onResponse(SendCodeEntry response) {

                if (!response.getMessage().equals(""))
                {
                    Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public void weinxinLogin(final Context context, HashMap<String,String> params,final AlertDialogUtils alertDialogUtils) {

        OkHttpUtils.postAsyn(context, params, HttpConstance.WEINXINLOGIN, OkHttpUtils.getInstance().new MyResultCallback<WeinxinLoginEnrty>() {

            @Override
            public void onError(Request request, Exception e) {
                Log.i("www","e"+e);
            }

            @Override
            public void onResponse(WeinxinLoginEnrty response) {
                Log.i("www","response"+response);
                if (response.getCode()==200)
                {
                    alertDialogUtils.dissView();
                    SharePreferenceUtils.getInstance(context,SharePreferenceConstance.USERNAME).CreateSharePreference(SharePreferenceConstance.USERTOKEN,response.getData().getToken());
                    IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, null);

                }

            }
        });
    }


    public void forgetPassword(final Context context, HashMap<String,String> params,final String telephone) {

        OkHttpUtils.postAsyn(context, params, HttpConstance.FORGETPASSWORD, OkHttpUtils.getInstance().new MyResultCallback<FogetEntry>() {

            @Override
            public void onError(Request request, Exception e) {



            }

            @Override
            public void onResponse(FogetEntry response) {

                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
               if (!response.getMessage().equals(""))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("authCode",response.getData());
                    bundle.putString("telephone", telephone);
                    IntentUtils.getInstance().startToAnoterActivity(context, Activity_resetPassword.class, bundle);
                }


            }
        });
    }

    public void resetPassword(final Context context, HashMap<String,String> params) {

        OkHttpUtils.postAsyn(context, params, HttpConstance.FORGETPASSWORD, OkHttpUtils.getInstance().new MyResultCallback<FogetEntry>() {

            @Override
            public void onError(Request request, Exception e) {



            }

            @Override
            public void onResponse(FogetEntry response) {

                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
             if (!response.getMessage().equals(""))
                {
                    IntentUtils.getInstance().startToAnoterActivity(context, Activity_Login.class, null);
                }

            }
        });
    }


    public void Register(final Context context, HashMap<String,String> params) {

        OkHttpUtils.postAsyn(context, params, HttpConstance.REGISTER, OkHttpUtils.getInstance().new MyResultCallback<RegisterEntry>() {


            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(RegisterEntry response) {

                Log.i("www", "response" + response);
                if (response.getCode() == 200) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(BundleConstance.UID, response.getData().getUid());
                    IntentUtils.getInstance().startToAnoterActivity(context, Activity_UploadHeaderImg.class, bundle);
                } else {
                    Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void UploadHeadImg(final Activity context, HashMap<String,String> params,Pair...p)
    {
        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.REGISTER, new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

                Log.i("www", "上传头像" + e);

            }

            @Override
            public void onResponse(String response) {
                // Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("www", response);
              /*  if (response.getCode() == 200) {
                    SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).CreateSharePreference(SharePreferenceConstance.USERTOKEN, response.getData().getToken());
                    IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, null);
                } else {
                    Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                }
*/
            }
        }, p);
    }
    public void Login(final Activity context, HashMap<String,String> params,final String flag) {

        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.LOGIN, OkHttpUtils.getInstance().new MyResultCallback<LoginEntry>() {
            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(LoginEntry response) {
                Toast.makeText(context,response.getMessage(),Toast.LENGTH_SHORT).show();
                if (response.getCode() == 200) {
                        IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, null);
                        SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).CreateSharePreference(SharePreferenceConstance.USERTOKEN, response.getData().getToken());

                }


            }
        });

    }

    public void PublishPurchase(final Activity context, HashMap<String,String> params,final AlertDialogUtils alertDialogUtils,Pair...p) {

        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.PUBLISHPURCHASEURL, new ResultCallback<PublishPurchaseEntry>() {
            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(PublishPurchaseEntry response) {

             Toast.makeText(context,response.getMessage(),Toast.LENGTH_SHORT).show();
                if (response.getCode()==200)
                {
                    alertDialogUtils.dissView();
                 IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, null);

                }


            }
        }, p);

    }


    public void Uplaoader(final Activity context, HashMap<String,String> params,Pair...p) {

        Log.i("www","jinlai0");
        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.REGISTER, new ResultCallback<UploadHeaderEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", e + "");

            }

            @Override
            public void onResponse(UploadHeaderEntry response) {

                Log.i("www", "222" + response);
                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                if (response.getCode() == 200) {
                    IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, null);
                    SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).CreateSharePreference(SharePreferenceConstance.USERTOKEN, response.getData().getToken());
                    IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, null);
                }


            }
        }, p);

    }

    public void DirectorSetting(final ExpandableListView expandableListView, final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.getAsynCallback(context, HttpConstance.DIRECTORESETTING, OkHttpUtils.getInstance().new MyResultCallback<DirectorSettingEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(DirectorSettingEntry response) {

                if (response.getCode() == 200) {
                    List<DirectorSettingData> directorSettingEntries = response.getData();
                    expandableListView.setAdapter(new BaseEpandable(directorSettingEntries, context));
                    EventBus.getDefault().post(directorSettingEntries);
                }

            }
        });
    }

    public void HomepageDirectorSetting(final ExpandableListView expandableListView, final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.getAsynCallback(context, HttpConstance.DIRECTORESETTING, OkHttpUtils.getInstance().new MyResultCallback<HomepageDirectorSettingEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(HomepageDirectorSettingEntry response) {

                Log.i("www", "response" + response);
                if (response.getCode() == 200) {
                    List<DirectorSettingData> directorSettingEntries = response.getData();
                    expandableListView.setAdapter(new BaseEpandable(directorSettingEntries, context));
                    EventBus.getDefault().post(response);
                }
            }
        });
    }

    public void TypePeapleDirectorSetting(final ExpandableListView expandableListView, final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.getAsynCallback(context, HttpConstance.DIRECTORESETTING, OkHttpUtils.getInstance().new MyResultCallback<TypePepleDirectorSettingEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(TypePepleDirectorSettingEntry response) {

                if (response.getCode() == 200) {
                    List<DirectorSettingData> directorSettingEntries = response.getData();
                    expandableListView.setAdapter(new BaseEpandable(directorSettingEntries, context));
                    EventBus.getDefault().post(response);
                }
            }
        });
    }
    public void PrintingPeapleDirectorSetting(final ExpandableListView expandableListView, final Context context,HashMap<String,String> params)
    {

        OkHttpUtils.getAsynCallback(context, HttpConstance.DIRECTORESETTING, OkHttpUtils.getInstance().new MyResultCallback<PrintingPeapleDirectorSettingEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(PrintingPeapleDirectorSettingEntry response) {

                if (response.getCode() == 200) {
                    List<DirectorSettingData> directorSettingEntries = response.getData();
                    expandableListView.setAdapter(new BaseEpandable(directorSettingEntries, context));
                    EventBus.getDefault().post(response);
                }
            }
        });
    }



    public void PublishSuply(final Activity context, HashMap<String,String> params,final AlertDialogUtils alertDialogUtils,Pair...p) {

        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.PUBLISHPURCHASEURL, new ResultCallback<PublishPurchaseEntry>() {
            @Override
            public void onError(Request request, Exception e) {

                Log.i("www", "" + e);

            }

            @Override
            public void onResponse(PublishPurchaseEntry response) {

                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                if (response.getCode() == 200) {
                    alertDialogUtils.dissView();
                    IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, null);

                }


            }
        }, p);

    }

    public void PublishUsed(final Activity context, HashMap<String,String> params,final AlertDialogUtils alertDialogUtils,Pair...p) {

        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.PUBLISHPURCHASEURL, new ResultCallback<PublishPurchaseEntry>() {
            @Override
            public void onError(Request request, Exception e) {

                Log.i("www",""+e);

            }

            @Override
            public void onResponse(PublishPurchaseEntry response) {

                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                if (response.getCode() == 200) {
                    alertDialogUtils.dissView();
                    IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, null);

                }


            }
        }, p);

    }

    public void PublishInvite(final Activity context, HashMap<String,String> params,final AlertDialogUtils alertDialogUtils,Pair...p) {

        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.PUBLISHPURCHASEURL, new ResultCallback<PublishPurchaseEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(PublishPurchaseEntry response) {

                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                if (response.getCode() == 200) {
                    alertDialogUtils.dissView();
                    IntentUtils.getInstance().startToAnoterActivity(context, MainActivity.class, null);

                }


            }
        }, p);

    }


    public void HomePageInfoList(final Activity context, HashMap<String,String> params) {
        final AlertDialogUtils alertDialogUtils = new AlertDialogUtils(context,R.layout.loading);
        ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
        Glide.with(context).load(R.mipmap.run).into(imageView);
        alertDialogUtils.showDialog(false);
        OkHttpUtils.postAsyn(context, params, HttpConstance.HOMPAGEINFOLIST, new ResultCallback<HomePageInfoEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(HomePageInfoEntry response) {
                Log.i("www", "" + response);
                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                if (response.getCode() == 200) {
                    EventBus.getDefault().post(response);
                    alertDialogUtils.dissView();
                }
            }
        });

    }

    public void UserGreetment(final Activity context) {

        OkHttpUtils.getAsyn(context, HttpConstance.USERGREETMENT, new ResultCallback<UserGreetMentEntry>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(UserGreetMentEntry response) {
                Log.i("www", response + "");
                if (response.getCode() == 200) {
                    EventBus.getDefault().post(response);

                }

            }


        });
    }

    public void UerOut(final Activity context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.USEROUT, new ResultCallback<UserOutEntry>() {
            @Override
            public void onError(Request request, Exception e) {

                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(UserOutEntry response) {

                if (response.getCode() == 200) {
                    Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                    SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).deleteData(SharePreferenceConstance.USERTOKEN);
                    SharePreferenceUtils.getInstance(context, SharePreferenceConstance.USERNAME).deleteData(SharePreferenceConstance.USERIMG);
                }
                Log.i("www", "" + response);
            }
        });
    }

    public void UerInfo(final Context context,HashMap<String,String> params)
    {
        final AlertDialogUtils alertDialogUtils = new AlertDialogUtils(context,R.layout.loading);
        ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
        Glide.with(context).load(R.mipmap.run).into(imageView);
        alertDialogUtils.showDialog(false);
        OkHttpUtils.postAsyn(context, params, HttpConstance.USERINFO, new ResultCallback<UserInfoEntry>() {
            @Override
            public void onError(Request request, Exception e) {

                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(UserInfoEntry response) {

                if (response.getCode() == 200) {
                    EventBus.getDefault().post(response);
                    alertDialogUtils.dissView();
                }
                Log.i("www", "" + response);
            }
        });
    }


    public void UserAuth(final Activity context, HashMap<String,String> params,final AlertDialogUtils alertDialogUtils,Pair...p) {

        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.USERAUTH, new ResultCallback<UserOutEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(UserOutEntry response) {

                Log.i("www", "" + response);
                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                if (response.getCode() == 200) {
                    alertDialogUtils.dissView();
                   IntentUtils.getInstance().startToAnoterActivity(context,MainActivity.class,null
                   );
                }


            }
        }, p);

    }

    public void PrintingPeaple(final Context context,HashMap<String,String> params)
    {
        final AlertDialogUtils alertDialogUtils = new AlertDialogUtils(context,R.layout.loading);
        ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
        Glide.with(context).load(R.mipmap.run).into(imageView);
        alertDialogUtils.showDialog(false);
        OkHttpUtils.postAsyn(context, params, HttpConstance.PRINTINGPEAPLE, new ResultCallback<PrintingPeapleEntry>() {
            @Override
            public void onError(Request request, Exception e) {

                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(PrintingPeapleEntry response) {
                if (response.getCode() == 200) {
                    EventBus.getDefault().post(response);
                    alertDialogUtils.dissView();

                }

            }
        });
    }

    public void PrintingPeapleTwo(final Context context,HashMap<String,String> params,final PullToRefreshListView pullToRefreshListView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.PRINTINGPEAPLE, new ResultCallback<PrintingPeapleEntry>() {
            @Override
            public void onError(Request request, Exception e) {

                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(PrintingPeapleEntry response) {
                if (response.getCode() == 200) {
                    pullToRefreshListView.onRefreshComplete();
                    EventBus.getDefault().post(response);
                    Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Log.i("www", "" + response);
            }
        });
    }



    public void TypographerFragment(final Context context,HashMap<String,String> params)
    {
        final AlertDialogUtils alertDialogUtils = new AlertDialogUtils(context,R.layout.loading);
        ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
        Glide.with(context).load(R.mipmap.run).into(imageView);
        alertDialogUtils.showDialog(false);
        OkHttpUtils.postAsyn(context, params, HttpConstance.CUSTOMERMAIN, new ResultCallback<TypograpterEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(TypograpterEntry response) {

                Log.i("www", "" + response);
                if (response.getCode() == 200) {
                    EventBus.getDefault().post(response);
                    alertDialogUtils.dissView();
                }

            }
        });
    }



    public void Typographer(final Activity context, HashMap<String,String> params,Pair...p) {

        final AlertDialogUtils alertDialogUtils = new AlertDialogUtils(context,R.layout.loading);
        ImageView imageView = alertDialogUtils.getView(R.id.londing_img);
        Glide.with(context).load(R.mipmap.run).into(imageView);
        alertDialogUtils.showDialog(false);
        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.CUSTOMERAUTH, new ResultCallback<AlbumAddEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(AlbumAddEntry response) {

                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                if (response.getCode() == 200) {
                    alertDialogUtils.dissView();
                   IntentUtils.getInstance().startToAnoterActivity(context,MainActivity.class,null);
                }
            }
        }, p);

    }

    public void uploadMyphoto(final Activity context, HashMap<String,String> params,Pair...p) {

        OkHttpUtils.multiFileUploadAsyn(context, params, HttpConstance.UPLOADMYPHOTO, new ResultCallback<myPhoto1Entry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(myPhoto1Entry response) {

                Log.i("www",""+response);


                    EventBus.getDefault().post(response.getData());




            }
        }, p);

    }


    public void Details(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.DETAILS, new ResultCallback<DetailsEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(DetailsEntry response) {
                Log.i("www", "" + response);

                if (response.getCode() == 200) {
                    EventBus.getDefault().post(response);

                }

            }
        });
    }
    public void MyPublic(final Context context,HashMap<String,String> params,final PullToRefreshListView pullToRefreshListView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYPUBLIC, new ResultCallback<MyPublicEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(MyPublicEntry response) {
                Log.i("www", "shuai" + response);
                if (response.getCode() == 200) {
                    pullToRefreshListView.onRefreshComplete();
                    EventBus.getDefault().post(response);
                }

            }
        });
    }
    public void MyPublicJiu(final Context context,HashMap<String,String> params,final PullToRefreshListView pullToRefreshListView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYPUBLIC, new ResultCallback<MyPublicJiuEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyPublicJiuEntry response) {
                Log.i("www","jiu"+response);
                if (response.getCode()==200)
                {
                    pullToRefreshListView.onRefreshComplete();
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void MyPublicZhi(final Context context,HashMap<String,String> params,final PullToRefreshListView pullToRefreshListView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYPUBLIC, new ResultCallback<MyPublicZhiEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyPublicZhiEntry response) {
                Log.i("www","zhi"+response);
                if (response.getCode()==200)
                {
                    pullToRefreshListView.onRefreshComplete();
                    EventBus.getDefault().post(response);
                }

            }
        });
    }




    public void MyPublicQiu(final Context context,HashMap<String,String> params,final PullToRefreshListView pullToRefreshListView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYPUBLIC, new ResultCallback<MyPublicQiuEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyPublicQiuEntry response) {
                Log.i("www","qiu"+response);
                if (response.getCode()==200)
                {
                    pullToRefreshListView.onRefreshComplete();
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void HisPublicShuai(final Context context,HashMap<String,String> params,final PullToRefreshListView pullToRefreshListView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.HISPUBLISH, new ResultCallback<MyPublicEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(MyPublicEntry response) {
                Log.i("www", "tadefabu" + response);
                if (response.getCode() == 200) {
                    pullToRefreshListView.onRefreshComplete();
                    EventBus.getDefault().post(response);
                }

            }
        });
    }


    public void albumadd(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.ALBUMADD, new ResultCallback<AlbumAddEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(AlbumAddEntry response) {
                Log.i("www", "tadefabu" + response);
          if (response.getCode()==200)
                {
                  IntentUtils.getInstance().startToAnoterActivity(context, Activity_MyPhoto.class, null);
                }

            }
        });
    }


    public void city(final String flag,final Context context,HashMap<String,String> params,final ListView listView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.CITY, new ResultCallback<CityEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }

            @Override
            public void onResponse(CityEntry response) {
                Log.i("www", "tadefabu" + response);
                if (response.getCode()==200)
                {
                    listView.setAdapter(new CityAdapter(flag,context, response.getData(), R.layout.province_item));
                }

            }
        });
    }

    public void province(final String flag,final Activity context, final ListView listView)
    {
        OkHttpUtils.getAsyn(context, HttpConstance.PROVINCE, new ResultCallback<ProvinceEntry>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(ProvinceEntry response) {
                if (response.getCode()==200) {
                    listView.setAdapter(new ProvinceAdapter(flag,context, response.getData(), R.layout.province_item));
                }
            }
        });
    }


    public void HisPublicQiu(final Context context,HashMap<String,String> params,final PullToRefreshListView pullToRefreshListView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.HISPUBLISH, new ResultCallback<MyPublicQiuEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyPublicQiuEntry response) {
                Log.i("www","tadefabu"+response);
                if (response.getCode()==200)
                {
                    pullToRefreshListView.onRefreshComplete();
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void HisPublicJiu(final Context context,HashMap<String,String> params,final PullToRefreshListView pullToRefreshListView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.HISPUBLISH, new ResultCallback<MyPublicJiuEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyPublicJiuEntry response) {
                Log.i("www","tadefabu"+response);
                if (response.getCode()==200)
                {
                    pullToRefreshListView.onRefreshComplete();
                    EventBus.getDefault().post(response);
                }

            }
        });
    }
    public void HisPublicZhi(final Context context,HashMap<String,String> params,final PullToRefreshListView pullToRefreshListView)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.HISPUBLISH, new ResultCallback<MyPublicZhiEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyPublicZhiEntry response) {
                Log.i("www","tadefabu"+response);
                if (response.getCode()==200)
                {
                    pullToRefreshListView.onRefreshComplete();
                    EventBus.getDefault().post(response);
                }

            }
        });
    }



    public void Mycustomeauth(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYCUSTOMERAUTH, new ResultCallback<AuthEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(AuthEntry response) {
                Log.i("www","renzheng"+response);
               if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void Hiscustomeauth(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.HISAUTH, new ResultCallback<AuthEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(AuthEntry response) {
                Log.i("www","taderenzheng"+response);
                if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void MyPhotoDel(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYPHOTODELETE, new ResultCallback<MyPhotoEnrty>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyPhotoEnrty response) {
                Log.i("www","myphoto"+response);
             if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }


    public void SendMessage(final Activity context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.SENDMESSAGE, new ResultCallback<MessageEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MessageEntry response) {

               if (response.getCode()==200)
                {
                    Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(String.valueOf(100));
                    context.finish();

                }

            }
        });
    }


    public void SendMsg(final Activity context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.SENDMSG, new ResultCallback<FucusEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(FucusEntry response) {

                Log.i("www","re"+response);
               if (response.getCode()==200)
                {
                    Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(String.valueOf(1));
                    context.finish();
                }

            }
        });
    }

    public void GetComment(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.GETCOMMENT, new ResultCallback<CommentEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(CommentEntry response) {

                Log.i("www"," "+response);
              if (response.getCode()==200)
                {

                    EventBus.getDefault().post(response);

                }

            }
        });
    }

    public void MyComment(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYCOMMMENT, new ResultCallback<MyMessageEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyMessageEntry response) {
                Log.i("www"," "+response);
            if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void pringtingPeapleDetails(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.PRINTINGPEAPEINFO, new ResultCallback<PringTingPeapleEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(PringTingPeapleEntry  response) {
                Log.i("www"," "+response);
                if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void authCheck(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.AUTHCHECK, new ResultCallback<AuthCheckEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(AuthCheckEntry  response) {
                Log.i("www"," "+response);
               if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void myGallery(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYGALLERY, new ResultCallback<MyPhotoEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyPhotoEntry  response) {
                Log.i("www", " " + response);
               if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void focusInfo(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.FOCUSMSG, new ResultCallback<FucusEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(FucusEntry response) {
                Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
            if (response.getCode()==200)
                {
                   Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void focusPp(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.FOCUSPEAPLE, new ResultCallback<FucusEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(FucusEntry response) {
                Log.i("www", " " + response);
                if (response.getCode()==200)
                {
                    Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void focusList(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.FOCUSLIST, new ResultCallback<HomePageInfoEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(HomePageInfoEntry response) {

                if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response.getData());
                }

            }
        });
    }
    public void focusListPeaple(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.FOCUSLIST, new ResultCallback<PrintingPeapleEntryTwo>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(PrintingPeapleEntryTwo response) {

                if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void focusListtypographer(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.FOCUSLIST, new ResultCallback<MyFocusTypographerEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(MyFocusTypographerEntry response) {

             if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }


    public void focusdelete(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.FOSUSTDELETE, new ResultCallback<FucusEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(FucusEntry response) {
                Log.i("www"," "+response);
                if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }
    public void msgList(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MSGLIST, new ResultCallback<privateEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(privateEntry response) {
                Log.i("www"," "+response);
               if (response.getCode()==200)
                {
                    EventBus.getDefault().post(response);
                }

            }
        });
    }

    public void myPublishiDel(final Context context,HashMap<String,String> params,final int cateId)
    {
        final ShuaiEntry shuaiEntry = new ShuaiEntry();
        final  QiuEntry qiuEntry = new QiuEntry();
        final JiuEntry jiuEntry = new JiuEntry();
        final ZhiEntry zhiEntry = new ZhiEntry();
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYPULISHIDELETE, new ResultCallback<PrivateDeleteEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(PrivateDeleteEntry response) {
                Log.i("www", " " + response);
               if (response.getCode()==200)
                {
                  switch (cateId)
                  {
                      case 2:
                          EventBus.getDefault().post(shuaiEntry);
                          break;
                      case 1:
                          EventBus.getDefault().post(qiuEntry);
                          break;
                      case 3:
                          EventBus.getDefault().post(jiuEntry);
                          break;
                      case 4:
                          EventBus.getDefault().post(zhiEntry);
                          break;
                  }

                }

            }
        });
    }

    public void msgDel(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.PRIVATEDELETE, new ResultCallback<PrivateDeleteEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(PrivateDeleteEntry response) {
             Toast.makeText(context,response.getMessage(),Toast.LENGTH_SHORT).show();
             if (response.getCode()==200)
                {
                    EventBus.getDefault().post(String.valueOf(6));
                }

            }
        });
    }

    public void mymsgDel(final Context context,HashMap<String,String> params)
    {
        OkHttpUtils.postAsyn(context, params, HttpConstance.MYCOMMENT, new ResultCallback<PrivateDeleteEntry>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("www", "" + e);
            }
            @Override
            public void onResponse(PrivateDeleteEntry response) {
              Toast.makeText(context, response.getMessage(), Toast.LENGTH_SHORT).show();
              if (response.getCode()==200)
                {
                    EventBus.getDefault().post(String.valueOf(7));
                }

            }
        });
    }



    public interface MyPulichChange
    {
        void change(String s);
    }
private MyPulichChange myPulichChange;

    public void setMyPulichChange(MyPulichChange myPulichChange) {
        this.myPulichChange = myPulichChange;
    }

    public interface typeChange
    {
       void chage(TypograpterEntry response);
    }

    private typeChange typechange;

    public void setTypechange(typeChange typechange) {
        this.typechange = typechange;
    }
}
