package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.CardOrder;
import com.ryj.yuyue.bean.CardOrderExample;
import com.ryj.yuyue.bean.CardOrderResult;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardOrderMapper {
    long countByExample(CardOrderExample example);

    int deleteByExample(CardOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardOrder record);

    int insertSelective(CardOrder record);

    List<CardOrder> selectByExample(CardOrderExample example);

    CardOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardOrder record, @Param("example") CardOrderExample example);

    int updateByExample(@Param("record") CardOrder record, @Param("example") CardOrderExample example);

    int updateByPrimaryKeySelective(CardOrder record);

    int updateByPrimaryKey(CardOrder record);
    
    /**
     * 查询购卡订单
     * @param managerId 管理员编号
     * @param userId 用户编号
     * @Param userName 姓名
     * @param cardKId 卡种编号
     * @param before 大于等于此日期
     * @param after 小于等于此日期
     * @return
     */
    List<CardOrderResult> getCardOrder(
    		@Param("managerId") Integer managerId,
    		@Param("userId") Integer userId, 
    		@Param("userName") String userName,
    		@Param("cardKId") Integer cardKId, 
    		@Param("before") Date before, 
    		@Param("after") Date after);
}