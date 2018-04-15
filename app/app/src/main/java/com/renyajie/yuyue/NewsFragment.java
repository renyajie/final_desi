package com.renyajie.yuyue;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassInfo;
import bean.News;
import bean.Place;
import main.activity.people_class_order.PeopleClassOrderActivity;
import main.activity.people_class_order.model.PlaceModel;
import news.adapter.ImageAdapter;
import news.model.NewsConfig;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.NewsData;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/3.
 */

public class NewsFragment extends Fragment {

    private ListView listView;
    List<NewsConfig> newsData = new ArrayList<>();
    private LinearLayout noNewsLayout;
    private ImageAdapter adapter;

    private static final int GET_NEWS_SUCCESS = 1;
    private static final int GET_NEWS_FAILURE = 2;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_NEWS_FAILURE:
                    Toast.makeText(getContext(),
                            "获取新闻失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_NEWS_SUCCESS:
                    //获取新闻，刷新新闻列表
                    Messenger messengerA = (Messenger) msg.obj;
                    List<News> newsList =
                            (ArrayList<News>) messengerA.getExtend().get("info");

                    newsData.clear();
                    for(News news: newsList) {
                        NewsConfig config = new NewsConfig();
                        config.title = news.getTitle();
                        config.publisher = news.getmName();
                        config.date = news.getPubTime();
                        config.newsId = news.getId();
                        config.url.add(AppConstant.URL + news.getPicUrl());
                        newsData.add(config);

                    }

                    adapter.setData(newsData);

                    //设置新闻列表可见
                    noNewsLayout.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = view.findViewById(R.id.list_view);
        adapter = new ImageAdapter(getContext(), 0, newsData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);

        noNewsLayout = view.findViewById(R.id.no_news_layout);

        getNewsData();
    }

    //获取通知
    private void getNewsData() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/news/getNews";
        Map<String, String> params = new HashMap<>();
        params.put("isPage", "0");
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
                        response, new TypeToken<Messenger<List<News>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_NEWS_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }
}
