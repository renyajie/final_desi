package main.helper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.renyajie.yuyue.MainFragment;

import java.util.List;

import main.activity.experience_class_order.ExperienceClassOrderActivity;
import main.activity.individual_class_order.IndividualClassOrderActivity;
import main.activity.people_class_order.PeopleClassOrderActivity;
import main.activity.place_class.PlaceClassActivity;
import main.activity.place_teacher.PlaceTeacherActivity;
import main.model.GridButtonModel;
import mine.activity.order_class.OrderClassListActivity;
import utils.RequestType;

/**
 * Created by Thor on 2018/3/11.
 *
 * Main页面网格按钮的适配器
 */

public class MyItemClickListener implements AdapterView.OnItemClickListener {

    private Context context;
    private List<GridButtonModel> gridButtonModelList;

    public MyItemClickListener(Context context, List<GridButtonModel> gridButtonModelList) {
        this.context = context;
        this.gridButtonModelList = gridButtonModelList;
    }

    // TODO 删除Toast，启动Activity
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GridButtonModel model = gridButtonModelList.get(position);
        switch (model.requestType) {
            case Main_PeopleClassOrder:
                Intent intentForPeopleClassOrder = new Intent(context, PeopleClassOrderActivity.class);
                context.startActivity(intentForPeopleClassOrder);
                break;
            case Main_IndividualClassOrder:
                Intent intentForIndividualClassOrder = new Intent(context, IndividualClassOrderActivity.class);
                context.startActivity(intentForIndividualClassOrder);
                break;
            case Main_Sign:
                Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show();
                break;
            case Main_ExperienceClassOrder:
                Intent intentForExperienceOrder = new Intent(context, ExperienceClassOrderActivity.class);
                context.startActivity(intentForExperienceOrder);
                break;
            case Main_PlaceClass:
                Intent intentForPlaceClass = new Intent(context, PlaceClassActivity.class);
                context.startActivity(intentForPlaceClass);
                break;
            case Main_Teacher:
                Intent intentForTeacher = new Intent(context, PlaceTeacherActivity.class);
                context.startActivity(intentForTeacher);
                break;
            case Main_Leave:
                Intent intentForLeave = new Intent(context, OrderClassListActivity.class);
                context.startActivity(intentForLeave);
                break;
            case Main_Classroom:
                Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
