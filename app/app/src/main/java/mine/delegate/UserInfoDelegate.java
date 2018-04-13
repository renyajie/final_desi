package mine.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.renyajie.yuyue.R;

import utils.MyApplication;
import utils.UtilsMethod;
import utils.ViewHolderType;
import mine.model.UserInfoModel;
import utils.SuperDelegate;

/**
 * Created by Thor on 2018/3/9.
 *
 * 处理Mine页面的用户信息部分
 */

public class UserInfoDelegate extends SuperDelegate {

    private LayoutInflater inflater;
    private UserInfoModel userInfoModel;
    private ImageLoader imageLoader;

    public UserInfoDelegate(Context context) {
        this.inflater = LayoutInflater.from(context);
        imageLoader = MyApplication.getImageLoader();
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.UserInfo;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d("GlideImageDeledate", "onCreateViewHolder");
        View view = inflater.inflate(
                R.layout.fragment_mine_user_info, parent, false);
        UserInfoViewHolder viewHolder = new UserInfoViewHolder(view);
        return viewHolder;
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("UserInfoDelegate", "onBindViewHolder");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI
        int resourceId = UtilsMethod.getUserGender().equals("男")?
                R.mipmap.boy : R.mipmap.girl;
        ((UserInfoViewHolder)viewHolder).icon.setImageResource(resourceId);

        ((UserInfoViewHolder)viewHolder).nickname.setText(userInfoModel.username);
        ((UserInfoViewHolder)viewHolder).phone.setText(userInfoModel.phone);
    }

    public static class UserInfoViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView nickname, phone;

        public UserInfoViewHolder(View itemView){
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            nickname = itemView.findViewById(R.id.nickname);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
