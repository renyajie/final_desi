package main.activity.people_class_order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;
import com.renyajie.yuyue.TestActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassInfo;
import bean.Place;
import main.activity.people_class_order.delegate.PeopleClassBriefDelegate;
import main.activity.people_class_order.delegate.PlaceAndDateDelegate;
import main.activity.people_class_order.model.PeopleClassBriefModel;
import main.activity.people_class_order.model.PlaceModel;
import main.helper.SpaceItemDecoration;
import mine.activity.order_class.delegate.OrderLessonRuleDelegate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.PeopleClassOrderData;
import utils.AppConstant;
import utils.MainAdapter;
import utils.Messenger;
import utils.SuperDelegate;
import utils.UtilsMethod;
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

    private static final int GET_PLACE_SUCCESS = 1;
    private static final int GET_PLACE_FAILURE = 2;
    private static final int GET_CLASS_SUCCESS = 3;
    private static final int GET_CLASS_FAILURE = 4;

    //应用数据
    private List<PlaceModel> placeModelList = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_PLACE_FAILURE:
                    Toast.makeText(PeopleClassOrderActivity.this,
                            "获取场馆失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_PLACE_SUCCESS:
                    //获取地址成功，填充地址信息，并发起课程查询
                    Messenger messengerB = (Messenger) msg.obj;
                    List<Place> placeList = (ArrayList<Place>) messengerB.getExtend().get("info");
                    placeModelList.clear();
                    for(Place place: placeList) {
                        PlaceModel model = new PlaceModel(place.getId(), place.getsName());
                        placeModelList.add(model);
                    }
                    initClass(placeModelList);

                    getClassInfoData(placeModelList.get(0).placeId, 0);
                    break;
                case GET_CLASS_FAILURE:
                    Toast.makeText(PeopleClassOrderActivity.this,
                            "获取课程信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CLASS_SUCCESS:
                    Messenger messengerD = (Messenger) msg.obj;
                    List<ClassInfo> classInfos = (ArrayList<ClassInfo>) messengerD.getExtend().get("info");
                    for(ClassInfo classInfo: classInfos) {
                        if(classInfo.getStaTime().getTime() <= new Date().getTime()) {
                            classInfo.setStatus(AppConstant.PEOPLE_ORDER_START_LESSON);
                        }
                        else if(classInfo.getAllowance() == 0) {
                            classInfo.setStatus(AppConstant.PEOPLE_ORDER_FULL);
                        }
                        else {
                            classInfo.setStatus(AppConstant.PEOPLE_ORDER_CAN_ORDER);
                        }
                    }
                    initPeopleClassBrief(classInfos);
                    initOrderLessonRule();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

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
        //initClass(PeopleClassOrderData.placeModelList);
        //initPeopleClassBrief(PeopleClassOrderData.peopleClassBriefModelList);

        //网络请求数据
        getPlaceData();
    }

    //初始化课程名称信息
    private void initClass(List<PlaceModel> placeModelList) {
        int position = getViewHolderPosition(ViewHolderType.ClassAndDate);
        if(position == -1) return;
        ((PlaceAndDateDelegate)delegates.get(position)).setPlaceModelList(placeModelList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化团课预约的课程信息
    private void initPeopleClassBrief(List<ClassInfo> classInfoList) {
        int position = getViewHolderPosition(ViewHolderType.PeopleClassBrief);
        if(position == -1) return;
        ((PeopleClassBriefDelegate)delegates.get(position))
                .setClassInfoList(classInfoList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化约课规则
    private void initOrderLessonRule() {
        int position = getViewHolderPosition(ViewHolderType.OrderLessonRule);
        if(position == -1) return;
        if(adapter != null) adapter.updatePositionDelegate(position);
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

    //用户修改场馆或时间时回调
    @Override
    public void changePlaceOrDate(int placeId, int amount) {
        Log.v("msg", "改变搜索条件");
        getClassInfoData(placeId, amount);
    }

    //获取地址信息
    private void getPlaceData() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getPlace";
        Map<String, String> params = new HashMap<>();
        params.put("isPage", "0");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_PLACE_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_HOUR,
                        response, new TypeToken<Messenger<List<Place>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_PLACE_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    /**
     * 获取课程信息
     * @param placeId 地址编号
     * @param nextAmount 第几天
     */
    private void getClassInfoData(int placeId, int nextAmount) {

        //构造请求地址
        String tmp = AppConstant.URL + "api/order/getClassInfo";
        String before = UtilsMethod.theNextNDayForServer(nextAmount);
        String after = UtilsMethod.theNextNDayForServer(nextAmount + 1);
        Map<String, String> params = new HashMap<>();
        params.put("isPage", "0");
        params.put("before", before);
        params.put("after", after);
        params.put("placeId", placeId + "");
        params.put("property", "g");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_CLASS_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_HOUR,
                        response, new TypeToken<Messenger<List<ClassInfo>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CLASS_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }
}
