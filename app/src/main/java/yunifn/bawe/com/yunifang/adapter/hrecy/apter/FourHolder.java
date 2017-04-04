package yunifn.bawe.com.yunifang.adapter.hrecy.apter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class FourHolder extends RecyclerView.ViewHolder {

    private final RecyclerView recyy_four;

    public FourHolder(View itemView) {
        super(itemView);
        recyy_four = (RecyclerView) itemView.findViewById(R.id.recyy_four);

    }

    public void setWview(final Context context, LookaltBean.DataBean list) {
        recyy_four.setLayoutManager(new GridLayoutManager(context,2, LinearLayoutManager.VERTICAL,false));
        FourHolderadapter fourHolderadapter = new FourHolderadapter(context, list);
        recyy_four.setAdapter(fourHolderadapter);

    }
}
