package mine.activity.order_class.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.renyajie.yuyue.R;

import java.util.List;

import mine.activity.order_class.model.OrderClassBriefModel;

/**
 * Created by Thor on 2018/3/15.
 *
 * 约课列表的适配器
 */

public class OrderClassListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<OrderClassBriefModel> data;

    public OrderClassListAdapter(Context context,
                                 List<OrderClassBriefModel> orderClassBriefModelList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = orderClassBriefModelList;
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
        OrderClassBriefModel model = data.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = layoutInflater.inflate(
                    R.layout.activity_mine_order_class_list_item, parent, false);
            viewHolder.classPic = convertView.findViewById(R.id.class_pic);
            viewHolder.className = convertView.findViewById(R.id.class_name);
            viewHolder.orderTime = convertView.findViewById(R.id.order_time);
            viewHolder.placeName = convertView.findViewById(R.id.place_name);

            viewHolder.className.setText(model.className);
            viewHolder.orderTime.setText(model.orderTime);
            viewHolder.placeName.setText(model.placeName);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            viewHolder.className.setText(model.className);
            viewHolder.orderTime.setText(model.orderTime);
            viewHolder.placeName.setText(model.placeName);
        }

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OrderClassBriefModel model = data.get(position);
        Log.d("msg", model.placeName);
    }

    class ViewHolder {
        public ImageView classPic;
        public TextView className, orderTime, placeName;
    }
}
