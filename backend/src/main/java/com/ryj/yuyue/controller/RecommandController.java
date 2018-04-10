package com.ryj.yuyue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryj.yuyue.bean.Score;
import com.ryj.yuyue.service.OrderService;
import com.ryj.yuyue.service.RecommandService;
import com.ryj.yuyue.service.ScoreService;
import com.ryj.yuyue.utils.Messenger;

/**
 * 进行来推荐算法有关的交互
 * 0. 用户评价课程
 * 1. 生成团课推荐列表
 * 2. 生成私教推荐列表
 * 3. 保存预约记录(用于推荐算法)
 * @author Thor
 *
 */
@Controller
@RequestMapping("api/recommand")
public class RecommandController {

	@Autowired
	private RecommandService recommandService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 用户评价订单，新增评分记录
	 * @param score
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addScore", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addScore(
			@RequestBody @Valid Score score, 
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
		
		//修改订单的评价状态
		orderService.updateOrderScoreStatus(score.getOrderId());
		//向评价表中加入新纪录
		scoreService.insertScore(score);
		return Messenger.success();
	}
	
	/**
	 * 获取课程评价
	 * @param userId 用户编号
	 * @param classKId 课程种类编号
	 * @return
	 */
	@RequestMapping(value = "getScore", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getScore(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "classKId", required = false) Integer classKId) {
		
		return Messenger.success()
				.add("info", scoreService.getScore(classKId, userId));
	}
	
	/**
	 * 获取某个用户的团课推荐结果
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "getPeopleClassRecommand", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getPeopleClassRecommand(
			@RequestParam(value = "userId", required = true) Integer userId) {
		
		return Messenger.success()
				.add("info", recommandService.getPeopleClassRecommand(userId));
	}
	
	/**
	 * 获取某个用户的私教推荐结果
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "getIndividualClassRecommand", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getIndividualClassRecommand(
			@RequestParam(value = "userId", required = true) Integer userId) {
		
		return Messenger.success()
				.add("info", recommandService.getIndividualClassRecommand(userId));
	}
}
