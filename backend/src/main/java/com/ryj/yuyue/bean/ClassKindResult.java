package com.ryj.yuyue.bean;

/**
 * 管理员查看课程种类
 * @author Thor
 *
 */
public class ClassKindResult {
	
	private Integer id;

    private Integer pId;

    private String pName;
    
    private String property;

    private String claKName;

    private Integer difficulty;

    private String intro;

	public ClassKindResult() {
		super();
	}

	public ClassKindResult(Integer id, Integer pId, String pName, String property, String claKName, Integer difficulty,
			String intro) {
		super();
		this.id = id;
		this.pId = pId;
		this.pName = pName;
		this.property = property;
		this.claKName = claKName;
		this.difficulty = difficulty;
		this.intro = intro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Override
	public String toString() {
		return "ClassKindResult [id=" + id + ", pId=" + pId + ", pName=" + pName + ", property=" + property
				+ ", claKName=" + claKName + ", difficulty=" + difficulty + ", intro=" + intro + "]";
	}
    
    
}
