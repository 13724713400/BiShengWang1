package com.zhushan.bishengwang.Itools;

import android.util.Log;

import com.zhushan.bishengwang.Itools.FTP.UploadProgressListener;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/12/17.
 */
public class FTPUtils {

    private static FTPUtils ftpUtils;

    private UploadSuccess uploadSuccess;

    public void setUploadSuccess(UploadSuccess uploadSuccess) {
        this.uploadSuccess = uploadSuccess;
    }

    public interface UploadSuccess
    {
        void success();
    }
    private FTPUtils(){}

    public static FTPUtils getInstance()
    {
        if (ftpUtils==null)
        {
            synchronized (FTPUtils.class)
            {
                if (ftpUtils==null)
                {
                    ftpUtils = new FTPUtils();

                }

            }

        }

        return ftpUtils;
    }

    public void downloadSingleFile(final String servePaht,final String localFile,final String filename)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    new FTP().downloadSingleFile(servePaht, localFile, filename, new FTP.DownLoadProgressListener() {
                        @Override
                        public void onDownLoadProgress(String currentStep, long downProcess, File file) {
                            if(currentStep.equals(FTP.FTP_UPLOAD_SUCCESS)){
                                Log.d("www", "-----xiazai--successful");
                            } else if(currentStep.equals(FTP.FTP_UPLOAD_LOADING)){

                                Log.d("www", "-----xiazai---shibai" + "%");
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 上传多个文件
     * @param file
     * @param dirPath
     */
    public void uploadMultiFile( final LinkedList<File> file,final String dirPath)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    new FTP().uploadMultiFile(file, dirPath, new UploadProgressListener() {
                        @Override
                        public void onUploadProgress(String currentStep, long uploadSize, File file) {

                            if(currentStep.equals(FTP.FTP_UPLOAD_SUCCESS)){
                               // uploadSuccess.success();
                                Log.d("www", "-----shanchuan--successful");
                            } else if(currentStep.equals(FTP.FTP_UPLOAD_LOADING)){
                                long fize = file.length();
                                float num = (float)uploadSize / (float)fize;
                                int result = (int)(num * 100);
                                Log.d("www", "-----shangchuan---"+result + "%");
                            }
                        }

                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 上传单个文件
     * @param file
     * @param dirPath
     *
     *
     */
    public void uploadSingleFile( final File file,final String dirPath)
    {
        Log.i("www","上传");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    new FTP().uploadSingleFile(file,dirPath, new UploadProgressListener() {
                        @Override
                        public void onUploadProgress(String currentStep, long uploadSize, File file) {

                            if(currentStep.equals(FTP.FTP_UPLOAD_SUCCESS)){
                                Log.i("www", "-----shanchu--success");
                                uploadSuccess.success();
                            } else if(currentStep.equals(FTP.FTP_UPLOAD_FAIL)){
                                long fize = file.length();
                                float num = (float)uploadSize / (float)fize;
                                int result = (int)(num * 100);
                                Log.i("www", "-----shangchuan---" + result + "%");
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("www","   "+e);
                }

            }
        });



    }


}
