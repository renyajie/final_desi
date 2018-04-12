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

import com.ryj.yuyue.bean.User;
import com.ryj.yuyue.dao.UserMapper;

/**
 * 创建1000名系统用户
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreateUser {
	
	@Autowired
	private UserMapper userMapper;
	
	File firstName = new File("E:/python-workspace/final_desi/py/data/generate/first_name");
	
	public String cleanString(String str) {
		return str.replace("/^(/s*|/t*)*/", "").replace("/(/s*|/t*)*$/", "");
	}
	/**
	 * 读取文件内容
	 * @throws IOException
	 */
	@Test
	public void readFile() throws IOException {
		File fin = new File("E:/python-workspace/final_desi/py/data/generate/first_name");
		
		FileInputStream fis = new FileInputStream(fin);  
		   
	    //Construct BufferedReader from InputStreamReader  
	    BufferedReader br = new BufferedReader(new InputStreamReader(fis));  
	   
	    String line = null;  
	    while ((line = br.readLine()) != null) {  
	        System.out.println(line);  
	    }
	   
	    br.close();
	}
	
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
	public List<String> generateUserName() throws IOException {
		
		List<String> firstName = getFirstName();
		List<String> lastName = getLastName();
		Random rand = new Random();
		
		List<String> result = new ArrayList<String>();
		int i = 0;
		while (i < 1000) {
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
	
	//@Test
	public List<User> generateUser() throws IOException {
		List<String> nameList = generateUserName();
		List<String> phoneList = generatePhone();
		List<User> result = new ArrayList<User>();
		Random rand = new Random();
		int i = 0;
		User user = null;
		
		
		String[] gender = {"男", "女"};
		while(i < 1000) {
			user = new User();
			user.setGender(gender[rand.nextInt(2)]);
			user.setPasswd("123456");
			user.setuName(nameList.get(i));
			user.setPhone(phoneList.get(i));
			user.setAge(rand.nextInt(28) + 18);
			result.add(user);
			i++;
		}
		
		return result;
	}
	
	/**
	 * 添加1000名用户
	 * @throws IOException
	 */
	@Test
	public void addUser() throws IOException {
		List<User> users = generateUser();
		for(User user: users) {
			userMapper.insertSelective(user);
		}
	}
	
}
