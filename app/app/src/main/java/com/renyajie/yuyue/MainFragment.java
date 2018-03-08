package com.renyajie.yuyue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Helper.GlideImageLoader;

/**
 * Created by Thor on 2018/3/3.
 */

public class MainFragment extends Fragment {

    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ArrayList<Integer> images = new ArrayList<>();
        images.addAll(Arrays.asList(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        //ArrayList<String> titles = new ArrayList<>();
        //titles.addAll(Arrays.asList("第一张图片", "第二张图片", "第三张图片"));
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


        gridView = view.findViewById(R.id.gridview);
        int[] icons = { R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher };
        String[] name = { "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10" };
        dataList = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", icons[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
        String[] from = {"image", "text"};
        int[] to = {R.id.grid_item_image, R.id.grid_item_text };
        adapter = new SimpleAdapter(
                getContext(), dataList, R.layout.fragment_main_gridview_item, from, to);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),
                        dataList.get(position).get("text").toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
