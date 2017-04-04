package yunifn.bawe.com.yunifang.moreimg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;

import yunifn.bawe.com.yunifang.R;

public class Moreimg2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreimg2);
        RecyclerView second_recyclerView= (RecyclerView) findViewById(R.id.second_recyclerView);
        second_recyclerView.setLayoutManager(new OverLayCardLayoutManager());
        CardConfig.initConfig(this);

//        ItemTouchHelper.Callback callback=new RenRenCallback(second_recyclerView,)
        CardConfig.MAX_SHOW_COUNT = 6;
    }
}
