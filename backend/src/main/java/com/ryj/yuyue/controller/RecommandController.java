package com.ryj.yuyue.controller;

import java.util.ArrayList;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryj.yuyue.bean.Score;
import com.ryj.yuyue.bean.ScoreResult;
import com.ryj.yuyue.service.OrderService;
import com.ryj.yuyue.service.RecommandService;
import com.ryj.yuyue.service.ScoreService;
import com.ryj.yuyue.utils.ConstantLiteral;
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
	 * @param pn
	 * @param userId 用户编号
	 * @param classKId 课程种类编号
	 * @param isPage 是否分页
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "getScore", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getScore(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "classKId", required = false) Integer classKId,
			@RequestParam(value = "placeId", required = false) Integer placeId,
			@RequestParam(value = "isPage", required = true) Integer isPage) {
		
		
		List<ScoreResult> result = new ArrayList<ScoreResult>();
		
		//判断是否需要分页
		if(isPage == 1) {
			PageHelper.startPage(pn, 5);
			result = scoreService.getScore(classKId, userId, placeId);
			PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
			return Messenger.success().add("pageInfo", page);
		}
		else {
			result = scoreService.getScore(classKId, userId, placeId);
		}
		
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 获取某个用户的推荐结果
	 * @param userId 用户编号
	 * @param isPeople 是否为团课
	 * @return
	 */
	@RequestMapping(value = "getClassRecommand", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getClassRecommand(
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "isPeople", required = true) Integer isPeople) {
		
		return Messenger.success()
				.add("info", recommandService.getRecommandResult(userId, isPeople));
	}
}
