package mine.model;

/**
 * Created by Thor on 2018/3/9.
 *
 * Mine页面中用户个人信息的数据模型
 */

public class UserInfoModel {

    public String iconUrl;
    public String nickname;
    public String phone;

    public UserInfoModel(String iconUrl, String nickname, String phone) {
        this.iconUrl = iconUrl;
        this.nickname = nickname;
        this.phone = phone;
    }
}
