package main.activity.people_class_order.delegate;

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
import android.widget.Toast;

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

public class PlaceAndDateDelegate extends SuperDelegate
        implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<PlaceModel> placeModelList;
    private ArrayAdapter<String> arrayAdapter;
    private PlaceAndDateDelegateViewHolder placeViewHolder;
    private ChangePlaceOrDate changePlaceOrDate;

    public interface ChangePlaceOrDate {
        void changePlace(int placeId);
        void changeDate(int amount);
    }

    public PlaceAndDateDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        changePlaceOrDate = (ChangePlaceOrDate) context;
    }

    public void setPlaceModelList(List<PlaceModel> placeModelList) {
        this.placeModelList = placeModelList;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.ClassAndDate;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PlaceAndDateDelegateViewHolder(
                layoutInflater.inflate(
                        R.layout.activity_main_people_class_order_place_and_date,
                        parent, false));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("PlaceAndDateDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        placeViewHolder = (PlaceAndDateDelegateViewHolder)viewHolder;

        //设置场馆下拉列表
        List<String> classNameList = new ArrayList<>();
        for(PlaceModel model : placeModelList) {
            classNameList.add(model.placeName);
        }
        arrayAdapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, classNameList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeViewHolder.classList.setAdapter(arrayAdapter);
        placeViewHolder.classList.setOnItemSelectedListener(this);

        //设置日期，默认选中今天
        placeViewHolder.radioGroup.setOnCheckedChangeListener(this);
        placeViewHolder.today.setText(UtilsMethod.theNextNDayWithText(0));
        placeViewHolder.tomorrow.setText(UtilsMethod.theNextNDayWithText(1));
        placeViewHolder.theDayAfterTomorrow.setText(UtilsMethod.theNextNDayWithText(2));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        PlaceModel model = placeModelList.get(position);
        changePlaceOrDate.changePlace(model.placeId);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.today:
                changePlaceOrDate.changeDate(0);
                break;
            case R.id.tomorrow:
                changePlaceOrDate.changeDate(1);
                break;
            case R.id.the_day_after_tomorrow:
                changePlaceOrDate.changeDate(2);
                break;
            default:
                break;
        }
    }


    public static class PlaceAndDateDelegateViewHolder extends RecyclerView.ViewHolder {

        Spinner classList;
        RadioGroup radioGroup;
        RadioButton today, tomorrow, theDayAfterTomorrow;

        public PlaceAndDateDelegateViewHolder(View itemView) {
            super(itemView);
            classList = itemView.findViewById(R.id.classList);
            radioGroup = itemView.findViewById(R.id.radio_group);
            today = itemView.findViewById(R.id.today);
            tomorrow = itemView.findViewById(R.id.tomorrow);
            theDayAfterTomorrow = itemView.findViewById(R.id.the_day_after_tomorrow);
        }
    }
}

