package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.Teacher;
import com.ryj.yuyue.bean.TeacherExample;
import com.ryj.yuyue.bean.TeacherResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    long countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByExample(TeacherExample example);

    Teacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    /**
	 * 查询教师信息
	 * @param pId 场馆编号
	 * @param teacherName 教师名称
	 * @return
	 */
	List<TeacherResult> getTeacher(
			@Param("pId") Integer pId, 
			@Param("teacherName") String teacherName);
}