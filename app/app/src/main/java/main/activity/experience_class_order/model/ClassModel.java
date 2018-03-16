package main.activity.experience_class_order.model;

/**
 * Created by Thor on 2018/3/16.
 *
 * 课程的数据结构，课程编号和课程名称
 */

public class ClassModel {

    public Integer classId;
    public String className;

    public ClassModel(Integer classId, String className) {
        this.classId = classId;
        this.className = className;
    }
}
