package com.ryj.yuyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryj.yuyue.bean.CardInfo;
import com.ryj.yuyue.bean.CardInfoResult;
import com.ryj.yuyue.bean.CardOrder;
import com.ryj.yuyue.bean.CardOrderResult;
import com.ryj.yuyue.bean.ClassInfoResult;
import com.ryj.yuyue.bean.ClassOrder;
import com.ryj.yuyue.bean.ClassOrderResult;
import com.ryj.yuyue.service.CardService;
import com.ryj.yuyue.service.ClassService;
import com.ryj.yuyue.service.OrderService;
import com.ryj.yuyue.utils.ConstantLiteral;
import com.ryj.yuyue.utils.Messenger;

/**
 * 处理预约课程的功能 
 * 1. 用户查看预约信息，预约课程，取消预约
 * 2. 管理员查看预约信息
 * 
 * @author Thor
 *
 */
@Controller
@RequestMapping("api/order")
public class OrderController {

	@Autowired
	private ClassService classService;
	@Autowired
	private CardService cardService;
	@Autowired
	private OrderService orderService;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	/**
	 * 根据课程编号，课程种类，场地，教师，上课时间，课程属性查询课程信息
	 * @param classId 课程编号
	 * @param classKindId 课程种类编号
	 * @param placeId 地点编号
	 * @param teacherId 教师编号
	 * @param before 大于等于此日期
	 * @param after 小于等于此日期
	 * @param isPub 是否公开
	 * @param isPage 是否分页
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "getClassInfo", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getClassInfo(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "classId", required = false) Integer classId,
			@RequestParam(value = "classKId", required = false) Integer classKId,
			@RequestParam(value = "placeId", required = false) Integer placeId,
			@RequestParam(value = "teacherId", required = false) Integer teacherId,
			@RequestParam(value = "teaName", required = false) String teaName,
			@DateTimeFormat(pattern = "yyyy-MM-dd") 
			@RequestParam(value = "before", required = false) Date before,
			@DateTimeFormat(pattern = "yyyy-MM-dd") 
			@RequestParam(value = "after", required = false) Date after,
			@RequestParam(value = "property", required = false) String property,
			@RequestParam(value = "isPage", required = true) Integer isPage) {

		//PageHelper只会对其后紧跟的查询起作用，所以要放在查询语句的上方
		PageHelper.startPage(pn, 9);
		List<ClassInfoResult> result = classService.getClassInfo(
				classId, classKId, placeId, teacherId, 
				teaName, before, after, property);
		//判断是否需要分页
		if(isPage == 1) {
			PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
			return Messenger.success().add("pageInfo", page);
		}
		
		return Messenger.success().add("info", result);
	}

	/**
	 * 用户预约课程
	 * @param classOrder 预约信息
	 * @param syntaxResult 校验信息
	 * @return
	 */
	@RequestMapping(value = "userOrderClass", method = RequestMethod.POST)
	@ResponseBody
	public Messenger userOrderClass(
			@RequestBody @Valid ClassOrder classOrder, 
			BindingResult syntaxResult) {
		
		logger.info("userOrderClass, classOrder is {}", classOrder.toString());

		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		//若格式正确，判断会员卡余额是否充足
		if(!cardService.updateCardInfo(classOrder.getCardId(), classOrder.getExpend())) {
			return Messenger.fail().add("info", "会员卡余额不足");
		}
		//判断课程余量是否充足
		if(!classService.subClassAllowance(classOrder.getClaId(), classOrder.getNum())) {
			return Messenger.fail().add("info", "课程余额不足");
		}
		//设置订单时间，保存订单记录
		classOrder.setOrdTime(new Date());
		orderService.addOrderClassRecored(classOrder);
		return Messenger.success();
	}
	
