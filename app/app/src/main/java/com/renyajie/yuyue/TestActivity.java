package com.renyajie.yuyue;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * 测试Activity
 * Created by Thor on 2018/4/3.
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private Button getButton, getButtonWithParam, postButton, putButton, deleteButton;
    private TextView textView;

    private static final int GET_SUCCESS = 1;
    private static final int GET_FAILURE = 2;
    private static final int POST_SUCCESS = 3;
    private static final int POST_FAILURE = 4;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_FAILURE:
                    Toast.makeText(TestActivity.this, "Get方法接收失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_SUCCESS:
                    Messenger messengerA = (Messenger) msg.obj;
                    textView.setText(messengerA.getExtend().get("info").toString());
                    break;
                case POST_SUCCESS:
                    Messenger messengerB = (Messenger) msg.obj;
                    textView.setText(messengerB.getExtend().get("info").toString());
                    break;
                case POST_FAILURE:
                    Messenger messengerC = (Messenger) msg.obj;
                    textView.setText(messengerC.getExtend().get("info").toString());
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
        setContentView(R.layout.test);

        initView();
    }

    private void initView() {
        getButton = this.findViewById(R.id.get_btn);
        getButtonWithParam = this.findViewById(R.id.get_btn_with_param);
        postButton = this.findViewById(R.id.post_btn);
        textView = this.findViewById(R.id.text);
        putButton = this.findViewById(R.id.put_btn);
        deleteButton = this.findViewById(R.id.delete_btn);

        getButton.setOnClickListener(this);
        getButtonWithParam.setOnClickListener(this);
        postButton.setOnClickListener(this);
        putButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.get_btn:
                String urlA = AppConstant.URL + "api/test/angular/getSimpleMessage";
                UtilsMethod.getDataAsync(urlA, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = handler.obtainMessage();
                        msg.what = GET_FAILURE;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Messenger messenger = UtilsMethod.getMessengerFromJson(response);
                        Message msg = handler.obtainMessage();
                        msg.what = GET_SUCCESS;
                        msg.obj = messenger;
                        handler.sendMessage(msg);
                    }
                });
                break;
            case R.id.get_btn_with_param:
                //构造带参数的URL
                String tmp = AppConstant.URL + "api/test/angular/getSimpleMessageWithParams";
                Map<String, String> params = new HashMap<>();
                params.put("name", "冰糖");
                String urlB = UtilsMethod.makeGetParams(tmp, params);
                Log.d("get", urlB);

                UtilsMethod.getDataAsync(urlB, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = handler.obtainMessage();
                        msg.what = GET_FAILURE;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Messenger messenger = UtilsMethod.getMessengerFromJson(response);
                        Message msg = handler.obtainMessage();
                        msg.what = GET_SUCCESS;
                        msg.obj = messenger;
                        handler.sendMessage(msg);
                    }
                });
                break;
            case R.id.post_btn:
                //构造参数体
                String urlC = AppConstant.URL + "api/test/angular/postParams";

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
                User user = new User();
                user.setuName("冰糖");
                String jsonStr = new Gson().toJson(user);
                Log.d("get", jsonStr);
                RequestBody body = RequestBody.create(JSON, jsonStr);

                UtilsMethod.postDataAsync(urlC, body, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = handler.obtainMessage();
                        msg.what = POST_FAILURE;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Messenger messenger = UtilsMethod.getMessengerFromJson(response);
                        Message msg = handler.obtainMessage();
                        msg.what = POST_SUCCESS;
                        msg.obj = messenger;
                        handler.sendMessage(msg);
                    }
                });
                break;
            case R.id.put_btn:
                //构造参数体
                String urlD = AppConstant.URL + "api/test/angular/putParams";

                MediaType JSOND = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
                User userD = new User();
                userD.setuName("冰糖");
                String jsonStrD = new Gson().toJson(userD);
                Log.d("get", jsonStrD);
                RequestBody bodyD = RequestBody.create(JSOND, jsonStrD);

                UtilsMethod.putDataAsync(urlD, bodyD, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = handler.obtainMessage();
                        msg.what = POST_FAILURE;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Messenger messenger = UtilsMethod.getMessengerFromJson(response);
                        Message msg = handler.obtainMessage();
                        msg.what = POST_SUCCESS;
                        msg.obj = messenger;
                        handler.sendMessage(msg);
                    }
                });
                break;
            case R.id.delete_btn:
                //构造参数体
                String urlE = AppConstant.URL + "api/test/angular/deleteParams";

                FormBody.Builder bodyE = new FormBody.Builder();
                bodyE.add("name", "冰糖");

                UtilsMethod.deleteDataAsync(urlE, bodyE, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = handler.obtainMessage();
                        msg.what = POST_FAILURE;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Messenger messenger = UtilsMethod.getMessengerFromJson(response);
                        Message msg = handler.obtainMessage();
                        msg.what = POST_SUCCESS;
                        msg.obj = messenger;
                        handler.sendMessage(msg);
                    }
                });
                break;
            default:
                break;
        }
    }

}
