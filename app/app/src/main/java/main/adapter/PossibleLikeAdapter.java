package main.adapter;

import android.content.Context;
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
import com.renyajie.yuyue.MyApplication;
import com.renyajie.yuyue.R;

import java.util.List;

import main.model.PossibleLikeModel;

/**
 * Created by Thor on 2018/3/9.
 *
 * Main页面中猜你喜欢的适配器
 */

public class PossibleLikeAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

    private Context context;
    private List<PossibleLikeModel> data;
    private LayoutInflater layoutInflater;
    ImageLoader imageLoader;

    public PossibleLikeAdapter(Context context, List<PossibleLikeModel> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        imageLoader = new ImageLoader(MyApplication.getHttpQueue(), MyApplication.getBitmapCache());
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PossibleLikeModel model = data.get(position);

        ViewHolder viewHolder = null;

        if(convertView == null) {
            viewHolder = new ViewHolder();

            convertView = layoutInflater.inflate(
                    R.layout.fragment_main_possible_like_item, parent, false);

            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.description = convertView.findViewById(R.id.description);

            viewHolder.image.setDefaultImageResId(R.mipmap.ic_launcher);
            viewHolder.image.setErrorImageResId(R.mipmap.ic_launcher);
            viewHolder.image.setImageUrl(model.picUrl, imageLoader);

            viewHolder.title.setText(model.title);
            viewHolder.description.setText(model.description);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            viewHolder.image.setDefaultImageResId(R.mipmap.ic_launcher);
            viewHolder.image.setErrorImageResId(R.mipmap.ic_launcher);
            viewHolder.image.setImageUrl(model.picUrl, imageLoader);

            viewHolder.title.setText(model.title);
            viewHolder.description.setText(model.description);
        }

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context, data.get(position).title, Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder {
        public NetworkImageView image;
        public TextView title;
        public TextView description;
    }

}
