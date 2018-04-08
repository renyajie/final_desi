package com.ryj.yuyue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryj.yuyue.service.RecommandService;
import com.ryj.yuyue.utils.Messenger;

/**
 * 进行来推荐算法有关的交互
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
