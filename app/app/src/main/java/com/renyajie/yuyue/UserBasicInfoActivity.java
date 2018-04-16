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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import bean.UserFeature;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/4/16.
 */

public class UserBasicInfoActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup questionOne, questionTwo, questionThree, questionFour;
    private Button confirm;
    private int one, two, three, four;
    private int userId;

    private static final int ADD_FEATURE_SUCCESS = 1;
    private static final int ADD_FEATURE_FAILURE = 2;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case ADD_FEATURE_FAILURE:
                    showNoticeDialog("提示", "发送未知错误，请检查是否连接服务器");
                    break;
                case ADD_FEATURE_SUCCESS:
                    //进入主界面
                    Intent intent = new Intent(
                            UserBasicInfoActivity.this, MainActivity.class);
                    startActivity(intent);
                    UserBasicInfoActivity.this.finish();
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
        setContentView(R.layout.activity_start_info);
        initView();
    }

    private void initView() {
        //获取传递过来的用户参数
        receiveIntentData();

        questionOne = findViewById(R.id.question_one);
        questionTwo = findViewById(R.id.question_two);
        questionThree = findViewById(R.id.question_three);
        questionFour = findViewById(R.id.question_four);

        questionOne.setOnCheckedChangeListener(this);
        questionTwo.setOnCheckedChangeListener(this);
        questionThree.setOnCheckedChangeListener(this);
        questionFour.setOnCheckedChangeListener(this);

        one = -1;
        two = -1;
        three = -1;
        four = -1;

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查数据合法性
                if(one == -1 || two == -1 || three == -1 || four == -1) {
                    showNoticeDialog("注意", "请选择完毕后再点击确认按钮");
                    return;
                }
                //向服务器发送数据
                addUserFeature();
            }
        });
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.userId = bundle.getInt("userId", 0);
        Log.d("get", "用户编号是" + userId);
    }

    //向服务器发送用户特征数据
    private void addUserFeature() {
        //构造请求地址
        String url = AppConstant.URL + "api/setting/addUserFeature";

        //构建用户特征数据
        UserFeature feature = new UserFeature();
        feature.setuId(userId);
        feature.setIllnese(one);
        feature.setSurgery(two);
        feature.setBalanceDiet(three);
        feature.setLimitIntake(four);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = new Gson().toJson(feature);
        Log.d("get", jsonStr);
        RequestBody body = RequestBody.create(JSON, jsonStr);

        UtilsMethod.postDataAsync(url, body, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = ADD_FEATURE_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<Object>>(){});
                Message msg = handler.obtainMessage();
                if(messenger.getCode() == 200) {
                    return;
                }
                msg.what = ADD_FEATURE_SUCCESS;
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.one_yes:
                one = 1;
                break;
            case R.id.one_no:
                one = 0;
                break;
            case R.id.two_yes:
                two = 1;
                break;
            case R.id.two_no:
                two = 0;
                break;
            case R.id.three_yes:
                three = 1;
                break;
            case R.id.three_no:
                three = 0;
                break;
            case R.id.four_yes:
                four = 1;
                break;
            case R.id.four_no:
                four = 0;
                break;
            default:
                break;
        }
    }
}
