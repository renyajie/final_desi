package main.activity.individual_class_order.model;

/**
 * Created by Thor on 2018/3/14.
 *
 * 私教预约的课程信息的数据模型，包括教师姓名，开课时间和预约数量
 */

public class IndividualClassBriefModel {
    public Integer placeId, classId;
    public String teacherName, time;
    public Integer number;

    public IndividualClassBriefModel(Integer placeId, Integer classId, String teacherName,
                                     String time, Integer number) {
        this.placeId = placeId;
        this.classId = classId;
        this.teacherName = teacherName;
        this.time = time;
        this.number = number;
    }
}
