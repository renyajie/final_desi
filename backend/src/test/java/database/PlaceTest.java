package database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.service.PlaceService;

/**
 * 和场馆有关的所有测试
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PlaceTest {

	private static final Logger logger = 
			LoggerFactory.getLogger(PlaceTest.class);
	
	@Autowired
	private PlaceService placeService;
	
	@Test
	public void getPlaceTest() throws Exception {
		logger.info("getPlaceTest: {}", 
				placeService.getPlace(
						1,
						null,
						null,
						null));
	}
}
