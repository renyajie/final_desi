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
import main.model.GridButtonModel;
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
            case Main_Photo:
                Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show();
                break;
            case Main_Teacher:
                Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show();
                break;
            case Main_Leave:
                Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show();
                break;
            case Main_Classroom:
                Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
