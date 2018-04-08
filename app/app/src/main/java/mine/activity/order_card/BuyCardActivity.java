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
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.MainActivity;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CardKind;
import bean.ClassInfo;
import bean.Place;
import main.activity.people_class_order.PeopleClassOrderActivity;
import main.activity.people_class_order.delegate.PeopleClassBriefDelegate;
import main.activity.people_class_order.delegate.PlaceAndDateDelegate;
import main.activity.people_class_order.model.PlaceModel;
import main.helper.SpaceItemDecoration;
import mine.activity.order_card.adapter.CardKindListAdapter;
import mine.activity.order_card.delegate.CardKindDelegate;
import mine.activity.order_card.delegate.OrderCardRuleDelegate;
import mine.activity.order_card.delegate.PlaceDelegate;
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
 * Created by Thor on 2018/4/7.
 */

public class BuyCardActivity extends AppCompatActivity
        implements
        PlaceDelegate.ChangePlace,
        CardKindListAdapter.FinishActivity,
        CardKindDelegate.CheckCardIsExitsOrNot,
        AdapterView.OnItemClickListener{

    private static final String PageName = "购买会员卡";
    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    private static final int GET_PLACE_SUCCESS = 1;
    private static final int GET_PLACE_FAILURE = 2;
    private static final int GET_CARD_KIND_SUCCESS = 3;
    private static final int GET_CARD_KIND_FAILURE = 4;
    private static final int CHECK_SUCCESS = 5;
    private static final int CHECK_FAILURE = 6;

    private List<PlaceModel> placeModelList = new ArrayList<>();
    private List<CardKind> cardKindList = new ArrayList<>();

    private int cardKId;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_PLACE_FAILURE:
                    Toast.makeText(BuyCardActivity.this,
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
                    initPlace(placeModelList);

                    getCardKindData(placeModelList.get(0).placeId);
                    break;
                case GET_CARD_KIND_FAILURE:
                    Toast.makeText(BuyCardActivity.this,
                            "获取会员卡种类失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CARD_KIND_SUCCESS:
                    Messenger messengerA = (Messenger) msg.obj;
                    cardKindList = (ArrayList<CardKind>) messengerA.getExtend().get("info");

                    initCardKind(cardKindList);
                    initOrderCardRule();
                    break;
                case CHECK_FAILURE:
                    Toast.makeText(BuyCardActivity.this,
                            "会员卡重复检查失败", Toast.LENGTH_SHORT).show();
                    break;
                case CHECK_SUCCESS:
                    Messenger messengerC = (Messenger) msg.obj;
                    Integer result = (Integer) messengerC.getExtend().get("info");

                    //若用户已经拥有该种类会员卡提示不能重复购买，
                    //否则跳转开卡界面
                    if(result == 1) {
                        showNoticeDialog();
                    }
                    else {
                        Intent intent = new Intent(context, PlaceCardDetailActivity.class);
                        intent.putExtra("cardKindId", cardKId);
                        context.startActivity(intent);
                        BuyCardActivity.this.finish();
                    }
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
        setContentView(R.layout.activity_mine_buy_card);
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
        delegates.add(new PlaceDelegate(this));
        delegates.add(new CardKindDelegate(this));
        delegates.add(new OrderCardRuleDelegate(this));

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

    //初始化场馆信息
    private void initPlace(List<PlaceModel> placeModelList) {
        int position = getViewHolderPosition(ViewHolderType.PlaceDetail);
        if(position == -1) return;
        ((PlaceDelegate)delegates.get(position)).setPlaceModelList(placeModelList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化团课预约的课程信息
    private void initCardKind(List<CardKind> cardKindList) {
        int position = getViewHolderPosition(ViewHolderType.CardKind);
        if(position == -1) return;
        ((CardKindDelegate)delegates.get(position))
                .setCardKindList(cardKindList);
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

    //获取指定场馆的会员卡种类信息
    private void getCardKindData(Integer placeId) {
        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getCardKind";
        Map<String, String> params = new HashMap<>();
        params.put("isPage", "0");
        params.put("placeId", placeId + "");
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
                        response, new TypeToken<Messenger<List<CardKind>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CARD_KIND_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void checkCardIsExitsOrNot() {
        //构造请求地址
        String tmp = AppConstant.URL + "api/order/checkCardIsExistOrNot";
        Map<String, String> params = new HashMap<>();
        params.put("cardKId", cardKId + "");
        params.put("userId", UtilsMethod.getUserId() + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = CHECK_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<Integer>>(){});
                Message msg = handler.obtainMessage();
                msg.what = CHECK_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void changePlace(int placeId) {
        getCardKindData(placeId);
    }

    @Override
    public void finishActivity() {
        this.finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        cardKId = cardKindList.get(position).getId();
        Log.d("get", "click item card kind id is " + cardKId);
        checkCardIsExitsOrNot();
    }

    private void showNoticeDialog(){
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
                "您已拥有此种类会员卡，不能重复开卡，只能对原卡进行充值续费。");
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
