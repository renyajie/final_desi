package com.ryj.yuyue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryj.yuyue.service.AnalyseService;
import com.ryj.yuyue.utils.Messenger;

@Controller
@RequestMapping("api/analyse")
public class AnalyseController {

	@Autowired
	private AnalyseService analyseService;
	
	/**
	 * 根据时间段统计预约数量
	 * @param timeLength
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value = "getByClassTime", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getByClassTime(
			@RequestParam(value = "timeLength", required = true) Integer timeLength, 
			@RequestParam(value = "placeId", required = true) Integer placeId) {

		return Messenger.success().add("info", 
				analyseService.getByClassTime(placeId, timeLength));
	}
	
	/**
	 * 统计每天的预约量
	 * @param timeLength
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value = "getByOrderNumber", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getByOrderNumber(
			@RequestParam(value = "timeLength", required = true) Integer timeLength, 
			@RequestParam(value = "placeId", required = true) Integer placeId) {

		return Messenger.success().add("info", 
				analyseService.getByOrderNumber(placeId, timeLength));
	}
	
	/**
	 * 统计每门课程的预约量
	 * @param timeLength
	 * @param placeId
	 * @return
	 */
	@RequestMapping(value = "getByClassNumber", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getByClassNumber(
			@RequestParam(value = "timeLength", required = true) Integer timeLength, 
			@RequestParam(value = "placeId", required = true) Integer placeId) {

		return Messenger.success().add("info", 
				analyseService.getByClassNumber(placeId, timeLength));
	}
}
