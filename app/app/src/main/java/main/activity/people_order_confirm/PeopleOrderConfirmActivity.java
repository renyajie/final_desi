package main.activity.people_order_confirm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CardInfo;
import bean.CardOrder;
import bean.ClassInfo;
import bean.Place;
import main.activity.people_class_order.PeopleClassOrderActivity;
import main.activity.people_class_order.delegate.PlaceAndDateDelegate;
import main.activity.people_class_order.model.PlaceModel;
import main.activity.people_order_confirm.delegate.PeopleOrderDetailDelegate;
import main.activity.people_order_confirm.delegate.PeopleOrderPayDelegate;
import main.activity.people_order_confirm.model.CardModel;
import main.activity.people_order_confirm.model.PeopleOrderDetailModel;
import main.activity.people_order_confirm.model.PeopleOrderPayModel;
import main.helper.SpaceItemDecoration;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.PeopleOrderConfirmData;
import utils.AppConstant;
import utils.MainAdapter;
import utils.Messenger;
import utils.SuperDelegate;
import utils.UtilsMethod;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/13.
 *
 * 订单预约界面
 */

public class PeopleOrderConfirmActivity extends AppCompatActivity {

    //标题栏名称
    private static final String PageName = "课程预约";
    //课程编号
    private Integer classId;

    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    private static final int GET_CARD_INFO_SUCCESS = 1;
    private static final int GET_CARD_INFO_FAILURE = 2;
    private static final int GET_CLASS_SUCCESS = 3;
    private static final int GET_CLASS_FAILURE = 4;

    private ClassInfo mClassInfo;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CLASS_FAILURE:
                    Toast.makeText(PeopleOrderConfirmActivity.this,
                            "获取课程信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CLASS_SUCCESS:
                    //初始化课程详情界面，获取用户会员卡信息
                    Messenger messengerA = (Messenger) msg.obj;
                    ClassInfo classInfo =
                            (ClassInfo)messengerA.getExtend().get("info");

                    if(classInfo.getStaTime().getTime() <= new Date().getTime()) {
                        classInfo.setStatus(AppConstant.PEOPLE_ORDER_START_LESSON);
                    }
                    else if(classInfo.getAllowance() == 0) {
                        classInfo.setStatus(AppConstant.PEOPLE_ORDER_FULL);
                    }
                    else {
                        classInfo.setStatus(AppConstant.PEOPLE_ORDER_CAN_ORDER);
                    }

                    initOrderDetail(classInfo);
                    mClassInfo = classInfo;

                    getCardInfo();
                    break;
                case GET_CARD_INFO_FAILURE:
                    Toast.makeText(PeopleOrderConfirmActivity.this,
                            "获取会员卡信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CARD_INFO_SUCCESS:
                    Messenger messengerB = (Messenger) msg.obj;
                    List<CardInfo> cardInfos =
                            (List<CardInfo>) messengerB.getExtend().get("info");

                    //若无会员卡，提示前往购买
                    if(cardInfos.size() == 0) {
                        showNoticeDialog();
                    }

                    //初始化支付部分的界面
                    PeopleOrderPayModel model = new PeopleOrderPayModel();
                    model.placeId = mClassInfo.getpId();
                    model.classId = mClassInfo.getId();
                    model.cardInformation = cardInfos;

                    initOrderPay(model);

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
        setContentView(R.layout.activity_main_people_order_confirm);
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
        delegates.add(new PeopleOrderDetailDelegate(this));
        delegates.add(new PeopleOrderPayDelegate(this));

        recyclerView = findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        //获取课程信息数据
        getClassInfoDataById(classId);
        //initOrderDetail(PeopleOrderConfirmData.peopleOrderDetailModel);
        //initOrderPay(PeopleOrderConfirmData.peopleOrderPayModel);
    }

    //初始化课程详情部分
    private void initOrderDetail(ClassInfo classInfo) {
        int position = getViewHolderPosition(ViewHolderType.PeopleOrderDetail);
        if(position == -1) return;
        ((PeopleOrderDetailDelegate)delegates.get(position)).setClassInfo(classInfo);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化订单详情部分
    private void initOrderPay(PeopleOrderPayModel model) {
        int position = getViewHolderPosition(ViewHolderType.PeopleOrderPay);
        if(position == -1) return;
        ((PeopleOrderPayDelegate)delegates.get(position)).setPeopleOrderPayModel(model);
        ((PeopleOrderPayDelegate)delegates.get(position)).setWhereStart(AppConstant.PEOPLE_ORDER);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.classId = bundle.getInt("classId", 0);
        Log.d("msg", "课程编号是" + classId);


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
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_HOUR,
                        response, new TypeToken<Messenger<ClassInfo>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CLASS_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //获取登录用户的会员卡信息
    private void getCardInfo() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getCardInfo";
        Map<String, String> params = new HashMap<>();
        //TODO 修改成目前登录用户的ID
        params.put("userId", 1 + "");
        params.put("isPage", 0 + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_CARD_INFO_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<List<CardInfo>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CARD_INFO_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //显示对话框
    private void showNoticeDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("注意");
        normalDialog.setMessage("您目前没有会员卡，需要先购买会员卡才能预约。");
        normalDialog.setPositiveButton("前往购买",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(PeopleOrderConfirmActivity.this,
                                "跳转会员卡购买界面", Toast.LENGTH_SHORT).show();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //关闭Activity
                        PeopleOrderConfirmActivity.this.finish();
                    }
                });
        // 显示
        normalDialog.show();
    }
}
