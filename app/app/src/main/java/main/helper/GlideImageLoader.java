package main.helper;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Thor on 2018/3/4.
 *
 * 加载 Main页面的轮播图的帮助类
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Picasso 加载图片简单用法
        Picasso.with(context).load((Integer) path).into(imageView);
    }
}
