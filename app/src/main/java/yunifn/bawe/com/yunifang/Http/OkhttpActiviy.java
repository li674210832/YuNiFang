package yunifn.bawe.com.yunifang.Http;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lenovo on 2017/3/19.
 * author ：李宁
 * 类注释：
 */

public class OkhttpActiviy {

    public  static String okHttp(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response execute = client.newCall(request).execute();

        return  execute.body().string();
    }

}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       