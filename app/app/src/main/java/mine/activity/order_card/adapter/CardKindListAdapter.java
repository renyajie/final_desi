package mine.activity.order_card.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.List;

import bean.CardKind;
import mine.activity.order_card.PlaceCardDetailActivity;
import mine.activity.order_card.PlaceCardListActivity;

/**
 * Created by Thor on 2018/4/7.
 */

public class CardKindListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CardKind> cardKindList;
    private FinishActivity finishActivity;

    public CardKindListAdapter(Context context,
                               List<CardKind> cardKindList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.cardKindList = cardKindList;
        this.finishActivity = (FinishActivity) context;
    }

    public interface FinishActivity {
        void finishActivity();
    }

    public void setData(List<CardKind> data) {
        this.cardKindList = data;
        notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        return cardKindList.size();
    }

    @Override
    public Object getItem(int position) {
        return cardKindList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardKind model = cardKindList.get(position);
        CardKindListAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new
                    CardKindListAdapter.ViewHolder();

            convertView = layoutInflater.inflate(
                    R.layout.activity_mine_place_card_list_item, parent, false);

            viewHolder.classPic = convertView.findViewById(R.id.class_pic);
            viewHolder.cardName = convertView.findViewById(R.id.card_name);
            viewHolder.capacity = convertView.findViewById(R.id.capacity);
            viewHolder.placeName = convertView.findViewById(R.id.place_name);
            viewHolder.expend = convertView.findViewById(R.id.expend);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CardKindListAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.cardName.setText(model.getCardKName());
        viewHolder.capacity.setText(String.valueOf(model.getCapacity()));
        viewHolder.placeName.setText(model.getpName());
        viewHolder.expend.setText(String.valueOf(model.getExpend()));

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CardKind cardKind = cardKindList.get(position);
        Log.d("get", "card name is " + cardKind.getCardKName());
        Intent intent = new Intent(context, PlaceCardDetailActivity.class);
        intent.putExtra("cardKindId", cardKind.getId());
        context.startActivity(intent);
        finishActivity.finishActivity();
    }

    class ViewHolder {
        public ImageView classPic;
        public TextView cardName, capacity, placeName, expend;
    }
}
