package main.activity.place_class.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.renyajie.yuyue.R;

import utils.SuperDelegate;
import utils.ViewHolderType;

import static utils.ViewHolderType.ClassProperty;

/**
 * 课程属性部分
 * Created by Thor on 2018/4/14.
 */

public class PropertyDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater layoutInflater;
    private ChangeProperty changeProperty;

    public PropertyDelegate(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        changeProperty = (ChangeProperty) context;
    }

    public interface ChangeProperty {
        void changeProperty(int isPeople);
    }

    public void setChangeProperty(ChangeProperty changeProperty) {
        this.changeProperty = changeProperty;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ClassProperty;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PropertyViewHolder(
                layoutInflater.inflate(
                        R.layout.activity_main_place_class_property,
                        parent, false));
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("recycler", "bind PropertyDelegate");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        ((PropertyViewHolder)viewHolder).radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.people_lesson:
                        changeProperty.changeProperty(1);
                        break;
                    case R.id.individual_lesson:
                        changeProperty.changeProperty(0);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {

        RadioGroup radioGroup;

        public PropertyViewHolder(View itemView) {
            super(itemView);
            radioGroup = itemView.findViewById(R.id.radio_group);
        }
    }
}
