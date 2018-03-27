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
	public void addOneNews(News news) {
		news.setPubTime(new Date());
		news.setBrowTime(0);
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
	 * @param newsId 新闻编号
	 * @param managerId 管理员编号
	 * @param placeId 场馆编号
	 * @param title 标题
	 * @param before 大于等于此日期
	 * @param after 小于等于此日期
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<NewsResult> getNewsList(
			Integer newsId,
			Integer managerId, 
			Integer placeId,
			String title,
			Date before,
			Date after) {
		
		List<NewsResult> result = newsMapper.getNewsList(
				newsId, managerId, placeId, title, before, after);
		for(NewsResult newsResult: result) {
    		Date d = newsResult.getPubTime();
    		d.setHours(d.getHours() + 8);
    		newsResult.setPubTime(d);
    	}
		return result;
	}
	
	/**
	 * 获取某个新闻消息
	 * @param id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public NewsResult getNewsById(Integer id) {
		NewsResult result = newsMapper.getNewsById(id);
		Date d = result.getPubTime();
		d.setHours(d.getHours() + 8);
		result.setPubTime(d);
		return result;
	}

}
