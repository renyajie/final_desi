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
 * 4. 更新会员卡余量
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
	 * @return 新增会员卡的ID
	 */
	public int addCardInfo(CardInfo cardInfo) {
		return cardInfoMapper.insertSelective(cardInfo);
	}
	
	/**
	 * 用户更新会员卡余额，支付成功返回true，否则返回false
	 * @param cardId
	 * @param expend
	 * @return
	 */
	public boolean updateCardInfo(
			Integer cardId, int expend) {
		CardInfo cardInfo = cardInfoMapper.selectByPrimaryKey(cardId);
		int allowance = cardInfo.getAllowance();
		if(allowance < expend) {
			return false;
		}
		cardInfo.setAllowance(allowance - expend);
		cardInfoMapper.updateByPrimaryKeySelective(cardInfo);
		return true;
	}
	
	/**
	 * 查询某种会员卡的初始次数
	 * @param cardKindId 会员卡种类
	 * @return
	 */
	public int getCardKindCapacity(Integer cardKindId) {
		return cardKindMapper.
				selectByPrimaryKey(cardKindId).getCapacity();
	}
	
	/**
	 * 管理员或用户查询会员卡
	 * @param cardKId 卡种编号
	 * @param uId 用户编号
	 * @return
	 */
	public List<CardInfoResult> getCardInfo(
			Integer cardKId, Integer uId) {
		return cardInfoMapper.getCardInfo(cardKId, uId);
	}
	
	/**
     * 管理员或用户查询会员卡种类
     * @param cardKId 会员卡种类编号
     * @param pId 场馆编号
     * @param cardKName 卡种名
     * @param capacity 容量
     * @param expend 花费
     * @return
     */
	public List<CardKindResult> getCardKind(
			Integer cardKId, Integer pId, String cardKName, 
			Integer capacity, Integer expend) {
		
		return cardKindMapper.getCardKind(
				cardKId, pId, cardKName, capacity, expend);
	}
	
	/**
	 * 管理员添加会员卡种类
	 * @param cardKind
	 */
	public void addCardKind(CardKind cardKind) {
		cardKindMapper.insertSelective(cardKind);
	}
	
	/**
	 * 管理员删除会员卡种类
	 * @param id
	 */
	public void deleteCardKind(Integer id) {
		cardKindMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 管理员更新会员卡种类
	 * @param cardKind
	 */
	public void updateCardKind(CardKind cardKind) {
		cardKindMapper.updateByPrimaryKeySelective(cardKind);
	}
}
