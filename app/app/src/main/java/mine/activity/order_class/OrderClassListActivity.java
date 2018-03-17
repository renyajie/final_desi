package mine.activity.order_class;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import com.renyajie.yuyue.R;

import java.util.ArrayList;
import java.util.List;

import mine.activity.order_class.model.OrderClassBriefModel;
import test.OrderClassListData;

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
    //TODO 改变数据源
    private List<OrderClassBriefModel> orderClassBriefModelList =
            new ArrayList<>(OrderClassListData.orderClassBriefModelList);

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
        adapter = new OrderClassListAdapter(this, orderClassBriefModelList);
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
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            //TODO 改变数据源
            case R.id.all_lesson:
                adapter.setData(orderClassBriefModelList);
                break;
            case R.id.people_lesson:
                adapter.setData(orderClassBriefModelList.subList(
                        0, 1));
                break;
            case R.id.individual_lesson:
                adapter.setData(orderClassBriefModelList.subList(
                        1, 2));
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
                    for (int i = 0; i < orderClassBriefModelList.size(); i++) {
                        Log.d("msg", " indexOfDeleteItem add " + i);
                        indexOfDeleteItem.add(new Integer(i));
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
        //若当前未开启选择，显示底部删除栏和所有单选框，将选中项加入删除列表
        if (!isSelected) {
            isSelected = true;
            firstPosition = position;
            relativeLayout.setVisibility(View.VISIBLE);
            indexOfDeleteItem.add(new Integer(position));
            //通知适配器重新绘制
            adapter.notifyDataSetInvalidated();
        }
        //若当前已开启，反转状态后直接加入删除列表或移出删除列表
        else {
            CheckBox checkBox = view.findViewById(R.id.item_checkbox);
            checkBox.setChecked(!checkBox.isChecked());
            if (!checkBox.isChecked() && indexOfDeleteItem.contains(position)) {
                for (int i = 0; i < indexOfDeleteItem.size(); i++) {
                    if (indexOfDeleteItem.get(i).equals(new Integer(position))) {
                        Log.d("msg", " indexOfDeleteItem remove " + position);
                        indexOfDeleteItem.remove(i);
                        break;
                    }
                }
            } else if (checkBox.isChecked() && !indexOfDeleteItem.contains(position)) {
                Log.d("msg", " indexOfDeleteItem add " + position);
                indexOfDeleteItem.add(new Integer(position));
            }
        }

        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //当前不在选择状态则跳转详情页面，若在则选中或移出对应项
        if (!isSelected) {
            OrderClassBriefModel model = OrderClassListData.orderClassBriefModelList.get(position);
            Intent intent = new Intent(this, OrderDetailActivity.class);
            intent.putExtra("viewType", model.classType);
            startActivity(intent);
        } else {
            CheckBox checkBox = view.findViewById(R.id.item_checkbox);
            checkBox.setChecked(!checkBox.isChecked());
            if (!checkBox.isChecked() && indexOfDeleteItem.contains(position)) {
                for (int i = 0; i < indexOfDeleteItem.size(); i++) {
                    if (indexOfDeleteItem.get(i).equals(new Integer(position))) {
                        Log.d("msg", " indexOfDeleteItem remove " + position);
                        indexOfDeleteItem.remove(i);
                        break;
                    }
                }
            } else if (checkBox.isChecked() && !indexOfDeleteItem.contains(position)) {
                Log.d("msg", " indexOfDeleteItem add " + position);
                indexOfDeleteItem.add(new Integer(position));
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
                        for (int i = 0; i < indexOfDeleteItem.size(); i++) {
                            //防止出现错删的情况
                            orderClassBriefModelList
                                    .remove(indexOfDeleteItem.get(i).intValue() - i);
                        }
                        isSelected = false;
                        adapter.notifyDataSetChanged();
                        indexOfDeleteItem.clear();
                        relativeLayout.setVisibility(View.GONE);
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

    /**
     * Created by Thor on 2018/3/15.
     * <p>
     * 约课列表的适配器
     */

    public class OrderClassListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private List<OrderClassBriefModel> data;

        public OrderClassListAdapter(Context context,
                                     List<OrderClassBriefModel> orderClassBriefModelList) {
            this.layoutInflater = LayoutInflater.from(context);
            this.data = orderClassBriefModelList;
        }

        public void setData(List<OrderClassBriefModel> data) {
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
            OrderClassBriefModel model = data.get(position);
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

            viewHolder.className.setText(model.className);
            viewHolder.orderTime.setText(model.orderTime);
            viewHolder.placeName.setText(model.placeName);

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
