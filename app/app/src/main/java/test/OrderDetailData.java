package test;

import mine.activity.order_class.model.IndividualLessonDetailModel;
import mine.activity.order_class.model.OrderDetailModel;
import mine.activity.order_class.model.PeopleLessonDetailModel;
import mine.activity.order_class.model.PlaceModel;

/**
 * Created by Thor on 2018/3/15.
 *
 * mine页面中的约课信息的课程详情的测试数据
 */

public class OrderDetailData {

    public static PeopleLessonDetailModel modelA = new PeopleLessonDetailModel(
            "初级瑜伽",
            "2018-3-15 06:00",
            "印度瑜伽馆",
            "一号教室"
    );

    public static IndividualLessonDetailModel modelB = new IndividualLessonDetailModel(
            "冰糖",
            "2018-3-15 06:00",
            "印度瑜伽馆"
    );

    public static PlaceModel placeModel = new PlaceModel(
            "印度瑜伽馆",
            "印度德里一号大街"
    );

    public static OrderDetailModel orderDetailModel = new OrderDetailModel(
            "2018031512356",
            "2018-03-15 14:63:32",
            "17826856214"
    );
}
