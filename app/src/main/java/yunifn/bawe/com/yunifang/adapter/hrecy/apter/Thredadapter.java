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

public class Thredadapter extends RecyclerView.Adapter<Thredadapter.FviewHolder> {

    private  Context context;
    private  List<LookaltBean.DataBean.ActivityInfoBean.ActivityInfoListBean> activityInfoList1;

    public Thredadapter(Context context, List<LookaltBean.DataBean.ActivityInfoBean.ActivityInfoListBean> activityInfoList1) {
        this.context = context;
        this.activityInfoList1 = activityInfoList1;
    }

    @Override
    public FviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hthred, parent, false);

        return new FviewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(FviewHolder holder, int position) {

        Glide.with(context).load(activityInfoList1.get(position).getActivityImg()).into(holder.hthredactivityImg);
    }

    @Override
    public int getItemCount() {
        return activityInfoList1.size();
    }

     class FviewHolder extends RecyclerView.ViewHolder {

         private final ImageView hthredactivityImg;

         public FviewHolder(View itemView) {
             super(itemView);
             hthredactivityImg = (ImageView) itemView.findViewById(R.id.hthredactivityImg);

         }
     }
}
