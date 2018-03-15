package main.activity.people_order_confirm.model;

/**
 * Created by Thor on 2018/3/14.
 *
 * 会员卡模型，包括会员卡编号，会员卡名称
 */

public class CardModel {
    public Integer cardId;
    public String cardName;

    public CardModel(Integer cardId, String cardName) {
        this.cardId = cardId;
        this.cardName = cardName;
    }
}
