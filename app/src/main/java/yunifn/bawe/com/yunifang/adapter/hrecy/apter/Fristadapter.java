package yunifn.bawe.com.yunifang.adapter.hrecy.apter;

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
import yunifn.bawe.com.yunifang.bean.LookaltBean;

/**
 * Created by Lenovo on 2017/3/23.
 * author ：李宁
 * 类注释：
 */

public class Fristadapter extends RecyclerView.Adapter<Fristadapter.FviewHolder> {


private  List<LookaltBean.DataBean.BestSellersBean> bestSellers;

    private  Context context;
    private List<LookaltBean.DataBean.BestSellersBean.GoodsListBeanX> goodsList1;

    public Fristadapter(List<LookaltBean.DataBean.BestSellersBean.GoodsListBeanX> bestSellers, Context context) {
        this.goodsList1=bestSellers;
        this.context=context;
        //goodsList1 = bestSellers.get(0).getGoodsList();
    }

    @Override
    public FviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hfrist, parent, false);

        return new FviewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(FviewHolder holder, int position) {
            holder.hfrist_goods_name.setText(goodsList1.get(position).getGoods_name());
            holder.hfrist_market_price.setText(goodsList1.get(position).getMarket_price()+"");
            holder.hfrist_shop_price.setText(goodsList1.get(position).getShop_price()+"");
            Glide.with(context).load(goodsList1.get(position).getGoods_img()).into(holder.hfrist_img);

    }

    @Override
    public int getItemCount() {
        return goodsList1.size();
    }

     class FviewHolder extends RecyclerView.ViewHolder{
         public  ImageView hfrist_img;
         public  TextView hfrist_goods_name;
         public  TextView hfrist_market_price;
         public  TextView hfrist_shop_price;
        public FviewHolder(View itemView) {
            super(itemView);
              hfrist_img = (ImageView) itemView.findViewById(R.id.hfrist_img);
        hfrist_goods_name = (TextView) itemView.findViewById(R.id.hfrist_goods_name);
        hfrist_market_price = (TextView) itemView.findViewById(R.id.hfrist_market_price);
        hfrist_shop_price = (TextView) itemView.findViewById(R.id.hfrist_shop_price);
        }
    }
}
