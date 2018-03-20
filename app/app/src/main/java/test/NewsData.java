package test;

import java.util.Arrays;
import java.util.List;

import news.model.NewsConfig;

/**
 * Created by Thor on 2018/3/7.
 *
 * News页面的测试数据
 */

public class NewsData {

    public final static String[] imageUrls = new String[]{
            "http://www.renyajie.com/images/docker.jpg",
            "http://www.renyajie.com/images/github%E5%90%8C%E4%B8%80%E8%B4%A6%E5%8F%B7%E5%A4%9A%E4%BB%93%E5%BA%93%E6%8E%A8%E9%80%81%E9%97%AE%E9%A2%98.jpg",
            "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg",
            "http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png",
            "http://img07.tooopen.com/images/20170316/tooopen_sy_201956178977.jpg",
            "http://img.zcool.cn/community/018d4e554967920000019ae9df1533.jpg@900w_1l_2o_100sh.jpg"
    };

    public final static List<NewsConfig> newSet = Arrays.asList(
            new NewsConfig(1, "一起来吃芒果", "03-07",
                    "印度瑜伽馆",
                    Arrays.asList("养生"),
                    Arrays.asList(
                            "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg",
                            "http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png",
                            "http://img07.tooopen.com/images/20170316/tooopen_sy_201956178977.jpg",
                            "http://img.zcool.cn/community/018d4e554967920000019ae9df1533.jpg@900w_1l_2o_100sh.jpg")
                    , null),
            new NewsConfig(2, "跑步", "03-07",
                    "中国瑜伽馆",
                    Arrays.asList("养生"),
                    Arrays.asList(
                            "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg")
                    , null),
            new NewsConfig(3, "一起来吃苹果", "03-07",
                    "泰国瑜伽馆",
                    Arrays.asList("养生"),
                    Arrays.asList(
                            "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg",
                            "http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png",
                            "http://img07.tooopen.com/images/20170316/tooopen_sy_201956178977.jpg",
                            "http://img.zcool.cn/community/018d4e554967920000019ae9df1533.jpg@900w_1l_2o_100sh.jpg")
                    , null),
            new NewsConfig(4, "打羽毛球", "03-07",
                    "韩国瑜伽馆",
                    Arrays.asList("养生"),
                    Arrays.asList(
                            "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg")
                    , null),
            new NewsConfig(5, "一起来吃芒果", "03-07",
                    "俄罗斯瑜伽馆",
                    Arrays.asList("养生"),
                    Arrays.asList(
                            "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg",
                            "http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png",
                            "http://img07.tooopen.com/images/20170316/tooopen_sy_201956178977.jpg",
                            "http://img.zcool.cn/community/018d4e554967920000019ae9df1533.jpg@900w_1l_2o_100sh.jpg")
                    , null),
            new NewsConfig(6, "踢足球", "03-07",
                    "日本瑜伽馆",
                    Arrays.asList("养生"),
                    Arrays.asList(
                            "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg")
                    , null)
    );
}
