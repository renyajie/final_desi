package utils;

/**
 * Created by Thor on 2018/3/8.
 *
 * 系统中所有的Item种类
 */

public enum ViewHolderType {
    //MainActivity中View类型
    GlideImage, GridButton, PossibleLike,
    UserInfo, BasicRecord, MoreFunction, SystemFunction, Logout,

    //PeopleClassOrderActivity中的View类型
    ClassAndDate, PeopleClassBrief,

    //PeopleOrderConfirmActivity中的View类型
    PeopleOrderDetail, PeopleOrderPay,

    //IndividualOrderActivity中的View类型
    IndividualClassBrief,

    //IndividualOrderConfirmActivity中的View类型
    IndividualOrderDetail;
}
