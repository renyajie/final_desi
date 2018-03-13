package main.activity.people_class_order.helper;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import main.activity.people_class_order.model.PlaceModel;

/**
 * Created by Thor on 2018/3/12.
 *
 * 处理选择课程的下拉框的监听器
 */

public class MySpinnerSelectListener implements Spinner.OnItemSelectedListener {

    private Context context;
    private List<PlaceModel> dateModelList;

    public MySpinnerSelectListener(Context context, List<PlaceModel> dateModelList) {
        this.context = context;
        this.dateModelList = dateModelList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        PlaceModel model = dateModelList.get(position);
        Toast.makeText(context, model.placeName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
