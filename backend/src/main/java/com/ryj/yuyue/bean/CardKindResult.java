package com.ryj.yuyue.bean;

/**
 * 查询会员卡种类
 * @author Thor
 *
 */
public class CardKindResult {

	private Integer id;

    private Integer pId;
    
    private String pName;

    private String cardKName;

    private Integer capacity;

    private Integer expend;

	public CardKindResult() {
		super();
	}

	public CardKindResult(Integer id, Integer pId, String pName, String cardKName, Integer capacity, Integer expend) {
		super();
		this.id = id;
		this.pId = pId;
		this.pName = pName;
		this.cardKName = cardKName;
		this.capacity = capacity;
		this.expend = expend;
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

	public String getCardKName() {
		return cardKName;
	}

	public void setCardKName(String cardKName) {
		this.cardKName = cardKName;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getExpend() {
		return expend;
	}

	public void setExpend(Integer expend) {
		this.expend = expend;
	}

	@Override
	public String toString() {
		return "CardKindResult [id=" + id + ", pId=" + pId + ", pName=" + pName + ", cardKName=" + cardKName
				+ ", capacity=" + capacity + ", expend=" + expend + "]";
	}
    
    
}
