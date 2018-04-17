package main.activity.class_comment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
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
import bean.Score;
import main.activity.class_comment.delegate.ClassCommentDelegate;
import main.activity.individual_order_confirm.IndividualOrderConfirmActivity;
import main.activity.individual_order_confirm.delegate.IndividualOrderDetailDelegate;
import main.activity.people_order_confirm.PeopleOrderConfirmActivity;
import main.activity.people_order_confirm.delegate.PeopleOrderDetailDelegate;
import main.helper.SpaceItemDecoration;
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
 * Created by Thor on 2018/4/10.
 */

public class ClassCommentActivity extends AppCompatActivity
implements View.OnClickListener{

    private static final String PageName = "课程详情";
    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;
    private Button confirmButton;

    //课程编号，课程种类编号
    private Integer classId, classKId;
    //课程种类
    private String property;

    //两种课程属性
    private final static String INDIVIDUAL = "s";
    private final static String PEOPLE = "g";

    private ClassInfo mClassInfo = new ClassInfo();

    private static final int GET_CLASS_INFO_SUCCESS = 1;
    private static final int GET_CLASS_INFO_FAILURE = 2;
    private static final int GET_COMMENT_SUCCESS = 3;
    private static final int GET_COMMENT_FAILURE = 4;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CLASS_INFO_FAILURE:
                    Toast.makeText(ClassCommentActivity.this,
                            "获取课程信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CLASS_INFO_SUCCESS:
                    //初始化课程详情界面
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

                    //根据课程种类初始化界面
                    if(property.equals(INDIVIDUAL)) {
                        initIndividualOrderDetail(classInfo);
                    }
                    else {
                        initPeopleOrderDetail(classInfo);
                    }

                    mClassInfo = classInfo;
                    //获取评价信息
                    getScore();
                    break;
                case GET_COMMENT_FAILURE:
                    Toast.makeText(ClassCommentActivity.this,
                            "获取课程评价失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_COMMENT_SUCCESS:
                    Messenger messengerB = (Messenger) msg.obj;
                    List<Score> scoreList = (ArrayList<Score>)
                            messengerB.getExtend().get("info");

                    //初始化评价界面
                    initClassComment(scoreList);
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
        setContentView(R.layout.activity_main_class_comment);
        initView();
    }

    private void initView() {

        //接收Intent参数
        receiveIntentData();

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        context = this;
        delegates.clear();

        //向RecyclerView中添加各类Item布局/
        if(property.equals(PEOPLE)) {
            delegates.add(new PeopleOrderDetailDelegate(this));
        }
        else {
            delegates.add(new IndividualOrderDetailDelegate(this));
        }
        delegates.add(new ClassCommentDelegate(this));

        recyclerView = findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(this);

        //获取课程信息数据
        getClassInfoDataById();
    }

    //初始化课程详情部分--若是团课
    private void initPeopleOrderDetail(ClassInfo classInfo) {
        int position = getViewHolderPosition(ViewHolderType.PeopleOrderDetail);
        if(position == -1) return;
        ((PeopleOrderDetailDelegate)delegates.get(position)).setClassInfo(classInfo);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化课程详情部分--若是私教
    private void initIndividualOrderDetail(ClassInfo classInfo) {
        int position = getViewHolderPosition(ViewHolderType.IndividualOrderDetail);
        if(position == -1) return;
        ((IndividualOrderDetailDelegate)delegates.get(position))
                .setClassInfo(classInfo);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化课程评价部分
    private void initClassComment(List<Score> scoreList) {
        int position = getViewHolderPosition(ViewHolderType.ClassComment);
        if(position == -1) return;
        ((ClassCommentDelegate)delegates.get(position))
                .setScoreList(scoreList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.classId = bundle.getInt("classId", 0);
        this.classKId = bundle.getInt("classKId", 0);
        this.property = bundle.getString("property", "s");
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
    private void getClassInfoDataById() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getOneClassInfo";
        Map<String, String> params = new HashMap<>();
        params.put("id", classId + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_CLASS_INFO_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_HOUR,
                        response, new TypeToken<Messenger<ClassInfo>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CLASS_INFO_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //获取某一课程种类的评价
    private void getScore() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/recommand/getScore";
        Map<String, String> params = new HashMap<>();
        params.put("classKId", classKId + "");
        params.put("isPage", 0 + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_COMMENT_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<List<Score>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_COMMENT_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_button:

                //跳转订单支付页面
                Intent intent = null;
                Bundle bundle = new Bundle();
                bundle.putInt("placeId", mClassInfo.getpId());
                bundle.putInt("classId", mClassInfo.getId());

                if(property.equals(PEOPLE)) {
                    intent = new Intent(this, PeopleOrderConfirmActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    intent = new Intent(this, IndividualOrderConfirmActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
