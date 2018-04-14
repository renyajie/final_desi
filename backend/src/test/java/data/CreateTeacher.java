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

import com.ryj.yuyue.bean.Teacher;
import com.ryj.yuyue.dao.TeacherMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreateTeacher {
	
	@Autowired
	private TeacherMapper teacherMapper;

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
	 * 每个场馆生成个15个教师姓名
	 * @return
	 * @throws IOException
	 */
	//@Test
	public List<String> generateTeacherName() throws IOException {
		
		List<String> firstName = getFirstName();
		List<String> lastName = getLastName();
		Random rand = new Random();
		
		List<String> result = new ArrayList<String>();
		int i = 0;
		while (i < 300) {
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
		
		//使用set生成300个不重复的手机号
		String first = null, second = null, third = null;
		while(result.size() < 300) {
			int index = rand.nextInt(telLength);
	        first = telFirst[index];
	        second = String.valueOf(rand.nextInt(888) + 10000).substring(1);
	        third = String.valueOf(rand.nextInt(9100) + 10000).substring(1);
	        result.add(first + second + third);
		}
		
		List<String> phoneList = new ArrayList<String>(result);
		
		return phoneList;
	}
	
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
	
	//@Test
	public List<Teacher> generateTeacher() throws IOException {
		
		List<String> placeName = getPlaceName();
		List<String> phoneList = generatePhone();
		List<String> nameList = generateTeacherName();
		String place = null;
		List<Teacher> result = new ArrayList<Teacher>();
		Teacher teacher = null;
		
		for(int i = 0; i < 20; i++) {
			place = placeName.get(i);
			for(int j = 0; j < 15; j++) {
				teacher = new Teacher();
				teacher.setPhone(phoneList.get(i * 15 + j));
				teacher.setpId(i + 1);
				teacher.setTeaName(nameList.get(i * 15 + j));
				teacher.setIntro("你好，我是来自" + place
				+ "瑜伽馆的" + nameList.get(i * 15 + j) + "老师，请多指教。");
				//System.out.println(teacher);
				result.add(teacher);
			}
		}
		
		return result;
	}
	
	@Test
	public void addTeacher() throws IOException {
		List<Teacher> teacherList = generateTeacher();
		for(Teacher teacher: teacherList) {
			teacherMapper.insertSelective(teacher);
		}
	}
	
	/**
	 * 为所有的教师添加上年龄和性别
	 */
	@Test
	public void addAgeAndGender() {
		Random rand = new Random();
		String[] gender = {"男", "女"};
		List<Teacher> teacherList = teacherMapper.selectByExample(null);
		
		for(Teacher teacher: teacherList) {
			teacher.setAge(rand.nextInt(14) + 22);
			teacher.setGender(gender[rand.nextInt(2)]);
			teacherMapper.updateByPrimaryKeySelective(teacher);
		}
	}
}
