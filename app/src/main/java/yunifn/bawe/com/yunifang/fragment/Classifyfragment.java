package yunifn.bawe.com.yunifang.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yunifn.bawe.com.yunifang.Http.OkhttpActiviy;
import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.classify.ClassRecyaleviewAdapter;
import yunifn.bawe.com.yunifang.adapter.hrecy.HRecycleviewadapter;
import yunifn.bawe.com.yunifang.base.BaseFragment;
import yunifn.bawe.com.yunifang.bean.ClassifyBean;
import yunifn.bawe.com.yunifang.bean.LookaltBean;
import yunifn.bawe.com.yunifang.moreimg.MorepicActivity;

/**
 * Created by Lenovo on 2017/3/16.
 * author ：李宁
 * 类注释：
 */

public class Classifyfragment extends BaseFragment {

    private List<ClassifyBean.DataBean.GoodsBriefBean> goodsBrief;
    private List<ClassifyBean.DataBean.GoodsBriefBean> listgoodsBrief=new ArrayList<>();
    private RecyclerView recyclerView;

    //类似于onCreatView
    @Override
    protected View initview() {
        View vv = View.inflate(mContext, R.layout.classifyxml, null);
        final PullToRefreshScrollView plist = (PullToRefreshScrollView) vv.findViewById(R.id.pulltoRefreshScrollview_class);
        //找到scrollview中的子元素
        LinearLayout lin = (LinearLayout) vv.findViewById(R.id.class_lin_scr);

        View inflate = View.inflate(mContext, R.layout.classify_child, null);

          findview(inflate);
        lin.addView(inflate);
        new GetDataTask().execute();
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
        return vv;
    }

    private void findview(View inflate) {
        recyclerView = (RecyclerView) inflate.findViewById(R.id.class_recy1);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.VERTICAL,false));
        ImageView classimg_h1= (ImageView) inflate.findViewById(R.id.classimg_h1);
        ImageView classimg_h2= (ImageView) inflate.findViewById(R.id.classimg_h2);
        ImageView classimg_h3= (ImageView) inflate.findViewById(R.id.classimg_h2);
        classimg_h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MorepicActivity.class));
            }
        });

        classimg_h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MorepicActivity.class));
            }
        });
        classimg_h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MorepicActivity.class));
            }
        });



    }

    //类似于onCreatActivity
    @Override
    protected void initData() {
        super.initData();
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
                String url="http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414";
                String resulttt = OkhttpActiviy.okHttp(url);
                Gson gson = new Gson();
                ClassifyBean classifyBean = gson.fromJson(resulttt, ClassifyBean.class);
                goodsBrief = classifyBean.getData().getGoodsBrief();
                listgoodsBrief.addAll(goodsBrief);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            ClassRecyaleviewAdapter classRecyaleviewAdapter = new ClassRecyaleviewAdapter(listgoodsBrief, getActivity());
            recyclerView.setAdapter(classRecyaleviewAdapter);
            classRecyaleviewAdapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }
    }
}
