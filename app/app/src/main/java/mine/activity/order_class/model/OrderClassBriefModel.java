package mine.activity.order_class.model;

/**
 * Created by Thor on 2018/3/15.
 *
 * 约课的概要信息的数据，包括课程名称，预约时间，场馆
 */

public class OrderClassBriefModel {

    public Integer orderId;
    public String className, orderTime, placeName;

    public OrderClassBriefModel(Integer orderId, String className, String orderTime, String placeName) {
        this.orderId = orderId;
        this.className = className;
        this.orderTime = orderTime;
        this.placeName = placeName;
    }
}
