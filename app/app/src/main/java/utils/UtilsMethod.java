package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Thor on 2018/3/12.
 *
 * 程序中需要用到的快捷方法
 */

public class UtilsMethod {

    private static final String DATE_FORMAT = "MM月dd日";

    /**
     * 返回日期的字符串，可以指定是第几天后
     * @param nextAmount 天数增量
     * @return 对应日期的字符串
     */
    public static String theNextNDay(int nextAmount) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, nextAmount);
        Date tomorrow = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String dateString = formatter.format(tomorrow);
        if(dateString.startsWith("0")) {
            dateString = dateString.substring(1, dateString.length());
        }
        return dateString;
    }
}
