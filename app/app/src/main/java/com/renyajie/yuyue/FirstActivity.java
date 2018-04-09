package com.renyajie.yuyue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import bean.User;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/4/9.
 */

public class FirstActivity extends AppCompatActivity {

    private static final int WAIT_TIME = 3000;
    private static final int LOGIN = 1;
    private static final int LOGOUT = 2;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case LOGIN:
                    //若已登录则跳转主页面
                    Intent intentForLogIn =
                            new Intent(FirstActivity.this, MainActivity.class);
                    startActivity(intentForLogIn);
                    FirstActivity.this.finish();
                    break;
                case LOGOUT:
                    //未登录跳转登录页面
                    Intent intentForLogOut =
                            new Intent(FirstActivity.this, MainActivity.class);
                    startActivity(intentForLogOut);
                    FirstActivity.this.finish();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_first);
        setUp();
    }

    //设置启动流程，若用户已登录进入主页面，否则进入登录页
    private void setUp() {
        //若用户已登录
        if(UtilsMethod.getLogInStatus()) {
            handler.sendEmptyMessageDelayed(LOGIN, WAIT_TIME);
        }
        else {
            handler.sendEmptyMessageDelayed(LOGOUT, WAIT_TIME);
        }
    }
}
