package main.activity.individual_order_confirm.model;

/**
 * Created by Thor on 2018/3/15.
 *
 * 私教预约的订单部分
 */

public class IndividualOrderDetailModel {
    public Integer placeId, classId;
    public String teacherName, time;
    public Integer number;
    public String introduction;

    public IndividualOrderDetailModel(Integer placeId, Integer classId, String teacherName,
                                     String time, Integer number, String introduction) {
        this.placeId = placeId;
        this.classId = classId;
        this.teacherName = teacherName;
        this.time = time;
        this.number = number;
        this.introduction = introduction;
    }
}
