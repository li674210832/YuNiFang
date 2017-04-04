package yunifn.bawe.com.yunifang.fragment.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import yunifn.bawe.com.yunifang.R;

public class HomeExchange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_exchange);
        ImageView  img= (ImageView) findViewById(R.id.HomeExchange_img1);
        ImageView  img2= (ImageView) findViewById(R.id.HomeExchange_img12);
        ImageView  img3= (ImageView) findViewById(R.id.HomeExchange_img13);
        ImageView  img4= (ImageView) findViewById(R.id.HomeExchange_img14);
        ImageView  img5= (ImageView) findViewById(R.id.HomeExchange_img15);
        Glide.with(HomeExchange.this).load("http://ocszs3xyu.qnssl.com/yunifang/web/assets/images/cash/cash-20160823/1.jpg").into(img);
        Glide.with(HomeExchange.this).load("http://ocszs3xyu.qnssl.com/yunifang/web/assets/images/cash/cash-20160823/2.jpg").into(img2);
        Glide.with(HomeExchange.this).load("http://ocszs3xyu.qnssl.com/yunifang/web/assets/images/cash/cash-20160823/3.jpg").into(img3);
        Glide.with(HomeExchange.this).load("http://ocszs3xyu.qnssl.com/yunifang/web/assets/images/cash/cash-20160823/4.jpg").into(img4);
        Glide.with(HomeExchange.this).load("http://ocszs3xyu.qnssl.com/yunifang/web/assets/images/cash/cash-20160823/5.jpg/").into(img5);
    }
}
