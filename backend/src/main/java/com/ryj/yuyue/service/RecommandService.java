package com.ryj.yuyue.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.ClassKindResult;
import com.ryj.yuyue.dao.ClassKindMapper;
import com.ryj.yuyue.utils.CallPython;

/**
 * 和推荐算法有关的功能
 * 1. 获取团课推荐列表
 * 2. 获取私教推荐列表
 * 3. 保存推荐记录(用于推荐算法)
 * @author Thor
 *
 */
@Service
public class RecommandService {
	
	@Autowired
	private ClassKindMapper classKindMapper;
	
	/**
	 * 获取某个用户的团课推荐结果
	 * @param userId 用户编号
	 * @return
	 */
	public List<ClassKindResult> getPeopleClassRecommand(Integer userId) {
		
		//调用python算法，获取推荐结果
		List<Integer> idList = CallPython.getPeopleClassRecommand(userId);
		
		//查询数据库，获取指定编号的团课
		if(idList.size() == 0) {
			return new ArrayList<ClassKindResult>();
		}
		
		return classKindMapper.getClassKindByIdList(idList);
	}
	
	/**
	 * 获取某个用户的私教推荐结果
	 * @param userId 用户编号
	 * @return
	 */
	public List<ClassKindResult> getIndividualClassRecommand(Integer userId) {
		
		//调用python算法，获取课程种类编号列表
		List<Integer> idList = CallPython.getIndividualClassRecommand(userId);
		
		//查询数据库，获取课程种类信息
		if(idList.size() == 0) {
			return new ArrayList<ClassKindResult>();
		}
		
		return classKindMapper.getClassKindByIdList(idList);
	}
	

}
