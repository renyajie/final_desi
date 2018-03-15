package test;

import java.util.Arrays;

import main.activity.people_order_confirm.model.CardModel;
import main.activity.people_order_confirm.model.PeopleOrderDetailModel;
import main.activity.people_order_confirm.model.PeopleOrderPayModel;

/**
 * Created by Thor on 2018/3/13.
 *
 * 团课预约订单界面的数据
 */

public class PeopleOrderConfirmData {

    public static PeopleOrderDetailModel peopleOrderDetailModel = new PeopleOrderDetailModel(
            0, 0,
            "https://pic3.zhimg.com/80/1f959cc7fbbf70be393864be9bd344c9_hd.jpg",
            "猴神瑜伽",
            "猴神",
            "印度",
            "03-01 06:00-07:00",
            3,
            3,
            "印度的大好人，上了他的课你也会变成好人"
    );

    public static PeopleOrderPayModel peopleOrderPayModel = new PeopleOrderPayModel(
            1, 1,
            Arrays.asList(
                    new CardModel(1, "黄金卡"),
                    new CardModel(2,"白银卡"),
                    new CardModel(3,"青铜卡")),
            1,
            1
    );
}