	/**
	 * 更新会员卡的余额次数，加上原始次数
	 * @param cardId 会员卡编号
	 * @return
	 */
	@RequestMapping(value = "updateCardCapacity", method = RequestMethod.GET)
	@ResponseBody
	public Messenger updateCardCapacity(
			@RequestParam(value = "cardId", required = true) Integer cardId) {
		
		CardInfoResult cardInfo = cardService.getOneCardInfo(cardId);
		
		//添加购买记录
		CardOrder cardOrder = new CardOrder();
		//保存会员卡编号，日期，保存会员卡订单记录
		cardOrder.setuId(cardInfo.getuId());
		cardOrder.setCardId(cardInfo.getId());
		cardOrder.setOrdTime(new Date());
		cardOrder.setCardKId(cardInfo.getCardKId());
		orderService.addCardOrder(cardOrder);
		
		return Messenger.success().add(
				"info", cardService.updateCardCapacity(cardId));
	}
	
	
	/**
	 * 检查用户是否拥有对应的会员卡种类
	 * @param userId 用户编号
	 * @param cardKId 会员卡种类编号
	 * @return 拥有返回1，不拥有返回0
	 */
	@RequestMapping(value = "checkCardIsExistOrNot", method = RequestMethod.GET)
	@ResponseBody
	public Messenger checkCardIsExistOrNot(
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "cardKId", required = true) Integer cardKId) {
		
		return Messenger.success().add("info", 
				cardService.checkCardIsExistOrNot(userId, cardKId));
	}
	
	@RequestMapping(value = "cancelClassOrder", method = RequestMethod.GET)
	@ResponseBody
	public Messenger cancelClassOrder(
			@RequestParam(value = "orderId", required = true) Integer orderId) {
		classService.addClassAllowance(orderId);
		orderService.deleteOrderClassRecord(orderId);
		return Messenger.success();
	}
	
	/**
	 * 用户或管理员查询订单，可多条件查询
	 * @param placeId
	 * @param classId
	 * @param userId
	 * @param cardId
	 * @param before
	 * @param after
	 * @param isScore
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "getClassOrder", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getClassOrder(
			@RequestParam(value = "pn", defaultValue = "1" ) Integer pn,
			@RequestParam(value = "orderId", required=false) Integer orderId, 
			@RequestParam(value = "placeId", required=false) Integer placeId, 
			@RequestParam(value = "classId", required=false)  Integer classId,
			@RequestParam(value = "classKId", required=false)  Integer classKId,
			@RequestParam(value = "userId", required=false)  Integer userId,
			@RequestParam(value = "cardId", required=false)  Integer cardId, 
			@DateTimeFormat(pattern="yyyy-MM-dd")
			@RequestParam(value = "before", required=false) Date before, 
			@DateTimeFormat(pattern="yyyy-MM-dd")
			@RequestParam(value = "after", required=false) Date after,
			@RequestParam(value = "property", required=false) String property,
			@RequestParam(value = "isScore", required=false) Integer isScore,
			@RequestParam(value = "isPage", required = true) Integer isPage) {
		
		//PageHelper只会对其后紧跟的查询起作用，所以要放在查询语句的上方
		PageHelper.startPage(pn, 10);
		List<ClassOrderResult> result = orderService.getClassOrder(
				orderId, placeId, classId, classKId, 
				userId, cardId, before, after, property, isScore);
		//判断是否需要分页
		if(isPage == 1) {
			PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
			return Messenger.success().add("pageInfo", page);
		}
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 管理员或用户查看会员卡订单，可多条件查询
	 * @param placeId
	 * @param classId
	 * @param userId
	 * @param cardId
	 * @param before
	 * @param after
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "getCardOrder", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getCardOrder(
			@RequestParam(value = "pn", defaultValue = "1" ) Integer pn,
			@RequestParam(value = "managerId", required=false)  Integer managerId,
			@RequestParam(value = "userId", required=false)  Integer userId,
			@RequestParam(value = "userName", required=false)  String userName,
			@RequestParam(value = "cardKId", required=false)  Integer cardKId, 
			@DateTimeFormat(pattern="yyyy-MM-dd")
			@RequestParam(value = "before", required=false) Date before, 
			@DateTimeFormat(pattern="yyyy-MM-dd")
			@RequestParam(value = "after", required=false) Date after,
			@RequestParam(value = "isPage", required = true) Integer isPage) {
		
		//PageHelper只会对其后紧跟的查询起作用，所以要放在查询语句的上方
		PageHelper.startPage(pn, 10);
		List<CardOrderResult> result = orderService.getCardOrder(
				managerId, userId, userName, cardKId, before, after);
		//判断是否需要分页
		if(isPage == 1) {
			PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
			return Messenger.success().add("pageInfo", page);
		}
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 用户购买会员卡
	 * @param cardOrder
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addCardOrder", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addCardOrder(
			@RequestBody @Valid CardOrder cardOrder, 
			BindingResult syntaxResult) {

		// 校验字段格式
		if (syntaxResult.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = syntaxResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Messenger.fail().add("errorFields", map);
		}
		
		//生成新的会员卡，并保存记录
		int cardKId = cardOrder.getCardKId();
		CardInfo cardInfo = new CardInfo();
		cardInfo.setuId(cardOrder.getuId());
		cardInfo.setCardKId(cardKId);
		cardInfo.setAllowance(cardService.getCardKindCapacity(cardKId));
		int cardId = cardService.addCardInfo(cardInfo);
		
		//保存会员卡编号，日期，保存会员卡订单记录
		cardOrder.setCardId(cardId);
		cardOrder.setOrdTime(new Date());
		orderService.addCardOrder(cardOrder);
		return Messenger.success();
	}
	
	/**
	 * 
	 * 删除约课订单，可以根据参数形式批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteClassOrder", method=RequestMethod.POST)
	public Messenger deleteClassOrder(
			@RequestParam("ids") String ids){
		logger.info("deleteClassOrder: ids is {}", ids);
		//判断为多个删除还是单个删除
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			orderService.deleteOrderInBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			orderService.deleteOrderClassRecord(id);
		}
		return Messenger.success().add("info", "删除成功");
	}
	
	/**
	 * 删除会员卡订单
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteCardOrder", method=RequestMethod.POST)
	public Messenger deleteCardOrder(
			@RequestParam("ids") String ids){
		logger.info("deleteCardOrder: ids is {}", ids);
		//判断为多个删除还是单个删除
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			orderService.deleteCardOrderInBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			orderService.deleteCardOrderRecord(id);
		}
		return Messenger.success().add("info", "删除成功");
	}
}
