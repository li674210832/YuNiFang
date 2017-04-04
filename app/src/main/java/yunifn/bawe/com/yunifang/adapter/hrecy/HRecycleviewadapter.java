package yunifn.bawe.com.yunifang.adapter.hrecy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.hrecy.apter.FourHolder;
import yunifn.bawe.com.yunifang.bean.LookaltBean;

/**
 * Created by Lenovo on 2017/3/22.
 * author ：李宁
 * 类注释：
 */

public class HRecycleviewadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
   private Context context;
    private LookaltBean.DataBean list;
    private static final int FIRST_TYPE = 0;
    private static final int SECOND_TYPE = 1;
    private static final int THIRD_TYPE = 2;
    private static final int FOUR_TYPE = 3;
    private int type = FIRST_TYPE;
    private List<LookaltBean.DataBean.BestSellersBean.GoodsListBeanX> goodsList;

    public HRecycleviewadapter(Context context, LookaltBean.DataBean list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
   RecyclerView.ViewHolder viewHolder =null;
    switch (viewType){
        case 0:
            View inflate = LayoutInflater.from(context).inflate(R.layout.frist_recy, parent, false);
            viewHolder=new FristHolder(inflate);

            break;
        case 1:
            //传递数据
            View inflate3 = LayoutInflater.from(context).inflate(R.layout.thred_recy, parent, false);
            viewHolder=new ThredHolder(inflate3);

            break;
        case 2:
            View inflate2 = LayoutInflater.from(context).inflate(R.layout.second_recy, parent, false);
            viewHolder=new SecondHolder(inflate2);
            break;

        case 3:
            View inflate33 = LayoutInflater.from(context).inflate(R.layout.hfour_recy, parent, false);
            viewHolder=new FourHolder(inflate33);
            break;

    }

        return viewHolder;
    }



    /**
     * 判断当前条目类型
     *

     */
    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:
                type = FIRST_TYPE;
                break;
            case 1:
                type = SECOND_TYPE;
                break;
            case 2:
                type = THIRD_TYPE;
                break;

            case 3:
                type = FOUR_TYPE;
                break;

        }
        return type;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //获取条目类型
        int itemViewType = getItemViewType(position);

        switch (itemViewType) {
            case 0:
              FristHolder fristHolder= (FristHolder) holder;
                List<LookaltBean.DataBean.BestSellersBean> bestSellers = list.getBestSellers();
                for (int i = 0; i < bestSellers.size(); i++) {
                    goodsList = bestSellers.get(i).getGoodsList();
                }
                fristHolder.mSetView(goodsList,context);

                break;
            case 1:
                ThredHolder thredHolder= (ThredHolder) holder;
                List<LookaltBean.DataBean.ActivityInfoBean.ActivityInfoListBean> activityInfoList1 = list.getActivityInfo().getActivityInfoList();
                thredHolder.mmSetView(activityInfoList1,context);

                break;
            case 2:
                SecondHolder secon= (SecondHolder) holder;

                secon.mmmSetView(list,context);
                break;
            case 3:
                         FourHolder fourHolder = (FourHolder) holder;
                fourHolder.setWview(context,list);

                break;


        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }



}
