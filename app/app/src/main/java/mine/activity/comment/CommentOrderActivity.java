package mine.activity.comment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CardInfo;
import bean.ClassInfo;
import bean.ClassOrder;
import bean.Score;
import main.activity.individual_order_confirm.delegate.IndividualOrderDetailDelegate;
import main.activity.people_order_confirm.PeopleOrderConfirmActivity;
import main.activity.people_order_confirm.delegate.PeopleOrderDetailDelegate;
import main.activity.people_order_confirm.delegate.PeopleOrderPayDelegate;
import main.activity.people_order_confirm.model.PeopleOrderPayModel;
import main.helper.SpaceItemDecoration;
import mine.activity.comment.delegate.CommentStringDelegate;
import mine.activity.comment.delegate.RatingDelegate;
import mine.activity.order_card.PlaceCardListActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.AppConstant;
import utils.MainAdapter;
import utils.Messenger;
import utils.SuperDelegate;
import utils.UtilsMethod;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/4/9.
 */

public class CommentOrderActivity extends AppCompatActivity
        implements RatingDelegate.ChangeRatingValue,
        CommentStringDelegate.ChangeCommentString,
        CommentStringDelegate.SubmitClick{

    //标题栏名称
    private static final String PageName = "课程评价";
    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    //课程编号和订单编号，场馆编号
    private Integer classId, orderId, placeId;
    //课程种类
    private String property;
    //两种课程属性
    private final static String INDIVIDUAL = "s";
    private final static String PEOPLE = "g";
    //用户评分
    private float ratingValue = 0.0f;
    //用户评论
    private String commentString = "";

    private static final int MAKE_SCORE_SUCCESS = 1;
    private static final int MAKE_SCORE_FAILURE = 2;
    private static final int GET_CLASS_SUCCESS = 3;
    private static final int GET_CLASS_FAILURE = 4;

    private ClassInfo mClassInfo;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CLASS_FAILURE:
                    Toast.makeText(CommentOrderActivity.this,
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

                    //根据课程种类初始化界面
                    if(property.equals(INDIVIDUAL)) {
                        initIndividualOrderDetail(classInfo);
                    }
                    else {
                        initPeopleOrderDetail(classInfo);
                    }

                    //初始化其他组件
                    initRating();
                    initCommentString();
                    mClassInfo = classInfo;

                    break;
                case MAKE_SCORE_FAILURE:
                    Toast.makeText(CommentOrderActivity.this,
                            "评价提交失败", Toast.LENGTH_SHORT).show();
                    break;
                case MAKE_SCORE_SUCCESS:
                    //显示提交成功对话框
                    showSuccessDialog();
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
        setContentView(R.layout.activity_mine_comment_order);
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
        if(property.equals(PEOPLE)) {
            delegates.add(new PeopleOrderDetailDelegate(this));
        }
        else {
            delegates.add(new IndividualOrderDetailDelegate(this));
        }
        delegates.add(new RatingDelegate(this));
        delegates.add(new CommentStringDelegate(this));

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

    //初始化评分栏
    private void initRating() {
        int position = getViewHolderPosition(ViewHolderType.COMMENT_RATING);
        if(position == -1) return;
        ((RatingDelegate)delegates.get(position)).setChangeRatingValue(this);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化用户评论部分
    private void initCommentString() {
        int position = getViewHolderPosition(ViewHolderType.COMMENT_STRING);
        if(position == -1) return;
        ((CommentStringDelegate)delegates.get(position)).setChangeCommentString(this);
        ((CommentStringDelegate)delegates.get(position)).setSubmitClick(this);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.classId = bundle.getInt("classId", 0);
        this.orderId = bundle.getInt("orderId", 0);
        this.placeId = bundle.getInt("placeId", 0);
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
    private void getClassInfoDataById(int classId) {

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

    //提交用户评价
    private void addScore() {

        String url = AppConstant.URL + "api/recommand/addScore";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        Score score = new Score();
        score.setClaId(classId);
        score.setComment(commentString);
        score.setOrderId(orderId);
        score.setpId(placeId);
        score.setScore(ratingValue);
        score.setuId(UtilsMethod.getUserId());
        score.setAge(UtilsMethod.getUserAge());
        score.setGender(UtilsMethod.getUserGender());
        String jsonStr = new Gson().toJson(score);
        Log.d("get", jsonStr);
        RequestBody body = RequestBody.create(JSON, jsonStr);

        UtilsMethod.postDataAsync(url, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = MAKE_SCORE_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.NORMAL_GSON,
                        response, new TypeToken<Messenger<Object>>(){});
                Message msg = handler.obtainMessage();
                msg.what = MAKE_SCORE_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void changeRatingValue(float newValue) {
        this.ratingValue = newValue;
    }

    @Override
    public void changeCommentString(String comment) {
        this.commentString = comment;
    }

    @Override
    public void submitComment() {
        //检查数据合理性
        if(ratingValue == 0.0f) {
            showNoticeDialog();
            return;
        }

        //向服务器提交评价
        addScore();
    }

    //显示提交成功对话框
    private void showSuccessDialog(){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("评价提交成功");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CommentOrderActivity.this,
                                CommentOrderListActivity.class);
                        startActivity(intent);
                        CommentOrderActivity.this.finish();
                    }
                });
        // 显示
        normalDialog.show();
    }

    //显示对话框
    private void showNoticeDialog(){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("注意");
        normalDialog.setMessage("课程评分不能为空");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        // 显示
        normalDialog.show();
    }
}
