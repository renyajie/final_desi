package main.activity.people_order_confirm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.renyajie.yuyue.R;

/**
 * Created by Thor on 2018/3/13.
 *
 * 订单预约界面
 */

public class PeopleOrderConfirmActivity extends AppCompatActivity {

    //标题栏名称
    private static final String PageName = "课程预约";
    //场馆编号和课程编号
    private Integer placeId, classId;
    private Toolbar toolbar;
    /*
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_people_order_confirm);
        initView();
    }

    private void initView() {
        //设置标题栏
        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //接收Intent参数
        receiveIntentData();

        /*
        context = this;
        delegates.clear();

        //TODO 向RecyclerView中添加各类Item布局

        recyclerView = findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        //TODO 初始化组件数据*/
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.placeId = bundle.getInt("placeId", 0);
        this.classId = bundle.getInt("classId", 0);
        //TODO REMOVE
        Toast.makeText(this,
                "场地编号是" + placeId + ", 课程编号是" + classId, Toast.LENGTH_SHORT).show();
    }
}
