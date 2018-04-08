package mine.model;

/**
 * Created by Thor on 2018/3/9.
 *
 * Mine页面中用户个人信息的数据模型
 */

public class UserInfoModel {

    public String iconUrl;
    public String username;
    public String phone;

    public UserInfoModel() {

    }

    public UserInfoModel(String iconUrl, String username, String phone) {
        this.iconUrl = iconUrl;
        this.username = username;
        this.phone = phone;
    }
}
