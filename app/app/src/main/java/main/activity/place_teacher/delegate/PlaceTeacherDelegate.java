package main.activity.place_teacher.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.List;

import bean.ClassKind;
import bean.Teacher;
import main.activity.place_teacher.adapter.TeacherAdapter;
import main.adapter.RecommandClassAdapter;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/4/14.
 *
 * 场馆教师列表
 */

public class PlaceTeacherDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Teacher> teacherList;
    private TeacherAdapter adapter;

    public PlaceTeacherDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.Teacher;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PlaceTeacherViewHolder(layoutInflater.inflate(
                R.layout.activity_main_place_teacher_list, parent, false));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("recycler", "bind PlaceTeacherViewHolder");

        // 防止刷新UI

        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        adapter = new TeacherAdapter(context, teacherList);
        ((PlaceTeacherViewHolder)viewHolder).listView.setAdapter(adapter);
        ((PlaceTeacherViewHolder)viewHolder).listView.setOnItemClickListener(adapter);
    }

    public static class PlaceTeacherViewHolder extends RecyclerView.ViewHolder {

        ListView listView;

        public PlaceTeacherViewHolder(View itemView) {
            super(itemView);
            listView = itemView.findViewById(R.id.list_view);
        }
    }
}
