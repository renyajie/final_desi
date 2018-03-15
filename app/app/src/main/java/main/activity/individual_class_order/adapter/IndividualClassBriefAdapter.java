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

import main.activity.individual_class_order.model.IndividualClassBriefModel;
import main.activity.individual_order_confirm.IndividualOrderConfirmActivity;
import main.activity.people_class_order.model.PeopleClassBriefModel;
import main.activity.people_order_confirm.PeopleOrderConfirmActivity;
import utils.AppConstant;

/**
 * Created by Thor on 2018/3/12.
 *
 * 团课预约界面的课程列表的适配器
 */

public class IndividualClassBriefAdapter extends BaseAdapter
        implements AdapterView.OnItemClickListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<IndividualClassBriefModel> data;

    public IndividualClassBriefAdapter(Context context, List<IndividualClassBriefModel> data) {
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
        IndividualClassBriefModel model = data.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = layoutInflater.inflate(
                    R.layout.activity_main_individual_class_order_class_list_item, parent, false);

            viewHolder.teacherName = convertView.findViewById(R.id.teacher_name);
            viewHolder.time = convertView.findViewById(R.id.time);
            viewHolder.number = convertView.findViewById(R.id.number);
            viewHolder.button = convertView.findViewById(R.id.button);

            viewHolder.teacherName.setText(model.teacherName);
            viewHolder.time.setText(model.time);
            viewHolder.number.setText(String.valueOf(model.number));

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            viewHolder.teacherName.setText(model.teacherName);
            viewHolder.time.setText(model.time);
            viewHolder.number.setText(String.valueOf(model.number));
        }

        return convertView;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        IndividualClassBriefModel model = data.get(position);
        Log.v("msg", "教师:" + model.teacherName);
        Intent intent = new Intent(context, IndividualOrderConfirmActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("placeId", model.placeId);
        bundle.putInt("classId", model.classId);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static class ViewHolder {
        TextView teacherName, time, number;
        Button button;
    }
}
