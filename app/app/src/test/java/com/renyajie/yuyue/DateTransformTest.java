package com.renyajie.yuyue;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Thor on 2018/3/12.
 *
 * 测试Date类型转换为指定格式的日期
 */

public class DateTransformTest {

    /**
     * 测试把日期转成字符串
     * @throws Exception
     */
    @Test
    public void dateToString() throws Exception {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        String dateString = formatter.format(currentTime);
        if(dateString.startsWith("0")) {
            dateString = dateString.substring(1, dateString.length());
        }
        assertEquals("3月12日", dateString);
    }

    @Test
    public void nextDay() throws Exception {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = c.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        String dateString = formatter.format(tomorrow);
        if(dateString.startsWith("0")) {
            dateString = dateString.substring(1, dateString.length());
        }
        assertEquals("3月13日", dateString);
    }
}
