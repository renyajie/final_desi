package main.activity.people_class_order.helper;

import android.content.Context;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.renyajie.yuyue.R;

/**
 * Created by Thor on 2018/3/12.
 *
 * 日期单选按钮的监听器
 */

public class MyRadioButtonListener implements RadioGroup.OnCheckedChangeListener {

    private Context context;

    public MyRadioButtonListener(Context context) {
        this.context = context;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.today:
                Toast.makeText(context, "点击了今天", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tomorrow:
                Toast.makeText(context, "点击了明天", Toast.LENGTH_SHORT).show();
                break;
            case R.id.the_day_after_tomorrow:
                Toast.makeText(context, "点击了后天", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
