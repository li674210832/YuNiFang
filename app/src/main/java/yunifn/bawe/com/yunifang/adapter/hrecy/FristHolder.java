package yunifn.bawe.com.yunifang.adapter.hrecy;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.hrecy.apter.Fristadapter;
import yunifn.bawe.com.yunifang.bean.LookaltBean;

/**
 * Created by Lenovo on 2017/3/22.
 * author ：李宁
 * 类注释：
 */

public class FristHolder extends RecyclerView.ViewHolder {
    private final RecyclerView recycleview11;



    public FristHolder(View itemView) {
        super(itemView);
        recycleview11 = (RecyclerView) itemView.findViewById(R.id.recycleview11);

    }
    public void mSetView(List<LookaltBean.DataBean.BestSellersBean.GoodsListBeanX> bestSellers, Context context){
        recycleview11.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        Fristadapter fristadapter = new Fristadapter(bestSellers,context);
        recycleview11.setAdapter(fristadapter);
    }


}
