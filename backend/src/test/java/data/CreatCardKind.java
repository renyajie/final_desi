package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.CardKind;
import com.ryj.yuyue.dao.CardKindMapper;

/**
 * 添加会员卡，一个瑜伽馆一张
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreatCardKind {

	@Autowired
	private CardKindMapper cardKindMapper;
	
	/**
	 * 获取场地名称
	 * @return
	 * @throws IOException
	 */
	public List<String> getPlaceName() throws IOException {
		File fin = new File("E:/python-workspace/final_desi/py/data/generate/place");
		List<String> result = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream(fin);  
		   
	    //Construct BufferedReader from InputStreamReader  
	    BufferedReader br = new BufferedReader(new InputStreamReader(fis));  
	   
	    String line = null;  
	    String[] temp = null;
	    while ((line = br.readLine()) != null) {  
	        temp = line.trim().split(" ");
	        result.add(temp[0]);
	    }
	   
	    br.close();
	    
	    return result;
	}
	
	@Test
	public void addCardKind() throws Exception {
		
		CardKind cardKind = null;
		List<String> placeList = getPlaceName();
		
		for(int i = 0; i < 20; i++) {
			cardKind = new CardKind();
			cardKind.setpId(i + 1);
			cardKind.setExpend(100);
			cardKind.setCardKName(placeList.get(i) + "青铜卡");
			cardKind.setCapacity(100);
			cardKindMapper.insertSelective(cardKind);
		}
	}
	
}
