package python;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ryj.yuyue.utils.CallPython;

public class RecommandTest {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(RecommandTest.class);
	
	@Test
	public void getPeopleClassRecommandTest() {
		logger.info(
				"call getPeopleClassRecommandTest: " + 
				CallPython.getPeopleClassRecommand(1));
	}
	
	@Test
	public void getIndividualRecommandList() {
		logger.info(
				"call getIndividualRecommandList: " + 
				CallPython.getIndividualClassRecommand(1));
	}
}
