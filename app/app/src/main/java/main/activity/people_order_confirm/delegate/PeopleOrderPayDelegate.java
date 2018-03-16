package main.activity.people_order_confirm.delegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.renyajie.yuyue.R;

import java.util.ArrayList;
import java.util.List;

import main.activity.order_success.OrderSuccessActivity;
import main.activity.people_order_confirm.model.CardModel;
import main.activity.people_order_confirm.model.PeopleOrderPayModel;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/14.
 *
 * 团课预约的费用结算部分，包括会员卡的选择，预约人数的选择， 最终费用的显示，最终的确认
 */

public class PeopleOrderPayDelegate extends SuperDelegate
        implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private PeopleOrderPayModel model;

    private ArrayAdapter<String> arrayAdapter;
    private List<String> cardName;
    private PeopleOrderPayViewHolder payViewHolder;
    //从何处启动，用于继续预约时返回对应界面
    private Integer whereStart;

    public PeopleOrderPayDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setPeopleOrderPayModel(PeopleOrderPayModel model) {
        this.model = model;
        //设置预约人数为1，总费用为1
        model.number = 1;
        model.expend = 1;
    }

    public void setWhereStart(Integer whereStart) {
        this.whereStart = whereStart;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.PeopleOrderPay;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PeopleOrderPayViewHolder(layoutInflater.inflate(
                R.layout.activity_main_people_order_confirm_pay, parent, false
        ));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("PeopleOrderPayDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        payViewHolder = (PeopleOrderPayViewHolder)viewHolder;
        //设置会员卡下拉选择
        cardName = new ArrayList<>();
        for(CardModel cardModel: model.cardInformation) {
            cardName.add(cardModel.cardName);
        }
        arrayAdapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, cardName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payViewHolder.cardSpinner.setAdapter(arrayAdapter);
        payViewHolder.cardSpinner.setOnItemSelectedListener(this);

        //设置加减按钮监听
        payViewHolder.addText.setOnClickListener(this);
        payViewHolder.subText.setOnClickListener(this);

        payViewHolder.numberForClick.setText(String.valueOf(model.number));
        payViewHolder.numberForPay.setText(String.valueOf(model.number));
        payViewHolder.expend.setText(String.valueOf(model.expend));

        //设置点击服务条款弹出对话框
        payViewHolder.protocol.setOnClickListener(this);

        //设置预约按钮的监听器
        payViewHolder.confirmButton.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CardModel cardModel = model.cardInformation.get(position);
        Toast.makeText(context, "卡号" + cardModel.cardId + " ,卡名" + cardModel.cardName,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_text:
                payViewHolder.numberForClick.setText(String.valueOf(++model.number));
                payViewHolder.numberForPay.setText(String.valueOf(model.number));
                payViewHolder.expend.setText(String.valueOf(++model.expend));
                break;
            case R.id.sub_text:
                if(model.number == 1) {
                    Toast.makeText(context, "人数不能少于1", Toast.LENGTH_SHORT).show();
                    return;
                }
                payViewHolder.numberForClick.setText(String.valueOf(--model.number));
                payViewHolder.numberForPay.setText(String.valueOf(model.number));
                payViewHolder.expend.setText(String.valueOf(--model.expend));
                break;
            case R.id.protocol:
                showProtocolDialog();
                break;
            case R.id.confirm_button:
                if(!payViewHolder.readCheck.isChecked()) {
                    showNoticeDialog();
                    return;
                }

                    Log.d("msg", "机构编号" + model.placeId +
                            ", 课程信息" + model.classId + ", 费用" + model.expend);
                    Intent intent = new Intent(context, OrderSuccessActivity.class);
                    intent.putExtra("start_from", this.whereStart);
                    context.startActivity(intent);
                    break;
            default:
                break;
        }
    }

    //显示对话框
    private void showProtocolDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("会员服务条款");
        //TODO 加入会员服务条款
        normalDialog.setMessage("xxxxx");
        normalDialog.setPositiveButton("同意",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        payViewHolder.readCheck.setChecked(true);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    //显示对话框
    private void showNoticeDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("注意");
        //TODO 加入会员服务条款
        normalDialog.setMessage("您需要先同意会员条款才能预约");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    public static class PeopleOrderPayViewHolder extends RecyclerView.ViewHolder {

        Spinner cardSpinner;
        TextView addText, subText, numberForClick, numberForPay, expend;
        CheckBox readCheck;
        TextView protocol;
        Button confirmButton;

        public PeopleOrderPayViewHolder(View itemView) {
            super(itemView);
            cardSpinner = itemView.findViewById(R.id.card_spinner);
            addText = itemView.findViewById(R.id.add_text);
            subText = itemView.findViewById(R.id.sub_text);
            numberForClick = itemView.findViewById(R.id.number_for_click);
            numberForPay = itemView.findViewById(R.id.number_for_pay);
            expend = itemView.findViewById(R.id.expend);
            readCheck = itemView.findViewById(R.id.read_check);
            protocol = itemView.findViewById(R.id.protocol);
            confirmButton = itemView.findViewById(R.id.confirm_button);
        }
    }
}
