package yunifn.bawe.com.yunifang.adapter.hrecy;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.hrecy.apter.Thredadapter;
import yunifn.bawe.com.yunifang.bean.LookaltBean;

/**
 * Created by Lenovo on 2017/3/22.
 * author ：李宁
 * 类注释：
 */

public class ThredHolder extends RecyclerView.ViewHolder {
    private final RecyclerView recycleview13;

    //public final ImageView hthredactivityImg;


    public ThredHolder(View itemView) {
        super(itemView);
       // hthredactivityImg = (ImageView) itemView.findViewById(R.id.hthredactivityImg);
        recycleview13 = (RecyclerView) itemView.findViewById(R.id.recycleview13);
    }

    public void mmSetView(List<LookaltBean.DataBean.ActivityInfoBean.ActivityInfoListBean> activityInfoList1, Context context) {
        recycleview13.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        Thredadapter thredadapter = new Thredadapter(context, activityInfoList1);
        recycleview13.setAdapter(thredadapter);

    }
}
