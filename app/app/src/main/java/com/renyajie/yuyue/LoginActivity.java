package com.renyajie.yuyue;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CardKind;
import bean.Place;
import bean.User;
import main.activity.people_class_order.model.PlaceModel;
import mine.activity.order_card.BuyCardActivity;
import mine.activity.order_card.PlaceCardDetailActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/4/8.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText phone, password;
    private ImageView phoneClear, passwordClear;
    private Button login, register;

    private static final int LOGIN_SUCCESS = 1;
    private static final int LOGIN_FAILURE = 2;
    private static final int UNKNOW_ERROR = 3;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case UNKNOW_ERROR:
                    showNoticeDialog("提示", "发送未知错误，请检查是否连接服务器");
                    break;
                case LOGIN_SUCCESS:
                    //保存用户信息
                    Messenger messengerB = (Messenger) msg.obj;
                    User user = (User) messengerB.getExtend().get("info");
                    UtilsMethod.saveUserInfo(user);
                    //进入主界面
                    Intent intent = new Intent(
                            LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                    break;
                case LOGIN_FAILURE:
                    Messenger messengerA= (Messenger) msg.obj;
                    showNoticeDialog("登录失败", messengerA.getMsg());
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
        setContentView(R.layout.activity_start_login);
        initView();
    }

    private void initView() {
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        phoneClear = findViewById(R.id.phone_clear);
        passwordClear = findViewById(R.id.password_clear);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        //添加监听器
        addClearListener(phone, phoneClear);
        addClearListener(password, passwordClear);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    /**
     * 为输入框添加清除监听器
     * @param et 输入框
     * @param iv 清除图表
     */
    private void addClearListener(final EditText et , final ImageView iv){
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果有输入内容长度大于0那么显示clear按钮
                String str = s + "" ;
                if (s.length() > 0){
                    iv.setVisibility(View.VISIBLE);
                }else{
                    iv.setVisibility(View.INVISIBLE);
                }
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });
    }

    //向服务器发起登录请求
    private void login() {
        //构造请求地址
        String url = AppConstant.URL + "api/auth/userLogIn";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        User user = new User();
        user.setPhone(phone.getText().toString());
        user.setPasswd(password.getText().toString());
        String jsonStr = new Gson().toJson(user);
        Log.d("get", jsonStr);
        RequestBody body = RequestBody.create(JSON, jsonStr);

        UtilsMethod.postDataAsync(url, body, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = UNKNOW_ERROR;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<User>>(){});
                Message msg = handler.obtainMessage();
                if(messenger.getCode() == 100) {
                    msg.what = LOGIN_SUCCESS;
                }
                else {
                    msg.what = LOGIN_FAILURE;
                }
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                //检查数据合法性
                if(phone.getText().toString().length() == 0) {
                    showNoticeDialog("提示", "手机号不能为空");
                    return;
                }
                if(password.getText().toString().length() == 0) {
                    showNoticeDialog("提示", "密码不能为空");
                    return;
                }
                //发起登录确认
                login();
                break;
            case R.id.register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                this.finish();
                break;
            default:
                break;
        }
    }

    private void showNoticeDialog(String title, String reason){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle(title);
        normalDialog.setMessage(reason);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        normalDialog.show();
    }
}
