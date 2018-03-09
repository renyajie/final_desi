package com.renyajie.yuyue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import news.adapter.ImageAdapter;
import news.test.Images;

/**
 * Created by Thor on 2018/3/3.
 */

public class NewsFragment extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = view.findViewById(R.id.list_view);
        ImageAdapter adapter = new ImageAdapter(getContext(), 0, Images.newSet);
        listView.setAdapter(adapter);
    }
}
