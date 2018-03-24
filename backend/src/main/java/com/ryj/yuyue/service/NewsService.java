package com.ryj.yuyue.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.News;
import com.ryj.yuyue.bean.NewsResult;
import com.ryj.yuyue.dao.NewsMapper;

/**
 * 有关新闻的所有业务
 * 
 * 1. 管理员对通知添加，删除，修改，查找
 * 
 * 2. 用户查看通知
 * @author Thor
 *
 */
@Service
public class NewsService {

	@Autowired
	private NewsMapper newsMapper;
	
	/**
	 * 新增新闻
	 * @param news
	 */
	public void addNews(News news) {
		newsMapper.insertSelective(news);
	}
	
	/**
	 * 删除新闻
	 * @param id
	 */
	public void deleteNews(Integer id) {
		newsMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 更新新闻
	 * @param news
	 */
	public void updateNews(News news) {
		newsMapper.updateByPrimaryKeyWithBLOBs(news);
	}
	
	/**
	 * 查找新闻列表
	 * @param managerId 管理员编号
	 * @param placeId 场馆编号
	 * @param title 标题
	 * @param before 大于等于此日期
	 * @param after 小于等于此日期
	 * @return
	 */
	public List<NewsResult> getNewsList(
			Integer managerId, 
			Integer placeId,
			String title,
			Date before,
			Date after) {
		return newsMapper.getNewsList(
				managerId, placeId, title, before, after);
	}
	
	/**
	 * 查找单个新闻
	 * @param newsId
	 * @return
	 */
	public NewsResult getNewsById(Integer newsId) {
		return newsMapper.getNewsById(newsId);
	}
}
