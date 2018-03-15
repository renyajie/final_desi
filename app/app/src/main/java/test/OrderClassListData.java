package test;

import java.util.Arrays;
import java.util.List;

import mine.activity.order_class.model.OrderClassBriefModel;

/**
 * Created by Thor on 2018/3/15.
 *
 * 约课列表的测试数据
 */

public class OrderClassListData {

    public static List<OrderClassBriefModel> orderClassBriefModelList = Arrays.asList(
            new OrderClassBriefModel(1, "基础瑜伽",
                    "2018/3/15 16:30", "印度瑜伽馆"
            ),

            new OrderClassBriefModel(2, "初级瑜伽",
                    "2018/3/16 16:30", "中国瑜伽馆"
            ),

            new OrderClassBriefModel(3, "中级瑜伽",
                    "2018/3/17 16:30", "俄罗斯瑜伽馆"
            ),

            new OrderClassBriefModel(4, "高级瑜伽",
                    "2018/3/18 16:30", "日本瑜伽馆"
            )
    );
}
