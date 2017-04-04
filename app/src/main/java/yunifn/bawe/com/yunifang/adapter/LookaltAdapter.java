package yunifn.bawe.com.yunifang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.bean.LookaltBean;

/**
 * Created by Lenovo on 2017/3/19.
 * author ：李宁
 * 类注释：
 */

public class LookaltAdapter extends BaseAdapter {
    public Context context;
    public List<LookaltBean.DataBean.DefaultGoodsListBean> list;

    public LookaltAdapter(Context context, List<LookaltBean.DataBean.DefaultGoodsListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
             viewHolder = new ViewHolder();
            convertView=View.inflate(context, R.layout.home_itme_lookalt,null);
            viewHolder.look_img= (ImageView) convertView.findViewById(R.id.look_img);
            viewHolder.look_tvtit= (TextView) convertView.findViewById(R.id.look_tvtit);
            viewHolder.look_tvcolor_num= (TextView) convertView.findViewById(R.id.look_tvcolor_num);
            viewHolder.look_tvcolor_num2= (TextView) convertView.findViewById(R.id.look_tvcolor_num2);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getGoods_img()).into(viewHolder.look_img);
        viewHolder.look_tvtit.setText(list.get(position).getGoods_name());
        viewHolder.look_tvcolor_num.setText(list.get(position).getShop_price()+"");
        viewHolder.look_tvcolor_num2.setText(list.get(position).getMarket_price()+"");
        return convertView;
    }
    class ViewHolder{

        public ImageView look_img;
        public TextView look_tvtit;
        public TextView look_tvcolor_num;
        public TextView look_tvcolor_num2;
    }
}
