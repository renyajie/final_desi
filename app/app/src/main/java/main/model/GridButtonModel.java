package main.model;

import java.io.Serializable;

/**
 * Created by Thor on 2018/3/8.
 *
 * 网格按钮的数据模型
 */

public class GridButtonModel implements Serializable {
    public Integer picResourceId;
    public String title;

    public GridButtonModel(Integer picResourceId, String title) {
        this.picResourceId = picResourceId;
        this.title = title;
    }
}
