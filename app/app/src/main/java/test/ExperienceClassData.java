package test;

import java.util.Arrays;
import java.util.List;

import main.activity.experience_class_order.model.ClassModel;

/**
 * Created by Thor on 2018/3/16.
 *
 * 体验课程的测试数据，包括课程列表和时间列表
 */

public class ExperienceClassData {

    public static List<ClassModel> classModelList =
            Arrays.asList(
                    new ClassModel(1, "初级瑜伽"),
                    new ClassModel(2, "基础瑜伽"),
                    new ClassModel(3, "中级瑜伽"),
                    new ClassModel(4, "高级瑜伽")
            );

    public static List<String> timeList = Arrays.asList(
            "2018-03-15 06:00",
            "2018-03-16 06:00",
            "2018-03-17 06:00",
            "2018-03-18 06:00",
            "2018-03-19 06:00"
    );
}
