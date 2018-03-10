package com.renyajie.yuyue;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import utils.ViewHolderType;
import utils.MainAdapter;
import main.delegate.GlideImageDelegate;
import main.delegate.GridButtonDelegate;
import main.delegate.PossibleLikeDelegate;
import utils.SuperDelegate;
import main.helper.SpaceItemDecoration;
import main.model.GlideImageModel;
import main.model.GridButtonModel;
import main.model.PossibleLikeModel;
import test.MainData;

/**
 * Created by Thor on 2018/3/3.
 *
 * 主页，负责主页片段的代码逻辑
 */

public class MainFragment extends Fragment {

    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        context = getContext();

        delegates.clear();

        //向RecyclerView中添加各类Item布局
        delegates.add(new GlideImageDelegate(context));
        delegates.add(new GridButtonDelegate(context));
        delegates.add(new PossibleLikeDelegate(context));

        recyclerView = view.findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        initGlideImage(MainData.imageModelList);
        initGridButton(MainData.buttonModelList);
        initPossibleLike(MainData.possibleLikeModelList);
    }

    //初始化轮播图
    private void initGlideImage(List<GlideImageModel> glideImageModelList) {
        int position = getViewHolderPosition(ViewHolderType.GlideImage);
        if(position == -1) return;
        ((GlideImageDelegate)delegates.get(position)).setGlideImageModelList(glideImageModelList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化网格按钮
    private void initGridButton(List<GridButtonModel> gridButtonModelList) {
        int position = getViewHolderPosition(ViewHolderType.GridButton);
        if(position == -1) return;
        ((GridButtonDelegate)delegates.get(position)).setGridButtonModelList(gridButtonModelList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化猜你喜欢
    private void initPossibleLike(List<PossibleLikeModel> possibleLikeModelList) {
        int position = getViewHolderPosition(ViewHolderType.PossibleLike);
        if(position == -1) return;
        ((PossibleLikeDelegate)delegates.get(position)).setPossibleLikeModelList(possibleLikeModelList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    // 获取指定类型View在列表中的位置
    private int getViewHolderPosition(ViewHolderType type) {
        for (int i = 0; i < delegates.size(); i++) {
            if (delegates.get(i).getViewHolderType() == type) {
                return i;
            }
        }
        return -1;
    }

}
