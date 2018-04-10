package mine.activity.comment.adapter;

/**
 * Created by Thor on 2018/4/9.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.List;

import bean.ClassOrder;
import bean.Score;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/15.
 * <p>
 * 订单评价的适配器
 */
public class CommentOrderListAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private List<ClassOrder> classOrderList;
    private List<Score> scoreList;
    private ScoreButtonClick scoreButtonClick;

    //当前列表状态为已经评价还是没有评价
    private int isScore = 1;

    //已评价和未评价
    private static final int SCORE_LESSON = 1;
    private static final int UNSCORE_LESSON = 0;

    interface ScoreButtonClick {
        void scoreButtonClick(int orderId, int classId);
    }

    public void setScoreButtonClick(ScoreButtonClick scoreButtonClick) {
        this.scoreButtonClick = scoreButtonClick;
    }

    public CommentOrderListAdapter(Context context, List<Score> scoreList, List<ClassOrder> classOrderList) {
        this.scoreList = scoreList;
        this.classOrderList = classOrderList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setClassOrderList(List<ClassOrder> classOrderList) {
        this.classOrderList = classOrderList;
        isScore = 0;
        notifyDataSetInvalidated();
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
        isScore = 1;
        notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        if (isScore == SCORE_LESSON) {
            return scoreList.size();
        }
        return classOrderList.size();
    }

    @Override
    public Object getItem(int position) {
        if (isScore == SCORE_LESSON) {
            return scoreList.get(position);
        }
        return classOrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return isScore;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ScoreViewHolder scoreViewHolder = null;
        UnscoreViewHolder unscoreViewHolder = null;

        if(convertView == null) {
            switch (isScore) {
                case SCORE_LESSON:
                    scoreViewHolder = new ScoreViewHolder();

                    convertView = layoutInflater.inflate(
                            R.layout.activity_mine_comment_list_score_item, parent, false);

                    scoreViewHolder.score = convertView.findViewById(R.id.score);
                    scoreViewHolder.scoreTime = convertView.findViewById(R.id.score_time);
                    scoreViewHolder.comment = convertView.findViewById(R.id.comment);
                    scoreViewHolder.placeName = convertView.findViewById(R.id.place_name);
                    scoreViewHolder.orderTime = convertView.findViewById(R.id.order_time);
                    scoreViewHolder.className = convertView.findViewById(R.id.class_name);

                    convertView.setTag(scoreViewHolder);
                    break;
                case UNSCORE_LESSON:
                    unscoreViewHolder = new UnscoreViewHolder();

                    convertView = layoutInflater.inflate(
                            R.layout.activity_mine_comment_list_unscore_item, parent, false);

                    unscoreViewHolder.className = convertView.findViewById(R.id.class_name);
                    unscoreViewHolder.placeName = convertView.findViewById(R.id.place_name);
                    unscoreViewHolder.orderTime = convertView.findViewById(R.id.order_time);
                    unscoreViewHolder.score = convertView.findViewById(R.id.score);

                    convertView.setTag(unscoreViewHolder);
                    break;
                default:
                    break;
            }
        }
        else {
            switch (isScore) {
                case SCORE_LESSON:
                    scoreViewHolder = (ScoreViewHolder) convertView.getTag();
                    break;
                case UNSCORE_LESSON:
                    unscoreViewHolder = (UnscoreViewHolder) convertView.getTag();
                    break;
                default:
                    break;
            }
        }

        switch (isScore) {
            case SCORE_LESSON:
                Score score = scoreList.get(position);
                scoreViewHolder.score.setRating(score.getScore());
                scoreViewHolder.scoreTime.setText(
                        UtilsMethod.getStringFromDateForScore(score.getScoreTime()));
                scoreViewHolder.comment.setText(score.getComment());
                scoreViewHolder.placeName.setText(score.getpName());
                scoreViewHolder.className.setText(score.getClassKName());
                scoreViewHolder.orderTime.setText(
                        UtilsMethod.getStringFromDateForCheck(score.getOrderTime())
                );
                break;
            case UNSCORE_LESSON:
                ClassOrder classOrder = classOrderList.get(position);
                unscoreViewHolder.placeName.setText(classOrder.getpName());
                unscoreViewHolder.className.setText(classOrder.getClaKName());
                unscoreViewHolder.orderTime.setText(
                        UtilsMethod.getStringFromDateForCheck(classOrder.getOrdTime())
                );
                break;
            default:
                break;
        }

        return convertView;
    }

    class UnscoreViewHolder {
        public ImageView classPic;
        public TextView className, orderTime, placeName, score;
    }

    class ScoreViewHolder {
        public RatingBar score;
        public TextView scoreTime, comment, className, orderTime, placeName;
        public ImageView classPic;
    }
}
