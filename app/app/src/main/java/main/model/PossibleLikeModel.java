package main.model;

import java.io.Serializable;

/**
 * Created by Thor on 2018/3/8.
 *
 * 猜你喜欢的数据结构
 */

public class PossibleLikeModel implements Serializable {

    public String picUrl;
    public String title;
    public String description;

    public PossibleLikeModel(String picUrl, String title, String description) {
        this.picUrl = picUrl;
        this.title = title;
        this.description = description;
    }
}
