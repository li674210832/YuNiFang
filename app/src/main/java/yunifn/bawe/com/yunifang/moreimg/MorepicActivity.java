package yunifn.bawe.com.yunifang.moreimg;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import yunifn.bawe.com.yunifang.Http.OkhttpActiviy;
import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.hrecy.HRecycleviewadapter;
import yunifn.bawe.com.yunifang.bean.LookaltBean;
import yunifn.bawe.com.yunifang.bean.More;

public class MorepicActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<More.DataBean.GoodsBriefBean> goodsBrief;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morepic);
        initView();
        Context con;
        View view;

    }

    public void initView(){

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        CarouselLayoutManager carouselLayoutManager=new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL,true);
        carouselLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        recyclerView.setLayoutManager(carouselLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new CenterScrollListener());
      new GetDataTask().execute();
       /* CarouselLayoutManager carouselLayoutManager1=new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL,true);
        carouselLayoutManager1.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        recyclerView1.setLayoutManager(carouselLayoutManager1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(new MyAdapter(this));
        recyclerView1.addOnScrollListener(new CenterScrollListener());*/
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
                String url="http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414";
                String s = OkhttpActiviy.okHttp(url);
                Gson gson = new Gson();
                More more = gson.fromJson(s, More.class);
                More.DataBean data = more.getData();
                goodsBrief = data.getGoodsBrief();

                Log.d("zzz","more more*****"+ more.toString());
                // Log.d("zzz","defaultGoodsList*****"+ defaultGoodsList.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            recyclerView.setAdapter(new MyAdapter(MorepicActivity.this,goodsBrief));
            super.onPostExecute(result);
        }
    }
}
