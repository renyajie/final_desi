package com.ryj.yuyue.bean;

import javax.validation.constraints.NotNull;

public class ClassKind {
    private Integer id;

    @NotNull(message="场馆编号不能为空")
    private Integer pId;

    @NotNull(message="课程属性不能为空")
    private String property;

    @NotNull(message="场馆名称不能为空")
    private String claKName;

    private Integer difficulty;

    @NotNull(message="课程介绍不能为空")
    private String intro;

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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public String getClaKName() {
        return claKName;
    }

    public void setClaKName(String claKName) {
        this.claKName = claKName == null ? null : claKName.trim();
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
        this.intro = intro == null ? null : intro.trim();
    }

	@Override
	public String toString() {
		return "ClassKind [id=" + id + ", pId=" + pId + ", property=" + property + ", claKName=" + claKName
				+ ", difficulty=" + difficulty + ", intro=" + intro + "]";
	}
}