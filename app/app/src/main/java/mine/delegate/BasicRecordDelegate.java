package mine.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import mine.helper.MyClickListener;
import mine.model.BasicRecordModel;
import utils.RequestType;
import utils.ViewHolderType;
import utils.SuperDelegate;

/**
 * Created by Thor on 2018/3/9.
 *
 * 处理Mine页面中用户的基本使用数据信息的部分
 */

public class BasicRecordDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater inflater;
    private BasicRecordModel basicRecordModel;

    public BasicRecordDelegate(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setBasicRecordModel(BasicRecordModel basicRecordModel) {
        this.basicRecordModel = basicRecordModel;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.BasicRecord;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d("BasicRecordDelegate", "onCreateViewHolder");
        View view = inflater.inflate(
                R.layout.fragment_mine_basic_record, parent, false);
        BasicRecordViewHolder viewHolder = new BasicRecordViewHolder(view);
        return viewHolder;
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("BasicRecordDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI

        //TextView设置数字内容需要转化为String，否则会被当成ResourceId使用
        ((BasicRecordViewHolder)viewHolder).orderLessonTime
                .setText(String.valueOf(basicRecordModel.orderLessonTime));
        ((BasicRecordViewHolder)viewHolder).learnTime
                .setText(String.valueOf(basicRecordModel.learnTime));
        ((BasicRecordViewHolder)viewHolder).memberScore
                .setText(String.valueOf(basicRecordModel.memberScore));

        ((BasicRecordViewHolder)viewHolder).orderLessonTime.
                setOnClickListener(new MyClickListener(RequestType.Mine_OrderLessonTime, context));
        ((BasicRecordViewHolder)viewHolder).learnTime.
                setOnClickListener(new MyClickListener(RequestType.Mine_LearnTime, context));
        ((BasicRecordViewHolder)viewHolder).memberScore.
                setOnClickListener(new MyClickListener(RequestType.Mine_MemberScore, context));
    }

    public static class BasicRecordViewHolder extends RecyclerView.ViewHolder {

        TextView orderLessonTime, learnTime, memberScore;

        public BasicRecordViewHolder(View itemView) {
            super(itemView);
            orderLessonTime = itemView.findViewById(R.id.orderLessonTime);
            learnTime = itemView.findViewById(R.id.learnTime);
            memberScore = itemView.findViewById(R.id.memberScore);
        }
    }
}
