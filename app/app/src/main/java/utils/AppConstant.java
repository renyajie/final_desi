package utils;

import android.content.Intent;

/**
 * Created by Thor on 2018/3/13.
 *
 * 程序中经常使用的一些常量
 */

public class AppConstant {

    // 团课预约中使用的一些常量
    public static final Integer PEOPLE_ORDER_START_LESSON = 1;
    public static final Integer PEOPLE_ORDER_CAN_ORDER = 2;
    public static final Integer PEOPLE_ORDER_FULL = 3;

    //表示从团课或是思科预约成功，用于继续预约返回对应的预约界面
    public static final Integer START_FROM_PEOPLE_ORDER = 1;
    public static final Integer START_FROM_INDIVIDUAL_ORDER = 2;
}
