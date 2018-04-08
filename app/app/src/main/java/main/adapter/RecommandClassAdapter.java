package main.adapter;

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
import bean.ClassKind;
import main.activity.people_order_confirm.PeopleOrderConfirmActivity;
import main.activity.recommand_class.RecommandClassActivity;
import utils.AppConstant;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/12.
 *
 * 团课预约界面的课程列表的适配器
 */

public class RecommandClassAdapter extends BaseAdapter
        implements AdapterView.OnItemClickListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ClassKind> data;

    public RecommandClassAdapter(Context context, List<ClassKind> data) {
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
        ClassKind model = data.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(
                    R.layout.activity_main_recommand_list_item, null);

            viewHolder.placeName = convertView.findViewById(R.id.place_name);
            viewHolder.className = convertView.findViewById(R.id.class_name);
            viewHolder.intro = convertView.findViewById(R.id.intro);
            viewHolder.difficulty = convertView.findViewById(R.id.difficulty);

            viewHolder.placeName.setText(model.getpName());
            viewHolder.className.setText(model.getClaKName());
            viewHolder.intro.setText(model.getIntro());

            viewHolder.difficulty.setRating(Float.valueOf(model.getDifficulty()));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            viewHolder.placeName.setText(model.getpName());
            viewHolder.className.setText(model.getClaKName());
            viewHolder.intro.setText(model.getIntro());

            viewHolder.difficulty.setRating(Float.valueOf(model.getDifficulty()));
        }

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ClassKind model = data.get(position);
        Intent intent = new Intent(context, RecommandClassActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("placeName", model.getpName());
        bundle.putInt("classKindId", model.getId());
        bundle.putString("classKName", model.getClaKName());
        bundle.putInt("isPeopleClass", model.getProperty().equals("s") ? 0 : 1);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static class ViewHolder {
        TextView placeName, className, intro;
        RatingBar difficulty;
    }
}
