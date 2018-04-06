package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.CardKind;
import com.ryj.yuyue.bean.CardKindExample;
import com.ryj.yuyue.bean.CardKindResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardKindMapper {
    long countByExample(CardKindExample example);

    int deleteByExample(CardKindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardKind record);

    int insertSelective(CardKind record);

    List<CardKind> selectByExample(CardKindExample example);

    CardKind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardKind record, @Param("example") CardKindExample example);

    int updateByExample(@Param("record") CardKind record, @Param("example") CardKindExample example);

    int updateByPrimaryKeySelective(CardKind record);

    int updateByPrimaryKey(CardKind record);
    
    /**
     * 查询会员卡种类
     * @param cardKId 会员卡种类编号
     * @param managerId 管理员编号
     * @param placeId 场馆编号
     * @param cardKName 卡种名
     * @param capacity 容量
     * @param expend 花费
     * @return
     */
    List<CardKindResult> getCardKind(
    		@Param("cardKId") Integer cardKId, 
    		@Param("managerId") Integer managerId, 
    		@Param("placeId") Integer placeId, 
    		@Param("cardKName") String cardKName, 
    		@Param("capacity") Integer capacity, 
    		@Param("expend") Integer expend);
}