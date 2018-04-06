package mine.activity.order_class;

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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import bean.ClassOrder;
import bean.Place;
import main.activity.people_class_order.PeopleClassOrderActivity;
import main.activity.people_class_order.model.PlaceModel;
import mine.activity.order_class.model.OrderClassBriefModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;
import test.OrderClassListData;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/15.
 * <p>
 * 约课信息列表
 */

public class OrderClassListActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener, View.OnClickListener,
        AdapterView.OnItemLongClickListener, OnItemClickListener {

    private static final String PageName = "约课信息";
    private Toolbar toolbar;
    private ListView listView;
    private OrderClassListAdapter adapter;
    private RadioGroup radioGroup;

    private RelativeLayout relativeLayout;
    private Button deleteButton, cancelButton;
    private CheckBox selectAll;
    //当前是否在选择，是否全部选中
    private boolean isSelected = false, isSelectAll = false;
    //当前选中的序列列表
    private List<Integer> indexOfDeleteItem = new ArrayList<>();
    //第一次选中时的位置
    private Integer firstPosition = -1;

    //3种约课类型的订单，第三种为了方便操作删除的情况
    private List<ClassOrder> orderList = new ArrayList<>();

    //课程种类序号
    private static final int ALL_PROPERTY = 1;
    private static final int PEOPLE_PROPERTY = 2;
    private static final int INDIVIDUAL_PROPERTY = 3;
    //当前用户选择的课程类型
    private int classType = 1;

    private static final int GET_CLASS_ORDER_SUCCESS = 1;
    private static final int GET_CLASS_ORDER_FAILURE = 2;
    private static final int DELETE_CLASS_ORDER_SUCCESS = 3;
    private static final int DELETE_CLASS_ORDER_FAILURE = 4;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_CLASS_ORDER_FAILURE:
                    Toast.makeText(OrderClassListActivity.this,
                            "获取订单失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CLASS_ORDER_SUCCESS:
                    //更新列表
                    Messenger messengerA = (Messenger) msg.obj;
                    orderList = (ArrayList<ClassOrder>)
                            messengerA.getExtend().get("info");
                    adapter.setData(orderList);
                    break;
                case DELETE_CLASS_ORDER_FAILURE:
                    Toast.makeText(OrderClassListActivity.this,
                            "删除订单失败", Toast.LENGTH_SHORT).show();
                    break;
                case DELETE_CLASS_ORDER_SUCCESS:
                    //重新设置界面，重置变量
                    isSelected = false;
                    indexOfDeleteItem.clear();
                    relativeLayout.setVisibility(View.GONE);
                    //重新获取订单数据
                    getClassOrder(classType);
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
        setContentView(R.layout.activity_mine_order_class_list);
        initView();
    }

    private void initView() {

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list_view);
        adapter = new OrderClassListAdapter(this, orderList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);

        relativeLayout = findViewById(R.id.relativeLayout);
        deleteButton = findViewById(R.id.delete_button);
        cancelButton = findViewById(R.id.cancel_button);
        selectAll = findViewById(R.id.select_all_checkbox);

        deleteButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        selectAll.setOnClickListener(this);

        classType = ALL_PROPERTY;
        getClassOrder(classType);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            //TODO 改变数据源
            case R.id.all_lesson:
                classType = ALL_PROPERTY;
                getClassOrder(classType);
                break;
            case R.id.people_lesson:
                classType = PEOPLE_PROPERTY;
                getClassOrder(classType);
                break;
            case R.id.individual_lesson:
                classType = INDIVIDUAL_PROPERTY;
                getClassOrder(classType);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_button:
                //若选中，则弹出提示对画框
                if (indexOfDeleteItem.size() > 0) {
                    showNoticeDialog();
                } else {
                    showNoSelectedNoticeDialog();
                }
                break;
            case R.id.cancel_button:
                cancelDeleteStatus();
                break;
            case R.id.select_all_checkbox:
                if (selectAll.isChecked()) {
                    indexOfDeleteItem.clear();
                    for (int i = 0; i < orderList.size(); i++) {
                        indexOfDeleteItem.add(orderList.get(i).getId());
                    }
                    isSelectAll = true;
                    adapter.notifyDataSetChanged();
                } else {
                    indexOfDeleteItem.clear();
                    isSelectAll = false;
                    adapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        int tmpId = orderList.get(position).getId();

        //若当前未开启选择，显示底部删除栏和所有单选框，将选中项加入删除列表
        if (!isSelected) {
            isSelected = true;
            firstPosition = position;
            relativeLayout.setVisibility(View.VISIBLE);
            Log.d("get", "添加了id: " + tmpId);
            indexOfDeleteItem.add(tmpId);
            //通知适配器重新绘制
            adapter.notifyDataSetInvalidated();
        }
        //若当前已开启，反转状态后直接加入删除列表或移出删除列表
        else {
            CheckBox checkBox = view.findViewById(R.id.item_checkbox);
            checkBox.setChecked(!checkBox.isChecked());
            //移除ID值或添加ID值
            if (!checkBox.isChecked() && indexOfDeleteItem.contains(tmpId)) {
                for (int i = 0; i < indexOfDeleteItem.size(); i++) {
                    if (indexOfDeleteItem.get(i).equals(tmpId)) {
                        Log.d("get", "移除了id: " + tmpId);
                        indexOfDeleteItem.remove(i);
                        break;
                    }
                }
            } else if (checkBox.isChecked() && !indexOfDeleteItem.contains(tmpId)) {
                Log.d("get", "添加了id: " + tmpId);
                indexOfDeleteItem.add(tmpId);
            }
        }

        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //当前不在选择状态则跳转详情页面，若在则选中或移出对应项
        if (!isSelected) {
            ClassOrder classOrder = orderList.get(position);
            Intent intent = new Intent(this, OrderDetailActivity.class);
            //根据课程属性，加载不同的布局
            intent.putExtra("viewType",
                    classOrder.getProperty().equals("s") ? AppConstant.INDIVIDUAL_ORDER : AppConstant.PEOPLE_ORDER);
            intent.putExtra("orderId", classOrder.getId());
            intent.putExtra("classId", classOrder.getClaId());
            intent.putExtra("placeId", classOrder.getpId());
            startActivity(intent);
        } else {
            CheckBox checkBox = view.findViewById(R.id.item_checkbox);
            checkBox.setChecked(!checkBox.isChecked());
            int tmpId = orderList.get(position).getId();
            //移除ID值或添加ID值
            if (!checkBox.isChecked() && indexOfDeleteItem.contains(tmpId)) {
                for (int i = 0; i < indexOfDeleteItem.size(); i++) {
                    if (indexOfDeleteItem.get(i).equals(tmpId)) {
                        Log.d("get", "移除了id: " + tmpId);
                        indexOfDeleteItem.remove(i);
                        break;
                    }
                }
            } else if (checkBox.isChecked() && !indexOfDeleteItem.contains(tmpId)) {
                Log.d("get", "添加了id: " + tmpId);
                indexOfDeleteItem.add(tmpId);
            }
        }
    }

    //点击返回键取消删除状态
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0
                    && isSelected) {
                cancelDeleteStatus();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    //取消删除状态
    private void cancelDeleteStatus() {
        isSelected = false;
        isSelectAll = false;
        selectAll.setChecked(false);
        relativeLayout.setVisibility(View.GONE);
        indexOfDeleteItem.clear();
        adapter.notifyDataSetInvalidated();
    }

    //显示删除提示框确认框
    private void showNoSelectedNoticeDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("注意");
        normalDialog.setMessage("您当前未选中任何课程");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
    }

    //显示删除提示框确认框
    private void showNoticeDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("注意");
        normalDialog.setMessage("确认要取消预约么");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        for (int i = 0; i < indexOfDeleteItem.size(); i++) {
//                            //防止出现错删的情况
//                            orderList
//                                    .remove(indexOfDeleteItem.get(i).intValue() - i);
//                        }
                        deleteClassOrder(indexOfDeleteItem);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelDeleteStatus();
                    }
                });
        // 显示
        normalDialog.show();
    }

    //删除用户订单
    private void deleteClassOrder(List<Integer> indexOfDeleteItem) {
        String ids = "";
        for(Integer index: indexOfDeleteItem) {
            ids = ids + index + "-";
        }
        ids = ids.substring(0, ids.length() - 1);

        String url = AppConstant.URL + "api/order/deleteClassOrder";

        FormBody.Builder body = new FormBody.Builder();
        body.add("ids", ids);

        Log.d("get", url);
        Log.d("get", "ids is " + ids);

        UtilsMethod.deleteDataAsync(url, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = DELETE_CLASS_ORDER_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.NORMAL_GSON,
                        response, new TypeToken<Messenger<String>>(){});
                Message msg = handler.obtainMessage();
                msg.what = DELETE_CLASS_ORDER_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    //获取用户订单
    private void getClassOrder(int propertyType) {

        //构造请求地址
        String tmp = AppConstant.URL + "api/order/getClassOrder";
        Map<String, String> params = new HashMap<>();
        params.put("isPage", "0");
        //TODO 修改用户编号
        params.put("userId", 1 + "");
        //判断请求哪种类型的订单
        if(propertyType == PEOPLE_PROPERTY) {
            params.put("property", "g");
        }
        else if(propertyType == INDIVIDUAL_PROPERTY) {
            params.put("property", "s");
        }
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

    /**
     * Created by Thor on 2018/3/15.
     * <p>
     * 约课列表的适配器
     */
    public class OrderClassListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private List<ClassOrder> data;

        public OrderClassListAdapter(Context context,
                                     List<ClassOrder> classOrderList) {
            this.layoutInflater = LayoutInflater.from(context);
            this.data = classOrderList;
        }

        public void setData(List<ClassOrder> data) {
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
            ClassOrder model = data.get(position);
            ViewHolder viewHolder;

            if (convertView == null) {
                viewHolder = new ViewHolder();

                convertView = layoutInflater.inflate(
                        R.layout.activity_mine_order_class_list_item, parent, false);

                viewHolder.classPic = convertView.findViewById(R.id.class_pic);
                viewHolder.className = convertView.findViewById(R.id.class_name);
                viewHolder.orderTime = convertView.findViewById(R.id.order_time);
                viewHolder.placeName = convertView.findViewById(R.id.place_name);
                viewHolder.itemCheckBox = convertView.findViewById(R.id.item_checkbox);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.className.setText(model.getClaKName());
            viewHolder.orderTime.setText(
                    UtilsMethod.getStringFromDateForCheck(model.getOrdTime()));
            viewHolder.placeName.setText(model.getpName());

            //当前正在选择状态则显示勾选框，
            if (isSelected) {
                viewHolder.itemCheckBox.setVisibility(View.VISIBLE);
                //全选或第一次选中

                    if (isSelectAll) {
                        viewHolder.itemCheckBox.setChecked(true);
                    } else {
                        viewHolder.itemCheckBox.setChecked(false);
                    }

                if (firstPosition != -1 && position == firstPosition) {
                    viewHolder.itemCheckBox.setChecked(true);
                    firstPosition = -1;
                }
            } else {
                viewHolder.itemCheckBox.setChecked(false);
                viewHolder.itemCheckBox.setVisibility(View.GONE);
            }

            return convertView;
        }

        class ViewHolder {
            public ImageView classPic;
            public TextView className, orderTime, placeName;
            public CheckBox itemCheckBox;
        }
    }
}
