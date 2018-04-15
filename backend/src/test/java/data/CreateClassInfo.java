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
	 * 获取上课日期
	 * @return
	 */
	//@Test
	@SuppressWarnings("static-access")
	public Date getCDay() {
		calendar.setTime(date);
	    calendar.add(calendar.DATE, rand.nextInt(17) + 1);
	    return calendar.getTime();
	}
	
	/**
	 * 获取上课开始时间
	 * @param cDay
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Date getStartTime(Date cDay) {
		Date date = new Date();
		date = cDay;
		date.setHours(rand.nextInt(10) + 7);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}
	
	/**
	 * 获取上课结束时间
	 * @param startTime
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Date getEndTime(Date startTime) {
		Date date = new Date();
		date.setHours(startTime.getHours() + 1);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}
	
	/**
	 * 生成课程信息: 每个场馆40个团课安排，40个私教安排
	 * 
	 * 1-20 场馆
	 * 1-300 老师
	 * 1-400 团课 401-800 私教
	 * 上课时间 4月15-5月1
	 * 开始时间 7：00-16：00
	 * 结束时间：开始时间+1
	 * 课程容量：20
	 * 花费：1
	 * 已选人数：0
	 * @return
	 */
	//@Test
	public List<ClassInfo> getClassInfo() {
		
		List<ClassInfo> result = new ArrayList<ClassInfo>();
		ClassInfo classInfo = null;
		Date cDay = null, startTime = null, endTime;
		Random rand = new Random();
		
		for(int i = 0; i < 20; i++) {
			
			for(int j = 0; j < 40; j++) {
				cDay = getCDay();
				startTime = getStartTime(cDay);
				endTime = getEndTime(startTime);
				
				classInfo = new ClassInfo();
				classInfo.setAllowance(20);
				classInfo.setcDay(cDay);
				classInfo.setStaTime(startTime);
				classInfo.setEndTime(endTime);
				classInfo.setClaKId(rand.nextInt(20) + 1 + i * 20 + 400);
				classInfo.setExpend(1);
				classInfo.setLength(60);
				classInfo.setOrderNum(0);
				classInfo.setpId(i + 1);
				classInfo.setTeaId(rand.nextInt(15) + 1 + i * 15);
				//System.out.println(classInfo);
				result.add(classInfo);
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
