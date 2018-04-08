package com.ryj.yuyue.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ryj.yuyue.bean.ClassInfoResult;

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
	
	/**
	 * 修正上课时间，加上8小时
	 * @param classInfoList
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<ClassInfoResult> fixClassInfoResultTime(
			List<ClassInfoResult> classInfoList) {
		
		Date cDay, staTime, endTime;
		for(ClassInfoResult classInfo: classInfoList) {
			cDay = classInfo.getcDay();
			staTime = classInfo.getStaTime();
			endTime = classInfo.getEndTime();
			
			cDay.setHours(cDay.getHours() + 8);
			staTime.setHours(staTime.getHours() + 8);
			endTime.setHours(endTime.getHours() + 8);
			
			classInfo.setcDay(cDay);
			classInfo.setStaTime(staTime);
			classInfo.setEndTime(endTime);
		}
		
		return classInfoList;
	}
}
