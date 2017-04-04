package yunifn.bawe.com.yunifang.u.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import yunifn.bawe.com.yunifang.R;

public class MainActivity extends AppCompatActivity {

    private ImageView qq_yunifang;
  Handler handler=new Handler(){
      @Override
      public void handleMessage(Message msg) {
          super.handleMessage(msg);
          Intent intent = new Intent(MainActivity.this, Main2Activity.class);
          startActivity(intent);
               finish();
      }
  };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qq_yunifang = (ImageView) findViewById(R.id.qq_yunifang);
        handler.sendEmptyMessageDelayed(1,3000);
    }
}
