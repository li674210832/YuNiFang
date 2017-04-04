package yunifn.bawe.com.yunifang.fragment.home;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.GridView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yunifn.bawe.com.yunifang.Http.OkhttpActiviy;
import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.LookaltAdapter;
import yunifn.bawe.com.yunifang.bean.LookaltBean;

public class LookAlt extends AppCompatActivity {
   Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);

       }
   };
    private String resulttt;
    private PullToRefreshGridView pullgrid;
    private ArrayList<LookaltBean.DataBean.DefaultGoodsListBean> list;
    private GridView mgridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_alt);
           // initview();
        list = new ArrayList<>();
        pullgrid = (PullToRefreshGridView)  findViewById(R.id.pullgrid);
        pullgrid.setMode(PullToRefreshBase.Mode.BOTH);
        mgridView = pullgrid.getRefreshableView();
        pullgrid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                //得到当前刷新的时间
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                //设置更新时间
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new GetDataTask().execute();
            }
        });

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
                LookaltBean lookaltBean = gson.fromJson(resulttt, LookaltBean.class);
                List<LookaltBean.DataBean.DefaultGoodsListBean> defaultGoodsList = lookaltBean.getData().getDefaultGoodsList();
                list.addAll(defaultGoodsList);
                Log.d("zzz","result*****"+ resulttt.toString());
                Log.d("zzz","defaultGoodsList*****"+ defaultGoodsList.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {

            LookaltAdapter lookaltAdapter = new LookaltAdapter(LookAlt.this,list);
            // mAdapter.notifyDataSetChanged();
            mgridView.setAdapter(lookaltAdapter);

            lookaltAdapter.notifyDataSetChanged();
            //把下拉和上拉状态还原
            pullgrid.onRefreshComplete();




            super.onPostExecute(result);
        }
    }



}
