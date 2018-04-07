package mine.activity.order_card.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.List;

import bean.CardKind;
import mine.activity.order_card.adapter.CardKindListAdapter;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/12.
 *
 * 购买会员卡的会员卡种类列表
 */

public class CardKindDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CardKind> cardKindList;
    private CardKindListAdapter adapter;
    private AdapterView.OnItemClickListener onItemClickListener;

    //判断用户是否已经拥有该会员卡
    public interface CheckCardIsExitsOrNot {
        void checkCardIsExitsOrNot();
    }

    public CardKindDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        onItemClickListener = (AdapterView.OnItemClickListener) context;
    }

    public void setCardKindList(List<CardKind> cardKindList) {
        this.cardKindList = cardKindList;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.CardKind;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new CardKindListViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_buy_card_list, parent, false));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("recycler", "bind CardKindDelegate");

        // 防止刷新UI

        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        adapter = new CardKindListAdapter(context, cardKindList);
        ((CardKindListViewHolder)viewHolder).listView.setAdapter(adapter);
        ((CardKindListViewHolder)viewHolder).listView.setOnItemClickListener(onItemClickListener);

        if(cardKindList.size() == 0) {
            ((CardKindListViewHolder)viewHolder).noCardLayout.setVisibility(View.VISIBLE);
            ((CardKindListViewHolder)viewHolder).listView.setVisibility(View.GONE);
        }
        else {
            ((CardKindListViewHolder)viewHolder).noCardLayout.setVisibility(View.GONE);
            ((CardKindListViewHolder)viewHolder).listView.setVisibility(View.VISIBLE);
        }
    }

    public static class CardKindListViewHolder extends RecyclerView.ViewHolder {

        ListView listView;
        LinearLayout noCardLayout;

        public CardKindListViewHolder(View itemView) {
            super(itemView);
            listView = itemView.findViewById(R.id.list_view);
            noCardLayout = itemView.findViewById(R.id.no_card_layout);
        }
    }
}
