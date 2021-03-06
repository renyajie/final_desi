package news.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import news.NewsDetailActivity;
import utils.MyApplication;
import com.renyajie.yuyue.R;

import java.util.List;

import news.model.NewsConfig;
import utils.UtilsMethod;

/**
 * Created by Thor on 2018/3/7.
 *
 * 新闻资讯的适配器
 */

public class ImageAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

    public static final int MANY_PIC_LAYOUT = 0;
    public static final int LESS_PIC_LAYOUT = 1;

    private List<NewsConfig> data;
    private Context context;
    ImageLoader imageLoader;

    public ImageAdapter(@NonNull Context context, int resource, List<NewsConfig> data) {
        imageLoader = new ImageLoader(MyApplication.getHttpQueue(), MyApplication.getBitmapCache());
        this.data = data;
        this.context = context;
    }

    public void setData(List<NewsConfig> data) {
        this.data = data;
        notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回指定字布局的类型编号
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        NewsConfig item = data.get(position);

        if(item.url.size() < 3) {
            return LESS_PIC_LAYOUT;
        }

        return MANY_PIC_LAYOUT;
    }

    /**
     * 总共有多少总布局类型
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        NewsConfig item = (NewsConfig) getItem(position);

        ViewHolderForLess viewHolderForLess = null;
        ViewHolderForMany viewHolderForMany = null;

        int type = getItemViewType(position);

        // 此处根据确定具体布局是三图还是单图
        if(convertView == null) {
            switch (type) {
                case LESS_PIC_LAYOUT:
                    Log.d("null view", "less pic layout");
                    viewHolderForLess = new ViewHolderForLess();

                    convertView = LayoutInflater.from(context)
                            .inflate(R.layout.fragment_news_listview_less, null);

                    viewHolderForLess.image = convertView.findViewById(R.id.image);
                    viewHolderForLess.title = convertView.findViewById(R.id.title);
                    viewHolderForLess.date = convertView.findViewById(R.id.date);
                    viewHolderForLess.publisher = convertView.findViewById(R.id.publisher);

                    convertView.setTag(viewHolderForLess);
                    break;
                case MANY_PIC_LAYOUT:
                    Log.d("null view", "many pic layout");
                    viewHolderForMany = new ViewHolderForMany();

                    convertView = LayoutInflater.from(context)
                            .inflate(R.layout.fragment_news_listview_many, null);

                    viewHolderForMany.pic_one = convertView.findViewById(R.id.pic_one);
                    viewHolderForMany.pic_two = convertView.findViewById(R.id.pic_two);
                    viewHolderForMany.pic_three = convertView.findViewById(R.id.pic_three);
                    viewHolderForMany.title = convertView.findViewById(R.id.title);
                    viewHolderForMany.date = convertView.findViewById(R.id.date);
                    viewHolderForMany.publisher = convertView.findViewById(R.id.publisher);

                    convertView.setTag(viewHolderForMany);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case LESS_PIC_LAYOUT:
                    viewHolderForLess = (ViewHolderForLess) convertView.getTag();
                    break;
                case MANY_PIC_LAYOUT:
                    viewHolderForMany = (ViewHolderForMany) convertView.getTag();
                    break;
                default:
                    break;
            }
        }

        switch (type) {
            case LESS_PIC_LAYOUT:
                viewHolderForLess.image.setDefaultImageResId(R.mipmap.ic_launcher);
                viewHolderForLess.image.setErrorImageResId(R.mipmap.ic_launcher);
                viewHolderForLess.image.setImageUrl(item.url.get(0), imageLoader);

                viewHolderForLess.title.setText(item.title);
                viewHolderForLess.date
                        .setText(UtilsMethod.getStringFromDateForCheck(item.date));
                viewHolderForLess.publisher.setText(item.publisher);
                break;
            case MANY_PIC_LAYOUT:
                viewHolderForMany.pic_one.setDefaultImageResId(R.mipmap.ic_launcher);
                viewHolderForMany.pic_one.setErrorImageResId(R.mipmap.ic_launcher);
                viewHolderForMany.pic_one.setImageUrl(item.url.get(0), imageLoader);

                viewHolderForMany.pic_two.setDefaultImageResId(R.mipmap.ic_launcher);
                viewHolderForMany.pic_two.setErrorImageResId(R.mipmap.ic_launcher);
                viewHolderForMany.pic_two.setImageUrl(item.url.get(1), imageLoader);

                viewHolderForMany.pic_three.setDefaultImageResId(R.mipmap.ic_launcher);
                viewHolderForMany.pic_three.setErrorImageResId(R.mipmap.ic_launcher);
                viewHolderForMany.pic_three.setImageUrl(item.url.get(2), imageLoader);

                viewHolderForMany.title.setText(item.title);
                viewHolderForMany.date
                        .setText(UtilsMethod.getStringFromDateForCheck(item.date));
                viewHolderForMany.publisher.setText(item.publisher);
                break;
            default:
                break;
        }

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击项启动新闻详情页，并传递新闻参数
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra("newsId", data.get(position).newsId);
        context.startActivity(intent);
    }

    public static class ViewHolderForLess {
        public NetworkImageView image;
        public TextView title;
        public TextView date;
        public TextView publisher;
    }

    public static class ViewHolderForMany {
        public NetworkImageView pic_one;
        public NetworkImageView pic_two;
        public NetworkImageView pic_three;
        public TextView title;
        public TextView date;
        public TextView publisher;
    }
}
