package com.ryj.yuyue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.CardInfo;
import com.ryj.yuyue.bean.CardInfoResult;
import com.ryj.yuyue.bean.CardKind;
import com.ryj.yuyue.bean.CardKindResult;
import com.ryj.yuyue.dao.CardInfoMapper;
import com.ryj.yuyue.dao.CardKindMapper;

/**
 * 处理和会员卡（除订单之外）的所有业务
 * 
 * 用户：
 * 1. 添加会员卡
 * 2. 查询会员卡
 * 3. 查看会员卡种类
 * 
 * 管理员
 * 1. 添加，删除，修改，查询会员卡种类
 * 2. 查询会员卡
 * 
 * 管理员
 * @author Thor
 *
 */
@Service
public class CardService {

	@Autowired
	private CardInfoMapper cardInfoMapper;
	@Autowired
	private CardKindMapper cardKindMapper;
	
	/**
	 * 用户添加会员卡
	 * @param cardInfo
	 */
	public void addCardInfo(CardInfo cardInfo) {
		cardInfoMapper.insertSelective(cardInfo);
	}
	
	/**
	 * 查询会员卡
	 * @param cardKId 卡种编号
	 * @param uId 用户编号
	 * @return
	 */
	public List<CardInfoResult> getCardInfo(
			Integer cardKId, Integer uId) {
		return cardInfoMapper.getCardInfo(cardKId, uId);
	}
	
	/**
     * 查询会员卡种类
     * @param pId 场馆编号
     * @param cardKName 卡种名
     * @param capacity 容量
     * @param expend 花费
     * @return
     */
	public List<CardKindResult> getCardKind(
			Integer pId, String cardKName, Integer capacity, Integer expend) {
		return cardKindMapper.getCardKind(
				pId, cardKName, capacity, expend);
	}
	
	/**
	 * 添加会员卡种类
	 * @param cardKind
	 */
	public void addCardKind(CardKind cardKind) {
		cardKindMapper.insertSelective(cardKind);
	}
	
	/**
	 * 删除会员卡种类
	 * @param id
	 */
	public void deleteCardKind(Integer id) {
		cardKindMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 更新会员卡种类
	 * @param cardKind
	 */
	public void updateCardKind(CardKind cardKind) {
		cardKindMapper.updateByPrimaryKeySelective(cardKind);
	}
}
