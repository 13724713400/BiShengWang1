package com.zhushan.bishengwang.Itools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/4.
 */
public class ZxingUtils {
    private static ZxingUtils zxingUtils;
    private ZxingUtils(){}
    public static ZxingUtils getInstance()
    {
        if (zxingUtils==null)
        {
            zxingUtils = new ZxingUtils();
        }
        return zxingUtils;
    }

    /**
     * 带图片的二维码
     * @param content
     * @param widthPix
     * @param heightPix
     * @param logoBm
     * @param filePath
     * @return
     */
    public Bitmap createQRImage(Context context,String content, int widthPix, int heightPix, Bitmap logoBm, String filePath,ImageView imageView) {
       try {
            if (content == null || "".equals(content)) {
               return null;
             }


          Map<EncodeHintType, Object> hints = new HashMap<>();
           hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
           //容错级别 
           hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
           //设置空白边距的宽度 
//            hints.put(EncodeHintType.MARGIN, 2); //default is 4 

           // 图像数据转换，使用了矩阵转换 
           BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);
           int[] pixels = new int[widthPix * heightPix];
           // 下面这里按照二维码的算法，逐个生成二维码的图片， 
           // 两个for循环是图片横列扫描的结果 
          for (int y = 0; y < heightPix; y++) {
              for (int x = 0; x < widthPix; x++) {
                   if (bitMatrix.get(x, y)) {
                       pixels[y * widthPix + x] = 0xff000000;
                       } else {
                      pixels[y * widthPix + x] = 0xffffffff;
                      }
                    }
               }
           // 生成二维码图片的格式，使用ARGB_8888 
           Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
           bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);

          if (logoBm != null) {
               bitmap = addLogo(bitmap, logoBm);
               }
           if(bitmap != null)
           {
               if(DisLruCacheUtils.getInstance().getValue(context,"wodeerweima",DisLruCacheUtils.BITMAP)==null) {
                   DisLruCacheUtils.getInstance().putValue(context, "wodeerweima", bitmap);
               }
              /* bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
               Bitmap bitmap1 = BitmapFactory.decodeFile(filePath);*/
              // Log.i("www", "图片" + bitmap1);
               if (imageView!=null) {
                   imageView.setImageBitmap((Bitmap) DisLruCacheUtils.getInstance().getValue(context, "wodeerweima", DisLruCacheUtils.BITMAP));
               }
               return bitmap;
           }


           //必须使用compress方法将bitmap保存到文件中再进行读取。直接返回的bitmap是没有任何压缩的，内存消耗巨大！ 

           } catch (WriterException e) {
           e.printStackTrace();
           }
      return null;
       }
      private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
            }

        if (logo == null) {
            return src;
            }

       //获取图片的宽高 
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
       int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
           return null;
           }

       if (logoWidth == 0 || logoHeight == 0) {
            return src;
            }

       //logo大小为二维码整体大小的1/5 
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

           canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
            } catch (Exception e) {
            bitmap = null;
           e.getStackTrace();
           }

       return bitmap;
       }




        /**
         * @方法功能说明: 生成二维码图片,实际使用时要初始化sweepIV,不然会报空指针错误
         * @作者:饶正勇
         * @时间:2013-4-18上午11:14:16
         * @参数: @param url 要转换的地址或字符串,可以是中文
         * @return void
         * @throws
         */
        //要转换的地址或字符串,可以是中文
        public void createQRImage(ImageView imageView,String url,int QR_WIDTH,int QR_HEIGHT)
        {
            try
            {
                //判断URL合法性
                if (url == null || "".equals(url) || url.length() < 1)
                {
                    return;
                }
                Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                //图像数据转换，使用了矩阵转换
                BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
                int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
                //下面这里按照二维码的算法，逐个生成二维码的图片，
                //两个for循环是图片横列扫描的结果
                for (int y = 0; y < QR_HEIGHT; y++)
                {
                    for (int x = 0; x < QR_WIDTH; x++)
                    {
                        if (bitMatrix.get(x, y))
                        {
                            pixels[y * QR_WIDTH + x] = 0xff000000;
                        }
                        else
                        {
                            pixels[y * QR_WIDTH + x] = 0xffffffff;
                        }
                    }
                }
                //生成二维码图片的格式，使用ARGB_8888
                Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
                //显示到一个ImageView上面
                imageView.setImageBitmap(bitmap);
            }
            catch (WriterException e)
            {
                e.printStackTrace();
            }
        }
    }

