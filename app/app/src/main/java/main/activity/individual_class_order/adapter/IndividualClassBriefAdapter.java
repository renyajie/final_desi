package main.activity.individual_class_order.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.List;

import bean.ClassInfo;
import main.activity.class_comment.ClassCommentActivity;
import main.activity.individual_class_order.model.IndividualClassBriefModel;
import main.activity.individual_order_confirm.IndividualOrderConfirmActivity;
import main.activity.people_class_order.model.PeopleClassBriefModel;
import main.activity.people_order_confirm.PeopleOrderConfirmActivity;
import mine.activity.order_card.adapter.CardKindListAdapter;
import utils.AppConstant;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/12.
 *
 * 团课预约界面的课程列表的适配器
 */

public class IndividualClassBriefAdapter extends BaseAdapter
        implements AdapterView.OnItemClickListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ClassInfo> data;
    private CardKindListAdapter.FinishActivity finishActivity;

    public void setFinishActivity(CardKindListAdapter.FinishActivity finishActivity) {
        this.finishActivity = finishActivity;
    }

    public IndividualClassBriefAdapter(Context context, List<ClassInfo> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
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
        ClassInfo model = data.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = layoutInflater.inflate(
                    R.layout.activity_main_individual_class_order_class_list_item, parent, false);

            viewHolder.teacherName = convertView.findViewById(R.id.teacher_name);
            viewHolder.time = convertView.findViewById(R.id.time);
            viewHolder.orderNumber = convertView.findViewById(R.id.order_number);
            viewHolder.button = convertView.findViewById(R.id.button);

            viewHolder.teacherName.setText(model.getTeaName());
            viewHolder.time.setText(UtilsMethod.getStringFromDate(model.getStaTime()));
            viewHolder.orderNumber.setText(String.valueOf(model.getOrderNum()));

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            viewHolder.teacherName.setText(model.getTeaName());
            viewHolder.time.setText(UtilsMethod.getStringFromDate(model.getStaTime()));
            viewHolder.orderNumber.setText(String.valueOf(model.getOrderNum()));
        }

        return convertView;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ClassInfo model = data.get(position);
        Log.d("get", "教师:" + model.getTeaName());
        Intent intent = new Intent(context, ClassCommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("classId", model.getId());
        bundle.putInt("classKId", model.getClaKId());
        bundle.putString("property", model.getProperty());
        intent.putExtras(bundle);
        context.startActivity(intent);
        finishActivity.finishActivity();
    }

    public static class ViewHolder {
        TextView teacherName, time, orderNumber;
        Button button;
    }
}
