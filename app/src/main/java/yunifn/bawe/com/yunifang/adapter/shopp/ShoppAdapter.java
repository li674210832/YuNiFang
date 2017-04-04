package yunifn.bawe.com.yunifang.adapter.shopp;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.dbutil.GoodBeann;
import yunifn.bawe.com.yunifang.fragment.Shoppingfragment;
import yunifn.bawe.com.yunifang.listenner.OnRecyclerViewItemClickListener;

/**
 * Created by Lenovo on 2017/3/27.
 * author ：李宁
 * 类注释：
 */

public class ShoppAdapter  extends RecyclerView.Adapter<ShoppAdapter.MviewHolder>{
    private  ArrayList<GoodBeann> listgood;
  private  Shoppingfragment sss;
    private Context context;
    public ShoppAdapter(ArrayList<GoodBeann> listgood, Context context, Shoppingfragment sss) {
        this.listgood = listgood;
        this.context = context;
        this.sss = sss;
    }


    @Override
    public MviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shopp_car, parent, false);
        return new MviewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(final MviewHolder holder, final int position) {
        Glide.with(context).load(listgood.get(position).getGood_img()).into(holder.carImg);
        holder.carName.setText(listgood.get(position).getGoodname());
        holder.carShopprice.setText("￥："+listgood.get(position).getGood_shop_price());

        holder.tvNumm.setText(""+sss.tnum);
        Log.d("zzz", "adapterlistgood:"+listgood.toString());
        holder.tvJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sss.tnum+=1;
                holder.tvNumm.setText(""+sss.tnum);
            }
        });
        holder.tvJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sss.tnum-=1;
                holder.tvNumm.setText(""+sss.tnum);
                if(sss.tnum<0){
                    sss.tnum=0;
                    holder.tvNumm.setText(""+sss.tnum);
                } }  });
           holder.check.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //判断一下当前是否选中
                   boolean checked = holder.check.isChecked();
                   listgood.get(position).setCheckboxxx(checked);
                   //重新设置价格

                    sss.setPrice();

               }
           });
       holder.check.setChecked(listgood.get(position).isCheckboxxx());



    }

    @Override
    public int getItemCount() {
        return listgood.size();

    }

    class MviewHolder extends RecyclerView.ViewHolder{
        private CheckBox check;
        private ImageView carImg;
        private TextView carName;
        private TextView carShopprice;

        private TextView tvJian;
        private TextView tvNumm;
        private TextView tvJia;
        public MviewHolder(View itemView) {
            super(itemView);
            check = (CheckBox)itemView.findViewById( R.id.check );
            carImg = (ImageView)itemView.findViewById( R.id.car_img );
            carName = (TextView)itemView.findViewById( R.id.car_name );
            carShopprice = (TextView)itemView.findViewById( R.id.car_shopprice );
                tvJian = (TextView)itemView.findViewById( R.id.tv_jian );
                tvNumm = (TextView)itemView.findViewById( R.id.tv_numm );
                tvJia = (TextView)itemView.findViewById( R.id.tv_jia );



        }
    }
}
