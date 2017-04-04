package yunifn.bawe.com.yunifang.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.shopp.ShoppAdapter;
import yunifn.bawe.com.yunifang.base.BaseFragment;
import yunifn.bawe.com.yunifang.dbutil.DbutilBean;
import yunifn.bawe.com.yunifang.dbutil.GoodBeann;
import yunifn.bawe.com.yunifang.listenner.OnRecyclerViewItemClickListener;
import yunifn.bawe.com.yunifang.pay.PayActivity;

/**
 * Created by Lenovo on 2017/3/16.
 * author ：李宁
 * 类注释：
 */

public class Shoppingfragment extends BaseFragment {

    private PullToRefreshScrollView plist;
    private RecyclerView sp_recy;
    public  ArrayList<GoodBeann> listgood;
    public int tnum=1;

    private ShoppAdapter adapter;
    private TextView tv_quan;
    public   TextView tv_price,pay;
    private LinearLayout lin_jiesuan;
  //  private Mrecyadapter adapter;

    //类似于onCreatView
    @Override
    protected View initview() {
        View vv = View.inflate(mContext, R.layout.shoppxml, null);
        initviewid(vv);

        dbutil();//从数据库里找出数据
        //适配器
        adapter = new ShoppAdapter(listgood,getActivity(),Shoppingfragment.this);
        //adapter = new Mrecyadapter();
        sp_recy.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        plist.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        //刷新数据
        plist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                plist.onRefreshComplete();
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                dbutil();
                adapter.notifyDataSetChanged();

                tv_quan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //只需要对象
                        for (int i = 0; i < listgood.size(); i++) {
                            //全选
                            listgood.get(i).setCheckboxxx(true);
                        }
                        //刷新
                        adapter.notifyDataSetChanged();
                        setPrice();
                    }
                });

                pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("支付")
                                .setMessage("目前只能支付宝")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getActivity(), PayActivity.class);
                                        startActivity(intent);

                                    }
                                })
                                .setNegativeButton("取消",null)
                                .show();

                    }
                });
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                dbutil();
                adapter.notifyDataSetChanged();
            }
        });
    // 全选
        tv_quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //只需要对象
                for (int i = 0; i < listgood.size(); i++) {
                    //全选
                    listgood.get(i).setCheckboxxx(true);
                }
                //刷新
                adapter.notifyDataSetChanged();
                setPrice();
            }
        });
        return vv;
    }
    /**
     * 修改价格
     */
    public  void setPrice() {
        int price = 0;
        //只需要对象
        for (int i = 0; i < listgood.size(); i++) {
            //全选
//            productList.get(i).setChecked(false);
            boolean checked = listgood.get(i).isCheckboxxx();
            if (checked) {
                price = (int) (price + listgood.get(i).getGood_shop_price() * tnum);
            }
        }
        tv_price.setText("总计:￥"+price);
    }

    private void initviewid(View vv) {
        plist = (PullToRefreshScrollView) vv.findViewById(R.id.shopp_scrview);
        LinearLayout lin= (LinearLayout) vv.findViewById(R.id.shop_lin);
        sp_recy = (RecyclerView) vv.findViewById(R.id.sp_recy);
        sp_recy.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,true));
        tv_quan = (TextView) vv.findViewById(R.id.tv_quan);
        pay = (TextView) vv.findViewById(R.id.pay);
        tv_price = (TextView) vv.findViewById(R.id.tv_price);
        lin_jiesuan = (LinearLayout) vv.findViewById(R.id.lin_jiesuan);
    }
    private void dbutil()  {
         DbUtils  dbUtils = DbUtils.create(getActivity(), "shopp.db", 1, null);
        try {
            List<DbutilBean> listutil  = dbUtils.findAll(DbutilBean.class);
            listgood = new ArrayList<>();
            for (DbutilBean s:listutil) {
                GoodBeann goodBeann = new GoodBeann();
                Log.d("zzz", "s:"+s.toString());
                String goo_efficacy = s.getGoo_efficacy();
                String good_img = s.getGood_img();
                String goodname = s.getGoodname();
                String good_market_price = s.getGood_market_price();
                double good_shop_price = s.getGood_shop_price();
                int idd = s.getId();

                goodBeann.setId(idd);
                goodBeann.setGoodname(goodname);
                goodBeann.setGood_market_price(good_market_price+"");
                goodBeann.setGoo_efficacy(goo_efficacy);
                goodBeann.setGood_img(good_img);
                goodBeann.setGood_shop_price(good_shop_price);
                listgood.add(goodBeann);
                Log.d("zzz", "listgood:"+listgood.toString());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    //类似于onCreatActivity
    @Override
    protected void initData() {
        super.initData();
    }


}
