package mine.model;

/**
 * Created by Thor on 2018/3/9.
 *
 * 用户的基本上课记录模型，记录了用户约课次数，上课记录和用户积分
 */

public class BasicRecordModel {

    //约课次数
    public Integer orderLessonTime;

    //上课次数
    public Integer learnTime;

    //会员积分
    public Integer memberScore;

    public BasicRecordModel(Integer orderLessonTime, Integer learnTime, Integer memberScore) {
        this.orderLessonTime = orderLessonTime;
        this.learnTime = learnTime;
        this.memberScore = memberScore;
    }
}
