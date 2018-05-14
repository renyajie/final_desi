package normal;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.dao.ClassInfoMapper;

/**
 * 和日期相关的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DateTest {
	
	@Autowired
	private ClassInfoMapper classInfoMapper;
	
	@Test
	@SuppressWarnings({ "deprecation", "static-access" })
	public void getDate() {
		Random rand = new Random();
		Calendar calendar = new GregorianCalendar();
		for(int i = 0; i < 10; i++) {
			ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(i + 1);
			Date cDay = classInfo.getcDay();
			calendar.setTime(cDay);
			calendar.add(calendar.DATE, -1);
		    Date date = calendar.getTime();
		    date.setHours(rand.nextInt(11) + 7);
		    date.setMinutes(rand.nextInt(60));
		    date.setSeconds(rand.nextInt(60));
		    System.out.println(date);
		}
		
	}
}
