package com.lianchaoyun.countdown;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by li on 2017/7/7.
 */

public class CountdownButton extends RelativeLayout implements View.OnClickListener {
    private TextView tv_countdown;
    private int count=0;
    private Timer mTimer=new Timer();
    public CountdownButton(Context context) {
        super(context);
        init(context,null);
    }

    public CountdownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.layout_countdown,this);
        tv_countdown=findViewById(R.id.tv_countdown);
        tv_countdown.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id == R.id.tv_countdown){
           if(count>0){
               return;
           }
            count=60;
            mTimer=new Timer();
            mTimer.schedule(  new TimerTask() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(0);
                }
            }, 0, 1000);

        }
    }



    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if(count>0){
                        tv_countdown.setText(count+"秒");
                        tv_countdown.setBackgroundResource(R.drawable.shape_normal);
                        count--;
                    }else{
                        tv_countdown.setText("重新获取验证码");
                        tv_countdown.setBackgroundResource(R.drawable.shape_select);
                        mTimer.cancel();
                    }
                    break;
            }
        }
    };







}
