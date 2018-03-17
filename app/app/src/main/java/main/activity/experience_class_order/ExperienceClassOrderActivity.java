package main.activity.experience_class_order;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.ArrayList;
import java.util.List;

import main.activity.experience_class_order.model.ClassModel;
import test.ExperienceClassData;

/**
 * Created by Thor on 2018/3/16.
 *
 * 申请体验课程
 */

public class ExperienceClassOrderActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private static final String PageName = "体验课申请";
    private Toolbar toolbar;
    private Spinner classSpinner, timeSpanner;
    private EditText name, phone, comment;
    private CheckBox readCheck;
    private TextView protocol;
    private Button confirmButton;

    private List<String> classNameList, timeList;
    private String nameString, phoneString, commentString;
    private ArrayAdapter<String> classAdapter, timeAdapter;

    public  static Integer NAME = 1, PHONE = 2, Comment = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_experience_class_order);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        classSpinner = findViewById(R.id.class_spinner);
        timeSpanner = findViewById(R.id.time_spinner);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        comment = findViewById(R.id.comment);
        readCheck = findViewById(R.id.read_check);
        protocol = findViewById(R.id.protocol);
        confirmButton = findViewById(R.id.confirm_button);


        // 完成组件的初始化工作
        //初始化课程下拉列表
        classNameList = new ArrayList<>();
        for(ClassModel model: ExperienceClassData.classModelList) {
            classNameList.add(model.className);
        }
        classAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, classNameList);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);
        classSpinner.setOnItemSelectedListener(this);

        //初始化时间下拉列表
        timeList = ExperienceClassData.timeList;
        timeAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, timeList);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpanner.setAdapter(timeAdapter);
        timeSpanner.setOnItemSelectedListener(this);

        //绑定EditText的变量
        name.addTextChangedListener(new MyTextWatcher(NAME));
        phone.addTextChangedListener(new MyTextWatcher(PHONE));
        comment.addTextChangedListener(new MyTextWatcher(Comment));

        //设置条款和按钮的点击事件
        protocol.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.class_spinner) {
            Log.d("msg", "课程：" + classNameList.get(position));
        } else {
            Log.d("msg", "时间：" + timeList.get(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.protocol:
                showProtocolDialog();
                break;
            case R.id.confirm_button:
                if(!readCheck.isChecked()) {
                    showNoticeDialog();
                    return;
                }
                Log.d("msg", "预约成功");
                break;
            default:
                break;
        }
    }

    //显示会员服务条款对话框
    private void showProtocolDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("会员服务条款");
        normalDialog.setMessage(getResources().getString(R.string.protocol));
        normalDialog.setPositiveButton("同意",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        readCheck.setChecked(true);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    //显示提示同意对话框
    private void showNoticeDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("注意");
        normalDialog.setMessage("您需要先同意会员条款才能预约");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    class MyTextWatcher implements TextWatcher {

        private Integer viewType;

        public MyTextWatcher(int viewType) {
            this.viewType = viewType;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (viewType == NAME) {
                nameString = s.toString();
                Log.d("msg", "姓名是" + nameString);
            } else if (viewType == PHONE){
                phoneString = s.toString();
                Log.d("msg", "手机号是" + phoneString);
            } else {
                commentString = s.toString();
                Log.d("msg", "留言是" + commentString);
            }
        }
    }
}
