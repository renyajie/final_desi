package main.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.renyajie.yuyue.R;

import java.util.List;

import utils.ViewHolderType;
import main.adapter.PossibleLikeAdapter;
import main.model.PossibleLikeModel;
import utils.SuperDelegate;

/**
 * Created by Thor on 2018/3/8.
 *
 * 处理 Main页面的猜你喜欢
 */

public class PossibleLikeDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<PossibleLikeModel> possibleLikeModelList;
    private PossibleLikeAdapter adapter;

    public PossibleLikeDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setPossibleLikeModelList(List<PossibleLikeModel> possibleLikeModelList) {
        this.possibleLikeModelList = possibleLikeModelList;
    }

    /**
     * 这一大类是属于哪一类的View，在ViewHolderType中有定义
     * @return
     */
    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.PossibleLike;
    }

    /**
     * 其中的某个位置的Item是这一大类中哪种Item，还需要继续向下分
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PossibleLikeViewHolder(layoutInflater.inflate(
                R.layout.fragment_main_possible_like, parent, false));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("PossibleLikeDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI

        adapter = new PossibleLikeAdapter(context, possibleLikeModelList);
        ((PossibleLikeViewHolder)viewHolder).listView.setAdapter(adapter);
        //设置监听事件
        ((PossibleLikeViewHolder)viewHolder).listView.setOnItemClickListener(adapter);
    }


    static class PossibleLikeViewHolder extends RecyclerView.ViewHolder {

        public ListView listView;

        public PossibleLikeViewHolder(View itemView) {
            super(itemView);
            listView = itemView.findViewById(R.id.list_view);
        }
    }
}
