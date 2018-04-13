package main.activity.class_comment.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.List;

import bean.ClassInfo;
import bean.Score;
import main.activity.class_comment.adapter.ClassCommentAdapter;
import main.activity.people_class_order.adapter.PeopleClassBriefAdapter;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/12.
 *
 * 课程评价部分，对应某一种课程的用户评价
 */

public class ClassCommentDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Score> scoreList;
    private ClassCommentAdapter adapter;

    public ClassCommentDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.ClassComment;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ClassCommentViewHolder(layoutInflater.inflate(
                R.layout.activity_main_class_comment_list, parent, false));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("recycler", "bind ClassCommentDelegate");

        // 防止刷新UI

        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        adapter = new ClassCommentAdapter(context, scoreList);
        ((ClassCommentViewHolder)viewHolder).listView.setAdapter(adapter);
        //((ClassCommentViewHolder)viewHolder).listView.setOnItemClickListener(adapter);

        if(scoreList.size() == 0) {
            ((ClassCommentViewHolder)viewHolder).noCommentLayout.setVisibility(View.VISIBLE);
            ((ClassCommentViewHolder)viewHolder).listView.setVisibility(View.GONE);
        }
        else {
            ((ClassCommentViewHolder)viewHolder).noCommentLayout.setVisibility(View.GONE);
            ((ClassCommentViewHolder)viewHolder).listView.setVisibility(View.VISIBLE);
        }
    }

    public static class ClassCommentViewHolder extends RecyclerView.ViewHolder {

        ListView listView;
        LinearLayout noCommentLayout;

        public ClassCommentViewHolder(View itemView) {
            super(itemView);
            listView = itemView.findViewById(R.id.list_view);
            noCommentLayout = itemView.findViewById(R.id.no_comment_layout);
        }
    }
}
