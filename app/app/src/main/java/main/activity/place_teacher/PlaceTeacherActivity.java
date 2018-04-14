package main.activity.place_teacher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.renyajie.yuyue.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassKind;
import bean.Place;
import bean.Teacher;
import main.activity.people_class_order.model.PlaceModel;
import main.activity.place_class.PlaceClassActivity;
import main.activity.place_class.delegate.PropertyDelegate;
import main.activity.place_teacher.delegate.PlaceTeacherDelegate;
import main.delegate.RecommandPeopleClassDelegate;
import main.helper.SpaceItemDecoration;
import mine.activity.order_card.delegate.PlaceDelegate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.AppConstant;
import utils.MainAdapter;
import utils.Messenger;
import utils.SuperDelegate;
import utils.UtilsMethod;
import utils.ViewHolderType;

/**
 * Created by Thor on 2018/4/14.
 */

public class PlaceTeacherActivity extends AppCompatActivity
implements PlaceDelegate.ChangePlace{

    private static final String PageName = "授课教师";
    private Toolbar toolbar;
    private List<SuperDelegate> delegates = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    private List<PlaceModel> placeModelList = new ArrayList<>();

    //场馆编号
    int placeId = 1;

    private static final int GET_PLACE_SUCCESS = 1;
    private static final int GET_PLACE_FAILURE = 2;
    private static final int GET_TEACHER_SUCCESS = 3;
    private static final int GET_TEACHER_FAILURE = 4;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_PLACE_FAILURE:
                    Toast.makeText(PlaceTeacherActivity.this,
                            "获取场馆失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_PLACE_SUCCESS:
                    //获取地址成功，填充地址信息，并发起课程查询
                    Messenger messengerB = (Messenger) msg.obj;
                    List<Place> placeList = (ArrayList<Place>) messengerB.getExtend().get("info");
                    placeModelList.clear();
                    for(Place place: placeList) {
                        PlaceModel model = new PlaceModel(place.getId(), place.getsName());
                        placeModelList.add(model);
                    }

                    //初始化场馆信息和课程属性
                    initPlace(placeModelList);

                    //发起课程查询
                    placeId = placeModelList.get(0).placeId;
                    getTeacher();
                    break;
                case GET_TEACHER_FAILURE:
                    Toast.makeText(PlaceTeacherActivity.this,
                            "获取教师信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case GET_TEACHER_SUCCESS:
                    Messenger messengerA = (Messenger) msg.obj;
                    List<Teacher> teacherList =
                            (ArrayList<Teacher>) messengerA.getExtend().get("info");

                    //初始化教师信息
                    initTeacher(teacherList);
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
        setContentView(R.layout.activity_main_place_teacher);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.my_toolBar);
        toolbar.setTitle(PageName);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        context = this;
        delegates.clear();

        //向RecyclerView中添加各类Item布局
        delegates.add(new PlaceDelegate(this));
        delegates.add(new PlaceTeacherDelegate(this));

        recyclerView = findViewById(R.id.recycler_view_content_container);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置Item间距
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        adapter = new MainAdapter(delegates);
        recyclerView.setAdapter(adapter);

        getPlaceData();
    }

    //初始化场馆信息
    private void initPlace(List<PlaceModel> placeModelList) {
        int position = getViewHolderPosition(ViewHolderType.PlaceDetail);
        if(position == -1) return;
        ((PlaceDelegate)delegates.get(position)).setPlaceModelList(placeModelList);
        if(adapter != null) adapter.updatePositionDelegate(position);
    }

    //初始化教师信息
    private void initTeacher(List<Teacher> teacherList) {
        int position = getViewHolderPosition(ViewHolderType.Teacher);
        if(position == -1) return;
        ((PlaceTeacherDelegate)delegates.get(position)).setTeacherList(teacherList);
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
     * 获取场馆信息
     */
    private void getPlaceData() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getPlace";
        Map<String, String> params = new HashMap<>();
        params.put("isPage", "0");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_PLACE_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_HOUR,
                        response, new TypeToken<Messenger<List<Place>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_PLACE_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void changePlace(int placeId) {
        this.placeId = placeId;
        getTeacher();
    }

    /**
     * 获取教师信息
     */
    private void getTeacher() {

        //构造请求地址
        String tmp = AppConstant.URL + "api/setting/getTeacher";
        Map<String, String> params = new HashMap<>();
        params.put("pId", placeId + "");
        String url = UtilsMethod.makeGetParams(tmp, params);
        Log.d("get", url);

        UtilsMethod.getDataAsync(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = handler.obtainMessage();
                msg.what = GET_TEACHER_FAILURE;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Messenger messenger = UtilsMethod.getFromJson(AppConstant.GSON_FOR_HOUR,
                        response, new TypeToken<Messenger<List<Teacher>>>(){});
                Message msg = handler.obtainMessage();
                msg.what = GET_TEACHER_SUCCESS;
                msg.obj = messenger;
                handler.sendMessage(msg);
            }
        });
    }
}
