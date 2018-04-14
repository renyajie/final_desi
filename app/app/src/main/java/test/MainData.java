package test;

import com.renyajie.yuyue.R;

import java.util.Arrays;
import java.util.List;

import main.model.GlideImageModel;
import main.model.GridButtonModel;
import main.model.PossibleLikeModel;
import utils.RequestType;

/**
 * Created by Thor on 2018/3/8.
 *
 * Main 页面的测试数据
 */

public class MainData {

    //轮播图数据
    public static List<GlideImageModel> imageModelList = Arrays.asList(
            new GlideImageModel(R.mipmap.ic_launcher, "第一张图"),
            new GlideImageModel(R.mipmap.ic_launcher, "第二张图"),
            new GlideImageModel(R.mipmap.ic_launcher, "第三张图")
    );

    //网格按钮数据
    public static List<GridButtonModel> buttonModelList = Arrays.asList(
            new GridButtonModel(R.mipmap.main_tuan_yuyue, "团课预约", RequestType.Main_PeopleClassOrder),
            new GridButtonModel(R.mipmap.main_si_yuyue, "私教预约", RequestType.Main_IndividualClassOrder),
            new GridButtonModel(R.mipmap.main_place_class, "瑜伽课程", RequestType.Main_PlaceClass),
            new GridButtonModel(R.mipmap.main_teacher, "授课教师", RequestType.Main_Teacher),
            new GridButtonModel(R.mipmap.main_tiyan, "申请体验", RequestType.Main_ExperienceClassOrder),
            new GridButtonModel(R.mipmap.main_qiandao, "上课签到", RequestType.Main_Sign),
            new GridButtonModel(R.mipmap.main_leave, "我要请假", RequestType.Main_Leave),
            new GridButtonModel(R.mipmap.main_classroom, "教室环境", RequestType.Main_Classroom)
    );

    //猜你喜欢数据
    public static List<PossibleLikeModel> possibleLikeModelList = Arrays.asList(
            new PossibleLikeModel(
                    "http://img3.imgtn.bdimg.com/it/u=3582777263,554719726&fm=11&gp=0.jpg",
                    "路飞",
                    "海贼王的主角"
            ),
            new PossibleLikeModel(
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=718973267,42635833&fm=27&gp=0.jpg",
                    "索罗",
                    "副船长"
            ),
            new PossibleLikeModel(
                    "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1256447754,1917514235&fm=27&gp=0.jpg",
                    "龙",
                    "路飞的老爸"
            ),
            new PossibleLikeModel(
                    "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2902066310,855110607&fm=27&gp=0.jpg",
                    "红发",
                    "把路飞引上海贼之路的男人"
            ),
            new PossibleLikeModel(
                    "http://img3.imgtn.bdimg.com/it/u=3582777263,554719726&fm=11&gp=0.jpg",
                    "路飞",
                    "海贼王的主角"
            ),
            new PossibleLikeModel(
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=718973267,42635833&fm=27&gp=0.jpg",
                    "索罗",
                    "副船长"
            ),
            new PossibleLikeModel(
                    "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1256447754,1917514235&fm=27&gp=0.jpg",
                    "龙",
                    "路飞的老爸"
            ),
            new PossibleLikeModel(
                    "http://img3.imgtn.bdimg.com/it/u=3582777263,554719726&fm=11&gp=0.jpg",
                    "路飞",
                    "海贼王的主角"
            ),
            new PossibleLikeModel(
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=718973267,42635833&fm=27&gp=0.jpg",
                    "索罗",
                    "副船长"
            ),
            new PossibleLikeModel(
                    "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1256447754,1917514235&fm=27&gp=0.jpg",
                    "龙",
                    "路飞的老爸"
            )
    );

}
