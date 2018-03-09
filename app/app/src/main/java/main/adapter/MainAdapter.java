package main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import main.AdapterViewHolderManager;
import main.ViewHolderType;
import main.delegate.SuperDelegate;
import main.model.GlideImageModel;

/**
 * Created by Thor on 2018/3/8.
 *
 * 将所有不同类型的View和主页面中的Recycler View进行斜街的总适配器
 */

public class MainAdapter extends RecyclerView.Adapter {

    //各类View的管理器
    private AdapterViewHolderManager manager;

    public MainAdapter(List<SuperDelegate> delegates) {
        manager = new AdapterViewHolderManager();
        manager.setDelegates(delegates);
    }

    //刷新某个Delegate
    public void updatePositionDelegate(int position) {
        manager.setItemRenderEnable(position);
        notifyItemChanged(position);
    }

    //刷新某一范围内Delegate
    public void updateRangeDelegates(int position, int size) {
        manager.setRangeRenderEnable(position, size);
        notifyItemRangeChanged(position, size);
    }

    //刷新全部Delegate
    public void updateTotalDelegates() {
        manager.setRangeRenderEnable(0, manager.getItemCount());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return manager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return manager.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        manager.onBindViewHolder(position, holder);
    }

    @Override
    public int getItemCount() {
        return manager.getItemCount();
    }
}
