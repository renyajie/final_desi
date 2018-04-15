package news.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 资讯的数据清单，用于展示一条具体的资讯或资讯列表，资讯列表没有文本集合
 * 1. 基本元素：标题，时间，发布者，标签
 * 2. 文本元素：段落集合
 * 3. 图片元素：图片的url集合
 * Created by Thor on 2018/3/7.
 */

public class NewsConfig implements Serializable {
    public Integer newsId;
    public String title;
    public Date date;
    public String publisher;
    public List<String> tags = new ArrayList<>();
    public List<String> paragraphs = new ArrayList<>();
    public List<String> url = new ArrayList<>();

    public NewsConfig() {

    }

    public NewsConfig(Integer newsId, String title, Date date, String publisher,
                      List<String> tags, List<String> url, List<String> paragraphs) {
        this.newsId = newsId;
        this.title = title;
        this.date = date;
        this.publisher = publisher;
        this.tags = tags;
        this.url = url;
        this.paragraphs = paragraphs;
    }
}
