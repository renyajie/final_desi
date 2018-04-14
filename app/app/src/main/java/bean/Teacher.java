package bean;

/**
 * Created by Thor on 2018/4/14.
 */

public class Teacher {

    private Integer id;

    private Integer pId;

    private String pName;

    private String teaName;

    private String phone;

    private String intro;

    private Integer age;

    private String gender;

    public Teacher() {
        super();
    }

    public Teacher(Integer id, Integer pId, String pName, String teaName, String phone, String intro) {
        super();
        this.id = id;
        this.pId = pId;
        this.pName = pName;
        this.teaName = teaName;
        this.phone = phone;
        this.intro = intro;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "TeacherResult [id=" + id + ", pId=" + pId + ", pName=" + pName + ", teaName=" + teaName + ", phone="
                + phone + ", intro=" + intro + "]";
    }
}
