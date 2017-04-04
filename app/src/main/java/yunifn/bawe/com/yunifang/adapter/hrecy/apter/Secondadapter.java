package yunifn.bawe.com.yunifang.adapter.hrecy.apter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class Secondadapter extends RecyclerView.Adapter<Secondadapter.SrcViholder>{
     private Context context;
    private LookaltBean.DataBean list;
    private List<LookaltBean.DataBean.SubjectsBean> subjects;


    public Secondadapter(Context context,LookaltBean.DataBean list) {
        this.context = context;
        this.list = list;
        subjects = list.getSubjects();

    }

    @Override
    public SrcViholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hsecond, parent, false);

        return new SrcViholder(inflate);
    }

    @Override
    public void onBindViewHolder(SrcViholder holder, int position) {

        for (int i = 0; i < subjects.size() ; i++) {
            Glide.with(context).load(subjects.get(position).getImage()).into(holder.second_image);

        }

        holder.SsetView(context,subjects.get(position).getGoodsList(),position);
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

      class SrcViholder extends RecyclerView.ViewHolder{


        private final ImageView second_image;
        private final RecyclerView recy;


        public SrcViholder(View itemView) {
            super(itemView);
            second_image = (ImageView) itemView.findViewById(R.id.second_image);

            recy = (RecyclerView) itemView.findViewById(R.id.recy44);

        }

        public void SsetView(Context context, List<LookaltBean.DataBean.SubjectsBean.GoodsListBean> goodsList, int position) {
            recy.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            SecondEndrecyAdapter secondEndrecyAdapter = new SecondEndrecyAdapter(context, goodsList);
            recy.setAdapter(secondEndrecyAdapter);

        }
    }
}
