package com.ryj.yuyue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.ryj.yuyue.bean.News;
import com.ryj.yuyue.bean.NewsResult;
import com.ryj.yuyue.service.NewsService;
import com.ryj.yuyue.utils.ConstantLiteral;
import com.ryj.yuyue.utils.Messenger;

/**
 * 负责消息通知所有的功能
 * @author Thor
 *
 */
@Controller
@RequestMapping("api/news")
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	/**
	 * 管理员或用户查看新闻
	 * @param newsId
	 * @param managerId
	 * @param placeId
	 * @param title
	 * @param before
	 * @param after
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "getNews", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getNews(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "newsId", required = false) Integer newsId,
			@RequestParam(value = "managerId", required = false) Integer managerId, 
			@RequestParam(value = "placeId", required = false) Integer placeId,
			@RequestParam(value = "title", required = false) String title,
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			@RequestParam(value = "before", required = false) Date before,
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			@RequestParam(value = "after", required = false) Date after,
			@RequestParam(value = "isPage", required = true) Integer isPage) {

		//PageHelper只会对其后紧跟的查询起作用，所以要放在查询语句的上方
		PageHelper.startPage(pn, 10);
		List<NewsResult> result = newsService.getNewsList(
				newsId, managerId, placeId, title, before, after);
		//判断是否需要分页
		if(isPage == 1) {
			PageInfo page = new PageInfo(result, ConstantLiteral.PAGE_SIZE);
			return Messenger.success().add("pageInfo", page);
		}
		
		return Messenger.success().add("info", result);
	}
	
	/**
	 * 获取一个新闻消息
	 * @param newsId
	 * @return
	 */
	@RequestMapping(value = "getOneNews", method = RequestMethod.GET)
	@ResponseBody
	public Messenger getOneNews(
			@RequestParam(value = "newsId", required = true) Integer newsId) {

		return Messenger.success().add("info", 
				newsService.getNewsById(newsId));
	}
	
	/**
	 * 管理员添加新闻
	 * @param news
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "addOneNews", method = RequestMethod.POST)
	@ResponseBody
	public Messenger addOneNews(
			@RequestBody @Valid News news, 
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
		
		newsService.addOneNews(news);
		return Messenger.success();
	}

	/**
	 * 管理员删除新闻
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteNews", method = RequestMethod.DELETE)
	@ResponseBody
	public Messenger deleteNews(
			@RequestParam(value = "id", required = true) Integer id) {
		
		newsService.deleteNews(id);
		return Messenger.success();
	}

	/**
	 * 管理员更新新闻
	 * @param news
	 * @param syntaxResult
	 * @return
	 */
	@RequestMapping(value = "updateNews", method = RequestMethod.PUT)
	@ResponseBody
	public Messenger updateNews(
			@RequestBody @Valid News news, 
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
		
		newsService.updateNews(news);
		return Messenger.success();
	}

}
