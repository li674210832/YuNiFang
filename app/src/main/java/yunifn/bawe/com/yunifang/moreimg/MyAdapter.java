package yunifn.bawe.com.yunifang.moreimg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.bean.More;

/**
 * Created by Lenovo on 2017/3/30.
 * author ：李宁
 * 类注释：
 */
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private Context context;
    private  List<More.DataBean.GoodsBriefBean> goodsBrief;
        public MyAdapter(Context context, List<More.DataBean.GoodsBriefBean> goodsBrief){
            this.context=context;
            this.goodsBrief=goodsBrief;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(context).inflate(R.layout.recycle_itme,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            for (int i = 0; i < goodsBrief.size(); i++) {
                Glide.with(context).load(goodsBrief.get(position).getGoods_img()).into(holder.t_img);
                holder.t_name.setText(goodsBrief.get(position).getGoods_name());
                holder.t_pri.setText("单价："+goodsBrief.get(position).getShop_price()+"元");
            }

        }

        @Override
        public int getItemCount() {
            return goodsBrief.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            private final ImageView t_img;
            private final TextView t_name;
            private final TextView t_pri;

            public ViewHolder(View itemView) {
                super(itemView);
                t_img = (ImageView) itemView.findViewById(R.id.t_img);
                t_name = (TextView) itemView.findViewById(R.id.t_name);
                t_pri = (TextView) itemView.findViewById(R.id.t_pri);
            }
        }
    }
