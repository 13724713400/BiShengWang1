package com.zhushan.bishengwang.Itools;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageUtil
{
    private static int compress = 100;
    public static String ISCIRCLE = "circle";
    public static String ISROUND="round";
    public static String ISOTHER="other";
    public static boolean isHasSdcard()
    {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static String getSDPath()
    {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 閸掋倖鏌噑d閸椻剝妲搁崥锕�摠閿燂拷
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();// 閼惧嘲褰囩捄鐔烘窗閿燂拷
        }
        return sdDir.toString() + "/";
    }
    
    public static String saveFilePath(String picName)
        throws FileNotFoundException
    {
        
        File dir = new File(getSDPath());
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        
        String tmpFilePath = getSDPath() + picName;
        
        /*
         * File tmpFile = new File(tmpFilePath); FileOutputStream fileOut = new FileOutputStream(tmpFile);
         * btp.compress(CompressFormat.JPEG, compress, fileOut);
         */
        return tmpFilePath;
    }
    
    public static String saveFile(Bitmap btp)
        throws FileNotFoundException
    {
        
        File dir = new File(getSDPath());
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        
      
        String tmpFilePath = getSDPath() + initPath()+".png";
        File tmpFile = new File(tmpFilePath);
        FileOutputStream fileOut = new FileOutputStream(tmpFile);
        if (btp==null)
        {
            return null;
        }

        btp.compress(CompressFormat.JPEG, compress, fileOut);
        return tmpFilePath;
    }

    public static String saveImage(Context context,Bitmap bit)
        throws FileNotFoundException
    {
        File dir = new File(getSDPath() + "fqy/image/");
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        boolean result = false;
        String tmpFilePath = getSDPath() + "fqy/image/" + initPath();
        Log.i("www","本地图片的路径"+tmpFilePath);
        File tmpFile = new File(tmpFilePath);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile));
        result = bit.compress(CompressFormat.JPEG, compress, bos);
        try
        {
            bos.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try
        {
            bos.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //扫描图片
        fileScan(context, getSDPath() + "fqy/image/");
        return tmpFilePath;
    }
    /**\
     * 对图片进行压缩
     * @param
     * @return 
     * @return
     * @throws Exception
     */
    private static String  initPath() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        String filename = simpleDateFormat.format(date);
        Random random = new Random();
        filename = filename+random.nextInt();
        filename = filename.substring(0,15);
        return  filename;
    }

    public static void setImageByPath(final ImageView imageView,final String ImagePath) {
                int imgw = imageView.getWidth();
                int imgh = imageView.getHeight();
                if (imgw == 0) {
                    return;
                }
                if (imgh == 0) {
                    return;
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(ImagePath, options);
                int bmw = options.outWidth;
                int bmh = options.outHeight;
                int scale = Math.min(bmw / imgw, bmh / imgh);
                options.inJustDecodeBounds = false;
                options.inSampleSize = scale;
                Bitmap bitmap = BitmapFactory.decodeFile(ImagePath, options);
                //  Log.i("www","压缩后的图片大小"+bitmap.getHeight()+"   "+bitmap.getWidth()+"缩放比例"+scale);
                imageView.setImageBitmap(bitmap);


    }

    public static Bitmap  setImageOptions(int imgw,int imgh,String ImagePath)
    {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(ImagePath, options);
        int bmw = options.outWidth;
        int bmh = options.outHeight;
        int scale = Math.min(bmw / imgw, bmh / imgh);
        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeFile(ImagePath, options);
        Log.i("www","压缩前的大小"+bmw+"  "+bmh+"压缩后的图片大小"+bitmap.getHeight()+"   "+bitmap.getWidth()+"缩放比例"+scale);
         return bitmap;


    }



    public static void setImageByPath(final ImageView imageView,final String ImagePath,final String ImageType)
    {

        if (imageView==null)
        {
            return;

        }

        if (ImagePath==null)
        {
            return;

        }

        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int  imgw =  imageView.getWidth();
                int  imgh =  imageView.getHeight();

                if (imgw==0)
                {
                    return;
                }
                if (imgh==0)
                {
                    return;
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(ImagePath, options);
                int bmw = options.outWidth;
                int bmh = options.outHeight;
                int scale = Math.min(bmw / imgw, bmh / imgh);
                options.inJustDecodeBounds = false;
                options.inSampleSize = scale;
                Bitmap bitmap1=null;
                if ((bitmap1=LruImageCache.instance().getBitmap(ImagePath))!=null)
                {

                }else{
                    Log.i("www","第一次");
                    Bitmap bitmap = BitmapFactory.decodeFile(ImagePath, options);
                    LruImageCache.instance().putBitmap(ImagePath,bitmap);
                    bitmap1 =  LruImageCache.instance().getBitmap(ImagePath);
                }



                if (bitmap1==null)
                {
                    return;
                }

                if (ImageType.equals(ISCIRCLE))
                {
                    imageView.setImageBitmap(toRoundBitmap(bitmap1));
                }

                if (ImageType.equals(ISROUND))
                {
                    imageView.setImageBitmap(toRoundCorner(bitmap1,14));
                }

                if (ImageType.equals(ISOTHER))
                {
                    imageView.setImageBitmap(bitmap1);

                }
                // Log.i("www","宽和高"+imgh+"  "+imgw);
            }
        });


    }

    public static byte[] readInputStream(InputStream inStream)
        throws Exception
    {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1)
        {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }
    
    public static Bitmap drawableToBitmap(Drawable drawable)
    {
        
        Bitmap bitmap =
            Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }
    
    /**
     * 閹跺﹤娴�?悧鍥у綁閹存劕娓鹃敓锟�?     * 
     * @param bitmap 鐟曚椒鎱ㄩ弨鍦畱閸ュ墽澧�     * @param pixels 閸﹀棜顫楅惃鍕К
     * @return 閸﹀棜顫楅崶鍓у
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels)
    {
        
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
    
    /**
     * 娴ｅ灝娓剧憴鎺戝閼宠姤鏁幐涓卛tampDrawable
     * 
     * @param bitmapDrawable
     * @param pixels
     * @return
     */
    public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable, int pixels)
    {

        Bitmap bitmap = bitmapDrawable.getBitmap();
        bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));
        return bitmapDrawable;
    }
    //扫描文件
    public static void fileScan(Context context ,String filePath){     
        Uri data = Uri.parse("file://"+filePath);     
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, data));     
}
}
