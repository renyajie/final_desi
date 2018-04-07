package news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CardInfo;
import bean.ClassInfo;
import bean.News;
import im.delight.android.webview.AdvancedWebView;
import main.activity.people_order_confirm.PeopleOrderConfirmActivity;
import main.activity.people_order_confirm.model.PeopleOrderPayModel;
import news.model.NewsConfig;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.NewsData;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/17.
 *
 * 新闻详情页面，查看具体的新闻
 */

public class NewsDetailActivity extends AppCompatActivity{

    private String PageName = "通知详情";
    private Toolbar toolbar;

    private TextView title, publisherName, publishTime, content;
    private int newsId;

    private News news = new News();

    private static final int GET_NEWS_SUCCESS = 1;
    private static final int GET_NEWS_FAILURE = 2;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_NEWS_FAILURE:
                    Toast.makeText(NewsDetailActivity.this,
                            "获取通知失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_NEWS_SUCCESS:
                    //初始化课界面
                    Messenger messengerA = (Messenger) msg.obj;
                    news = (News) messengerA.getExtend().get("info");

                    initContent();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_layout);
        initView();
    }

    private void initView() {
        //接收Intent启动参数
        receiveIntentData();

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.title);
        publisherName = findViewById(R.id.publisher_name);
        publishTime = findViewById(R.id.publish_time);
        content = findViewById(R.id.content);

        getNews();
    }

    private void receiveIntentData() {
        Bundle bundle = getIntent().getExtras();
        this.newsId = bundle.getInt("newsId", 0);
    }

    private void initContent() {
        title.setText(news.getTitle());
        publisherName.setText(news.getpName() + ": " + news.getmName());
        publishTime.setText(
                UtilsMethod.getStringFromDateForCheck(news.getPubTime()));
        content.setText(news.getContext());
    }

    //获取新闻内容
    private void getNews() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/news/getOneNews";
        Map<String, String> params = new HashMap<>();
        params.put("newsId", newsId + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_NEWS_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_ALL,
                        response, new TypeToken<Messenger<News>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_NEWS_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

}
