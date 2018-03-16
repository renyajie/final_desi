package mine.activity.order_class;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.renyajie.yuyue.R;

import mine.activity.order_class.adapter.OrderClassListAdapter;
import test.OrderClassListData;

/**
 * Created by Thor on 2018/3/15.
 *
 * 约课信息列表
 */

public class OrderClassListActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener {

    private static final String PageName = "约课信息";
    private Toolbar toolbar;
    private ListView listView;
    private OrderClassListAdapter adapter;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_order_class_list);
        initView();
    }

    private void initView() {

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list_view);
        adapter = new OrderClassListAdapter(this,
                OrderClassListData.orderClassBriefModelList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);

        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.all_lesson:
                adapter.setData(OrderClassListData.orderClassBriefModelList);
                break;
            case R.id.people_lesson:
                adapter.setData(OrderClassListData.orderClassBriefModelList.subList(
                        0, 1));
                break;
            case R.id.individual_lesson:
                adapter.setData(OrderClassListData.orderClassBriefModelList.subList(
                        1, 2));
                break;
            default:
                break;
        }
    }
}
