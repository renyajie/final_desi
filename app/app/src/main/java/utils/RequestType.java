package utils;

/**
 * Created by Thor on 2018/3/10.
 *
 * APP向服务器发起请求的种类
 */

public enum RequestType {
    //Mine页面基本数据信息
    Mine_OrderLessonTime, Mine_LearnTime, Mine_MemberScore,
    //Mine页面的更多功能
    Mine_ExperimentLessonRecord, Mine_MemberCard, Mine_CardOrder, Mine_LessonAbsent, Mine_HistoryRecord,
    //Mine页面的系统功能
    Mine_ServiceProtocol, Mine_Setting,
    //Mine页面的退出按钮
    Mine_Logout;
}