package mine.activity.order_class.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import main.activity.individual_order_confirm.model.IndividualOrderDetailModel;
import mine.activity.order_class.model.IndividualLessonDetailModel;
import mine.activity.order_class.model.PeopleLessonDetailModel;
import utils.AppConstant;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/15.
 *
 * 约课详情的第一部分，课程信息，包括课程名称，时间，场地，教室，教师姓名，
 * 根据团课，或私教课而有所不同
 */

public class OrderLessonDetailDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private PeopleLessonDetailModel peopleModel;
    private IndividualLessonDetailModel individualModel;
    //此处有多种情况，该变量表示选择加载哪种布局
    private Integer viewType;

    public OrderLessonDetailDelegate(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setPeopleModel(PeopleLessonDetailModel model) {
        this.peopleModel = model;
    }

    public void setIndividualModel(IndividualLessonDetailModel model) {
        this.individualModel = model;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.OrderLesson;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        //若是团课约课信息
        if (viewType == AppConstant.PEOPLE_ORDER) {
            Log.d("msg", "加载团课布局");
            return new PeopleOrderDetailViewHolder(layoutInflater.inflate(
                    R.layout.activity_mine_order_detail_lesson_people,
                    parent, false
            ));
        }
        //若是私教约课信息
        else if (viewType == AppConstant.INDIVIDUAL_ORDER) {
            Log.d("msg", "加载私教布局");
            return new IndividualOrderDetailViewHolder(layoutInflater.inflate(
                    R.layout.activity_mine_order_detail_lesson_individual,
                    parent, false
            ));
        }
        //若是体验课程
        else {
            Log.d("msg", "加载体验课布局");
            return new ExperienceOrderDetailViewHolder(layoutInflater.inflate(
                    R.layout.activity_mine_order_detail_lesson_experience,
                    parent, false
            ));
        }
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("OrderLessonDetailDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        if (viewType == AppConstant.PEOPLE_ORDER) {
            ((PeopleOrderDetailViewHolder)viewHolder).className.setText(peopleModel.className);
            ((PeopleOrderDetailViewHolder)viewHolder).time.setText(peopleModel.time);
            ((PeopleOrderDetailViewHolder)viewHolder).placeName.setText(peopleModel.placeName);
            ((PeopleOrderDetailViewHolder)viewHolder).classroom.setText(peopleModel.classroom);

        } else if (viewType == AppConstant.INDIVIDUAL_ORDER) {

            ((IndividualOrderDetailViewHolder)viewHolder).teacherName
                    .setText(individualModel.teacherName);
            ((IndividualOrderDetailViewHolder)viewHolder).time
                    .setText(individualModel.time);
            ((IndividualOrderDetailViewHolder)viewHolder).placeName
                    .setText(individualModel.placeName);

        } else {



        }
    }

    public static class PeopleOrderDetailViewHolder extends RecyclerView.ViewHolder {
        public TextView className, time, placeName, classroom;
        public PeopleOrderDetailViewHolder(View itemView) {
            super(itemView);
            this.className = itemView.findViewById(R.id.class_name);
            this.time = itemView.findViewById(R.id.time);
            this.placeName = itemView.findViewById(R.id.place_name);
            this.classroom = itemView.findViewById(R.id.classroom);
        }
    }

    public static class IndividualOrderDetailViewHolder extends RecyclerView.ViewHolder {
        public TextView teacherName, time, placeName;
        public IndividualOrderDetailViewHolder(View itemView) {
            super(itemView);
            this.teacherName = itemView.findViewById(R.id.teacher_name);
            this.time = itemView.findViewById(R.id.time);
            this.placeName = itemView.findViewById(R.id.place_name);
        }
    }

    public static class ExperienceOrderDetailViewHolder extends RecyclerView.ViewHolder {
        public ExperienceOrderDetailViewHolder(View itemView) {
            super(itemView);
        }
    }
}
