package bean;

import java.util.Date;

/**
 * 课程信息
 * Created by Thor on 2018/4/4.
 */

public class ClassInfo {

    private Integer id;

    private Integer claKId;

    private String claKName;

    private Integer pId;

    private String pName;

    private Integer teaId;

    private String teaName;

    private String intro;

    private Date cDay;

    private Date staTime;

    private Date endTime;

    private Integer length;

    private Integer allowance;

    private Integer orderNum;

    private Integer expend;

    private String property;

    //TODO 接收到服务器数据后，添加Status属性
    private Integer status;

    private Integer difficulty;

    public ClassInfo() {
        super();
    }

    public ClassInfo(Integer id, Integer claKId, String claKName, Integer pId, String pName, Integer teaId,
                           String teaName, Date cDay, Date staTime, Date endTime, Integer length, Integer allowance, Integer orderNum,
                           Integer expend, String property, String intro) {
        super();
        this.id = id;
        this.claKId = claKId;
        this.claKName = claKName;
        this.pId = pId;
        this.pName = pName;
        this.teaId = teaId;
        this.teaName = teaName;
        this.cDay = cDay;
        this.staTime = staTime;
        this.endTime = endTime;
        this.length = length;
        this.allowance = allowance;
        this.orderNum = orderNum;
        this.expend = expend;
        this.property = property;
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaKId() {
        return claKId;
    }

    public void setClaKId(Integer claKId) {
        this.claKId = claKId;
    }

    public String getClaKName() {
        return claKName;
    }

    public void setClaKName(String claKName) {
        this.claKName = claKName;
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

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public Date getcDay() {
        return cDay;
    }

    public void setcDay(Date cDay) {
        this.cDay = cDay;
    }

    public Date getStaTime() {
        return staTime;
    }

    public void setStaTime(Date staTime) {
        this.staTime = staTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getAllowance() {
        return allowance;
    }

    public void setAllowance(Integer allowance) {
        this.allowance = allowance;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getExpend() {
        return expend;
    }

    public void setExpend(Integer expend) {
        this.expend = expend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "ClassInfo [id=" + id + ", claKId=" + claKId + ", claKName=" + claKName + ", pId=" + pId
                + ", pName=" + pName + ", teaId=" + teaId + ", teaName=" + teaName + ", cDay=" + cDay + ", staTime="
                + staTime + ", endTime=" + endTime + ", length=" + length + ", allowance=" + allowance + ", orderNum="
                + orderNum + ", expend=" + expend + ", property=" + property + "]";
    }

}
