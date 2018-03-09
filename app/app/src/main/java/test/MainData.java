package test;

import com.renyajie.yuyue.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.model.GlideImageModel;
import main.model.GridButtonModel;
import main.model.PossibleLikeModel;

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
            new GridButtonModel(R.mipmap.ic_launcher, "按钮一"),
            new GridButtonModel(R.mipmap.ic_launcher, "按钮二"),
            new GridButtonModel(R.mipmap.ic_launcher, "按钮三"),
            new GridButtonModel(R.mipmap.ic_launcher, "按钮四"),
            new GridButtonModel(R.mipmap.ic_launcher, "按钮五"),
            new GridButtonModel(R.mipmap.ic_launcher, "按钮六"),
            new GridButtonModel(R.mipmap.ic_launcher, "按钮七"),
            new GridButtonModel(R.mipmap.ic_launcher, "按钮八"),
            new GridButtonModel(R.mipmap.ic_launcher, "按钮九"),
            new GridButtonModel(R.mipmap.ic_launcher, "按钮十")
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
            )
    );

}
