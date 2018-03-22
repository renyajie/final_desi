package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.ClassOrder;
import com.ryj.yuyue.bean.ClassOrderExample;
import com.ryj.yuyue.bean.ClassOrderResult;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassOrderMapper {
    long countByExample(ClassOrderExample example);

    int deleteByExample(ClassOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassOrder record);

    int insertSelective(ClassOrder record);

    List<ClassOrder> selectByExample(ClassOrderExample example);

    ClassOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassOrder record, @Param("example") ClassOrderExample example);

    int updateByExample(@Param("record") ClassOrder record, @Param("example") ClassOrderExample example);

    int updateByPrimaryKeySelective(ClassOrder record);

    int updateByPrimaryKey(ClassOrder record);
    
    /**
	 * 用户或管理员查询订单，可多条件查询
	 * @param placeId 场馆id
     * @param classId 课程id
     * @param userId 用户编号
     * @param cardId 会员卡编号
     * @param before 大于等于此时间
     * @param after 小于等于此时间
     * @return
	 */
    List<ClassOrderResult> getClassOrder(
    		@Param("placeId") Integer placeId,
    		@Param("classId") Integer classId,
    		@Param("userId") Integer userId,
    		@Param("cardId") Integer cardId,
    		@Param("before") Date before,
    		@Param("after") Date after);
}