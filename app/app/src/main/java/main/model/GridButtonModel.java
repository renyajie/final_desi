package main.model;

import java.io.Serializable;

import utils.RequestType;

/**
 * Created by Thor on 2018/3/8.
 *
 * 网格按钮的数据模型
 */

public class GridButtonModel implements Serializable {
    public Integer picResourceId;
    public String title;
    public RequestType requestType;

    public GridButtonModel(Integer picResourceId, String title, RequestType requestType) {
        this.picResourceId = picResourceId;
        this.title = title;
        this.requestType = requestType;
    }
}
