package main.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renyajie.yuyue.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import utils.ViewHolderType;
import main.helper.GlideImageLoader;
import main.model.GlideImageModel;
import utils.SuperDelegate;

/**
 * Created by Thor on 2018/3/8.
 *
 * 处理 Main页面的轮播图
 */
public class GlideImageDelegate extends SuperDelegate {

    private LayoutInflater layoutInflater;
    private List<GlideImageModel> glideImageModelList;

    public GlideImageDelegate(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    // 设置数据源
    public void setGlideImageModelList(List<GlideImageModel> glideImageModelList) {
        this.glideImageModelList = glideImageModelList;
    }

    // 返回ViewHolder的类型
    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.GlideImage;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    //创建View的布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = layoutInflater.inflate(
                R.layout.fragment_main_glide_image, parent, false);
        GlideImageViewHolder viewHolder = new GlideImageViewHolder(view);

        return viewHolder;
    }

    @Override
    public <T extends RecyclerView.ViewHolder> void onBindViewHolder(T viewHolder) {
        Log.d("recycler", "bind GlideImageDelegate");

        // 防止刷新UI
        if(!uiFlag) {
            return;
        }
        uiFlag = false;

        //开始刷新UI

        List<String> titles = new ArrayList<String>();
        List<Integer> picResourceId = new ArrayList<Integer>();
        for(GlideImageModel model : glideImageModelList) {
            titles.add(model.title);
            picResourceId.add(model.imageResourceId);
        }

        ((GlideImageViewHolder)viewHolder).banner.setBannerTitles(titles);
        ((GlideImageViewHolder)viewHolder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        ((GlideImageViewHolder)viewHolder).banner.setImageLoader(new GlideImageLoader());
        ((GlideImageViewHolder)viewHolder).banner.setImages(picResourceId);
        ((GlideImageViewHolder)viewHolder).banner.setBannerAnimation(Transformer.Default);
        ((GlideImageViewHolder)viewHolder).banner.isAutoPlay(true);
        ((GlideImageViewHolder)viewHolder).banner.setDelayTime(3000);
        ((GlideImageViewHolder)viewHolder).banner.setIndicatorGravity(BannerConfig.RIGHT);
        ((GlideImageViewHolder)viewHolder).banner.start();
    }

    public static class GlideImageViewHolder extends RecyclerView.ViewHolder {

        public Banner banner;

        public GlideImageViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
}


