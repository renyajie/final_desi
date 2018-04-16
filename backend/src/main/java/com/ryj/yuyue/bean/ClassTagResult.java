package com.ryj.yuyue.bean;

/**
 * 查询课程标签
 * @author Thor
 *
 */
public class ClassTagResult {
	private Integer id;

    private Integer relaxed;

    private Integer intense;

    private Integer common;

    private Integer recovery;

    private Integer enhance;

    private Integer nurse;

    private Integer consume;

    private Integer classKId;
    
    private String property;

    private String claKName;

    private Integer difficulty;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRelaxed() {
		return relaxed;
	}

	public void setRelaxed(Integer relaxed) {
		this.relaxed = relaxed;
	}

	public Integer getIntense() {
		return intense;
	}

	public void setIntense(Integer intense) {
		this.intense = intense;
	}

	public Integer getCommon() {
		return common;
	}

	public void setCommon(Integer common) {
		this.common = common;
	}

	public Integer getRecovery() {
		return recovery;
	}

	public void setRecovery(Integer recovery) {
		this.recovery = recovery;
	}

	public Integer getEnhance() {
		return enhance;
	}

	public void setEnhance(Integer enhance) {
		this.enhance = enhance;
	}

	public Integer getNurse() {
		return nurse;
	}

	public void setNurse(Integer nurse) {
		this.nurse = nurse;
	}

	public Integer getConsume() {
		return consume;
	}

	public void setConsume(Integer consume) {
		this.consume = consume;
	}

	public Integer getClassKId() {
		return classKId;
	}

	public void setClassKId(Integer classKId) {
		this.classKId = classKId;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getClaKName() {
		return claKName;
	}

	public void setClaKName(String claKName) {
		this.claKName = claKName;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
    
    
}
