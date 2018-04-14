package main.activity.place_teacher.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.List;

import bean.ClassKind;
import bean.Teacher;
import main.activity.recommand_class.RecommandClassActivity;

/**
 * Created by Thor on 2018/4/14.
 *
 * 场馆教师列表的适配器
 */

public class TeacherAdapter extends BaseAdapter
        implements AdapterView.OnItemClickListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Teacher> data;

    public TeacherAdapter(Context context, List<Teacher> data) {
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
        Teacher model = data.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(
                    R.layout.activity_main_place_teacher_list_item, null);

            viewHolder.icon = convertView.findViewById(R.id.icon);
            viewHolder.teacherName = convertView.findViewById(R.id.teacher_name);
            viewHolder.intro = convertView.findViewById(R.id.intro);
            viewHolder.age = convertView.findViewById(R.id.age);
            viewHolder.phone = convertView.findViewById(R.id.phone);
            viewHolder.gender = convertView.findViewById(R.id.gender);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.icon.setImageResource(model.getGender().equals("男") ? R.mipmap.male : R.mipmap.female);
        viewHolder.teacherName.setText(model.getTeaName());
        viewHolder.intro.setText(model.getIntro());
        viewHolder.age.setText(String.valueOf(model.getAge()));
        viewHolder.phone.setText(model.getPhone());
        viewHolder.gender.setText(model.getGender());

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Teacher model = data.get(position);
    }

    public static class ViewHolder {
        ImageView icon;
        TextView teacherName, phone, age, gender, intro;
    }
}
