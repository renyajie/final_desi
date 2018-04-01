package database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.dao.ClassOrderMapper;

/**
 * 课程订单的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ClassOrderTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(ClassOrderTest.class);
	
	@Autowired
	private ClassOrderMapper classOrderMapper;
	
	@Test
	public void getClassOrderTest() throws Exception {
		logger.info("getClassOrderTest: {}", 
				classOrderMapper.getClassOrder(
						null,
						null,
						null, 
						null, 
						1, 
						null, 
						null, 
						null));
	}
	
	//将每一天的时间按小时划分
	@SuppressWarnings({ "deprecation", "unused" })
	@Test
	public void getClassTimeTest() {
		
		int[] result = new int[11];
		
		Date before = new Date();
		Date after = new Date();
		after.setHours(11);
		/*
		int[] hourList = {7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
		
		Calendar calendar = Calendar.getInstance();
		
		for(int i = 0; i < 3; i++) {
			logger.info("第 {} 次循环", i + 1);
			calendar.add(Calendar.DAY_OF_MONTH, -i);
			before = calendar.getTime();
			after = calendar.getTime();
			before.setMinutes(0);
			before.setSeconds(0);
			after.setMinutes(0);
			after.setSeconds(0);
			for(int j = 0; j < 11; j++) {      
				logger.info("内部第 {} 次循环", j + 1);
		        before.setHours(hourList[j]);
				logger.info("before hour is {}", before);
				after.setHours(hourList[j] + 1);
				logger.info("after hour is {}", after);
				result[j] = result[j] + 1;
			}
		}
		
		logger.info("result is {}", result);
		*/
        
		
		logger.info("getOrderNumberByHourTest: {}", 
				classOrderMapper.getOrderNumberByHour(1, before, after));
	}
	
	@Test
	public void dayFormatTest() {
		Date before = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM月dd号");
		String day = dateFormat.format(before);
		
		logger.info("day is {}", day);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getClassNumberTest() {
		
		Date before = new Date();
		Date after = new Date();
		after.setHours(11);
		
		logger.info("get class number is {}",
				classOrderMapper.getOrderNumberByClass(1, before, after));
	}
}
