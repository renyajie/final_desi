package data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.dao.ClassInfoMapper;

/**
 * 创建课程安排
 * @author Thor
 * 2018/4/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreateClassInfo {

	@Autowired
	private ClassInfoMapper classInfoMapper;
	
	Calendar calendar = new GregorianCalendar();
	Date date = new Date();
	Random rand = new Random();
	
	/**
	 * 最后修改日期：2018-5-9，生成4-21至5-21的日期
	 * 获取上课日期
	 * @return
	 */
	//@Test
	@SuppressWarnings("static-access")
	public Date getCDay() {
		calendar.setTime(date);
	    calendar.add(calendar.DATE, rand.nextInt(31) - 19);
	    return calendar.getTime();
	}
	
	/**
	 * 获取上课开始时间 7-16
	 * @param cDay
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Date getStartTime(Date cDay) {
		Date date = new Date();
		date.setDate(cDay.getDate());;
		date.setMonth(cDay.getMonth());
		date.setYear(cDay.getYear());
		date.setHours(rand.nextInt(10) + 7);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}
	
	/**
	 * 获取上课结束时间，上课时间开始+1
	 * @param startTime
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Date getEndTime(Date cDay, int hour) {
		Date date = new Date();
		date.setDate(cDay.getDate());
		date.setMonth(cDay.getMonth());
		date.setYear(cDay.getYear());
		date.setHours(hour + 1);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}
	
	/**
	 * 生成课程信息: 每个场馆60个团课安排，60个私教安排
	 * 
	 * 1-1200团课   1201-2400私教
	 * 
	 * 1-20 场馆
	 * 1-300 老师
	 * 1-400 团课 401-800 私教
	 * 上课时间 4月21-5月21日
	 * 开始时间 7：00-16：00
	 * 结束时间：开始时间+1
	 * 课程容量：20
	 * 花费：1
	 * 已选人数：0
	 * @return
	 */
	//@Test
	@SuppressWarnings("deprecation")
	public List<ClassInfo> getClassInfo() {
		
		List<ClassInfo> result = new ArrayList<ClassInfo>();
		ClassInfo classInfo = null;
		Date cDay = null, startTime = null, endTime = null;
		Random rand = new Random();
		
		//循环两次，第一次生成团课安排，第二次生成私教安排
		for(int m = 0; m < 2; m++) {
			
			for(int i = 0; i < 20; i++) {
				
				for(int j = 0; j < 60; j++) {
					
					cDay = getCDay();
					startTime = getStartTime(cDay);
					endTime = getEndTime(cDay, startTime.getHours());
					
					classInfo = new ClassInfo();
					classInfo.setAllowance(20);
					classInfo.setcDay(cDay);
					classInfo.setStaTime(startTime);
					classInfo.setEndTime(endTime);
					classInfo.setClaKId(rand.nextInt(20) + 1 + i * 20 + m * 400);
					classInfo.setExpend(1);
					classInfo.setLength(60);
					classInfo.setOrderNum(0);
					classInfo.setpId(i + 1);
					classInfo.setTeaId(rand.nextInt(15) + 1 + i * 15);
					//System.out.println(classInfo);
					result.add(classInfo);
				}
			}
		}
		
		
		
		return result;
	}
	
	@Test
	public void addClassInfo() {
		List<ClassInfo> classInfoList = getClassInfo();
		for(ClassInfo classInfo: classInfoList) {
			classInfoMapper.insertSelective(classInfo);
		}
	}
}
