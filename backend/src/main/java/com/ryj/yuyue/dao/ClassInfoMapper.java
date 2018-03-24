package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.bean.ClassInfoExample;
import com.ryj.yuyue.bean.ClassInfoResult;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassInfoMapper {
    long countByExample(ClassInfoExample example);

    int deleteByExample(ClassInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    List<ClassInfo> selectByExample(ClassInfoExample example);

    ClassInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    int updateByExample(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);
    
    /**
	 * 根据课程编号，课程种类，场地，教师，上课时间，课程属性查询课程信息
	 * @param classId 课程编号
	 * @param classKindId 课程种类编号
	 * @param placeId 地点编号
	 * @param teacherId 教师编号
	 * @param before 大于等于此日期
	 * @param after 小于等于此日期
	 * @param isPub 是否公开
	 */
    List<ClassInfoResult> getClassInfo(
    		@Param("classId") Integer classId, 
    		@Param("classKindId") Integer classKindId, 
    		@Param("placeId") Integer placeId, 
    		@Param("teacherId") Integer teacherId, 
    		@Param("before") Date before, 
    		@Param("after") Date after,
    		@Param("isPub") Integer isPub);
}