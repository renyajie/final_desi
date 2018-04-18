package bean;

/**
 * 会员卡种类
 * Created by Thor on 2018/4/6.
 */

public class CardKind {

    private Integer id;

    private Integer pId;

    private String pName;

    private String cardKName;

    private Integer capacity;

    private Integer expend;

    private String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public CardKind() {
        super();
    }

    public CardKind(Integer id, Integer pId, String pName, String cardKName, Integer capacity, Integer expend) {
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
        return "CardKind [id=" + id + ", pId=" + pId + ", pName=" + pName + ", cardKName=" + cardKName
                + ", capacity=" + capacity + ", expend=" + expend + "]";
    }
}
