package mine.helper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.renyajie.yuyue.LoginActivity;
import com.renyajie.yuyue.MainActivity;

import mine.activity.order_card.BuyCardActivity;
import mine.activity.order_card.OrderCardListActivity;
import mine.activity.order_card.adapter.CardKindListAdapter;
import mine.activity.order_class.OrderClassListActivity;
import utils.RequestType;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/10.
 *
 * Mine页面Item的点击监听器
 */

public class MyClickListener implements View.OnClickListener {

    private RequestType requestType;
    private Context context;
    private CardKindListAdapter.FinishActivity finishActivity;

    public MyClickListener(RequestType requestType, Context context) {
        this.requestType = requestType;
        this.context = context;
        finishActivity = (CardKindListAdapter.FinishActivity) context;
    }

    @Override
    public void onClick(View v) {
        switch (this.requestType) {
            case Mine_OrderLessonTime:
                Toast.makeText(context, "点击了约课记录", Toast.LENGTH_SHORT).show();
                break;
            case Mine_LearnTime:
                Toast.makeText(context, "点击了上课记录", Toast.LENGTH_SHORT).show();
                break;
            case Mine_MemberScore:
                Toast.makeText(context, "点击了积分", Toast.LENGTH_SHORT).show();
                break;
            case Mine_OrderLesson:
                Intent intentForOrderLesson =
                        new Intent(context, OrderClassListActivity.class);
                context.startActivity(intentForOrderLesson);
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
                //取消用户登录状态，返回登录界面
                UtilsMethod.setUserLogOut();
                Intent intentForLogOut = new Intent(context, LoginActivity.class);
                context.startActivity(intentForLogOut);
                finishActivity.finishActivity();
                break;
            default:
                Toast.makeText(context, "点击了XX", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
