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
    public List<String> url = new ArrayList<>(
            Arrays.asList("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523080941245&di=6c08f1e304e782fc75b0539857005e94&imgtype=0&src=http%3A%2F%2Fgss0.bdstatic.com%2FyqACvGbaBA94lNC68IqT0jB-xx1xbK%2Fztd%2Fw%3D350%3Bq%3D70%2Fsign%3Dcd4310299813b07ebdbd560d3cece01e%2F962bd40735fae6cd4d383c6904b30f2442a70f2d.jpg")
    );

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
