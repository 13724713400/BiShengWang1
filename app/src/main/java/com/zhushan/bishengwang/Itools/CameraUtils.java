package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhushan.bishengwang.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UnknownFormatConversionException;

/**
 * Created by Administrator on 2015/12/3.
 */
public class CameraUtils {

    public static final int ACTION_PICK1 = 2;
    public static final int CAPTURE1=1;

    public static final int ACTION_PICK2 = 4;
    public static final int CAPTURE2=3;

    public static final int ACTION_PICK3 = 6;
    public static final int CAPTURE3=5;


    public static final int ACTION_PICK4 =10 ;
    public static final int CAPTURE4=9;

    public static final int ACTION_PICK5 = 12;
    public static final int CAPTURE5=11;

    public static final int ACTION_PICK6 = 14;
    public static final int CAPTURE6=13;
    public static final int RECORDER=7;
    private static Dialog alertDialog;
    public static void photograph(final Activity context,final int cameraTag,final int picTag) {
        alertDialog = new Dialog(context, R.style.my_dialog);
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.comment_dialog, null);
        TextView textView = (TextView) view.findViewById(R.id.coomment_from_camara);
        TextView comment_from_cancle = (TextView) view.findViewById(R.id.comment_from_cancle);
        TextView comment_from_paoto = (TextView) view.findViewById(R.id.comment_from_paoto);
        comment_from_paoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IntentUtils.getInstance().isIntentAvailable(context,Intent.ACTION_PICK)) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    context.startActivityForResult(intent, picTag);
                }else{
                    Toast.makeText(context, "没有相册", Toast.LENGTH_LONG).show();
                }
            }
        });
        comment_from_cancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alertDialog.dismiss();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IntentUtils.getInstance().isIntentAvailable(context,Intent.ACTION_PICK)) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    context.startActivityForResult(intent, cameraTag);
                }else {
                    Toast.makeText(context, "无照相机", Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialog.setContentView(view);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
       // window.setWindowAnimations(R.style.alertDialog);
    }


    public static void photographByFragment(final Activity context,final Fragment fragment,final int cameraTag,final int picTag) {
        alertDialog = new Dialog(context, R.style.my_dialog);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.comment_dialog, null);
        TextView textView = (TextView) view.findViewById(R.id.coomment_from_camara);
        TextView comment_from_cancle = (TextView) view.findViewById(R.id.comment_from_cancle);
        TextView comment_from_paoto = (TextView) view.findViewById(R.id.comment_from_paoto);
        comment_from_paoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IntentUtils.getInstance().isIntentAvailable(context,Intent.ACTION_PICK)) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    fragment.startActivityForResult(intent, picTag);
                }else{
                    Toast.makeText(context, "没有相册", Toast.LENGTH_LONG).show();
                }
            }
        });
        comment_from_cancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alertDialog.dismiss();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IntentUtils.getInstance().isIntentAvailable(context,Intent.ACTION_PICK)) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    fragment.startActivityForResult(intent, cameraTag);
                }else {
                    Toast.makeText(context, "无照相机", Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialog.setContentView(view);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        // window.setWindowAnimations(R.style.alertDialog);
    }
    public static String getCameraBitmap(Intent data,ImageView imageView) throws FileNotFoundException {

            if (!SDCardUtils.isSDCardEnable()) {
                return null;
            }

        if (data==null)
        {
            return null;

        }
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Log.i("www", "压缩前的图【图片大小" + bitmap.getHeight() + "   " + bitmap.getWidth());
            String path2 =  ImageUtil.saveFile(bitmap);
            String path = ImageUtil.saveFile(ImageUtil.setImageOptions(300, 300, path2));
            Log.i("www","压缩后的图片路径"+path);
         if (path==null)
         {
            return null;
         }
        if (imageView!=null)
        {
            ImageUtil.setImageByPath(imageView, path);
        }
            return path;
    }

    public static String getCameraBitmapCircle(Intent data,ImageView imageView) throws FileNotFoundException {

        if (!SDCardUtils.isSDCardEnable()) {
            return null;
        }

        if (data==null)
        {
            return null;

        }
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        Log.i("www", "压缩前的图【图片大小" + bitmap.getHeight() + "   " + bitmap.getWidth());
        String path2 =  ImageUtil.saveFile(bitmap);
        String path = ImageUtil.saveFile(ImageUtil.setImageOptions(500,500,path2));
        Log.i("www","压缩后的图片路径"+path);
        if (path==null)
        {
            return null;
        }
        if (imageView!=null)
        {
            ImageUtil.setImageByPath(imageView, path,ImageUtil.ISCIRCLE);
        }
        return path;
    }



    public static void dialogdiss()
    {
        if (alertDialog==null)
        {
            return;

        }

        alertDialog.dismiss();

    }

    public static void startRecorder(Activity context,int RecoderTag)
    {
        if(IntentUtils.getInstance().isIntentAvailable(context,MediaStore.Audio.Media.RECORD_SOUND_ACTION)) {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            context.startActivityForResult(intent, RecoderTag);
        }else {
            Toast.makeText(context, "无录音设备", Toast.LENGTH_LONG).show();
        }
    }

    public static  String getAudioInfo(Activity activity,Intent data)
    {
        Uri uri = data.getData();
        String filePath = getAudioFilePathFromUri(activity, uri);
        Log.i("www","filePaht"+filePath);
        return filePath;
    }
    public static String getAudioFilePathFromUri(Activity activity,Uri uri){
        Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
        return  cursor.getString(index);
    }
    public static String getPicBitmap(Intent data,Activity activity,ImageView imageView)
    {
        Uri uri = data.getData();
        String[] filePathColum = {MediaStore.Images.Media.DATA};
        Cursor cursor =  activity.getContentResolver().query(uri, filePathColum, null, null, null);
        cursor.moveToFirst();
        String picturePath = cursor.getString(cursor.getColumnIndex(filePathColum[0]));
        String path=null;
        try {
             path = ImageUtil.saveFile(ImageUtil.setImageOptions(300, 300, picturePath));
            Log.i("www","压缩后的图片路径"+path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //   Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
        if(imageView!=null)
        {
            ImageUtil.setImageByPath(imageView, picturePath);
           /* Log.i("www","bitmap"+bitmap);
            imageView.setImageBitmap(bitmap);*/
        }
        if (path==null)
        {
            return null;

        }



        return path;

    }


    public static String getPicBitmapCilcle(Intent data,Activity activity,ImageView imageView)
    {
        Uri uri = data.getData();
        String[] filePathColum = {MediaStore.Images.Media.DATA};
        Cursor cursor =  activity.getContentResolver().query(uri, filePathColum, null, null, null);
        cursor.moveToFirst();
        String picturePath = cursor.getString(cursor.getColumnIndex(filePathColum[0]));
        String path=null;
        try {
            path = ImageUtil.saveFile(ImageUtil.setImageOptions(500, 500, picturePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(imageView!=null)
        {
            ImageUtil.setImageByPath(imageView, picturePath,ImageUtil.ISCIRCLE);
           /* Log.i("www","bitmap"+bitmap);
            imageView.setImageBitmap(bitmap);*/
        }
        if (path==null)
        {
            return null;

        }

        return path;

    }


}







