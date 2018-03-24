package com.ryj.yuyue.bean;

import java.util.Date;

/**
 * 查询新闻
 * @author Thor
 *
 */
public class NewsResult {

	private Integer id;

    private Integer mId;
    
    private String mName;

    private Integer pId;
    
    private String pName;

    private String title;

    private Integer browTime;

    private Date pubTime;

    private String context;

	public NewsResult() {
		super();
	}

	public NewsResult(Integer id, Integer mId, String mName, Integer pId, String pName, String title, Integer browTime,
			Date pubTime, String context) {
		super();
		this.id = id;
		this.mId = mId;
		this.mName = mName;
		this.pId = pId;
		this.pName = pName;
		this.title = title;
		this.browTime = browTime;
		this.pubTime = pubTime;
		this.context = context;
	}

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

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		this.context = context;
	}

	@Override
	public String toString() {
		return "NewsResult [id=" + id + ", mId=" + mId + ", mName=" + mName + ", pId=" + pId + ", pName=" + pName
				+ ", title=" + title + ", browTime=" + browTime + ", pubTime=" + pubTime + ", context=" + context + "]";
	}
}
