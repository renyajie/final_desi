package main.activity.individual_class_order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.renyajie.yuyue.R;

/**
 * Created by Thor on 2018/3/11.
 *
 * Main页面中的私教预约子页面，负责私教预约的所有业务逻辑
 */

public class IndividualClassOrderActivity extends AppCompatActivity {

    private static final String PageName = "私教预约";
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_individual_class_order);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
