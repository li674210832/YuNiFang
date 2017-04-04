package yunifn.bawe.com.yunifang;

import android.app.Application;

/**
 * Created by jinguo on 2017/3/16.
 */

public class CrashApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //全局的异常捕获
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}
