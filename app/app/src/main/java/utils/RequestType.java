package utils;

/**
 * Created by Thor on 2018/3/10.
 *
 * APP向服务器发起请求的种类
 */

public enum RequestType {
    //Main页面的网格数据
    Main_PeopleClassOrder, Main_IndividualClassOrder, Main_Sign, Main_ExperienceClassOrder,
    Main_Photo, Main_Teacher, Main_Leave, Main_Classroom,

    //Mine页面基本数据信息
    Mine_OrderLessonTime, Mine_LearnTime, Mine_MemberScore,
    //Mine页面的更多功能
    Mine_OrderLesson, Mine_ExperimentLessonRecord, Mine_MemberCard, Mine_BuyCard, Mine_LessonAbsent, Mine_HistoryRecord,
    //Mine页面的系统功能
    Mine_ServiceProtocol, Mine_Setting,
    //Mine页面的退出按钮
    Mine_Logout;
}
