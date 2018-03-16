package mine.activity.order_class.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renyajie.yuyue.R;

import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/16.
 *
 * 约课详情的最后部分，约课规则
 */

public class OrderLessonRuleDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;

    public OrderLessonRuleDelegate(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.OrderLessonRule;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new OrderLessonRuleViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_order_detail_rule, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("OrderLessonRuleDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
    }

    public static class OrderLessonRuleViewHolder extends RecyclerView.ViewHolder {

        public OrderLessonRuleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
