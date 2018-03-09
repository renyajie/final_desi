package main.model;

import java.io.Serializable;

/**
 * Created by Thor on 2018/3/8.
 *
 * 轮播图
 */

public class GlideImageModel implements Serializable {
    public Integer imageResourceId;
    public String title;

    public GlideImageModel(Integer imageResourceId, String title) {
        this.imageResourceId = imageResourceId;
        this.title = title;
    }
}
