package mine.activity.order_card.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/16.
 *
 * 购买会员卡规则
 */

public class OrderCardRuleDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;

    public OrderCardRuleDelegate(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.OrderCardRule;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new OrderCardRuleViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_order_detail_rule, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("OrderCardRuleDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        ((OrderCardRuleViewHolder)viewHolder).title.setText("购卡须知");
        ((OrderCardRuleViewHolder)viewHolder).content.setText(R.string.card_rule);
    }

    public static class OrderCardRuleViewHolder extends RecyclerView.ViewHolder {

        TextView title, content;

        public OrderCardRuleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}
