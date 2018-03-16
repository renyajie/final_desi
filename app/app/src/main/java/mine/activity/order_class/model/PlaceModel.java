package mine.activity.order_class.model;

/**
 * Created by Thor on 2018/3/16.
 *
 * 约课详情中的地方模型。包括瑜伽馆名称和地址
 */

public class PlaceModel {

    public String placeName;
    public String address;

    public PlaceModel(String placeName, String address) {
        this.placeName = placeName;
        this.address = address;
    }
}
