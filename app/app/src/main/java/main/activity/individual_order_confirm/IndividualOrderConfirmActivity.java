package main.activity.individual_order_confirm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.renyajie.yuyue.R;

import java.util.ArrayList;
import java.util.List;

import main.activity.individual_order_confirm.delegate.IndividualOrderDetailDelegate;
import main.activity.individual_order_confirm.model.IndividualOrderDetailModel;
import main.activity.people_order_confirm.delegate.PeopleOrderDetailDelegate;
import main.activity.people_order_confirm.delegate.PeopleOrderPayDelegate;
import main.activity.people_order_confirm.model.PeopleOrderDetailModel;
import main.activity.people_order_confirm.model.PeopleOrderPayModel;
import main.helper.SpaceItemDecoration;
import test.IndividualOrderConfirmData;
import test.PeopleOrderConfirmData;
import utils.AppConstant;
import utils.MainAdapter;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/15.
 *
 * 私教预约的订单确认页面
 */

public class IndividualOrderConfirmActivity extends AppCompatActivity {

    //标题栏名称
    private static final String PageName = "课程预约";
    //场馆编号和课程编号
    private Integer placeId, classId;

    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_individual_order_confirm);
        initView();
    }

    private void initView() {

        //接收Intent参数
        receiveIntentData();

        //设置标题栏
        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        context = this;
        delegates.clear();

        //向RecyclerView中添加各类Item布局
        delegates.add(new IndividualOrderDetailDelegate(this));
        delegates.add(new PeopleOrderPayDelegate(this));

        recyclerView = findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        //TODO 初始化参数
        //initOrderDetail(IndividualOrderConfirmData.individualOrderDetailModel);
        //initOrderPay(PeopleOrderConfirmData.peopleOrderPayModel);
    }

    //初始化课程详情部分
    private void initOrderDetail(IndividualOrderDetailModel model) {
        int position = getViewHolderPosition(ViewHolderType.IndividualOrderDetail);
        if(position == -1) return;
        ((IndividualOrderDetailDelegate)delegates.get(position))
                .setIndividualOrderDetailModel(model);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化订单详情部分
    private void initOrderPay(PeopleOrderPayModel model) {
        int position = getViewHolderPosition(ViewHolderType.PeopleOrderPay);
        if(position == -1) return;
        ((PeopleOrderPayDelegate)delegates.get(position)).setPeopleOrderPayModel(model);
        ((PeopleOrderPayDelegate)delegates.get(position)).setWhereStart(AppConstant.INDIVIDUAL_ORDER);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.placeId = bundle.getInt("placeId", 0);
        this.classId = bundle.getInt("classId", 0);
        //TODO REMOVE, 初始化订单详情模型数据
        Log.d("msg", "场地编号是" + placeId + ", 课程编号是" + classId);
    }

    // 获取指定类型View在列表中的位置
    private int getViewHolderPosition(ViewHolderType type) {
        for (int i = 0; i < delegates.size(); i++) {
            if (delegates.get(i).getViewHolderType() == type) {
                return i;
            }
        }
        return -1;
    }
}
