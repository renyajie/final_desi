package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.ryj.yuyue.bean.ClassKind;

public class CreateClassKind {
	
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

	/**
	 * 获取课程名称
	 * @return
	 * @throws IOException
	 */
	public List<String> getClassName() throws IOException {
		File fin = new File("E:/python-workspace/final_desi/py/data/generate/class_name");
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
	
	@Test
	public void generateClassKind() throws IOException {
		List<String> placeList = getPlaceName();
		List<String> classList = getClassName();
		List<ClassKind> result = new ArrayList<ClassKind>();
		Random rand = new Random();
		
		String[] level = {"基础", "初级", "中级", "高级"};
		Integer[][] difficultyList = {
				{1, 2, 3, 4},
				{1, 3, 4, 5},
				{2, 3, 4, 5},
				{1, 2, 4, 5},
				{1, 2, 3, 5}};
		
		ClassKind classKind = null;
		String className = null;
		String place = null;
		int difficulty;
		
		for(int i = 0; i < 20; i++) {
			place = placeList.get(i);
			for(int j = 0; j < 5; j++) {
				className = place + classList.get(rand.nextInt(classList.size()));
				//生成随机难度列表
				difficulty = rand.nextInt(5);
				for(int k = 0; k < 4; k++) {
					classKind = new ClassKind();
					classKind.setpId(i + 1);
					classKind.setProperty("g");
					classKind.setClaKName(className + level[k]);
					classKind.setDifficulty(difficultyList[difficulty][k]);
					classKind.setIntro(
							"这是" + className + level[k] + "瑜伽， 难度为" 
									+ difficultyList[difficulty][k] + "颗星。");
					result.add(classKind);
					System.out.println(classKind);
				}
				
			}
		}
		
		//return result;
	}
}
