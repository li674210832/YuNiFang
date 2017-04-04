package yunifn.bawe.com.yunifang.adapter.hrecy.apter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class FourHolderadapter extends RecyclerView.Adapter<FourHolderadapter.FourViewholder> {
    private Context context;
    private  LookaltBean.DataBean list;
    private final List<LookaltBean.DataBean.DefaultGoodsListBean> defaultGoodsList;
    public OnRecyclerViewItemClickListener onItemClickListener;


    public FourHolderadapter(Context context, LookaltBean.DataBean list) {
        this.context = context;
        this.list = list;
        defaultGoodsList = list.getDefaultGoodsList();
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public FourViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hfour_hour, parent, false);

        return new FourViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(FourViewholder holder, final int position) {

        for (int i = 0; i < defaultGoodsList.size(); i++) {


       holder.four_efficacy.setText(defaultGoodsList.get(position).getEfficacy());
       holder.four_goods_name.setText(defaultGoodsList.get(position).getGoods_name());
       holder.four_market_price.setText(defaultGoodsList.get(position).getMarket_price()+"");
       holder.four_shop_price.setText(defaultGoodsList.get(position).getShop_price()+"");

        Glide.with(context).load(defaultGoodsList.get(position).getGoods_img()).into(holder.four_goodimg);
          holder.lin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(context, Goodsdetails.class);
                  intent.putExtra("type",defaultGoodsList.get(position).getId());
                //  Log.d("zzz","id***defaultGoodsList**"+ defaultGoodsList.get(position).getId());
                  context.startActivity(intent);
              }
          });

    }
    }

    @Override
    public int getItemCount() {
        return defaultGoodsList.size();
    }

    class FourViewholder extends RecyclerView.ViewHolder{

        private final TextView four_efficacy;
        private final TextView four_goods_name;
        private final TextView four_market_price;
        private final TextView four_shop_price;
        private final ImageView four_goodimg;
        private final LinearLayout lin;

        public FourViewholder(View itemView) {
            super(itemView);
            four_efficacy = (TextView) itemView.findViewById(R.id.four_efficacy);
            four_goods_name = (TextView) itemView.findViewById(R.id.four_goods_name);
            four_market_price = (TextView) itemView.findViewById(R.id.four_market_price);
            four_shop_price = (TextView) itemView.findViewById(R.id.four_shop_price);
            four_goodimg = (ImageView) itemView.findViewById(R.id.four_goodimg);
            lin = (LinearLayout) itemView.findViewById(R.id.four_lin);


        }
    }
}
