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

    //团课，私教或是体验课程
    public static final Integer PEOPLE_ORDER = 1;
    public static final Integer INDIVIDUAL_ORDER = 2;
    public static final Integer EXPERIENCE_ORDER = 3;

    //后台交互常量
    public static final String URL = "http://192.168.137.1:8000/";
    public static final Integer HANDLE_SUCCESS_CODE = 100;
    public static final Integer HANDLE_FAILURE_CODE = 200;
    public static final int GSON_FOR_ALL = 1;
    public static final int GSON_FOR_HOUR = 2;
    public static final int NORMAL_GSON = 0;

    public static final String Pic_Url = "https://pic3.zhimg.com/80/1f959cc7fbbf70be393864be9bd344c9_hd.jpg";
}
