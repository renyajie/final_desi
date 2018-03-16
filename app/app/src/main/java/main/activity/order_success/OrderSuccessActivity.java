package main.activity.order_success;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.renyajie.yuyue.R;

import main.activity.individual_class_order.IndividualClassOrderActivity;
import main.activity.people_class_order.PeopleClassOrderActivity;
import mine.activity.order_class.OrderClassListActivity;
import utils.AppConstant;

/**
 * Created by Thor on 2018/3/15.
 *
 * 预约成功页面
 */

public class OrderSuccessActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PageName = "预约成功";
    private Toolbar toolbar;
    private Button checkOrder, continueOrder;
    //继续团课预约还是私教预约
    private Integer whereToBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_order_success);
        initView();
    }

    private void initView() {
        //接收Intent启动参数
        receiveIntentData();

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkOrder = findViewById(R.id.check_order);
        continueOrder = findViewById(R.id.continue_order);

        //设置查看预约和继续预约的监听器
        checkOrder.setOnClickListener(this);
        continueOrder.setOnClickListener(this);
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        this.whereToBack = getIntent().getIntExtra("start_from",
                AppConstant.PEOPLE_ORDER);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_order:
                Intent intentForCheck = new Intent(this, OrderClassListActivity.class);
                startActivity(intentForCheck);
                break;
            case R.id.continue_order:
                if (whereToBack == AppConstant.PEOPLE_ORDER) {
                    Intent intent = new Intent(this, PeopleClassOrderActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, IndividualClassOrderActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
