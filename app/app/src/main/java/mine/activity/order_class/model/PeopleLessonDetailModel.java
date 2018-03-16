package mine.activity.order_class.model;

import mine.activity.order_class.helper.GeneralLessonDetailModel;

/**
 * Created by Thor on 2018/3/15.
 *
 * 团课约课的数据
 */

public class PeopleLessonDetailModel implements GeneralLessonDetailModel {
    public String className, time, placeName, classroom;

    public PeopleLessonDetailModel(String className, String time,
                                   String placeName, String classroom) {
        this.className = className;
        this.time = time;
        this.placeName = placeName;
        this.classroom = classroom;
    }
}
