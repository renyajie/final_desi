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
import bean.CardKind;
import utils.AppConstant;
import utils.MyApplication;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/16.
 *
 * 会员卡种类信息
 */

public class PlaceCardDetailDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private CardKind cardKind;
    private ImageLoader imageLoader;

    public PlaceCardDetailDelegate(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        imageLoader = MyApplication.getImageLoader();
    }

    public void setCardKind(CardKind cardKind) {
        this.cardKind = cardKind;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.PlaceCardDetail;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PlaceCardDetailViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_place_card_detail_card, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("PlaceCardDetailDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        ((PlaceCardDetailViewHolder)viewHolder).classPic
                .setDefaultImageResId(R.mipmap.ic_launcher);
        ((PlaceCardDetailViewHolder)viewHolder).classPic
                .setErrorImageResId(R.mipmap.ic_launcher);
        ((PlaceCardDetailViewHolder)viewHolder).classPic
                .setImageUrl(AppConstant.URL + cardKind.getPicUrl(), imageLoader);

        //开始刷新UI
        ((PlaceCardDetailViewHolder)viewHolder).placeName
                .setText(cardKind.getpName());
        ((PlaceCardDetailViewHolder)viewHolder).cardName
                .setText(cardKind.getCardKName());
        ((PlaceCardDetailViewHolder)viewHolder).expend
                .setText(String.valueOf(cardKind.getExpend()));
        ((PlaceCardDetailViewHolder)viewHolder).capacity
                .setText(String.valueOf(cardKind.getCapacity()));
    }

    public static class PlaceCardDetailViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView classPic;
        TextView placeName, cardName, expend, capacity;

        public PlaceCardDetailViewHolder(View itemView) {
            super(itemView);
            classPic = itemView.findViewById(R.id.class_pic);
            placeName = itemView.findViewById(R.id.place_name);
            cardName = itemView.findViewById(R.id.card_name);
            expend = itemView.findViewById(R.id.expend);
            capacity = itemView.findViewById(R.id.capacity);
        }
    }
}
