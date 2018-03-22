package com.ryj.yuyue.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.ClassInfoResult;
import com.ryj.yuyue.bean.ClassKind;
import com.ryj.yuyue.bean.ClassKindResult;
import com.ryj.yuyue.dao.ClassInfoMapper;
import com.ryj.yuyue.dao.ClassKindMapper;

/**
 * 有关课程的所有业务
 * 
 * 用户：
 * 1. 查看课程安排
 * 
 * 管理员：
 * 1. 添加，查看，删除，修改课程种类
 * 2. 添加，查看，删除，修改课程安排
 * 
 * @author Thor
 *
 */
@Service
public class ClassService {

	@Autowired
	private ClassInfoMapper classInfoMapper;
	@Autowired
	private ClassKindMapper classKindMapper;
	
	/**
	 * 用户查看课程安排
	 * @param placeId 瑜伽馆编号
	 * @param classDay 上课日期
	 * @return
	 */
	public List<ClassInfoResult> getClassForUser(
			Integer placeId, Date classDay) {
		return classInfoMapper.getClassForUser(placeId, classDay);
	}
	
	/**
	 * 管理员添加课程种类
	 * @param classKind
	 */
	public void addClassKind(ClassKind classKind) {
		classKindMapper.insertSelective(classKind);
	}
	
	/**
	 * 管理员删除课程种类
	 * @param id
	 */
	public void deleteClassKind(Integer id) {
		classKindMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 管理员更新课程种类
	 * @param classKind
	 */
	public void updateClassKind(ClassKind classKind) {
		classKindMapper.updateByPrimaryKeySelective(classKind);
	}
	
	/**多
	 * 管理员根据课程名，课程属性多条件查询所在场馆的课程信息
	 * @param managerId 管理员编号
	 * @param kName 课程名
	 * @param property 团课或私教
	 */
	public List<ClassKindResult> getClassKind(
			Integer managerId, String kName, String property) {
		return classKindMapper.getClassKind(managerId, kName, property);
	}
	
	/**
	 * 管理员根据课程中种类查看具体的信息
	 * @param id
	 * @return
	 */
	public ClassKindResult getClassKindById(Integer id) {
		return classKindMapper.getClassKindById(id);
	}
}
