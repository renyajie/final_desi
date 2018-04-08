package mine.activity.order_card;

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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.MainActivity;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CardInfo;
import bean.CardKind;
import bean.CardOrder;
import bean.User;
import main.helper.SpaceItemDecoration;
import mine.activity.order_card.delegate.OrderCardDetailDelegate;
import mine.activity.order_card.delegate.OrderCardRuleDelegate;
import mine.activity.order_card.delegate.PlaceCardDetailDelegate;
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
 * Created by Thor on 2018/4/6.
 */

public class PlaceCardDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PageName = "会员卡详情";
    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    private TextView expend;
    private LinearLayout pay;

    //会员卡种类编号
    private Integer cardKindId;

    private CardKind cardKind = new CardKind();

    private static final int GET_CARD_KIND_SUCCESS = 1;
    private static final int GET_CARD_KIND_FAILURE = 2;
    private static final int BUY_CARD_SUCCESS = 3;
    private static final int BUY_CARD_FAILURE = 4;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CARD_KIND_FAILURE:
                    Toast.makeText(PlaceCardDetailActivity.this,
                            "获取会员卡信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CARD_KIND_SUCCESS:

                    Messenger messengerA = (Messenger) msg.obj;
                    cardKind =
                            (CardKind) messengerA.getExtend().get("info");

                    //初始化界面信息
                    initPlaceCardDetail(cardKind);
                    initOrderCardRule();
                    expend.setText(String.valueOf(cardKind.getExpend()));
                    break;
                case BUY_CARD_FAILURE:
                    Toast.makeText(PlaceCardDetailActivity.this,
                            "开卡失败", Toast.LENGTH_SHORT).show();
                    break;
                case BUY_CARD_SUCCESS:

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
        setContentView(R.layout.activity_mine_place_card_detail);
        initView();
    }

    private void initView() {

        receiveIntentData();

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        context = this;
        delegates.clear();

        //向RecyclerView中添加各类Item布局
        delegates.add(new PlaceCardDetailDelegate(this));
        delegates.add(new OrderCardRuleDelegate(this));

        recyclerView = findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        pay = findViewById(R.id.pay);
        expend = findViewById(R.id.expend);

        pay.setOnClickListener(this);

        //获取会员卡信息
        getCardKind();
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.cardKindId = bundle.getInt("cardKindId", 0);
        Log.d("get", "会员卡种类号是" + cardKindId);
    }

    //初始化会员卡详情部分
    private void initPlaceCardDetail(CardKind cardKind) {
        int position = getViewHolderPosition(ViewHolderType.PlaceCardDetail);
        if(position == -1) return;
        ((PlaceCardDetailDelegate)delegates.get(position)).setCardKind(cardKind);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化购买会员卡规则
    private void initOrderCardRule() {
        int position = getViewHolderPosition(ViewHolderType.OrderCardRule);
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

    //获取会员卡信息
    private void getCardKind() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getOneCardKind";
        Map<String, String> params = new HashMap<>();
        params.put("cardKId", cardKindId + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_CARD_KIND_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<CardKind>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CARD_KIND_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //开卡
    private void buyCard() {

        //构造请求地址
        String url = AppConstant.URL + "api/order/addCardOrder";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        CardOrder cardOrder = new CardOrder();
        cardOrder.setuId(UtilsMethod.getUserId());
        cardOrder.setCardKId(cardKindId);
        String jsonStr = new Gson().toJson(cardOrder);
        Log.d("get", jsonStr);
        RequestBody body = RequestBody.create(JSON, jsonStr);

        UtilsMethod.postDataAsync(url, body, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = BUY_CARD_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<Object>>(){});
                Message msg = handler.obtainMessage();
                msg.what = BUY_CARD_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //显示提示对话框
    private void showPayDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("购买会员卡");
        normalDialog.setMessage(
                "您确定要购买么？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buyCard();
                    }
                });
        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // 显示
        normalDialog.show();
    }

    private void showSuccessDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("提示");
        normalDialog.setMessage(
                "开卡成功");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        PlaceCardDetailActivity.this.finish();
                    }
                });

        // 显示
        normalDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay:
                showPayDialog();
                break;
            default:
                break;
        }
    }
}
