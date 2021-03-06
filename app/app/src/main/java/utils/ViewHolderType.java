package utils;

/**
 * Created by Thor on 2018/3/8.
 *
 * 系统中所有的Item种类
 */

public enum ViewHolderType {
    //MainActivity中View类型
    GlideImage, GridButton, PossibleLike, RecommandPeopleClass, RecommandIndividualClass,
    UserInfo, BasicRecord, MoreFunction, SystemFunction, Logout,

    //RecommandClass中的View类型
    Date,

    //PlaceTeacherActivity中的View类型
    Teacher,

    //PeopleClassOrderActivity中的View类型
    ClassAndDate, PeopleClassBrief,

    //PeopleOrderConfirmActivity中的View类型
    PeopleOrderDetail, PeopleOrderPay,

    //ClassCommentActivity中的view类型
    ClassComment,

    //IndividualOrderActivity中的View类型
    IndividualClassBrief,

    //IndividualOrderConfirmActivity中的View类型
    IndividualOrderDetail,

    //PlaceClassActivity中的view类型
    ClassProperty,

    //OrderDetailActivity中的view类型
    OrderLesson, OrderPlace, OrderDetail, OrderLessonRule,

    //OrderCardDetailActivity中的view类型
    OrderCardDetail, OrderCardRule,

    //PlaceCardDetailActivity中的view类型
    PlaceCardDetail,

    //BuyCardActivity中的view类型
    PlaceDetail, CardKind,

    //CommentOrderActivity中的view类型
    COMMENT_RATING, COMMENT_STRING;
}
