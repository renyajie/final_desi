package main.activity.people_class_order.adapter;

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

import java.text.ParseException;
import java.util.List;

import bean.ClassInfo;
import main.activity.people_order_confirm.PeopleOrderConfirmActivity;
import main.activity.people_class_order.model.PeopleClassBriefModel;
import utils.AppConstant;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/12.
 *
 * 团课预约界面的课程列表的适配器
 */

public class PeopleClassBriefAdapter extends BaseAdapter
        implements AdapterView.OnItemClickListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ClassInfo> data;

    public PeopleClassBriefAdapter(Context context, List<ClassInfo> data) {
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
                    R.layout.activity_main_people_class_order_class_list_item, null);

            viewHolder.startTime = convertView.findViewById(R.id.start_time);
            viewHolder.className = convertView.findViewById(R.id.class_name);
            viewHolder.teacherName = convertView.findViewById(R.id.teacher_name);
            //viewHolder.classroom = convertView.findViewById(R.id.classroom);
            viewHolder.statusText = convertView.findViewById(R.id.status_text);
            viewHolder.difficulty = convertView.findViewById(R.id.difficulty);
            viewHolder.statusButton = convertView.findViewById(R.id.status_button);
            viewHolder.allowance = convertView.findViewById(R.id.allowance);
            viewHolder.allowanceHelp = convertView.findViewById(R.id.allowance_help);

            viewHolder.startTime.setText(UtilsMethod.getStringFromDate(model.getStaTime()));
            viewHolder.className.setText(model.getClaKName());
            viewHolder.teacherName.setText(model.getTeaName());
            //viewHolder.classroom.setText(model.classroom);

            // 未约满时还需显示余量有多少，这里需要将status，涉及到Text和Button的转换
            if(model.getStatus() != AppConstant.PEOPLE_ORDER_CAN_ORDER) {
                viewHolder.allowanceHelp.setVisibility(View.INVISIBLE);
                viewHolder.allowance.setVisibility(View.INVISIBLE);
                viewHolder.statusButton.setVisibility(View.INVISIBLE);
                viewHolder.statusText.setText(convertStatusToString(model.getStatus()));
            } else {
                viewHolder.allowance.setText(String.valueOf(model.getAllowance()));
                viewHolder.statusText.setVisibility(View.INVISIBLE);
            }

            viewHolder.difficulty.setRating(Float.valueOf(model.getDifficulty()));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.startTime.setText(UtilsMethod.getStringFromDate(model.getStaTime()));
            viewHolder.className.setText(model.getClaKName());
            viewHolder.teacherName.setText(model.getTeaName());
            //viewHolder.classroom.setText(model.classroom);

            // 未约满时还需显示余量有多少，这里需要将status，涉及到Text和Button的转换
            if(model.getStatus() != AppConstant.PEOPLE_ORDER_CAN_ORDER) {
                viewHolder.allowanceHelp.setVisibility(View.INVISIBLE);
                viewHolder.allowance.setVisibility(View.INVISIBLE);
                viewHolder.statusButton.setVisibility(View.INVISIBLE);
                viewHolder.statusText.setText(convertStatusToString(model.getStatus()));
            } else {
                viewHolder.allowance.setText(String.valueOf(model.getAllowance()));
                viewHolder.statusText.setVisibility(View.INVISIBLE);
            }

            viewHolder.difficulty.setRating(Float.valueOf(model.getDifficulty()));
        }

        return convertView;
    }

    //将数字状态转化为字符串
    private String convertStatusToString(Integer statusCode){
        switch (statusCode) {
            case 1:
                return "已开课";
            case 2:
                return "预约";
            case 3:
                return "约满";
            default:
                return "错误";
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ClassInfo model = data.get(position);
        Log.v("msg", "课程名:" + model.getClaKName());
        Intent intent = new Intent(context, PeopleOrderConfirmActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("placeId", model.getpId());
        bundle.putInt("classId", model.getClaKId());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static class ViewHolder {
        TextView startTime, className, teacherName, classroom, statusText, allowance, allowanceHelp;
        RatingBar difficulty;
        Button statusButton;
    }
}
