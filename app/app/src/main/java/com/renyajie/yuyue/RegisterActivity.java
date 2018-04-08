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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import bean.User;
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

public class RegisterActivity extends AppCompatActivity
implements View.OnClickListener{

    private EditText phone, password, username;
    private ImageView phoneClear, passwordClear, usernameClear;
    private Button login, register;
    private RadioButton maleButton, femaleButton;

    private static final int REGISTER_SUCCESS = 1;
    private static final int REGISTER_FAILURE = 2;
    private static final int UNKNOW_ERROR = 3;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case UNKNOW_ERROR:
                    showNoticeDialog("提示", "发送未知错误，请检查是否连接服务器");
                    break;
                case REGISTER_SUCCESS:
                    //显示注册成功对话框
                    showSuccessDialog();
                    break;
                case REGISTER_FAILURE:
                    Messenger messengerA= (Messenger) msg.obj;

                    //Msg存在，则为手机号重复错误，否则为字段错误
                    if(messengerA.getMsg() != null && messengerA.getMsg().length() > 0) {
                        showNoticeDialog("注册失败", messengerA.getMsg());
                        return;
                    }

                    //生成字段错误信息，并提示用户
                    Map<String, String> result =
                            (Map<String, String>) messengerA.getExtend().get("errorFields");
                    String reason = "";
                    boolean first = true;
                    for(String str: result.values()) {

                        if (first) {
                            reason = str;
                            first = false;
                        }
                        else {
                            reason = reason + "\n" + str;
                        }
                    }
                    showNoticeDialog("注册失败", reason);
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
        setContentView(R.layout.activity_start_register);
        initView();
    }

    private void initView() {
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        phoneClear = findViewById(R.id.phone_clear);
        passwordClear = findViewById(R.id.password_clear);
        usernameClear = findViewById(R.id.username_clear);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        maleButton = findViewById(R.id.male);
        femaleButton = findViewById(R.id.female);

        //设置清空监听器
        addClearListener(phone, phoneClear);
        addClearListener(password, passwordClear);
        addClearListener(username, usernameClear);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                //返回登录界面
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.register:
                //检查数据合法性
                if(phone.getText().toString().length() == 0) {
                    showNoticeDialog("提示", "手机号不能为空");
                    return;
                }
                if(!isChinaPhoneLegal(phone.getText().toString())) {
                    showNoticeDialog("提示", "手机号不合法");
                    return;
                }
                if(password.getText().toString().length() == 0) {
                    showNoticeDialog("提示", "密码不能为空");
                    return;
                }
                if(password.getText().toString().length() < 6) {
                    showNoticeDialog("提示", "密码长度不能小于6");
                    return;
                }
                if(password.getText().toString().contains(" ")) {
                    showNoticeDialog("提示", "密码不能包含空格");
                    return;
                }
                if(password.getText().toString().length() > 16) {
                    showNoticeDialog("提示", "密码长度不能大于16");
                    return;
                }
                if(username.getText().toString().length() == 0) {
                    showNoticeDialog("提示", "姓名不能为空");
                    return;
                }
                if(username.getText().toString().trim().length() == 0) {
                    showNoticeDialog("提示", "姓名不能为空白");
                    return;
                }
                if(username.getText().toString().length() > 10) {
                    showNoticeDialog("提示", "姓名长度不能超过10");
                    return;
                }
                if(!maleButton.isChecked() && !femaleButton.isChecked()) {
                    showNoticeDialog("提示", "未选择性别");
                    return;
                }

                //构造用户，发送注册请求
                User user = new User();
                user.setPasswd(password.getText().toString());
                user.setuName(username.getText().toString());
                user.setPhone(phone.getText().toString());
                if(maleButton.isChecked()) {
                    user.setGender("男");
                }
                else {
                    user.setGender("女");
                }

                register(user);
                break;
        }
    }

    //向服务器发起注册请求
    private void register(User user) {
        //构造请求地址
        String url = AppConstant.URL + "api/auth/userRegister";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
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
                        response, new TypeToken<Messenger<Map<String, String>>>(){});
                Message msg = handler.obtainMessage();
                if(messenger.getCode() == 100) {
                    msg.what = REGISTER_SUCCESS;
                }
                else {
                    msg.what = REGISTER_FAILURE;
                }
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //提示对话框
    private void showNoticeDialog(String title, String reason){
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

    //注册成功对话框
    private void showSuccessDialog(){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("注册成功");
        normalDialog.setPositiveButton("前往登录",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        RegisterActivity.this.finish();
                    }
                });
        normalDialog.setNegativeButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        normalDialog.show();
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
