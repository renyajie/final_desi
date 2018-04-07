package mine.activity.order_card;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CardKind;
import mine.activity.order_card.adapter.CardKindListAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/4/6.
 */

public class PlaceCardListActivity extends AppCompatActivity implements CardKindListAdapter.FinishActivity{

    private Toolbar toolbar;
    private ListView listView;
    private LinearLayout noCardLayout;
    private CardKindListAdapter adapter;

    //场馆编号和场馆名称
    private int placeId;
    private String placeName;

    //会员卡种类
    private List<CardKind> cardKindList = new ArrayList<>();

    private static final int GET_CARD_KIND_SUCCESS = 1;
    private static final int GET_CARD_KIND_FAILURE = 2;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CARD_KIND_FAILURE:
                    Toast.makeText(PlaceCardListActivity.this,
                            "获取会员卡种类失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CARD_KIND_SUCCESS:

                    Messenger messengerA = (Messenger) msg.obj;
                    cardKindList = (ArrayList<CardKind>)
                            messengerA.getExtend().get("info");

                    //若有会员卡则设置会员卡可见，否则显示默认界面
                    if(cardKindList.size() == 0) {
                        listView.setVisibility(View.GONE);
                        noCardLayout.setVisibility(View.VISIBLE);
                    }
                    else {
                        noCardLayout.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        adapter.setData(cardKindList);
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
        setContentView(R.layout.activity_mine_place_card_list);
        initView();
    }

    private void initView() {

        receiveIntentData();

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(placeName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list_view);
        noCardLayout = findViewById(R.id.no_card_layout);

        adapter = new CardKindListAdapter(this, cardKindList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);

        getCardKind();
    }

    //接收Intent启动参数
    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.placeId = bundle.getInt("placeId", 0);
        this.placeName = bundle.getString("placeName", "瑜伽馆");
        Log.d("get", "课程编号是" + placeId);

    }

    //获取会员卡种类
    public void getCardKind() {
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
    public void finishActivity() {
        this.finish();
    }
}
