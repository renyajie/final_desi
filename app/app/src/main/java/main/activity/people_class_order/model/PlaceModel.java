package main.activity.people_class_order.model;

/**
 * Created by Thor on 2018/3/12.
 *
 * 选择课程数据模型，包括场馆名称，场馆编号
 */

public class PlaceModel {

    public Integer placeId;
    public String placeName;

    public PlaceModel(Integer placeId, String placeName) {
        this.placeId = placeId;
        this.placeName = placeName;
    }
}
