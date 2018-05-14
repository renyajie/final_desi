package com.ryj.yuyue.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.ClassKindResult;
import com.ryj.yuyue.dao.ClassKindMapper;
import com.ryj.yuyue.dao.ClassOrderMapper;
import com.ryj.yuyue.utils.SimpleToken;

/**
 * 和统计分析相关的所有业务
 * @author Thor
 *
 */
@Service
public class AnalyseService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = 
			LoggerFactory.getLogger(AnalyseService.class);
	
	@Autowired
	private ClassOrderMapper classOrderMapper;
	@Autowired
	private ClassKindMapper classKindMapper;
	
	/**
	 * 根据上课时间段统计每个时间段的约课人数
	 * @param timeLength 时间段
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	public List<SimpleToken> getByClassTime(int placeId, int timeLength) {
		
		//查询出11个小时段之间人数总和
		int[] hourNumbers = new int[11];
		
		for(int i = 0; i < 11; i++) {
			hourNumbers[i] = 0;
		}
		
		Date before = new Date();
		Date after = new Date();
		
		int[] hourList = {7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
		
		Calendar calendar = Calendar.getInstance();
		
		for(int i = 0; i < timeLength; i++) {
			if(i != 0) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			}
			before = calendar.getTime();
			after = calendar.getTime();
			before.setMinutes(0);
			before.setSeconds(0);
			after.setMinutes(0);
			after.setSeconds(0);
			for(int j = 0; j < 11; j++) {
		        before.setHours(hourList[j]);
				after.setHours(hourList[j] + 1);
				hourNumbers[j] = hourNumbers[j] + 
						classOrderMapper.getOrderNumberByHour(placeId, before, after);
			}
		}
		
		String[] labels = {
				"7:00-8:00","8:00-9:00","9:00-10:00","10:00-11:00",
				"11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00",
				"15:00-16:00","16:00-17:00","17:00-18:00"
		};
		
		for(int i = 0; i < 11; i++) {
			logger.info("hourNumbers " + i + " is " + hourNumbers[i]);
		}
		
		
		List<SimpleToken> result = new ArrayList<SimpleToken>();
		for(int i = 0; i < 11; i++) {
			result.add(new SimpleToken(labels[i], hourNumbers[i] + ""));
		}
		
		return result;
	}
	
	//根据每天的预约人数统计
	@SuppressWarnings("deprecation")
	public List<SimpleToken> getByOrderNumber(int placeId, int timeLength) {
		
		List<SimpleToken> result = new ArrayList<SimpleToken>();
		
		Date before = new Date();
		Date after = new Date();
		
		Calendar calendar = Calendar.getInstance();
		
		for(int i = 0; i < timeLength; i++) {
			//历遍每一天的预约数量
			if(i != 0) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			}
			before = calendar.getTime();
			after = calendar.getTime();
			
			before.setHours(0);
			before.setMinutes(0);
			before.setSeconds(0);
			
			after.setHours(23);
			after.setMinutes(59);
			after.setSeconds(59);
			
			int tmp = classOrderMapper.
					getOrderNumberByHour(placeId, before, after);
			
			DateFormat dateFormat = new SimpleDateFormat("MM月dd号");
			String day = dateFormat.format(before);
			result.add(new SimpleToken(day, tmp + ""));
		}
		
		return result;
	}
	
	//根据每门课的预约人数统计
	@SuppressWarnings("deprecation")
	public List<SimpleToken> getByClassNumber(int placeId, int timeLength) {
		
		List<SimpleToken> result = new ArrayList<SimpleToken>();
		
		List<ClassKindResult> classKinds = 
				classKindMapper.getClassKind(placeId, null, null, null, null, null); 
		
		//确认开始时间
		Date before = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1 * timeLength);
		before = calendar.getTime();
		before.setHours(0);
		before.setMinutes(0);
		before.setSeconds(0);
		
		//确认结束时间
		Date after = new Date();
		after.setHours(23);
		after.setMinutes(59);
		after.setSeconds(59);
		
		int classOrderNumber = 0;
		
		//得到某个场馆的n门课，循环n次
		for(ClassKindResult classKind: classKinds) {
			
			classOrderNumber = classOrderMapper.getOrderNumberByClass(
							classKind.getId(), before, after);
			
			result.add(new SimpleToken(
							classKind.getClaKName(), classOrderNumber + ""));
		}
		
		logger.info("timelength is " + timeLength);
		
		return result;
	}
}
