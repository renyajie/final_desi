package com.ryj.yuyue.bean;

/**
 * 查询会员卡信息
 * @author Thor
 *
 */
public class CardInfoResult {

	private Integer id;

    private Integer cardKId;
    
    private String cardKName;

    private Integer uId;
    
    private String uName;

    private Integer allowance;

	public CardInfoResult() {
		super();
	}

	public CardInfoResult(Integer id, Integer cardKId, String cardKName, Integer uId, String uName, Integer allowance) {
		super();
		this.id = id;
		this.cardKId = cardKId;
		this.cardKName = cardKName;
		this.uId = uId;
		this.uName = uName;
		this.allowance = allowance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCardKId() {
		return cardKId;
	}

	public void setCardKId(Integer cardKId) {
		this.cardKId = cardKId;
	}

	public String getCardKName() {
		return cardKName;
	}

	public void setCardKName(String cardKName) {
		this.cardKName = cardKName;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	@Override
	public String toString() {
		return "CardInfoResult [id=" + id + ", cardKId=" + cardKId + ", cardKName=" + cardKName + ", uId=" + uId
				+ ", uName=" + uName + ", allowance=" + allowance + "]";
	}
}
