package com.ryj.yuyue.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class News {
    private Integer id;

    @NotNull(message="管理人编号不能为空")
    private Integer mId;

    @NotNull(message="场馆编号不能为空")
    private Integer pId;

    @NotNull(message="新闻标题不能为空")
    private String title;

    private Integer browTime;

    private Date pubTime;

    @NotNull(message="发布内容不能为空")
    private String context;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getBrowTime() {
        return browTime;
    }

    public void setBrowTime(Integer browTime) {
        this.browTime = browTime;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}