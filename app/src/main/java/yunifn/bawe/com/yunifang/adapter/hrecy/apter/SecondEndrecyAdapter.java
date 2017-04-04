package yunifn.bawe.com.yunifang.adapter.hrecy.apter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.all.Goodsdetails;
import yunifn.bawe.com.yunifang.bean.LookaltBean;
import yunifn.bawe.com.yunifang.listenner.OnRecyclerViewItemClickListener;

/**
 * Created by Lenovo on 2017/3/23.
 * author ：李宁
 * 类注释：
 */

public class SecondEndrecyAdapter extends RecyclerView.Adapter<SecondEndrecyAdapter.EndHolder>{
    private Context context;
    private List<LookaltBean.DataBean.SubjectsBean.GoodsListBean> goodsList;
    public OnRecyclerViewItemClickListener onItemClickListener;
    public SecondEndrecyAdapter(Context context, List<LookaltBean.DataBean.SubjectsBean.GoodsListBean> goodsList22) {

        this.context = context;
        this.goodsList = goodsList22;
    }

    @Override
    public EndHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.hsecnt_recy_recy, parent, false);
        return new EndHolder(inflate);
    }

    @Override
    public void onBindViewHolder(EndHolder holder, final int position) {


                Glide.with(context).load(goodsList.get(position).getGoods_img()).into( holder.st_img);
                holder.st_goods_name.setText(goodsList.get(position).getGoods_name());
                holder.st_market_price.setText(goodsList.get(position).getMarket_price()+"");
                holder.st_shop_price.setText(goodsList.get(position).getShop_price()+"");
        holder.st_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Goodsdetails.class);
                intent.putExtra("type",goodsList.get(position).getId());
              //  Log.d("zzz","id***goodsList**"+ goodsList.get(position).getId());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return goodsList.size();
    }
    class EndHolder extends RecyclerView.ViewHolder{
          private final ImageView st_img;
        private final TextView st_goods_name;
        private final TextView st_market_price;
        private final TextView st_shop_price;
        public EndHolder(View itemView) {
            super(itemView);
                st_img = (ImageView) itemView.findViewById(R.id.st_img);
            st_goods_name = (TextView) itemView.findViewById(R.id.st_goods_name);
            st_market_price = (TextView) itemView.findViewById(R.id.st_market_price);
            st_shop_price = (TextView) itemView.findViewById(R.id.st_shop_price);
        }
    }
}
