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

import bean.ClassOrder;
import mine.activity.order_class.model.OrderDetailModel;
import mine.activity.order_class.model.PlaceModel;
import utils.SuperDelegate;
import utils.UtilsMethod;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/16.
 *
 * 约课详情的订单部分
 */

public class OrderDetailDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private ClassOrder classOrder;

    public OrderDetailDelegate(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setClassOrder(ClassOrder classOrder) {
        this.classOrder = classOrder;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.OrderDetail;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new OrderDetailViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_order_detail_pay, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("OrderDetailDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        ((OrderDetailViewHolder)viewHolder).orderId
                .setText(String.valueOf(classOrder.getId()));
        ((OrderDetailViewHolder)viewHolder).orderTime
                .setText(UtilsMethod.getStringFromDateForCheck(classOrder.getOrdTime()));
        ((OrderDetailViewHolder)viewHolder).phone
                .setText(UtilsMethod.getUserPhone());
    }

    public static class OrderDetailViewHolder extends RecyclerView.ViewHolder {

        public TextView orderId, orderTime, phone;

        public OrderDetailViewHolder(View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.order_id);
            orderTime = itemView.findViewById(R.id.order_time);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
