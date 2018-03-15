package mine.activity.order_class;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.renyajie.yuyue.R;

import mine.activity.order_class.adapter.OrderClassListAdapter;
import test.OrderClassListData;

/**
 * Created by Thor on 2018/3/15.
 *
 * 约课信息列表
 */

public class OrderClassListActivity extends AppCompatActivity {

    private static final String PageName = "约课信息";
    private Toolbar toolbar;
    private ListView listView;
    private OrderClassListAdapter adapter;

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
    }
}
