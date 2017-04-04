package yunifn.bawe.com.yunifang.adapter.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.bean.ClassifyBean;

/**
 * Created by Lenovo on 2017/3/25.
 * author ：李宁
 * 类注释：
 */

public class ClassRecyaleviewAdapter extends RecyclerView.Adapter<ClassRecyaleviewAdapter.CViewholder> {
  private   List<ClassifyBean.DataBean.GoodsBriefBean> goodsBrief;
    private Context context;

    public ClassRecyaleviewAdapter(List<ClassifyBean.DataBean.GoodsBriefBean> goodsBrief, Context context) {

        this.goodsBrief = goodsBrief;
        this.context = context;
    }

    @Override
    public CViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hfour_hour, parent, false);

        return new CViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(CViewholder holder, int position) {
        for (int i = 0; i < goodsBrief.size(); i++) {


        holder.four_efficacy.setText(goodsBrief.get(position).getEfficacy());
        holder.four_goods_name.setText(goodsBrief.get(position).getGoods_name());
        holder.four_market_price.setText(goodsBrief.get(position).getMarket_price()+"");
        holder.four_shop_price.setText(goodsBrief.get(position).getShop_price()+"");

        Glide.with(context).load(goodsBrief.get(position).getGoods_img()).into(holder.four_goodimg);
    }}

    @Override
    public int getItemCount() {
        return goodsBrief.size();
    }

    class CViewholder extends RecyclerView.ViewHolder{
        private final TextView four_efficacy;
        private final TextView four_goods_name;
        private final TextView four_market_price;
        private final TextView four_shop_price;
        private final ImageView four_goodimg;
        private final LinearLayout lin;
        public CViewholder(View itemView) {
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
