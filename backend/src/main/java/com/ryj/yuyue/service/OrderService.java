package com.ryj.yuyue.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.CardOrder;
import com.ryj.yuyue.bean.CardOrderResult;
import com.ryj.yuyue.bean.ClassOrder;
import com.ryj.yuyue.bean.ClassOrderResult;
import com.ryj.yuyue.dao.CardOrderMapper;
import com.ryj.yuyue.dao.ClassOrderMapper;

/**
 * 负责所有订单业务:
 * 
 * 会员卡订单:
 * 用户：
 * 1. 添加会员卡订单
 * 2. 查询会员卡订单
 * 管理员：
 * 1. 查询会员卡订单 
 * 
 * 约课订单:
 * 用户
 * 1. 添加预课订单
 * 2. 取消预约
 * 3. 查询约课订单
 * 管理员:
 * 1. 查询约课订单
 * @author Thor
 *
 */
@Service
public class OrderService {
	
	@Autowired
	private CardOrderMapper cardOrderMapper;
	@Autowired
	private ClassOrderMapper classOrderMapper;
	
	/**
	 * 添加会员卡订单
	 * @param cardOrder
	 */
	public void addCardOrder(CardOrder cardOrder) {
		cardOrderMapper.insertSelective(cardOrder);
	}
	
	/**
     * 查询购卡订单
     * @param managerId 管理员编号
     * @param userId 用户编号
     * @param cardKId 卡种编号
     * @param userName 姓名
     * @param before 大于等于此日期
     * @param after 小于等于此日期
     * @return
     */
	@SuppressWarnings("deprecation")
	public List<CardOrderResult> getCardOrder(
			Integer managerId, Integer userId, String userName, 
			Integer cardKId, Date before, Date after) {
		List<CardOrderResult> result = cardOrderMapper.getCardOrder(
				managerId, userId, userName, cardKId, before, after);
		for(CardOrderResult cardOrder: result) {
    		Date d = cardOrder.getOrdTime();
    		d.setHours(d.getHours() + 8);
    		cardOrder.setOrdTime(d);
    	}
		return result;
	}
	
	/**
	 * 新增用户预约记录
	 * @param classOrder
	 */
	public void addOrderClassRecored(ClassOrder classOrder) {
		classOrderMapper.insertSelective(classOrder);
	}
	
	/**
	 * 删除用户预约记录
	 * @param id 订单编号
	 */
	public void deleteOrderClassRecord(Integer id) {
		classOrderMapper.deleteByPrimaryKey(id);
	}
	
	/**
     * 用户或管理员查询订单，可多条件查询
     * @param orderId 订单编号
     * @param placeId 场馆编号
     * @param classId 课程编号
     * @param classKId 课程种类
     * @param userId 用户编号
     * @param cardId 会员卡
     * @param before 大于等于此时间
     * @param after 小于等于此事件
     * @return
     */
    @SuppressWarnings("deprecation")
	public List<ClassOrderResult> getClassOrder(
  			Integer orderId, Integer placeId, Integer classId,
    		Integer classKId, Integer userId, Integer cardId,
    		Date before, Date after) {
    	
    	List<ClassOrderResult> result = classOrderMapper.getClassOrder(
    			orderId, placeId, classId, classKId, 
    			userId, cardId, before, after);
    	for(ClassOrderResult classOrder: result) {
    		Date d = classOrder.getOrdTime();
    		d.setHours(d.getHours() + 8);
    		classOrder.setOrdTime(d);
    	}
    	return result;
    }
}
