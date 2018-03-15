package main.activity.people_order_confirm.model;

import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import main.activity.people_order_confirm.delegate.PeopleOrderPayDelegate;

/**
 * Created by Thor on 2018/3/14.
 *
 * 团课预约的用户费用结算部分的数据结构，包括会员卡信息，预约人数，最终费用
 */

public class PeopleOrderPayModel {

    public Integer placeId, classId;
    public List<CardModel> cardInformation;
    public Integer number, expend;

    public PeopleOrderPayModel(
            Integer placeId, Integer classId, List<CardModel> cardInformation,
                                  Integer number, Integer expend) {
        this.placeId = placeId;
        this.classId = classId;
        this.cardInformation = cardInformation;
        this.number = number;
        this.expend = expend;
    }
}