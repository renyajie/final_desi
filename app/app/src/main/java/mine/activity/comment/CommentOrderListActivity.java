package mine.activity.comment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassOrder;
import bean.Score;
import mine.activity.comment.adapter.CommentOrderListAdapter;
import mine.activity.order_class.OrderClassListActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/4/9.
 */

public class CommentOrderListActivity extends AppCompatActivity
    implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener{

    private static final String PageName = "课程评价";
    private Toolbar toolbar;
    private ListView listView;
    private RadioGroup radioGroup;
    private CommentOrderListAdapter adapter;
    private LinearLayout noCommentLayout;

    //未评价订单
    private List<ClassOrder> classOrderList = new ArrayList<>();
    //已评价订单
    private List<Score> scoreList = new ArrayList<>();

    //当前列表状态为已经评价还是没有评价
    private int isScore = 1;

    //已评价和未评价
    private static final int SCORE_LESSON = 1;
    private static final int UNSCORE_LESSON = 0;

    private static final int GET_CLASS_ORDER_SUCCESS = 1;
    private static final int GET_CLASS_ORDER_FAILURE = 2;
    private static final int GET_SCORE_SUCCESS = 3;
    private static final int GET_SCORE_FAILURE = 4;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CLASS_ORDER_FAILURE:
                    Toast.makeText(CommentOrderListActivity.this,
                            "获取未评价订单失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CLASS_ORDER_SUCCESS:
                    //更新列表
                    Messenger messengerA = (Messenger) msg.obj;
                    classOrderList = (ArrayList<ClassOrder>)
                            messengerA.getExtend().get("info");

                    if(classOrderList.size() == 0) {
                        listView.setVisibility(View.GONE);
                        noCommentLayout.setVisibility(View.VISIBLE);
                    }
                    else {
                        noCommentLayout.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                    }

                    adapter.setClassOrderList(classOrderList);

                    break;
                case GET_SCORE_FAILURE:
                    Toast.makeText(CommentOrderListActivity.this,
                            "获取已评价订单失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_SCORE_SUCCESS:
                    Messenger messengerB = (Messenger) msg.obj;
                    scoreList = (ArrayList<Score>)
                            messengerB.getExtend().get("info");

                    if(scoreList.size() == 0) {
                        listView.setVisibility(View.GONE);
                        noCommentLayout.setVisibility(View.VISIBLE);
                    }
                    else {
                        noCommentLayout.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                    }

                    adapter.setScoreList(scoreList);
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
        setContentView(R.layout.activity_mine_comment_list);
        initView();
    }

    private void initView() {

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list_view);
        adapter = new CommentOrderListAdapter(this, scoreList, classOrderList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);

        noCommentLayout = findViewById(R.id.no_comment_layout);

        //获取已评价订单
        isScore = SCORE_LESSON;
        getScore();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //若当前在已评价页面，则不发生动作
        if(isScore == SCORE_LESSON) {
            return;
        }
        ClassOrder classOrder = classOrderList.get(position);
        Toast.makeText(this, classOrderList.get(position).getClaKName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CommentOrderActivity.class);
        intent.putExtra("classId", classOrder.getClaId());
        intent.putExtra("placeId", classOrder.getpId());
        intent.putExtra("orderId", classOrder.getId());
        intent.putExtra("property", classOrder.getProperty());
        startActivity(intent);
        this.finish();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.score_lesson:
                isScore = SCORE_LESSON;
                getScore();
                break;
            case R.id.unscore_lesson:
                isScore = UNSCORE_LESSON;
                getClassOrder();
                break;
            default:
                break;
        }
    }

    //获取用户已评价订单列表
    private void getScore() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/recommand/getScore";
        Map<String, String> params = new HashMap<>();
        params.put("userId", UtilsMethod.getUserId() + "");
        params.put("isPage", 0 + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_SCORE_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<List<Score>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_SCORE_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //获取用户订单
    private void getClassOrder() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/order/getClassOrder";
        Map<String, String> params = new HashMap<>();
        params.put("isPage", "0");
        params.put("userId", UtilsMethod.getUserId() + "");
        params.put("isScore", isScore + "");
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
                        response, new TypeToken<Messenger<List<ClassOrder>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CLASS_ORDER_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }
}
