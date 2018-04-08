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
import utils.RequestType;
import utils.ViewHolderType;
import utils.SuperDelegate;

/**
 * Created by Thor on 2018/3/9.
 *
 * Mine页面中的其他部分，包含体验课记录，我的会员卡，购物订单等
 */

public class MoreFunctionDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater inflater;

    public MoreFunctionDelegate(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.MoreFunction;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d("MoreFunctionDelegate", "onCreateViewHolder");
        View view = inflater.inflate(
                R.layout.fragment_mine_more_function, parent, false);
        MoreFunctionViewHolder viewHolder = new MoreFunctionViewHolder(view);
        return viewHolder;
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("MoreFunctionDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        ((MoreFunctionViewHolder)viewHolder).orderLesson
                .setOnClickListener(new MyClickListener(RequestType.Mine_OrderLesson, context));
        ((MoreFunctionViewHolder)viewHolder).experimentLessonRecord
                .setOnClickListener(new MyClickListener(RequestType.Mine_ExperimentLessonRecord, context));
        ((MoreFunctionViewHolder)viewHolder).memberCard
                .setOnClickListener(new MyClickListener(RequestType.Mine_MemberCard, context));
        ((MoreFunctionViewHolder)viewHolder).buyCard
                .setOnClickListener(new MyClickListener(RequestType.Mine_BuyCard, context));
        ((MoreFunctionViewHolder)viewHolder).lessonAbsent
                .setOnClickListener(new MyClickListener(RequestType.Mine_LessonAbsent, context));
        ((MoreFunctionViewHolder)viewHolder).historyRecord
                .setOnClickListener(new MyClickListener(RequestType.Mine_HistoryRecord, context));
    }

    public static class MoreFunctionViewHolder extends RecyclerView.ViewHolder {

        TextView orderLesson, experimentLessonRecord, memberCard, buyCard, lessonAbsent, historyRecord;

        public MoreFunctionViewHolder(View itemView) {
            super(itemView);
            orderLesson = itemView.findViewById(R.id.order_lesson);
            experimentLessonRecord = itemView.findViewById(R.id.experiment_lesson_record);
            memberCard = itemView.findViewById(R.id.member_card);
            buyCard = itemView.findViewById(R.id.buy_card);
            lessonAbsent = itemView.findViewById(R.id.lesson_absent);
            historyRecord = itemView.findViewById(R.id.history_record);
        }
    }
}
