package com.ryj.yuyue.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.bean.ClassInfoResult;
import com.ryj.yuyue.bean.ClassKind;
import com.ryj.yuyue.bean.ClassKindResult;
import com.ryj.yuyue.bean.ClassOrder;
import com.ryj.yuyue.dao.ClassInfoMapper;
import com.ryj.yuyue.dao.ClassKindMapper;
import com.ryj.yuyue.dao.ClassOrderMapper;

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
	@Autowired
	private ClassOrderMapper classOrderMapper;
	
	/**
	 * 增加课程余量
	 * @param classId 课程编号
	 * @param number 预约人数
	 */
	public void addClassAllowance(Integer orderId) {
		ClassOrder classOrder = classOrderMapper.selectByPrimaryKey(orderId);
		int classId = classOrder.getClaId();
		int number = classOrder.getNum();
		ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(classId);
		classInfo.setAllowance(classInfo.getAllowance() + number);
		classInfo.setOrderNum(classInfo.getOrderNum() - number);
		classInfoMapper.updateByPrimaryKeySelective(classInfo);
	}
	
	/**
	 * 减少课程余量，余量足够返回true，否则返回false
	 * @param classId 课程编号
	 * @param number 预约人数
	 * @return
	 */
	public boolean subClassAllowance(Integer classId, Integer number) {
		ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(classId);
		int allowance = classInfo.getAllowance();
		if(allowance < number) {
			return false;
		}
		classInfo.setAllowance(allowance - number);
		classInfo.setOrderNum(classInfo.getOrderNum() + number);
		classInfoMapper.updateByPrimaryKeySelective(classInfo);
		return true;
	}
	
	/**
	 * 管理员添加课程信息
	 * @param classInfo
	 */
	public void addClassInfo(ClassInfo classInfo) {
		classInfoMapper.insertSelective(classInfo);
	}
	
	/**
	 * 管理员删除课程信息
	 * @param classId
	 */
	public void deleteClassInfo(Integer classId) {
		classInfoMapper.deleteByPrimaryKey(classId);
	}
	
	/**
	 * 管理员更新课程信息
	 * @param classInfo
	 */
	public void updateClassInfo(ClassInfo classInfo) {
		classInfoMapper.updateByPrimaryKeySelective(classInfo);
	}
	
	/**
	 * 根据课程编号，课程种类，场地，教师，上课时间，课程属性查询课程信息
	 * @param classId 课程编号
	 * @param classKindId 课程种类编号
	 * @param placeId 地点编号
	 * @param teacherId 教师编号
	 * @param before 大于等于此日期
	 * @param after 小于等于此日期
	 * @param isPub 是否公开, null代表全部查看
	 */
	public List<ClassInfoResult> getClassInfo(
			Integer classId, Integer classKindId, Integer placeId, 
			Integer teacherId, Date before, Date after, 
			Integer isPub) {
		
		return classInfoMapper.getClassInfo(
				classId, classKindId, placeId, teacherId, before, 
				after, isPub);
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
	 * @param classKId 课程种类编号
	 * @param managerId 管理员编号
	 * @param kName 课程名
	 * @param property 团课或私教
	 */
	public List<ClassKindResult> getClassKind(
			Integer classKId, Integer managerId, String kName, 
			String property) {
		
		return classKindMapper.getClassKind(
				classKId, managerId, kName, property);
	}
}