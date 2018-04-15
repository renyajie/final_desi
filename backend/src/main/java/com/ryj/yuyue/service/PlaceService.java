package com.ryj.yuyue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryj.yuyue.bean.Place;
import com.ryj.yuyue.bean.PlaceExample;
import com.ryj.yuyue.bean.PlaceExample.Criteria;
import com.ryj.yuyue.dao.PlaceMapper;
import com.ryj.yuyue.utils.ConstantLiteral;

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
		//设置默认的图片和简介
		place.setPicUrl(ConstantLiteral.DEFAULT_IMAGE);
		if(place.getIntro() == null || place.getIntro().trim().length() == 0) {
			place.setIntro("暂无介绍");
		}
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
	public List<Place> getPlace(
			Integer id, String sName, String address, String phone) {
		PlaceExample example = new PlaceExample();
		Criteria criteria = example.createCriteria();
		if(id != null) {
			criteria.andIdEqualTo(id);
		}
		if(sName != null && sName.length() != 0) {
			criteria.andSNameLike("%" + sName +"%");
		}
		if(address != null && address.length() != 0) {
			criteria.andAddressLike("%" + address + "%");
		}
		if(phone != null && phone.length() != 0) {
			criteria.andPhoneLike("%" + phone + "%");
		}
		return placeMapper.selectByExample(example);
	}

	/**
	 * 获取一个场馆信息
	 * @param id
	 * @return
	 */
	public Place getOnePlace(Integer id) {
		return placeMapper.selectByPrimaryKey(id);
	}
}
