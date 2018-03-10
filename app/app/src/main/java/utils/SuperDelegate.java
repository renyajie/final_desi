package utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Thor on 2018/3/8.
 */

public abstract class SuperDelegate {

    public boolean uiFlag = false;

    public ViewHolderType type = getViewHolderType();

    public abstract ViewHolderType getViewHolderType();

    public abstract int getItemViewType(int position);

    @NonNull
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    public abstract <T extends RecyclerView.ViewHolder> void  onBindViewHolder(T viewHolder);
}
