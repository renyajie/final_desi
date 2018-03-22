package com.ryj.yuyue.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 有关日期的快捷方法
 * @author Thor
 *
 */
public class DateMethod {

	public static Date getDateFromString(String str) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(str);
	}
}
