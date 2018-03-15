package test;

import java.util.Arrays;
import java.util.List;

import main.activity.people_class_order.model.PeopleClassBriefModel;
import main.activity.people_class_order.model.PlaceModel;
import utils.AppConstant;

/**
 * Created by Thor on 2018/3/12.
 *
 * 团课预约的测试界面
 */

public class PeopleClassOrderData {

    //场馆信息
    public static List<PlaceModel> placeModelList = Arrays.asList(
            new PlaceModel(1, "A场馆"),
            new PlaceModel(2, "B场馆"),
            new PlaceModel(3, "C场馆")
    );

    //课程列表数据
    public static List<PeopleClassBriefModel> peopleClassBriefModelList = Arrays.asList(
            /*
            new PeopleClassBriefModel(1, 1,
                    "06:00", "A课程", "张馨月",
                    "一号教室", 2,
                    AppConstant.PEOPLE_ORDER_CAN_ORDER, 3),*/

            new PeopleClassBriefModel(1,2,
                    "07:30", "B课程", "王斌",
                    "二号教室", 3,
                    AppConstant.PEOPLE_ORDER_START_LESSON, 5),

            new PeopleClassBriefModel(1,3,
                    "08:00", "C课程", "雪梨",
                    "三号教室", 5,
                    AppConstant.PEOPLE_ORDER_CAN_ORDER, 2),

            new PeopleClassBriefModel(1,4,
                    "08:30", "D课程", "冰糖",
                    "四号教室", 5,
                    AppConstant.PEOPLE_ORDER_FULL, 0),

            new PeopleClassBriefModel(1,5,
                    "09:00", "E课程", "胖胖",
                    "三号教室", 5,
                    AppConstant.PEOPLE_ORDER_FULL, 3),

            new PeopleClassBriefModel(1,6,
                    "09:30", "F课程", "糖糖",
                    "三号教室", 5,
                    AppConstant.PEOPLE_ORDER_CAN_ORDER, 3)
    );
}
