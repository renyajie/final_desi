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

import com.ryj.yuyue.bean.Manager;
import com.ryj.yuyue.dao.ManagerMapper;

/**
 * 创建管理员
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreateManager {
	
	@Autowired
	private ManagerMapper managerMapper;

	/**
	 * 获取姓氏到List中
	 * @throws IOException 
	 */
	//@Test
	public List<String> getFirstName() throws IOException {
		File fin = new File("E:/python-workspace/final_desi/py/data/generate/first_name");
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
	 * 获取名字到List中
	 * @throws IOException 
	 */
	//@Test
	public List<String> getLastName() throws IOException {
		File fin = new File("E:/python-workspace/final_desi/py/data/generate/last_name");
		List<String> result = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream(fin);  
		   
	    //Construct BufferedReader from InputStreamReader  
	    BufferedReader br = new BufferedReader(new InputStreamReader(fis));  
	   
	    String line = null;  
	    String[] temp = null;
	    while ((line = br.readLine()) != null) {  
	        temp = line.trim().split(" ");
	        //去除空行
	        if(temp.length == 1) {
	        	continue;
	        }
	        result.addAll(Arrays.asList(temp));
	    }
	   
	    br.close();
	    
	    return result;
	}
	
	/**
	 * 生成1000个用户姓名
	 * @return
	 * @throws IOException
	 */
	//@Test
	public List<String> generateManagerName() throws IOException {
		
		List<String> firstName = getFirstName();
		List<String> lastName = getLastName();
		Random rand = new Random();
		
		List<String> result = new ArrayList<String>();
		int i = 0;
		while (i < 20) {
			result.add(
					firstName.get(rand.nextInt(firstName.size()))
					+ lastName.get(rand.nextInt(lastName.size())));
			i++;
		}
		
		return result;
	}
	
	/**
	 * 获取手机号
	 * @return
	 */
	//@Test
	public List<String> generatePhone() {
		Set<String> result = new HashSet<String>();
		String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
		int telLength = telFirst.length;
		Random rand = new Random();
		
		//使用set生成1000个不重复的手机号
		String first = null, second = null, third = null;
		while(result.size() < 1000) {
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
	 * 昌盛管理员
	 * @return
	 * @throws IOException
	 */
	//@Test
	public List<Manager> generateManager() throws IOException {
		
		List<String> nameList = generateManagerName();
		List<String> phoneList = generatePhone();
		List<Manager> result = new ArrayList<Manager>();
		Random rand = new Random();
		int i = 0;
		Manager manager = null;
		
		String[] gender = {"男", "女"};
		while(i < 20) {
			manager = new Manager();
			manager.setmName(nameList.get(i));
			manager.setAccount(phoneList.get(i));
			manager.setPhone(phoneList.get(i));
			manager.setGender(gender[rand.nextInt(2)]);
			manager.setPasswd("123456");
			manager.setpId(i + 1);
			result.add(manager);
			i++;
		}
		
		return result;
	}
	
	@Test
	public void addManager() throws IOException {
		List<Manager> managerList = generateManager();
		for(Manager manager: managerList) {
			managerMapper.insertSelective(manager);
		}
	}
}
