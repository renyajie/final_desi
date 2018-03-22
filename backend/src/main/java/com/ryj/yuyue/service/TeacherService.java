package com.ryj.yuyue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.Teacher;
import com.ryj.yuyue.bean.TeacherResult;
import com.ryj.yuyue.dao.TeacherMapper;

/**
 * 和教师相关的所有业务
 * 
 * 1. 添加，删除，修改，查看教师信息
 * @author Thor
 *
 */
@Service
public class TeacherService {
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	/**
	 * 添加教师信息
	 * @param teacher
	 */
	public void addTeacher(Teacher teacher) {
		teacherMapper.insertSelective(teacher);
	}
	
	/**
	 * 删除教师
	 * @param id
	 */
	public void deleteTeacher(Integer id) {
		teacherMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 更新教师信息
	 * @param teacher
	 */
	public void updateTeacher(Teacher teacher) {
		teacherMapper.updateByPrimaryKeySelective(teacher);
	}
	
	/**
	 * 查询教师信息
	 * @param pId 场馆编号
	 * @param teacherName 教师名称
	 * @return
	 */
	public List<TeacherResult> getTeacher(
			Integer pId, String teacherName) {
		return teacherMapper.getTeacher(pId, teacherName);
	}

}
