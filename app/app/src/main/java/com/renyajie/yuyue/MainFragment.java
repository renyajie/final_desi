package com.renyajie.yuyue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassInfo;
import bean.ClassKind;
import main.activity.individual_class_order.delegate.IndividualClassBriefDelegate;
import main.activity.people_class_order.delegate.PeopleClassBriefDelegate;
import main.delegate.RecommandIndividualClassDelegate;
import main.delegate.RecommandPeopleClassDelegate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.AppConstant;
import utils.Messenger;
import utils.UtilsMethod;
import utils.ViewHolderType;
import utils.MainAdapter;
import main.delegate.GlideImageDelegate;
import main.delegate.GridButtonDelegate;
import utils.SuperDelegate;
import main.helper.SpaceItemDecoration;
import main.model.GlideImageModel;
import main.model.GridButtonModel;
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

    private static final int GET_PEOPLE_RECOMMAND_SUCCESS = 1;
    private static final int GET_PEOPLE_RECOMMAND_FAILURE = 2;
    private static final int GET_INDIVIDUAL_RECOMMAND_SUCCESS = 3;
    private static final int GET_INDIVIDUAL_RECOMMAND_FAILURE = 4;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_PEOPLE_RECOMMAND_FAILURE:
                    Toast.makeText(getContext(),
                            "获取团课列表失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_PEOPLE_RECOMMAND_SUCCESS:
                    //填充团课推荐信息
                    Messenger messengerA = (Messenger) msg.obj;
                    List<ClassKind> peopleClassList =
                            (ArrayList<ClassKind>) messengerA.getExtend().get("info");

                    //初始化团课列表
                    initRecommandPeopleClass(peopleClassList);
                    break;
                case GET_INDIVIDUAL_RECOMMAND_FAILURE:
                    Toast.makeText(getContext(),
                            "获取私教推荐列表失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_INDIVIDUAL_RECOMMAND_SUCCESS:
                    Messenger messengerB = (Messenger) msg.obj;
                    List<ClassKind> individualClassList =
                            (ArrayList<ClassKind>) messengerB.getExtend().get("info");

                    //初始化私教列表
                    initRecommandIndividualClass(individualClassList);
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
        //移出猜你喜欢，加入团课预约和私教预约的推荐
        //delegates.add(new PossibleLikeDelegate(context));
        delegates.add(new RecommandPeopleClassDelegate(context));
        delegates.add(new RecommandIndividualClassDelegate(context));

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

        getPeopleRecommand();
        getIndividualRecommand();
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

    //初始化团课推荐
    private void initRecommandPeopleClass(List<ClassKind> classKindList) {
        int position = getViewHolderPosition(ViewHolderType.RecommandPeopleClass);
        if(position == -1) return;
        ((RecommandPeopleClassDelegate)delegates.get(position))
                .setClassKindList(classKindList);
        ((RecommandPeopleClassDelegate)delegates.get(position))
                .setTitle("团课推荐");
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化团课推荐
    private void initRecommandIndividualClass(List<ClassKind> classKindList) {
        int position = getViewHolderPosition(ViewHolderType.RecommandIndividualClass);
        if(position == -1) return;
        ((RecommandIndividualClassDelegate)delegates.get(position))
                .setClassKindList(classKindList);
        ((RecommandIndividualClassDelegate)delegates.get(position))
                .setTitle("私教推荐");
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

    /**
     * 获取团课推荐信息
     */
    private void getPeopleRecommand() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/recommand/getPeopleClassRecommand";
        Map<String, String> params = new HashMap<>();
        params.put("userId", UtilsMethod.getUserId() + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_PEOPLE_RECOMMAND_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_HOUR,
                        response, new TypeToken<Messenger<List<ClassKind>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_PEOPLE_RECOMMAND_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    /**
     * 获取私教推荐信息
     */
    private void getIndividualRecommand() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/recommand/getIndividualClassRecommand";
        Map<String, String> params = new HashMap<>();
        params.put("userId", UtilsMethod.getUserId() + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_INDIVIDUAL_RECOMMAND_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_HOUR,
                        response, new TypeToken<Messenger<List<ClassKind>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_INDIVIDUAL_RECOMMAND_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

}
