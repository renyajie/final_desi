package test;

import java.util.Arrays;
import java.util.List;

import main.activity.individual_class_order.model.IndividualClassBriefModel;

/**
 * Created by Thor on 2018/3/15.
 *
 * 私教预约的测试数据
 */

public class IndividualClassOrderData {

    public static List<IndividualClassBriefModel> individualClassBriefModelList = Arrays.asList(
            new IndividualClassBriefModel(
                    1, 1,
                    "雪梨", "03-15 06:00-08:00", 3
            ),

            new IndividualClassBriefModel(
                    1, 2,
                    "张悦", "03-15 07:00-09:00", 2
            ),

            new IndividualClassBriefModel(
                    1, 3,
                    "冰糖", "03-15 09:00-18:00", 1
            ),

            new IndividualClassBriefModel(
                    1, 5,
                    "胖胖", "03-15 16:00-18:00", 4
            )
    );
}
