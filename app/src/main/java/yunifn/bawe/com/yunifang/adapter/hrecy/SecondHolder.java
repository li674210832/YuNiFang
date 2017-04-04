package yunifn.bawe.com.yunifang.adapter.hrecy;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.hrecy.apter.Secondadapter;
import yunifn.bawe.com.yunifang.bean.LookaltBean;

/**
 * Created by Lenovo on 2017/3/22.
 * author ：李宁
 * 类注释：
 */

public class SecondHolder extends RecyclerView.ViewHolder {
    private final RecyclerView recycleview12;

    public SecondHolder(View itemView) {
        super(itemView);
        recycleview12 = (RecyclerView) itemView.findViewById(R.id.recycleview12);
    }

    public void mmmSetView( LookaltBean.DataBean subjects1,
      Context context) {
        recycleview12.setLayoutManager(new LinearLayoutManager(context));
        Secondadapter secondadapter = new Secondadapter(context, subjects1);
        recycleview12.setAdapter(secondadapter);

    }


}
