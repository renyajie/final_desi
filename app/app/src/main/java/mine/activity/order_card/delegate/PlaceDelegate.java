package mine.activity.order_card.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.renyajie.yuyue.R;

import java.util.ArrayList;
import java.util.List;

import main.activity.people_class_order.model.PlaceModel;
import utils.SuperDelegate;
import utils.UtilsMethod;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/12.
 *
 * 团课预约中选择场馆和日期的部分
 */

public class PlaceDelegate extends SuperDelegate
        implements AdapterView.OnItemSelectedListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<PlaceModel> placeModelList;
    private ArrayAdapter<String> arrayAdapter;
    private PlaceDelegateViewHolder placeViewHolder;
    private ChangePlace changePlace;

    private Integer amount = 0;
    private Integer placeId = 1;

    public interface ChangePlace {
        void changePlace(int placeId);
    }

    public PlaceDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        changePlace = (ChangePlace) context;
    }

    public void setPlaceModelList(List<PlaceModel> placeModelList) {
        this.placeModelList = placeModelList;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.PlaceDetail;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PlaceDelegateViewHolder(
                layoutInflater.inflate(
                        R.layout.activity_mine_buy_card_place,
                        parent, false));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("recycler", "bind PlaceAndDateDelegate");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        placeViewHolder = (PlaceDelegateViewHolder)viewHolder;

        //设置场馆下拉列表
        List<String> placeNameList = new ArrayList<>();
        for(PlaceModel model : placeModelList) {
            placeNameList.add(model.placeName);
        }
        arrayAdapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, placeNameList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeViewHolder.placeList.setAdapter(arrayAdapter);
        placeViewHolder.placeList.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        PlaceModel model = placeModelList.get(position);
        this.placeId = model.placeId;
        changePlace.changePlace(placeId);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public static class PlaceDelegateViewHolder extends RecyclerView.ViewHolder {

        Spinner placeList;

        public PlaceDelegateViewHolder(View itemView) {
            super(itemView);
            placeList = itemView.findViewById(R.id.place_list);
        }
    }
}

