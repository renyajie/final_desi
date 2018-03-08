package Helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.renyajie.yuyue.MainActivity;
import com.renyajie.yuyue.MyApplication;
import com.renyajie.yuyue.R;

import java.util.List;

import News.NewsConfig;

/**
 * Created by Thor on 2018/3/7.
 */

public class ImageAdapter extends BaseAdapter {

    public static final int MANY_PIC_LAYOUT = 0;
    public static final int LESS_PIC_LAYOUT = 1;

    private List<NewsConfig> data;
    private Context context;
    ImageLoader imageLoader;

    public ImageAdapter(@NonNull Context context, int resource, List<NewsConfig> data) {
        imageLoader = new ImageLoader(MyApplication.getHttpQueue(), new BitmapCache());
        this.data = data;
        this.context = context;
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

                    viewHolderForLess.image.setDefaultImageResId(R.mipmap.ic_launcher);
                    viewHolderForLess.image.setErrorImageResId(R.mipmap.ic_launcher);
                    viewHolderForLess.image.setImageUrl(item.url.get(0), imageLoader);

                    viewHolderForLess.title.setText(item.title);
                    viewHolderForLess.date.setText(item.date);

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
                    viewHolderForMany.date.setText(item.date);

                    convertView.setTag(viewHolderForMany);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case LESS_PIC_LAYOUT:
                    Log.d("not null view", "less pic layout");
                    viewHolderForLess = (ViewHolderForLess) convertView.getTag();

                    viewHolderForLess.image.setDefaultImageResId(R.mipmap.ic_launcher);
                    viewHolderForLess.image.setErrorImageResId(R.mipmap.ic_launcher);
                    viewHolderForLess.image.setImageUrl(item.url.get(0), imageLoader);

                    viewHolderForLess.title.setText(item.title);
                    viewHolderForLess.date.setText(item.date);
                    break;
                case MANY_PIC_LAYOUT:
                    Log.d("not null view", "many pic layout");
                    viewHolderForMany = (ViewHolderForMany) convertView.getTag();

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
                    viewHolderForMany.date.setText(item.date);
                    break;
                default:
                    break;
            }
        }

        return convertView;
    }

    public class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            //获取应用程序的最大可用内存
            int maxMemory = (int) Runtime.getRuntime().maxMemory();
            int cacheSize = maxMemory / 8;
            mCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }

    public static class ViewHolderForLess {
        public NetworkImageView image;
        public TextView title;
        public TextView date;
    }

    public static class ViewHolderForMany {
        public NetworkImageView pic_one;
        public NetworkImageView pic_two;
        public NetworkImageView pic_three;
        public TextView title;
        public TextView date;
    }
}
