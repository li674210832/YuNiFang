package yunifn.bawe.com.yunifang.all;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yunifn.bawe.com.yunifang.Http.OkhttpActiviy;
import yunifn.bawe.com.yunifang.R;
import yunifn.bawe.com.yunifang.adapter.LookaltAdapter;
import yunifn.bawe.com.yunifang.bean.GoodsdetailsBean;
import yunifn.bawe.com.yunifang.bean.LookaltBean;
import yunifn.bawe.com.yunifang.dbutil.DbutilBean;
import yunifn.bawe.com.yunifang.fragment.home.LookAlt;

public class Goodsdetails extends AppCompatActivity {

    private ViewPager vp;

    private List<GoodsdetailsBean.DataBean.GoodsBean.GalleryBean> gallery;
    private ArrayList<ImageView> listimg;
    private LinearLayout lin;
    private JSONObject jsonObject;
    private ListView lv;
    private JSONArray js;
    private int length333;
    private TextView tit1,tit2,tit3,good_shop_price,good_market_price,goo_goods_name,goo_efficacy;
    private List<GoodsdetailsBean.DataBean.ActivityBean> listactivity;
    private GoodsdetailsBean.DataBean.GoodsBean goods;
    private LinearLayout shoppcar;
    private DbUtils dbUtils;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetails);
        Intent intent = getIntent();
        id = intent.getStringExtra("type");

        dbutil();//数据库
        vp = (ViewPager) findViewById(R.id.good_vp_gallery);
        lin = (LinearLayout) findViewById(R.id.good_lin);
        lv = (ListView) findViewById(R.id.lv_gg);
        tit1 = (TextView) findViewById(R.id.good_tit1);
         tit2= (TextView) findViewById(R.id.good_tit12);
         tit3= (TextView) findViewById(R.id.good_tit13);
        goo_efficacy= (TextView) findViewById(R.id.goo_efficacy);
        goo_goods_name= (TextView) findViewById(R.id.goo_goods_name);
        good_market_price= (TextView) findViewById(R.id.good_market_price);
        good_shop_price= (TextView) findViewById(R.id.good_shop_price);
        shoppcar = (LinearLayout) findViewById(R.id.shoppcar);
        shoppcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppcarview();//设置购物车
            }
        });


        new GetDataTask().execute();

        yuan();
        vpset();

    }

    private void dbutil() {
        try {
        dbUtils = DbUtils.create(this, "shopp.db", 1, new DbUtils.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbUtils dbUtils, int i, int i1) {

            }
        });

            dbUtils.createTableIfNotExist(DbutilBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    //设置购物车
    private void shoppcarview() {

        DbutilBean dbutilBean = new DbutilBean();
        dbutilBean.setGood_img(gallery.get(0).getThumb_url());
        dbutilBean.setGoo_efficacy(goods.getEfficacy());
        dbutilBean.setGood_market_price(goods.getMarket_price()+"");
        dbutilBean.setGoodname(goods.getGoods_name());
        dbutilBean.setGood_shop_price(goods.getShop_price());

        try {
            dbUtils.save(dbutilBean);
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void vpset() {
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < listimg.size(); i++) {
                    ImageView imageView = listimg.get(i);
                    if(i==position){
                        imageView.setImageResource(R.drawable.yuan);
                    }else{
                        imageView.setImageResource(R.drawable.yuan2);
                    }


                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void yuan() {

        listimg = new ArrayList<>();
        for (int i = 0; i < length333; i++) {
            ImageView imageView = new ImageView(this);
            if(i==0){
                imageView.setImageResource(R.drawable.yuan);
            }else{
                imageView.setImageResource(R.drawable.yuan2);
            }

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10,10));
            listimg.add(imageView);
            lin.addView(imageView);
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
                String url="http://m.yunifang.com/yunifang/mobile/goods/detail?random=46389&encode=70ed2ed2facd7a812ef46717b37148d6&id="+id;
                Log.d("zzz","id*****"+ id);

                String resulttt = OkhttpActiviy.okHttp(url);
                Gson gson = new Gson();
                GoodsdetailsBean goodsdetailsBean = gson.fromJson(resulttt, GoodsdetailsBean.class);
                listactivity = goodsdetailsBean.getData().getActivity();
                Log.d("zzz","goodsdetailsBean*****"+ goodsdetailsBean.toString());
                goods = goodsdetailsBean.getData().getGoods();

                gallery = goods.getGallery();

                String goods_desc = goods.getGoods_desc();
                Log.d("zzz","goods_desc*****"+ goods_desc.toString());
                js = new JSONArray(goods_desc);
                for (int i = 0; i < js.length(); i++) {
                    jsonObject = js.optJSONObject(i);
                   // Log.d("zzz","jsonObject+i*****"+ jsonObject.toString());

                }





            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {

            ininvp();
            lv.setAdapter(new Lvadapter());
            for (int i = 0; i < listactivity.size(); i++) {
                tit1.setText(listactivity.get(0).getTitle());
                tit2.setText(listactivity.get(1).getTitle());
                tit3.setText(listactivity.get(2).getTitle());
            }

            goo_goods_name.setText(goods.getGoods_name());
            goo_efficacy.setText(goods.getEfficacy());
            good_market_price.setText(goods.getMarket_price()+"");
            good_shop_price.setText(goods.getShop_price()+"");
            super.onPostExecute(result);
        }
    }
    private void ininvp() {

        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return gallery.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return object==view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(Goodsdetails.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
                for (int i = 0; i < gallery.size(); i++) {
                    length333 = gallery.get(position).getThumb_url().length();
                    Glide.with(Goodsdetails.this).load(gallery.get(position).getThumb_url()).into(imageView);
                }

                container.addView(imageView);
                return imageView;
            }
        });

    }

  class Lvadapter extends BaseAdapter{
      @Override
      public int getCount() {
          return js.length();
      }

      @Override
      public Object getItem(int position) {
          return null;
      }

      @Override
      public long getItemId(int position) {
          return 0;
      }

      @RequiresApi(api = Build.VERSION_CODES.KITKAT)
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
          ViewHolder viewHolder;
          if(convertView==null){
               viewHolder = new ViewHolder();
              convertView= View.inflate(Goodsdetails.this,R.layout.goodimg,null);
              viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imggood);
              convertView.setTag(viewHolder);
          }else{
              viewHolder= (ViewHolder) convertView.getTag();
          }

              try {
                  JSONArray jsonArray=new JSONArray(js);
                  for (int i = 0; i < jsonArray.length(); i++) {
                      JSONObject jsonObject2 = jsonArray.optJSONObject(i);
                      Glide.with(Goodsdetails.this).load(jsonObject2).into(viewHolder.imageView);

                  }
              } catch (JSONException e) {
                  e.printStackTrace();
              }



          return convertView;
      }
  }
    class  ViewHolder {

        public ImageView imageView;
    }
}