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

import main.helper.SpaceItemDecoration;
import mine.delegate.BasicRecordDelegate;
import mine.delegate.LogoutDelegate;
import mine.delegate.MoreFunctionDelegate;
import mine.delegate.SystemFunctionDelegate;
import mine.delegate.UserInfoDelegate;
import mine.model.BasicRecordModel;
import mine.model.UserInfoModel;
import test.MineData;
import utils.MainAdapter;
import utils.SuperDelegate;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/3/3.
 */

public class MineFragment extends Fragment {

    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        context = getContext();

        delegates.clear();

        //向RecyclerView中添加各类Item布局
        delegates.add(new UserInfoDelegate(context));
        delegates.add(new BasicRecordDelegate(context));
        delegates.add(new MoreFunctionDelegate(context));
        delegates.add(new SystemFunctionDelegate(context));
        delegates.add(new LogoutDelegate(context));

        recyclerView = view.findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(60));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        initUserInfo(MineData.userInfoModel);
        initBasicRecord(MineData.basicRecordModel);
        initMoreFunction();
        initSystemFunction();
        initLogout();
    }

    //初始化用户基本信息
    private void initUserInfo(UserInfoModel userInfoModel) {
        int position = getViewHolderPosition(ViewHolderType.UserInfo);
        if(position == -1) return;
        ((UserInfoDelegate)delegates.get(position)).setUserInfoModel(userInfoModel);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化基本用户数据
    private void initBasicRecord(BasicRecordModel basicRecordModel) {
        int position = getViewHolderPosition(ViewHolderType.BasicRecord);
        if(position == -1) return;
        ((BasicRecordDelegate)delegates.get(position)).setBasicRecordModel(basicRecordModel);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化更多功能部分
    private void initMoreFunction() {
        int position = getViewHolderPosition(ViewHolderType.MoreFunction);
        if(position == -1) return;
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化系统功能部分
    private void initSystemFunction() {
        int position = getViewHolderPosition(ViewHolderType.SystemFunction);
        if(position == -1) return;
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化退出按钮
    private void initLogout() {
        int position = getViewHolderPosition(ViewHolderType.Logout);
        if(position == -1) return;
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
