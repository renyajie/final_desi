package main.activity.people_order_confirm.model;

/**
 * Created by Thor on 2018/3/13.
 *
 * 团课订单的数据构成
 */

public class PeopleOrderDetailModel {

    public Integer placeId, classId;
    public String picUrl, className, teacherName, classroom, classTime;
    public Integer difficulty, allowance;
    public String introduction;

    public PeopleOrderDetailModel(
            Integer placeId, Integer classId, String picUrl, String className, String teacherName,
            String classroom, String classTime, Integer difficulty, Integer allowance,
            String introduction) {
        this.placeId = placeId;
        this.classId = classId;
        this.picUrl = picUrl;
        this.className = className;
        this.teacherName = teacherName;
        this.classroom = classroom;
        this.classTime = classTime;
        this.difficulty = difficulty;
        this.allowance = allowance;
        this.introduction = introduction;
    }
}
