package com.zhushan.bishengwang.Itools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.zhushan.bishengwang.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2015/12/3.
 */
public class Mediaplayutils {
    private   String recorderFilename;
    private  MediaRecorder mediaRecorder,mRecorder;
    private boolean isRecording = false ;
    private  Dialog alertDialog,alertDialogtwo;
    private MediaPlayer mediaPlayer;
    private double mEMA = 0.0;
    private SoundMeter soundMeter;
    public static final int RECORDER=7;
    private RecorderSettext recorderSettext;
   private  Button recorder_showtext;
    private boolean isFirst;
    private   boolean isRecorder;
    private boolean isPlay;
    public Mediaplayutils()
    {
        soundMeter = new SoundMeter();
    }
    public void setRecorderSettext(RecorderSettext recorderSettext) {
        this.recorderSettext = recorderSettext;

    }
    public  void dialogdiss()
    {
        Log.i("www","alertDialog"+alertDialog);
        if (alertDialog==null)
        {
            return;

        }
        alertDialog.dismiss();
    }
    int index,index2;
    final Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==2)
            {
                index = (int) msg.obj;
                index2 = index+index2;

                String recorder =  soundMeter.formatLongToTimeStr(index2);
                recorder_showtext.setText(recorder);
            }

            if (msg.what==3)
            {
                index = (int) msg.obj;
                index2 = index+index2;
                if (!isPlay)
                {
                   return;
                }
                String recorder =  soundMeter.formatLongToTimeStr(index2);
                recorder_showtext.setText(recorder);
            }
        }
    };

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

    public void startPlay(final Activity context,final String Path)
    {
        alertDialogtwo = new Dialog(context, R.style.my_dialog);
        alertDialogtwo.setCancelable(false);
        alertDialogtwo.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_play, null);
        final TextView recorder_starpplay = (TextView) view.findViewById(R.id.recorder_starpplay);
        final TextView recorder_stopplay = (TextView) view.findViewById(R.id.recorder_stopplay);
        TextView recorder_cancle = (TextView) view.findViewById(R.id.recorder_cancle);
        recorder_showtext  = (Button) view.findViewById(R.id.recorder_showtext);
        soundMeter.setStopPlay(new SoundMeter.StopPlay() {
            @Override
            public void stop() {
                isPlay= false;
            }
        });
        recorder_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isPlay = false;
                index2 = 0;
                soundMeter.stopPlay();
                alertDialogtwo.dismiss();
            }
        });
        recorder_starpplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlay =true;
                index2 = 0;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isPlay) {
                            Message message = handler.obtainMessage();
                            message.what = 3;
                            message.obj = 1000;
                            try {
                                Thread.sleep(1300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendMessage(message);
                        }
                    }
                }).start();
                soundMeter.startPlayTwo(Path);
                Toast.makeText(context,"正在播放",Toast.LENGTH_SHORT).show();
            }
        });
        recorder_stopplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundMeter.stopPlay();
                isPlay = false;
                index2 = 0;
                Toast.makeText(context,"已停止播放",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogtwo.setContentView(view);
        alertDialogtwo.show();
        Window window = alertDialogtwo.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

    }



    public  void startRecorder(final Activity context,final int recorderTag,final String filenam) {

        alertDialog = new Dialog(context, R.style.my_dialog);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_recorder, null);
        final TextView recorder = (TextView) view.findViewById(R.id.recorder);
        final TextView recorder_finish = (TextView) view.findViewById(R.id.recorder_finish);
        final TextView recorder_starpplay = (TextView) view.findViewById(R.id.recorder_starpplay);
        final TextView recorder_stopplay = (TextView) view.findViewById(R.id.recorder_stopplay);
        TextView recorder_cancle = (TextView) view.findViewById(R.id.recorder_cancle);
         recorder_showtext = (Button) view.findViewById(R.id.recorder_showtext);
        soundMeter.setStopPlay(new SoundMeter.StopPlay() {
            @Override
            public void stop() {
                isPlay= false;
            }
        });
     //   final TimeCount timeCount = new TimeCount(60000,1000,recorder_showtext);
        recorder_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    isRecorder = false;
                    isPlay = false;
                    index2 = 0;
                    soundMeter.stopPlay();
                    soundMeter.stop();
                    alertDialog.dismiss();
                }
        });
        recorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay)
                {
                   isPlay = false;

                }
                recorder_finish.setClickable(true);
                recorder_stopplay.setClickable(false);
                recorder_starpplay.setClickable(false);
                soundMeter.start();
                isRecorder = true;
                index2 = 0;
                Toast.makeText(context, "开始录音", Toast.LENGTH_SHORT).show();
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                ;executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (isRecorder) {
                            recorder.setClickable(false);
                            Message message = handler.obtainMessage();
                            message.what = 2;
                            message.obj = 1000;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendMessage(message);
                        }
                    }
                });
                recorder.setClickable(true);
                //   timeCount.start();
              /*  stoPlayListnener();
                CameraUtils.startRecorder(context, recorderTag);*/
            }
            });
             recorder_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              stoPlayListnener();
                recorder_starpplay.setClickable(true);
                isRecorder = false;
                Log.i("www","文件名"+soundMeter.getFileName());
                recorderSettext.setText(soundMeter.formatLongToTimeStr(index2), soundMeter.getFileName());
                index2 = 0;
             //   timeCount.onFinish();
              /*  stopRecord();
                Toast.makeText(context,"重新录音。。。。", Toast.LENGTH_LONG).show();
                alertDialog.dismiss();*/
                soundMeter.stop();
                Toast.makeText(context,"已完成录音",Toast.LENGTH_SHORT).show();
              //  recorderSettext.setText(index2);
                alertDialog.dismiss();
               // CameraUtils.startRecorder(context, recorderTag);
            }
        });

        recorder_starpplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*if (filenam==null)
                {
                    Toast.makeText(context,"没有可播放的录音文件",Toast.LENGTH_SHORT).show();
                    return;
                }
                startPlay(filenam);
              *//*  play();*//*
                Toast.makeText(context,"正在播放",Toast.LENGTH_SHORT).show();*/
                recorder_stopplay.setClickable(true);
                isPlay =true;
                index2 = 0;
                recorder_finish.setClickable(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isPlay) {
                            Message message = handler.obtainMessage();
                            message.what = 3;
                            message.obj = 1000;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendMessage(message);
                        }
                    }
                }).start();
                soundMeter.startPlay();
                Toast.makeText(context,"正在播放",Toast.LENGTH_SHORT).show();
            }
        });
        recorder_stopplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (filenam==null)
                {
                    Toast.makeText(context,"",Toast.LENGTH_SHORT).show();
                    return;
                }
                stoPlayListnener();*/
                soundMeter.stopPlay();
                isPlay = false;
                index2 = 0;
                Toast.makeText(context,"已停止播放",Toast.LENGTH_SHORT).show();
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

    public  void stoPlayListnener()
    {
            if (mediaPlayer!=null) {
                mediaPlayer.stop();
                mediaRecorder.release();
                mediaPlayer=null;
            }
    }

    public interface RecorderSettext
    {
        void setText(String textview,String filename);
    }




}
