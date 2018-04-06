package mine.activity.order_class;

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
import android.view.View;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassInfo;
import bean.ClassOrder;
import bean.Place;
import main.activity.people_class_order.PeopleClassOrderActivity;
import main.activity.people_order_confirm.PeopleOrderConfirmActivity;
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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.OrderDetailData;
import utils.AppConstant;
import utils.MainAdapter;
import utils.Messenger;
import utils.SuperDelegate;
import utils.UtilsMethod;
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

    //课程订单信息
    private ClassOrder classOrder = new ClassOrder();
    private ClassInfo classInfo = new ClassInfo();
    private Place place = new Place();

    //加载哪种团课布局，还是私教布局
    private Integer viewType;
    //订单编号
    private Integer orderId;
    //课程编号
    private Integer classId;
    //场馆编号
    private Integer placeId;

    private static final int GET_CLASS_ORDER_SUCCESS = 1;
    private static final int GET_CLASS_ORDER_FAILURE = 2;
    private static final int GET_CLASS_SUCCESS = 3;
    private static final int GET_CLASS_FAILURE = 4;
    private static final int GET_PLACE_SUCCESS = 5;
    private static final int GET_PLACE_FAILURE = 6;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CLASS_ORDER_FAILURE:
                    Toast.makeText(OrderDetailActivity.this,
                            "获取订单失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CLASS_ORDER_SUCCESS:
                    //更新列表
                    Messenger messengerA = (Messenger) msg.obj;
                    classOrder = (ClassOrder)
                            messengerA.getExtend().get("info");

                    initOrderDetail(classOrder);
                    initOrderLessonRule();
                    break;
                case GET_CLASS_FAILURE:
                    Toast.makeText(OrderDetailActivity.this,
                            "获取课程信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CLASS_SUCCESS:
                    //初始化课程详情界面，获取用户会员卡信息
                    Messenger messengerB = (Messenger) msg.obj;
                    classInfo =
                            (ClassInfo)messengerB.getExtend().get("info");
                    Log.d("get", "classInfo is " + classInfo);

                    initOrderLessonDetail(classInfo);

                    //获取地址信息
                    getPlaceById(placeId);
                    break;
                case GET_PLACE_FAILURE:
                    Toast.makeText(OrderDetailActivity.this,
                            "获取场馆失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_PLACE_SUCCESS:
                    Messenger messengerC = (Messenger) msg.obj;
                    place = (Place) messengerC.getExtend().get("info");

                    initOrderPlace(place);
                    //获取订单详情
                    getClassOrderById(orderId);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };



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
        //initOrderLessonDetail(classInfo);
        //initOrderPlace(place);
        //initOrderDetail(classOrder);
        getClassInfoDataById(classId);
    }

    //初始化课程信息部分
    private void initOrderLessonDetail(ClassInfo classInfo) {
        int position = getViewHolderPosition(ViewHolderType.OrderLesson);
        if(position == -1) return;
        ((OrderLessonDetailDelegate)delegates.get(position))
                    .setClassInfo(classInfo);
        ((OrderLessonDetailDelegate)delegates.get(position)).setViewType(viewType);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化地址部分
    private void initOrderPlace(Place place) {
        int position = getViewHolderPosition(ViewHolderType.OrderPlace);
        if(position == -1) return;
        ((OrderPlaceDelegate)delegates.get(position))
                .setPlace(place);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化订单详情部分
    private void initOrderDetail(ClassOrder classOrder) {
        int position = getViewHolderPosition(ViewHolderType.OrderDetail);
        if(position == -1) return;
        ((OrderDetailDelegate)delegates.get(position))
                .setClassOrder(classOrder);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化约课规则
    private void initOrderLessonRule() {
        int position = getViewHolderPosition(ViewHolderType.OrderLessonRule);
        if(position == -1) return;
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.viewType = bundle.getInt("viewType", 0);
        this.orderId = bundle.getInt("orderId", 0);
        this.classId = bundle.getInt("classId", 0);
        this.placeId = bundle.getInt("placeId", 0);

        Log.d("get", "classId id is " + classId);
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

    //获取订单详情
    private void getClassOrderById(int orderId) {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getOneClassOrder";
        Map<String, String> params = new HashMap<>();
        params.put("orderId", orderId + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_CLASS_ORDER_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<ClassOrder>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CLASS_ORDER_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //获取课程信息
    private void getClassInfoDataById(int classId) {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getOneClassInfo";
        Map<String, String> params = new HashMap<>();
        //TODO 修改成目前登录用户的ID
        params.put("id", classId + "");
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
                Log.d("get", "get classinfo success");
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_HOUR,
                        response, new TypeToken<Messenger<ClassInfo>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CLASS_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //获取场馆信息
    private void getPlaceById(int placeId) {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getOnePlace";
        Map<String, String> params = new HashMap<>();
        params.put("id", placeId + "");
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
                        response, new TypeToken<Messenger<Place>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_PLACE_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }
}
