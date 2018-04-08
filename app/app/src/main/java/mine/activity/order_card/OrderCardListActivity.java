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

import bean.CardInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/4/6.
 */
public class OrderCardListActivity extends AppCompatActivity{

    private static final String PageName = "会员卡订单";
    private Toolbar toolbar;
    private ListView listView;
    private LinearLayout noCardLayout;
    private CardInfoListAdapter adapter;

    //会员卡订单
    private List<CardInfo> cardInfoList = new ArrayList<>();

    private static final int GET_CARD_ORDER_SUCCESS = 1;
    private static final int GET_CARD_ORDER_FAILURE = 2;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CARD_ORDER_FAILURE:
                    Toast.makeText(OrderCardListActivity.this,
                            "获取订单失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CARD_ORDER_SUCCESS:

                    Messenger messengerA = (Messenger) msg.obj;
                    cardInfoList = (ArrayList<CardInfo>)
                            messengerA.getExtend().get("info");

                    //若有会员卡则设置会员卡可见，否则显示默认界面
                    if(cardInfoList.size() == 0) {
                        listView.setVisibility(View.GONE);
                        noCardLayout.setVisibility(View.VISIBLE);
                    }
                    else {
                        noCardLayout.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        adapter.setData(cardInfoList);
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
        setContentView(R.layout.activity_mine_order_card_list);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list_view);
        noCardLayout = findViewById(R.id.no_card_layout);

        adapter = new CardInfoListAdapter(this, cardInfoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);

        getCardInfo();
    }

    //获取用户的会员卡信息
    public void getCardInfo() {
        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getCardInfo";
        Map<String, String> params = new HashMap<>();
        params.put("isPage", "0");
        params.put("userId", UtilsMethod.getUserId() + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_CARD_ORDER_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<List<CardInfo>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_CARD_ORDER_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    public class CardInfoListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

        private Context context;
        private LayoutInflater layoutInflater;
        private List<CardInfo> data;

        public CardInfoListAdapter(Context context,
                                     List<CardInfo> cardInfoList) {
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
            this.data = cardInfoList;
        }

        public void setData(List<CardInfo> data) {
            this.data = data;
            notifyDataSetInvalidated();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CardInfo model = data.get(position);
            ViewHolder viewHolder;

            if (convertView == null) {
                viewHolder = new ViewHolder();

                convertView = layoutInflater.inflate(
                        R.layout.activity_mine_order_card_list_item, parent, false);

                viewHolder.classPic = convertView.findViewById(R.id.class_pic);
                viewHolder.cardName = convertView.findViewById(R.id.card_name);
                viewHolder.allowance = convertView.findViewById(R.id.allowance);
                viewHolder.placeName = convertView.findViewById(R.id.place_name);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.cardName.setText(model.getCardKName());
            viewHolder.allowance.setText(String.valueOf(model.getAllowance()));
            viewHolder.placeName.setText(model.getsName());

            return convertView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            CardInfo cardInfo = cardInfoList.get(position);
            Log.d("get", "card name is " + cardInfo.getCardKName());
            Intent intent = new Intent(context, OrderCardDetailActivity.class);
            intent.putExtra("cardId", cardInfo.getId());
            startActivity(intent);
        }

        class ViewHolder {
            public ImageView classPic;
            public TextView cardName, allowance, placeName;
        }
    }

}
