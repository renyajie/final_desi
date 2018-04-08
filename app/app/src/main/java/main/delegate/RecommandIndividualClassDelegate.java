package main.delegate;

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
import main.adapter.RecommandClassAdapter;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/12.
 *
 * 推荐私教课程
 */

public class RecommandIndividualClassDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ClassKind> classKindList;
    private RecommandClassAdapter adapter;
    private String title;

    public RecommandIndividualClassDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClassKindList(List<ClassKind> classKindList) {
        this.classKindList = classKindList;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.RecommandIndividualClass;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new RecommandIndivudualClassViewHolder(layoutInflater.inflate(
                R.layout.activity_main_recommand_list, parent, false));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("recycler", "bind RecommandIndividualClassDelegate");

        // 防止刷新UI

        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        adapter = new RecommandClassAdapter(context, classKindList);
        ((RecommandIndivudualClassViewHolder)viewHolder).listView.setAdapter(adapter);
        ((RecommandIndivudualClassViewHolder)viewHolder).listView.setOnItemClickListener(adapter);
        ((RecommandIndivudualClassViewHolder)viewHolder).title.setText(title);
    }

    public static class RecommandIndivudualClassViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ListView listView;

        public RecommandIndivudualClassViewHolder(View itemView) {
            super(itemView);
            listView = itemView.findViewById(R.id.list_view);
            title = itemView.findViewById(R.id.title);
        }
    }
}
