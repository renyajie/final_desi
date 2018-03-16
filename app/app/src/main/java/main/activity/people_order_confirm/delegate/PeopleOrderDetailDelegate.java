package main.activity.people_order_confirm.delegate;

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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.renyajie.yuyue.R;

import main.activity.people_order_confirm.model.PeopleOrderDetailModel;
import utils.MyApplication;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/13.
 *
 * 团课预约订单的基本信息展示部分
 */

public class PeopleOrderDetailDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private PeopleOrderDetailModel model;
    private ImageLoader imageLoader;

    public PeopleOrderDetailDelegate(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        imageLoader = MyApplication.getImageLoader();
    }

    public void setPeopleOrderDetailModel(PeopleOrderDetailModel peopleOrderConfirmModel) {
        this.model = peopleOrderConfirmModel;
        Toast.makeText(context, "哈哈", Toast.LENGTH_SHORT).show();
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.PeopleOrderDetail;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PeopleClassOrderDetailViewHolder(layoutInflater.inflate(
                R.layout.activity_main_people_order_confirm_class_detail, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("PeopleOrderDetailDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI

        ((PeopleClassOrderDetailViewHolder)viewHolder).classPic
                .setDefaultImageResId(R.mipmap.ic_launcher);
        ((PeopleClassOrderDetailViewHolder)viewHolder).classPic
                .setErrorImageResId(R.mipmap.ic_launcher);
        ((PeopleClassOrderDetailViewHolder)viewHolder).classPic
                .setImageUrl(model.picUrl, imageLoader);

        ((PeopleClassOrderDetailViewHolder)viewHolder).className.setText(model.className);
        ((PeopleClassOrderDetailViewHolder)viewHolder).teacherName.setText(model.teacherName);
        ((PeopleClassOrderDetailViewHolder)viewHolder).classroom.setText(model.classroom);
        //Integer类型的参数不要直接设置到TextView里
        ((PeopleClassOrderDetailViewHolder)viewHolder).allowance.setText(String.valueOf(model.allowance));
        ((PeopleClassOrderDetailViewHolder)viewHolder).time.setText(model.classTime);
        ((PeopleClassOrderDetailViewHolder)viewHolder).difficulty.setRating(Float.valueOf(model.difficulty));

        //TODO 设置一个对话框，显示课程简介
        ((PeopleClassOrderDetailViewHolder)viewHolder).introduction.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showNormalDialog(model);
                    }
                }
        );
    }

    //显示对话框
    private void showNormalDialog(PeopleOrderDetailModel model){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle(model.className);
        normalDialog.setMessage(model.introduction);
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

    public static class PeopleClassOrderDetailViewHolder extends RecyclerView.ViewHolder {

        NetworkImageView classPic;
        Button introduction;
        TextView className, teacherName, classroom, allowance, time;
        RatingBar difficulty;

        public PeopleClassOrderDetailViewHolder(View itemView) {
            super(itemView);
            classPic = itemView.findViewById(R.id.class_pic);
            introduction = itemView.findViewById(R.id.introduction);
            className = itemView.findViewById(R.id.class_name);
            teacherName = itemView.findViewById(R.id.teacher_name);
            classroom = itemView.findViewById(R.id.classroom);
            allowance = itemView.findViewById(R.id.allowance);
            time = itemView.findViewById(R.id.time);
            difficulty = itemView.findViewById(R.id.difficulty);
        }
    }
}