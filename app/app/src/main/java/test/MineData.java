package test;

import mine.model.BasicRecordModel;
import mine.model.UserInfoModel;

/**
 * Created by Thor on 2018/3/10.
 */

public class MineData {

    public static UserInfoModel userInfoModel = new UserInfoModel(
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3968877636,266043084&fm=27&gp=0.jpg",
            "雷利",
            "17826856214"
    );

    public static BasicRecordModel basicRecordModel = new BasicRecordModel(
            2,
            4,
            200
    );
}
