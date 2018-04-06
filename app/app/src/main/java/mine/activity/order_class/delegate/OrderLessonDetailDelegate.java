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

import bean.ClassInfo;
import main.activity.individual_order_confirm.model.IndividualOrderDetailModel;
import mine.activity.order_class.model.IndividualLessonDetailModel;
import mine.activity.order_class.model.PeopleLessonDetailModel;
import utils.AppConstant;
import utils.SuperDelegate;
import utils.UtilsMethod;
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
    private ClassInfo classInfo;
    //此处有多种情况，该变量表示选择加载哪种布局
    private Integer viewType;

    public OrderLessonDetailDelegate(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
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

        Log.d("recycler", "create OrderLessonDetailDelegate");
        Log.d("get", "viewType is " + viewType);
        //若是团课约课信息
        if (viewType == AppConstant.PEOPLE_ORDER) {
            Log.d("get", "加载团课布局");
            return new PeopleOrderDetailViewHolder(layoutInflater.inflate(
                    R.layout.activity_mine_order_detail_lesson_people,
                    parent, false
            ));
        }
        //若是私教约课信息
        Log.d("get", "加载私教布局");
        return new IndividualOrderDetailViewHolder(layoutInflater.inflate(
                R.layout.activity_mine_order_detail_lesson_individual,
                parent, false
        ));

    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("recycler", "bind OrderLessonDetailDelegate");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        if (viewType == AppConstant.PEOPLE_ORDER) {
            ((PeopleOrderDetailViewHolder)viewHolder).className
                    .setText(classInfo.getClaKName());
            ((PeopleOrderDetailViewHolder)viewHolder).time
                    .setText(UtilsMethod.getStringFromDateForDetail(
                            classInfo.getcDay(), classInfo.getStaTime(), classInfo.getEndTime()));
            ((PeopleOrderDetailViewHolder)viewHolder).placeName
                    .setText(classInfo.getpName());
            //((PeopleOrderDetailViewHolder)viewHolder).classroom.setText(peopleModel.classroom);

        } else if (viewType == AppConstant.INDIVIDUAL_ORDER) {

            ((IndividualOrderDetailViewHolder)viewHolder).teacherName
                    .setText(classInfo.getTeaName());
            ((IndividualOrderDetailViewHolder)viewHolder).time
                    .setText(UtilsMethod.getStringFromDateForDetail(
                            classInfo.getcDay(), classInfo.getStaTime(), classInfo.getEndTime()));
            ((IndividualOrderDetailViewHolder)viewHolder).placeName
                    .setText(classInfo.getpName());

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
