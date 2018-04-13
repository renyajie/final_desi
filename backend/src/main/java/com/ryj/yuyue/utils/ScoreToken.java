package com.ryj.yuyue.utils;

/**
 * 用户评分的排序结构，课程种类编号，课程评价数量
 * @author Thor
 *
 */
public class ScoreToken {
	
	private Integer classKindId;
	
	private Integer number;

	public ScoreToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ScoreToken(Integer classKindId, Integer number) {
		super();
		this.classKindId = classKindId;
		this.number = number;
	}

	public Integer getClassKindId() {
		return classKindId;
	}

	public void setClassKindId(Integer classKindId) {
		this.classKindId = classKindId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "ScoreToken [classKindId=" + classKindId + ", number=" + number + "]";
	}
	
}
