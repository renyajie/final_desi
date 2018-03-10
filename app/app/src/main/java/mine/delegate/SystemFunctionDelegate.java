package mine.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import mine.helper.MyClickListener;
import utils.RequestType;
import utils.ViewHolderType;
import utils.SuperDelegate;

/**
 * Created by Thor on 2018/3/9.
 *
 * Main页面的系统功能，包括服务条款和系统设置两项
 */

public class SystemFunctionDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater inflater;

    public SystemFunctionDelegate(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.SystemFunction;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d("SystemFunctionDelegate", "onCreateViewHolder");
        View view = inflater.inflate(
                R.layout.fragment_mine_system_function, parent, false);
        SystemFunctionViewHolder viewHolder = new SystemFunctionViewHolder(view);
        return viewHolder;
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("SystemFunctionDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        ((SystemFunctionViewHolder)viewHolder).serviceProtocol
                .setOnClickListener(new MyClickListener(RequestType.Mine_ServiceProtocol, context));
        ((SystemFunctionViewHolder)viewHolder).setting
                .setOnClickListener(new MyClickListener(RequestType.Mine_Setting, context));
    }

    public static class SystemFunctionViewHolder extends RecyclerView.ViewHolder {
        TextView serviceProtocol, setting;

        public SystemFunctionViewHolder(View itemView) {
            super(itemView);
            serviceProtocol = itemView.findViewById(R.id.service_protocol);
            setting = itemView.findViewById(R.id.setting);
        }
    }
}
