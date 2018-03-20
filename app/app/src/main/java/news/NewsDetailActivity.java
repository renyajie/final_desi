package news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import im.delight.android.webview.AdvancedWebView;
import news.model.NewsConfig;
import test.NewsData;

/**
 * Created by Thor on 2018/3/17.
 *
 * 新闻详情页面，查看具体的新闻
 */

public class NewsDetailActivity extends AppCompatActivity implements AdvancedWebView.Listener{

    private String PageName = "xxxx新闻标题";
    private Toolbar toolbar;
    private AdvancedWebView mWebView;
//    private TextView title, publisherName, publishTime;
//    private LinearLayout content;
//    private Integer newsId;
//    private NewsConfig newsConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_layout);
        initView();
    }

    private void initView() {
        //接收Intent启动参数
//        receiveIntentData();

        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        title = findViewById(R.id.title);
//        publisherName = findViewById(R.id.publisher_name);
//        publishTime = findViewById(R.id.publish_time);
//        content = findViewById(R.id.content);
//
//        title.setText(newsConfig.title);
//        publisherName.setText(newsConfig.publisher);
//        publishTime.setText(newsConfig.date);

//        initContent();

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.loadUrl("http://blog.csdn.net/ctlwy/article/details/54601865");
    }

//    //动态拼装新闻内容
//    private void initContent() {
//
//    }



    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    //接收Intent启动参数
//    private void receiveIntentData() {
//        this.newsId = getIntent().getIntExtra("id", 0);
//    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }
}
