package com.zhushan.bishengwang.Itools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public  class SoundMeter{
    static final private double EMA_FILTER = 0.6;  
  
    private MediaRecorder mRecorder = null;  
    private double mEMA = 0.0;
    private MediaPlayer mediaPlayer;
    private StopPlay stopPlay;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setStopPlay(StopPlay stopPlay) {
        this.stopPlay = stopPlay;
    }

    public String start(){
        if (!Environment.getExternalStorageState().equals(  
                android.os.Environment.MEDIA_MOUNTED)) {  
            return null;
        }

        fileName = initPath()+".amr";
        if (mRecorder == null) {  
            mRecorder = new MediaRecorder();  
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);  
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  
            mRecorder.setOutputFile(android.os.Environment.getExternalStorageDirectory()+"/"+fileName);
            try {  
                mRecorder.prepare();  
                mRecorder.start();
                mEMA = 0.0;  
            } catch (IllegalStateException e) {  
                System.out.print(e.getMessage());  
            } catch (IOException e) {  
                System.out.print(e.getMessage());  
            }  
  
        }

        return fileName;
    }
    public  String  initPath() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        String filename = simpleDateFormat.format(date);
        Random random = new Random();
        filename = filename+random.nextInt();
        filename = filename.substring(0,15);
        Log.i("www", "filename" + filename);
        return  filename;
    }
    public  void  startPlay()
    {
        if (mediaPlayer==null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            mediaPlayer.reset();
            Log.i("www","文件名"+fileName);
            mediaPlayer.setDataSource(android.os.Environment.getExternalStorageDirectory() + "/" + fileName);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                  //  stopPlay.stop();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("www", "播放长度" + mediaPlayer.getDuration());
        mediaPlayer.start();

    }

    public  void  startPlayTwo(String path)
    {
        if (mediaPlayer==null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            mediaPlayer.reset();
            Log.i("www", "文件名" + path);
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                //    stopPlay.stop();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("www", "播放长度" + mediaPlayer.getDuration());
        mediaPlayer.start();

    }

    public void stopPlay()
    {
        if (mediaPlayer==null)
        {
            return;
        }
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;
    }

    public  String formatLongToTimeStr(int l) {
        int hour = 0;
        int minute = 0;
        int second = 0;

        second = l / 1000;

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        return (getTwoLength(hour) + ":" + getTwoLength(minute)  + ":"  + getTwoLength(second));
    }
    private  String getTwoLength(final int data) {
        if(data < 10) {
            return "0" + data;
        } else {
            return "" + data;
        }
    }

    public  String getLength()
    {

        if (mediaPlayer==null)
        {
           mediaPlayer = new MediaPlayer();
        }
        String date = null;
        try {
           mediaPlayer.setDataSource(android.os.Environment.getExternalStorageDirectory()+"/"+fileName);
           mediaPlayer.prepare();
            date = formatLongToTimeStr(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return date;
    }

    public  String getLength(String fileName)
    {
        if (mediaPlayer==null)
        {
           mediaPlayer = new MediaPlayer();
        }
        String date = null;
        try {
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            date = formatLongToTimeStr(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return date;
    }


    public void stop() {  
        if (mRecorder != null) {  
            mRecorder.stop();  
            mRecorder.release();  
            mRecorder = null;  
        }  
    }  
  
    public void pause() {  
        if (mRecorder != null) {  
            mRecorder.stop();  
        }  
    }  
  
   /* public void start() {
        if (mRecorder != null) {  
            mRecorder.start();  
        }  
    }  */
  
    public double getAmplitude() {  
        if (mRecorder != null)  
            return (mRecorder.getMaxAmplitude() / 2700.0);  
        else  
            return 0;  
  
    }  
  
    public double getAmplitudeEMA() {  
        double amp = getAmplitude();  
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;  
        return mEMA;  
    }

    public interface StopPlay
    {
        void stop();
    }


}