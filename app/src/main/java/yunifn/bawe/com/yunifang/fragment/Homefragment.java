package yunifn.bawe.com.yunifang.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yunifn.bawe.com.yunifang.Http.OkhttpActiviy;
import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.LookaltAdapter;
import yunifn.bawe.com.yunifang.adapter.hrecy.HRecycleviewadapter;
import yunifn.bawe.com.yunifang.all.Goodsdetails;
import yunifn.bawe.com.yunifang.base.BaseFragment;
import yunifn.bawe.com.yunifang.bean.LookaltBean;
import yunifn.bawe.com.yunifang.fragment.home.GlideImageLoader;
import yunifn.bawe.com.yunifang.fragment.home.HomeExchange;
import yunifn.bawe.com.yunifang.fragment.home.LookAlt;
import yunifn.bawe.com.yunifang.listenner.OnRecyclerViewItemClickListener;

import static android.R.id.list;

/**
 * Created by Lenovo on 2017/3/16.
 * author ：李宁
 * 类注释：
 */

public class Homefragment extends BaseFragment implements View.OnClickListener{
    public List<Integer>imgglist=new ArrayList<>();
    private RecyclerView recyclerView;
    private String resulttt;
    private HRecycleviewadapter hRecycleviewadapter;
    private LookaltBean.DataBean lookaltBeen;

    //类似于onCreatView
    @Override
    protected View initview() {
        View vv = View.inflate(mContext, R.layout.homexml, null);
//找到pulltorefreshscrollview
        final PullToRefreshScrollView plist = (PullToRefreshScrollView) vv.findViewById(R.id.pulltoRefreshScrollview);
        //找到scrollview中的子元素
        LinearLayout lin = (LinearLayout) vv.findViewById(R.id.scroll);
        View inflate = View.inflate(mContext, R.layout.home_banner, null);
        viewpager(inflate);
        lin.addView(inflate);
        new GetDataTask().execute();
        lin.setFocusable( true);
        lin.setFocusableInTouchMode( true);
        lin.requestFocus();
        plist.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        plist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                plist.onRefreshComplete();
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new GetDataTask().execute();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                new GetDataTask().execute();
            }
        });
       return  vv;
    }
   //轮播
    private void viewpager(View inflate) {
        Banner banner = (Banner) inflate.findViewById(R.id.banner);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recy);
        initrecycleview();
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        image();
        //设置图片集合
        banner.setImages(imgglist);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
       LinearLayout  homelin_dui= (LinearLayout) inflate.findViewById(R.id.homelin_dui);
        homelin_dui.setOnClickListener(this);
        TextView  tv= (TextView) inflate.findViewById(R.id.tv_more);
        tv.setOnClickListener(this);
    }

    private void initrecycleview() {
        lookaltBeen = new LookaltBean.DataBean ();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //hRecycleviewadapter = new HRecycleviewadapter(getActivity(), lookaltBeen);


    }



    private void image() {
        imgglist.add(R.drawable.efficacy);
        imgglist.add(R.drawable.eshuxing);
        imgglist.add(R.drawable.skin);
        imgglist.add(R.drawable.a1);
        imgglist.add(R.drawable.a2);
    }

    //类似于onCreatActivity
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_more:
                Intent intent = new Intent(getActivity(), LookAlt.class);
                startActivity(intent);
                break;
            case R.id.homelin_dui:
                Intent intentt = new Intent(getActivity(), HomeExchange.class);
                startActivity(intentt);
                break;
        }
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            try {
                String url="http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
                resulttt = OkhttpActiviy.okHttp(url);
                Gson gson = new Gson();
                LookaltBean lookaltBeann = gson.fromJson(resulttt, LookaltBean.class);
                LookaltBean.DataBean data = lookaltBeann.getData();

                    lookaltBeen=data;
               // list.addAll(defaultGoodsList);
                Log.d("zzz","result222*****"+ resulttt.toString());
                Log.d("zzz","data data*****"+ data.toString());
                Log.d("zzz","lookaltBeen data*****"+ lookaltBeen.toString());
               // Log.d("zzz","defaultGoodsList*****"+ defaultGoodsList.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {


            hRecycleviewadapter = new HRecycleviewadapter(getActivity(), lookaltBeen);

           // lookaltAdapter.notifyDataSetChanged();
            //把下拉和上拉状态还原
            recyclerView.setAdapter(hRecycleviewadapter);
            hRecycleviewadapter.notifyDataSetChanged();




            super.onPostExecute(result);
        }
    }
}
