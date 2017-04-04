package yunifn.bawe.com.yunifang.app;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Lenovo on 2017/3/30.
 * author ：李宁
 * 类注释：
 */

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
