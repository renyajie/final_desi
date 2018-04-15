package main.activity.individual_order_confirm.delegate;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.renyajie.yuyue.R;

import bean.ClassInfo;
import main.activity.individual_order_confirm.model.IndividualOrderDetailModel;
import main.activity.people_order_confirm.model.PeopleOrderDetailModel;
import mine.activity.order_card.adapter.CardKindListAdapter;
import utils.AppConstant;
import utils.MyApplication;
import utils.SuperDelegate;
import utils.UtilsMethod;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/13.
 *
 * 私教预约订单的基本信息展示部分
 */

public class IndividualOrderDetailDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private ClassInfo classInfo;
    private IndividualOrderDetailViewHolder detailViewHolder;
    ImageLoader imageLoader;

    public IndividualOrderDetailDelegate(Context context) {
        imageLoader = new ImageLoader(MyApplication.getHttpQueue(), MyApplication.getBitmapCache());
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.IndividualOrderDetail;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new IndividualOrderDetailViewHolder(layoutInflater.inflate(
                R.layout.activity_main_individual_order_confirm_class_detail, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("IndividualOrderDetailDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        detailViewHolder = (IndividualOrderDetailViewHolder)viewHolder;

        detailViewHolder.classPic.setDefaultImageResId(R.mipmap.ic_launcher);
        detailViewHolder.classPic.setErrorImageResId(R.mipmap.ic_launcher);
        detailViewHolder.classPic.setImageUrl(AppConstant.URL + classInfo.getPicUrl(), imageLoader);

        detailViewHolder.teacherName.setText(classInfo.getTeaName());
        detailViewHolder.number.setText(String.valueOf(classInfo.getOrderNum()));
        detailViewHolder.time.setText(
                UtilsMethod.getStringFromDateForDetail(
                        classInfo.getcDay(), classInfo.getStaTime(), classInfo.getEndTime()
                ));
        detailViewHolder.introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog(classInfo);
            }
        });
    }

    //显示对话框
    private void showNormalDialog(ClassInfo classInfo){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("课程介绍");
        normalDialog.setMessage(classInfo.getIntro());
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    public static class IndividualOrderDetailViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView classPic;
        Button introduction;
        TextView teacherName, number, time;

        public IndividualOrderDetailViewHolder(View itemView) {
            super(itemView);
            classPic = itemView.findViewById(R.id.class_pic);
            introduction = itemView.findViewById(R.id.introduction);
            number = itemView.findViewById(R.id.number);
            teacherName = itemView.findViewById(R.id.teacher_name);
            time = itemView.findViewById(R.id.time);
        }
    }
}
