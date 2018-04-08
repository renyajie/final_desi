package main.activity.recommand_class;

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

import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassInfo;
import bean.Place;
import main.activity.individual_class_order.delegate.IndividualClassBriefDelegate;
import main.activity.people_class_order.delegate.PeopleClassBriefDelegate;
import main.activity.recommand_class.delegate.DateDelegate;
import main.helper.SpaceItemDecoration;
import mine.activity.order_class.delegate.OrderLessonRuleDelegate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.AppConstant;
import utils.MainAdapter;
import utils.Messenger;
import utils.SuperDelegate;
import utils.UtilsMethod;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/4/8.
 */

public class RecommandClassActivity extends AppCompatActivity
implements DateDelegate.ChangeDate{

    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    private String placeName = "";
    private int classKindId;
    private String classKName = "";
    private int isPeopleClass = 1;

    private static final int GET_CLASS_SUCCESS = 1;
    private static final int GET_CLASS_FAILURE = 2;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CLASS_FAILURE:
                    Toast.makeText(RecommandClassActivity.this,
                            "获取课程信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CLASS_SUCCESS:
                    Messenger messengerA = (Messenger) msg.obj;
                    List<ClassInfo> classInfos = (ArrayList<ClassInfo>) messengerA.getExtend().get("info");
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

                    //初始化界面
                    if(isPeopleClass == 1) {
                        initPeopleClassBrief(classInfos);
                    }
                    else {
                        initIndividualClassBrief(classInfos);
                    }
                    initOrderLessonRule();
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
        setContentView(R.layout.activity_main_recommand_class);
        initView();
    }

    private void initView() {
        //接收启动参数
        receiveIntentData();

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(classKName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        context = this;
        delegates.clear();

        //向RecyclerView中添加各类Item布局
        delegates.add(new DateDelegate(this));

        //若课程属性为团课，加载团课布局，否则加载私教布局
        if(isPeopleClass == 1) {
            delegates.add(new PeopleClassBriefDelegate(this));
        }
        else {
            delegates.add(new IndividualClassBriefDelegate(this));
        }
        delegates.add(new OrderLessonRuleDelegate(this));

        recyclerView = findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        //初始化界面
        initDate();

        //请求课程信息
        getClassInfoData(0);
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.classKName = bundle.getString("classKName", null);
        this.placeName = bundle.getString("placeName", null);
        this.classKindId = bundle.getInt("classKindId", 0);
        this.isPeopleClass = bundle.getInt("isPeopleClass", 1);
    }

    //初始化日期选择部分
    private void initDate() {
        int position = getViewHolderPosition(ViewHolderType.Date);
        if(position == -1) return;
        ((DateDelegate)delegates.get(position)).setPlaceName(placeName);
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

    //初始化私教课城详细信息
    private void initIndividualClassBrief(List<ClassInfo> classInfos) {
        int position = getViewHolderPosition(ViewHolderType.IndividualClassBrief);
        if(position == -1) return;
        ((IndividualClassBriefDelegate)delegates.get(position))
                .setClassInfoList(classInfos);
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

    /**
     * 获取课程信息
     * @param nextAmount 第几天
     */
    private void getClassInfoData(int nextAmount) {

        //构造请求地址
        String tmp = AppConstant.URL + "api/order/getClassInfo";
        String before = UtilsMethod.theNextNDayForServer(nextAmount);
        String after = UtilsMethod.theNextNDayForServer(nextAmount + 1);
        Map<String, String> params = new HashMap<>();
        params.put("isPage", "0");
        params.put("before", before);
        params.put("after", after);
        params.put("classKId", classKindId + "");
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

    @Override
    public void changeDate(int amount) {
        getClassInfoData(amount);
    }
}
