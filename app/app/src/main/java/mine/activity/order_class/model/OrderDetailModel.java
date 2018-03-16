package mine.activity.order_class.model;

/**
 * Created by Thor on 2018/3/16.
 *
 * 约课数据的订单数据，包括订单号，购买时间和手机号
 */

public class OrderDetailModel {

    public String orderId, orderTime, phone;

    public OrderDetailModel(String orderId, String orderTime, String phone) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.phone = phone;
    }
}
