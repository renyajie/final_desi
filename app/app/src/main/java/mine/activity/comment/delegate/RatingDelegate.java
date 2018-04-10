package mine.activity.comment.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.renyajie.yuyue.R;

import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/4/10.
 */

public class RatingDelegate extends SuperDelegate{

    private Context context;
    private LayoutInflater layoutInflater;
    private ChangeRatingValue changeRatingValue;

    public RatingDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public interface ChangeRatingValue {
        void changeRatingValue(float newValue);
    }

    public void setChangeRatingValue(ChangeRatingValue changeRatingValue) {
        this.changeRatingValue = changeRatingValue;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.COMMENT_RATING;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new RatingViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_comment_order_rating, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {

        Log.d("recycler", "bind RatingDelegate");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        ((RatingViewHolder)viewHolder).rating.setRating(0.0f);
        ((RatingViewHolder)viewHolder).rating.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        changeRatingValue.changeRatingValue(rating);
                    }
                }
        );
    }

    public static class RatingViewHolder extends RecyclerView.ViewHolder {

        RatingBar rating;

        public RatingViewHolder(View itemView) {
            super(itemView);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
