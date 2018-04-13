package main.activity.class_comment.adapter;

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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.List;

import bean.ClassInfo;
import bean.Score;
import main.activity.people_order_confirm.PeopleOrderConfirmActivity;
import utils.AppConstant;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/12.
 *
 * 团课预约界面的课程列表的适配器
 */

public class ClassCommentAdapter extends BaseAdapter
        implements AdapterView.OnItemClickListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Score> data;

    public ClassCommentAdapter(Context context, List<Score> data) {
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
        Score score = data.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(
                    R.layout.activity_main_class_comment_list_item, null);

            viewHolder.username = convertView.findViewById(R.id.username);
            viewHolder.scoreTime = convertView.findViewById(R.id.score_time);
            viewHolder.comment = convertView.findViewById(R.id.comment);
            viewHolder.score = convertView.findViewById(R.id.score);
            viewHolder.icon = convertView.findViewById(R.id.icon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.username.setText(score.getuName());
        viewHolder.scoreTime.setText(UtilsMethod.getStringFromDateForScore(score.getScoreTime()));
        viewHolder.comment.setText(score.getComment());
        viewHolder.score.setRating(score.getScore());

        //根据用户的性别设置邮箱
        int imageResourceId = score.getGender().equals("男") ?
                R.mipmap.boy : R.mipmap.girl;
        viewHolder.icon.setImageResource(imageResourceId);

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Score score = data.get(position);
        Log.v("get", "课程名:" + score.getClassKName());
        Intent intent = new Intent(context, PeopleOrderConfirmActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("placeId", score.getpId());
        bundle.putInt("classId", score.getClaId());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static class ViewHolder {
        ImageView icon;
        TextView username, scoreTime, comment;
        RatingBar score;
    }
}
