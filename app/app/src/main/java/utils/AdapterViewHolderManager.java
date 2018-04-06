package utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

import utils.SuperDelegate;

/**
 * Created by Thor on 2018/3/8.
 *
 * 管理所有 Main页面中的布局
 */

public class AdapterViewHolderManager {

    //所有类型的View代理
    private List<SuperDelegate> delegates;

    public void setDelegates(List<SuperDelegate> delegates) {
        this.delegates = delegates;
    }

    //获取对应位置的View的类型
    public int getItemViewType(int position) {
        Log.d("recycler", "manager get delegate item view type");
        if (delegates != null) {
            SuperDelegate delegate = delegates.get(position);
            if (delegate != null) {
                int type = delegate.getItemViewType(position);
                Log.d("recycler", "item view type is " + type);
                return type;
            } else {
                Log.d("recycler", "item view type is null");
                return -1;
            }
        } else {
            Log.d("recycler", "item view type is null");
            return -1;
        }
    }

    //刷新某一范围的View
    public void setRangeRenderEnable(int position, int size){
        for(int n = position; n < size; n++){
            delegates.get(n).uiFlag = true;
        }
    }

    //刷新某个View
    public void setItemRenderEnable(int position) {
        delegates.get(position).uiFlag = true;
    }

    //创建某个View类型
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        Log.d("recycler", "manager create view");
        SuperDelegate delegate = getDelegateFromViewType(ViewType);
        if (delegate != null) {
            Log.d("recycler", "manager create view, type is " + ViewType);
            return delegate.onCreateViewHolder(parent);
        }
        Log.d("recycler", "manager create view, but it is null");
        return null;
    }

    public void onBindViewHolder(int position, @NonNull RecyclerView.ViewHolder viewHolder) {
        Log.d("recycler", "manager bind view");
        SuperDelegate delegate = getDelegateFromViewType(position);
        if (delegate != null) {
            Log.d("recycler", "manager bind view, view is not null");
            delegate.onBindViewHolder(viewHolder);
        }
    }

    public SuperDelegate getDelegateFromViewType(int viewType) {
        return delegates.get(viewType);
    }

    public int getItemCount() {
        int count = delegates.size();
        Log.d("recycler", "manager get item count is " + count);
        return count;
    }
}
