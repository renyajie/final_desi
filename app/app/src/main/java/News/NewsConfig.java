package News;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 资讯的数据清单，用于展示一条具体的资讯或资讯列表，资讯列表没有文本集合
 * 1. 基本元素：标题，时间，标签
 * 2. 文本元素：段落集合
 * 3. 图片元素：图片的url集合
 * Created by Thor on 2018/3/7.
 */

public class NewsConfig {
    public String title;
    public String date;
    public List<String> tags = new ArrayList<>();
    public List<String> paragraphs = new ArrayList<>();
    public List<String> url = new ArrayList<>();

    public NewsConfig(String title, String date, List<String> tags
            , List<String> url, List<String> paragraphs) {
        this.title = title;
        this.date = date;
        this.tags = tags;
        this.url = url;
        this.paragraphs = paragraphs;
    }
}
