package main.activity.people_class_order.model;

import java.text.ParseException;

import bean.ClassInfo;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/12.
 *
 * 团课预约列表的数据，包含开始时间，课程名称，教师名称，教室名称，难易程度，课程状态，课程余量
 * status：1表示已开课，2表示预约，3表示已约满
 */

public class PeopleClassBriefModel {

    public Integer placeId, classId;
    public String startTime, className, teacherName, classroom;
    public Integer difficulty, status, allowance;

    public PeopleClassBriefModel() {

    }

    public PeopleClassBriefModel(
            Integer placeId, Integer classId,
            String startTime, String className, String teacherName, String classroom,
            Integer difficulty, Integer status, Integer allowance) {
        this.placeId = placeId;
        this.classId = classId;
        this.startTime = startTime;
        this.className = className;
        this.teacherName = teacherName;
        this.className = className;
        this.classroom = classroom;
        this.difficulty = difficulty;
        this.status = status;
        this.allowance = allowance;
    }
}
