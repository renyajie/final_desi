package com.ryj.yuyue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.Place;
import com.ryj.yuyue.bean.PlaceExample;
import com.ryj.yuyue.bean.PlaceExample.Criteria;
import com.ryj.yuyue.dao.PlaceMapper;

/**
 * 和场馆有关的所有业务
 * 1. 场馆的添加，删除，修改，查询
 * @author Thor
 *
 */
@Service
public class PlaceService {

	@Autowired
	private PlaceMapper placeMapper;
	
	/**
	 * 添加场馆
	 * @param place
	 */
	public void addPlace(Place place) {
		placeMapper.insertSelective(place);
	}
	
	/**
	 * 删除场馆
	 * @param placeId
	 */
	public void deletePlace(Integer placeId) {
		placeMapper.deleteByPrimaryKey(placeId);
	}
	
	/**
	 * 更新场馆
	 * @param place
	 */
	public void updatePlace(Place place) {
		placeMapper.updateByPrimaryKeySelective(place);
	}
	
	/**
	 * 查询场馆信息
	 * @param id 场馆编号
	 * @param placeName 场馆名称
	 * @return 
	 */
	public List<Place> getPlace(Integer id, String placeName) {
		PlaceExample example = new PlaceExample();
		Criteria criteria = example.createCriteria();
		if(id != null) {
			criteria.andIdEqualTo(id);
		}
		if(placeName != null && placeName.length() != 0) {
			criteria.andSNameLike(placeName);
		}
		return placeMapper.selectByExample(example);
	}
}
