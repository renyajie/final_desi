package mine.activity.order_class;

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

import main.activity.people_order_confirm.delegate.PeopleOrderPayDelegate;
import main.activity.people_order_confirm.model.PeopleOrderPayModel;
import main.helper.SpaceItemDecoration;
import mine.activity.order_class.delegate.OrderDetailDelegate;
import mine.activity.order_class.delegate.OrderLessonDetailDelegate;
import mine.activity.order_class.delegate.OrderLessonRuleDelegate;
import mine.activity.order_class.delegate.OrderPlaceDelegate;
import mine.activity.order_class.helper.GeneralLessonDetailModel;
import mine.activity.order_class.model.IndividualLessonDetailModel;
import mine.activity.order_class.model.OrderDetailModel;
import mine.activity.order_class.model.PeopleLessonDetailModel;
import mine.activity.order_class.model.PlaceModel;
import test.OrderDetailData;
import utils.AppConstant;
import utils.MainAdapter;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/15.
 *
 * 约课详情界面
 */

public class OrderDetailActivity extends AppCompatActivity {

    private static final String PageName = "课程详情";
    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;
    private Integer viewType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_order_detail);
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

        context = this;
        delegates.clear();

        //向RecyclerView中添加各类Item布局
        delegates.add(new OrderLessonDetailDelegate(this));
        delegates.add(new OrderPlaceDelegate(this));
        delegates.add(new OrderDetailDelegate(this));
        delegates.add(new OrderLessonRuleDelegate(this));

        recyclerView = findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        //初始化组件数据
        initOrderLessonDetail(OrderDetailData.modelA, OrderDetailData.modelB);
        initOrderPlace(OrderDetailData.placeModel);
        initOrderDetail(OrderDetailData.orderDetailModel);
    }

    //初始化订单详情部分
    private void initOrderLessonDetail(PeopleLessonDetailModel modelA
        , IndividualLessonDetailModel modelB) {
        int position = getViewHolderPosition(ViewHolderType.OrderLesson);
        if(position == -1) return;

        //根据加载布局的信息载入不同的数据
        if(viewType == AppConstant.PEOPLE_ORDER) {
            ((OrderLessonDetailDelegate)delegates.get(position))
                    .setPeopleModel(modelA);
        } else if (viewType == AppConstant.INDIVIDUAL_ORDER) {
            ((OrderLessonDetailDelegate)delegates.get(position))
                    .setIndividualModel(modelB);
        } else {

        }

        ((OrderLessonDetailDelegate)delegates.get(position)).setViewType(viewType);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化约课信息的地址部分
    private void initOrderPlace(PlaceModel placeModel) {
        int position = getViewHolderPosition(ViewHolderType.OrderPlace);
        if(position == -1) return;
        ((OrderPlaceDelegate)delegates.get(position))
                .setPlaceModel(placeModel);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化约课信息的地址部分
    private void initOrderDetail(OrderDetailModel orderDetailModel) {
        int position = getViewHolderPosition(ViewHolderType.OrderDetail);
        if(position == -1) return;
        ((OrderDetailDelegate)delegates.get(position))
                .setOrderDetailModel(orderDetailModel);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.viewType = bundle.getInt("viewType", 0);
        //TODO REMOVE
        Log.d("msg", "view type是" +
                ((viewType == 1) ? "团课" : (viewType == 2) ? "私教" : "体验课" ));
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
