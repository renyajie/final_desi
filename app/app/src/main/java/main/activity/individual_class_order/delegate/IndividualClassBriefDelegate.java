package main.activity.individual_class_order.delegate;

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

import bean.ClassInfo;
import main.activity.individual_class_order.adapter.IndividualClassBriefAdapter;
import main.activity.individual_class_order.model.IndividualClassBriefModel;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/14.
 */

public class IndividualClassBriefDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ClassInfo> classInfoList;
    private IndividualClassBriefAdapter adapter;
    private boolean showRecommendTitle;

    public IndividualClassBriefDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        showRecommendTitle = false;
    }

    public void setClassInfoList(List<ClassInfo> classInfoList) {
        this.classInfoList = classInfoList;
    }

    public void setShowRecommendTitle(boolean showRecommendTitle) {
        this.showRecommendTitle = showRecommendTitle;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.IndividualClassBrief;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new IndividualClassBriefViewHolder(layoutInflater.inflate(
                R.layout.activity_main_individual_class_order_class_list, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("recycler", "bind IndividualClassBriefDelegate");

        // 防止刷新UI

        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        adapter = new IndividualClassBriefAdapter(context, classInfoList);
        ((IndividualClassBriefViewHolder)viewHolder).listView.setAdapter(adapter);
        ((IndividualClassBriefViewHolder)viewHolder).listView.setOnItemClickListener(adapter);

        if (showRecommendTitle) {
            ((IndividualClassBriefViewHolder)viewHolder).title.setText("-私教推荐-");
        }else {
            ((IndividualClassBriefViewHolder)viewHolder).title.setVisibility(View.GONE);
        }
    }

    public static class IndividualClassBriefViewHolder extends RecyclerView.ViewHolder {

        ListView listView;
        TextView title;

        public IndividualClassBriefViewHolder(View itemView) {
            super(itemView);
            listView = itemView.findViewById(R.id.list_view);
            title = itemView.findViewById(R.id.title);
        }
    }
}
