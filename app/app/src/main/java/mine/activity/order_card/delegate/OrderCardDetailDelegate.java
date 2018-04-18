package mine.activity.order_card.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.renyajie.yuyue.R;

import bean.CardInfo;
import bean.ClassOrder;
import utils.AppConstant;
import utils.MyApplication;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/16.
 *
 * 会员卡信息
 */

public class OrderCardDetailDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private CardInfo cardInfo;
    private ImageLoader imageLoader;

    public OrderCardDetailDelegate(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        imageLoader = MyApplication.getImageLoader();
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.OrderCardDetail;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new OrderCardDetailViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_order_card_detail_card, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("OrderCardDetailDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI

        ((OrderCardDetailViewHolder)viewHolder).classPic
                .setDefaultImageResId(R.mipmap.ic_launcher);
        ((OrderCardDetailViewHolder)viewHolder).classPic
                .setErrorImageResId(R.mipmap.ic_launcher);
        ((OrderCardDetailViewHolder)viewHolder).classPic
                .setImageUrl(AppConstant.URL + cardInfo.getPicUrl(), imageLoader);

        ((OrderCardDetailViewHolder)viewHolder).placeName
                .setText(cardInfo.getsName());
        ((OrderCardDetailViewHolder)viewHolder).cardName
                .setText(cardInfo.getCardKName());
        ((OrderCardDetailViewHolder)viewHolder).allowance
                .setText(String.valueOf(cardInfo.getAllowance()));
    }

    public static class OrderCardDetailViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView classPic;
        TextView placeName, cardName, allowance;

        public OrderCardDetailViewHolder(View itemView) {
            super(itemView);
            classPic = itemView.findViewById(R.id.class_pic);
            placeName = itemView.findViewById(R.id.place_name);
            cardName = itemView.findViewById(R.id.card_name);
            allowance = itemView.findViewById(R.id.allowance);
        }
    }
}
