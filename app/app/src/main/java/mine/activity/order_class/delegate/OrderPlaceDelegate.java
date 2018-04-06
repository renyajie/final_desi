package mine.activity.order_class.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import bean.Place;
import mine.activity.order_class.model.PlaceModel;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/16.
 *
 * 约课详情的地址部分
 */

public class OrderPlaceDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private Place place;

    public OrderPlaceDelegate(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.OrderPlace;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new OrderPlaceViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_order_detail_place, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("OrderPlaceDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        ((OrderPlaceViewHolder)viewHolder).placeName.setText(place.getsName());
        ((OrderPlaceViewHolder)viewHolder).address.setText(place.getAddress());
    }

    public static class OrderPlaceViewHolder extends RecyclerView.ViewHolder {

        public TextView placeName, address;

        public OrderPlaceViewHolder(View itemView) {
            super(itemView);

            placeName = itemView.findViewById(R.id.place_name);
            address = itemView.findViewById(R.id.address);
        }
    }
}
