package mine.helper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import mine.activity.order_card.BuyCardActivity;
import mine.activity.order_card.OrderCardListActivity;
import mine.activity.order_class.OrderClassListActivity;
import utils.RequestType;

/**
 * Created by Thor on 2018/3/10.
 *
 * Mine页面Item的点击监听器
 */

public class MyClickListener implements View.OnClickListener {

    private RequestType requestType;
    private Context context;

    public MyClickListener(RequestType requestType, Context context) {
        this.requestType = requestType;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        switch (this.requestType) {
            case Mine_OrderLessonTime:
                Intent intentForOrderLessonTime =
                        new Intent(context, OrderClassListActivity.class);
                context.startActivity(intentForOrderLessonTime);
                break;
            case Mine_LearnTime:
                Toast.makeText(context, "点击了上课记录", Toast.LENGTH_SHORT).show();
                break;
            case Mine_MemberScore:
                Toast.makeText(context, "点击了积分", Toast.LENGTH_SHORT).show();
                break;
            case Mine_ExperimentLessonRecord:
                Toast.makeText(context, "点击了体验课纪录", Toast.LENGTH_SHORT).show();
                break;
            case Mine_MemberCard:
                Intent intentForMyCard =
                        new Intent(context, OrderCardListActivity.class);
                context.startActivity(intentForMyCard);
                break;
            case Mine_BuyCard:
                Intent intentForBuyCard =
                        new Intent(context, BuyCardActivity.class);
                context.startActivity(intentForBuyCard);
                break;
            case Mine_LessonAbsent:
                Toast.makeText(context, "点击了我的请假", Toast.LENGTH_SHORT).show();
                break;
            case Mine_HistoryRecord:
                Toast.makeText(context, "点击了历史记录", Toast.LENGTH_SHORT).show();
                break;
            case Mine_ServiceProtocol:
                Toast.makeText(context, "点击了服务条款", Toast.LENGTH_SHORT).show();
                break;
            case Mine_Setting:
                Toast.makeText(context, "点击了设置", Toast.LENGTH_SHORT).show();
                break;
            case Mine_Logout:
                Toast.makeText(context, "点击了退出", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "点击了XX", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
