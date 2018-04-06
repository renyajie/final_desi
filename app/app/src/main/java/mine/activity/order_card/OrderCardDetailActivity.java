package mine.activity.order_card;

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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CardInfo;
import main.helper.SpaceItemDecoration;
import mine.activity.order_card.delegate.OrderCardDetailDelegate;
import mine.activity.order_card.delegate.OrderCardRuleDelegate;
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
 * Created by Thor on 2018/4/6.
 */

public class OrderCardDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PageName = "会员卡详情";
    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    private TextView expend;
    private LinearLayout pay;

    //会员卡编号
    private Integer cardId;

    private CardInfo cardInfo = new CardInfo();

    private static final int GET_CARD_INFO_SUCCESS = 1;
    private static final int GET_CARD_INFO_FAILURE = 2;
    private static final int UPDATE_CARD_INFO_SUCCESS = 3;
    private static final int UPDATE_CARD_INFO_FAILURE = 4;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CARD_INFO_FAILURE:
                    Toast.makeText(OrderCardDetailActivity.this,
                            "获取会员卡信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CARD_INFO_SUCCESS:

                    Messenger messengerA = (Messenger) msg.obj;
                    cardInfo =
                            (CardInfo)messengerA.getExtend().get("info");

                    //初始化界面信息
                    initCardDetail(cardInfo);
                    initOrderCardRule();
                    expend.setText(String.valueOf(cardInfo.getExpend()));
                    break;
                case UPDATE_CARD_INFO_FAILURE:
                    Toast.makeText(OrderCardDetailActivity.this,
                            "续费失败", Toast.LENGTH_SHORT).show();
                    break;
                case UPDATE_CARD_INFO_SUCCESS:
                    Messenger messengerB = (Messenger) msg.obj;
                    double allowance =
                            (Double)messengerB.getExtend().get("info");
                    cardInfo.setAllowance((int) allowance);
                    initCardDetail(cardInfo);

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
        setContentView(R.layout.activity_mine_order_card_detail);
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
        delegates.add(new OrderCardDetailDelegate(this));
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
        getCardInfo();
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.cardId = bundle.getInt("cardId", 0);
        Log.d("get", "会员卡号是" + cardId);
    }

    //初始化会员卡详情部分
    private void initCardDetail(CardInfo cardInfo) {
        int position = getViewHolderPosition(ViewHolderType.OrderCardDetail);
        if(position == -1) return;
        ((OrderCardDetailDelegate)delegates.get(position)).setCardInfo(cardInfo);
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

    //获取登录用户的会员卡信息
    private void getCardInfo() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getOneCardInfo";
        Map<String, String> params = new HashMap<>();
        params.put("cardId", cardId + "");
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
                        response, new TypeToken<Messenger<CardInfo>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CARD_INFO_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //会员卡续费
    private void updateCardCapacity() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/order/updateCardCapacity";
        Map<String, String> params = new HashMap<>();
        params.put("cardId", cardId + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = UPDATE_CARD_INFO_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<Object>>(){});
                Message msg = handler.obtainMessage();
                msg.what = UPDATE_CARD_INFO_SUCCESS;
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
                "您确定要充值会员卡次数么？充值后您的会员卡次数将增加"
                        + cardInfo.getCapacity() + "次。");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateCardCapacity();
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
                "续费成功");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


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
