package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.Place;
import com.ryj.yuyue.dao.PlaceMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreatePlace {
	
	@Autowired
	private PlaceMapper placeMapper;
	
	/**
	 * 生成场馆名称和地址
	 * @return
	 * @throws IOException
	 */
	//@Test
	public List<String> getPlaceNameAndAddress() throws IOException {
		File fin = new File("E:/python-workspace/final_desi/py/data/generate/place");
		List<String> result = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream(fin);  
		   
	    //Construct BufferedReader from InputStreamReader  
	    BufferedReader br = new BufferedReader(new InputStreamReader(fis));  
	   
	    String line = null;  
	    String[] temp = null;
	    while ((line = br.readLine()) != null) {  
	        temp = line.trim().split(" ");
	        result.addAll(Arrays.asList(temp));
	    }
	   
	    br.close();
	    
	    return result;
	}
	
	/**
	 * 生成联系电话
	 * @return
	 */
	public List<String> generatePhone() {
		Set<String> result = new HashSet<String>();
		String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
		int telLength = telFirst.length;
		Random rand = new Random();
		
		//使用set生成1000个不重复的手机号
		String first = null, second = null, third = null;
		while(result.size() < 20) {
			int index = rand.nextInt(telLength);
	        first = telFirst[index];
	        second = String.valueOf(rand.nextInt(888) + 10000).substring(1);
	        third = String.valueOf(rand.nextInt(9100) + 10000).substring(1);
	        result.add(first + second + third);
	        System.out.println(first + second + third);
		}
		
		List<String> phoneList = new ArrayList<String>(result);
		
		return phoneList;
	}
	
	/**
	 * 生成场馆信息
	 * @return
	 * @throws IOException
	 */
	//@Test
	public List<Place> generatePlace() throws IOException {
		List<String> placeAndAddress = getPlaceNameAndAddress();
		List<String> phoneList = generatePhone();
		List<Place> result = new ArrayList<Place>();
		int i = 0;
		Place place = null;
		
		while(i < 20) {
			place = new Place();
			place.setPhone(phoneList.get(i));
			place.setsName(placeAndAddress.get(i * 2) + "瑜伽");
			place.setAddress(placeAndAddress.get(i * 2 + 1));
			result.add(place);
			i++;
		}
		
		return result;
	}
	
	@Test
	public void addPlace() throws IOException {
		List<Place> places = generatePlace();
		for(Place place: places) {
			placeMapper.insertSelective(place);
		}
	}
}
