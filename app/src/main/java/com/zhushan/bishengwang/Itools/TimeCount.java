package com.zhushan.bishengwang.Itools;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by Administrator on 2015/12/18.
 */
public class TimeCount extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    private Button button;
    public TimeCount(long millisInFuture, long countDownInterval,Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;

    }

    /**
     * 计时过程显示
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        this.button.setClickable(false);
        this.button.setText(millisUntilFinished/1000+"秒");
    }

    @Override
    public void onFinish() {
        this.button.setClickable(true);
        this.button.setText("重新验证");
    }
}
