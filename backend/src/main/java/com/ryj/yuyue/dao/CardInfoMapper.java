package com.ryj.yuyue.dao;

import com.ryj.yuyue.bean.CardInfo;
import com.ryj.yuyue.bean.CardInfoExample;
import com.ryj.yuyue.bean.CardInfoResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardInfoMapper {
    long countByExample(CardInfoExample example);

    int deleteByExample(CardInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardInfo record);

    int insertSelective(CardInfo record);

    List<CardInfo> selectByExample(CardInfoExample example);

    CardInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardInfo record, @Param("example") CardInfoExample example);

    int updateByExample(@Param("record") CardInfo record, @Param("example") CardInfoExample example);

    int updateByPrimaryKeySelective(CardInfo record);

    int updateByPrimaryKey(CardInfo record);
    
    /**
	 * 查询会员卡
	 * @param managerId 管理员编号
	 * @param cardKId 卡种编号
	 * @param uId 用户编号
	 * @param id 会员卡编号
	 * @return
	 */
	List<CardInfoResult> getCardInfo(
			@Param("managerId") Integer managerId,
			@Param("cardKId") Integer cardKId, 
			@Param("uId") Integer uId);
	
	/**
	 * 获取一个会员卡信息
	 * @param id
	 * @return
	 */
	CardInfoResult getOneCardInfo(
			@Param("id") Integer id);
}