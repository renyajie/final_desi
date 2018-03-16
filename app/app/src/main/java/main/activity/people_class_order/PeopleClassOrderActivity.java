package main.activity.people_class_order;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.renyajie.yuyue.R;

import java.util.ArrayList;
import java.util.List;

import main.activity.people_class_order.delegate.PeopleClassBriefDelegate;
import main.activity.people_class_order.delegate.PlaceAndDateDelegate;
import main.activity.people_class_order.model.PeopleClassBriefModel;
import main.activity.people_class_order.model.PlaceModel;
import main.helper.SpaceItemDecoration;
import mine.activity.order_class.delegate.OrderLessonRuleDelegate;
import test.PeopleClassOrderData;
import utils.MainAdapter;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/11.
 *
 * Main页面中的团课预约子页面，负责团课预约的所有逻辑
 */

public class PeopleClassOrderActivity
        extends AppCompatActivity implements PlaceAndDateDelegate.ChangePlaceOrDate {

    private static final String PageName = "团课预约";
    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_people_class_order);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        context = this;
        delegates.clear();

        //向RecyclerView中添加各类Item布局
        delegates.add(new PlaceAndDateDelegate(this));
        delegates.add(new PeopleClassBriefDelegate(this));
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
        initClass(PeopleClassOrderData.placeModelList);
        //initPeopleClassBrief(PeopleClassOrderData.peopleClassBriefModelList);
    }

    //初始化课程名称信息
    private void initClass(List<PlaceModel> placeModelList) {
        int position = getViewHolderPosition(ViewHolderType.ClassAndDate);
        if(position == -1) return;
        ((PlaceAndDateDelegate)delegates.get(position)).setPlaceModelList(placeModelList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化团课预约的课程信息
    private void initPeopleClassBrief(List<PeopleClassBriefModel> peopleClassBriefModelList) {
        int position = getViewHolderPosition(ViewHolderType.PeopleClassBrief);
        if(position == -1) return;
        ((PeopleClassBriefDelegate)delegates.get(position))
                .setPeopleClassBriefModelList(peopleClassBriefModelList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //刷新PeopleClassBrief模块
    private void refreshPeopleClassBrief(List<PeopleClassBriefModel> peopleClassBriefModelList) {
        int position = getViewHolderPosition(ViewHolderType.PeopleClassBrief);
        ((PeopleClassBriefDelegate) delegates.get(position))
                .setPeopleClassBriefModelList(peopleClassBriefModelList);
        adapter.updatePositionDelegate(position);
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

    //用户修改场馆时回调
    @Override
    public void changePlace(int placeId) {
        Log.v("msg", "改变场馆");
        refreshPeopleClassBrief(PeopleClassOrderData.peopleClassBriefModelList.subList(0, 3));
    }

    //用户修改日期时回调
    @Override
    public void changeDate(int amount) {
        Log.v("msg","改变日期");
        refreshPeopleClassBrief(PeopleClassOrderData.peopleClassBriefModelList.subList(0, 1));
    }
}
