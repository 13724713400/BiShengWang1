package com.zhushan.bishengwang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.appevents.AppEventsConstants;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.laiwang.media.LWDynamicShareContent;
import com.umeng.socialize.laiwang.media.LWShareContent;
import com.umeng.socialize.media.GooglePlusShareContent;
import com.umeng.socialize.media.MailShareContent;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.RenrenShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.TwitterShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.RenrenSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;
import com.zhushan.bishengwang.Ifactory.HttpFactory;
import com.zhushan.bishengwang.Itools.AutoCompleteTextViewUtils;
import com.zhushan.bishengwang.Itools.EdiTextUtils;
import com.zhushan.bishengwang.Itools.IntentUtils;
import com.zhushan.bishengwang.Itools.UmengUtils;
import com.zhushan.bishengwang.Iview.MainVu;
import com.zhushan.bishengwang.MainActivity;
import com.zhushan.bishengwang.R;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Activity_Login extends Basetivity implements View.OnClickListener {

    private UMSocialService mController = UMServiceFactory
            .getUMSocialService("com.umeng.login");
    private ImageView login_QQlogin, login_weixinlogin;
    private Button login_btn;
    private final static String APP_ID = "wx06a9ceef6a0738d2";
    private  final static String APP_SECRET="1aa4186aa7d30ab09a002ee1bbd4e321";
    private IWXAPI api;
    private BaseResp resp = null;
    private AutoCompleteTextViewUtils autoCompleteTextViewUtils;
    private EditText Login_phone_number, Login_pssword;
    private TextView login_register, login_feedback, login_forgetpassword;
    private MainVu mainVu = new MainVu();
    private String flag;
    public static IWXAPI WXapi;
    private String weixinCode;
    SendMessageToWX.Req scene;
    private void regToWx(){

        api=WXAPIFactory.createWXAPI(this,APP_ID,true);
        api.registerApp(APP_ID);
    }
    private String text1;
    private final static int LOGIN_WHAT_INIT = 1;
    private static String get_access_token = "";
    // 获取第一步的code后，请求以下链接获取access_token
    public static String GetCodeRequest = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx06a9ceef6a0738d2&secret=1aa4186aa7d30ab09a002ee1bbd4e321&code=CODE&grant_type=authorization_code";
    //获取用户个人信息
    public static String GetUserInfo="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mainVu.initByLayout(this, R.layout.activity_login));
        initView();
        initData();
        initListener();

    }

    public void  sendResp(BaseResp resp){

    }
    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            flag = bundle.getString("flag");
        }
        //  autoCompleteTextViewUtils = new AutoCompleteTextViewUtils();
        //  autoCompleteTextViewUtils.initAutoCompleteText(Activity_Login.this, autoCompleteTextView_phoneNumer);
        UmengUtils.getInstance().InitPlatform(Activity_Login.this, mController);
        //  autoCompleteTextView_phoneNumer.showDropDown();
        // UmengUtils.getInstance().cancleLogin(Activity_Login.this,mController,SHARE_MEDIA.QQ);
        // UmengUtils.getInstance().InitPlatform(Activity_Login.this);
    }

    @Override
    public void initListener() {
        login_weixinlogin.setOnClickListener(this);
        login_QQlogin.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        login_register.setOnClickListener(this);
        login_feedback.setOnClickListener(this);
        login_forgetpassword.setOnClickListener(this);
    }

    @Override
    public void initView() {
        login_QQlogin = mainVu.getItemView(R.id.login_QQlogin);
        login_weixinlogin = mainVu.getItemView(R.id.login_weixinlogin);
        login_btn = mainVu.getItemView(R.id.login);
        Login_phone_number = mainVu.getItemView(R.id.Login_phone_number);
        Login_pssword = mainVu.getItemView(R.id.Login_pssword);
        login_register = mainVu.getItemView(R.id.login_register);
        login_feedback = mainVu.getItemView(R.id.login_feedback);
        login_forgetpassword = mainVu.getItemView(R.id.login_forgetpassword);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 根据requestCode获取对应的SsoHandler
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
                resultCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_forgetpassword:
                IntentUtils.getInstance().startToAnoterActivity(Activity_Login.this, Activity_ForgetPassword.class, null);
                break;

            case R.id.login_feedback:
                IntentUtils.getInstance().startToAnoterActivity(Activity_Login.this, Activity_feedBack.class, null);
                break;

            case R.id.login_QQlogin:
                Login(Activity_Login.this, mController, SHARE_MEDIA.QQ, UmengUtils.QQ);
                break;
            case R.id.login_weixinlogin:
                WXLogin();
                //   Toast.makeText(Activity_Login.this,"top"+ UmengUtils.getInstance().getAge()+"button", Toast.LENGTH_SHORT).show();
                // UmengUtils.getInstance().Login(Activity_Login.this, mController, SHARE_MEDIA.WEIXIN, UmengUtils.WEINXIN);
                break;

            case R.id.login_register:
                IntentUtils.getInstance().startToAnoterActivity(Activity_Login.this, Activity_Register.class, null);
                break;

            case R.id.login:

                if (!EdiTextUtils.getInstance().CheckedEmpty(Login_phone_number, getResources().getString(R.string.register_emptyphone))) {
                    return;
                }

                if (!EdiTextUtils.getInstance().CheckedEmpty(Login_pssword, getResources().getString(R.string.register_emptypssword))) {
                    return;

                }

                if (!EdiTextUtils.getInstance().CheckedPassword(Login_pssword, getResources().getString(R.string.register_emptypssword_less))) {
                    return;

                }

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("username", Login_phone_number.getText().toString().trim());
                map.put("pwd", Login_pssword.getText().toString().trim());
                HttpFactory.getInstance().Login(Activity_Login.this, map, flag);

                //   IntentUtils.getInstance().startToAnoterActivity(Activity_Login.this, MainActivity.class,null);
                //autoCompleteTextViewUtils.saveHistory(Activity_Login.this, autoCompleteTextView_phoneNumer);
                break;
        }


    }

    public void Login(final Context mContext, final UMSocialService mController, SHARE_MEDIA platform, final String flag) {
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
            public void onComplete(final Bundle value, SHARE_MEDIA platform) {

                String uid = value.getString("uid");
                if (!TextUtils.isEmpty(uid)) {
                    // uid不为空，获取用户信息
                    getUserInfo(mContext, mController, platform);

                } else {
                    Toast.makeText(mContext, "授权失败...",
                            Toast.LENGTH_LONG).show();
                }


            }/*SharedPreferences sp = getActivity().getSharedPreferences("config.txt", Context.MODE_PRIVATE);
            String tel = sp.getString("title","0");*/


            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(mContext, "授权取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserInfo(final Context mContext, final UMSocialService mController, SHARE_MEDIA platform) {
        mController.getPlatformInfo(mContext, platform,
                new SocializeListeners.UMDataListener() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {
                        if (status == StatusCode.ST_CODE_SUCCESSED) {
                            final String name = (String) info.get("screen_name");
                            final String imgUrl = (String) info.get("profile_image_url");
                            final String sex = (String) info.get("gender");
                            // Toast.makeText(mContext,"用户名"+name+"\t头像"+imgUrl+"\t性别"+sex, Toast.LENGTH_SHORT).show();
                            IntentUtils.getInstance().startToAnoterActivity(mContext, MainActivity.class, null);
                            saveToSharedPreference(name, imgUrl, sex);
                            //    Toast.makeText(mContext,"用户名" +saveToSharedPreference(name,imgUrl,sex), Toast.LENGTH_SHORT).show();
                            //Log.d("#########", "##########" + info.toString());

                        } else {
                            String showText = "获取用户信息失败";
                        }
                        if (info != null) {
                          /*  Toast.makeText(mContext, info.toString(),
                                    Toast.LENGTH_LONG).show();*/
                        }
                    }
                });
    }

    /*  public static String loginByGet(String userid){
          try{
              String path= "http://syw1643380001.my3w.com/php/deliver_data.php?userid="+URLEncoder.encode(userid,"utf-8");
              URL url=new URL(path);
              HttpURLConnection conn=(HttpURLConnection)url.openConnection();
              conn.setConnectTimeout(5000);
              conn.setRequestMethod("GET");

              int code=conn.getResponseCode();
              if(code==200){
                  InputStream is=conn.getInputStream();
                  String text=StreamTools.readInputStream(is);
                  return text;
              }else{
                  return null;
              }
          }catch (Exception e) {
              // TODO: handle exception
              e.printStackTrace();
          }
          return null;
      }*/
    private void WXLogin() {
        Log.i("www","login");
        WXapi = WXAPIFactory.createWXAPI(this, APP_ID, true);
        Log.i("www","login2");
        WXapi.registerApp(APP_ID);
        Log.i("www","login3");
        SendAuth.Req req = new SendAuth.Req();
        Log.i("www","login4");
        req.scope = "snsapi_userinfo";
        Log.i("www","login5");
        req.state = "wechat_sdk_demo";
        Log.i("www","login6");
        WXapi.sendReq(req);
        Log.i("www","login7");

    }



    public String saveToSharedPreference(String name, String imgUrl, String sex) {
        SharedPreferences sp = Activity_Login.this.getSharedPreferences("config.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", name);
        editor.putString("imgUrl", imgUrl);
        editor.putString("sex", sex);
        editor.putBoolean("success", true);
        editor.commit();
        String tel = sp.getString("name", "没有获取到值");

        //  Toast.makeText(getActivity(),tel,Toast.LENGTH_SHORT).show();
        return tel;
    }


    public static String urlEnodeUTF8(String str) {
        Log.i("www",str);
        String result = str;
        Log.i("www",str);
        try {
            Log.i("www",str);
            result = URLEncoder.encode(str, "UTF-8");
            Log.i("www",result);
        } catch (Exception e) {
            Log.i("www","resutl="+result);
            e.printStackTrace();
            Log.i("www",str);
        }
        Log.i("www result=",result);
        return result;
    }


    /**
     * 获取access_token的URL（微信）
     * @param code 授权时，微信回调给的
     * @return URL
     */
    public static String getCodeRequest(String code) {
        Log.i("www",code);
        String result = null;
        GetCodeRequest = GetCodeRequest.replace("APPID",
                urlEnodeUTF8(APP_ID));
        Log.i("www",code);
        GetCodeRequest = GetCodeRequest.replace("SECRET",
                urlEnodeUTF8(APP_SECRET));
        Log.i("www",code);
        GetCodeRequest = GetCodeRequest.replace("CODE",urlEnodeUTF8( code));
        result = GetCodeRequest;
        Log.i("www",code);
        return result;
    }
    /**
     * 获取用户个人信息的URL（微信）
     * @param access_token 获取access_token时给的
     * @param openid 获取access_token时给的
     * @return URL
     */
    public static String getUserInfo(String access_token,String openid){
        Log.i("www",access_token);
        String result = null;
        GetUserInfo = GetUserInfo.replace("ACCESS_TOKEN",
                urlEnodeUTF8(access_token));
        Log.i("www",access_token);
        GetUserInfo = GetUserInfo.replace("OPENID",
                urlEnodeUTF8(openid));
        Log.i("www",access_token);
        result = GetUserInfo;
        Log.i("www",result);
        return result;
    }

    public  Runnable downloadRun = new Runnable() {

        @Override
        public void run() {
            WXGetAccessToken();

        }
    };
    @Override
    protected void onResume() {
        super.onResume();
			/*
			 * resp是你保存在全局变量中的
			 */

   /*     if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            // code返回
            weixinCode = ((SendAuth.Resp)resp).code;
				*//*
				 * 将你前面得到的AppID、AppSecret、code，拼接成URL
				 *//*
            get_access_token = getCodeRequest(weixinCode);
            Thread thread=new Thread(downloadRun);
            Log.i("www", weixinCode);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/
    }
    /**
     * 获取access_token等等的信息(微信)
     */
    private  void WXGetAccessToken(){
        HttpClient get_access_token_httpClient = new DefaultHttpClient();
        HttpClient get_user_info_httpClient = new DefaultHttpClient();
        String access_token="";
        String openid ="";
        try {
            HttpPost postMethod = new HttpPost(get_access_token);
            HttpResponse response = get_access_token_httpClient.execute(postMethod); // 执行POST方法
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                InputStream is = response.getEntity().getContent();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));
                String str = "";
                StringBuffer sb = new StringBuffer();
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                is.close();
                String josn = sb.toString();
                JSONObject json1 = new JSONObject(josn);
                access_token = (String) json1.get("access_token");
                openid = (String) json1.get("openid");


            } else {
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String get_user_info_url=getUserInfo(access_token,openid);
        WXGetUserInfo(get_user_info_url);
    }

    /**
     * 获取微信用户个人信息
     * @param get_user_info_url 调用URL
     */
    private  void WXGetUserInfo(String get_user_info_url){
        HttpClient get_access_token_httpClient = new DefaultHttpClient();
        String openid="";
        String nickname="";
        String headimgurl="";
        try {
            HttpGet getMethod = new HttpGet(get_user_info_url);
            HttpResponse response = get_access_token_httpClient.execute(getMethod); // 执行GET方法
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                InputStream is = response.getEntity().getContent();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));
                String str = "";
                StringBuffer sb = new StringBuffer();
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                is.close();
                String josn = sb.toString();
                JSONObject json1 = new JSONObject(josn);
                openid = (String) json1.get("openid");
                nickname = (String) json1.get("nickname");
                headimgurl=(String)json1.get("headimgurl");

            } else {
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
