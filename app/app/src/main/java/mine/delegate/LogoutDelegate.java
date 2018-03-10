package mine.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import mine.helper.MyClickListener;
import utils.RequestType;
import utils.ViewHolderType;
import utils.SuperDelegate;

/**
 * Created by Thor on 2018/3/9.
 *
 * Mine页面的退出按钮
 */

public class LogoutDelegate extends SuperDelegate {

    private Context context;
    private LayoutInflater inflater;

    public LogoutDelegate(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.Logout;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d("LogoutDelegate", "onCreateViewHolder");
        View view = inflater.inflate(
                R.layout.fragment_mine_logout, parent, false);
        LogoutViewHolder viewHolder = new LogoutViewHolder(view);
        return viewHolder;
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("LogoutDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        ((LogoutViewHolder)viewHolder).logout
                .setOnClickListener(new MyClickListener(RequestType.Mine_Logout, context));
    }

    public static class LogoutViewHolder extends RecyclerView.ViewHolder {

        Button logout;

        public LogoutViewHolder(View itemView) {
            super(itemView);
            logout = itemView.findViewById(R.id.logout);
        }
    }
}
