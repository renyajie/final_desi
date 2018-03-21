package com.ryj.yuyue.bean;

public class ClassKind {
    private Integer id;

    private Integer pId;

    private String property;

    private String claKName;

    private Integer difficulty;

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
}