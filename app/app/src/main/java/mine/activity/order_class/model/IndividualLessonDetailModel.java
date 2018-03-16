package mine.activity.order_class.model;

import mine.activity.order_class.helper.GeneralLessonDetailModel;

/**
 * Created by Thor on 2018/3/15.
 *
 * 私教约课的数据
 */

public class IndividualLessonDetailModel implements GeneralLessonDetailModel{
    public String teacherName, time, placeName;

    public IndividualLessonDetailModel(String teacherName, String time, String placeName) {
        this.teacherName = teacherName;
        this.time = time;
        this.placeName = placeName;
    }
}
