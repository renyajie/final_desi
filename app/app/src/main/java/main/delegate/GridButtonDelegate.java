package main.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.renyajie.yuyue.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.helper.MyItemClickListener;
import utils.ViewHolderType;
import main.model.GridButtonModel;
import utils.SuperDelegate;

/**
 * Created by Thor on 2018/3/8.
 *
 * 处理 Main页面的网格按钮
 */

public class GridButtonDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<GridButtonModel> gridButtonModelList;
    List<Map<String, Object>> buttonData;

    public GridButtonDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setGridButtonModelList(List<GridButtonModel> gridButtonModelList) {
        this.gridButtonModelList = gridButtonModelList;
    }

    //返回View的类型
    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.GridButton;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new GridButtonViewHolder(layoutInflater.inflate(
                R.layout.fragment_main_grid_button, parent, false));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("GridButtonDeledate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI

        buttonData = new ArrayList<>();
        for (GridButtonModel model : gridButtonModelList) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", model.picResourceId);
            map.put("text", model.title);
            map.put("RequestType", model.requestType);
            buttonData.add(map);
        }
        String[] from = {"image", "text"};
        int[] to = {R.id.grid_item_image, R.id.grid_item_text };
        SimpleAdapter buttonAdapter = new SimpleAdapter(
                context, buttonData, R.layout.fragment_main_grid_button_item, from, to);
        ((GridButtonViewHolder)viewHolder).gridView.setAdapter(buttonAdapter);
        ((GridButtonViewHolder)viewHolder).gridView
                .setOnItemClickListener(new MyItemClickListener(context, gridButtonModelList));
    }


    static class GridButtonViewHolder extends RecyclerView.ViewHolder {

        public GridView gridView;

        public GridButtonViewHolder(View itemView) {
            super(itemView);
            gridView = itemView.findViewById(R.id.gridview);
        }
    }
}
